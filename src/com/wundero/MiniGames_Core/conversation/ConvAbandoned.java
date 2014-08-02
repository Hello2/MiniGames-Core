package com.wundero.MiniGames_Core.conversation;

import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class ConvAbandoned implements ConversationAbandonedListener {

	
	
	
	@Override
	public void conversationAbandoned(ConversationAbandonedEvent e) {
		
		if(!(e.getCanceller() instanceof Player)) return;
		
		ChatUtils.sendMessage((Player) e.getCanceller(), "Arena creation cancelled.", MessageLevel.WARNING);
	}

}
