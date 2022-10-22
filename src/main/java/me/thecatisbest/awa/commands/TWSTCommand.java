package me.thecatisbest.awa.commands;

import me.thecatisbest.awa.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class TWSTCommand implements CommandExecutor {

    private final Main plugin;

    public TWSTCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("twstcore")) {
            if (args.length == 0) {
                if (sender.hasPermission(plugin.config.getString("Permission.Help-Command"))) {
                    for (String msg : plugin.config.getStringList("Help.Admin")) {
                        sender.sendMessage(plugin.color(msg));
                    }
                } else {
                    sender.sendMessage(plugin.color(
                            plugin.config.getString("Message.No-Permission")
                                    .replaceAll(("%permission%"), plugin.config.getString("Permission.Help-Command"))));
                    // Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis player " + player.getDisplayName() + "has not permission. (twstcore.commands.reload)"));
                }
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {

                    if (sender.hasPermission(plugin.config.getString("Permission.Reload-Command"))) {
                        sender.sendMessage(plugin.color(plugin.config.getString("Message.Starting-Reload")));
                        try {
                            plugin.config.reload();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        sender.sendMessage(plugin.color(plugin.config.getString("Message.Success-Reload")));
                    } else {
                        sender.sendMessage(plugin.color(
                                plugin.config.getString("Message.No-Permission")
                                        .replaceAll(("%permission%"), plugin.config.getString("Permission.Reload-Command"))));
                        // Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis player " + player.getDisplayName() + "has not permission. (twstcore.commands.reload)"));
                    }
                }
                return true;
            }
        }
        return true;
    }
}