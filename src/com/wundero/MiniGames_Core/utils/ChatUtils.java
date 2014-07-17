package com.wundero.MiniGames_Core.utils;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.handlers.MessageLevel;

public class ChatUtils {
	public static void broadcast(String msg, MessageLevel level)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(prefix()+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
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
	
	private static String prefix(Type type)
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
		player.sendMessage(pre+level.getPrefix()==null? "" : level.getPrefix()+level.getColor()+msg);
	}
	
	//TODO add methods for Type and such
}
