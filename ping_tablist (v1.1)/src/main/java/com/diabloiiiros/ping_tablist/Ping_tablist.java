package com.diabloiiiros.ping_tablist;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class Ping_tablist extends JavaPlugin {
    public Logger console = Bukkit.getLogger();
    @Override
    public void onEnable() {
        getCommand("pingtablistreload").setExecutor(new reloadCommand(this));
        new pingOnTabListRunTask(this).runTaskTimerAsynchronously(this, this.getConfig().getInt("tablist-ping-update-delay") * 20L, this.getConfig().getInt("tablist-ping-update-delay") * 20L);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        console.info("Ping tab list has been fully loaded!");
    }
}
