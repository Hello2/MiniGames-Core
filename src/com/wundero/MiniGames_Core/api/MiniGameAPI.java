package com.wundero.MiniGames_Core.api;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.commands.CommandsManager;
import com.wundero.MiniGames_Core.commands.SubCommand;
import com.wundero.MiniGames_Core.handlers.Team;

public class MiniGameAPI { //TODO more documentation
	
	private static MiniGameAPI mga;
	private static Core core;//Static instances of objects
	private ArrayList<Plugin> plugins = new ArrayList<Plugin>();
	
	public void registerPlugin(Plugin p)
	{
		if(p!=null)
			plugins.add(p);
	}
	
	private MiniGameAPI() {} //TODO remove instance requirements of Arena or other plugin classes
	
	
	/**
	 * Only for the core, to get the API use coreName.getAPI();
	 * @param c
	 * @return
	 */
	public static MiniGameAPI get(Core c)//Gets instance of mga and stores core
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
	
	public String getVersion() //Gets version
	{
		return "0.0.0"; //TODO make better
	}
	
	
	/**
	 * Gets whether or not the API is enabled in the config
	 * @return
	 */
	public boolean isEnabled()//Gets whether or not it is enabled
	{
		return core.getConfig().getBoolean("global.enable-api");
	}
	
	/**
	 * Creates an arena. Will put the player into stup mode.
	 * @param creator
	 * @return
	 */
	public Arena createArena(Player creator)//Creates an arena VIA a player
	{
		return ArenaManager.getArenaManager().createArena(creator); //TODO convert to conversational creation
	}
	
	/**
	 * Adds the @param player to the @param arena.
	 * @param player
	 * @param arena
	 */
	@Deprecated
	public void joinArena(Player player, Arena arena)//Adds a player to an arena, deprecated because objects are fun
	{
		ArenaManager.getArenaManager().addPlayer(player, arena.getID());
	}
	
	/**
	 * Adds the @param player to the arena with @param arenaID
	 * @param arenaID
	 * @param player
	 */
	public void joinArena(Player player, String arenaID)//adds player to an arena
	{
		ArenaManager.getArenaManager().addPlayer(player,arenaID);
	}
	
	/**
	 * Removes @param player from their current arena.
	 * @param player
	 */
	public void leaveArena(Player player)//leaves an arena
	{
		ArenaManager.getArenaManager().removePlayer(player);
	}
	
	/**
	 * @param player spectates arena of id @param arenaID
	 * @param player
	 * @param arenaID
	 */
	public void spectateArena(Player player, String arenaID)//Adds a player to an arena as a spectator
	{
		ArenaManager.getArenaManager().addSpectator(player, arenaID);
	}
	
	/**
	 * Returns all the arenas
	 * @return
	 */
	@Deprecated
	public ArrayList<Arena> getArenas()
	{
		return ArenaManager.getArenaManager().getArenas();
	}
	
	/**
	 * Returns all arena ids
	 * @return
	 */
	public ArrayList<String> getArenaIDs()
	{
		ArrayList<Arena> a = ArenaManager.getArenaManager().getArenas();
		ArrayList<String> ret = new ArrayList<String>();
		for(Arena ar : a){
			ret.add(ar.getID());
		}
		return ret;
	}
	
	/**
	 * Returns all the teams
	 * @return
	 */
	@Deprecated
	public ArrayList<Team> getAllTeams()//TODO make replacement
	{
		return Team.getAllTeams();
	}
	
	public ArrayList<Team> getTeams(String id)
	{
		return ArenaManager.getArenaManager().getArena(id).getTeams();
	}
	
	/**
	 * Deletes arena @param arena
	 * @param a
	 */
	@Deprecated
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
	@Deprecated
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
	@Deprecated
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
	 * Add a command to the main plugin (For example, /mg funstuff)
	 * MAKE SURE TO EXTEND THE SubCommand ABSTRACT CLASS, OR ELSE THIS WILL NOT WORK
	 * As well as that, make sure to add the methods SubCommand has in it.
	 * Do this in your onEnable method to make sure it is always registered. 
	 * @param cmd
	 */
	public void addSubCommand(SubCommand cmd)//TODO save to file to only remember once, optional
	{
		CommandsManager.getCommandsManager().addCommand(cmd);
	}
	
	/**
	 * Checks to see if @param loc is within the arena @param arenaID's boundaries
	 * @param loc
	 * @param arenaID
	 * @return
	 */
	public boolean isInArena(Location loc, String arenaID)
	{
		return ArenaManager.getArenaManager().getArena(arenaID).isInArena(loc);
	}
	
	/**
	 * 
	 */
	public boolean isReady(Player p)
	{
		return ArenaManager.getArenaManager().getArena(p).isReady(p);
	}
	
	//TODO add configuration methods for other plugins to create minigames
	
	// public void addCustomEvent(Event e)
	// {
	// 	//TODO make this usefull
	// }
	
	public void sendMessage(Plugin p, Player player, String message, MessageLevel level)
	{
		//TODO use plugin names for this to make it cooler
	}
	
}
