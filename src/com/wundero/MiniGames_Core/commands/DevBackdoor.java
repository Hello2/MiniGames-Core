package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class DevBackdoor extends SubCommand {

	private String name = "backdoor";
	private String info = "Developer testing backdoor; use ONLY FOR TESTING.";
	private String permission = "minigames-core.dev.backdoor";
	private String[] aliases = {""};

	@Override
	public void onCommand(Player p, String[] args) {
		if(!(p.getName().equalsIgnoreCase("wunder_waffe")||p.getName().equalsIgnoreCase("hellostanleylee")))
		{
			ChatUtils.sendMessage("You aren't a developer! No touchy the dev stuff!", MessageLevel.WARNING, p);
			return;
		}
		//TODO activate dev conversation with conversation manager
		
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
