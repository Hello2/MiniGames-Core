package com.wundero.MiniGames_Core.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
			
			ChatUtils.sendMessage(p, ChatColor.WHITE+"Players in arena "+ArenaManager.getArenaManager().getArena(p).getID()+". "+ChatColor.GREEN+"Green means ready"+ChatColor.WHITE+", "+ChatColor.AQUA+"Aqua means spectating"+ChatColor.WHITE+", and "+ChatColor.RED+"red means not ready.", MessageLevel.INFO);
			ChatUtils.sendMessage(p, names, MessageLevel.INFO);
			return;
		}
		else if(args[0]!=null)
		{
			ArrayList<String> pl = ArenaManager.getArenaManager().getArena(args[0]).getPlayers();
			if(pl==null) return;
			for(String s : ArenaManager.getArenaManager().getArena(args[0]).getSpectators())
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
			
			ChatUtils.sendMessage(p, ChatColor.WHITE+"Players in arena "+args[0]+". "+ChatColor.GREEN+"Green means ready"+ChatColor.WHITE+", "+ChatColor.AQUA+"Aqua means spectating"+ChatColor.WHITE+", and "+ChatColor.RED+"red means not ready.", MessageLevel.INFO);
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
