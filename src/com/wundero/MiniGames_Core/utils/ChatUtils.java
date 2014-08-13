package com.wundero.MiniGames_Core.utils;

import static org.bukkit.ChatColor.BLACK;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.WHITE;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.wundero.MiniGames_Core.handlers.GameType;
import com.wundero.MiniGames_Core.handlers.MessageLevel;

public class ChatUtils {
	public static void broadcast(String msg, MessageLevel level)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(prefix()+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
		}
	}
	
	public static void broadcast(Plugin pl, String msg, MessageLevel level)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(prefix(p.getName())+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
		}
	}
	
	public static void broadcast(GameType t, String msg, MessageLevel level)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(prefix(t)+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
		}
	}
	
	private static String prefix()
	{
		String prefix = BLACK + "[" + 
		//Insert custom thingy from config, or game type
		GREEN + "MiniGame-Core" + BLACK +"]" + WHITE+" ";
		return prefix;
	}
	
	private static String prefix(String name)
	{
		String prefix = BLACK + "[" + GREEN + name + BLACK + "]" + WHITE + " ";
		return prefix;
	}
	
	private static String prefix(GameType type)
	{
		String prefix = BLACK + "[" + GREEN + type.getGame() + BLACK + "]" + WHITE + " ";
		return prefix;
	}
	
	public static void sendMessage(Player p, String msg, MessageLevel level)
	{
		p.sendMessage(prefix()+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
	}
	
	public static void sendMessage(CommandSender s, String msg, MessageLevel level)
	{
		s.sendMessage(prefix()+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
	}
	
	public static void sendMessage(ArrayList<String> players, String msg, MessageLevel level)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if(players.contains(p.getName()))
			{
				sendMessage(p, msg, level);
			}
		}
	}
	
	public static void sendMessageFromAPI(Plugin plugin, Player player, String message, MessageLevel level)
	{
		String pre = prefix(plugin.getName());
		player.sendMessage(pre+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+message);
	}
	
	public static void sendMessageFromAPI(Plugin plugin, String message, MessageLevel level, Player... players)
	{
		String pre = prefix(plugin.getName());
		for(Player p : players)
		{
			p.sendMessage(pre+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+message);
		}
	}
	
	public static void sendMessageFromGame(GameType t, Player p, String m, MessageLevel l)
	{
		p.sendMessage(prefix(t)+l.getPrefix()==null?"":l.getPrefix()+l.getColor()+m);
	}
	
	public static void sendMessageFromGame(GameType t, ArrayList<String> players, String m, MessageLevel l){
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if(players.contains(p.getName()))
			{
				p.sendMessage(prefix(t)+l.getPrefix()==null? "" : l.getPrefix()+l.getColor()+m);
			}
		}
	}

	public static String getPrefix() {
		return prefix();
	}
	
	//TODO add methods for Type and such
}
