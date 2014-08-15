package com.wundero.MiniGames_Core.utils;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtils {
	public static Player[] getPlayerFromString(String... player)
	{
		Player[] players = new Player[player.length];
		int i = 0;
		for(String s : player)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(p.getName().equals(s)) players[i] = p;
			}
			i++;
		}
		return players[0]==null?null:players;
	}
	
	public static Player[] getPlayersFromList(List<String> players)
	{
		Player[] player = new Player[players.size()];
		int i = 0;
		for(String s : players)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(s.equals(p.getName()))
				{
					player[i] = p;
				}
			}
			i++;
		}
		return player[0]==null?null:player;
	}
	public static Player[] getPlayersFromSet(Set<String> players)
	{
		Player[] player = new Player[players.size()];
		int i = 0;
		for(String s : players)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(s.equals(p.getName()))
				{
					player[i] = p;
				}
			}
			i++;
		}
		return player[0]==null?null:player;
	}
}
