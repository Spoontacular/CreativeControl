package com.spoontacular.creativecontrol;

import java.awt.Event;
import java.util.List;

import org.bukkit.entity.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class CreativeControl extends JavaPlugin {
	
	@Override
    //When the plugin is enabled this method is called.
    public void onEnable() {
		//Create the pluginmanage pm.
		PluginManager pm = getServer().getPluginManager();
		//Get the infomation from the yml file.
		PluginDescriptionFile pdfFile = this.getDescription();
		//Print that the plugin has been enabled!
		System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
       
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    if(cmd.getName().equalsIgnoreCase("cr")){
	        Player s = (Player)sender;  
	        s.setFireTicks(10000);
	        return true;
	    }
	    return false;
	}
}
