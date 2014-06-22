package com.otabi.firestar.leashedplayers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import com.otabi.firestar.moolib.MooLibPlugin;

public class LeashedPlayers extends MooLibPlugin {
	
	public void onEnable(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new LeashedPlayersListener(), this);
		super.onEnable();
	}
	
}
