package me.thecatisbest.awa.listeners.motd;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.utilis.CC;
import me.thecatisbest.awa.utilis.Utilis;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class MaintenanceEvents implements Listener {

    private final Main plugin;

    public MaintenanceEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMaintenance(ServerListPingEvent e) {
        if (plugin.module.getBoolean("Enable-MaintenanceMOTD")) {

            String line1 = CC.serverMOTDColorFormat(plugin.config.getString("Maintenance-MOTD.Line-1"));
            String line2 = CC.serverMOTDColorFormat(plugin.config.getString("Maintenance-MOTD.Line-2"));

            e.setMotd(Utilis.addPlaceholdersMessage(null, line1 + "\n" + line2));
        }
    }
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        if (plugin.module.getBoolean("Enable-MaintenanceMOTD")) {
            if (!player.hasPermission(plugin.permission.getString("Maintenance-Bypass"))) {
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                e.setKickMessage(CC.color(plugin.message.getString("Maintenance-Kick")));
                player.kickPlayer(CC.color(plugin.message.getString("Maintenance-Kick")));
            }
        }
    }
}
