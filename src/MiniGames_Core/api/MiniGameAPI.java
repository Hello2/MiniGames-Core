package com.wundero.MiniGames_Core.api;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.Arena.Arena;
import com.wundero.MiniGames_Core.Arena.ArenaManager;
import com.wundero.MiniGames_Core.Handlers.Team;

public class MiniGameAPI { //TODO more documentation
	
	private static MiniGameAPI mga;
	private static Core core;
	
	private MiniGameAPI() {}
	
	
	/**
	 * Only for the core, to get the API use coreName.getAPI();
	 * @param c
	 * @return
	 */
	public static MiniGameAPI get(Core c)
	{
		if(mga==null)
		{
			mga = new MiniGameAPI();
		}
		core = c;
		return mga;
	}
	
	/**
	 * Returns the version of the API
	 * @return
	 */
	
	public String getVersion() //Version: 1.0.0 in int form: 100. biggest.medium.build. if 1.2.5 is version, return 125
	{
		return "0.0.0"; //TODO make better
	}
	
	
	/**
	 * Gets whether or not the API is enabled in the config
	 * @return
	 */
	public boolean isEnabled()
	{
		return core.getConfig().getBoolean("global.enable-api");
	}
	
	/**
	 * Creates an arena with the params
	 * @param id
	 * @param locations
	 * @param miscLocations
	 * @param arenaType
	 * @param maxPlayers
	 * @param minPlayers
	 * @return
	 */
	public Arena createArena(Player creator)
	{
		return ArenaManager.getArenaManager().createArena(creator); //TODO convert to conversational creation
	}
	
	/**
	 * Adds the @param player to the @param arena.
	 * @param player
	 * @param arena
	 */
	public void joinArena(Player player, Arena arena)
	{
		ArenaManager.getArenaManager().addPlayer(player, arena.getID());
	}
	
	/**
	 * 
	 */
	public void joinArena(Player player, String arenaID)
	{
		ArenaManager.getArenaManager().addPlayer(player,arenaID);
	}
	
	/**
	 * Removes @param player from their current arena.
	 * @param player
	 */
	public void leaveArena(Player player)
	{
		ArenaManager.getArenaManager().removePlayer(player);
	}
	
	public void spectateArena(Player p, Arena a)
	{
		
	}
	
	public ArrayList<Arena> getArenas()
	{
		return ArenaManager.getArenaManager().getArenas();
	}
	
	public ArrayList<Team> getTeams()
	{
		return Team.getAllTeams();
	}
	
	/**
	 * Deletes arena @param arena
	 * @param a
	 */
	public void deleteArena(Arena arena)
	{
		ArenaManager.getArenaManager().deleteArena(arena);
	}
	
	/**
	 * Deletes arena with the id @param arenaID
	 * @param id
	 */
	public void deleteArena(String arenaID)
	{
		ArenaManager.getArenaManager().deleteArena(ArenaManager.getArenaManager().getArena(arenaID));
	}
	
	/**
	 * Starts arena @param arena
	 * @param arena
	 */
	public void startArena(Arena arena)
	{
		arena.startCountdown();
	}
	
	
	/**
	 * Starts arena with the id @param arenaID
	 * @param arenaID
	 */
	public void startArena(String arenaID)
	{
		ArenaManager.getArenaManager().getArena(arenaID).startCountdown();
	}
	
	/**
	 * Stops arena @param arena
	 * @param arena
	 */
	public void stopArena(Arena arena)
	{
		arena.endArena();
	}
	
	/**
	 * Stops arena with the id @param arenaID
	 * @param arenaID
	 */
	public void stopArena(String arenaID)
	{
		ArenaManager.getArenaManager().getArena(arenaID).endArena();
	}
	
	/**
	 * 
	 */
	public boolean sendCommand()
	{
		//TODO add implementation 
		return true;
	}
	
	public boolean isInArena(Location loc, String arenaID)
	{
		return ArenaManager.getArenaManager().getArena(arenaID).isInArena(loc);
	}
	
	
	
}
