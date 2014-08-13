package com.wundero.MiniGames_Core.misc_multiple;

import org.bukkit.entity.Player;

import com.wundero.MiniGames_Core.minigame.MGStat;
import com.wundero.MiniGames_Core.visualization.Visualization;

public class PlayerData {
	
	private String name;
	
	private MGStat stats;
	
	private int coins;
	
	private Visualization currentVisualization;
	
	public PlayerData(Player p)
	{
		name = p.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MGStat getStats() {
		return stats;
	}

	public void setStats(MGStat stats) {
		this.stats = stats;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public Visualization getCurrentVisualization() {
		return currentVisualization;
	}

	public void setCurrentVisualization(Visualization currentVisualization) {
		this.currentVisualization = currentVisualization;
	}
	
}
