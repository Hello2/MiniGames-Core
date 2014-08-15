package com.wundero.MiniGames_Core.handlers;

public class MGObjective
{
	private int number;
	private String name;
	private MGObj type;
	private boolean decrement;
	
	public MGObjective(int n, String na, MGObj t, boolean dec)
	{
		setNumber(n);
		setName(na);
		setType(t);
		setDecrement(dec);
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void incOrDecNumber() {
		if(decrement) number--; else number++;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public MGObj getType() {
		return type;
	}
	
	public void setType(MGObj type) {
		this.type = type;
	}
	
	public boolean isDecrementing() {
		return decrement;
	}
	
	public void setDecrement(boolean decrement) {
		this.decrement = decrement;
	}
}
