package me.thecatisbest.awa.listeners.motd;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.utilis.CC;
import me.thecatisbest.awa.utilis.Utilis;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingEvents implements Listener {

    private final Main plugin;

    public ServerListPingEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        if (plugin.module.getBoolean("Enable-ServerMOTD")) {

            String line1 = CC.serverMOTDColorFormat(plugin.config.getString("Server-MOTD.Line-1"));
            String line2 = CC.serverMOTDColorFormat(plugin.config.getString("Server-MOTD.Line-2"));

            e.setMotd(Utilis.addPlaceholdersMessage(null, line1 + "\n" + line2));
        }
        if (plugin.module.getBoolean("Enable-ServerMaxPlayers")) {
            e.setMaxPlayers(plugin.config.getInt("Server-Max-Players"));
        }
    }
}
