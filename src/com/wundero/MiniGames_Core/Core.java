package com.wundero.MiniGames_Core;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.wundero.MiniGames_Core.api.MiniGameAPI;
import com.wundero.MiniGames_Core.arena.Arena;
import com.wundero.MiniGames_Core.arena.ArenaManager;
import com.wundero.MiniGames_Core.commands.CommandsManager;
import com.wundero.MiniGames_Core.configuration.SettingsManager;

/*
 * MiniGames-Core - A bukkit plugin
 * TODO add licensing
 */

public class Core extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public final static Logger loggerTwo = Logger.getLogger("Minecraft"); //Two loggers, static one that can be retrieved by other classes
	
	private MiniGameAPI mga; //API for other plugins to hook into
	private FileConfiguration conf = getConfig(); //Idunno, will make this better
	
	private CommandsManager cm; //This is to register all the commands
	
	private static Core c; //Static instance to be able to get an instance with other classes
	
	@Override
	public void onDisable()
	{
		this.saveConfig();//Saves config. TODO move to configuration
		ArenaManager.getArenaManager().disable();
		SettingsManager.getSettingsManager().disable();
		
		PluginDescriptionFile pdfFile = this.getDescription();//Logs that the plugin is being disabled.
		this.logger.info(pdfFile.getName()+" has been disabled!");
	}
	
	public void reload()
	{
		//TODO kick all players out and reload config, do other stuff
	}
	
	@Override
	public void onEnable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();//Logs the enable
		this.logger.info(pdfFile.getName()+" version "+pdfFile.getVersion()+" has been enabled!");
		if(getConfig()==null)//If no config, create new one
		{
			this.saveDefaultConfig();
		}
		this.reloadConfig();//Reloads config, essentially a load
		
		CoreProtectAPI coreProtect = getCoreProtect();//Loads coreprotect to use for arena resets
		if(coreProtect!=null)
		{
			coreProtect.testAPI(); 
		}
		
		mga = MiniGameAPI.get(this);//Gets the API
		
		cm = CommandsManager.getCommandsManager();//sets up commands
		cm.setup();
		getCommand("minigame").setExecutor(cm); //TODO make sure this works with aliases like /mg and /minigames
		//^This sets the command executor as the commands manager
		
		//This part will check for other plugins that handle minigames and ask for an override choice - this or those plugins. This plugins other parts will function normally.
		if(getServer().getPluginManager().getPlugin("MobArena")!=null)
		{
			//TODO add logic for overrides - config to override defaults to TRUE, not false.
		}
		if(getServer().getPluginManager().getPlugin("BattleArena")!=null) //TODO add other minigame plugins here
		{
			//TODO add logic for overrides - config to override defaults to TRUE, not false.
		}
		
		SettingsManager.getSettingsManager().setup(this);
	}
	
	
	
	public static void registerStaticListener(Listener l)//Registers a listener with static reference
	{
		Core c = (Core) Bukkit.getServer().getPluginManager().getPlugin("MiniGames-Core");
		Bukkit.getServer().getPluginManager().registerEvents(l, c);//Registers listener with core
	}
	
	public void registerListener(Listener l)
	{
		getServer().getPluginManager().registerEvents(l, this);//Register listener with core instance
	}
	
	public MiniGameAPI getAPI()//Gets the API, this is what other plugins will use
	{
		if(!mga.isEnabled()) return null;
		return mga;
	}
	
	public static Core getCore()//Gets the core from a static reference
	{
		if(c==null)
		{
			c = (Core) Bukkit.getServer().getPluginManager().getPlugin("MiniGames-Core");
			if(c==null||!(c instanceof Core)) return null;
		}
		return c;
	}
	
	public void resetArena(int timeOfGame, Arena a, ArrayList<String> players)//resets arena based on who played in it and how long the game lasted
	{
		CoreProtectAPI cp = getCoreProtect();
		for(String p : players)
		{
			cp.performRollback(p, timeOfGame, 0, null, null, null);
		}
		
	}
	
	private CoreProtectAPI getCoreProtect()//returns coreprotect api
	{
		Plugin cp = getServer().getPluginManager().getPlugin("CoreProtect");
		if(cp==null||!(cp instanceof CoreProtect))
		{
			return null;
		}
		CoreProtectAPI CoreProtect = ( (CoreProtect) cp ).getAPI();
		if(CoreProtect.isEnabled()==false)
		{
			return null;
		}
		if(CoreProtect.APIVersion() <2)
		{
			return null;
		}
		return CoreProtect;
	}
	
	
	public static Logger getPluginLogger()//static logger getter
	{
		return loggerTwo;
	}
	
//	public void setupConfig()
//	{
//		//TODO register
//	}
	
	public void registerKits(){//will register kits
		//TODO get kits from files
		//TODO store kit info
	}
	
	
	public FileConfiguration getPluginConfig()//Gets config
	{
		return conf;
	}
	
	public static void start(Arena a)//Starts an arena, TODO make smoother
	{
		ArenaManager.getArenaManager().getArena(a.getID()).startCountdown();
	}
	public static void stop(Arena a)//Stops an arena, TODO make smoother
	{
		ArenaManager.getArenaManager().getArena(a.getID()).endArena();
	}
	
}
