package com.wundero.MiniGames_Core.conversation;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;

import com.wundero.MiniGames_Core.utils.ChatUtils;

public class ConvPrefix implements ConversationPrefix {

	@Override
	public String getPrefix(ConversationContext arg0) {
		String ret = ChatUtils.prefix();
		return ret;
	}

}
