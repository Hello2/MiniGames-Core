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
		if(args != null){
			if(ArenaManager.getArena(args[0] != null)){
				String arenaLocations[6] = {};
				arenaLocations[0] = Arena.getLocations().get(3).getX();
			
			}else{
				ChatUtils.sendMessage(p, "Arena does not exist!", MessageLevel.WARNING);
			}
			
		}else{
			ChatUtils.sendMessage(p, "You must specify an arena!", MessageLevel.WARNING);
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
