package com.wundero.MiniGames_Core.Threads;

import org.bukkit.scheduler.BukkitRunnable;

import com.wundero.MiniGames_Core.Arena.Arena;
//import com.wundero.MiniGames_Core.Utils.ChatUtils;

public class GameTimer extends BukkitRunnable {
	public static int timeElapsed;
	private Arena arena;
	
	public GameTimer(Arena a)
	{
		this.setArena(a);
	}
	
	public static int getTimeElapsed()
	{
		return timeElapsed;
	}
	
	public void run()
	{
		//TODO add thingy for display of time remaining
		//TODO add check for arena config to see how much time should pass
		timeElapsed++;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
}
