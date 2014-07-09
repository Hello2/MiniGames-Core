package com.wundero.MiniGames_Core.conversation;

import org.bukkit.command.Command;

import static org.bukkit.ChatColor.*;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.ConversationPrefix;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.Core;

public class ArenaConversation implements CommandExecutor,
		ConversationAbandonedListener { // TODO finish and implement

	private ConversationFactory convFact;

	@Override
	public void conversationAbandoned(ConversationAbandonedEvent arg0) {
		// TODO hook through core

	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO hook through command handler
		return false;
	}

	public ArenaConversation() {
		this.convFact = new ConversationFactory(Core.getCore())
				.withModality(true)
				.withPrefix(new ArenaConversationPrefix())
				.withFirstPrompt(new ArenaNamePrompt())
				.withEscapeSequence("exit")
				.withTimeout(50)
				.thatExcludesNonPlayersWithMessage("The console cannot create an arena!")
				.addConversationAbandonedListener(this);

	}

	private class ArenaNamePrompt extends StringPrompt { //TODO add more prompts for further questions

		@Override
		public Prompt acceptInput(ConversationContext arg0, String arg1) {
			return null;
		}

		@Override
		public String getPromptText(ConversationContext arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class ArenaConversationPrefix implements ConversationPrefix {
		final String mainPrefix = DARK_GRAY + "[" + RED + "MiniGames-Core"
				+ DARK_GRAY + "] ";

		@Override
		public String getPrefix(ConversationContext context) {

			return mainPrefix;
		}
	}

}
