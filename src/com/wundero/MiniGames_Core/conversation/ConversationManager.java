package com.wundero.MiniGames_Core.conversation;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.minigame.MiniGame;

public class ConversationManager {
	
	public static ArenaCreationConversation arenaconv;

	private static ConversationManager inst;

	private Core core;

	private Object factory;
	
	private ConversationManager() {}

	public static ConversationManager getInstance()
	{
		if(inst==null)
		{
			inst = new ConversationManager();
		}
		return inst;
	}

	public void hook(Core c)
	{
		this.core = c;
		this.setFactory(core.getFactory());
	}
	
	public Arena createArena(Player p)
	{
		arenaconv = ArenaCreationConversation.getInstance();
		arenaconv.hook(core);
		return arenaconv.createArena(p);
	}
	
	public MiniGame createMinigame(Player p) {return null;}
	
	public Arena editArena(Player p, Arena a) {return null;}
	
	public MiniGame editMinigame(Player p, MiniGame m) {return null;}

	public Object getFactory() {
		return factory;
	}

	public void setFactory(Object factory) {
		this.factory = factory;
	}
	
	//TODO stuff
}
