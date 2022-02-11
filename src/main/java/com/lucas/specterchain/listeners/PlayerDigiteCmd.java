package com.lucas.specterchain.listeners;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerDigiteCmd implements Listener
{
    private Main plugin;
    
    public PlayerDigiteCmd(final Main main) {
        this.plugin = main;
    }
    
    @EventHandler
    public void playerDigita(final PlayerCommandPreprocessEvent ev) {
        if (this.plugin.getJogadores().contains(ev.getPlayer().getName())) {
            final Player jogador = ev.getPlayer();
            if (jogador.hasPermission("specterchain.admin")) {
                return;
            }
            if (ev.getMessage().contains(" ")) {
                if (!this.plugin.getConfigCache().getStringList("Opcoes.ComandosPermitidos").contains(ev.getMessage().substring(0, ev.getMessage().lastIndexOf(" ")))) {
                    jogador.sendMessage(this.plugin.getMsg("§cVocê não pode usar esse comando no Chain!"));
                    ev.setCancelled(true);
                }
            }
            else if (!this.plugin.getConfigCache().getStringList("Opcoes.ComandosPermitidos").contains(ev.getMessage())) {
                jogador.sendMessage(this.plugin.getMsg("§cVocê não pode usar esse comando no Chain!"));
                ev.setCancelled(true);
            }
        }
    }
}
