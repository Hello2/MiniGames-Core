package com.wundero.MiniGames_Core.threads;

import org.bukkit.scheduler.BukkitRunnable;

import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class StartCountdown extends BukkitRunnable {
	public static int timeUntilStart;
	private Arena arena;
	
	public StartCountdown(Arena a)
	{
		this.arena = a;
	}
	
	public void run()
	{
		
		
		if(timeUntilStart==0)
		{
			
			if(!arena.canStart())
			{
				arena.restartCountdown();
				ChatUtils.broadcast("Restarting countdown, arena could not start!", MessageLevel.WARNING);
				return;
			}
			arena.startArena();
			
		}
		
		if(timeUntilStart%10==0||timeUntilStart < 10)
		{
			ChatUtils.broadcast(String.valueOf(timeUntilStart)+" seconds until the game starts!", MessageLevel.INFO);
		}
		
		if(arena.getPlayers().size()<arena.getMinPlayers()) arena.stopCountdown();
		
		timeUntilStart--;
	}
}
