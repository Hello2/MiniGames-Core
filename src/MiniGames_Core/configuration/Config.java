package com.wundero.MiniGames_Core.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class Config {
	
	//TODO add methods
	
	public abstract String getFileName(); //Includes filetype - example: config.yml
	
	public abstract File getFile();
	
	public abstract FileConfiguration getFileConfiguration();
	
	
	
}
