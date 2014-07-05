package com.wundero.MiniGames_Core.Utils;

import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
		//Insert custom thingy from config, or game type, or whatever
		RED + "MiniGame-Core" + DARK_GRAY +"]" + WHITE+" ";
		return prefix;
	}
	
	public static void sendMessage(Player p, String msg)
	{
		p.sendMessage(prefix()+msg);
	}
}
