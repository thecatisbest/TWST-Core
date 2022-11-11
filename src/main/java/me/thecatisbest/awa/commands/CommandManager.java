package me.thecatisbest.awa.commands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommands.Reload;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();
    public final Main plugin;

    public CommandManager(Main plugin){
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


    public ArrayList<SubCommand> getSubCommands (){
        return subCommands;
    }
}