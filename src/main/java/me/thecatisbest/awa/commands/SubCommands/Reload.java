package me.thecatisbest.awa.commands.SubCommands;

import me.thecatisbest.awa.Main;
import me.thecatisbest.awa.commands.SubCommand;
import me.thecatisbest.awa.utilis.CC;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.util.List;

public class Reload extends SubCommand {

    private final Main plugin;

    public Reload(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return plugin.message.getString("SubCommand-Reload-Description");
    }

    @Override
    public String getSyntax() {
        return "/twstcore reload";
    }

    @Override
    public int maxArguments(){
        return 1;
    }

    @Override
    public boolean canConsoleExecute(){
        return true;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (sender.hasPermission(plugin.config.getString("Permission.Reload-Command"))) {
            sender.sendMessage(CC.color(plugin.message.getString("Starting-Reload")));
            sender.sendMessage(CC.color(plugin.message.getString("Success-Reload")));
            try {
                plugin.config.reload();
                plugin.message.reload();
                plugin.module.reload();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            sender.sendMessage(CC.color(
                    plugin.message.getString("No-Permission")
                            .replaceAll(("%permission%"), plugin.config.getString("Permission.Reload-Command"))));
        }
    }
    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return null;
    }
}

