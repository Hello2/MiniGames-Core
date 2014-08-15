package com.wundero.MiniGames_Core.minigame;

import java.util.HashMap;

import com.wundero.MiniGames_Core.handlers.Kit;
import com.wundero.MiniGames_Core.handlers.MGObjective;

public interface MiniGame
{
	String name();
	void setName(String name);
	String info();
	void setInfo(String info);
	MGObjective objective();
	void setObjective(MGObjective obj);
	HashMap<String, Object> getPvPSettings();
	void setPvPSettings(HashMap<String, Object> settings);
	HashMap<String, Object> getGameplaySettings();
	void setGameplaySettings(HashMap<String, Object> settings);
	HashMap<String, Kit> getKits();
	void setKits(HashMap<String, Kit> kits);
	//TODO add more stuff. This is a must before the plugin can work
}


//package com.wundero.MiniGames_Core.minigame;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.wundero.MiniGames_Core.handlers.GameType;
//import com.wundero.MiniGames_Core.handlers.MGObjective;
//
//public class MiniGame {
//  private String name, info;
//  private ArrayList<String> teamNames = new ArrayList<String>();
//  private GameType base;
//  private HashMap<String, Boolean> enabled = new HashMap<String, Boolean>();
//  private double pvpDamage;
//  private int pvpHealth, lives, teamNum, teamLives;
//  private MGObjective obj;
//  
//  public MiniGame(String name, String info, ArrayList<String> teamNames, GameType base, Map<String, Boolean> enabled, double pvpDamage, int pvpHealth, int teamLives, int lives, int teamNum, MGObjective obj)
//  {
//	  this.setName(name);
//	  this.setInfo(info);
//	  this.setTeamNames(teamNames);
//	  this.setBase(base);
//	  this.setEnabled((HashMap<String, Boolean>) enabled);
//	  this.setPvpDamage(pvpDamage);
//	  this.setPvpHealth(pvpHealth);
//	  this.setLives(lives);
//	  this.setTeamNum(teamNum);
//	  this.setTeamLives(teamLives);
//	  this.setObj(obj);
//  }
//  //TODO methods
//
//public String getName() {
//	return name;
//}
//
//public void setName(String name) {
//	this.name = name;
//}
//
//public String getInfo() {
//	return info;
//}
//
//public void setInfo(String info) {
//	this.info = info;
//}
//
//public ArrayList<String> getTeamNames() {
//	return teamNames;
//}
//
//public void setTeamNames(ArrayList<String> teamNames) {
//	this.teamNames = teamNames;
//}
//
//public GameType getBase() {
//	return base;
//}
//
//public void setBase(GameType base) {
//	this.base = base;
//}
//
//public HashMap<String, Boolean> getEnabled() {
//	return enabled;
//}
//
//public void setEnabled(HashMap<String, Boolean> enabled) {
//	this.enabled = enabled;
//}
//
//public double getPvpDamage() {
//	return pvpDamage;
//}
//
//public void setPvpDamage(double pvpDamage) {
//	this.pvpDamage = pvpDamage;
//}
//
//public int getPvpHealth() {
//	return pvpHealth;
//}
//
//public void setPvpHealth(int pvpHealth) {
//	this.pvpHealth = pvpHealth;
//}
//
//public int getLives() {
//	return lives;
//}
//
//public void setLives(int lives) {
//	this.lives = lives;
//}
//
//public int getTeamNum() {
//	return teamNum;
//}
//
//public void setTeamNum(int teamNum) {
//	this.teamNum = teamNum;
//}
//
//public int getTeamLives() {
//	return teamLives;
//}
//
//public void setTeamLives(int teamLives) {
//	this.teamLives = teamLives;
//}
//
//public MGObjective getObj() {
//	return obj;
//}
//
//public void setObj(MGObjective obj) {
//	this.obj = obj;
//}
//}
