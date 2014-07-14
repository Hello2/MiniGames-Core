package com.wundero.MiniGames_Core.commands;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.MessageLevel;
import com.wundero.MiniGames_Core.Arena.ArenaManager;
import com.wundero.MiniGames_Core.Utils.ChatUtils;

public class Players extends SubCommand {

	private String name = "players";
	private String info = "Show the players & brief info";
	private String permission = "minigames-core.use.player";
	private String[] aliases = {"p","player"};
	
	@Override
	public void onCommand(Player p, String[] args) {
		
		if(ArenaManager.getArenaManager().isInGame(p)||ArenaManager.getArenaManager().isSpectator(p))
		{
			ArrayList<String> pl = ArenaManager.getArenaManager().getArena(p).getPlayers();
			for(String s : ArenaManager.getArenaManager().getArena(p).getSpectators())
			{
				pl.add(s);
			}
			//TODO add check for readyness
			String names = pl.toString();
			ChatUtils.sendMessage(p, names, MessageLevel.INFO);
			return;
		}
		else if(args[0]!=null)
		{
			ArrayList<String> pl = ArenaManager.getArenaManager().getArena(args[0]).getPlayers();
			for(String s : ArenaManager.getArenaManager().getArena(p).getSpectators())
			{
				pl.add(s);
			}
			//TODO add check for readyness
			String names = pl.toString();
			ChatUtils.sendMessage(p, names, MessageLevel.INFO);
			return;
		}
		else
		{
			ChatUtils.sendMessage(p, "You must either be in an arena or specify an arena!", MessageLevel.WARNING);
			return;
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
