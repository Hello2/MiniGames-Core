package com.wundero.MiniGames_Core.arena;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.handlers.GameState;
import com.wundero.MiniGames_Core.handlers.MGTeam;
import com.wundero.MiniGames_Core.minigame.MiniGame;
import com.wundero.MiniGames_Core.threads.MGTimer;

public interface Arena {
	public String getID();
	public void setID(String id);
	
	public FileConfiguration getConfig();
	public void setConfig(FileConfiguration f);
	
	public World getWorld();
	public void setWorld(World w);
	
	public boolean isInArena(Location loc);
	public boolean isInLobby(Location loc);
	public boolean isWallBlock(Location loc);
	
	public boolean isEnabled();
	public void setEnabled(boolean enabled);
	
	public GameState getState();
	public void setState(GameState state);
	
	public int getMinPlayers();
	public int getMaxPlayers();
	public int getMinReady();
	public void setMinPlayers(int minPlayers);
	public void setMaxPlayers(int maxPlayers);
	public void setMinReady(int minReady);
	
	public boolean playerJoin(Player p);
	public boolean playerSpectate(Player p);
	public boolean playerLeave(Player p);
	
	public ArrayList<String> getPlayersInArena();
	public ArrayList<String> getPlayersInLobby();
	public ArrayList<String> getPlayersSpectating();
	public ArrayList<String> getReadyPlayers();
	public ArrayList<String> getPlayersToRollback();
	
	public MiniGame getMiniGame();
	public void setMiniGame(MiniGame m);
	
	public ArrayList<Location> getLocations();
	public void setLocations(ArrayList<Location> locs);
	
	public boolean startArena(boolean force);
	
	public boolean endArena(boolean force);
	
	public boolean resetArena();
	
	public boolean canStart();
	public void setCanStart(boolean canStart);
	
	public boolean startTimer(MGTimer timer);
	public boolean stopTimer(MGTimer timer);
	
	public MGTimer getTimer(String type);
	
	public boolean isPlayer(Player p);
	public boolean isReady(Player p);
	
	public void setReady(Player p, boolean b);
	
	public ArrayList<MGTeam> getTeams();
	public void setTeams(ArrayList<MGTeam> teams);
	public void addTeam(MGTeam team);
	
	public void cleanup();
}
