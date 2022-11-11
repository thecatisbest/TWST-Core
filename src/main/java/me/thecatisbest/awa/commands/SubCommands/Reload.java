package me.thecatisbest.awa.commands.SubCommands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommand;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Reload extends SubCommand {

    private final Main plugin;

    public Reload(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return plugin.message.getString("SubCommand-Reload-Description");
    }

    @Override
    public String getSyntax() {
        return "/twstcore reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission(plugin.config.getString("Permission.Reload-Command"))) {
            player.sendMessage(CC.color(plugin.message.getString("Starting-Reload")));
            player.sendMessage(CC.color(plugin.message.getString("Success-Reload")));
            try {
                plugin.config.reload();
                plugin.message.reload();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            player.sendMessage(CC.color(
                    plugin.message.getString("No-Permission")
                            .replaceAll(("%permission%"), plugin.config.getString("Permission.Reload-Command"))));
        }
    }
}

