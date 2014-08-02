package com.wundero.MiniGames_Core.listeners;

import java.util.ArrayList;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.listeners.block.BlockChangeListener;
import com.wundero.MiniGames_Core.listeners.block.SignListener;
import com.wundero.MiniGames_Core.listeners.inventory.InventoryClick;
import com.wundero.MiniGames_Core.listeners.player.PlayerAttackListener;
import com.wundero.MiniGames_Core.listeners.player.PlayerInteractListener;
import com.wundero.MiniGames_Core.listeners.player.PlayerJoinListener;
import com.wundero.MiniGames_Core.listeners.player.PlayerLeaveListener;
import com.wundero.MiniGames_Core.listeners.player.SpectatorListener;

public class ListenerManager {
	
	private ArrayList<MGListener> listeners = new ArrayList<MGListener>();
	
	private static ListenerManager inst;
	
	private ListenerManager() {}
	
	public static ListenerManager getListenerManager()
	{
		if(inst==null)
		{
			inst = new ListenerManager();
		}
		return inst;
	}
	
	public void setup()
	{
		listeners = new ArrayList<MGListener>();
		
		listeners.add(new SignListener(Core.getCore()));
		listeners.add(new BlockChangeListener(Core.getCore()));
		listeners.add(new InventoryClick(Core.getCore()));
		listeners.add(new PlayerAttackListener(Core.getCore()));
		listeners.add(new PlayerInteractListener(Core.getCore()));
		listeners.add(new PlayerJoinListener(Core.getCore()));
		listeners.add(new PlayerLeaveListener(Core.getCore()));
		listeners.add(new SpectatorListener(Core.getCore()));
	}
	
	public void disable()
	{
		for(MGListener l : listeners)
		{
			l = null;
		}
		listeners = null;
	}
	
}
