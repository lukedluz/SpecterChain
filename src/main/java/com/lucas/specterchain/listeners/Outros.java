package com.lucas.specterchain.listeners;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Outros implements Listener
{
    private Main plugin;
    
    public Outros(final Main main) {
        this.plugin = main;
    }
    
    @EventHandler
    public void playerDropaItem(final PlayerDropItemEvent ev) {
        if (this.plugin.getJogadores().contains(ev.getPlayer().getName())) {
            final Player jogador = ev.getPlayer();
            if (jogador.hasPermission("specterchain.admin")) {
                return;
            }
            if (this.plugin.getConfigCache().getString("Opcoes.BloquearOPlayerDeDroparItem").equals("true")) {
                ev.setCancelled(true);
                jogador.sendMessage(this.plugin.getMsg("§cVocê não pode dropar items enquanto estiver no Chain!"));
            }
        }
    }
}
