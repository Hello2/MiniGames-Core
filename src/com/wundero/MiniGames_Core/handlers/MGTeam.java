package com.wundero.MiniGames_Core.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class MGTeam {
	private static ArrayList<MGTeam> allTeams = new ArrayList<MGTeam>();
	private static HashMap<String, MGTeam> playerTeams = new HashMap<String, MGTeam>();
	
	private String teamName;
	
	private Team t;
	
	private MGTeam(String tn, Scoreboard s, Objective o)
	{
		this.teamName = tn.trim();
		allTeams.add(this);
		setScoreboardTeam(s.registerNewTeam(tn));
		o.getScore((this.teamName));
	}
	public MGTeam(String[] teamNames, Scoreboard s, Objective o) //TODO IMPROVE, THIS IS NOT READY YET.
	{
		for(String st : teamNames)

			new MGTeam(st, s, o);
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
	
	public static MGTeam getTeam(Player p)
	{
		if(!hasTeam(p))
		{
			return null;
		}
		return playerTeams.get(p.getName());
	}
	
	public static ArrayList<MGTeam> getTeams()
	{
		return allTeams;
	}
	public static ArrayList<MGTeam> getAllTeams()
	{
		return getTeams();
	}
	
	public static MGTeam getTeam(String name)
	{
		for(MGTeam t: allTeams)
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
	/**
	 * @return the t
	 */
	public Team getScoreboardTeam() {
		return t;
	}
	/**
	 * @param t the t to set
	 */
	public void setScoreboardTeam(Team t) {
		this.t = t;
	}
}
