package me.thecatisbest.awa.commands.SubCommands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommand;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maintenance extends SubCommand {

    private final Main plugin;

    public Maintenance(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "maintenance <setenabled|setdisabled>";
    }

    @Override
    public String getDescription() {
        return plugin.message.getString("SubCommand-Maintenance-Description");
    }

    @Override
    public String getSyntax() {
        return "/twstcore";
    }

    @Override
    public boolean canConsoleExecute() {
        return true;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        if (sender.hasPermission(plugin.permission.getString("Maintenance-Command-View"))) {
            if (args.length == 1) {
                List<String> lore = plugin.message.getStringList("MaintenanceMOTD-Message");
                for (String l : lore)
                    sender.sendMessage(CC.color(l)
                            .replace("%line-1%", plugin.config.getString(CC.color("Maintenance-MOTD.Line-1"))
                                    .replace("%line-2%", plugin.config.getString(CC.color("Maintenance-MOTD.Line-2")))));
            }
        } else {
            sender.sendMessage(CC.color(
                    plugin.message.getString("No-Permission")
                            .replaceAll(("%permission%"), plugin.permission.getString("Maintenance-Command-View"))));
        }

        if (sender.hasPermission(plugin.permission.getString("Maintenance-Command-Edit"))) {
            if (args.length == 2 && args[1].equalsIgnoreCase("setenabled")) {
                if (!plugin.module.getBoolean("Enable-MaintenanceMOTD")) {
                    for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                        if (!allPlayers.hasPermission("Maintenance-Bypass")) {
                            allPlayers.kickPlayer(CC.color(plugin.message.getString("Maintenance-Kick")));
                        }
                    }
                    plugin.module.set("Enable-MaintenanceMOTD", true);
                    sender.sendMessage(CC.color(plugin.message.getString("Maintenance-Enabled")));
                    try {
                        plugin.module.save();
                        plugin.module.reload();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (args.length == 2 && args[1].equalsIgnoreCase("setdisabled")) {
                if (plugin.module.getBoolean("Enable-MaintenanceMOTD")) {
                    plugin.module.set("Enable-MaintenanceMOTD", false);
                    sender.sendMessage(CC.color(plugin.message.getString("Maintenance-Disabled")));
                    try {
                        plugin.module.save();
                        plugin.module.reload();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        if (args.length == 2) {
            List<String> arguments = new ArrayList<>();
            arguments.add("setenabled");
            arguments.add("setdisabled");
            return arguments;
        }
        return null;
    }
}



