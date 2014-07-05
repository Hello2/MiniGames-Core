package com.wundero.MiniGames_Core.Arena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.wundero.MiniGames_Core.ArenaType;
import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.GameState;
import com.wundero.MiniGames_Core.Handlers.Team;
import com.wundero.MiniGames_Core.Misc_Multiple.Randomizer;
import com.wundero.MiniGames_Core.Threads.GameTimer;
import com.wundero.MiniGames_Core.Threads.StartCountdown;

public class Arena {
	
	public String id;
	
	public static int startCountdownId = 0;
	public static int gameTimerId = 0;
	
	
//	private static int x;
	
	private ArrayList<String> players = new ArrayList<String>();
	private int maxPlayers;
	private boolean inProgress;
	private GameState gs;
	private ArenaType type;
	private boolean canStart;
	private Team[] teams;
	private Core c;
	private int tE;
	private int minPlayers;
	private ArrayList<Location> locations; //Numbers to locations, in order:
	/* -NOTE- any location that is not needed should be set to null -NOTE- FFA spawns will be random
	 * Index = location - extra information
	 * 0 = lobby spawn
	 * 1 = lobby corner 1
	 * 2 = lobby corner 2
	 * 3 = arena corner 1
	 * 4 = arena corner 2
	 * 5 = team 1 spawn - random relative to location, toggleable - max distance settable in conf
	 * 6 = team 2 spawn - random relative to location, toggleable - max distance settable in conf
	 * 7 = team 3 spawn - random relative to location, toggleable - max distance settable in conf
	 * 8 = death location - only use if enabled in config, else use spec spawn
	 * 9 = spectator spawn
	 * 10 = 
	 */ 
	private ArrayList<Location> miscLocs; //Numbers to locations, in order
	/* -NOTE- any location that is not needed should be set to null
	 * Index = location - extra information
	 * 
	 */
	
	//TODO Make better, add more info
	
	
	
	public Arena(ArrayList<Location> locations, ArrayList<Location> misclocs, String ID, ArenaType type, int maxp, int minp, Core core) //TODO add spawn areas for team stuffs, use 0.5 for x and z to center player
	{
		
		id = ID;
		maxPlayers = maxp;
		minPlayers = minp;
		this.type = type;
		gs = GameState.EDIT;
		canStart = false;
		this.locations = locations;
		c = core;
		miscLocs = misclocs;
	}
	
	public ArrayList<Location> getLocations()
	{
		return locations;
	}
	public ArrayList<Location> getMiscLocations()
	{
		return miscLocs;
	}
	
	//TODO add team spawn manager
	
	public String getID()
	{
		return id;
	}
	
	public ArenaType getType()
	{
		return type;
	}
	
	public ArrayList<String> getPlayers()
	{
		return players;
	}
	
	public boolean isInProgress()
	{
		return inProgress;
	}
	
	public boolean startArena() //Create exception to throw for unable to start
	{
		if(canStart)
		{
			stopCountdown();
			gs = GameState.IN_GAME;
			if(players.isEmpty())
			{
				return false;
			}
			
			//TODO add conf check to see how many teams to create
			int x = 2;//In a freeforallgame, there would be 0 teams
			//TODO do free for all stuff
			for(int i = 0; i<x; i++)
			{
				teams[i] = new Team(new String[] { Randomizer.randTeamName()}); //TODO add thingy for multiple teams and make em all different
			}
			
			int i = 0;
			for(String p : players)
			{
				if(i>=Team.getTeams().size())
				{
					i = 0;
				}
				Team.getTeams().get(i).addPlayer(Bukkit.getPlayer(p));
				i++;
			}
			
			//TODO tp players to team spawn areas - 5,6,7 in locations
			
			
			/*
			for(int i = 0; i<players.size();i++)
			{
				teams[Randomizer.randomNumber(0, teams.length)].addPlayer(Bukkit.getPlayer(players.get(i)));
			}*/
			
			
			
			
			//TODO add start to listening for player hits and such
			GameTimer.timeElapsed = 0;
			gameTimerId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) ArenaManager.getArenaManager().getCore(), new GameTimer(this), 20l, 20l);
			canStart = false;
			inProgress = true;
			return true;
		}
		return false;
	}
	
	public void startCountdown()
	{
		if(players.size()>=minPlayers)
		{
			StartCountdown.timeUntilStart = 120;
			startCountdownId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) ArenaManager.getArenaManager().getCore(), new StartCountdown(this), 20l, 20l);
		}
		else return;
	}
	
	public void stopCountdown()
	{
		Bukkit.getScheduler().cancelTask(startCountdownId);
	}
	
	public int getMaxPlayers()
	{
		return maxPlayers;
	}
	
	public int getMinPlayers()
	{
		return minPlayers;
	}
	
	public GameState getState()
	{
		return gs;
	}
	
	public void edit()
	{
		gs = GameState.EDIT;
		//TODO add block change stuff w/ logging
	}
	
	public void done() //called when done editing or resetting
	{
		gs = GameState.IN_LOBBY;
		canStart = true;
	}
	
	public void disable()
	{
		gs = GameState.DISABLED;
	}
	
	public void endArena()
	{
		gs = GameState.POST_GAME;
		tE = GameTimer.getTimeElapsed();
		Bukkit.getScheduler().cancelTask(gameTimerId);
		if(locations.get(8)!=null)//Checks for death location, and tps all game players there. If not, tps all players to spec loc
		{
			for(String s : players)
			{
				for(Player p : Bukkit.getServer().getOnlinePlayers())
				{
					if(p.getName()==s)
					{
						p.teleport(locations.get(8));
					}
				}
			}
		}
		else
		{
			for(String s : players)
			{
				for(Player p : Bukkit.getServer().getOnlinePlayers())
				{
					if(p.getName()==s)
					{
						p.teleport(locations.get(9));
					}
				}
			}
		}
		//TODO add more stuff to make ending game better
	}
	public void resetArena()
	{
		//TODO make sure this is triggered after x time in POST_GAME gamestate
		gs = GameState.RESETTING;
		for(Player p : Bukkit.getOnlinePlayers()) //kicks in game players from game
		{
			if(players.contains(p.getName()))
			{
				ArenaManager.getArenaManager().removePlayer(p);
			}
		}
		if(c.getConfig().getBoolean("global.use-rollbacks"))
			c.resetArena(tE, this);
		//TODO do arena resetting - block rollback is done
		
	}
	
	public boolean canStart()
	{
		return canStart;
	}
	
	public void restartCountdown()
	{
		stopCountdown();
		startCountdown();
	}
	
	public boolean isInArena(Location loc) //3,4
	{
		double maxX,maxY,maxZ,minX,minY,minZ;
		maxX = Math.max(locations.get(3).getX(),locations.get(4).getX());
		minX = Math.min(locations.get(3).getX(),locations.get(4).getX());
		maxY = Math.max(locations.get(3).getY(),locations.get(4).getY());
		minY = Math.min(locations.get(3).getY(),locations.get(4).getY());
		maxZ = Math.max(locations.get(3).getZ(),locations.get(4).getZ());
		minZ = Math.min(locations.get(3).getZ(),locations.get(4).getZ());
		
		if(loc.getX()>minX&&loc.getX()<maxX)
		{
			if(loc.getY()>minY&&loc.getY()<maxY)
			{
				if(loc.getZ()>minZ&&loc.getZ()<maxZ)
				{
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	//TODO add config stuff
	
	
}
