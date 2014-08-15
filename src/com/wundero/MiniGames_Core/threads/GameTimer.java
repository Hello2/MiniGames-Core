package com.wundero.MiniGames_Core.threads;

import com.wundero.MiniGames_Core.arena.Arena;

public class GameTimer extends MGTimer {
	
	public GameTimer(Arena a, int time, boolean inf)
	{
		super(a, time, inf);
	}
	
	@Override
	public void executeEnd()
	{
		arena.endArena(false);
	}
}
