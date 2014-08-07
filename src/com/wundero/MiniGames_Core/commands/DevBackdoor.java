package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class DevBackdoor extends SubCommand {

	private String name = "backdoor";
	private String info = "Developer testing backdoor; use ONLY FOR TESTING.";
	private String permission = "minigames-core.dev.backdoor";
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
