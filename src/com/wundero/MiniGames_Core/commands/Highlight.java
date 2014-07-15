package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Highlight extends SubCommand {
	private String name = "highlight";
	private String info = "Displays the corners of the arena as glowstone blocks";
	private String permission = "minigames-core.setup.highlight";
	private String[] aliases = {"hl"};
	
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