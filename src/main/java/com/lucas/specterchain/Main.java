package com.lucas.specterchain;

import com.lucas.specterchain.comandos.Comandos;
import com.lucas.specterchain.extras.DataBase;
import com.lucas.specterchain.extras.KillStreak;
import com.lucas.specterchain.extras.Titulos;
import com.lucas.specterchain.extras.Top;
import com.lucas.specterchain.listeners.*;
import org.bukkit.plugin.java.*;
import org.bukkit.configuration.*;
import org.bukkit.inventory.*;
import net.milkbowl.vault.economy.*;
import org.bukkit.command.*;
import java.util.*;
import org.bukkit.event.*;

import org.bukkit.entity.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import java.util.logging.*;
import java.io.*;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

public class Main extends JavaPlugin {
	public static Plugin plugin;
	public Titulos getTitles;
	private ConfigurationSection configCache;
	private static ArrayList<String> jogadoresChain;
	private ArrayList<String> jogadoresCamarote;
	private Location spawn;
	private Location camarote;
	private Location saida;
	private HashMap<Integer, ItemStack> chainKit;
	private HashMap<String, HashMap<Integer, ItemStack>> jogadoresItens;
	private File chainDados;
	public FileConfiguration editaDados;
	private static Economy economy;
	public static Main m;

	public Main() {
		this.getTitles = new Titulos(this);
		Main.jogadoresChain = new ArrayList<String>();
		this.jogadoresCamarote = new ArrayList<String>();
		this.chainKit = new HashMap<Integer, ItemStack>();
		this.jogadoresItens = new HashMap<String, HashMap<Integer, ItemStack>>();
	}

	public static Plugin getPlugin() {
		return Main.plugin;
	}

	public void onEnable() {
		m = this;
		Main.plugin = (Plugin) this;
		DataBase.create();
		DataBase.SaveConfig();
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("§7| §bSpecterChain           §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §bVersão 1.0             §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §fStatus: §aLigado       §7|");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("");
		this.criaConfig();
		this.carregaConfig();
		this.carregaEconomy();
		this.getCommand("chain").setExecutor((CommandExecutor) new Comandos(this));
		this.carregaListeners();
		Top.GetTop();
	}

	public void onDisable() {
		for (final String jogadores : Main.getJogadores()) {
			this.tpSaida(Bukkit.getPlayer(jogadores));
			this.desequipaJogador(Bukkit.getPlayer(jogadores));
		}
		for (final String camarotes : this.getCamarotes()) {
			this.tpSaida(Bukkit.getPlayer(camarotes));
		}
		Main.getJogadores().clear();
		this.getCamarotes().clear();
		Main.plugin = null;
	}

	public String getMsg(final String msg) {
		return this.ff(this.configCache.getString("Mensagens." + msg));
	}

	public ArrayList<String> getMsgMulti(final String msg) {
		return this.ffM((ArrayList<String>) this.configCache.getStringList("Mensagens." + msg));
	}

