package com.wundero.MiniGames_Core.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

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
		if(!(commands.contains(cmd)))
			commands.add(cmd);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { ChatUtils.sendMessageToCmdSender(sender, "Only a player can use MiniGames-Core!", MessageLevel.SEVERE); return true;}
		
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase("minigames")||label.equalsIgnoreCase("mg")||label.equalsIgnoreCase("minigame"))//TODO make sure this works
		{
			if(args.length==0)
			{
				ChatUtils.sendMessage("Commands:", MessageLevel.INFO, p);
				for(SubCommand c : commands)
				{
					if(p.hasPermission(c.permission()))
					{
						ChatUtils.sendMessage("/"+label+" <"+aliases(c)+"> - "+c.info(), MessageLevel.INFO, p);
					}
					
				}
				return true;
			}
			
			SubCommand target = get(args[0]);
			
			
			if(target==null) {
				ChatUtils.sendMessage("/"+label+" "+args[0]+" is not a valid command!", MessageLevel.WARNING, p);
				return true;
			}
			if(p.hasPermission(target.permission())) {
				
				//Removes argument 1 (the subcommand)
				
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
					ChatUtils.sendMessage(e.getMessage(), MessageLevel.ERROR, p);
					e.printStackTrace();
					return true;
				}
			}
		}		
		return true;
	}
	
	public void disable()
	{
		for(SubCommand cmd : commands)
		{
			cmd.name();
			cmd = null;
		}
		
		commands = null;
	}
	
	public void setup()
	{
		commands.add(new Create());
		commands.add(new Delete());
		commands.add(new Edit());
		commands.add(new Highlight());
		commands.add(new Info());
		commands.add(new Join());
		commands.add(new Leave());
		commands.add(new Players());
		commands.add(new Ready());
		commands.add(new Reload());
		commands.add(new Select());
		commands.add(new Spectate());
		commands.add(new Start());
		commands.add(new Stop());
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
