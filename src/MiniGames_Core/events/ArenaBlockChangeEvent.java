package com.wundero.MiniGames_Core.Events;

import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.event.hanging.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.painting.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.world.*;

import com.wundero.MiniGames_Core.Core;

@SuppressWarnings("unused")
public class ArenaBlockChangeEvent extends Event implements Listener { //TODO call event, register listener in core
	private static final HandlerList handlers = new HandlerList();
	private String message;
	
	
	public ArenaBlockChangeEvent(String ex)//TODO get block stuffs
	{
		message = ex;
		Core.registerListener(this);
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		//TODO add check for inside arena
		//TODO make sure, if gamestate is in game, that only players can edit the blocks, and only the blocks that are NOT the walls (to not escape)
		//TODO make sure, if in edit mode, only the player who used the command to edit arena can edit it
		//TODO make sure, if in reset mode, only plugin can change the blocks
	}
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e)
	{
		
	}
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockFade(BlockFadeEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockForm(BlockFormEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockGrow(BlockGrowEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent e)
	{
		
	}
	@EventHandler
	public void onBlockMultiPlace(BlockMultiPlaceEvent e)
	{
		
	}@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e)
	{
		
	}@EventHandler
	public void onBlockPiston(BlockPistonEvent e)
	{
		
	}@EventHandler
	public void onBlockPistonExtend(BlockPistonExtendEvent e)
	{
		
	}@EventHandler
	public void onBlockPistonRetract(BlockPistonRetractEvent e)
	{
		
	}@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		
	}@EventHandler
	public void onBlockRedstone(BlockRedstoneEvent e)
	{
		
	}@EventHandler
	public void onBlockSpread(BlockSpreadEvent e)
	{
		
	}@EventHandler
	public void onBlockEntityForm(EntityBlockFormEvent e)
	{
		
	}@EventHandler
	public void onLeafDecay(LeavesDecayEvent e)
	{
		
	}@EventHandler
	public void onSignChange(SignChangeEvent e)
	{
		
	}@EventHandler
	public void onEntityBlockChange(EntityChangeBlockEvent e)
	{
		
	}
	@EventHandler
	public void onPortalCreate(EntityCreatePortalEvent e)
	{
		
	}
	@EventHandler
	public void onEntityBoom(EntityExplodeEvent e)
	{
		
	}
	@EventHandler
	public void onEntityInteract(EntityInteractEvent e)
	{
		
	}
	
	
	
}
