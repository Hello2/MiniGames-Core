package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Ready extends SubCommand {
	private String name = "ready";
	private String info = "Ready up for a game";
	private String permission = "minigames-core.use.ready";
	private String[] aliases = {"r"};
	
	@Override
	public void onCommand(Player p, String[] args) {		
		if(ArenaManager.getArenaManager().isSpectator(p)){
			ChatUtils.sendMessage("You are spectating the game, you cannot ready up!", MessageLevel.WARNING, p);
			return;

		}else if(ArenaManager.getArenaManager().isPlayer(p)){
			ArenaManager.getArenaManager().getArena(p).setReady(p, !ArenaManager.getArenaManager().getArena(p).isReady(p));
			return;
		}

		else{
			ChatUtils.sendMessage("You must be in an arena to be ready!", MessageLevel.WARNING);
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
