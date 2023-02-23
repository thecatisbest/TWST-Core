package me.thecatisbest.awa.commands.SubCommands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommand;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ViewServerMOTD extends SubCommand {

    private final Main plugin;

    public ViewServerMOTD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "servermotd";
    }

    @Override
    public String getDescription() {
        return plugin.message.getString("SubCommand-ViewServerMOTD-Description");
    }

    @Override
    public String getSyntax() {
        return "/twstcore";
    }

    @Override
    public boolean canConsoleExecute(){
        return true;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender.hasPermission(plugin.permission.getString("ViewServerMOTD-Command"))) {
            List<String> lore = plugin.message.getStringList("ViewServerMOTD-Message");
            for (String l : lore)
                sender.sendMessage(CC.serverMOTDColorFormat(l)
                        .replace("%max-players%", String.valueOf(plugin.config.getInt("Server-Max-Players")))
                        .replace("%line-1%", plugin.config.getString("Server-MOTD.Line-1"))
                        .replace("%line-2%", plugin.config.getString("Server-MOTD.Line-2")));
        } else {
            sender.sendMessage(CC.color(
                    plugin.message.getString("No-Permission")
                            .replaceAll(("%permission%"), plugin.permission.getString("ViewServerMOTD-Command"))));
        }
    }
}