package io.github.derexxd.squidImprovements;

import io.github.derexxd.squidImprovements.listeners.SquidDamageListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SquidImprovements extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SquidDamageListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
