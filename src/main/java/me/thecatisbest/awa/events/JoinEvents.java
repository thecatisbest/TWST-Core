package me.thecatisbest.awa.events;

import me.thecatisbest.awa.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinEvents implements Listener {

    private final Main plugin;

    public JoinEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player.hasPlayedBefore()) {
            e.setJoinMessage(plugin.color(plugin.config.getString("Join-Leave.Join-message").replaceAll("%player%", player.getName()).replaceAll("%player_DisplayName%", player.getDisplayName())));
        } else {
            e.setJoinMessage(plugin.color(plugin.config.getString("Join-Leave.First-Join").replaceAll("%player%", player.getName()).replaceAll("%player_DisplayName%", player.getDisplayName())));
        }
    }
}