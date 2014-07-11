package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Ready extends SubCommand {
	private String name = "ready";
	private String info = "Ready up for a game";
	private String permission = "minigames-core.use.ready";
	private String[] aliases = {"r"};
	
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
