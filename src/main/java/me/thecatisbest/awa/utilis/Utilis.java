package me.thecatisbest.awa.utilis;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilis {

    /**
     * Executable by players or console.
     * [player] | [console]
     *
     * @param command The command line that will be executed.
     * @param player  The player that the command will be executed from.
     *                Supports placeholders. %player% - player's name.
     */
    public static void configCommand(String command, Player player) {
        if (command.startsWith("[player]")) {
            Bukkit.dispatchCommand(player, command.replace("[player] ", "").replace("%player%", player.getName()));
        } else if (command.startsWith("[console]")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("[console] ", "").replace("%player%", player.getName()));

        }
    }

    /**
     * Send a message to all online players.
     *
     * @param s The message to send to all players.
     *          Doesn't support placeholders atm.
     */
    public static void sendMessageToAllPlayers(String s) {
        for (Player allPlayers : Bukkit.getOnlinePlayers()) {
            allPlayers.sendMessage(CC.color(s));
        }
    }

    /**
     * Send a placeholder on message.
     *
     * @param player The player that the command will be executed from.
     * @param s The message to send to players
     *
     */
    public static String addPlaceholdersMessage(Player player, String s) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            s = PlaceholderAPI.setPlaceholders(player, s);
        }
        return CC.color(s);
    }
    /**
     * Log message, send to console.
     *
     * @param className the class that is using this log message.
     * @param message   The message the OP wants to send.
     */
    public static String logMessage(Class<?> className, String message) {

        Logger logger = Logger.getLogger(className.getName());
        logger.setLevel(Level.WARNING);
        logger.warning(message);

        return message;
    }
}
