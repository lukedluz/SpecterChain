package com.lucas.specterchain.extras;

import java.util.HashMap;

import com.lucas.specterchain.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillStreak implements Listener {

	private Main plugin;

	public KillStreak(final Main main) {
		this.plugin = main;
	}

	public static HashMap<String, Integer> Kill = new HashMap<>();

	public static void addKill(Player p) {
		if (Kill.containsKey(p.getName())) {
			int value = ((Integer) Kill.get(p.getName())).intValue();
			Kill.put(p.getName(), Integer.valueOf(value + 1));
		} else {
			Kill.put(p.getName(), 1);
		}
	}

	public static void RemoverKill(Player p) {
		if (Kill.containsKey(p.getName())) {
			Kill.put(p.getName(), 0);
		}
	}

	public static int getKill(Player p) {
		if (Kill.containsKey(p.getName())) {
			return Kill.get(p.getName()).intValue();
		} else {
			return 0;
		}
	}

	@EventHandler
	public void Matar(PlayerDeathEvent e) {
		if ((e.getEntity().getKiller() instanceof Player)) {
			Player p = e.getEntity().getKiller();
			Player t = e.getEntity();
			addKill(p);
			RemoverKill(t);
			if (DataBase.fc.contains(p.getUniqueId().toString())) {
				DataBase.fc.set(p.getUniqueId() + ".kills", DataBase.fc.getInt(p.getUniqueId() + ".kills") + 1);
				DataBase.SaveConfig();
			} else {
				DataBase.fc.set(p.getUniqueId() + ".kills", 1);
				DataBase.SaveConfig();
			}
			if (DataBase.fc.contains(p.getUniqueId().toString())) {
				if (getKill(p) > DataBase.fc.getInt(p.getUniqueId() + ".killstreak")) {
					DataBase.fc.set(p.getUniqueId() + ".killstreak", getKill(p));
					DataBase.SaveConfig();
				}
			} else {
				DataBase.fc.set(p.getUniqueId() + ".killstreak", 1);
				DataBase.SaveConfig();
			}
		}
	}
}
