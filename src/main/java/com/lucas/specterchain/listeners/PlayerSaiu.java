package com.lucas.specterchain.listeners;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerSaiu implements Listener
{
    private Main plugin;
    
    public PlayerSaiu(final Main main) {
        this.plugin = main;
    }
    
    @EventHandler
    public void playerSai(final PlayerQuitEvent ev) {
        final Player jogador = ev.getPlayer();
        if (this.plugin.getJogadores().contains(jogador.getName())) {
            for (final String jogadores : this.plugin.getJogadores()) {
                Bukkit.getPlayer(jogadores).sendMessage(this.plugin.getMsg("§e[Chain] "+jogador+" deslogou do servidor. Total de jogadores: "+String.valueOf(this.plugin.getJogadores().size())+"!"));
            }
            for (final String jogadores : this.plugin.getCamarotes()) {
                Bukkit.getPlayer(jogadores).sendMessage(this.plugin.getMsg("§e[Chain] "+jogador+" deslogou do servidor. Total de jogadores: "+String.valueOf(this.plugin.getJogadores().size())+"!"));
            }
            for (final PotionEffect efeitos : jogador.getActivePotionEffects()) {
                jogador.removePotionEffect(efeitos.getType());
            }
            this.plugin.desequipaJogador(jogador);
            this.plugin.delJogador(jogador.getName());
        }
        if (this.plugin.getCamarotes().contains(jogador.getName())) {
            this.plugin.delCamarote(jogador.getName());
        }
    }
}
