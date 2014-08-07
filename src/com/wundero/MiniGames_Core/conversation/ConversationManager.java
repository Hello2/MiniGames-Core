package com.wundero.MiniGames_Core.conversation;

public class ConversationManager {

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
	
	//TODO stuff
}
