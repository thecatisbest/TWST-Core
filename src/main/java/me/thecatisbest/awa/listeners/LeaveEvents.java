package me.thecatisbest.awa.listeners;

import dev.dejvokep.boostedyaml.block.implementation.Section;
import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.utilis.CC;
import me.thecatisbest.awa.utilis.Utilis;
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
    public void onQuit (PlayerQuitEvent e){

        Player player = e.getPlayer();
        Section mdSection = plugin.config.getSection("Groups");
        if (!plugin.module.getBoolean("Enable-Groups")) return;
        if (mdSection == null) return;

        for (String lore: mdSection.getRoutesAsStrings(false)) {

            Section idSection = mdSection.getSection(lore);
            String permission = idSection.getString("Permission");

            if (permission != null && player.hasPermission(permission)){
                setCustomLeaveMessage(e, player, idSection);
            }
        }
    }

    private void setCustomLeaveMessage(PlayerQuitEvent e, Player player, Section idSection) {
        String leaveMessage = Utilis.addPlaceholdersMessage(player,
                idSection.getString("Leave-Message"));
        e.setQuitMessage(CC.color(leaveMessage)
                        .replace("%player%", player.getName()
                        .replace("%player_displayName%", player.getDisplayName())));
    }
}
