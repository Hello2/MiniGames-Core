package com.wundero.MiniGames_Core.listeners.player;

import org.bukkit.event.EventHandler;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.events.PlayerLeaveArenaEvent;
import com.wundero.MiniGames_Core.listeners.MGListener;

public class PlayerLeaveListener extends MGListener {
	public PlayerLeaveListener(Core pl)
	{
		super(pl);
	} //TODO register
	
	@EventHandler
	public void onPlayerJoin(PlayerLeaveArenaEvent e) //TODO add event for arena join
	{
		
	}
}

