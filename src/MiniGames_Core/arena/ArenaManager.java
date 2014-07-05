package com.wundero.MiniGames_Core.Arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.wundero.MiniGames_Core.ArenaType;
import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.GameState;
import com.wundero.MiniGames_Core.Events.ArenaCreateEvent;
import com.wundero.MiniGames_Core.Events.PlayerJoinArenaEvent;
import com.wundero.MiniGames_Core.Events.PlayerLeaveArenaEvent;
import com.wundero.MiniGames_Core.Utils.ChatUtils;

public class ArenaManager {
	
	//TODO Make class better
	
	public Map<String, Location> locs = new HashMap<String, Location>();
	
	private static ArenaManager am;
	
	private boolean b = false;
	
	private Core c;
	
	Map<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
	Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();
	
	ArrayList<Arena> arenas = new ArrayList<Arena>();
	
	private ArenaManager(){}
	
	public static ArenaManager getArenaManager()
	{
		if(am==null)
		{
			am = new ArenaManager();
		}
		
		return am;
	}
	
	public void hook(Core core)
	{
		c = core;
	}
	
	public Core getCore()
	{
		return c;
	}
	
	public Arena getArena(String id)
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
	

	
	public void addPlayer(Player p, String id)
	{
		Arena a = getArena(id);
		if(a==null)
		{
			ChatUtils.sendMessage(p, ChatColor.RED+id+" is not a valid arena!");
			return;
		}
		
		if(!a.getState().canJoin())
		{
			ChatUtils.sendMessage(p, ChatColor.RED+"You cannot join this arena, it is "+getMessageForState(a.getState())+"."); return;
		}
		
		if(a.getPlayers().size()==a.getMaxPlayers()) { ChatUtils.sendMessage(p, ChatColor.RED+"You cannot join this arena, it is full."); return; }
		
		PlayerJoinArenaEvent event = new PlayerJoinArenaEvent(p, a);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
		{
			a.getPlayers().add(p.getName());
			inv.put(p.getName(), p.getInventory().getContents());
			armor.put(p.getName(), p.getInventory().getArmorContents());
			
			p.getInventory().setArmorContents(null);
			p.getInventory().clear();
			
			locs.put(p.getName(), p.getLocation());
			p.teleport(a.getLocations().get(0));
			
			if(a.getMinPlayers()<=a.getPlayers().size()&&!b)
			{
				b = true;
				a.stopCountdown();
			}
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
			ChatUtils.sendMessage(p, ChatColor.RED+"Invalid Operation.");
			return;
		}
		PlayerLeaveArenaEvent event = new PlayerLeaveArenaEvent(p, a);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
		{
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			
			p.getInventory().setContents(inv.get(p.getName()));
			p.getInventory().setArmorContents(armor.get(p.getName()));
			
			inv.remove(p.getName());
			armor.remove(p.getName());
			
			p.teleport(locs.get(p.getName()));
			locs.remove(p.getName());
			
			p.setFireTicks(0);
			
			if(a.getMinPlayers()>a.getPlayers().size())
			{
				b = false;
				a.stopCountdown();
			}
		}
	}
	
	public Arena createArena(ArrayList<Location> locs, ArrayList<Location> locs2, String id, ArenaType r, int m, int f)
	{
		
		Arena a = new Arena(locs, locs2, id, r, m, f, c);
		ArenaCreateEvent event = new ArenaCreateEvent(a);
		if(!event.isCancelled())
		{
			arenas.add(a);
			return a;
		}
		else return null;
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
    			arenas.remove(a);
    			a = null;
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
