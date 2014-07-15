package com.wundero.MiniGames_Core.listeners.player;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.listeners.MGListener;

public class PlayerInteractListener extends MGListener {

	public PlayerInteractListener(Core pl) {
		super(pl);
	} //TODO register
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if(e.getItem()==null) return;
		if(e.getItem().getType()==Material.AIR) return;
		
		//TODO add stuff linking to inventoryutils to make stuff happen
	}

}
