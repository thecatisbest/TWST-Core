package me.thecatisbest.awa.listeners;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.utilis.CC;
import me.thecatisbest.awa.utilis.Utilis;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinMotdEvents implements Listener {

    private final Main plugin;

    public JoinMotdEvents(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        List<String> lore = plugin.config.getStringList("Join-MOTD");
        for(String l : lore)
            player.sendMessage(CC.color(Utilis.addPlaceholdersMessage(player,l
                    .replace("%player%", player.getName())
                    .replace("%player_displayName%", player.getDisplayName()))));
    }
}
