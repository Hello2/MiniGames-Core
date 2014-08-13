package com.wundero.MiniGames_Core.conversation;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class InfoPrompt extends FixedSetPrompt {

	private SetupPrompt setup;
	
	public InfoPrompt()
	{
		super("Name", "Game type", "Minimum players", "Maximum players", "Minimum ready", "None");
	}
	
	public InfoPrompt(SetupPrompt setup)
	{
		super("Name", "Game type", "Minimum players", "Maximum players", "Minimum ready", "None");
		this.setup=setup;
	}
	
	@Override
	protected boolean isInputValid(ConversationContext arg0, String arg1)
	{
		for(String s : fixedSet)
		{
			if(arg1.equalsIgnoreCase(s))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		String msg = ChatColor.AQUA+"What would you like to change?\n";
		msg+=ChatColor.GREEN+"Options:\n";
		msg+=ChatColor.AQUA+"Name, Game type, Minimum players, Maximum players, Minimum ready";
		return msg;
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext arg0, String arg1) {
		
		if(arg1.equalsIgnoreCase("name"))
		{
			return new NamePrompt();
		}
		else if(arg1.equalsIgnoreCase("game type"))
		{
			return new GamePrompt();
		}
		else if(arg1.equalsIgnoreCase("Minimum players"))
		{
			return new NumberPrompt("MinPlayers", arg1);
		}
		else if(arg1.equalsIgnoreCase("Maximum players"))
		{
			return new NumberPrompt("MaxPlayers", arg1);
		}
		else if(arg1.equalsIgnoreCase("Minimum ready"))
		{
			return new NumberPrompt("MinReady", arg1);
		}
		else return setup;
	}
	
	class NumberPrompt extends NumericPrompt
	{
		private String type, desc;
		public NumberPrompt(String type, String desc)
		{
			this.type = type;
			this.desc = desc.toLowerCase();
		}
		
		@Override
		public String getPromptText(ConversationContext arg0) {
			return ChatColor.AQUA+"What should the "+desc.toLowerCase()+" be for "+arg0.getSessionData("ArenaName")+"?";
		}

		@Override
		protected Prompt acceptValidatedInput(ConversationContext arg0, Number arg1) {
			int i = arg1.intValue();
			arg0.setSessionData(type, i);
			return setup;
		}
		
	}
	
	//TODO this class
	class GamePrompt extends FixedSetPrompt
	{
		public GamePrompt()
		{
			super();//TODO add game types
		}
		
		@Override
		public String getPromptText(ConversationContext arg0) {
			String msg = ChatColor.AQUA+"What would you like to change the type to?";
			return msg;
		}
		
		@Override
		protected String getFailedValidationText(ConversationContext c, String i)
		{
			String msg = ChatColor.AQUA+"That is not a valid game type!";
			return msg;
		}

		@Override
		protected Prompt acceptValidatedInput(ConversationContext arg0,
				String arg1) {
			arg0.setSessionData("GameType", arg1);
			return setup;
		}
		
	}
	
	class NamePrompt extends StringPrompt
	{
		private boolean b;
		public NamePrompt()
		{
			b = true;
		}
		@Override
		public Prompt acceptInput(ConversationContext arg0, String arg1) {
			if(arg1.length()<=16)
			{
				arg0.setSessionData("ArenaName", arg1);
				
				arg0.getForWhom().sendRawMessage(new ConvPrefix().getPrefix(arg0)+"The arena's name has been changed to "+arg1+" successfully!");
				return setup;
			}
			else
			{
				arg0.getForWhom().sendRawMessage(new ConvPrefix().getPrefix(arg0)+"That name is too long, please make it shorter.");
				b=false;
				return this;
			}
		}

		@Override
		public String getPromptText(ConversationContext arg0) {
			return (b)?"What would you like to change the name to?":"";
		}
		
	}
}
