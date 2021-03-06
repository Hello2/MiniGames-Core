package com.wundero.MiniGames_Core.commands;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Select extends SubCommand {
	
	private String name = "select";
	private String info = "Select an arena to use in arena based commands";
	private String permission = "minigames-core.setup.select";
	private String[] aliases = {"sl"};
	private static HashMap<String, String> selected = new HashMap<String, String>();

	@Override
	public void onCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		if(args[0]!=null){
			if(ArenaManager.getArenaManager().getArena(args[0]) != null){
				selected.put(p.getName(), args[0]);
				ChatUtils.sendMessage("You selected "+args[0]+"!", MessageLevel.INFO, p);
			}else{
				ChatUtils.sendMessage("That is not a valid arena!", MessageLevel.WARNING, p);
			}
		}
		else{
			if(selected.containsKey(p.getName()))
			{
				selected.remove(p.getName());
				ChatUtils.sendMessage("You deselected "+selected.get(p.getName())+"!", MessageLevel.INFO, p);
			}else ChatUtils.sendMessage("You must specify an arena!", MessageLevel.WARNING, p);
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
	
	public static String getSelectedArena(Player p){
		return selected.get(p.getName());
	}
}
