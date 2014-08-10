package com.wundero.MiniGames_Core.conversation;

public class ConversationManager {
	
	public static ArenaCreationConversation arenaconv;

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
		this.factory = core.getFactory();
	}
	
	public Arena createArena(Player p)
	{
		arenaconv = ArenaCreationConversation.getInstance();
		return arenaconv.createArena(Player p);
	}
	
	public MiniGame createMinigame(Player p) {return null;}
	
	public Arena editArena(Player p, Arena a) {return null;}
	
	public MiniGame editMinigame(Player p, MiniGame m) {return null;}
	
	//TODO stuff
}
