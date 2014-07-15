package com.wundero.MiniGames_Core;

public enum GameState {
	
	IN_LOBBY(true, "in lobby"), IN_GAME(false, "in game"), POST_GAME(false, "ending"), RESETTING(false, "resetting"), DISABLED(false, "disabled"), EDIT(false, "in edit mode");
	
	private boolean canJoin;
	private String msg;
	
	private static GameState currentState;
	
	GameState(boolean canJoin, String msg)
	{
		this.canJoin = canJoin;
		this.msg = msg;
	}
	
	public boolean canJoin()
	{
		return canJoin;
	}
	
	public static void setState(GameState state)
	{
		GameState.currentState = state;
	}
	
	public static boolean isState(GameState state)
	{
		return GameState.currentState == state;
	}
	
	public static GameState getState()
	{
		return GameState.currentState;
	}
	
	public String getMessage()
	{
		return msg;
	}
	
}
