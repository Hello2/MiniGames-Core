package com.wundero.MiniGames_Core.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamG {
	private static ArrayList<TeamG> allTeams = new ArrayList<TeamG>();
	private static HashMap<String, TeamG> playerTeams = new HashMap<String, TeamG>();
	
	private String teamName;
	
	private Team t;

	private TeamG(String tn, Scoreboard o)
	{
		this.teamName = tn.trim();
		allTeams.add(this);
		setScoreboardTeam(o.registerNewTeam(tn));
	}
	public TeamG(String[] teamNames, Scoreboard s) //TODO IMPROVE, THIS IS NOT READY YET.
	{
		for(String st : teamNames)

			new TeamG(st, s);
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
	
	public static TeamG getTeam(Player p)
	{
		if(!hasTeam(p))
		{
			return null;
		}
		return playerTeams.get(p.getName());
	}
	
	public static ArrayList<TeamG> getTeams()
	{
		return allTeams;
	}
	public static ArrayList<TeamG> getAllTeams()
	{
		return getTeams();
	}
	
	public static TeamG getTeam(String name)
	{
		for(TeamG t: allTeams)
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
