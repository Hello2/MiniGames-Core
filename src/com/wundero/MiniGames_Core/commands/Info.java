package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Info extends SubCommand {
	
	private String name = "info";
	private String info = "Get the info of an arena or minigame";
	private String permission = "minigames-core.use.info";
	private String[] aliases = {"i"};
	
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
