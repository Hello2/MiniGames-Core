package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Leave extends SubCommand {

	private String name = "leave";
	private String info = "Leave an arena";
	private String[] aliases = {"l"};
	private String permission = "minigames-core.use.leave";
	
	@Override
	public void onCommand(Player p, String[] args) {
		if(!(ArenaManager.getArenaManager().isInGame(p)))
		{
			ChatUtils.sendMessage(p, "You are not in a game!", MessageLevel.WARNING);
			return;
		}
		
		ArenaManager.getArenaManager().removePlayer(p);
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
