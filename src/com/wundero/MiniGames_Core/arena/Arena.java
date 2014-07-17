package com.wundero.MiniGames_Core.arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.handlers.GameState;
import com.wundero.MiniGames_Core.handlers.Team;
import com.wundero.MiniGames_Core.handlers.Type;
import com.wundero.MiniGames_Core.misc_multiple.Randomizer;
import com.wundero.MiniGames_Core.threads.EndCountdown;
import com.wundero.MiniGames_Core.threads.GameTimer;
import com.wundero.MiniGames_Core.threads.StartCountdown;

public class Arena {
	
	public String id;
	
	public static int startCountdownId = 0;
	public static int gameTimerId = 0;
	public static int endCountdownId = 0;
	
	
//	private static int x;
	
	private ArrayList<String> players = new ArrayList<String>();
	private HashMap<String, Boolean> pReady = new HashMap<String, Boolean>();
	private ArrayList<String> specs = new ArrayList<String>();
	private int maxPlayers;
	private boolean inProgress;
	private GameState gs;
	private Type type;
	private boolean canStart;
	private Team[] teams;
	private Core c;
	private int tE;
	private int minPlayers;
	private int minReady;
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
	
	
	
	public Arena(ArrayList<Location> locations, ArrayList<Location> misclocs, String ID, Type type, int maxp, int minp, int minr, Core core) //TODO add spawn areas for team stuffs, use 0.5 for x and z to center player
	{
		//Sets variables
		id = ID;
		maxPlayers = maxp;
		minPlayers = minp;
		minReady = minr;
		this.type = type;
		gs = GameState.EDIT;
		canStart = false;
		this.locations = locations;
		c = core;
		miscLocs = misclocs;
	}
	
	public ArrayList<Location> getLocations()//Gets all locations
	{
		return locations;
	}
	public ArrayList<Location> getMiscLocations()//Gets all misc locations
	{
		return miscLocs;
	}
	
	//TODO add team spawn manager
	
	public String getID()//Gets arena id
	{
		return id;
	}
	
	public boolean isReady(Player p)
	{
		return pReady.get(p.getName());
	}
	
	public void setReady(Player p, boolean b)
	{
		pReady.put(p.getName(), b);
	}
	
	public int getMinReady()
	{
		return minReady;
	}
	
	public HashMap<String, Boolean> getReady()
	{
		return pReady;
	}
	
	public Type getType()//gets arena type (minigame)
	{
		return type;
	}
	
	public ArrayList<Team> getTeams()//gets the arena's teams
	{
		return (ArrayList<Team>) Arrays.asList(teams);
	}
	
	public ArrayList<String> getPlayers()//gets the players
	{
		return players;
	}
	
	public ArrayList<String> getSpectators()//gets the spectators
	{
		return specs;
	}
	
	public boolean isInProgress()//gets if the game is in prograss
	{
		return inProgress;
	}
	
	public boolean startArena() //Create exception to throw for unable to start
	{
		if(canStart)
		{
			stopCountdown();//Stops countdown
			gs = GameState.IN_GAME;//Sets game state
			if(players.isEmpty())
			{
				return false;
			}
			
			//TODO add conf check to see how many teams to create - Maybe add to gametype?
			int x = 2;//In a freeforallgame, there would be 0 teams
			//TODO do free for all stuff
			for(int i = 0; i<x; i++)
			{
				teams[i] = new Team(new String[] { Randomizer.randTeamName()}); //TODO add thingy for multiple teams and make em all different
			}
			
			int i = 0;
			for(String p : players)//Adds players to teams
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
			GameTimer.timeElapsed = 0;//sets time elapsed to 0 TODO make better
			gameTimerId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) ArenaManager.getArenaManager().getCore(), new GameTimer(this), 20l, 20l);//Starts game timer
			canStart = false; //sets joinability to false
			inProgress = true;//sets in progress
			pReady.clear();
			return true;
		}
		return false;
	}
	
	public void startCountdown()//Starts countdown
	{
		if(players.size()>=minPlayers)
		{
			StartCountdown.timeUntilStart = 120;//Initializes thread
			startCountdownId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) ArenaManager.getArenaManager().getCore(), new StartCountdown(this), 20l, 20l);
		}
		else return;
	}
	
	public void stopCountdown()//stops countdown
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
		//TODO add failsafe logic if in game
	}
	
	public void endArena()
	{
		gs = GameState.POST_GAME;
		tE = GameTimer.getTimeElapsed()+110; //TODO add check for pregame lobby timer, through configs
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
		endCountdownId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) ArenaManager.getArenaManager().getCore(), new EndCountdown(this, 60), 20l, 20l);
		//TODO add more stuff to make ending game better
	}
	public void resetArena()
	{
		//TODO make sure this is triggered after x time in POST_GAME gamestate
		Bukkit.getScheduler().cancelTask(endCountdownId);
		gs = GameState.RESETTING;
		ArrayList<String> playas = players;//Creates list of players to roll back
		for(String p : specs)
		{
			playas.add(p);
		}
		
		for(Player p : Bukkit.getOnlinePlayers()) //kicks in game players from game
		{
			if(players.contains(p.getName()))
			{
				ArenaManager.getArenaManager().removePlayer(p);
			}
			if(specs.contains(p.getName()))
			{
				ArenaManager.getArenaManager().removePlayer(p);
			}
		}
		
		
		specs.clear();
		players.clear();//Clears lists
		if(c.getConfig().getBoolean("global.use-rollbacks"))
			c.resetArena(tE, this, playas);//Rolls back arena
		
		//TODO do arena resetting - block rollback is done
		
		inProgress = false;
		canStart = true;//Resets booleans
		gs = GameState.IN_LOBBY;
	}
	
	public boolean canStart()
	{
		return canStart;//returns whether arena can start
	}
	
	public void restartCountdown()//Restarts countdown
	{
		stopCountdown();
		startCountdown();
	}
	
	public boolean isInArena(Location loc) //Returns whether or not a location is in the arena - location corners are 3 and 4
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
