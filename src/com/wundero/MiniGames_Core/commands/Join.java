package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Join extends SubCommand {

	private String name = "join";
	private String info = "Join an arena";
	private String[] aliases = {"j"};
	private String permission = "minigames-core.use.join";
	
	@Override
	public void onCommand(Player p, String[] args) {
		if(ArenaManager.getArenaManager().isInGame(p)) {
			ChatUtils.sendMessage(p, "You are already in a game!", MessageLevel.WARNING);
			return;
		}
		Arena ar = null;
		for(Arena a : ArenaManager.getArenaManager().getArenas())
		{
			if(a.getID().equalsIgnoreCase(args[0])) { ar = a; break; }
		}
		
		if(ar==null) {
			ChatUtils.sendMessage(p, "That is not a valid arena!", MessageLevel.ERROR);
			return;
		}
		
		ArenaManager.getArenaManager().addPlayer(p, ar.getID());
		
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

	
	public String permission() {
		
		return permission;
	}
	
}
