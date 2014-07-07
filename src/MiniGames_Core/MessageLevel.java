package com.wundero.MiniGames_Core;

import org.bukkit.ChatColor;

public enum MessageLevel {
	INFO(null, ChatColor.AQUA), WARNING(null, ChatColor.RED), SEVERE("Severe: ", ChatColor.DARK_RED), ERROR(ChatColor.RED+"Error: ",ChatColor.DARK_RED), SUCCESS(null, ChatColor.GREEN);
	
	private ChatColor color;
	private String prefix;
	
	MessageLevel(String prefix, ChatColor c)
	{
		this.prefix = prefix;
		this.color = c;
	}
	
	public String getPrefix()
	{
		return prefix;
	}
	
	public ChatColor getColor()
	{
		return color;
	}
}
