package com.lucas.specterchain.listeners;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;

public class PlayerMorre implements Listener
{
    private Main plugin;
    
    public PlayerMorre(final Main main) {
        this.plugin = main;
    }
    
    @EventHandler
    public void playerMorreu(final PlayerDeathEvent ev) {
        if (Main.getJogadores().contains(ev.getEntity().getName())) {
            final Player morreu = ev.getEntity();
            if (this.plugin.tocarSom()) {
                morreu.playSound(morreu.getLocation(), Sound.BLOCK_NOTE_BELL, 1.0f, 2.0f);
            }
            ev.setDeathMessage((String)null);
            ev.setDroppedExp(0);
            if (this.plugin.getConfigCache().getString("Opcoes.NaoDroparItemAoMorrer").equals("true")) {
                ev.getDrops().clear();
            }
            if (ev.getEntity().getKiller() instanceof Player) {
                final Player matou = ev.getEntity().getKiller();
                for (final String efeitos : this.plugin.getConfigCache().getString("Opcoes.AplicarEfeitosAoMatar").split("-")) {
                    final String[] efeito = efeitos.split(":");
                    if (this.plugin.getVersao()) {
                        matou.addPotionEffect(new PotionEffect(PotionEffectType.getByName(efeito[0]), Integer.parseInt(efeito[2]) * 20, Integer.parseInt(efeito[1]) + 1, true, true));
                    }
                    else {
                        matou.addPotionEffect(new PotionEffect(PotionEffectType.getByName(efeito[0]), Integer.parseInt(efeito[2]) * 20, Integer.parseInt(efeito[1]) + 1));
                    }
                }
                for (final String cmd : this.plugin.getConfigCache().getStringList("Opcoes.ExecutarComandosAoMatar")) {
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd.replace("@matou@", matou.getName()).replace("@morreu@", morreu.getName()));
                }
                if (this.plugin.mostrarFogos()) {
                    this.plugin.efeitosFogos(matou);
                }
                if (this.plugin.getConfigCache().getString("Opcoes.DarMoneyAoMatar").equals("true")) {
                    this.plugin.adicionaMoney(matou.getName(), this.plugin.getConfigCache().getDouble("Opcoes.MoneyAoMatar"));
                }
                if (this.plugin.tocarSom()) {
                    matou.playSound(matou.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
                }
                for (final String jogadores : Main.getJogadores()) {
                    if (matou.getName().equalsIgnoreCase(jogadores)) {
                        matou.sendMessage(this.plugin.getMsg("§aVoce matou o jogador "+morreu.getName()+" e recebeu "+this.plugin.getConfigCache().getString("Opcoes.MoneyAoMatar")+" de money!"));
                    }
                    else if (morreu.getName().equalsIgnoreCase(jogadores)) {
                        morreu.sendMessage(this.plugin.getMsg("§aVoce matou o jogador "+morreu.getName()+" e recebeu "+this.plugin.getConfigCache().getString("Opcoes.MoneyAoMatar")+" de money!"));
                        this.plugin.getTitles.enviaTitulo(morreu, "§eVoce foi morto por "+matou.getName()+"!");
                    }
                    else {
                        if (this.plugin.tocarSom()) {
                            Bukkit.getPlayer(jogadores).playSound(Bukkit.getPlayer(jogadores).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
                        }
                        Bukkit.getPlayer(jogadores).sendMessage(this.plugin.getMsg("§b[Specter] §a"+morreu.getName()+" foi morto por "+matou.getName()+"!"));
                    }
                }
                for (final String camarotes : this.plugin.getCamarotes()) {
                    Bukkit.getPlayer(camarotes).sendMessage(this.plugin.getMsg("§b[Specter] §a"+morreu.getName()+" foi morto por "+matou.getName()+"!"));
                    Bukkit.getPlayer(camarotes).playSound(Bukkit.getPlayer(camarotes).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
                }
                Main.delJogador(morreu.getName());
                return;
            }
            for (final String jogadores2 : Main.getJogadores()) {
                if (morreu.getName().equalsIgnoreCase(jogadores2)) {
                    morreu.sendMessage(this.plugin.getMsg("§eVocê foi morto no Chain!"));
                }
                else {
                    if (this.plugin.tocarSom()) {
                        Bukkit.getPlayer(jogadores2).playSound(Bukkit.getPlayer(jogadores2).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
                    }
                    Bukkit.getPlayer(jogadores2).sendMessage(this.plugin.getMsg("§b[Specter] §a"+morreu.getName()+" foi morto no Chain!"));
                }
            }
            for (final String camarotes2 : this.plugin.getCamarotes()) {
                Bukkit.getPlayer(camarotes2).sendMessage(this.plugin.getMsg("§b[Specter] §a"+morreu.getName()+" foi morto no Chain!"));
                Bukkit.getPlayer(camarotes2).playSound(Bukkit.getPlayer(camarotes2).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
            }
            Main.delJogador(morreu.getName());
        }
        if (this.plugin.getCamarotes().contains(ev.getEntity().getName())) {
            if (this.plugin.getConfigCache().getString("Opcoes.NaoDroparItemAoMorrer").equals("true")) {
                ev.getDrops().clear();
            }
            this.plugin.delCamarote(ev.getEntity().getName());
        }
    }
}
