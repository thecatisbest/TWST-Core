package me.thecatisbest.awa.commands.SubCommands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommand;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Helpful extends SubCommand {

    private final Main plugin;

    public Helpful(Main plugin) {
        this.plugin = plugin;
        this.addAlias("helpful");
    }

    @Override
    public String getName() {
        return "helpful";
    }

    @Override
    public String getDescription() {
        return plugin.message.getString("SubCommand-Fly-Description");
    }

    @Override
    public String getSyntax() {
        return "/twstcore";
    }

    @Override
    public boolean canConsoleExecute() {
        return false;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission(plugin.permission.getString("Helpful-Command"))) {
            List<String> lore = plugin.message.getStringList("Helpful-Message");
            for (String l : lore) {
                player.sendMessage(CC.color(l));
            }
        } else {
            player.sendMessage(CC.color(plugin.message.getString("No-Permission"))
                    .replaceAll("%permission%", plugin.permission.getString("Helpful-Command")));
        }
    }
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return null;
    }
}
