package com.wundero.MiniGames_Core.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.wundero.MiniGames_Core.Arena.Arena;

public class PlayerSpectateArenaEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player p;
	private Arena a;
	private boolean cancelled;
	
	public PlayerSpectateArenaEvent(Player p, Arena a)
	{
		this.a = a;
		this.p = p;
		cancelled = false;
	}
	
	
	public boolean isCancelled()
	{
		return cancelled;
	}
	
	public void setCancelled(boolean b)
	{
		this.cancelled = b;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public Arena getArena()
	{
		return a;
	}
	
	public void changeArena(Arena a)
	{
		this.a = a;
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
