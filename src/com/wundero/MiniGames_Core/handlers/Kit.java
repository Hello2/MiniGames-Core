package com.wundero.MiniGames_Core.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wundero.MiniGames_Core.utils.ChatUtils;
import com.wundero.MiniGames_Core.utils.InventoryUtils;

public class Kit {
	
	private static ArrayList<Kit> allKits = new ArrayList<Kit>();
	private static HashMap<String, Kit> playerKits = new HashMap<String, Kit>();
	
	private static HashMap<String, ItemStack[]> playerInvs = new HashMap<String, ItemStack[]>();
	private static HashMap<String, ItemStack[]> playerArmor = new HashMap<String, ItemStack[]>();
	private static HashMap<String, ArrayList<PotionEffect>> playerPots = new HashMap<String, ArrayList<PotionEffect>>();
	
	private ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	private ArrayList<ItemStack> armor = new ArrayList<ItemStack>();
	
	private String name, permission, effect;
	
	private int time, potency;
	
	private ItemStack displayItem;
	
	private HashMap<String, Object> kitOverriddenThings = new HashMap<String, Object>();
	
	public Kit(String name, ArrayList<ItemStack> items, ArrayList<ItemStack> armor, ItemStack displayItem, String effect, HashMap<String, Object> kot)
	{ 
		this.name = name;
		this.permission = "minigames-core.kit."+name;
		this.displayItem = displayItem;
		
		this.items = items;
		this.armor = armor;
		
		String[] effectID = effect.split(":");
		
		this.effect=effectID[0];
		this.potency=Integer.parseInt(effectID[1]);
		this.time=Integer.parseInt(effectID[2]);
		
		this.setKitOverriddenThings(kot);
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
			ChatUtils.sendMessage("You do not have permission for this kit!", MessageLevel.ERROR, p);
			return;
		}
		playerKits.put(p.getName(), k);
	}
	
	public void giveKit(Player p)
	{
		playerInvs.put(p.getName(), p.getInventory().getContents());
		playerArmor.put(p.getName(), p.getInventory().getArmorContents());
		InventoryUtils.clearInv(p);
		p.getInventory().setContents((ItemStack[]) items.toArray());
		p.getInventory().setArmorContents((ItemStack[]) armor.toArray());
		
		PotionEffectType effec = PotionEffectType.getByName(effect.toUpperCase());
		Collection<PotionEffect> rems = new ArrayList<PotionEffect>();
		for(PotionEffectType po : PotionEffectType.values())
		{
			rems.add(new PotionEffect(po, 0, 0));
		}
		ArrayList<PotionEffect> value = new ArrayList<PotionEffect>();
		for(PotionEffect pot : p.getActivePotionEffects())
		{
			value.add(pot);
		}
		playerPots.put(p.getName(), value);
		p.addPotionEffects(rems);
		p.addPotionEffect(new PotionEffect(effec, potency, time), true);
	}
	
	public void restoreInv(Player p)
	{
		if(playerInvs.containsKey(p.getName())==false||playerArmor.containsKey(p.getName())==false||playerPots.containsKey(p.getName())==false) return;
		InventoryUtils.clearInv(p);
		p.getInventory().setContents(playerInvs.get(p.getName()));
		p.getInventory().setArmorContents(playerArmor.get(p.getName()));
		playerInvs.remove(p.getName());
		playerArmor.remove(p.getName());
		Collection<PotionEffect> rems = new ArrayList<PotionEffect>();
		for(PotionEffectType po : PotionEffectType.values())
		{
			rems.add(new PotionEffect(po, 0, 0));
		}
		p.addPotionEffects(rems);
		p.addPotionEffects(playerPots.get(p.getName()));
		playerPots.remove(p.getName());
	}
	
	public static Kit getKit(Player p)
	{
		return playerKits.get(p.getName())== null? allKits.get(0) : playerKits.get(p.getName());
	}
	
	public ItemStack getDisplayItem()
	{
		ItemStack is = displayItem;
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("\u00A7aClick to choose this kit!");
		im.setLore(lore);
		
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

	public HashMap<String, Object> getKitOverriddenThings() {
		return kitOverriddenThings;
	}

	public void setKitOverriddenThings(HashMap<String, Object> kitOverriddenThings) {
		this.kitOverriddenThings = kitOverriddenThings;
	}
}
