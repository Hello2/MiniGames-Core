package com.wundero.MiniGames_Core.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.configuration.SettingsManager;
import com.wundero.MiniGames_Core.conversation.ArenaCreationConversation;
import com.wundero.MiniGames_Core.events.ArenaCreateEvent;
import com.wundero.MiniGames_Core.events.PlayerJoinArenaEvent;
import com.wundero.MiniGames_Core.events.PlayerLeaveArenaEvent;
import com.wundero.MiniGames_Core.events.PlayerSpectateArenaEvent;
import com.wundero.MiniGames_Core.handlers.GameState;
import com.wundero.MiniGames_Core.handlers.MessageLevel;
import com.wundero.MiniGames_Core.minigame.MiniGame;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class ArenaManager {
	
	//TODO Make class better
	
	//TODO rename variables
	
	public Map<String, Location> locs = new HashMap<String, Location>();//Player locations
	
	private static ArenaManager am;//Static instance
	
	private boolean b = false;//I dunno what this is or does, trying to figure this out :P
	
	private Core c;//Core instance
	
	Map<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
	Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();//Players inventories and armor - TODO store XP
	
	ArrayList<Arena> arenas = new ArrayList<Arena>();
	
	private ArenaManager(){}//Private constructor to prevent multiple instances
	
	public static ArenaManager getArenaManager()//Returns instance from static reference
	{
		if(am==null)
		{
			am = new ArenaManager();
		}
		
		return am;
	}
	
	@SuppressWarnings("unchecked")
	public void setup()
	{
		for(String s : (ArrayList<String>) SettingsManager.getSettingsManager().getValue("arenas.yml", "arenas"))
		{
			new ArenaImpl(YamlConfiguration.loadConfiguration(SettingsManager.getSettingsManager().getFile(s)));
		}
	}
	
	public Arena updateArena(Arena oldArena, Arena newArena)
	{
		int index = arenas.indexOf(oldArena);
		arenas.set(index, newArena);
		return newArena;
	}
	
	public void disable()
	{
		saveArenasToFile();
		for(Arena a : getArenas())
		{
			a.cleanup();
		}
		arenas = null;
		inv = null;
		armor = null;
		locs = null;
	}
	
	public void saveArenasToFile()
	{
		for(Arena a : getArenas())
		{
			SettingsManager.getSettingsManager().saveArenaInfo(a);
		}
	}
	
	public Core getCore()//gets core
	{
		return c;
	}
	
	public Arena getArena(String id)//Gets an arena with the id
	{
		for(Arena a : arenas)
		{
			if(a.getID().equalsIgnoreCase(id)) //Change to just equals if problems arise with multiple same arena names with different case
			{
				return a;
			}
		}
		return null;
	}
	
	public Arena getArena(Player p)
	{
		for(Arena a : arenas)
		{
			if(a.getPlayersInArena().contains(p.getName())||a.getPlayersSpectating().contains(p.getName())||a.getPlayersInLobby().contains(p.getName()))
			{
				return a;
			}
		}
		return null;
	}
	
	public void addPlayer(Player p, String id)
	{
		Arena a = getArena(id);
		if(a==null)
		{
			ChatUtils.sendMessage(id+" is not a valid arena!", MessageLevel.ERROR, p);
			return;
		}
		
		if(!a.getState().canJoin())
		{
			ChatUtils.sendMessage("You cannot join this arena, it is "+getMessageForState(a.getState())+".", MessageLevel.WARNING, p); return;
		}//Makes sure arena exists and is joinable
		
		if(a.getPlayersInArena().size()==a.getMaxPlayers()) { ChatUtils.sendMessage("You cannot join this arena, it is full.", MessageLevel.WARNING, p); return; }
		
		PlayerJoinArenaEvent event = new PlayerJoinArenaEvent(p, a);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
		{
			
			if(a.getPlayersSpectating().contains(p.getName())) a.getPlayersSpectating().remove(p.getName());
			
			a.getPlayersInArena().add(p.getName());
			if(!locs.containsKey(p.getName())) locs.put(p.getName(), p.getLocation());
			
			teleport(p, a.getLocations().get(0));
			
			if(a.getMinPlayers()<=a.getPlayersInArena().size()&&!b&&a.getState().equals(GameState.IN_LOBBY))
			{
				int i = a.getReadyPlayers().size();
				if(i>=a.getMinReady())
				{
					b = true;
					a.startTimer(a.getTimer("StartCountdown"));
				}
			}
		}
	}
	
	public void setReady(Player p, boolean b, String id)
	{
		arenas.get(arenas.indexOf(id)).setReady(p, b);
	}
	
	private void teleport(Player p, Location l)
	{
		inv.put(p.getName(), p.getInventory().getContents());
		armor.put(p.getName(), p.getInventory().getArmorContents());
		
		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		
		p.teleport(l);
		
		p.setFireTicks(0);
	}
	
	private void teleport2(Player p, Location l)
	{
		
		p.getInventory().setContents(inv.get(p.getName()));
		p.getInventory().setArmorContents(armor.get(p.getName()));
		
		p.teleport(l);
		
		p.setFireTicks(0);
		
		locs.remove(p.getName());
		inv.remove(p.getName());
		armor.remove(p.getName());
	}
	
	public void addSpectator(Player p, String id)
	{
		Arena a = getArena(id);
		if(a==null)
		{
			ChatUtils.sendMessage(id+" is not a valid arena!", MessageLevel.ERROR, p);
			return;
		}
		
		if(a.getState()==GameState.EDIT||a.getState()==GameState.RESETTING||a.getState()==GameState.POST_GAME||a.getState()==GameState.DISABLED) {
			ChatUtils.sendMessage("You cannot spectate "+id+" right now, it is "+a.getState().getMessage(), MessageLevel.WARNING, p);
			return;
		}
		
		PlayerSpectateArenaEvent event = new PlayerSpectateArenaEvent(p,a);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
		{
			if(a.getPlayersInArena().contains(p.getName()))
			{
				a.getPlayersInArena().remove(p.getName());
				a.getPlayersSpectating().add(p.getName());
				teleport(p, a.getLocations().get(0));
				return;
			}
			
			locs.put(p.getName(), p.getLocation());
			a.getPlayersSpectating().add(p.getName());
			teleport(p, a.getLocations().get(0));
		}
		
	}
	
	private String getMessageForState(GameState s)
	{
		return s.getMessage();
	}
	
	public void removePlayer(Player p)
	{
		Arena a = null;
		for(Arena ar : arenas)
		{
			if(ar.getPlayersInArena().contains(p.getName()))
			{
				a = ar;
			}
		}
		if(a==null||!a.getPlayersInArena().contains(p.getName()))
		{
			ChatUtils.sendMessage("Invalid Operation.", MessageLevel.ERROR, p);
			return;
		}
		PlayerLeaveArenaEvent event = new PlayerLeaveArenaEvent(p, a);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		if(!a.getPlayersToRollback().contains(p.getName())&&a.getPlayersInArena().contains(p.getName())) a.getPlayersToRollback().add(p.getName());
		
		if(!event.isCancelled())
		{
			teleport2(p, locs.get(p.getName()));
			locs.remove(p.getName());
			
			if(!a.getPlayersSpectating().contains(p.getName()))
			{
				a.getReadyPlayers().remove(p.getName());
				a.getPlayersInArena().remove(p.getName());
			}
			else
			{
				a.getPlayersSpectating().remove(p.getName());
			}
			
			
			
			if(a.getMinPlayers()>a.getPlayersInArena().size()&&a.getState().equals(GameState.IN_LOBBY)&&!(a.getPlayersSpectating().contains(p.getName())))
			{
				int i = a.getReadyPlayers().size();
				if(i<a.getMinReady()||a.getMinPlayers()>a.getPlayersInArena().size())
				{
					b = false;
					a.stopTimer(a.getTimer("StartCountdown"));
				}
			}
		}
	}
	
	public boolean isSpectator(Player p, String id)
	{
		if(getArena(id).getPlayersSpectating().contains(p.getName()))
		{
			return true;
		}
		else return false;
	}
	
	public boolean isPlayer(Player p)
	{
		for(Arena a : arenas)
		{
			if(a.getPlayersInArena().contains(p.getName())) return true;
		}
		return false;
	}
	
	public boolean isPlayer(Player p, String id)
	{
		if(getArena(id).getPlayersInArena().contains(p.getName()))
		{
			return true;
		}
		else return false;
	}
	
	public boolean isSpectator(Player p)
	{
		for(Arena a : arenas)
		{
			if(a.getPlayersSpectating().contains(p.getName())) return true;
		}
		return false;
	}
	
	public ArrayList<Arena> getArenas()
	{
		return arenas;
	}
	
	public Arena createArena(ArrayList<Location> locs, String id, int m, int f, int mr, MiniGame mg)
	{
		
		Arena a = new ArenaImpl(locs, id, m, f, mr, c, mg);
		ArenaCreateEvent event = new ArenaCreateEvent(a);
		if(!event.isCancelled())
		{
			arenas.add(a);
			return a;
		}
		else return null;
	}
	
	public Arena createArena(Player p)
	{
		return ArenaCreationConversation.getInstance().createArena(p);
	}
	
	public boolean isInGame(Player p)
	{
		for(Arena a : arenas)
		{
			if(a.getPlayersInArena().contains(p.getName()))
			{
				return true;
			}
		}
		return false;
	}
	
	public String serializeLoc(Location l){
        return l.getWorld().getName()+","+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ();
    }
	
    public Location deserializeLoc(String s){
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
	
    public boolean deleteArena(Arena arena)
    {
    	for(Arena a : arenas)
    	{
    		if(a==arena)
    		{
    			for(String p : a.getPlayersInArena())
    			{
    				removePlayer(Bukkit.getPlayer(p)); //TODO convert all stuff to UUID if necessary
    			}
    			arenas.remove(a);
    			a = null; //Do this to make sure garbage collector in Java gets it
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean editArena(Arena arena)
    {
    	for(Arena a: arenas)
    	{
    		if(a==arena) { a.setState(GameState.EDIT); return true; }
    	}
    	return false;
    }
}
