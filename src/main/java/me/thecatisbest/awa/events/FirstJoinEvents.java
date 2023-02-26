package me.thecatisbest.awa.events;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.utilis.CC;
import me.thecatisbest.awa.utilis.Utilis;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinEvents implements Listener {

    private final Main plugin;

    public FirstJoinEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFirstJoin (PlayerJoinEvent e){
        Player player = e.getPlayer();

        if (!player.hasPlayedBefore()){
            String leaveMessage = Utilis.addPlaceholdersMessage(player,
                    plugin.config.getString("First-Join"));
            e.setJoinMessage(CC.color(leaveMessage)
                    .replaceAll("%player%", player.getName())
                    .replaceAll("%player_DisplayName%", player.getDisplayName()));
        }
    }
}
