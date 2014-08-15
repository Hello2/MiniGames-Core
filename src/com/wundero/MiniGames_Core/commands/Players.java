package com.wundero.MiniGames_Core.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Players extends SubCommand {

	private String name = "players";
	private String info = "Show the players & brief info";
	private String permission = "minigames-core.use.player";
	private String[] aliases = {"p","player"};
	
	@Override
	public void onCommand(Player p, String[] args) {
		
		if(ArenaManager.getArenaManager().isInGame(p)||ArenaManager.getArenaManager().isSpectator(p))
		{
			ArrayList<String> pl = ArenaManager.getArenaManager().getArena(p).getPlayersInArena();
			for(String s : ArenaManager.getArenaManager().getArena(p).getPlayersSpectating())
			{
				pl.add(s);
			}
			//TODO add check for readyness
			String names = ChatColor.WHITE+"Players: ";
			
			for(String s : pl)
			{
				if(ArenaManager.getArenaManager().getArena(Bukkit.getPlayer(s)).isReady(Bukkit.getPlayer(s)))
				{
					names += ChatColor.GREEN+s;
				}
				else if(ArenaManager.getArenaManager().isSpectator(Bukkit.getPlayer(s), ArenaManager.getArenaManager().getArena(Bukkit.getPlayer(s)).getID()))
				{
					names += ChatColor.AQUA+s;
				}
				else
				{
					names += ChatColor.RED+s;
				}
				
			}
			
			ChatUtils.sendMessage(ChatColor.WHITE+"Players in arena "+ArenaManager.getArenaManager().getArena(p).getID()+". "+ChatColor.GREEN+"Green means ready"+ChatColor.WHITE+", "+ChatColor.AQUA+"Aqua means spectating"+ChatColor.WHITE+", and "+ChatColor.RED+"red means not ready.", MessageLevel.INFO, p);
			ChatUtils.sendMessage(names, MessageLevel.INFO, p);
			return;
		}
		else if(args[0]!=null)
		{
			ArrayList<String> pl = ArenaManager.getArenaManager().getArena(args[0]).getPlayersInArena();
			if(pl==null) return;
			for(String s : ArenaManager.getArenaManager().getArena(args[0]).getPlayersSpectating())
			{
				pl.add(s);
			}
			
			String names = ChatColor.WHITE+"Players: ";
			
			for(String s : pl)
			{
				if(ArenaManager.getArenaManager().getArena(Bukkit.getPlayer(s)).isReady(Bukkit.getPlayer(s)))
				{
					names += ChatColor.GREEN+s;
				}
				else if(ArenaManager.getArenaManager().isSpectator(Bukkit.getPlayer(s), ArenaManager.getArenaManager().getArena(Bukkit.getPlayer(s)).getID()))
				{
					names += ChatColor.AQUA+s;
				}
				else
				{
					names += ChatColor.RED+s;
				}
				
			}
			
			ChatUtils.sendMessage(ChatColor.WHITE+"Players in arena "+args[0]+". "+ChatColor.GREEN+"Green means ready"+ChatColor.WHITE+", "+ChatColor.AQUA+"Aqua means spectating"+ChatColor.WHITE+", and "+ChatColor.RED+"red means not ready.", MessageLevel.INFO, p);
			ChatUtils.sendMessage(names, MessageLevel.INFO, p);
			return;
		}
		else
		{
			ChatUtils.sendMessage("You must either be in an arena or specify an arena!", MessageLevel.WARNING, p);
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
