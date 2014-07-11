package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Start extends SubCommand {
	
	private String name = "start";
	private String info = "Start an arena without waiting for users";
	private String permission = "minigames-core.use.stop";
	private String[] aliases = {""};

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
