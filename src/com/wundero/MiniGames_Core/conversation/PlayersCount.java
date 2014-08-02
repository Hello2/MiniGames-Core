package com.wundero.MiniGames_Core.conversation;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;

import com.wundero.MiniGames_Core.handlers.MessageLevel;

public class PlayersCount extends NumericPrompt {
	
	private String t;
	private String d;

	public PlayersCount(String type, String desc)
	{
		this.t = type;
		this.d = desc;
	}
	
	@Override
	public Prompt acceptInput(ConversationContext arg0, String arg1) {
		if(isInputValid(arg0, arg1)) return acceptValidatedInput(arg0, arg1);
		arg0.getForWhom().sendRawMessage((new ConvPrefix().getPrefix(arg0)+getFailedValidationText()));
		return this;
	}

	@Override
	public String getPromptText(ConversationContext arg0) {
		return MessageLevel.INFO.getColor()+"What should the "+d.toLowerCase()+" be for "+arg0.getSessionData("ArenaName")+"?";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext arg0, Number arg1) {
		int i = arg1.intValue();
		arg0.setSessionData(t, i);
		ArenaCreationConversation.getInstance().promptnum++;
		return ArenaCreationConversation.getInstance().getNextPrompt();
	}
	
	@Override
	protected boolean isInputValid(ConversationContext c, String s)
	{
		try
		{
			Integer.valueOf(s);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	protected String getFailedValidationText() {
		return MessageLevel.WARNING.getColor()+"That is not a valid number!";
	}

}
