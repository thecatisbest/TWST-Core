package me.thecatisbest.awa.commands.SubCommands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommand;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Info extends SubCommand {

    private final Main plugin;

    public Info(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return plugin.message.getString("SubCommand-Info-Description");
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
        if (sender.hasPermission(plugin.permission.getString("Info-Command"))) {
            List<String> lore = plugin.message.getStringList("Info-Message");
            for (String l : lore)
                sender.sendMessage(CC.color(l)
                        .replace("%author%", plugin.getDescription().getAuthors().toString())
                        .replace("%version%", plugin.getDescription().getVersion()));
        } else {
            sender.sendMessage(CC.color(
                    plugin.message.getString("No-Permission")
                            .replaceAll(("%permission%"), plugin.permission.getString("Info-Command"))));
        }
    }
}

