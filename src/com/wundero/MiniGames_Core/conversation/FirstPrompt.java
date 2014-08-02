package com.wundero.MiniGames_Core.conversation;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

import com.wundero.MiniGames_Core.handlers.MessageLevel;

public class FirstPrompt extends StringPrompt {
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		String msg = MessageLevel.INFO.getColor()+"What would you like to call the arena?";
		return msg;
	}
	
	@Override
	public Prompt acceptInput(ConversationContext c, String s) {
		c.setSessionData("ArenaName", s);
		return new TypePrompt();
	}
}
