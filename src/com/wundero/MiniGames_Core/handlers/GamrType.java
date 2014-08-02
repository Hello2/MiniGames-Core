package com.wundero.MiniGames_Core.handlers;

import java.util.ArrayList;

//TODO Make better

public enum GameType {
	TEAM_DEATH_MATCH("Team Deathmatch", true, "Teams duke it out in this PvP mini game. The teams, two or three of them, are pitted against each other and are to fight to the death."),
	FREE_FOR_ALL("Free for All", true, "It's every man for themselves in this PvP mini game. Every person must look out for themselves and remember that no one is your friend!"),
	CAPTURE_THE_FLAG("Capture the Flag", true, "Teams fight to capture each other flags to be declared victor!"),//TODO add more descriptions
	MAN_HUNT("Manhunt", true, ""),
	MOB_ARENA("Mob Arena", true, ""),
	SPLEEF("Spleef", true, ""),
	PRISON_BREAK("Prison Break", true, ""),
	HUNGER_GAMES("Survival Games", true, ""),
	BLOCK_HUNT("Block Hunt", true, ""),
	RACE("Race", true, ""),
	PARKOUR("Parkour", true, ""),
	SIEGE("Siege", true, ""),
	PICTIONARY("Build My Thing", true, ""),
	COLOR_MATCH("Colour Match", true, ""),
	CUSTOM("Custom Gamemode", true, ""),
	SMOKE_MONSTER("Smoke Monster", true, "");
	
	private String name;
	private String info;
	private static ArrayList<GameType> allTypes = new ArrayList<GameType>();
	private boolean enabled;
	
	
	
	GameType(String name, boolean b, String info)
	{
		this.name = name;
		this.enabled = b;
		this.info = info;
	}
	
	public static ArrayList<GameType> getAllTypes()
	{
		if(allTypes.isEmpty())
		{
			allTypes.add(TEAM_DEATH_MATCH);
			allTypes.add(FREE_FOR_ALL);
			allTypes.add(CAPTURE_THE_FLAG);
			allTypes.add(MAN_HUNT);
			allTypes.add(SPLEEF);
			allTypes.add(MOB_ARENA);
			allTypes.add(PRISON_BREAK);
			allTypes.add(HUNGER_GAMES);
			allTypes.add(BLOCK_HUNT);
			allTypes.add(RACE);
			allTypes.add(PARKOUR);
			allTypes.add(SIEGE);
			allTypes.add(PICTIONARY);
			allTypes.add(COLOR_MATCH);
			allTypes.add(CUSTOM);
			allTypes.add(SMOKE_MONSTER);
		}
		return allTypes;
	}
	
	public String getInfo()
	{
		return info;
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
