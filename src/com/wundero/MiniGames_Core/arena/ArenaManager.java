package com.wundero.MiniGames_Core.Arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.wundero.MiniGames_Core.Type;
import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.GameState;
import com.wundero.MiniGames_Core.MessageLevel;
import com.wundero.MiniGames_Core.Events.ArenaCreateEvent;
import com.wundero.MiniGames_Core.Events.PlayerJoinArenaEvent;
import com.wundero.MiniGames_Core.Events.PlayerLeaveArenaEvent;
import com.wundero.MiniGames_Core.Events.PlayerSpectateArenaEvent;
import com.wundero.MiniGames_Core.Utils.ChatUtils;

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
	
	public void hook(Core core)//Hooks to core, unused method at the moment
	{
		c = core;
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
			if(a.getPlayers().contains(p.getName())||a.getSpectators().contains(p.getName()))
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
			ChatUtils.sendMessage(p, id+" is not a valid arena!", MessageLevel.ERROR);
			return;
		}
		
		if(!a.getState().canJoin())
		{
			ChatUtils.sendMessage(p, "You cannot join this arena, it is "+getMessageForState(a.getState())+".", MessageLevel.WARNING); return;
		}//Makes sure arena exists and is joinable
		
		if(a.getPlayers().size()==a.getMaxPlayers()) { ChatUtils.sendMessage(p, "You cannot join this arena, it is full.", MessageLevel.WARNING); return; }
		
		PlayerJoinArenaEvent event = new PlayerJoinArenaEvent(p, a);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
		{
			a.getPlayers().add(p.getName());
			locs.put(p.getName(), p.getLocation());
			
			teleport(p, a.getLocations().get(0));
			
			a.getReady().put(p.getName(), false);
			
			if(a.getMinPlayers()<=a.getPlayers().size()&&!b&&a.getState().equals(GameState.IN_LOBBY))
			{
				int i = 0;
				for(Boolean bool : a.getReady().values())
				{
					if(bool)
					{
						i++;
					}
				}
				if(i>a.getMinReady())
				{
					b = true;
					a.startCountdown();
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
	
	public void addSpectator(Player p, String id)
	{
		Arena a = getArena(id);
		if(a==null)
		{
			ChatUtils.sendMessage(p, id+" is not a valid arena!", MessageLevel.ERROR);
			return;
		}
		
		if(a.getState()==GameState.EDIT||a.getState()==GameState.RESETTING||a.getState()==GameState.POST_GAME||a.getState()==GameState.DISABLED) {
			ChatUtils.sendMessage(p, "You cannot spectate "+id+" right now, it is "+a.getState().getMessage(), MessageLevel.WARNING);
			return;
		}
		
		PlayerSpectateArenaEvent event = new PlayerSpectateArenaEvent(p,a);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
		{
			locs.put(p.getName(), p.getLocation());
			a.getSpectators().add(p.getName());
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
			if(ar.getPlayers().contains(p.getName()))
			{
				a = ar;
			}
		}
		if(a==null||!a.getPlayers().contains(p.getName()))
		{
			ChatUtils.sendMessage(p, "Invalid Operation.", MessageLevel.ERROR);
			return;
		}
		PlayerLeaveArenaEvent event = new PlayerLeaveArenaEvent(p, a);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
		{
			teleport(p, locs.get(p.getName()));
			locs.remove(p.getName());
			
			if(!a.getSpectators().contains(p.getName()))
			{
				a.getReady().remove(p.getName());
			}
			
			if(a.getMinPlayers()>a.getPlayers().size()&&a.getState().equals(GameState.IN_LOBBY)&&!(a.getSpectators().contains(p.getName())))
			{
				int i = 0;
				for(Boolean bool : a.getReady().values())
				{
					if(bool)
					{
						i++;
					}
				}
				if(i<a.getMinReady()||a.getMinPlayers()>a.getPlayers().size())
				{
					b = false;
					a.stopCountdown();
				}
			}
		}
	}
	
	public boolean isSpectator(Player p, String id)
	{
		if(getArena(id).getSpectators().contains(p.getName()))
		{
			return true;
		}
		else return false;
	}
	
	public boolean isSpectator(Player p)
	{
		for(Arena a : arenas)
		{
			if(a.getSpectators().contains(p.getName())) return true;
		}
		return false;
	}
	
	public ArrayList<Arena> getArenas()
	{
		return arenas;
	}
	
	public Arena createArena(ArrayList<Location> locs, ArrayList<Location> locs2, String id, Type r, int m, int f, int mr)
	{
		
		Arena a = new Arena(locs, locs2, id, r, m, f, mr, c);
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
		//TODO add conversation
		return null;
	}
	
	public boolean isInGame(Player p)
	{
		for(Arena a : arenas)
		{
			if(a.getPlayers().contains(p.getName()))
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
    			for(String p : a.getPlayers())
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
    		if(a==arena) { a.edit(); return true; }
    	}
    	return false;
    }
}
