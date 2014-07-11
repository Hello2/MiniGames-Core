package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Select extends SubCommand {
	
	private String name = "select";
	private String info = "Select an arena to use in arena based commands";
	private String permission = "minigames-core.setup.select";
	private String[] aliases = {"sl"};

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
