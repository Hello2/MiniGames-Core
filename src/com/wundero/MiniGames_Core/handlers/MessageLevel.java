package com.wundero.MiniGames_Core.handlers;

import org.bukkit.ChatColor;

public enum MessageLevel {
	INFO(null, ChatColor.AQUA), WARNING(null, ChatColor.RED), SEVERE("Severe: ", ChatColor.DARK_RED), ERROR(ChatColor.RED+"Error: ",ChatColor.DARK_RED), SUCCESS(null, ChatColor.GREEN);
	//Those are levels of message, with a prefix and a colour
	
	private ChatColor color;
	private String prefix;
	
	MessageLevel(String prefix, ChatColor c)//Constructor
	{
		this.prefix = prefix;
		this.color = c;
	}
	
	public String getPrefix()//gets prefix
	{
		return prefix;
	}
	
	public ChatColor getColor()//gets colour
	{
		return color;
	}
}
