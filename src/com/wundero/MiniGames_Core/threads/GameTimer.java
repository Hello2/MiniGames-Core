package com.wundero.MiniGames_Core.threads;

import org.bukkit.scheduler.BukkitRunnable;

import com.wundero.MiniGames_Core.arena.Arena;

public class GameTimer extends BukkitRunnable {
	private int timeRemaining;
	private int startTime;
	private Arena arena;
	
	public GameTimer(Arena a, int time)
	{
		this.setArena(a);//Constructor
		this.timeRemaining=time;
		this.startTime = time;
	}
	
	public int getTimeRemaining()
	{
		return timeRemaining;
	}
	
	public int getTimeElapsed()
	{
		return startTime-timeRemaining;
	}
	
	public void run()
	{
		//TODO add thingy for display of time remaining
		//TODO add check for arena config to see how much time should pass
		timeRemaining--;
		if(timeRemaining==0)
		{
			arena.endArena();
		}
		
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
}
