package me.thecatisbest.awa.events;

import me.thecatisbest.awa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class JoinFireworkEvents implements Listener {

    private final Main plugin;
    public JoinFireworkEvents(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Location location = player.getLocation();

        if (player.hasPermission(plugin.config.getString("Permission.Join-Firework")))
            for (int i = 1; i < plugin.config.getInt("Join-firework.amount"); i++) {
                ArrayList<Color> colors = new ArrayList<>();
                ArrayList<Color> fade = new ArrayList<>();
                List<String> lore = plugin.config.getStringList("Join-firework.colors");
                List<String> lore2 = plugin.config.getStringList("Join-firework.fade");
                for (String l : lore)
                    colors.add(getColor(l));
                for (String l : lore2)
                    fade.add(getColor(l));
                final Firework fw = (Firework) player.getWorld().spawn(
                        location.add(0.5D, plugin.config.getInt("Join-firework.height"), 0.5D),
                        Firework.class);
                FireworkMeta fm = fw.getFireworkMeta();
                fm.addEffect(FireworkEffect.builder().flicker(plugin.config.getBoolean("Join-firework.flicker"))
                        .trail(plugin.config.getBoolean("Join-firework.trail"))
                        .with(FireworkEffect.Type.valueOf(plugin.config.getString("Join-firework.type"))).withColor(colors).withFade(fade)
                        .build());
                if (!plugin.config.getBoolean("Join-firework.instant-explode"))
                    fm.setPower(plugin.config.getInt("Join-firework.power"));
                fw.setFireworkMeta(fm);
                if (plugin.config.getBoolean("Join-firework.instant-explode"))
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                        public void run() {
                            fw.detonate();
                        }
                    }, 1L);
            }
    }
    public Color getColor(String paramString) {
        String temp = paramString;
        if (temp.equalsIgnoreCase("AQUA"))
            return Color.AQUA;
        if (temp.equalsIgnoreCase("BLACK"))
            return Color.BLACK;
        if (temp.equalsIgnoreCase("BLUE"))
            return Color.BLUE;
        if (temp.equalsIgnoreCase("FUCHSIA"))
            return Color.FUCHSIA;
        if (temp.equalsIgnoreCase("GRAY"))
            return Color.GRAY;
        if (temp.equalsIgnoreCase("GREEN"))
            return Color.GREEN;
        if (temp.equalsIgnoreCase("LIME"))
            return Color.LIME;
        if (temp.equalsIgnoreCase("MAROON"))
            return Color.MAROON;
        if (temp.equalsIgnoreCase("NAVY"))
            return Color.NAVY;
        if (temp.equalsIgnoreCase("OLIVE"))
            return Color.OLIVE;
        if (temp.equalsIgnoreCase("ORANGE"))
            return Color.ORANGE;
        if (temp.equalsIgnoreCase("PURPLE"))
            return Color.PURPLE;
        if (temp.equalsIgnoreCase("RED"))
            return Color.RED;
        if (temp.equalsIgnoreCase("SILVER"))
            return Color.SILVER;
        if (temp.equalsIgnoreCase("TEAL"))
            return Color.TEAL;
        if (temp.equalsIgnoreCase("WHITE"))
            return Color.WHITE;
        if (temp.equalsIgnoreCase("YELLOW"))
            return Color.YELLOW;
        return null;
    }
}

