package com.wundero.MiniGames_Core.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import com.wundero.MiniGames_Core.utils.ChatUtils;
import com.wundero.MiniGames_Core.utils.InventoryUtils;

@SuppressWarnings("unused")
public class Kit {
	
	private static ArrayList<Kit> allKits = new ArrayList<Kit>();
	private static HashMap<String, Kit> playerKits = new HashMap<String, Kit>();
	
	private ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	
	private String name, permission;
	
	private int displayItem;
	
	@SuppressWarnings("deprecation")
	public Kit(String name, ArrayList<String> items, int displayItem)
	{ 
		this.name = name;
		this.permission = "minigames-core.kit."+name;
		this.displayItem = displayItem;
		
		for(String s : items)
		{
			
			int id = 0, amount = 1;
			if(s.contains(":")){
				String[] splitItem = s.split(":");
				id = Integer.valueOf(splitItem[0].trim()); //TODO add item ids and metadata
				amount = Integer.valueOf(splitItem[1].trim()); //ID, Amount 
			} else id = Integer.valueOf(s.trim());
			
			this.items.add(new ItemStack(Material.getMaterial(id))); //TODO add support for item names
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public static boolean isKit(String name)
	{
		for(Kit k : allKits)
		{
			if(name.equalsIgnoreCase(k.getName()))
			{
				return true;
			}
		}
		return false;
	}
	
	public static Kit getKit(String name)
	{
		for(Kit k : allKits)
		{
			if(name.equalsIgnoreCase(k.getName()))
			{
				return k;
			}
		}
		return null;
	}
	
	public static ArrayList<Kit> getKits()
	{
		return allKits;
	}
	
	public static void setKit(Player p, Kit k)
	{
		if(!(p.hasPermission(k.getPermissionNode())))
		{
			ChatUtils.sendMessage(p, "You do not have permission for this kit!", MessageLevel.ERROR);
			return;
		}
		playerKits.put(p.getName(), k);
	}
	
	public void giveKit(Player p)
	{
		InventoryUtils.clearInv(p);
		for(ItemStack i : items)
		{
			p.getInventory().addItem(i);
		}
	}
	
	public static Kit getKit(Player p)
	{
		return playerKits.get(p.getName())== null? allKits.get(0) : playerKits.get(p.getName());
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getDisplayItem()
	{
		ItemStack is = new ItemStack(displayItem);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		
		is.setItemMeta(im);
		
		return is;
	}
	
	public Permission getPermissionNode()
	{
		return new Permission(permission);
	}
	
	public void setKit(Player p)
	{
		setKit(p, this);
	}
}
