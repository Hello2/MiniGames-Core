package com.wundero.MiniGames_Core.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.wundero.MiniGames_Core.Core;

public class ArenaBlockChangeEvent extends Event implements Listener { //TODO call event, register listener in core
	private static final HandlerList handlers = new HandlerList();
	private String message;
	
	
	public ArenaBlockChangeEvent(String ex)//TODO get block stuffs
	{
		message = ex;
		Core.registerListener(this);
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
}
