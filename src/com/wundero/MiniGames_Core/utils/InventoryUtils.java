package com.wundero.MiniGames_Core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryUtils {
	
	private final static String PREFIX = ChatColor.DARK_GRAY+"["+ChatColor.AQUA+"Spark"+ChatColor.RED+"Craft"+ChatColor.DARK_GRAY+"]";
	
	public static void clearInv(Player p)
	{
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
	}
	
	public static void openGUI(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 18, PREFIX+" "+ChatColor.GREEN+"Mini-Game Selector");
		
		ItemStack itemOne = new ItemStack(Material.ANVIL);
		ItemMeta iOMeta = itemOne.getItemMeta();

		//TODO add more items, allow customizable items (w/ BossShop stuff thingy?)
		
		iOMeta.setDisplayName(ChatColor.GREEN+"Mob Arena");
		itemOne.setItemMeta(iOMeta);
		
		inv.setItem(4, itemOne);
		
		p.openInventory(inv);
		
	}
}
