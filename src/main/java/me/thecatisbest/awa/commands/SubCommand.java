package me.thecatisbest.awa.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class SubCommand {

    private ArrayList<String> aliases;

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public void addAlias(String a) {
        String[] aliases = a.split("\\|");
        this.aliases.addAll(Arrays.asList(aliases));
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

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
}