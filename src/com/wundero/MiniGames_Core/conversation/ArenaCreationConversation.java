package com.wundero.MiniGames_Core.conversation;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.GameType;

public class ArenaCreationConversation {
	
	public int promptnum;
	
	private Core core;
	
	public ConversationFactory factory;
	
	private static ArenaCreationConversation inst;
	
	private ArenaCreationConversation() {}
	
	public static ArenaCreationConversation getInstance()
	{
		if(inst==null)
		{
			inst = new ArenaCreationConversation();
		}
		return inst;
	}
	
	public void hook(Core c)
	{
		this.core = c;
		this.factory = core.getFactory();
	}
	
	public Prompt getNextPrompt()
	{
		if(promptnum==0) return new PlayersCount("MaxPlayers", "maximum players");
		else if(promptnum==1) return new PlayersCount("MinPlayers", "minimum players");
		else if(promptnum==2) return new PlayersCount("MinReady", "minimum players that need to be ready for the game to start");
		else return new SetupPrompt("Create");
	}
	
	public Core getCore()
	{
		return core;
	}
	
	public Arena createArena(Player p)
	{
		promptnum = 0;
		Conversation c = factory.withFirstPrompt(new FirstPrompt()).withEscapeSequence("exit").thatExcludesNonPlayersWithMessage("You cannot create an arena, silly!").withPrefix(new ConvPrefix()).addConversationAbandonedListener(new ConvAbandoned()).withLocalEcho(false).buildConversation((Conversable) p);
		ConversationContext co = c.getContext();
		String arenaName = (String) co.getSessionData("ArenaName");
		GameType gType = (GameType) co.getSessionData("GameType");
		//TODO get stuff from setup
		int maxPlayers = (int) co.getSessionData("MaxPlayers"), minPlayers = (int) co.getSessionData("MinPlayers"), minReady = (int) co.getSessionData("MinReady");
		
		return ArenaManager.getArenaManager().createArena(locs, locs2, arenaName, gType, maxPlayers, minPlayers, minReady);
	}
	
}
