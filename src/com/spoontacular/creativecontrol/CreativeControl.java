package com.spoontacular.creativecontrol;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVPlugin;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

public class CreativeControl extends JavaPlugin implements MVPlugin {
	
    public static final Logger log = Logger.getLogger("Minecraft");
    public static final String logPrefix = "[Multiverse-CreativeControl] ";

    protected MVCCPluginListener pluginListener;

    protected MultiverseCore core;
    
	private PluginManager pm;
	
	@Override
    //When the plugin is enabled this method is called.
    public void onEnable() {
		this.core = (MultiverseCore) getServer().getPluginManager().getPlugin("Multiverse-Core");
        // Test if the Core was found, if not we'll disable this plugin.
        if (this.core == null) {
            log.info(logPrefix + "Multiverse-Core not found, will keep looking.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
        this.core.incrementPluginCount();

		//Create the pluginmanager pm.
		pm = getServer().getPluginManager();
		//Get the information from the yml file.
		PluginDescriptionFile pdfFile = this.getDescription();
		
		this.pluginListener = new MVCCPluginListener(this);
		
		pm.registerEvents(this.pluginListener, this);
        
    	System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );    
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    if(cmd.getName().equalsIgnoreCase("cr")){
	        Player s = (Player)sender;
	        MultiverseWorld world = core.getMVWorldManager().getMVWorld(s.getLocation().getWorld().getName());
	        if( world == null ) {
	        	s.sendMessage("You must be in a multiworld creative world to use this command");
	        	return true;
	        }
	        if( world.getGameMode() == GameMode.CREATIVE) {
		        GameMode mode = s.getGameMode();
		        switch (mode) {
		        case SURVIVAL:
		        	s.setGameMode(GameMode.CREATIVE);
		        	break;
		        case CREATIVE:
		        	s.setGameMode(GameMode.SURVIVAL);
		        	break;
		        }
	        } else {
	        	s.sendMessage("Can not change mode in a survival world!");
	        }
	    }
	    return true;
	}

	@Override
	public void log(Level level, String msg) {
		log.log(level, logPrefix + " " + msg);
	}

	@Override
    @Deprecated
	public String dumpVersionInfo(String buffer) {
		buffer += this.getVersionInfo();
        return buffer;
    }

	@Override
	public MultiverseCore getCore() {
        return this.core;
	}

	@Override
	public void setCore(MultiverseCore core) {
        this.core = core;
	}
	
	public String getVersionInfo() {
        return new StringBuffer("[Multiverse-CreativeControl] Multiverse-CreativeControl Version: ").append(this.getDescription().getVersion()).append('\n').toString();
    }
	
	@Override
	public int getProtocolVersion() {
		return 1;
	}
}
