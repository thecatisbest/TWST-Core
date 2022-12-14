package me.thecatisbest.awa;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import me.thecatisbest.awa.commands.CommandManager;
import me.thecatisbest.awa.events.JoinEvents;
import me.thecatisbest.awa.events.JoinFireworkEvents;
import me.thecatisbest.awa.events.JoinMotdEvents;
import me.thecatisbest.awa.events.LeaveEvents;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends JavaPlugin {

    // Config
    public YamlDocument config;
    // Message
    public YamlDocument message;

    @Override
    public void onEnable() {
        // Plugin startup logic
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

        // Create and update the file
        try {
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), Objects.requireNonNull(getResource("config.yml")),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            message = YamlDocument.create(new File(getDataFolder(), "message.yml"), Objects.requireNonNull(getResource("message.yml")),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
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

        pm.registerEvents(new JoinMotdEvents(this), this);
        pm.registerEvents(new JoinFireworkEvents(this), this);
        pm.registerEvents(new JoinEvents(this), this);
        pm.registerEvents(new LeaveEvents(this), this);
    }
    private void log(String... args) {
        for (String s : args)
            getLogger().info(s);
    }
}
