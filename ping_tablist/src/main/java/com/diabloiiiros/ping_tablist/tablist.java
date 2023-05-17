package com.diabloiiiros.ping_tablist;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class tablist extends BukkitRunnable {
    private final Ping_tablist pl;
    public tablist(Ping_tablist pl) {
        this.pl = pl;
    }
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
            String formattedPing = color + Integer.toString(players.getPing()) + ChatColor.RESET;
            if (pl.getConfig().getString("ping-position", "behind").equals("behind")) {
                players.setPlayerListName(name + " " + (pl.getConfig().getBoolean("show-ping-brackets") ? bracketsLeft + formattedPing + bracketsRight : formattedPing));
            } else if (pl.getConfig().getString("ping-position", "behind").equals("front")) {
                players.setPlayerListName((pl.getConfig().getBoolean("show-ping-brackets") ? bracketsLeft + formattedPing + bracketsRight + " " : "") + name);
            } else {
                throw new RuntimeException("Invalid value for ping-position in config.yml");
            }
        }
    }
}
