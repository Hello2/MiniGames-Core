package com.wundero.MiniGames_Core.conversation;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetupPrompt extends FixedSetPrompt {

	@SuppressWarnings("unused")
	private String type;
	private boolean b;
	
	public SetupPrompt(String type)
	{
		super("?", "show", "done", "left", "remaining", "missing", "finished", "info");
		this.type = type;
		b = true;
	}
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		String msg = "";
		if(b)
		{
			ItemStack[] tools = new ItemStack[9];//TODO make this look much nicer, methods & stuff
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
			Player p = (Player) arg0.getForWhom();
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
			arg0.getForWhom().sendRawMessage(ChatUtils.getPrefix()+ChatColor.AQUA+"You can type any of these:");
			arg0.getForWhom().sendRawMessage(ChatUtils.getPrefix()+ChatColor.AQUA+"? - Shows this help page.");
			arg0.getForWhom().sendRawMessage(ChatUtils.getPrefix()+ChatColor.AQUA+"done|finished - Exits the session.");
			arg0.getForWhom().sendRawMessage(ChatUtils.getPrefix()+ChatColor.AQUA+"show - Displays the finished points in certain colours.");
			arg0.getForWhom().sendRawMessage(ChatUtils.getPrefix()+ChatColor.AQUA+"left|missing|remaining - Displays the remaining points.");
			arg0.getForWhom().sendRawMessage(ChatUtils.getPrefix()+ChatColor.AQUA+"info - Allows you to edit the arena information.");
		}
		if(arg1.equalsIgnoreCase("done")||arg1.equalsIgnoreCase("finished"))
		{
			arg0.getForWhom().sendRawMessage(ChatUtils.getPrefix()+ChatColor.AQUA+"Arena setup finished! The arena "+arg0.getSessionData("ArenaName")+" is now ready for use!";
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
			return new InfoPrompt();//TODO make this class
		}
		return this;
	}

}
