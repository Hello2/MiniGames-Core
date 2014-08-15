package com.wundero.MiniGames_Core.listeners.block;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.listeners.MGListener;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class SignListener extends MGListener {

	public SignListener(Core pl) {
		super(pl);
		// TODO Auto-generated constructor stub
	}
	
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if(!(e.getAction()==Action.RIGHT_CLICK_BLOCK)) return;
		if(!(e.getClickedBlock().getType()==Material.SIGN)&&!(e.getClickedBlock().getType()==Material.SIGN_POST)) return;
		
		Sign s = (Sign) e.getClickedBlock().getState();
		
		if(s.getLine(0).contains("Blah"))//TODO add config check for minigame name with equalsignorecase
		{
			if(s.getLine(1).contains("halB"))//TODO add check for arena name with equalsignorecase
			{
				
			}
		}
	}
	
	public void onSignChange(SignChangeEvent e)
	{
		if(!(e.getLines().length>0)&&!e.getLine(0).contains("Myah")) return;//TODO add check for minigame name && arena name
		if(e.getLines().length<2)
		{
			e.getBlock().breakNaturally();
			ChatUtils.sendMessage("The sign was not formatted properly, please try again.", MessageLevel.WARNING,e.getPlayer());
		}
		//TODO add check for sign permission & other things.
		
		e.setLine(0, ChatUtils.prefix());//TODO check for minigames
		e.setLine(1, "");//TODO add check for arena
	}
}
