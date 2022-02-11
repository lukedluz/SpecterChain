package com.lucas.specterchain.comandos;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.menus.TopMenu;
import com.lucas.specterchain.*;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.*;

public class Comandos implements CommandExecutor
{
    private Main plugin;
    
    public Comandos(final Main main) {
        this.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player jogador = (Player)sender;
        if (!(sender instanceof Player) || !cmd.getName().equalsIgnoreCase("chain")) {
            return true;
        }
        if (args.length == 0) {
        	TopMenu.OpenTopMenu(jogador);
        	return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (!jogador.hasPermission("specterchain.admin")) {
                jogador.sendMessage(this.plugin.getMsg("§cVocê não tem permissão para usar esse comando!"));
                return true;
            }
            this.plugin.salvaConfig();
            this.plugin.carregaConfig();
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
            }
            jogador.sendMessage(this.plugin.getMsg("§aA configuração foi recarregada!"));
            return true;
        }
        else if (args[0].equalsIgnoreCase("setarkit")) {
            if (!jogador.hasPermission("specterchain.admin")) {
                jogador.sendMessage(this.plugin.getMsg("§cVocê não tem permissão para usar esse comando!"));
                return true;
            }
            for (int loop = 0; loop <= 35; ++loop) {
                this.plugin.editaDados.set("Itens.Slot." + loop, (Object)jogador.getInventory().getItem(loop));
            }
            for (int loop = 36; loop <= 39; ++loop) {
                this.plugin.editaDados.set("Armadura.Slot." + loop, (Object)jogador.getInventory().getItem(loop));
            }
            this.plugin.salvaKit();
            jogador.getInventory().clear();
            jogador.getInventory().setArmorContents((ItemStack[])null);
            jogador.sendMessage(this.plugin.getMsg("§aO kit do Chain foi definido com sucesso!"));
            this.plugin.carregaKit();
            if (this.plugin.tocarSom()) {
                if (this.plugin.getVersao()) {
                    jogador.playSound(jogador.getLocation(), Sound.ENTITY_HORSE_ARMOR, 1.0f, 2.0f);
                }
                else {
                    jogador.playSound(jogador.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 3.0f);
                }
            }
            return true;
        }
        else if (args[0].equalsIgnoreCase("setarspawn")) {
            if (!jogador.hasPermission("specterchain.admin")) {
                jogador.sendMessage(this.plugin.getMsg("§cVocê não tem permissão para usar esse comando!"));
                return true;
            }
            this.plugin.setLocal("spawn", jogador.getLocation());
            this.plugin.editaDados.set("Lugares.Spawn", (Object)(jogador.getWorld().getName() + ";" + jogador.getLocation().getBlockX() + ";" + jogador.getLocation().getBlockY() + ";" + jogador.getLocation().getBlockZ() + ";" + jogador.getLocation().getYaw() + ";" + jogador.getLocation().getPitch()));
            this.plugin.salvaKit();
            jogador.sendMessage(this.plugin.getMsg("§aO spawn do Chain foi definido com sucesso!"));
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
            }
            return true;
        }
        else if (args[0].equalsIgnoreCase("setarcamarote")) {
            if (!jogador.hasPermission("specterchain.admin")) {
                jogador.sendMessage(this.plugin.getMsg("§cVocê não tem permissão para usar esse comando!"));
                return true;
            }
            this.plugin.setLocal("camarote", jogador.getLocation());
            this.plugin.editaDados.set("Lugares.Camarote", (Object)(jogador.getWorld().getName() + ";" + jogador.getLocation().getBlockX() + ";" + jogador.getLocation().getBlockY() + ";" + jogador.getLocation().getBlockZ() + ";" + jogador.getLocation().getYaw() + ";" + jogador.getLocation().getPitch()));
            this.plugin.salvaKit();
            jogador.sendMessage(this.plugin.getMsg("§aO camarote do Chain foi definido!"));
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
            }
            return true;
        }
        else if (args[0].equalsIgnoreCase("setarsaida")) {
            if (!jogador.hasPermission("specterchain.admin")) {
                jogador.sendMessage(this.plugin.getMsg("§cVocê não tem permissão para usar esse comando!"));
                return true;
            }
            this.plugin.setLocal("saida", jogador.getLocation());
            this.plugin.editaDados.set("Lugares.Saida", (Object)(jogador.getWorld().getName() + ";" + jogador.getLocation().getBlockX() + ";" + jogador.getLocation().getBlockY() + ";" + jogador.getLocation().getBlockZ() + ";" + jogador.getLocation().getYaw() + ";" + jogador.getLocation().getPitch()));
            this.plugin.salvaKit();
            jogador.sendMessage(this.plugin.getMsg("§aA saida do Chain foi definida!"));
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
            }
            return true;
        }
        else if (args[0].equalsIgnoreCase("entrar")) {
            if (!this.plugin.lugaresEstaoSetados()) {
                jogador.sendMessage(this.plugin.getMsg("§cAlguns lugares não foram setados ainda!"));
                return true;
            }
            if (this.plugin.getJogadores().contains(jogador.getName())) {
                jogador.sendMessage(this.plugin.getMsg("§eVocê já esta no Chain!"));
                return true;
            }
            if (!this.plugin.guardaInventario() && this.plugin.contemItems(jogador)) {
                jogador.sendMessage(this.plugin.getMsg("§eEsvazie seu inventario antes de entrar!"));
                return true;
            }
            if (this.plugin.getCamarotes().contains(jogador.getName())) {
                this.plugin.delCamarote(jogador.getName());
            }
            for (final PotionEffect efeitos : jogador.getActivePotionEffects()) {
                jogador.removePotionEffect(efeitos.getType());
            }
            if (this.plugin.getVersao()) {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10, true, false));
            }
            else {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10));
            }
            for (final String comandos : this.plugin.getConfigCache().getStringList("Opcoes.ExecutarComandosAoEntrar")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), comandos.replace("@entrou@", jogador.getName()));
            }
            this.plugin.tpSpawn(jogador);
            this.plugin.getTitles.enviaTitulo(jogador, "§aVocê entrou no chain. Atualmente há §c"+String.valueOf(this.plugin.getJogadores().size())+" §ano Chain!");
            jogador.sendMessage(this.plugin.getMsg("§aVocê entrou no chain. Atualmente há §c"+String.valueOf(this.plugin.getJogadores().size())+" §ano Chain!"));
            for (final String jogadores : this.plugin.getJogadores()) {
                Bukkit.getPlayer(jogadores).sendMessage(this.plugin.getMsg("§b[Specter] §a@jogador@ entrou no Chain. Total de jogadores no Chain: @jogadores@!").replace("@jogador@", jogador.getName()).replace("@jogadores@", String.valueOf(this.plugin.getJogadores().size())));
            }
            if (this.plugin.getConfigCache().getString("Opcoes.AnunciarGlobalEntrou").equalsIgnoreCase("true")) {
                try {
                    for (final Player jogadores2 : Bukkit.getOnlinePlayers()) {
                        if (!this.plugin.getJogadores().contains(jogadores2.getName()) && !jogadores2.getName().equalsIgnoreCase(jogador.getName())) {
                            jogadores2.sendMessage(this.plugin.getMsg("§a§l[Chain] O jogador @jogador@ acaba de entrar no Chain, entre também! /chain entrar!").replace("@jogador@", jogador.getName()));
                        }
                    }
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            for (final String jogadores : this.plugin.getCamarotes()) {
                Bukkit.getPlayer(jogadores).sendMessage(this.plugin.getMsg("§b[Specter] §a@jogador@ entrou no Chain. Total de jogadores no Chain: @jogadores@!").replace("@jogador@", jogador.getName()).replace("@jogadores@", String.valueOf(this.plugin.getJogadores().size())));
            }
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1.0f, 2.0f);
            }
            this.plugin.addJogador(jogador.getName());
            this.plugin.equipaJogador(jogador);
            return true;
        }
        else if (args[0].equalsIgnoreCase("sair")) {
            if (!this.plugin.lugaresEstaoSetados()) {
                jogador.sendMessage(this.plugin.getMsg("§cAlguns lugares não foram setados ainda!"));
                return true;
            }
            if (!this.plugin.getJogadores().contains(jogador.getName())) {
                jogador.sendMessage(this.plugin.getMsg("§eVocê não esta no Chain para sair!"));
                return true;
            }
            for (final PotionEffect efeitos : jogador.getActivePotionEffects()) {
                jogador.removePotionEffect(efeitos.getType());
            }
            for (final String comandos : this.plugin.getConfigCache().getStringList("Opcoes.ExecutarComandosAoSair")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), comandos.replace("@saiu@", jogador.getName()));
            }
            if (this.plugin.getVersao()) {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10, true, false));
            }
            else {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10));
            }
            this.plugin.tpSaida(jogador);
            this.plugin.getTitles.enviaTitulo(jogador, "§cVocê saiu do Chain :(");
            jogador.sendMessage(this.plugin.getMsg("§cVocê saiu do Chain :(").replace("@jogadores@", String.valueOf(this.plugin.getJogadores().size())));
            this.plugin.delJogador(jogador.getName());
            for (final String jogadores : this.plugin.getJogadores()) {
                Bukkit.getPlayer(jogadores).sendMessage(this.plugin.getMsg("§e[Chain] @jogador@ saiu do Chain. Total de jogadores: @jogadores@!").replace("@jogador@", jogador.getName()).replace("@jogadores@", String.valueOf(this.plugin.getJogadores().size())));
            }
            for (final String jogadores : this.plugin.getCamarotes()) {
                Bukkit.getPlayer(jogadores).sendMessage(this.plugin.getMsg("§e[Chain] @jogador@ saiu do Chain. Total de jogadores: @jogadores@!").replace("@jogador@", jogador.getName()).replace("@jogadores@", String.valueOf(this.plugin.getJogadores().size())));
            }
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.WEATHER_RAIN, 1.0f, 2.0f);
            }
            this.plugin.desequipaJogador(jogador);
            return true;
        }
        else if (args[0].equalsIgnoreCase("assistir")) {
            if (!this.plugin.lugaresEstaoSetados()) {
                jogador.sendMessage(this.plugin.getMsg("§cAlguns lugares não foram setados ainda!"));
                return true;
            }
            if (this.plugin.getJogadores().contains(jogador.getName())) {
                jogador.sendMessage(this.plugin.getMsg("§eVocê precisa sair do Chain para assistir ao Chain!"));
                return true;
            }
            if (this.plugin.getCamarotes().contains(jogador.getName())) {
                jogador.sendMessage(this.plugin.getMsg("§aVocê já esta assistindo ao Chain!"));
                return true;
            }
            if (this.plugin.getVersao()) {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10, true, false));
            }
            else {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10));
            }
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
            }
            jogador.sendMessage(this.plugin.getMsg("§eVoce foi teleportado ao camarote do Chain!"));
            this.plugin.addCamarote(jogador.getName());
            this.plugin.tpCamarote(jogador);
            return true;
        }
        else if (args[0].equalsIgnoreCase("sairassistir")) {
            if (!this.plugin.lugaresEstaoSetados()) {
                jogador.sendMessage(this.plugin.getMsg("§cAlguns lugares não foram setados ainda!"));
                return true;
            }
            if (!this.plugin.getCamarotes().contains(jogador.getName())) {
                jogador.sendMessage(this.plugin.getMsg("§cVocê não esta no camarote do Chain para sair!"));
                return true;
            }
            if (this.plugin.getVersao()) {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10, true, false));
            }
            else {
                jogador.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 28, 10));
            }
            if (this.plugin.tocarSom()) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
            }
            jogador.sendMessage(this.plugin.getMsg("§eVoce saiu do camarote do Chain!"));
            this.plugin.tpSaida(jogador);
            this.plugin.delCamarote(jogador.getName());
            return true;
        } else if (args[0].equalsIgnoreCase("help")) {
            if (jogador.hasPermission("specterchain.admin")) {
                jogador.sendMessage("");
                jogador.sendMessage("§e - /chain reload §c: Recarrega a configuração!");
                jogador.sendMessage("§e - /chain setarkit §c: Seta o kit do chain!");
                jogador.sendMessage("§e - /chain setarspawn §c: Seta o local de spawn do Chain!");
                jogador.sendMessage("§e - /chain setarcamarote §c: Setar o local do camarote do Chain!");
                jogador.sendMessage("§e - /chain setarsaida §c: Setar a saida do Chain!");
                jogador.sendMessage("");
            } else {
                jogador.sendMessage("");
                jogador.sendMessage("§b§l[Chain]");
                jogador.sendMessage("§a - /chain entrar §b: Entrar na arena do Chain!");
                jogador.sendMessage("§a - /chain sair §b: Sair da arena do Chain!");
                jogador.sendMessage("§a - /chain assistir §b: Assistir a arena Chain!");
                jogador.sendMessage("§a - /chain sairassistir §b: Parar de assistir o Chain!");
                jogador.sendMessage("§a - /chain status §b: Ver os jogadores presentes no evento!");
                jogador.sendMessage("");
            }
            return true;
        }

        else {
            if (args[0].equalsIgnoreCase("status")) {
                jogador.sendMessage(this.plugin.getMsg("§b[Specter] §aAtualmente há @jogadores@ no Chain! Junte-se a eles, use /chain entrar!").replace("@jogadores@", String.valueOf(this.plugin.getJogadores().size())));
                if (this.plugin.tocarSom()) {
                    jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 2.0f);
                }
                return true;
            }
            return true;
        }
    }
    
    private String ff(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
