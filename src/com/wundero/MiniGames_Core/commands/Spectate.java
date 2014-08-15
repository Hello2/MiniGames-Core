package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Spectate extends SubCommand {
	
	private String name = "spectate";
	private String info = "Spectate an arena";
	private String permission = "minigames-core.use.spectate";
	private String[] aliases = {"sp","spec"};

	@Override
	public void onCommand(Player p, String[] args) {
		if(args[0]!=null)
		{
			ArenaManager.getArenaManager().addSpectator(p, args[0]);
		}
		else
		{
			ChatUtils.sendMessage("You must specify an arena!", MessageLevel.WARNING, p);
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
