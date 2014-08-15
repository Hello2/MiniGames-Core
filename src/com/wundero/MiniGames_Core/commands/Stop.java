package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Stop extends SubCommand {

	private String name = "stop";
	private String info = "Stop an arena without waiting for users";
	private String permission = "minigames-core.use.stop";
	private String[] aliases = {"end"};

	@Override
	public void onCommand(Player p, String[] args) {
		//TODO add confirmation
		Arena ar = null;
		for(Arena a : ArenaManager.getArenaManager().getArenas())
		{
			if(a.getID().equalsIgnoreCase(args[0])) { ar = a; break; }
		}
		if(ar==null)
		{
			if(Select.getSelectedArena(p)!=null) ar = ArenaManager.getArenaManager().getArena(Select.getSelectedArena(p));
			else
			{
				ChatUtils.sendMessage("That arena does not exist!", MessageLevel.WARNING, p);
				return;
			}
		}
		
		//Add confirmation here
		ar.endArena(true);
		ChatUtils.sendMessage("Arena "+ar.getID()+" successfully stopped.", MessageLevel.INFO, p);
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
