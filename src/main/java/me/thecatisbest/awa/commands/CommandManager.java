package me.thecatisbest.awa.commands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommands.*;
import me.thecatisbest.awa.utilis.CC;
import me.thecatisbest.awa.utilis.Utilis;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("All")
public class CommandManager implements CommandExecutor, TabCompleter {

    private final List<SubCommand> subCommands = new ArrayList<>();
    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
        registerCommands();

        subCommands.add(new ServerMOTD(plugin));
        subCommands.add(new Reload(plugin));
        subCommands.add(new Info(plugin));
        subCommands.add(new Maintenance(plugin));
        subCommands.add(new Helpful(plugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender.hasPermission(plugin.permission.getString("Help-Command"))) {
            if (args.length > 0) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    SubCommand subCommand = getSubCommands().get(i);

                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        if (!subCommand.canConsoleExecute() && sender instanceof ConsoleCommandSender) {
                            Utilis.consoleMessage(CC.color(plugin.message.getString("Console-Cannot-Execute")));
                            return true;
                        }
                    }
                }
                for (SubCommand count : subCommands) {
                    if (args[0].equalsIgnoreCase(count.getName())) {
                        count.perform(sender, args);
                    }
                }
            } else {
                sender.sendMessage(" ");
                sender.sendMessage(CC.color(" &bTWST-Core &7- &bHelp Menu"));
                sender.sendMessage(" ");
                for (int i = 0; i < getSubCommands().size(); i++) {
                    sender.sendMessage(CC.color(" &b" + getSubCommands().get(i).getSyntax()
                            + " &f" + getSubCommands().get(i).getName()
                            + " &b- &d" + getSubCommands().get(i).getDescription()));
                }
                sender.sendMessage(" ");
                sender.sendMessage(CC.color(" &dAuthor: &b" + plugin.getDescription().getAuthors()));
                sender.sendMessage(" ");
                return true;
            }
            return true;
        } else {
            sender.sendMessage(CC.color("&bTWST-Core &fBy &d" + plugin.getDescription().getAuthors()));
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            List<String> tabList = new ArrayList<>();
            if (sender.hasPermission(plugin.permission.getString("SubCommand-View"))) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    tabList.add(getSubCommands().get(i).getName());
                }
                return tabList;
            }
        } else if (args.length == 2) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    return getSubCommands().get(i).getSubcommandArguments(sender, args);
                }
            }
        }
        return null;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public void registerCommands() {
        for (SubCommand subCommand : subCommands) {
            for (String l : subCommand.getAliases()) {
                PluginCommand command = plugin.getCommand(l);
                if(command == null) {
                    throw new IllegalStateException("Command not found! Is it in plugin.yml?");
                }
                command.setExecutor(this);
                command.setTabCompleter(this);
            }
        }
    }
}