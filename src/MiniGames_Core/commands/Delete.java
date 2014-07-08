package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.MessageLevel;
import com.wundero.MiniGames_Core.Arena.Arena;
import com.wundero.MiniGames_Core.Arena.ArenaManager;
import com.wundero.MiniGames_Core.Utils.ChatUtils;

public class Delete extends SubCommand {
	
	private String name = "delete";
	private String info = "Create an arena.";
	private String permission = "minigames-core.admin.delete";
	private String[] aliases = {"d"};
	
	@Override
	public void onCommand(Player p, String[] args) {
		
		if(args.length==0)
		{
			ChatUtils.sendMessage(p, "You must specify an arena!", MessageLevel.WARNING);
			return;
		}
		
		Arena a = null;
		for(Arena ar : ArenaManager.getArenaManager().getArenas())
		{
			if(ar.getID().equalsIgnoreCase(args[0])) { a = ar; break; }
		}
		
		if(a==null)
		{
			ChatUtils.sendMessage(p, "There is no arena with id \""+args[0]+"\"", MessageLevel.WARNING);
			return;
		}
		
		if(a.isInProgress())
		{
			ChatUtils.sendMessage(p, "You cannot delete an arena that is in progress!", MessageLevel.WARNING);
			return;
		}
		
		
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String info() {
		return info;
	}

	@Override
	public String[] aliases() {
		return aliases;
	}

	@Override
	public String permission() {
		return permission;
	}
	
}
