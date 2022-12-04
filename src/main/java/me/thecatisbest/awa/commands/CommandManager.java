package me.thecatisbest.awa.commands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommands.Reload;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("All")
public class CommandManager implements CommandExecutor, TabCompleter {

    private final List<SubCommand> subCommands = new ArrayList<>();
    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;

        subCommands.add(new Reload(plugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission(plugin.config.getString("Permission.Help-Command"))) {


                if (args.length > 0) {
                    for (SubCommand count : subCommands) {
                        if (args[0].equalsIgnoreCase(count.getName())) {
                            count.perform(player, args);
                        }
                    }
                } else {
                    player.sendMessage(" ");
                    player.sendMessage(CC.color("    &d&m--------&bHelp List&d&m--------"));
                    player.sendMessage(" ");
                    for (int i = 0; i < getSubCommands().size(); i++) {
                        player.sendMessage(CC.color(" &e" + getSubCommands().get(i).getSyntax() + " &b" + getSubCommands().get(i).getDescription()));
                    }
                    player.sendMessage(" ");
                    player.sendMessage(CC.color(" &dAuthor: &bCloudOcean"));
                    player.sendMessage(" ");
                    player.sendMessage(CC.color("    &d&m--------------------&f"));
                    player.sendMessage(" ");
                    return true;
                }
                return true;
            } else {
                player.sendMessage(CC.color("&d&lTWST-Core &f&oBy CloudOcean"));
                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        if (args.length == 1) {
            List<String> tabList = new ArrayList<>();
            if (sender.hasPermission(plugin.config.getString("Permission.SubCommand-View"))) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    tabList.add(getSubCommands().get(i).getName());
                }
                return tabList;
            } else if (args.length == 2){
                for (int i = 0; i < getSubCommands().size(); i++){
                    if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                        return getSubCommands().get(i).getSubcommandArguments(sender, args);
                    }
                }
            }
        }
        return null;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }
}