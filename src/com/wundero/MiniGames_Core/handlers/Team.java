package com.wundero.MiniGames_Core.Handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class Team {
	private static ArrayList<Team> allTeams = new ArrayList<Team>();
	private static HashMap<String, Team> playerTeams = new HashMap<String, Team>();
	
	private String teamName;
	
	private Team(String tn)
	{
		this.teamName = tn.trim();
		allTeams.add(this);
	}
	public Team(String[] teamNames)
	{
		for(String s : teamNames)
			new Team(s);
	}
	
	
	public String getName()
	{
		return teamName;
	}
	
	public void addPlayer(Player p)
	{
		playerTeams.put(p.getName(), this);
	}
	
	public boolean removePlayer(Player p)
	{
		if(!hasTeam(p))
		{
			return false;
		}
		playerTeams.remove(p.getName());
		return true;
	}
	
	public static boolean hasTeam(Player p)
	{
		return playerTeams.containsKey(p.getName());
	}
	
	public static Team getTeam(Player p)
	{
		if(!hasTeam(p))
		{
			return null;
		}
		return playerTeams.get(p.getName());
	}
	
	public static ArrayList<Team> getTeams()
	{
		return allTeams;
	}
	public static ArrayList<Team> getAllTeams()
	{
		return getTeams();
	}
	
	public static Team getTeam(String name)
	{
		for(Team t: allTeams)
		{
			if(t.getName().equalsIgnoreCase(name))
			{
				return t;
			}
		}
		return null;
	}
	
	public int getID()
	{
		for(int i = 0; i<allTeams.size(); i++)
		{
			if(this==allTeams.get(i))
			{
				return i;
			}
		}
		return -1;
	}
}
