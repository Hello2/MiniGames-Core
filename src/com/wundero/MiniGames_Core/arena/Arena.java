package com.wundero.MiniGames_Core.arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.configuration.SettingsManager;
import com.wundero.MiniGames_Core.handlers.GameState;
import com.wundero.MiniGames_Core.handlers.GameType;
import com.wundero.MiniGames_Core.handlers.MGTeam;
import com.wundero.MiniGames_Core.minigame.MiniGame;
import com.wundero.MiniGames_Core.misc_multiple.Randomizer;
import com.wundero.MiniGames_Core.threads.EndCountdown;
import com.wundero.MiniGames_Core.threads.GameTimer;
import com.wundero.MiniGames_Core.threads.StartCountdown;

public class Arena {
	
	public String id;
	
	public int startCountdownId = 0;
	public int gameTimerId = 0;
	public int endCountdownId = 0;
	
	
//	private static int x;
	
	private ArrayList<String> players = new ArrayList<String>();
	private HashMap<String, Boolean> pReady = new HashMap<String, Boolean>();
	private ArrayList<String> specs = new ArrayList<String>();
	private ArrayList<String> playas = new ArrayList<String>();
	private int maxPlayers;
	private boolean inProgress;
	private GameState gs;
	private GameType type;
	private boolean canStart;
	private MGTeam[] teams;
	private Core c;
	private int tE;
	private int minPlayers;
	private int minReady;
	private MiniGame mg;
	private boolean canPlayersEdit;
	private ArrayList<Location> locations; //Numbers to locations, in order:
	/* -NOTE- any location that is not needed should be set to null -NOTE- FFA spawns will be random
	 * Index = location - extra information
	 * 0 = lobby spawn
	 * 1 = lobby corner 1
	 * 2 = lobby corner 2
	 * 3 = arena corner 1
	 * 4 = arena corner 2
	 * 5 = death location - only use if enabled in config, else use spec spawn
	 * 6 = spectator spawn
	 * 7 = team 1 spawn - random relative to location, toggleable - max distance settable in conf
	 * 8 = team 2 spawn - random relative to location, toggleable - max distance settable in conf
	 * 9 = team 3 spawn - random relative to location, toggleable - max distance settable in conf
	 * 10 = team 4 spawn, all nums + up to 23 are teams
	 * 7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22 
	 * 23 = 
	 */  
	private ArrayList<Location> miscLocs; //Numbers to locations, in order
	/* -NOTE- any location that is not needed should be set to null
	 * Index = location - extra information
	 * 0-7: arena corners 1-8
	 * 8-15: lobby corners 1-8 - these coords are all 8 corner coords and will be created when the arena is made by using the existing two locs
	 * 
	 */
	
	//TODO Make better, add more info
	
	private Scoreboard sb;
	private Objective o;
	
	public Arena(ArrayList<Location> locations, ArrayList<Location> misclocs, String ID, GameType type, int maxp, int minp, int minr, Core core) //TODO add spawn areas for team stuffs, use 0.5 for x and z to center player
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
		sb = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		o = sb.registerNewObjective("Team Scores", "dummy");//TODO make sure it takes obj name from config. Always use dummy objs though.
		//TODO add stuff for mg and can edit
		
