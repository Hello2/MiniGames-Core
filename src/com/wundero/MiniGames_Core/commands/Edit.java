package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Edit extends SubCommand {
	
	private String name = "edit";
	private String info = "Edit an arena";
	private String permission = "minigames-core.setup.edit";
	private String[] aliases = {"e"};

	@Override
	public void onCommand(Player p, String[] args) {
		if(args != null){
			//Edit the arena
		}else if(Select.getSelectedArena() != null){
			//Also edit the arena
		}else{
			ChatUtils.sendMessage(p, "Please specify or select an arena!", MessageLevel.WARNING);
		}
		
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
