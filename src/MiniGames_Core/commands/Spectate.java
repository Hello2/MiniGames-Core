package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Spectate extends SubCommand {
	
	private String name = "spectate";
	private String info = "Spectate an arena";
	private String permission = "minigames-core.use.spectate";
	private String[] aliases = {"sp","spec"};

	@Override
	public void onCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		
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
