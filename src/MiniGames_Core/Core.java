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

import com.wundero.MiniGames_Core.Arena.Arena;
import com.wundero.MiniGames_Core.Arena.ArenaManager;
import com.wundero.MiniGames_Core.api.MiniGameAPI;
import com.wundero.MiniGames_Core.commands.CommandsManager;

/*
 * MiniGames-Core - A bukkit plugin
 * TODO add licensing
 */

public class Core extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public final static Logger loggerTwo = Logger.getLogger("Minecraft");
	
	private MiniGameAPI mga;
	private FileConfiguration conf = getConfig();
	
	private CommandsManager cm;
	
	private static Core c;
	
	@Override
	public void onDisable()
	{
		this.saveConfig();
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName()+" has been disabled!");
	}
	
	@Override
	public void onEnable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName()+" version "+pdfFile.getVersion()+" has been enabled!");
		if(getConfig()==null)
		{
			this.saveDefaultConfig();
		}
		this.reloadConfig();
		
		CoreProtectAPI coreProtect = getCoreProtect();
		if(coreProtect!=null)
		{
			coreProtect.testAPI(); 
		}
		
		mga = MiniGameAPI.get(this);
		
		cm = CommandsManager.getCommandsManager();
		cm.setup();
		getCommand("minigame").setExecutor(cm); //TODO make sure this works with aliases like /mg and /minigames
		
		//This part will check for other plugins that handle minigames and ask for an override choice - this or those plugins. This plugins other parts will function normally.
		if(getServer().getPluginManager().getPlugin("MobArena")!=null)
		{
			//TODO add logic for overrides - config to override defaults to TRUE, not false.
		}
		if(getServer().getPluginManager().getPlugin("BattleArena")!=null) //TODO add other minigame plugins here
		{
			//TODO add logic for overrides - config to override defaults to TRUE, not false.
		}
	}
	
	
	
	public static void registerStaticListener(Listener l)
	{
		Core c = (Core) Bukkit.getServer().getPluginManager().getPlugin("MiniGames-Core");
		Bukkit.getServer().getPluginManager().registerEvents(l, c);
	}
	
	public void registerListener(Listener l)
	{
		getServer().getPluginManager().registerEvents(l, this);
	}
	
	public MiniGameAPI getAPI()
	{
		if(!mga.isEnabled()) return null;
		return mga;
	}
	
	public static Core getCore()
	{
		if(c==null)
		{
			c = (Core) Bukkit.getServer().getPluginManager().getPlugin("MiniGames-Core");
			if(c==null||!(c instanceof Core)) return null;
		}
		return c;
	}
	
	public void resetArena(int timeOfGame, Arena a, ArrayList<String> players)
	{
		CoreProtectAPI cp = getCoreProtect();
		for(String p : players)
		{
			cp.performRollback(p, timeOfGame, 0, null, null, null);
		}
		
	}
	
	private CoreProtectAPI getCoreProtect()
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
	
	
	public static Logger getPluginLogger()
	{
		return loggerTwo;
	}
	
//	public void setupConfig()
//	{
//		
//	}
	
	public void registerKits(){
		//TODO get kits from files
		//TODO store kit info
	}
	
//	public void registerListeners() // - NOTE - No longer required - register listener is better method - add back if necessary
//	{
//		PluginManager pm = getServer().getPluginManager();
//		pm.registerEvents(new PlayerJoinListener(this), this);
//		//TODO add more listeners as more are made
//	}
	
	public FileConfiguration getPluginConfig()
	{
		return conf;
	}
	
	public static void start(Arena a)
	{
		ArenaManager.getArenaManager().getArena(a.getID()).startCountdown();
	}
	public static void stop(Arena a)
	{
		ArenaManager.getArenaManager().getArena(a.getID()).endArena();
	}
	
}
