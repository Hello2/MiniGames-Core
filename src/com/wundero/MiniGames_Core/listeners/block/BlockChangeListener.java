package com.wundero.MiniGames_Core.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.listeners.MGListener;

public class BlockChangeListener extends MGListener { //TODO register
	public BlockChangeListener(Core pl) {
		super(pl);
	}
	//TODO add more stuff
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
		
	}
	
	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockPiston(BlockPistonEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockPistonExtend(BlockPistonExtendEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockPistonRetract(BlockPistonRetractEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockRedstone(BlockRedstoneEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent e)
	{
		
	}
	
	@EventHandler
	public void onBlockEntityForm(EntityBlockFormEvent e)
	{
		
	}
	
	@EventHandler
	public void onLeafDecay(LeavesDecayEvent e)
	{
		
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e)
	{
		
	}
	
	@EventHandler
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
