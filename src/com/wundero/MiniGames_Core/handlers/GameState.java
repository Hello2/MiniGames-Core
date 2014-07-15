package com.wundero.MiniGames_Core.handlers;

public enum GameState {
	
	IN_LOBBY(true, "in lobby"), IN_GAME(false, "in game"), POST_GAME(false, "ending"), RESETTING(false, "resetting"), DISABLED(false, "disabled"), EDIT(false, "in edit mode");
	//Those store the state of an arena, whether it is joinable and the message
	
	private boolean canJoin;
	private String msg;
	
	private static GameState currentState;
	
	GameState(boolean canJoin, String msg)//Constructor
	{
		this.canJoin = canJoin;
		this.msg = msg;
	}
	
	public boolean canJoin()//Gets if you can join
	{
		return canJoin;
	}
	
	public static void setState(GameState state)//Changes the state
	{
		GameState.currentState = state;
	}
	
	public static boolean isState(GameState state)//Checks the state
	{
		return GameState.currentState == state;
	}
	
	public static GameState getState()//Gets the state
	{
		return GameState.currentState;
	}
	
	public String getMessage()//gets the message
	{
		return msg;
	}
	
}
