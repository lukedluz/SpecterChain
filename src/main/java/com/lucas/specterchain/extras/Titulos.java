package com.lucas.specterchain.extras;

import com.lucas.specterchain.Main;
import com.lucas.specterchain.*;
import org.bukkit.configuration.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class Titulos
{
    private Main plugin;
    private static HashMap<String, String> titleMessages;
    private static HashMap<String, Boolean> titleConfig;
    
    public Titulos(final Main main) {
        this.plugin = main;
    }
    
    public void carregaTitulos() {
        Titulos.titleMessages.clear();
        final ConfigurationSection titlesMsg = this.plugin.getConfigCache().getConfigurationSection("Titulos");
        for (final String key : titlesMsg.getKeys(true)) {
            if (key.contains("Titulo") || key.contains("Subtitulo")) {
                final String msg = titlesMsg.getString(key);
                Titulos.titleMessages.put(key, msg);
            }
            else {
                if (!key.contains("Ativar")) {
                    continue;
                }
                Titulos.titleConfig.put(key, titlesMsg.getBoolean(key));
            }
        }
    }
    
    public void enviaTitulo(final Player jogador, final String titulo) {
        if (this.plugin.getVersao() && Titulos.titleConfig.get(titulo + ".Ativar")) {
            final String msg1 = Titulos.titleMessages.get(titulo + ".Titulo");
            final String msg2 = Titulos.titleMessages.get(titulo + ".Subtitulo");
            jogador.sendTitle(this.ff(msg1), this.ff(msg2));
        }
    }
    
    private String ff(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    
    static {
        Titulos.titleMessages = new HashMap<String, String>();
        Titulos.titleConfig = new HashMap<String, Boolean>();
    }
}
