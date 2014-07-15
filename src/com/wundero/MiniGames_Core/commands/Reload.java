package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Reload extends SubCommand {
	
	private String name = "reload";
	private String info = "Reload MiniGames-Core";
	private String permission = "minigames-core.admin.reload";
	private String[] aliases = {"re"};

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
