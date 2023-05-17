package com.diabloiiiros.ping_tablist;

import org.bukkit.plugin.java.JavaPlugin;

public final class Ping_tablist extends JavaPlugin {
    @Override
    public void onEnable() {
        new tablist(this).runTaskTimerAsynchronously(this, this.getConfig().getInt("tablist-ping-update-delay") * 20L, this.getConfig().getInt("tablist-ping-update-delay") * 20L);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
