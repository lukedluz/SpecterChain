package com.lucas.specterchain.listeners;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerRenasce implements Listener
{
    private Main plugin;
    
    public PlayerRenasce(final Main main) {
        this.plugin = main;
    }
    
    @EventHandler
    public void playerRespawna(final PlayerRespawnEvent ev) {
        final Player jogador = ev.getPlayer();
        this.plugin.desequipaJogador(jogador);
    }
}
