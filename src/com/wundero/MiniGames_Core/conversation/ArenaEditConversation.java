package com.wundero.MiniGames_Core.conversation;

import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.minigame.MiniGameManager;

public class ArenaEditConversation {
	
	private Core core;
	
	public ConversationFactory factory;
	
	private static ArenaEditConversation inst;
	
	private ArenaEditConversation() {}
	
	public static ArenaEditConversation getInstance()
	{
		if(inst==null)
		{
			inst = new ArenaEditConversation();
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
	
	public Arena editArena(Player p, Arena a)
	{
		SetupPrompt setup = new SetupPrompt("Edit");
		Conversation c = factory.withFirstPrompt(setup).withEscapeSequence("exit").thatExcludesNonPlayersWithMessage("You cannot create an arena, silly!").withPrefix(new ConvPrefix()).addConversationAbandonedListener(new ConvAbandoned()).withLocalEcho(false).buildConversation((Conversable) p);
		c.begin();
		ConversationContext co = c.getContext();
		Arena ar = a;
		if(co.getSessionData("ArenaName")!=null) a.setID((String) co.getSessionData("ArenaName"));
		if(co.getSessionData("GameType")!=null)
		{
			a.setMiniGame(MiniGameManager.getInstance().getMiniGame((String) co.getSessionData("GameType")));
		}
		if(co.getSessionData("MinPlayers")!=null)
		{
			a.setMinPlayers((int) co.getSessionData("MinPlayers"));
		}
		if(co.getSessionData("MaxPlayers")!=null)
		{
			a.setMaxPlayers((int) co.getSessionData("MaxPlayers"));
		}
		if(co.getSessionData("MinReady")!=null)
		{
			a.setMinReady((int) co.getSessionData("MinReady"));
		}
		
		if(setup.getLocs()!=a.getLocations()) a.setLocations(setup.getLocs());
		
		return ArenaManager.getArenaManager().updateArena(ar, a);
	}
	
}
