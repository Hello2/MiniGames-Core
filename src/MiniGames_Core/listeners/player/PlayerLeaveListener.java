package com.wundero.MiniGames_Core.Listeners.Player;

import org.bukkit.event.EventHandler;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.Events.PlayerLeaveArenaEvent;
import com.wundero.MiniGames_Core.Listeners.MGListener;

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

