package com.spoontacular.creativecontrol;

import java.util.List;

import org.bukkit.entity.*;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.pneumaticraft.commandhandler.multiverse.Command;

public class CreativeControl extends JavaPlugin {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    if(cmd.getCommandName().equalsIgnoreCase("cr")){
	        Player s = (Player)sender;
	        List<Player> players = s.getWorld().getPlayers();
	        Player target = players.get(players.indexOf(args[0])); // Gets the player who was typed in the command. 
	        // For instance, if the command was "/ignite notch", then the player would be just "notch". 
	        // Note: The first argument starts with [0], not [1]. So arg[0] will get the player typed. 
	        target.setFireTicks(10000);
	        return true;
	    }
	    return false;
	}
}