	public void criaConfig() {
		final File confiG = new File(Main.plugin.getDataFolder().getName() + File.separatorChar + "config.yml",
				"UTF-8");
		if (!confiG.exists()) {
			this.saveResource("config.yml", false);
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Criando um novo arquivo config.yml!");
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "A configuração foi carregada!");
		}
	}

	public void carregaConfig() {
		this.configCache = this.getConfig().getConfigurationSection("Configurar");
		this.getTitles.carregaTitulos();
		this.carregaKit();
		this.carregaLugar();
	}

	public ConfigurationSection getConfigCache() {
		return this.configCache;
	}

	public void salvaConfig() {
		this.reloadConfig();
	}

	private void carregaListeners() {
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new PlayerSaiu(this), Main.plugin);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new PlayerDamage(this), Main.plugin);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new PlayerMorre(this), Main.plugin);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new PlayerDigiteCmd(this), Main.plugin);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new Outros(this), Main.plugin);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new PlayerRenasce(this), Main.plugin);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new Top(this), Main.plugin);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) new KillStreak(this), Main.plugin);
	}

	public boolean tocarSom() {
		return this.getConfigCache().getString("Opcoes.AtivarSons").equals("true");
	}

	public boolean mostrarFogos() {
		return this.getConfigCache().getString("Opcoes.EfeitosFogos").equals("true");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void efeitosFogos(final Player jogador) {
		if (jogador != null) {
			final Firework f = (Firework) jogador.getPlayer().getWorld().spawn(jogador.getPlayer().getLocation(),
					(Class) Firework.class);
			final FireworkMeta fm = f.getFireworkMeta();
			fm.addEffect(FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BURST)
					.withColor(new Color[] { Color.GREEN, Color.BLUE }).withFade(Color.RED).build());
			fm.setPower(0);
			f.setFireworkMeta(fm);
		}
	}

	private String ff(final String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	private ArrayList<String> ffM(final ArrayList<String> msg) {
		final ArrayList<String> array = new ArrayList<String>();
		for (final String linha : msg) {
			array.add(this.ff(linha));
		}
		return array;
	}

	public static ArrayList<String> getJogadores() {
		return Main.jogadoresChain;
	}

	public ArrayList<String> getCamarotes() {
		return this.jogadoresCamarote;
	}

	public HashMap<Integer, ItemStack> getKit() {
		return this.chainKit;
	}

	public void tpSpawn(final Player jogador) {
		jogador.teleport(this.spawn);
	}

	public void tpSaida(final Player jogador) {
		jogador.teleport(this.saida);
	}

	public void tpCamarote(final Player jogador) {
		jogador.teleport(this.camarote);
	}

	public boolean contemItems(final Player jogador) {
		for (final ItemStack item : jogador.getInventory().getContents()) {
			if (item != null) {
				return true;
			}
		}
		return false;
	}

	public boolean guardaInventario() {
		return this.getConfigCache().getString("Opcoes.GuardarInventario").equals("true");
	}

	public void desequipaJogador(final Player jogador) {
		if (this.getConfigCache().getString("Opcoes.NaoRemoverCabecas").equals("true")) {
			for (int loop = 0; loop <= 39; ++loop) {
				if (jogador.getInventory().getItem(loop) != null
						&& (jogador.getInventory().getItem(loop).getType() != Material.SKULL
								|| jogador.getInventory().getItem(loop).getType() != Material.SKULL_ITEM)) {
					jogador.getInventory().setItem(loop, (ItemStack) null);
				}
			}
		} else {
			jogador.getInventory().clear();
			jogador.getInventory().setArmorContents((ItemStack[]) null);
		}
		this.devolveInventario(jogador);
	}

	public void devolveInventario(final Player jogador) {
		if (this.guardaInventario() && this.jogadoresItens.containsKey(jogador.getName())) {
			if (this.jogadoresItens.containsKey(jogador.getName())) {
				final HashMap<Integer, ItemStack> map = this.jogadoresItens.get(jogador.getName());
				for (final int item : map.keySet()) {
					if (jogador.getInventory().getItem(item) == null) {
						jogador.getInventory().setItem(item, (ItemStack) map.get(item));
					} else {
						jogador.getInventory().addItem(new ItemStack[] { map.get(item) });
					}
				}
			}
			this.jogadoresItens.remove(jogador.getName());
		}
	}

	public void equipaJogador(final Player jogador) {
		if (this.guardaInventario()) {
			final HashMap<Integer, ItemStack> map = new HashMap<Integer, ItemStack>();
			for (int loop = 0; loop <= 35; ++loop) {
				map.put(loop, jogador.getInventory().getItem(loop));
			}
			for (int loop = 36; loop <= 39; ++loop) {
				map.put(loop, jogador.getInventory().getItem(loop));
			}
			this.jogadoresItens.put(jogador.getName(), map);
		}
		jogador.getInventory().clear();
		jogador.getInventory().setArmorContents((ItemStack[]) null);
		for (final int item : this.getKit().keySet()) {
			jogador.getInventory().setItem(item, (ItemStack) this.getKit().get(item));
		}
	}

	public void salvaKit() {
		try {
			this.editaDados.save(this.chainDados);
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setLocal(final String local, final Location novolocal) {
		if (local.equalsIgnoreCase("spawn")) {
			this.spawn = novolocal;
		} else if (local.equalsIgnoreCase("camarote")) {
			this.camarote = novolocal;
		} else if (local.equalsIgnoreCase("saida")) {
			this.saida = novolocal;
		}
	}

	public static void addJogador(final String jogador) {
		Main.getJogadores().add(jogador);
	}

	public void addCamarote(final String jogador) {
		this.getCamarotes().add(jogador);
	}

	public void delCamarote(final String jogador) {
		this.getCamarotes().remove(jogador);
	}

	public static void delJogador(final String jogador) {
		Main.getJogadores().remove(jogador);
	}

	private void carregaLugar() {
		try {
			final String[] pos22 = this.editaDados.getString("Lugares.Spawn").split(";");
			this.spawn = new Location(Bukkit.getServer().getWorld(pos22[0]), Double.parseDouble(pos22[1]),
					Double.parseDouble(pos22[2]), Double.parseDouble(pos22[3]), Float.parseFloat(pos22[4]),
					Float.parseFloat(pos22[5]));
			final String[] cm = this.editaDados.getString("Lugares.Camarote").split(";");
			this.camarote = new Location(Bukkit.getServer().getWorld(cm[0]), Double.parseDouble(cm[1]),
					Double.parseDouble(cm[2]), Double.parseDouble(cm[3]), Float.parseFloat(cm[4]),
					Float.parseFloat(cm[5]));
			final String[] sai = this.editaDados.getString("Lugares.Saida").split(";");
			this.saida = new Location(Bukkit.getServer().getWorld(sai[0]), Double.parseDouble(sai[1]),
					Double.parseDouble(sai[2]), Double.parseDouble(sai[3]), Float.parseFloat(sai[4]),
					Float.parseFloat(sai[5]));
		} catch (Exception ex) {
		}
	}

	public void carregaKit() {
		final File file = new File(this.getDataFolder(), "dados.yml");
		this.chainDados = new File(Main.plugin.getDataFolder(), "dados.yml");
		if (file.exists()) {
			this.getKit().clear();
			this.editaDados = (FileConfiguration) YamlConfiguration.loadConfiguration(this.chainDados);
			if (this.editaDados.contains("Itens")) {
				final ConfigurationSection itensKit = this.editaDados.getConfigurationSection("Itens.Slot");
				for (final String slot : itensKit.getKeys(false)) {
					this.getKit().put(Integer.parseInt(slot), itensKit.getItemStack(slot));
				}
			}
			if (this.editaDados.contains("Armadura")) {
				final ConfigurationSection armorKit = this.editaDados.getConfigurationSection("Armadura.Slot");
				for (final String slot : armorKit.getKeys(false)) {
					this.getKit().put(Integer.parseInt(slot), armorKit.getItemStack(slot));
				}
			}
		} else {
			try {
				file.createNewFile();
				this.editaDados = (FileConfiguration) YamlConfiguration.loadConfiguration(this.chainDados);
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public boolean lugaresEstaoSetados() {
		return this.spawn != null && this.saida != null && this.camarote != null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean carregaEconomy() {
		try {
			if (this.getServer().getPluginManager().getPlugin("Vault") != null) {
				final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>) this
						.getServer().getServicesManager().getRegistration((Class) Economy.class);
				if (economyProvider != null) {
					Main.economy = (Economy) economyProvider.getProvider();
				}
				return Main.economy != null;
			}
		} catch (Exception ex) {
			return false;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public void adicionaMoney(final String jogador, final Double quantidade) {
		Main.economy.depositPlayer(jogador, (double) quantidade);
	}

	private boolean versaoAcima18() {
		final String versioN = Bukkit.getServer().getVersion();
		return !versioN.contains("1.5") && !versioN.contains("1.6") && !versioN.contains("1.7")
				&& !versioN.contains("1.4") && !versioN.contains("1.3");
	}

	public boolean getVersao() {
		return this.versaoAcima18();
	}

	@SuppressWarnings("deprecation")
	public void removeMoney(final String jogador, final Double quantidade) {
		Main.economy.withdrawPlayer(jogador, (double) quantidade);
	}

	static {
		Main.economy = null;
	}
	
	public static Main getInstance() {
		return Main.m;
	}
}
