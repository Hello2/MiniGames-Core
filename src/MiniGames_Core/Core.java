package com.wundero.MiniGames_Core;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.wundero.MiniGames_Core.Arena.Arena;
import com.wundero.MiniGames_Core.Arena.ArenaManager;
import com.wundero.MiniGames_Core.Handlers.Commands;
import com.wundero.MiniGames_Core.api.MiniGameAPI;


public class Core extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public final static Logger loggerTwo = Logger.getLogger("Minecraft");
	
	private MiniGameAPI mga;
	private FileConfiguration conf = getConfig();
	
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
	}
	
	public static void registerListener(Listener l)
	{
		Core c = (Core) Bukkit.getServer().getPluginManager().getPlugin("MiniGames-Core");
		Bukkit.getServer().getPluginManager().registerEvents(l, c);
	}
	
	public MiniGameAPI getAPI()
	{
		return mga;
	}
	
	public static Core getCore()
	{
		if(c==null)
		{
			c = (Core) Bukkit.getServer().getPluginManager().getPlugin("MiniGames-Core");
		}
		return c;
	}
	
	public void resetArena(int timeOfGame, Arena a)
	{
		CoreProtectAPI cp = getCoreProtect();
		ArrayList<Location> locs = a.getLocations();
		//TODO make sure entire arena is rolled back
		double x,y,z;
		x = (locs.get(1).getX() - locs.get(0).getX())/2;
		y = (locs.get(1).getY() - locs.get(0).getY())/2;
		z = (locs.get(1).getZ() - locs.get(0).getZ())/2;
		
		int r;
		int mx,mz;
		mx = (int) Math.abs(locs.get(0).getX())+(int)Math.abs(locs.get(1).getX());
		mz = (int) Math.abs(locs.get(0).getZ())+(int)Math.abs(locs.get(1).getZ());
		r = Math.max(mx, mz);
		
		
		Location l = new Location(a.getLocations().get(0).getWorld(), x, y ,z);
		cp.performRollback(null, timeOfGame, r, l, null, null);
		
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
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		boolean ret = Commands.execCommand(sender, cmd, label, args, this);
		return ret;
	}
}
