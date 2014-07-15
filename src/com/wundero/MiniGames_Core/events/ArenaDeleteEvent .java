package com.wundero.MiniGames_Core.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.wundero.MiniGames_Core.arena.Arena;

public class ArenaDeleteEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Arena arena;
	private boolean isCancelled;
	
	public ArenaDeleteEvent(Arena a)
	{
		arena = a;
		isCancelled = false;
	}
	
	public Arena getArena()
	{
		return arena;
	}
	
	public boolean isCancelled()
	{
		return isCancelled;
	}
	
	public void setCancelled(boolean b)
	{
		isCancelled = b;
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
