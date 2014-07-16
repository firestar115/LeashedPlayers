package com.otabi.firestar.leashedplayers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.otabi.firestar.moolib.EntityUtils;

public class LeashedPlayersListener implements Listener {
	
	public static HashMap<String, UUID> firstEntities = new HashMap<String, UUID>();
	
	public void onRightClick(PlayerInteractEvent evt){
		if(evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(evt.getPlayer().getItemInHand().getType() == Material.LEASH){
				for(Entity e:EntityUtils.getAllEntities(evt.getPlayer(), 4)){
					if(evt.getPlayer().hasLineOfSight(e)){
						if(e instanceof Player){
							Player p = (Player) e;
							p.setLeashHolder(evt.getPlayer());
						}
						else if(e instanceof LivingEntity && evt.getPlayer().isSneaking()){
							if(firstEntities.containsKey(evt.getPlayer().getDisplayName())){
								for (Entity e1:((LivingEntity) e).getNearbyEntities(64, 64, 64)){
									if(e1.getUniqueId() == firstEntities.get(evt.getPlayer().getName()) && e1 instanceof LivingEntity){
										((LivingEntity) e1).setLeashHolder(e);
									}
								}
								firstEntities.remove(evt.getPlayer().getDisplayName());
							} else {
								firstEntities.put(evt.getPlayer().getName(), e.getUniqueId());
							}
						}
					}
				}
			}
		}
	}
	
	public void onEntityHitEntity(EntityDamageByEntityEvent evt){
		Entity preleashed = evt.getEntity();
		Entity preholder = evt.getDamager();
		if(preleashed instanceof LivingEntity && preholder instanceof LivingEntity){
			LivingEntity leashed = (LivingEntity) preleashed;
			LivingEntity holder = (LivingEntity) preholder;
			if(holder.getEquipment().getItemInHand().getType() == Material.LEASH){
				leashed.setLeashHolder(holder);
			}
		}
	}
	
}
