package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Highlight extends SubCommand {
	private String name = "highlight";
	private String info = "Displays the corners of the arena as glowstone blocks";
	private String permission = "minigames-core.setup.highlight";
	private String[] aliases = {"hl"};
	
	@Override
	public void onCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		if(args != null){
			if(ArenaManager.getArenaManager().getArena(args[0]) != null){
				//Start the highlight class and stuff
			
			}else{
				ChatUtils.sendMessage("Arena does not exist!", MessageLevel.WARNING, p);
			}
			
		}else{
			ChatUtils.sendMessage("You must specify an arena!", MessageLevel.WARNING, p);
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
