package me.thecatisbest.awa.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {


    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract int maxArguments();

    public abstract boolean canConsoleExecute();

    /**
     * Execute and perform commands with this method.
     *
     // @param player The player object.
     * @param sender The sender object.
     * @param args   The command's arguments. (Starts from 1)
     */
    // public abstract void perform(Player player, String[] args);
    public abstract void perform(CommandSender sender, String[] args);

    public abstract List<String> getSubcommandArguments(CommandSender sender, String[] args);

}