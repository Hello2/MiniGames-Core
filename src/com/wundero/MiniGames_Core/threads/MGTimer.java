package com.wundero.MiniGames_Core.threads;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;
import com.wundero.MiniGames_Core.utils.PlayerUtils;

public abstract class MGTimer extends BukkitRunnable {
	
	protected int startTime, timeElapsed, timeRemaining;
	protected boolean infinite;
	protected Arena arena;
	
	public MGTimer(Arena a, int time, boolean infinite)
	{
		this.arena = a;
		startTime = time;
		timeElapsed = 0;
		timeRemaining = time;
		this.infinite = infinite;
	}
	
	public int getTimeElapsed()
	{
		return timeElapsed;
	}
	
	public int getTimeRemaining()
	{
		return timeRemaining;
	}
	
	public int getStartTime()
	{
		return startTime;
	}
	
	public Arena getArena()
	{
		return arena;
	}
	
	public abstract void executeEnd();
	
	public void run()
	{
		timeRemaining--;
		timeElapsed++;
		if(!infinite)
		{
			Player[] players1 = PlayerUtils.getPlayersFromList(arena.getPlayersInArena());
			Player[] players2 = PlayerUtils.getPlayersFromList(arena.getPlayersSpectating());
			
			ChatUtils.sendMessageFromGame(arena.getMiniGame(), "Time remaining: "+Integer.toString(timeRemaining), MessageLevel.INFO, players1);
			ChatUtils.sendMessageFromGame(arena.getMiniGame(), "Time remaining: "+Integer.toString(timeRemaining), MessageLevel.INFO, players2);
		}
		if(timeRemaining==0&&!infinite)
		{
			executeEnd();
		}
	}
}
