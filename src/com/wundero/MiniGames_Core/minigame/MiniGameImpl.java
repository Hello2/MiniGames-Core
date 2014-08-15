package com.wundero.MiniGames_Core.minigame;

import java.util.HashMap;

import com.wundero.MiniGames_Core.handlers.Kit;
import com.wundero.MiniGames_Core.handlers.MGObjective;

public class MiniGameImpl implements MiniGame {

	private String name;
	private String info;
	private MGObjective obj;
	private HashMap<String, Object> pvpSettings = new HashMap<String, Object>(), gameplaySettings = new HashMap<String, Object>();
	private HashMap<String, Kit> kits = new HashMap<String, Kit>();
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String info() {
		return info;
	}

	@Override
	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public MGObjective objective() {
		return obj;
	}

	@Override
	public void setObjective(MGObjective obj) {
		this.obj = obj;
	}

	@Override
	public HashMap<String, Object> getPvPSettings() {
		return pvpSettings;
	}

	@Override
	public void setPvPSettings(HashMap<String, Object> settings) {
		this.pvpSettings = settings;
	}

	@Override
	public HashMap<String, Object> getGameplaySettings() {
		return gameplaySettings;
	}

	@Override
	public void setGameplaySettings(HashMap<String, Object> settings) {
		this.gameplaySettings = settings;
	}

	@Override
	public HashMap<String, Kit> getKits() {
		return kits;
	}

	@Override
	public void setKits(HashMap<String, Kit> kits) {
		this.kits = kits;
	}

}
