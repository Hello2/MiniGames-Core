package com.wundero.MiniGames_Core.Listeners.Player;

import org.bukkit.event.EventHandler;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.Events.PlayerJoinArenaEvent;
import com.wundero.MiniGames_Core.Listeners.MGListener;
import com.wundero.MiniGames_Core.Utils.InventoryUtils;

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
