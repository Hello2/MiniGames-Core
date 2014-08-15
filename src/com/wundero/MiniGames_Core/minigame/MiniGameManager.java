package com.wundero.MiniGames_Core.minigame;

import java.util.ArrayList;

public class MiniGameManager {
	public ArrayList<MiniGame> minigames = new ArrayList<MiniGame>();
	
	private static MiniGameManager inst;
	
	private MiniGameManager() {}
	
	public static MiniGameManager getInstance()
	{
		if(inst==null)
		{
			inst = new MiniGameManager();
		}
		return inst;
	}
	
	public ArrayList<MiniGame> getAllMiniGames()
	{
		return minigames;
	}
	
	public MiniGame getMiniGame(String name)
	{
		for(MiniGame mg : minigames)
		{
			if(mg.name().equalsIgnoreCase(name))
			{
				return mg;
			}
		}
		return null;
	}
}
