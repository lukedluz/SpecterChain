package com.lucas.specterchain.extras;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lucas.specterchain.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class Top implements Listener {

	private Main plugin;

	public Top(final Main main) {
		this.plugin = main;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String, Double> top = new HashMap();
	public static List<Entry<String, Double>> valores;
	public static String kstop1p;
	public static String kstop2p;
	public static String kstop3p;
	public static String kstop4p;
	public static String kstop5p;
	public static String kstop6p;
	public static String kstop7p;
	public static String kstop8p;
	public static String kstop9p;
	public static String kstop10p;
	public static Double kstop1v;
	public static Double kstop2v;
	public static Double kstop3v;
	public static Double kstop4v;
	public static Double kstop5v;
	public static Double kstop6v;
	public static Double kstop7v;
	public static Double kstop8v;
	public static Double kstop9v;
	public static Double kstop10v;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String, Double> top2 = new HashMap();
	public static List<Entry<String, Double>> valores2;
	public static String killstop1p;
	public static String killstop2p;
	public static String killstop3p;
	public static String killstop4p;
	public static String killstop5p;
	public static String killstop6p;
	public static String killstop7p;
	public static String killstop8p;
	public static String killstop9p;
	public static String killstop10p;
	public static Double killstop1v;
	public static Double killstop2v;
	public static Double killstop3v;
	public static Double killstop4v;
	public static Double killstop5v;
	public static Double killstop6v;
	public static Double killstop7v;
	public static Double killstop8v;
	public static Double killstop9v;
	public static Double killstop10v;

	public static void GetTop() {
		new BukkitRunnable() {
			@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
			public void run() {
				OfflinePlayer[] var4;
				int var3 = (var4 = Bukkit.getOfflinePlayers()).length;

				int id;
				for (id = 0; id < var3; ++id) {
					OfflinePlayer off = var4[id];
					if (off == null)
						return;
					double granax = DataBase.fc.getInt(off.getUniqueId() + ".kills");
					top2.put(off.getName(), granax);
				}

				Stream<Entry<String, Double>> o = top2.entrySet().stream().sorted((x, y) -> {
					return ((Double) y.getValue()).compareTo((Double) x.getValue());
				});
				valores2 = (List) o.collect(Collectors.toList());
				id = 1;

				Iterator iterator;
				for (iterator = valores2.iterator(); iterator.hasNext(); ++id) {
					Entry<String, Double> entradax = (Entry) iterator.next();
					String jogador = (String) entradax.getKey();
					Double valor = (Double) entradax.getValue();
					OfflinePlayer p;
					if (id == 1) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0 && valor != 0) {
							killstop1p = p.getName();
							killstop1v = valor;
						} else {
							killstop1p = "Ninguém";
							killstop1v = 0.0;
						}
					}

					if (id == 2) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop2p = p.getName();
							killstop2v = valor;
						} else {
							killstop2p = "Ninguém";
							killstop2v = 0.0;
						}
					}

					if (id == 3) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop3p = p.getName();
							killstop3v = valor;
						} else {
							killstop3p = "Ninguém";
							killstop3v = 0.0;
						}
					}

					if (id == 4) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop4p = p.getName();
							killstop4v = valor;
						} else {
							killstop4p = "Ninguém";
							killstop4v = 0.0;
						}
					}

					if (id == 5) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop5p = p.getName();
							killstop5v = valor;
						} else {
							killstop5p = "Ninguém";
							killstop5v = 0.0;
						}
					}

					if (id == 6) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop6p = p.getName();
							killstop6v = valor;
						} else {
							killstop6p = "Ninguém";
							killstop6v = 0.0;
						}
					}

					if (id == 7) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop7p = p.getName();
							killstop7v = valor;
						} else {
							killstop7p = "Ninguém";
							killstop7v = 0.0;
						}
					}

					if (id == 8) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop8p = p.getName();
							killstop8v = valor;
						} else {
							killstop8p = "Ninguém";
							killstop8v = 0.0;
						}
					}

					if (id == 9) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop9p = p.getName();
							killstop9v = valor;
						} else {
							killstop9p = "Ninguém";
							killstop9v = 0.0;
						}
					}

					if (id == 10) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							killstop10p = p.getName();
							killstop10v = valor;
						} else {
							killstop10p = "Ninguém";
							killstop10v = 0.0;
						}
					}

					if (id > 10) {
						top2.clear();
						break;
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0L, 6000L);

		new BukkitRunnable() {
			@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
			public void run() {
				OfflinePlayer[] var4;
				int var3 = (var4 = Bukkit.getOfflinePlayers()).length;

				int id;
				for (id = 0; id < var3; ++id) {
					OfflinePlayer off = var4[id];
					if (off == null)
						return;
					double granax = DataBase.fc.getInt(off.getUniqueId() + ".killstreak");
					top.put(off.getName(), granax);
				}

				Stream<Entry<String, Double>> o = top.entrySet().stream().sorted((x, y) -> {
					return ((Double) y.getValue()).compareTo((Double) x.getValue());
				});
				valores = (List) o.collect(Collectors.toList());
				id = 1;

				Iterator iterator;
				for (iterator = valores.iterator(); iterator.hasNext(); ++id) {
					Entry<String, Double> entradax = (Entry) iterator.next();
					String jogador = (String) entradax.getKey();
					Double valor = (Double) entradax.getValue();
					OfflinePlayer p;
					if (id == 1) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop1p = p.getName();
							kstop1v = valor;
						} else {
							kstop1p = "Ninguém";
							kstop1v = 0.0;
						}
					}

					if (id == 2) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop2p = p.getName();
							kstop2v = valor;
						} else {
							kstop2p = "Ninguém";
							kstop2v = 0.0;
						}
					}

					if (id == 3) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop3p = p.getName();
							kstop3v = valor;
						} else {
							kstop3p = "Ninguém";
							kstop3v = 0.0;
						}
					}

					if (id == 4) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop4p = p.getName();
							kstop4v = valor;
						} else {
							kstop4p = "Ninguém";
							kstop4v = 0.0;
						}
					}

					if (id == 5) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop5p = p.getName();
							kstop5v = valor;
						} else {
							kstop5p = "Ninguém";
							kstop5v = 0.0;
						}
					}

					if (id == 6) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop6p = p.getName();
							kstop6v = valor;
						} else {
							kstop6p = "Ninguém";
							kstop6v = 0.0;
						}
					}

					if (id == 7) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop7p = p.getName();
							kstop7v = valor;
						} else {
							kstop7p = "Ninguém";
							kstop7v = 0.0;
						}
					}

					if (id == 8) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop8p = p.getName();
							kstop8v = valor;
						} else {
							kstop8p = "Ninguém";
							kstop8v = 0.0;
						}
					}

					if (id == 9) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop9p = p.getName();
							kstop9v = valor;
						} else {
							kstop9p = "Ninguém";
							kstop9v = 0.0;
						}
					}

					if (id == 10) {
						p = Bukkit.getOfflinePlayer(jogador);
						if (p != null && valor != 0) {
							kstop10p = p.getName();
							kstop10v = valor;
						} else {
							kstop10p = "Ninguém";
							kstop10v = 0.0;
						}
					}

					if (id > 10) {
						top.clear();
						break;
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0L, 6000L);
	}
}
