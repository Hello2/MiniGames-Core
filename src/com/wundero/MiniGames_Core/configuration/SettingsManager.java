package com.wundero.MiniGames_Core.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.arena.Arena;

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
	
	
	private File adir;
	private File mdir;
	
	private SettingsManager() {}
	
	private ArrayList<String> files = new ArrayList<String>();
	
	private static SettingsManager sm;
	
	private File file;
	
	private Core core;
	
	private ArrayList<FileConfiguration> confs = new ArrayList<FileConfiguration>(); //TODO only use one
	
	public static SettingsManager getSettingsManager()
	{
		if(sm==null)
		{
			sm = new SettingsManager();
		}
		return sm;
	}
	
	public void disable()
	{
		for(FileConfiguration conf : confs)
		{
			file = new File(conf.getCurrentPath(), conf.getName());
			try {
				conf.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//TODO add more?
	}
	
	public void setup(Core c)
	{
		this.core = c;
		
		//TODO add configs
		
		if(!c.getDataFolder().exists()) c.getDataFolder().mkdir();//Makes directory
		
		adir = new File(c.getDataFolder(), "arenas");
		mdir = new File(c.getDataFolder(), "minigames");
		
		if(!adir.exists()) adir.mkdir();
		if(!mdir.exists()) mdir.mkdir();//Makes additional arena&minigame directories
		
		for(String s : files)
		{
			
			if(s.substring(s.indexOf('-')).equalsIgnoreCase("-arena.yml")) file = new File(adir, s);
			else if(s.substring(s.indexOf('-')).equalsIgnoreCase("-minigame.yml")) file = new File(mdir, s);
			else file = new File(c.getDataFolder(), s);
			
			
			
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
	
	public boolean createConfig(String configName)
	{
		if(configName.substring(configName.indexOf('-')).equalsIgnoreCase("-arena.yml"))
		{
			adir = new File(core.getDataFolder(), "arenas");
			file = new File(adir, configName);
			if(!file.exists())
			{
				try
				{
					file.createNewFile();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return false;
				}
			}
			confs.add(YamlConfiguration.loadConfiguration(file));
			return true;
		}
		else if(configName.substring(configName.indexOf('-')).equalsIgnoreCase("-minigame.yml"))
		{
			mdir = new File(core.getDataFolder(), "minigames");
			file = new File(mdir, configName);
			if(!file.exists())
			{
				try
				{
					file.createNewFile();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return false;
				}
			}
			confs.add(YamlConfiguration.loadConfiguration(file));
			return true;
		}
		else
		{
			file = new File(core.getDataFolder(), configName);
			if(!file.exists())
			{
				try
				{
					file.createNewFile();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return false;
				}
			}
			confs.add(YamlConfiguration.loadConfiguration(file));
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(String configName, String path)
	{
		for(FileConfiguration conf : confs)
		{
			if(configName==conf.getName())
			{
				return (T) conf.get(path);
			}
		}
		return null;
	}
	
	private File getFile(String name)
	{
		for(FileConfiguration conf : confs)
		{
			if(conf.getName().equalsIgnoreCase(name))
			{
				if(name.substring(name.indexOf('-')).equalsIgnoreCase("-minigame.yml"))
				{
					return new File(mdir, name);
				}
				else if(name.substring(name.indexOf('-')).equalsIgnoreCase("-arena.yml"))
				{
					return new File(adir, name);
				}
				else
				{
					return new File(core.getDataFolder(), name);
				}
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
				conf.set(path, value);
				if(getFile(configName)==null) return;
				
				try
				{
					conf.save(getFile(configName));
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
	
	public void saveArenaInfo(Arena a)
	{
		if(a==null) return;
		
		adir = new File(core.getDataFolder(), "arenas");
		for(File f : adir.listFiles())
		{
			if(f.getName().equalsIgnoreCase(a.getID()+"-arena.yml"))
			{
				//TODO write arena info to file
				
				//TODO save file
				return;
			}
		}
		
		createConfig(a.getID()+"-arena.yml");
		//TODO write to file
		//TODO save file
	}
	
	public void saveMinigameInfo()
	{
		//TODO do this ;D
	}
}
