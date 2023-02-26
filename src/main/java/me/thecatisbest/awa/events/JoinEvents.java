package me.thecatisbest.awa.events;

import com.cryptomorin.xseries.XSound;
import dev.dejvokep.boostedyaml.block.implementation.Section;
import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.utilis.CC;
import me.thecatisbest.awa.utilis.Utilis;
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
        Section mdSection = plugin.config.getSection("Groups");
        if (!plugin.module.getBoolean("Enable-Groups")) return;
        if (mdSection == null) return;

        for (String lore : mdSection.getRoutesAsStrings(false)) {

            Section idSection = mdSection.getSection(lore);
            String permission = idSection.getString("Permission");

            if (permission != null && player.hasPermission(permission)) {
                setCustomJoinMessage(e, player, idSection);
                setCustomJoinSounds(player, idSection);
                setCustomJoinCommands(player, idSection);
            }
        }
    }

    private void setCustomJoinMessage(PlayerJoinEvent e, Player player, Section idSection) {
        String joinMessage = Utilis.addPlaceholdersMessage(player,
                idSection.getString("Join-Message"));
        e.setJoinMessage(CC.color(joinMessage)
                .replace("%player%", player.getName()
                        .replace("%player_displayName%", player.getDisplayName())));
    }
    private void setCustomJoinSounds(Player player,Section idSection) {
            try {
                if (!idSection.getString("Sound").equalsIgnoreCase("")) {
                    XSound.play(player, idSection.getString("Sound") + ", 1, 1");
                }
            } catch (IllegalArgumentException e1) {
                Utilis.logMessage(this.getClass(), "The sound " + idSection.getString("Sound") + " doesn't exist in your server version!");
            }
        }
    private void setCustomJoinCommands(Player player,Section idSection) {
        for (String lore1 : idSection.getStringList("Commands")) {
            Utilis.configCommand(lore1, player);
        }
    }
}
