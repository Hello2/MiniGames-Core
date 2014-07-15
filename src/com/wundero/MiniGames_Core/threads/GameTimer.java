package com.wundero.MiniGames_Core.threads;

import org.bukkit.scheduler.BukkitRunnable;

import com.wundero.MiniGames_Core.arena.Arena;

public class GameTimer extends BukkitRunnable {
	public static int timeElapsed;
	private Arena arena;
	
	public GameTimer(Arena a)
	{
		this.setArena(a);//Constructor
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
