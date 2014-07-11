package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Players extends SubCommand {

	private String name = "players";
	private String info = "Show the players & brief info";
	private String permission = "minigames-core.use.player";
	private String[] aliases = {"p","player"};
	
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
