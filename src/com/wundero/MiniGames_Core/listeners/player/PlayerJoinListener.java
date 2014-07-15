package com.wundero.MiniGames_Core.listeners.player;

import org.bukkit.event.EventHandler;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.events.PlayerJoinArenaEvent;
import com.wundero.MiniGames_Core.listeners.MGListener;
import com.wundero.MiniGames_Core.utils.InventoryUtils;

public class PlayerJoinListener extends MGListener {
	public PlayerJoinListener(Core pl)
	{
		super(pl);
	} //TODO register
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinArenaEvent e) //TODO add event for arena join
	{
		InventoryUtils.clearInv(e.getPlayer());
	}
}
