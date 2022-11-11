package me.thecatisbest.awa.commands;

import me.thecatisbest.awa.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    private final List<String> tabList = new ArrayList<>();
    private final CommandManager commandManager;
    private final Main plugin;
    public TabComplete(Main plugin, CommandManager commandManager){
        this.plugin = plugin;
        this.commandManager = commandManager;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        if (args.length == 1){
            if (sender.hasPermission(plugin.config.getString("twstcore.subcommand.view"))){
                for (int i = 0; i < commandManager.getSubCommands().size(); i++){
                    tabList.add(commandManager.getSubCommands().get(i).getName());
                }
                return tabList;
            }
        }
        return null;
    }


}