package com.wundero.MiniGames_Core.threads;

import com.wundero.MiniGames_Core.arena.Arena;

public class StartCountdown extends MGTimer {

	public StartCountdown(Arena a, int time, boolean inf) {
		super(a, time, inf);
	}

	@Override
	public void executeEnd() {
		arena.startArena(false);
	}
}
