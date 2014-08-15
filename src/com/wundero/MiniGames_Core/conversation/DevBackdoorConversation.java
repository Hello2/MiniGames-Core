package com.wundero.MiniGames_Core.conversation;

import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.Core;

public class DevBackdoorConversation {
	
	private Core core;
	
	public ConversationFactory factory;
	
	private static DevBackdoorConversation inst;
	
	private DevBackdoorConversation() {}
	
	public static DevBackdoorConversation getInstance()
	{
		if(inst==null)
		{
			inst = new DevBackdoorConversation();
		}
		return inst;
	}
	
	public void hook(Core c)
	{
		this.core = c;
		this.factory = core.getFactory();
	}
	
	public Core getCore()
	{
		return core;
	}
	
	public void startConversation(Player p)
	{
		Conversation conv = factory.withFirstPrompt(new DevPrompt()).withModality(false).withPrefix(new ConvPrefix()).thatExcludesNonPlayersWithMessage("You are not allowed to use conversations!").buildConversation(p);
		conv.begin();
		
	}
	
}
