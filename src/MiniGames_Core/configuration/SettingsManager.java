package com.wundero.MiniGames_Core.configuration;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.wundero.MiniGames_Core.Core;

public class SettingsManager {
	
	/*
	 * Config setup (arenaname.yml):
	 * 
	 * id: String
	 * max-players: int
	 * min-players: int
	 * arena-type: int #DO NOT TOUCH, WILL BREAK ARENA. TO CHANGE ARENA TYPE, DO IT THROUGH EDIT MODE.
	 * teams: String[]
	 * max-game-time: int #in seconds
	 * lobby-countdown: int #Starts when min players is reached. in seconds
	 * end-game-countdown: int #When a game ends, this timer is triggered and when it ends, everyone is kicked out.
	 * can-break: default #Can be true, false or default. If it is default, it will inherit from minigame type.
	 * player-time: default #can be day, night, default, or minigame. If default, uses normal player time. if minigame, uses minigame specification
	 * objective: String #Can be anything, will be displayed when you get arena info
	 * authors: String[] #A list of authors. To add their contributions, do [authorname] - [contribution]
	 * rules: String[] #A list of rules. Do not add numbers, they will be given a number format on display
	 * 
	 * locations: ArrayList<Location> #DO NOT TOUCH, WILL BREAK ARENA. TO CHANGE ARENA COORDINATES, DO SO THROUGH EDIT MODE.
	 */
	
	
	
	
	private SettingsManager() {}
	
	private static SettingsManager sm;
	
	private File file;
	
	private ArrayList<Config> configs = new ArrayList<Config>();
	private ArrayList<FileConfiguration> confs = new ArrayList<FileConfiguration>(); //TODO only use one
	
	public static SettingsManager getSettingsManager()
	{
		if(sm==null)
		{
			sm = new SettingsManager();
		}
		return sm;
	}
	
	
	
	public void setup(Core c)
	{
		//TODO add configs
		
		if(!c.getDataFolder().exists()) c.getDataFolder().mkdir();
		
		for(Config conf : configs)
		{
			file = new File(c.getDataFolder(), conf.getFileName());
			if(!file.exists())
			{
				try
				{
					file.createNewFile();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}
			}
			
			confs.add(YamlConfiguration.loadConfiguration(file));
		}
	}
	
	public Object getValue(String configName, String path)
	{
		for(FileConfiguration conf : confs)
		{
			if(configName==conf.getName())
			{
				return conf.get(path); //TODO add safety check
			}
		}
		return null;
	}
	
	public void set(String configName, String path, Object value)
	{
		for(FileConfiguration conf : confs)
		{
			if(configName==conf.getName())
			{
				try
				{
					conf.set(path, value);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}
				return;
			}
		}
	}
	
}