		for(Location l : getAllCornerLocs(locations.get(3),locations.get(4)))
		{
			this.miscLocs.add(l);
		}
		for(Location l : getAllCornerLocs(locations.get(1),locations.get(2)))
		{
			this.miscLocs.add(l);
		}
		
	}
	
	public void setLocations(ArrayList<Location> locs)
	{
		this.locations = locs;
	}
	
	public Arena(FileConfiguration conf)
	{
		id = conf.getString("name");
		maxPlayers = conf.getInt("max_players");
		minPlayers = conf.getInt("min_players");
		minReady = conf.getInt("min_ready");
	}
	
	public void cleanup()
	{
		
		if(Bukkit.getScheduler().isCurrentlyRunning(startCountdownId))
		{
			stopCountdown();
		}
		endArena(true);
		
		players = null;
		pReady = null;
		specs = null;
		playas = null;
		gs = null;
		type = null;
		teams = null;
		c = null;
		locations = null;
		miscLocs = null;
		sb = null;
		o = null;
	}
	
	public Objective getObjective()
	{
		return o;
	}
	
	public Scoreboard getScoreboard()
	{
		return sb;
	}
	
	public Arena(String name)
	{
		new Arena(YamlConfiguration.loadConfiguration(SettingsManager.getSettingsManager().getFile(name)));
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
	
	public GameType getType()//gets arena type (minigame)
	{
		return type;
	}
	
	public ArrayList<MGTeam> getTeams()//gets the arena's teams
	{
		return (ArrayList<MGTeam>) Arrays.asList(teams);
	}
	
	public ArrayList<String> getPlayers()//gets the players
	{
		return players;
	}
	
	public ArrayList<String> getSpectators()//gets the spectators
	{
		return specs;
	}
	
	public ArrayList<String> getAllPlayers()
	{
		return playas;
	}
	
	public boolean isInProgress()//gets if the game is in prograss
	{
		return inProgress;
	}
	
	public ArrayList<Location> getAllCornerLocs(Location loc1, Location loc2)
	{
		if(!loc1.getWorld().getName().equals(loc2.getWorld().getName())) return null;
		World w = loc1.getWorld();
		double x1 = loc1.getX(), x2 = loc2.getX(), y1 = loc1.getY(), y2 = loc2.getY(), z1 = loc1.getZ(), z2 = loc2.getZ();
		ArrayList<Location> result = new ArrayList<Location>();
		result.add(new Location(w,x1,y1,z1));
		result.add(new Location(w,x1,y1,z2));
		result.add(new Location(w,x1,y2,z1));
		result.add(new Location(w,x1,y2,z2));
		result.add(new Location(w,x2,y1,z1));
		result.add(new Location(w,x2,y1,z2));
		result.add(new Location(w,x2,y2,z1));
		result.add(new Location(w,x2,y2,z2));
		return result;
	}
	
	public boolean isObjectiveMet()
	{
		Score[] teamScores = new Score[teams.length];
		for(Score s : teamScores)
		{
			if((Integer) s.getScore() == SettingsManager.getSettingsManager().getValue(id+"-arena.yml", ""))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Objective getScoreboardObjective()
	{
		return o;
	}
	
	@SuppressWarnings("deprecation")
	public boolean startArena() //TODO Create exception to throw for unable to start
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
				teams[i] = new MGTeam(new String[] { Randomizer.randTeamName()}, sb, o); //TODO add thingy for multiple teams and make em all different
			}
			
			int i = 0;
			for(String p : players)//Adds players to teams
			{
				if(i>=MGTeam.getTeams().size())
				{
					i = 0;
				}
				MGTeam.getTeams().get(i).addPlayer(Bukkit.getPlayer(p));
				i++;
			}
			
			//TODO tp players to team spawn areas
			
			//TODO start objectives
			
			/*
			for(int i = 0; i<players.size();i++)
			{
				teams[Randomizer.randomNumber(0, teams.length)].addPlayer(Bukkit.getPlayer(players.get(i)));
			}*/
			
			
			
			
			//TODO add start to listening for player hits and such
			gameTimerId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) ArenaManager.getArenaManager().getCore(), new GameTimer(this, 120), 20l, 20l);//Starts game timer
			canStart = false; //sets joinability to false
			inProgress = true;//sets in progress
			pReady.clear();
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public void startCountdown()//Starts countdown
	{
		if(players.size()>=minPlayers)
		{//TODO get time from arena config
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
	}
	
	public void done() //called when done editing or resetting
	{
		gs = GameState.IN_LOBBY;
		canStart = true;
	}
	
	public void disable()
	{
		endArena(true);
		gs = GameState.DISABLED;
		canStart = false;
	}
	
	@SuppressWarnings("deprecation")
	public void endArena()
	{
		gs = GameState.POST_GAME;
		tE = ((GameTimer) Bukkit.getScheduler().getActiveWorkers().get(gameTimerId)).getTimeElapsed()+110; //TODO add check for pregame lobby timer, through configs
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
	
	public void endArena(boolean reload)
	{
		gs = GameState.POST_GAME;
		tE = ((GameTimer) Bukkit.getScheduler().getActiveWorkers().get(gameTimerId)).getTimeElapsed()+110; //TODO add check for pregame lobby timer, through configs
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
		resetArena(true);
	}
	
	public void resetArena(boolean reload)
	{
		gs = GameState.RESETTING;
		playas = players;//Creates list of players to roll back
		for(String p : specs)
		{
			if(!playas.contains(p)) playas.add(p);
			
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
	
	public boolean isWallBlock(Location loc)
	{
		//TODO this
		return false;
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

	public boolean canPlayersEdit() {
		return canPlayersEdit;
	}

	public void setCanPlayersEdit(boolean canPlayersEdit) {
		this.canPlayersEdit = canPlayersEdit;
	}

	public MiniGame getMiniGame() {
		return mg;
	}
	
	//TODO comment this out
	public void setMiniGame(MiniGame mg) {
		this.mg = mg;
	}
	
	//TODO add config stuff
	
	
}
