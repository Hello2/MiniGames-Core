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
		String[] allPlaying = {};//String array that should contain the players currently playing in the arena
		String[] allSpec = {};//String array that should contain all the spectators
		String[] allPlayers = {};//String array that should contain both playing and spectators
		String allPlayer;//Contains allPlayer[] but just in string form
		String arenaName;//String containing the name of the arena
		if(args[0]!=null){//checks if the player specified any arena name
			arenaName = args[1];
			//^if the player did specify an arena name, then set the arena name to what he/she stated
		}else{// runs if the player didn't specify any arena name
			arenaName = ArenaManager.getArenaManager().isInGame(p);
			//^sets the arena name to the arena the player is currently in	
		}
		
		allPlaying = (ArenaManager.getArenaManager().getArena(arenaID(arenaName)).getPlayers()).toArray();
		//^ sets the array allPlaying to contain the players currently playing in the arena specified
		allSpec = (ArenaManager.getArenaManager().getArena(arenaID(arenaName)).getSpectators()).toArray();
		//^ sets the array allSpec to contain all the spectators in the arena
		allPlayers = allPlaying + allSpec;//combines the arrays
		
		for(int x; x >= allPlayers.length; x ++){
			//^loops through allPlayers[] and adds the "x" element to allPlayer
			allPlayer += allPlayers[x];//adds the player to allPlayer
			allPlayer += ", ";//adds a comma after each player name for clarity
		}
		
		ChatUtils.sendMessage(p, allPlayer);
			
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
