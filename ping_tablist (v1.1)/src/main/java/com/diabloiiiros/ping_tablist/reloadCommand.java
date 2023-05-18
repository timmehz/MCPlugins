package com.diabloiiiros.ping_tablist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class reloadCommand implements CommandExecutor {
    private final Ping_tablist pl;
    public reloadCommand(Ping_tablist pl) { this.pl = pl; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("pingtablist.reload")) {
                pl.reloadConfig();
                pl.saveDefaultConfig();
                sender.sendMessage(ChatColor.GREEN + "config.yml have been reloaded");
            } else if (!sender.hasPermission("pingtablist.reload")) {
                if (pl.getConfig().getString("no-permission", "You do not have permission to use this command.").contains("&")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("no-permission", "You do not have permission to use this command.")));
                } else {
                    sender.sendMessage(pl.getConfig().getString("no-permission", "You do not have permission to use this command."));
                }
            }
        } else if (sender instanceof ConsoleCommandSender) {
            pl.reloadConfig();
            pl.saveDefaultConfig();
            pl.console.warning("config.yml have been reloaded");
        }
        return true;
    }
}
