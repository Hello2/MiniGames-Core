package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Edit extends SubCommand {
	
	private String name = "edit";
	private String info = "Edit an arena";
	private String permission = "minigames-core.setup.edit";
	private String[] aliases = {"e"};

	@Override
	public void onCommand(Player p, String[] args) {
		if(args != null){
			
		}else if(Select.getSelectedArena(p) != null){
			//Also edit the arena
		}else{
			ChatUtils.sendMessage("Please specify or select an arena!", MessageLevel.WARNING, p);
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
