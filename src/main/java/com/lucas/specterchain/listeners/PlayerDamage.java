package com.lucas.specterchain.listeners;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.*;
import com.lucas.specterchain.menus.TopMenu;

import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerDamage implements Listener
{
    private Main plugin;
    
    public PlayerDamage(final Main main) {
        this.plugin = main;
    }
    
    @EventHandler
    public void playerHita(final EntityDamageByEntityEvent ev) {
        if (ev.getDamager() instanceof Player) {
            final Player damager = (Player)ev.getDamager();
            if (ev.getEntity() instanceof Player) {
                final Player hitado = (Player)ev.getEntity();
                if (!this.plugin.getJogadores().contains(damager.getName()) && this.plugin.getJogadores().contains(hitado.getName())) {
                    ev.setCancelled(true);
                    damager.sendMessage(this.plugin.getMsg("§eVocê não pode hitar jogadores que estão no Chain!"));
                }
                else if (this.plugin.getJogadores().contains(damager.getName()) && !this.plugin.getJogadores().contains(hitado.getName())) {
                    ev.setCancelled(true);
                    damager.sendMessage(this.plugin.getMsg("§eVocê não pode hitar jogadores que não estão no Chain!"));
                }
                else if (this.plugin.getCamarotes().contains(damager.getName()) && this.plugin.getCamarotes().contains(hitado.getName())) {
                    ev.setCancelled(true);
                    damager.sendMessage(this.plugin.getMsg("§eVocê não pode hitar outros jogadores no camarote!"));
                }
            }
        }
    }
    
    @EventHandler
	public void ClickMain(InventoryClickEvent e) {
		Inventory Inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		if (Inv.getTitle().equalsIgnoreCase("§cTop Kills")) {
			e.setCancelled(true);
		}
		if (Inv.getTitle().equalsIgnoreCase("§cTop KillStreak")) {
			e.setCancelled(true);
		}
		if (Inv.getTitle().equalsIgnoreCase("Arena Chain")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
				return;
			}
			if (e.getSlot() == 11) {
				p.closeInventory();
				TopMenu.Openkillstop(p);
			} else if (e.getSlot() == 13) {
				p.closeInventory();
				p.chat("/chain entrar");
			} else if (e.getSlot() == 15) {
				p.closeInventory();
				TopMenu.Openkstop(p);
			}
		}
	}
}
