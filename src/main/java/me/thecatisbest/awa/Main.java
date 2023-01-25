package me.thecatisbest.awa;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import lombok.Getter;
import me.thecatisbest.awa.commands.CommandManager;
import me.thecatisbest.awa.events.*;
import me.thecatisbest.awa.events.motd.ServerListPingEvents;
import me.thecatisbest.awa.utilis.Utilis;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends JavaPlugin {

    // Instance
    @Getter private static Main instance;
    // Config
    public YamlDocument config;
    // Message
    public YamlDocument message;
    // Module
    public YamlDocument module;
    // Permission
    public YamlDocument permission;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.getCommand("twstcore").setExecutor(new CommandManager(this));
        this.getCommand("twstcore").setTabCompleter(new CommandManager(this));

        registerEvents();

        PluginDescriptionFile pdf = this.getDescription();
        log(
                "[------------ " + pdf.getName() + " Enabled ------------]",
                "-----> Description:",
                "-----> Author: " + pdf.getAuthors(),
                "-----> Version: " + pdf.getVersion(),
                "[----------------------------------------]"
        );

        // Small check to make sure that PlaceholderAPI is installed
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholders(this).register();
        } else {
            Utilis.logMessage(this.getClass(), "PlaceholderAPI was not Found! PAPI placeholders won't work!");
        }

        // Create and update the file
        try {
            // config = YamlDocument.create(folder = new File(getDataFolder().getPath() + "/server icon/"));
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), Objects.requireNonNull(getResource("config.yml")),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            message = YamlDocument.create(new File(getDataFolder(), "message.yml"), Objects.requireNonNull(getResource("message.yml")),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            module = YamlDocument.create(new File(getDataFolder(), "module.yml"), Objects.requireNonNull(getResource("module.yml")),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            permission = YamlDocument.create(new File(getDataFolder(), "permission.yml"), Objects.requireNonNull(getResource("permission.yml")),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PluginDescriptionFile pdf = this.getDescription();
        log(
                "[------------ " + pdf.getName() + " Disabled ------------]",
                "-----> Description:",
                "-----> Author: " + pdf.getAuthors(),
                "-----> Version: " + pdf.getVersion(),
                "[----------------------------------------]"
        );
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new ServerListPingEvents(this), this);
        pm.registerEvents(new JoinMotdEvents(this), this);
        pm.registerEvents(new JoinFireworkEvents(this), this);
        pm.registerEvents(new FirstJoinEvents(this), this);
        pm.registerEvents(new JoinEvents(this), this);
        pm.registerEvents(new LeaveEvents(this), this);
    }

    private void log(String... args) {
        for (String s : args)
            getLogger().info(s);
    }
}
