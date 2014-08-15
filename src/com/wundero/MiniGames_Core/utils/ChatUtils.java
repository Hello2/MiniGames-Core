package com.wundero.MiniGames_Core.utils;

import static org.bukkit.ChatColor.BLACK;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.WHITE;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.minigame.MiniGame;

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
	
	public static void broadcast(MiniGame t, String msg, MessageLevel level)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(prefix(t)+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
		}
	}
	
	public static String prefix()
	{
		String prefix = BLACK + "[" + 
		//Insert custom thingy from config, or game type
		GREEN + "MiniGame-Core" + BLACK +"]" + WHITE+" ";
		return prefix;
	}
	
	public static String prefix(String name)
	{
		String prefix = BLACK + "[" + GREEN + name + BLACK + "]" + WHITE + " ";
		return prefix;
	}
	
	public static String prefix(MiniGame type)
	{
		String prefix = BLACK + "[" + GREEN + type.name() + BLACK + "]" + WHITE + " ";
		return prefix;
	}
	
	public static void sendMessageToCmdSender(CommandSender s, String msg, MessageLevel level)
	{
		s.sendMessage(prefix()+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
	}
	
	public static void sendMessage(String msg, MessageLevel level, Player... players)
	{
		for(Player p : players)
		{
			p.sendMessage(prefix()+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
		}
	}
	
	public static void sendMessageFromAPI(Plugin plugin, String message, MessageLevel level, Player... players)
	{
		for(Player p : players)
		{
			p.sendMessage(prefix(plugin.getName())+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+message);
		}
	}
	
	public static void sendMessageFromGame(MiniGame t, String m, MessageLevel l, Player... players)
	{
		for(Player p : players)
		{
			p.sendMessage(prefix(t)+l.getPrefix()==null? "" : l.getPrefix()+l.getColor()+m);
		}
	}
	//TODO add methods for Type and such
}
