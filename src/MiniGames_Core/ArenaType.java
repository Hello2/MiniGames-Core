package com.wundero.MiniGames_Core;

//TODO Make better

public enum ArenaType {
	TEAM_DEATH_MATCH("Team Deathmatch", true),
	FREE_FOR_ALL("Free for All", true),
	CAPTURE_THE_FLAG("Capture the Flag", true),
	MAN_HUNT("Manhunt", true),
	MOB_ARENA("Mob Arena", true),
	SPLEEF("Spleef", true),
	PRISON_BREAK("Prison Break", true),
	HUNGER_GAMES("Survival Games", true),
	BLOCK_HUNT("Block Hunt", true),
	RACE("Race", true),
	PARKOUR("Parkour", true),
	SIEGE("Siege", true),
	PICTONARY("Build My Thing", true),
	COLOR_MATCH("Colour Match", true),
	CUSTOM("Custom Gamemode", true),
	SMOKE_MONSTER("Smoke Monster", true);
	
	private String name;
	private boolean enabled;
	
	ArenaType(String name, boolean b)
	{
		this.name = name;
		this.enabled = b;
	}
	
	public String getGame()
	{
		return name;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean b)
	{
		this.enabled = b;
	}
}
