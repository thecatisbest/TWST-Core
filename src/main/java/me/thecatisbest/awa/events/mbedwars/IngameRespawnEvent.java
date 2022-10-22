package me.thecatisbest.awa.events.mbedwars;

import de.marcely.bedwars.api.BedwarsAPI;
import de.marcely.bedwars.api.event.player.PlayerIngameRespawnEvent;
import de.marcely.bedwars.api.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class IngameRespawnEvent implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerIngameRespawnEvent e) {
        Player player = e.getPlayer();
        BedwarsAPI.getNMSHelper().showTitle(player,
                Message.build("&a已重生！").done(),
                Message.build("").done(),
                30, 5, 10);
    }
}
