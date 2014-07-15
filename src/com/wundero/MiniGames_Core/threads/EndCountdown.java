package com.wundero.MiniGames_Core.threads;

import org.bukkit.scheduler.BukkitRunnable;

import com.wundero.MiniGames_Core.arena.Arena;

public class EndCountdown extends BukkitRunnable {
	public static int timeUntilStart;
	private Arena arena;
	public static int totalTime;
	
	public EndCountdown(Arena a, int time)
	{
		this.arena = a;
		timeUntilStart = time;
		totalTime = time;
	}
	
	public void run()
	{
		
		
		if(timeUntilStart==0)
		{
			
			arena.resetArena();
			
		}
		
		
		
		timeUntilStart--;
	}
}
