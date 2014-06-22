package com.otabi.firestar.leashedplayers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeashedPlayersListener implements Listener {
	
	public void onRightClick(PlayerInteractEvent evt){
		if(evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(evt.getPlayer().getItemInHand().getType() == Material.LEASH){
				for(Player p:Bukkit.getOnlinePlayers()){
					if(evt.getPlayer().hasLineOfSight(p)){
						p.setLeashHolder(evt.getPlayer());
					}
				}
			}
		}
	}
	
}
