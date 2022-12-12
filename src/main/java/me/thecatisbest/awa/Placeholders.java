package me.thecatisbest.awa;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {

    private final Main plugin;

    public Placeholders(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "twstcore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "CloudOcean";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if(params.equalsIgnoreCase("version-own")){
            return plugin.message.getString("version_own");
        }
        return null;
    }
}
