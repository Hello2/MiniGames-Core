package com.wundero.MiniGames_Core.threads;

import com.wundero.MiniGames_Core.arena.Arena;

public class EndCountdown extends MGTimer {
	
	public EndCountdown(Arena a, int time, boolean inf)
	{
		super(a, time, inf);
	}

	@Override
	public void executeEnd() {
		arena.resetArena();
	}
}
