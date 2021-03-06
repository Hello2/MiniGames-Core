package com.wundero.MiniGames_Core.conversation;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;

import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.minigame.MiniGame;
import com.wundero.MiniGames_Core.minigame.MiniGameManager;

public class TypePrompt extends FixedSetPrompt {
	
	@Override
	public String getPromptText(ConversationContext c) {
		return MessageLevel.INFO.getColor()+"What game type should this arena, "+c.getSessionData("ArenaName")+", be?";
	}
	
	@Override
	public Prompt acceptInput(ConversationContext c, String s)
	{
		if(isInputValid(c, s)) return acceptValidatedInput(c, s);
		c.getForWhom().sendRawMessage(new ConvPrefix().getPrefix(c)+getFailedValidationText());
		return this;
	}
	
	@Override
	protected Prompt acceptValidatedInput(ConversationContext c, String s) {
		c.setSessionData("GameType", MiniGameManager.getInstance().getMiniGame(s).name());
		return new PlayersCount("MaxPlayers", "maximum players");//TODO add another prompt
	}
	
	@Override
	protected boolean isInputValid(ConversationContext c, String s)
	{
		for(MiniGame mg : MiniGameManager.getInstance().getAllMiniGames())
		{
			if(mg.name().equalsIgnoreCase(s))
			{
				return true;
			}
		}
		return false;
	}
	
	protected String getFailedValidationText()
	{
		return MessageLevel.WARNING.getColor()+"That is not a valid game type!";
	}

}
