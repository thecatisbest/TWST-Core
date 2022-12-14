package me.thecatisbest.awa.events;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvents implements Listener {

    private final Main plugin;

    public LeaveEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        e.setQuitMessage(CC.color(plugin.config.getString("Join-Leave.Leave-message")
                .replaceAll("%player%", player.getName())
                .replaceAll("%player_DisplayName%", player.getDisplayName())));
    }
}