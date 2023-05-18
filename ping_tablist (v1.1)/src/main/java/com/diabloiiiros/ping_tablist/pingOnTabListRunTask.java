package com.diabloiiiros.ping_tablist;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class pingOnTabListRunTask extends BukkitRunnable {
    private final Ping_tablist pl;
    public pingOnTabListRunTask(Ping_tablist pl) { this.pl = pl; }
    @Override
    public void run() {
        String bracketsLeft = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("ping-brackets-left", "["));
        String bracketsRight = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("ping-brackets-right", "]"));
        for (Player players : pl.getServer().getOnlinePlayers()) {
            String name = pl.getConfig().getBoolean("show-display-name") ? players.getDisplayName() : players.getName();
            ChatColor color;
            if (players.getPing() <= pl.getConfig().getInt("green-ping-max")) {
                color = ChatColor.GREEN;
            } else if (players.getPing() <= pl.getConfig().getInt("yellow-ping-max")) {
                color = ChatColor.YELLOW;
            } else {
                color = ChatColor.RED;
            }
            String formattedPing = color + Integer.toString(players.getPing());
            if (pl.getConfig().getString("ping-position", "behind").equals("behind")) {
                if (pl.getConfig().getBoolean("show-ping-brackets")) {
                    players.setPlayerListName(name + ChatColor.RESET + " " + bracketsLeft + ChatColor.RESET + formattedPing + ChatColor.RESET + bracketsRight + ChatColor.RESET);
                } else if (!pl.getConfig().getBoolean("show-ping-brackets")) {
                    players.setPlayerListName(name + ChatColor.RESET + " " + formattedPing + ChatColor.RESET);
                }
            } else if (pl.getConfig().getString("ping-position", "behind").equals("front")) {
                if (pl.getConfig().getBoolean("show-ping-brackets")) {
                    players.setPlayerListName(bracketsLeft + ChatColor.RESET + formattedPing + ChatColor.RESET + bracketsRight + ChatColor.RESET + " " + name + ChatColor.RESET);
                } else if (!pl.getConfig().getBoolean("show-ping-brackets")) {
                    players.setPlayerListName(formattedPing + ChatColor.RESET + " " + name + ChatColor.RESET);
                }
            } else {
                throw new RuntimeException("Invalid value for ping-position in config.yml");
            }
        }
    }
}
