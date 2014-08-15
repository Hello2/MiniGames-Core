package com.wundero.MiniGames_Core.listeners.block;

import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockEvent;
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

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.handlers.GameState;
import com.wundero.MiniGames_Core.listeners.MGListener;

public class BlockChangeListener extends MGListener { //TODO register
	public BlockChangeListener(Core pl) {
		super(pl);
	}
	
	//TODO add permissions check
	private void onBlockChange(BlockEvent e)
	{
		for(Arena a : ArenaManager.getArenaManager().getArenas())
		{
			if(a.isWallBlock(e.getBlock().getLocation())) {
				if(a.getState().equals(GameState.EDIT))
				{
					return;
				}
				((Cancellable) e).setCancelled(true);
				return;
			}
			if(a.isInArena(e.getBlock().getLocation()))
			{
				if(a.getState().equals(GameState.IN_GAME)&&a.getConfig().getBoolean("gameplay.blocks.player-edit"))
				{
					return;
				}
				if(a.getState().equals(GameState.EDIT)) return;
				((Cancellable) e).setCancelled(true);
				return;
			}
		}
		return;
	}
	
	//TODO add more stuff
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		onBlockChange(e);
	}
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e)
	{
		onBlockChange(e);
	}
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockFade(BlockFadeEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockForm(BlockFormEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockGrow(BlockGrowEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockMultiPlace(BlockMultiPlaceEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockPiston(BlockPistonEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockPistonExtend(BlockPistonExtendEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockPistonRetract(BlockPistonRetractEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockRedstone(BlockRedstoneEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onBlockEntityForm(EntityBlockFormEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onLeafDecay(LeavesDecayEvent e)
	{
		onBlockChange(e);
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e)
	{
		onBlockChange(e);
	}
	
}
