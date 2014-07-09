package com.wundero.MiniGames_Core.Listeners;

import org.bukkit.event.Listener;

import com.wundero.MiniGames_Core.Core;

public class MGListener implements Listener {

	Core plugin;
	
	public MGListener(Core pl)
	{
		plugin = pl;
		plugin.registerListener(this);
	}
}
