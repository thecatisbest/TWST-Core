package me.thecatisbest.awa.utilis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CC {

    /**
     * @param msg The message to format
     * @return The formatted message
     */
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


    /**
     * @param string The server motd to format
     * @return The formatted message
     */
    public static String serverMOTDColorFormat(String string) {
        string = string.replace("&0", "\u00a70");
        string = string.replace("&1", "\u00a71");
        string = string.replace("&2", "\u00a72");
        string = string.replace("&3", "\u00a73");
        string = string.replace("&4", "\u00a74");
        string = string.replace("&5", "\u00a75");
        string = string.replace("&6", "\u00a76");
        string = string.replace("&7", "\u00a77");
        string = string.replace("&8", "\u00a78");
        string = string.replace("&9", "\u00a79");
        string = string.replace("&a", "\u00a7a");
        string = string.replace("&b", "\u00a7b");
        string = string.replace("&c", "\u00a7c");
        string = string.replace("&d", "\u00a7d");
        string = string.replace("&e", "\u00a7e");
        string = string.replace("&f", "\u00a7f");
        string = string.replace("&k", "\u00a7k");
        string = string.replace("&l", "\u00a7l");
        string = string.replace("&m", "\u00a7m");
        string = string.replace("&n", "\u00a7n");
        string = string.replace("&o", "\u00a7o");
        string = string.replace("&r", "\u00a7r");
        return string;
    }
}
