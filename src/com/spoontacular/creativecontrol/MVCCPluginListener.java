package com.spoontacular.creativecontrol;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

import com.onarandombox.MultiverseCore.MultiverseCore;

public class MVCCPluginListener implements Listener {
	private CreativeControl plugin;

    public MVCCPluginListener(CreativeControl plugin) {
        this.plugin = plugin;
    }

    /**
     * This method is fired when any plugin enables.
     * @param event The PluginEnable event.
     */
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        if (event.getPlugin().getDescription().getName().equals("Multiverse-Core")) {
            this.plugin.setCore(((MultiverseCore) plugin.getServer().getPluginManager().getPlugin("Multiverse-Core")));
            this.plugin.getServer().getPluginManager().enablePlugin(this.plugin);
        }
    }
    
}
