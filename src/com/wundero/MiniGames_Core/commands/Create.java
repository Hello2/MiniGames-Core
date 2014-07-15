package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Create extends SubCommand {
	
	private String name = "create";
	private String info = "Create an arena.";
	private String[] aliases = { "c" };
	private String permission = "minigames-core.admin.create";
	
	@Override
	public void onCommand(Player p, String[] args) {
		// TODO create arena using conversation - arenacreatorconv
		
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
