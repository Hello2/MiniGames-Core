package com.wundero.MiniGames_Core.Listeners.Player;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.wundero.MiniGames_Core.Core;
import com.wundero.MiniGames_Core.Arena.ArenaManager;
import com.wundero.MiniGames_Core.Listeners.MGListener;

public class SpectatorListener extends MGListener {
	
	
	
	public SpectatorListener(Core pl) {
		super(pl);
	}


  //TODO add more spectator event cancels.
  
  
	@EventHandler
	public void onPlayerHurtPlayer(EntityDamageByEntityEvent event) {
	    Entity entityDamager = event.getDamager();
	    Entity entityDamaged = event.getEntity();
	   
	    if(entityDamager instanceof Arrow) {
	        if(entityDamaged instanceof Player && ((Arrow) entityDamager).getShooter() instanceof Player) {
	            Arrow arrow = (Arrow) entityDamager;
	 
	            Vector velocity = arrow.getVelocity();
	 
	            Player shooter = (Player) arrow.getShooter();
	            Player damaged = (Player) entityDamaged;
	 
	            if(ArenaManager.getArenaManager().isSpectator(damaged)) {
	                damaged.teleport(entityDamaged.getLocation().add(0, 5, 0));
	                damaged.setFlying(true);
	               
	                Arrow newArrow = shooter.launchProjectile(Arrow.class);
	                newArrow.setShooter(shooter);
	                newArrow.setVelocity(velocity);
	                newArrow.setBounce(false);
	               
	                event.setCancelled(true);
	                arrow.remove();
	            }
	        }
	    }
	}
}
