package com.wundero.MiniGames_Core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.wundero.MiniGames_Core.arena.Arena;

public class PlayerHitEvent extends Event implements Listener {

	private static final HandlerList handlers = new HandlerList();
	private Arena arena;
	private Player p;
	private boolean isCancelled;
	
	public PlayerHitEvent(Arena a, Player p)
	{
		this.arena = a;
		this.p = p;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public Arena getArena()
	{
		return arena;
	}
	
	public boolean isCancelled()
	{
		return isCancelled;
	}
	
	public void setCancelled(boolean b){
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
	//TODO add listener for player hits & such
}
