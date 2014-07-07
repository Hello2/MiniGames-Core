package com.wundero.MiniGames_Core.Utils;

import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.MessageLevel;

public class ChatUtils {
	public static void broadcast(String msg)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(prefix()+msg);
		}
	}
	
	private static String prefix()
	{
		String prefix = DARK_GRAY + "[" + 
		//Insert custom thingy from config, or game type
		RED + "MiniGame-Core" + DARK_GRAY +"]" + WHITE+" ";
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
}
