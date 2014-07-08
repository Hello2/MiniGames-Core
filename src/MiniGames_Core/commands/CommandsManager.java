package com.wundero.MiniGames_Core.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.MessageLevel;
import com.wundero.MiniGames_Core.Utils.ChatUtils;

public class CommandsManager implements CommandExecutor {

	private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
	
	private CommandsManager() {}
	
	private static CommandsManager cm;
	
	public static CommandsManager getCommandsManager()
	{
		if(cm==null)
		{
			cm = new CommandsManager();
		}
		return cm;
	}
	
	public void addCommand(SubCommand cmd)
	{
		commands.add(cmd);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { ChatUtils.sendMessage(sender, "Only a player can use MiniGames-Core!", MessageLevel.SEVERE); return true;}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("minigames")||cmd.getName().equalsIgnoreCase("mg")||cmd.getName().equalsIgnoreCase("minigame"))
		{
			if(args.length==0)
			{
				for(SubCommand c : commands)
				{
					if(p.hasPermission(c.permission()))
					{
						ChatUtils.sendMessage(p, "/"+cmd.getName()+" <"+aliases(c)+"> - "+c.info(), MessageLevel.INFO); //TODO only show cmds user has perm for
					}
					return true;
				}
			}
			
			SubCommand target = get(args[0]);
			
			
			if(target==null) {
				ChatUtils.sendMessage(p, "/"+cmd.getName()+" "+args[0]+" is not a valid command!", MessageLevel.WARNING);
				return true;
			}
			if(p.hasPermission(target.permission())) {
				ArrayList<String> a = new ArrayList<String>();
				a.addAll(Arrays.asList(args));
				a.remove(0);
				args = a.toArray(new String[a.size()]);
				
				try
				{
					target.onCommand(p, args);
				}
				catch(Exception e)
				{
					ChatUtils.sendMessage(p, e.getMessage(), MessageLevel.ERROR);
					e.printStackTrace();
					return true;
				}
			}
		}		
		return true;
	}
	
	public void setup()
	{
		//TODO add commands to command list
	}
	
	private SubCommand get(String name)
	{
		for(SubCommand c : commands)
		{
			if(c.name().equalsIgnoreCase(name)) return c;
			for(String a : c.aliases())
			{
				if(a.equalsIgnoreCase(name)) return c;
			}
		}
		return null;
	}
	
	private String aliases(SubCommand c)
	{
		
		String fin = "";
		for(String a : c.aliases())
		{
			fin+=a+" | ";
		}
		
		return fin.substring(0, fin.lastIndexOf(" | "));
	}
	
	
	
}
