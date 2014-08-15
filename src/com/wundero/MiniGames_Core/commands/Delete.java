package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.GameState;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class Delete extends SubCommand {
	
	private String name = "delete";
	private String info = "Create an arena.";
	private String permission = "minigames-core.admin.delete";
	private String[] aliases = {"d"};
	
	@Override
	public void onCommand(Player p, String[] args) {
		
		if(args.length==0)
		{
			if(Select.getSelectedArena(p)==null)
			{
			ChatUtils.sendMessage("You must specify an arena!", MessageLevel.WARNING, p);
			return;
			}
		}
		
		Arena a = null;
		for(Arena ar : ArenaManager.getArenaManager().getArenas())
		{
			if(ar.getID().equalsIgnoreCase(args[0])) { a = ar; break; }
		}
		
		if(a==null)
		{
			if(Select.getSelectedArena(p)!=null) a = ArenaManager.getArenaManager().getArena(Select.getSelectedArena(p));
			else
			{
				ChatUtils.sendMessage("There is no arena with id \""+args[0]+"\"", MessageLevel.WARNING, p);
				return;
			}
		}
		
		if(a.getState()==GameState.IN_GAME)
		{
			ChatUtils.sendMessage("You cannot delete an arena that is in progress!", MessageLevel.WARNING, p);
			return;
		}
		
		
		ChatUtils.sendMessage( "Are you sure you want to delete arena "+a.getID()+"?", MessageLevel.WARNING, p);
		//TODO confirmation, use conversation
		//if(.getSessionData().get("Confirmed")){
		ArenaManager.getArenaManager().deleteArena(a);
		ChatUtils.sendMessage("Successfully deleted "+a.getID()+"!", MessageLevel.SUCCESS, p);
		//} else
		//{
		//	ChatUtils.sendMessage(p, "Arena deletion cancelled!", MessageLevel.WARNING);
		//}
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String info() {
		return info;
	}

	@Override
	public String[] aliases() {
		return aliases;
	}

	@Override
	public String permission() {
		return permission;
	}
	
}
