package com.wundero.MiniGames_Core.commands;

import org.bukkit.entity.Player;

public class Stop extends SubCommand {

	private String name = "stop";
	private String info = "Stop an arena without waiting for users";
	private String permission = "minigames-core.use.stop";
	private String[] aliases = {"end"};

	@Override
	public void onCommand(Player p, String[] args) {
		//TODO add confirmation
		Arena ar;
		for(Arena a : ArenaManager.getArenaManager().getArenas())
		{
			if(a.getID().equalsIgnoreCase(args[0])) { ar = a; break; }
		}
		if(ar==null)
		{
			if(Select.getSelected(p)!=null) ar = ArenaManager.getArenaManager().getArena(Select.getSelected(p));
			else
			{
				ChatUtils.sendMessage(p, "That arena does not exist!", MessageLevel.WARNING);
			}
		}
		
		//Add confirmation here
		ar.endArena(true);
		ChatUtils.sendMessage(p, "Arena "+ar.getIF+D()+" successfully stopped.", MessageLevel.INFO);
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
