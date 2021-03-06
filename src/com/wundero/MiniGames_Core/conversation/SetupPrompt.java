package com.wundero.MiniGames_Core.conversation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.configuration.SettingsManager;
import com.wundero.MiniGames_Core.utils.ChatUtils;

public class SetupPrompt extends FixedSetPrompt implements Listener {

	private String type;
	private boolean b;
	private int te;
	
	Map<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
	Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();//Players inventories and armor - TODO store XP
	
	ArrayList<Location> locs = new ArrayList<Location>(22);
	
	private ItemStack[] tools = new ItemStack[9];
	
	public SetupPrompt(String type)
	{
		super("?", "show", "done", "left", "remaining", "missing", "finished", "info");
		this.type = type;
		b = true;
		Core.getCore().registerListener(this);
		te = 7;
	}
	
	public ArrayList<Location> getLocs()
	{
		return locs;
	}
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		
		String msg = "";
		if(b)
		{//TODO make this look much nicer, methods & stuff
			
			Player p = (Player) arg0.getForWhom();
			
			inv.put(p.getName(), p.getInventory().getContents());
			
			armor.put(p.getName(), p.getInventory().getArmorContents());
			
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			
			tools[0] = new ItemStack(Material.AIR);
			tools[1] = new ItemStack(Material.GOLD_SWORD);
			tools[2] = new ItemStack(Material.GOLD_SPADE);
			tools[4] = new ItemStack(Material.DIAMOND_SWORD);
			tools[5] = new ItemStack(Material.DIAMOND_SPADE);
			tools[6] = new ItemStack(Material.DIAMOND_PICKAXE);
			tools[7] = new ItemStack(Material.DIAMOND_AXE);
			tools[8] = new ItemStack(Material.AIR);
			tools[3] = new ItemStack(Material.AIR);
			ItemMeta[] descs = new ItemMeta[6];
			descs[0] = tools[1].getItemMeta();
			descs[1] = tools[2].getItemMeta();
			descs[2] = tools[4].getItemMeta();
			descs[3] = tools[5].getItemMeta();
			descs[4] = tools[6].getItemMeta();
			descs[5] = tools[7].getItemMeta();
			
			descs[0].setDisplayName("Arena region");
			descs[1].setDisplayName("Lobby region");
			descs[2].setDisplayName("Death position");
			descs[3].setDisplayName("Lobby warp");
			descs[4].setDisplayName("Team spawn");
			descs[5].setDisplayName("Spectator warp");
			ArrayList<ArrayList<String>> lores = new ArrayList<ArrayList<String>>();
			
			lores.add(new ArrayList<String>());
			lores.add(new ArrayList<String>());
			lores.add(new ArrayList<String>());
			lores.add(new ArrayList<String>());
			lores.add(new ArrayList<String>());
			lores.add(new ArrayList<String>());
			
			lores.get(0).add("\u00A7aLeft click: \u00A7fArena position 1");
			lores.get(0).add("\u00A7cRight click: \u00A7fArena position 2");
			lores.get(1).add("\u00A7aLeft click: \u00A7fLobby position 1");
			lores.get(1).add("\u00A7cRight click: \u00A7fLobby position 2");
			lores.get(2).add("\u00A7aLeft click: \u00A7fDeath position");
			lores.get(3).add("\u00A7aLeft click: \u00A7fLobby position");
			lores.get(4).add("\u00A7aLeft click: \u00A7fSet team position");
			lores.get(4).add("\u00A7aRight click: \u00A7fCycle through teams");
			lores.get(5).add("\u00A7aLeft click: \u00A7fSpectator position");
			descs[0].setLore(lores.get(0));
			descs[1].setLore(lores.get(1));
			descs[2].setLore(lores.get(2));
			descs[3].setLore(lores.get(3));
			descs[4].setLore(lores.get(4));
			descs[5].setLore(lores.get(5));
			tools[1].setItemMeta(descs[0]);
			tools[2].setItemMeta(descs[1]);
			tools[4].setItemMeta(descs[2]);
			tools[5].setItemMeta(descs[3]);
			tools[6].setItemMeta(descs[4]);
			tools[7].setItemMeta(descs[5]);
			
			p.getInventory().addItem(tools);
			msg = ChatColor.AQUA+"You are now "+type.toLowerCase()+"ing "+arg0.getSessionData("ArenaName")+".\n"+ChatColor.AQUA+"You have several tools, and each tool has specific functions. You can read their descriptions for more information about them.\nYou can also type out some things into chat.";
			msg+="\nThese are your options:\n? - Displays help.\ndone - Finishes the editing session.";
			b = false;
		}
		else
		{
			msg = "Type ? to display help.";
		}
		return msg;
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext arg0, String arg1) {
		if(arg1.equalsIgnoreCase("?"))
		{
			arg0.getForWhom().sendRawMessage(ChatUtils.prefix()+ChatColor.AQUA+"You can type any of these:");
			arg0.getForWhom().sendRawMessage(ChatUtils.prefix()+ChatColor.AQUA+"? - Shows this help page.");
			arg0.getForWhom().sendRawMessage(ChatUtils.prefix()+ChatColor.AQUA+"done|finished - Exits the session.");
			arg0.getForWhom().sendRawMessage(ChatUtils.prefix()+ChatColor.AQUA+"show - Displays the finished points in certain colours.");
			arg0.getForWhom().sendRawMessage(ChatUtils.prefix()+ChatColor.AQUA+"left|missing|remaining - Displays the remaining points.");
			arg0.getForWhom().sendRawMessage(ChatUtils.prefix()+ChatColor.AQUA+"info - Allows you to edit the arena information.");
		}
		if(arg1.equalsIgnoreCase("done")||arg1.equalsIgnoreCase("finished"))
		{
			arg0.getForWhom().sendRawMessage(ChatUtils.prefix()+ChatColor.AQUA+"Arena setup finished! The arena "+arg0.getSessionData("ArenaName")+" is now ready for use!");
			Player p = (Player) arg0.getForWhom();
			p.getInventory().clear();
			p.getInventory().setContents(inv.get(p.getName()));
			p.getInventory().setArmorContents(armor.get(p.getName()));
			inv.remove(p.getName());
			armor.remove(p.getName());
			
			if(type.equalsIgnoreCase("Create"))
			{
				SettingsManager.getSettingsManager().set("arenas.yml", "arenas."+arg0.getSessionData("ArenaName"), true);
			}
			else
			{
				if(!((String) SettingsManager.getSettingsManager().getValue("arenas.yml", "arenas")).equalsIgnoreCase((String) arg0.getSessionData("ArenaName")))
				{
					SettingsManager.getSettingsManager().set("arenas.yml", "arenas."+arg0.getSessionData("ArenaName"), true);
				}
			}
			return Prompt.END_OF_CONVERSATION;
		}
		if(arg1.equalsIgnoreCase("show"))
		{
			//TODO this
		}
		if(arg1.equalsIgnoreCase("left")||arg1.equalsIgnoreCase("missing")||arg1.equalsIgnoreCase("remaining"))
		{
			//TODO this - these two are visualization stuff and don't use a return statement
		}
		if(arg1.equalsIgnoreCase("info"))
		{
			return new InfoPrompt(this);//TODO make this class
		}
		return this;
	}
	
	//TODO confirmation messages
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		boolean b = false;
		for(ItemStack t : tools)
		{
			if(event.getPlayer().getItemInHand()==t&&t.getType()!=Material.AIR)
			{
				b = true;
			}
		}
		if(!b) return;
		if(event.getAction()==Action.LEFT_CLICK_BLOCK)
		{
			for(ItemStack t : tools)
			{
				if(event.getPlayer().getItemInHand()==t&&t.getType()!=Material.AIR)
				{
					
					Location bl = event.getClickedBlock().getLocation();
					switch(t.getItemMeta().getDisplayName())
					{
					/* -NOTE- any location that is not needed should be set to null -NOTE- FFA spawns will be random
					 * Index = location - extra information
					 * 0 = lobby spawn
					 * 1 = lobby corner 1
					 * 2 = lobby corner 2
					 * 3 = arena corner 1
					 * 4 = arena corner 2
					 * 5 = death location - only use if enabled in config, else use spec spawn
					 * 6 = spectator spawn
					 * 7 = team 1 spawn - random relative to location, toggleable - max distance settable in conf
					 * 8 = team 2 spawn - random relative to location, toggleable - max distance settable in conf
					 * 9 = team 3 spawn - random relative to location, toggleable - max distance settable in conf
					 * 10 = team 4 spawn, all nums + up to 23 are teams
					 */ 
					case "Arena region":
						locs.add(3, bl);
						return;
					case "Lobby region":
						locs.add(1, bl);
						return;
					case "Death position":
						locs.add(8, bl);
						return;
					case "Lobby warp":
						locs.add(0, bl);
						return;
					case "Team spawn":
						locs.add(te, bl);
						return;
					case "Spectator warp":
						locs.add(6, bl);
						return;
					default:
						return;
					}
				}
			}
		}
		else if(event.getAction()==Action.LEFT_CLICK_AIR)
		{
			for(ItemStack t : tools)
			{
				if(event.getPlayer().getItemInHand()==t&&t.getType()!=Material.AIR)
				{
					
					@SuppressWarnings("deprecation")
					Location bl = event.getPlayer().getTargetBlock(null, 150).getLocation();
					switch(t.getItemMeta().getDisplayName())
					{
					/* -NOTE- any location that is not needed should be set to null -NOTE- FFA spawns will be random
					 * Index = location - extra information
					 * 0 = lobby spawn
					 * 1 = lobby corner 1
					 * 2 = lobby corner 2
					 * 3 = arena corner 1
					 * 4 = arena corner 2
					 * 5 = death location - only use if enabled in config, else use spec spawn
					 * 6 = spectator spawn
					 * 7 = team 1 spawn - random relative to location, toggleable - max distance settable in conf
					 * 8 = team 2 spawn - random relative to location, toggleable - max distance settable in conf
					 * 9 = team 3 spawn - random relative to location, toggleable - max distance settable in conf
					 * 10 = team 4 spawn, all nums + up to 23 are teams
					 */ 
					case "Arena region":
						locs.add(3, bl);
						return;
					case "Lobby region":
						locs.add(1, bl);
						return;
					case "Death position":
						locs.add(8, bl);
						return;
					case "Lobby warp":
						locs.add(0, bl);
						return;
					case "Team spawn":
						locs.add(te, bl);
						return;
					case "Spectator warp":
						locs.add(6, bl);
						return;
					default:
						return;
					}
				}
			}
		}
		else if(event.getAction()==Action.RIGHT_CLICK_BLOCK)
		{
			for(ItemStack t : tools)
			{
				if(event.getPlayer().getItemInHand()==t&&t.getType()!=Material.AIR)
				{
					Location bl = event.getClickedBlock().getLocation();
					switch(t.getItemMeta().getDisplayName())
					{
					case "Arena region":
						locs.add(4, bl);
						return;
					case "Lobby region":
						locs.add(2, bl);
						return;
					case "Death position":
						return;
					case "Lobby warp":
						return;
					case "Team spawn":
						te++;
						te = (te>22)?7:te;
						return;
					case "Spectator warp":
						return;
					default:
						return;
					}
				}
			}
		}
		else if(event.getAction()==Action.RIGHT_CLICK_AIR)
		{
			for(ItemStack t : tools)
			{
				if(event.getPlayer().getItemInHand()==t&&t.getType()!=Material.AIR)
				{
					@SuppressWarnings("deprecation")
					Location bl = event.getPlayer().getTargetBlock(null, 150).getLocation();
					switch(t.getItemMeta().getDisplayName())
					{
					case "Arena region":
						locs.add(4, bl);
						return;
					case "Lobby region":
						locs.add(2, bl);
						return;
					case "Death position":
						return;
					case "Lobby warp":
						return;
					case "Team spawn":
						te++;
						te = (te>22)?7:te;
						return;
					case "Spectator warp":
						return;
					default:
						return;
					}
				}
			}
		}
		else return;
	}

}
