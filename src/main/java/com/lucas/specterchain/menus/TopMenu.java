package com.lucas.specterchain.menus;

import java.util.ArrayList;
import java.util.List;

import com.lucas.specterchain.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.lucas.specterchain.extras.Top;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class TopMenu {
	
	private Main plugin;
    
    public TopMenu(final Main main) {
        this.plugin = main;
    }

	@SuppressWarnings("deprecation")
	public static String getTag(String p) {
		if (PermissionsEx.getUser(p).getGroups()[0].getPrefix().replaceAll("&", "§").equalsIgnoreCase("")) {
			return "§7[M] ";
		} else {
			return PermissionsEx.getUser(p).getGroups()[0].getPrefix().replaceAll("&", "§");
		}
	}
	
	public static void OpenTopMenu(Player s) {
		final Inventory Top = Bukkit.createInventory(null, 27, "Arena Chain");

		ItemStack cabecakill = Heads.TopKill;
		SkullMeta cabecakillmeta = (SkullMeta) cabecakill.getItemMeta();
		cabecakillmeta.setDisplayName("§cTop Kills");
		ArrayList<String> lorekill = new ArrayList<>();
		lorekill.add("");
		lorekill.add("§aClique para acessar o menu dos");
		lorekill.add("§ajogadores que mais mataram na arena chain");
		cabecakillmeta.setLore(lorekill);
		cabecakill.setItemMeta(cabecakillmeta);
		
		ItemStack cabecaentrar = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta cabecaentrarmeta = cabecaentrar.getItemMeta();
		cabecaentrarmeta.setDisplayName("§aEntrar na arena chain");
		ArrayList<String> loreentrar = new ArrayList<>();
		loreentrar.add("");
		loreentrar.add("§aClique para entrar na arena chain");
		cabecaentrarmeta.setLore(loreentrar);
		cabecaentrar.setItemMeta(cabecaentrarmeta);

		ItemStack cabecastreak = Heads.KillStreak;
		SkullMeta cabecastreakmeta = (SkullMeta) cabecastreak.getItemMeta();
		cabecastreakmeta.setDisplayName("§cTop KillStreak");
		ArrayList<String> lorestreak = new ArrayList<>();
		lorestreak.add("");
		lorestreak.add("§aClique para acessar o menu dos jogadores");
		lorestreak.add("§aque fizeram os maiores killstreak na arena chain");
		cabecastreakmeta.setLore(lorestreak);
		cabecastreak.setItemMeta(cabecastreakmeta);
		
		Top.setItem(11, cabecakill);
		Top.setItem(13, cabecaentrar);
		Top.setItem(15, cabecastreak);

		s.openInventory(Top);
	}
	
	public static void Openkillstop(Player s) {

		final Inventory killstop = Bukkit.createInventory(null, 36, "§cTop Kills");

		ItemStack top1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top1meta = (SkullMeta) top1.getItemMeta();
		if (Top.killstop1p.equalsIgnoreCase("Ninguém")) {
			top1meta.setOwner("MHF_Question");
		} else {
			top1meta.setOwner(Top.killstop1p);
		}
		if (Top.killstop1p.equalsIgnoreCase("Ninguém")) {
			top1meta.setDisplayName("§cNinguém");
		} else {
			top1meta.setDisplayName(getTag(Top.killstop1p) + Top.killstop1p);
		}
		List<String> loretop1 = new ArrayList<String>();
		if (Top.killstop1p == null || Top.killstop1p.equalsIgnoreCase("Ninguém")) {
			loretop1.add("");
			loretop1.add("§fJogador: §cNinguém");
			loretop1.add("§fColocação: 1§");
			loretop1.add("§fGrupo: §c-=X=-");
			loretop1.add("§fKills: §70");
			loretop1.add("");
		} else {
			loretop1.add("");
			loretop1.add("§fJogador: §7" + Top.killstop1p);
			loretop1.add("§fColocação: 1§");
			loretop1.add("§fGrupo: " + getTag(Top.killstop1p));
			loretop1.add("§fKills: §7" + Top.killstop1v);
			loretop1.add("");
		}
		top1meta.setLore(loretop1);
		top1.setItemMeta(top1meta);

		ItemStack top2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top2meta = (SkullMeta) top2.getItemMeta();
		if (Top.killstop2p == null || Top.killstop2p.equalsIgnoreCase("Ninguém")) {
			top2meta.setOwner("MHF_Question");
		} else {
			top2meta.setOwner(Top.killstop2p);
		}
		if (Top.killstop2p == null || Top.killstop2p.equalsIgnoreCase("Ninguém")) {
			top2meta.setDisplayName("§cNinguém");
		} else {
			top2meta.setDisplayName(getTag(Top.killstop2p) + Top.killstop2p);
		}
		List<String> loretop2 = new ArrayList<String>();
		if (Top.killstop2p == null || Top.killstop2p.equalsIgnoreCase("Ninguém")) {
			loretop2.add("");
			loretop2.add("§fJogador: §cNinguém");
			loretop2.add("§fColocação: 2§");
			loretop2.add("§fGrupo: §c-=X=-");
			loretop2.add("§fKills: §70");
			loretop2.add("");
		} else {
			loretop2.add("");
			loretop2.add("§fJogador: §7" + Top.killstop2p);
			loretop2.add("§fColocação: 2§");
			loretop2.add("§fGrupo: " + getTag(Top.killstop2p));
			loretop2.add("§fKills: §7" + Top.killstop2v);
			loretop2.add("");
		}
		top2meta.setLore(loretop2);
		top2.setItemMeta(top2meta);

		ItemStack top3 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top3meta = (SkullMeta) top3.getItemMeta();
		if (Top.killstop3p == null || Top.killstop3p.equalsIgnoreCase("Ninguém")) {
			top3meta.setOwner("MHF_Question");
		} else {
			top3meta.setOwner(Top.killstop3p);
		}
		if (Top.killstop3p == null || Top.killstop3p.equalsIgnoreCase("Ninguém")) {
			top3meta.setDisplayName("§cNinguém");
		} else {
			top3meta.setDisplayName(getTag(Top.killstop3p) + Top.killstop3p);
		}
		List<String> loretop3 = new ArrayList<String>();
		if (Top.killstop3p == null || Top.killstop3p.equalsIgnoreCase("Ninguém")) {
			loretop3.add("");
			loretop3.add("§fJogador: §cNinguém");
			loretop3.add("§fColocação: 3§");
			loretop3.add("§fGrupo: §c-=X=-");
			loretop3.add("§fKills: §70");
			loretop3.add("");
		} else {
			loretop3.add("");
			loretop3.add("§fJogador: §7" + Top.killstop3p);
			loretop3.add("§fColocação: 3§");
			loretop3.add("§fGrupo: " + getTag(Top.killstop3p));
			loretop3.add("§fKills: §7" + Top.killstop3v);
			loretop3.add("");
		}
		top3meta.setLore(loretop3);
		top3.setItemMeta(top3meta);

		ItemStack top4 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top4meta = (SkullMeta) top4.getItemMeta();
		if (Top.killstop4p == null || Top.killstop4p.equalsIgnoreCase("Ninguém")) {
			top4meta.setOwner("MHF_Question");
		} else {
			top4meta.setOwner(Top.killstop4p);
		}
		if (Top.killstop4p == null || Top.killstop4p.equalsIgnoreCase("Ninguém")) {
			top4meta.setDisplayName("§cNinguém");
		} else {
			top4meta.setDisplayName(getTag(Top.killstop4p) + Top.killstop4p);
		}
		List<String> loretop4 = new ArrayList<String>();
		if (Top.killstop4p == null || Top.killstop4p.equalsIgnoreCase("Ninguém")) {
			loretop4.add("");
			loretop4.add("§fJogador: §cNinguém");
			loretop4.add("§fColocação: 4§");
			loretop4.add("§fGrupo: §c-=X=-");
			loretop4.add("§fKills: §70");
			loretop4.add("");
		} else {
			loretop4.add("");
			loretop4.add("§fJogador: §7" + Top.killstop4p);
			loretop4.add("§fColocação: 4§");
			loretop4.add("§fGrupo: " + getTag(Top.killstop4p));
			loretop4.add("§fKills: §7" + Top.killstop4v);
			loretop4.add("");
		}
		top4meta.setLore(loretop4);
		top4.setItemMeta(top4meta);

		ItemStack top5 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top5meta = (SkullMeta) top5.getItemMeta();
		if (Top.killstop5p == null || Top.killstop5p.equalsIgnoreCase("Ninguém")) {
			top5meta.setOwner("MHF_Question");
		} else {
			top5meta.setOwner(Top.killstop5p);
		}
		if (Top.killstop5p == null || Top.killstop5p.equalsIgnoreCase("Ninguém")) {
			top5meta.setDisplayName("§cNinguém");
		} else {
			top5meta.setDisplayName(getTag(Top.killstop5p) + Top.killstop5p);
		}
		List<String> loretop5 = new ArrayList<String>();
		if (Top.killstop5p == null || Top.killstop5p.equalsIgnoreCase("Ninguém")) {
			loretop5.add("");
			loretop5.add("§fJogador: §cNinguém");
			loretop5.add("§fColocação: 5§");
			loretop5.add("§fGrupo: §c-=X=-");
			loretop5.add("§fKills: §70");
			loretop5.add("");
		} else {
			loretop5.add("");
			loretop5.add("§fJogador: §7" + Top.killstop5p);
			loretop5.add("§fColocação: 5§");
			loretop5.add("§fGrupo: " + getTag(Top.killstop5p));
			loretop5.add("§fKills: §7" + Top.killstop5v);
			loretop5.add("");
		}
		top5meta.setLore(loretop5);
		top5.setItemMeta(top5meta);

		ItemStack top6 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top6meta = (SkullMeta) top6.getItemMeta();
		if (Top.killstop6p == null || Top.killstop6p.equalsIgnoreCase("Ninguém")) {
			top6meta.setOwner("MHF_Question");
		} else {
			top6meta.setOwner(Top.killstop6p);
		}
		if (Top.killstop6p == null || Top.killstop6p.equalsIgnoreCase("Ninguém")) {
			top6meta.setDisplayName("§cNinguém");
		} else {
			top6meta.setDisplayName(getTag(Top.killstop6p) + Top.killstop6p);
		}
		List<String> loretop6 = new ArrayList<String>();
		if (Top.killstop6p == null || Top.killstop6p.equalsIgnoreCase("Ninguém")) {
			loretop6.add("");
			loretop6.add("§fJogador: §cNinguém");
			loretop6.add("§fColocação: 6§");
			loretop6.add("§fGrupo: §c-=X=-");
			loretop6.add("§fKills: §70");
			loretop6.add("");
		} else {
			loretop6.add("");
			loretop6.add("§fJogador: §7" + Top.killstop6p);
			loretop6.add("§fColocação: 6§");
			loretop6.add("§fGrupo: " + getTag(Top.killstop6p));
			loretop6.add("§fKills: §7" + Top.killstop6v);
			loretop6.add("");
		}
		top6meta.setLore(loretop6);
		top6.setItemMeta(top6meta);

		ItemStack top7 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top7meta = (SkullMeta) top7.getItemMeta();
		if (Top.killstop7p == null || Top.killstop7p.equalsIgnoreCase("Ninguém")) {
			top7meta.setOwner("MHF_Question");
		} else {
			top7meta.setOwner(Top.killstop7p);
		}
		if (Top.killstop7p == null || Top.killstop7p.equalsIgnoreCase("Ninguém")) {
			top7meta.setDisplayName("§cNinguém");
		} else {
			top7meta.setDisplayName(getTag(Top.killstop7p) + Top.killstop7p);
		}
		List<String> loretop7 = new ArrayList<String>();
		if (Top.killstop7p == null || Top.killstop7p.equalsIgnoreCase("Ninguém")) {
			loretop7.add("");
			loretop7.add("§fJogador: §cNinguém");
			loretop7.add("§fColocação: 7§");
			loretop7.add("§fGrupo: §c-=X=-");
			loretop7.add("§fKills: §70");
			loretop7.add("");
		} else {
			loretop7.add("");
			loretop7.add("§fJogador: §7" + Top.killstop7p);
			loretop7.add("§fColocação: 7§");
			loretop7.add("§fGrupo: " + getTag(Top.killstop7p));
			loretop7.add("§fKills: §7" + Top.killstop7v);
			loretop7.add("");
		}
		top7meta.setLore(loretop7);
		top7.setItemMeta(top7meta);

		ItemStack top8 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top8meta = (SkullMeta) top8.getItemMeta();
		if (Top.killstop8p == null || Top.killstop8p.equalsIgnoreCase("Ninguém")) {
			top8meta.setOwner("MHF_Question");
		} else {
			top8meta.setOwner(Top.killstop8p);
		}
		if (Top.killstop8p == null || Top.killstop8p.equalsIgnoreCase("Ninguém")) {
			top8meta.setDisplayName("§cNinguém");
		} else {
			top8meta.setDisplayName(getTag(Top.killstop8p) + Top.killstop8p);
		}
		List<String> loretop8 = new ArrayList<String>();
		if (Top.killstop8p == null || Top.killstop8p.equalsIgnoreCase("Ninguém")) {
			loretop8.add("");
			loretop8.add("§fJogador: §cNinguém");
			loretop8.add("§fColocação: 8§");
			loretop8.add("§fGrupo: §c-=X=-");
			loretop8.add("§fKills: §70");
			loretop8.add("");
		} else {
			loretop8.add("");
			loretop8.add("§fJogador: §7" + Top.killstop8p);
			loretop8.add("§fColocação: 8§");
			loretop8.add("§fGrupo: " + getTag(Top.killstop8p));
			loretop8.add("§fKills: §7" + Top.killstop8v);
			loretop8.add("");
		}
		top8meta.setLore(loretop8);
		top8.setItemMeta(top8meta);

		ItemStack top9 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top9meta = (SkullMeta) top9.getItemMeta();
		if (Top.killstop9p == null || Top.killstop9p.equalsIgnoreCase("Ninguém")) {
			top9meta.setOwner("MHF_Question");
		} else {
			top9meta.setOwner(Top.killstop9p);
		}
		if (Top.killstop9p == null || Top.killstop9p.equalsIgnoreCase("Ninguém")) {
			top9meta.setDisplayName("§cNinguém");
		} else {
			top9meta.setDisplayName(getTag(Top.killstop9p) + Top.killstop9p);
		}
		List<String> loretop9 = new ArrayList<String>();
		if (Top.killstop9p == null || Top.killstop9p.equalsIgnoreCase("Ninguém")) {
			loretop9.add("");
			loretop9.add("§fJogador: §cNinguém");
			loretop9.add("§fColocação: 9§");
			loretop9.add("§fGrupo: §c-=X=-");
			loretop9.add("§fKills: §70");
			loretop9.add("");
		} else {
			loretop9.add("");
			loretop9.add("§fJogador: §7" + Top.killstop9p);
			loretop9.add("§fColocação: 9§");
			loretop9.add("§fGrupo: " + getTag(Top.killstop9p));
			loretop9.add("§fKills: §7" + Top.killstop9v);
			loretop9.add("");
		}
		top9meta.setLore(loretop9);
		top9.setItemMeta(top9meta);

		ItemStack top10 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top10meta = (SkullMeta) top10.getItemMeta();
		if (Top.killstop10p == null || Top.killstop10p.equalsIgnoreCase("Ninguém")) {
			top10meta.setOwner("MHF_Question");
		} else {
			top10meta.setOwner(Top.killstop10p);
		}
		if (Top.killstop10p == null || Top.killstop10p.equalsIgnoreCase("Ninguém")) {
			top10meta.setDisplayName("§cNinguém");
		} else {
			top10meta.setDisplayName(getTag(Top.killstop10p) + Top.killstop10p);
		}
		List<String> loretop10 = new ArrayList<String>();
		if (Top.killstop10p == null || Top.killstop10p.equalsIgnoreCase("Ninguém")) {
			loretop10.add("");
			loretop10.add("§fJogador: §cNinguém");
			loretop10.add("§fColocação: §b10º");
			loretop10.add("§fGrupo: §c-=X=-");
			loretop10.add("§fKills: §70");
			loretop10.add("");
		} else {
			loretop10.add("");
			loretop10.add("§fJogador: §7" + Top.killstop10p);
			loretop10.add("§fColocação: §b10º");
			loretop10.add("§fGrupo: " + getTag(Top.killstop10p));
			loretop10.add("§fKills: §7" + Top.killstop10v);
			loretop10.add("");
		}
		top10meta.setLore(loretop10);
		top10.setItemMeta(top10meta);

		killstop.setItem(12, top1);
		killstop.setItem(13, top2);
		killstop.setItem(14, top3);
		killstop.setItem(19, top4);
		killstop.setItem(20, top5);
		killstop.setItem(21, top6);
		killstop.setItem(22, top7);
		killstop.setItem(23, top8);
		killstop.setItem(24, top9);
		killstop.setItem(25, top10);
		s.openInventory(killstop);
	}
	
	public static void Openkstop(Player s) {

		final Inventory kstop = Bukkit.createInventory(null, 36, "§cTop KillStreak");

		ItemStack top1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top1meta = (SkullMeta) top1.getItemMeta();
		if (Top.kstop1p.equalsIgnoreCase("Ninguém")) {
			top1meta.setOwner("MHF_Question");
		} else {
			top1meta.setOwner(Top.kstop1p);
		}
		if (Top.kstop1p.equalsIgnoreCase("Ninguém")) {
			top1meta.setDisplayName("§cNinguém");
		} else {
			top1meta.setDisplayName(getTag(Top.kstop1p) + Top.kstop1p);
		}
		List<String> loretop1 = new ArrayList<String>();
		if (Top.kstop1p == null || Top.kstop1p.equalsIgnoreCase("Ninguém")) {
			loretop1.add("");
			loretop1.add("§fJogador: §cNinguém");
			loretop1.add("§fColocação: 1§");
			loretop1.add("§fGrupo: §c-=X=-");
			loretop1.add("§fKillStreak: §b0");
			loretop1.add("");
		} else {
			loretop1.add("");
			loretop1.add("§fJogador: §7" + Top.kstop1p);
			loretop1.add("§fColocação: 1§");
			loretop1.add("§fGrupo: " + getTag(Top.kstop1p));
			loretop1.add("§fKillStreak: §b" + Top.kstop1v);
			loretop1.add("");
		}
		top1meta.setLore(loretop1);
		top1.setItemMeta(top1meta);

		ItemStack top2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top2meta = (SkullMeta) top2.getItemMeta();
		if (Top.kstop2p == null || Top.kstop2p.equalsIgnoreCase("Ninguém")) {
			top2meta.setOwner("MHF_Question");
		} else {
			top2meta.setOwner(Top.kstop2p);
		}
		if (Top.kstop2p == null || Top.kstop2p.equalsIgnoreCase("Ninguém")) {
			top2meta.setDisplayName("§cNinguém");
		} else {
			top2meta.setDisplayName(getTag(Top.kstop2p) + Top.kstop2p);
		}
		List<String> loretop2 = new ArrayList<String>();
		if (Top.kstop2p == null || Top.kstop2p.equalsIgnoreCase("Ninguém")) {
			loretop2.add("");
			loretop2.add("§fJogador: §cNinguém");
			loretop2.add("§fColocação: 2§");
			loretop2.add("§fGrupo: §c-=X=-");
			loretop2.add("§fKillStreak: §b0");
			loretop2.add("");
		} else {
			loretop2.add("");
			loretop2.add("§fJogador: §7" + Top.kstop2p);
			loretop2.add("§fColocação: 2§");
			loretop2.add("§fGrupo: " + getTag(Top.kstop2p));
			loretop2.add("§fKillStreak: §b" + Top.kstop2v);
			loretop2.add("");
		}
		top2meta.setLore(loretop2);
		top2.setItemMeta(top2meta);

		ItemStack top3 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top3meta = (SkullMeta) top3.getItemMeta();
		if (Top.kstop3p == null || Top.kstop3p.equalsIgnoreCase("Ninguém")) {
			top3meta.setOwner("MHF_Question");
		} else {
			top3meta.setOwner(Top.kstop3p);
		}
		if (Top.kstop3p == null || Top.kstop3p.equalsIgnoreCase("Ninguém")) {
			top3meta.setDisplayName("§cNinguém");
		} else {
			top3meta.setDisplayName(getTag(Top.kstop3p) + Top.kstop3p);
		}
		List<String> loretop3 = new ArrayList<String>();
		if (Top.kstop3p == null || Top.kstop3p.equalsIgnoreCase("Ninguém")) {
			loretop3.add("");
			loretop3.add("§fJogador: §cNinguém");
			loretop3.add("§fColocação: 3§");
			loretop3.add("§fGrupo: §c-=X=-");
			loretop3.add("§fKillStreak: §b0");
			loretop3.add("");
		} else {
			loretop3.add("");
			loretop3.add("§fJogador: §7" + Top.kstop3p);
			loretop3.add("§fColocação: 3§");
			loretop3.add("§fGrupo: " + getTag(Top.kstop3p));
			loretop3.add("§fKillStreak: §b" + Top.kstop3v);
			loretop3.add("");
		}
		top3meta.setLore(loretop3);
		top3.setItemMeta(top3meta);

		ItemStack top4 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top4meta = (SkullMeta) top4.getItemMeta();
		if (Top.kstop4p == null || Top.kstop4p.equalsIgnoreCase("Ninguém")) {
			top4meta.setOwner("MHF_Question");
		} else {
			top4meta.setOwner(Top.kstop4p);
		}
		if (Top.kstop4p == null || Top.kstop4p.equalsIgnoreCase("Ninguém")) {
			top4meta.setDisplayName("§cNinguém");
		} else {
			top4meta.setDisplayName(getTag(Top.kstop4p) + Top.kstop4p);
		}
		List<String> loretop4 = new ArrayList<String>();
		if (Top.kstop4p == null || Top.kstop4p.equalsIgnoreCase("Ninguém")) {
			loretop4.add("");
			loretop4.add("§fJogador: §cNinguém");
			loretop4.add("§fColocação: 4§");
			loretop4.add("§fGrupo: §c-=X=-");
			loretop4.add("§fKillStreak: §b0");
			loretop4.add("");
		} else {
			loretop4.add("");
			loretop4.add("§fJogador: §7" + Top.kstop4p);
			loretop4.add("§fColocação: 4§");
			loretop4.add("§fGrupo: " + getTag(Top.kstop4p));
			loretop4.add("§fKillStreak: §b" + Top.kstop4v);
			loretop4.add("");
		}
		top4meta.setLore(loretop4);
		top4.setItemMeta(top4meta);

		ItemStack top5 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top5meta = (SkullMeta) top5.getItemMeta();
		if (Top.kstop5p == null || Top.kstop5p.equalsIgnoreCase("Ninguém")) {
			top5meta.setOwner("MHF_Question");
		} else {
			top5meta.setOwner(Top.kstop5p);
		}
		if (Top.kstop5p == null || Top.kstop5p.equalsIgnoreCase("Ninguém")) {
			top5meta.setDisplayName("§cNinguém");
		} else {
			top5meta.setDisplayName(getTag(Top.kstop5p) + Top.kstop5p);
		}
		List<String> loretop5 = new ArrayList<String>();
		if (Top.kstop5p == null || Top.kstop5p.equalsIgnoreCase("Ninguém")) {
			loretop5.add("");
			loretop5.add("§fJogador: §cNinguém");
			loretop5.add("§fColocação: 5§");
			loretop5.add("§fGrupo: §c-=X=-");
			loretop5.add("§fKillStreak: §b0");
			loretop5.add("");
		} else {
			loretop5.add("");
			loretop5.add("§fJogador: §7" + Top.kstop5p);
			loretop5.add("§fColocação: 5§");
			loretop5.add("§fGrupo: " + getTag(Top.kstop5p));
			loretop5.add("§fKillStreak: §b" + Top.kstop5v);
			loretop5.add("");
		}
		top5meta.setLore(loretop5);
		top5.setItemMeta(top5meta);

		ItemStack top6 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top6meta = (SkullMeta) top6.getItemMeta();
		if (Top.kstop6p == null || Top.kstop6p.equalsIgnoreCase("Ninguém")) {
			top6meta.setOwner("MHF_Question");
		} else {
			top6meta.setOwner(Top.kstop6p);
		}
		if (Top.kstop6p == null || Top.kstop6p.equalsIgnoreCase("Ninguém")) {
			top6meta.setDisplayName("§cNinguém");
		} else {
			top6meta.setDisplayName(getTag(Top.kstop6p) + Top.kstop6p);
		}
		List<String> loretop6 = new ArrayList<String>();
		if (Top.kstop6p == null || Top.kstop6p.equalsIgnoreCase("Ninguém")) {
			loretop6.add("");
			loretop6.add("§fJogador: §cNinguém");
			loretop6.add("§fColocação: 6§");
			loretop6.add("§fGrupo: §c-=X=-");
			loretop6.add("§fKillStreak: §b0");
			loretop6.add("");
		} else {
			loretop6.add("");
			loretop6.add("§fJogador: §7" + Top.kstop6p);
			loretop6.add("§fColocação: 6§");
			loretop6.add("§fGrupo: " + getTag(Top.kstop6p));
			loretop6.add("§fKillStreak: §b" + Top.kstop6v);
			loretop6.add("");
		}
		top6meta.setLore(loretop6);
		top6.setItemMeta(top6meta);

		ItemStack top7 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top7meta = (SkullMeta) top7.getItemMeta();
		if (Top.kstop7p == null || Top.kstop7p.equalsIgnoreCase("Ninguém")) {
			top7meta.setOwner("MHF_Question");
		} else {
			top7meta.setOwner(Top.kstop7p);
		}
		if (Top.kstop7p == null || Top.kstop7p.equalsIgnoreCase("Ninguém")) {
			top7meta.setDisplayName("§cNinguém");
		} else {
			top7meta.setDisplayName(getTag(Top.kstop7p) + Top.kstop7p);
		}
		List<String> loretop7 = new ArrayList<String>();
		if (Top.kstop7p == null || Top.kstop7p.equalsIgnoreCase("Ninguém")) {
			loretop7.add("");
			loretop7.add("§fJogador: §cNinguém");
			loretop7.add("§fColocação: 7§");
			loretop7.add("§fGrupo: §c-=X=-");
			loretop7.add("§fKillStreak: §b0");
			loretop7.add("");
		} else {
			loretop7.add("");
			loretop7.add("§fJogador: §7" + Top.kstop7p);
			loretop7.add("§fColocação: 7§");
			loretop7.add("§fGrupo: " + getTag(Top.kstop7p));
			loretop7.add("§fKillStreak: §b" + Top.kstop7v);
			loretop7.add("");
		}
		top7meta.setLore(loretop7);
		top7.setItemMeta(top7meta);

		ItemStack top8 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top8meta = (SkullMeta) top8.getItemMeta();
		if (Top.kstop8p == null || Top.kstop8p.equalsIgnoreCase("Ninguém")) {
			top8meta.setOwner("MHF_Question");
		} else {
			top8meta.setOwner(Top.kstop8p);
		}
		if (Top.kstop8p == null || Top.kstop8p.equalsIgnoreCase("Ninguém")) {
			top8meta.setDisplayName("§cNinguém");
		} else {
			top8meta.setDisplayName(getTag(Top.kstop8p) + Top.kstop8p);
		}
		List<String> loretop8 = new ArrayList<String>();
		if (Top.kstop8p == null || Top.kstop8p.equalsIgnoreCase("Ninguém")) {
			loretop8.add("");
			loretop8.add("§fJogador: §cNinguém");
			loretop8.add("§fColocação: 8§");
			loretop8.add("§fGrupo: §c-=X=-");
			loretop8.add("§fKillStreak: §b0");
			loretop8.add("");
		} else {
			loretop8.add("");
			loretop8.add("§fJogador: §7" + Top.kstop8p);
			loretop8.add("§fColocação: 8§");
			loretop8.add("§fGrupo: " + getTag(Top.kstop8p));
			loretop8.add("§fKillStreak: §b" + Top.kstop8v);
			loretop8.add("");
		}
		top8meta.setLore(loretop8);
		top8.setItemMeta(top8meta);

		ItemStack top9 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top9meta = (SkullMeta) top9.getItemMeta();
		if (Top.kstop9p == null || Top.kstop9p.equalsIgnoreCase("Ninguém")) {
			top9meta.setOwner("MHF_Question");
		} else {
			top9meta.setOwner(Top.kstop9p);
		}
		if (Top.kstop9p == null || Top.kstop9p.equalsIgnoreCase("Ninguém")) {
			top9meta.setDisplayName("§cNinguém");
		} else {
			top9meta.setDisplayName(getTag(Top.kstop9p) + Top.kstop9p);
		}
		List<String> loretop9 = new ArrayList<String>();
		if (Top.kstop9p == null || Top.kstop9p.equalsIgnoreCase("Ninguém")) {
			loretop9.add("");
			loretop9.add("§fJogador: §cNinguém");
			loretop9.add("§fColocação: 9§");
			loretop9.add("§fGrupo: §c-=X=-");
			loretop9.add("§fKillStreak: §b0");
			loretop9.add("");
		} else {
			loretop9.add("");
			loretop9.add("§fJogador: §7" + Top.kstop9p);
			loretop9.add("§fColocação: 9§");
			loretop9.add("§fGrupo: " + getTag(Top.kstop9p));
			loretop9.add("§fKillStreak: §b" + Top.kstop9v);
			loretop9.add("");
		}
		top9meta.setLore(loretop9);
		top9.setItemMeta(top9meta);

		ItemStack top10 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta top10meta = (SkullMeta) top10.getItemMeta();
		if (Top.kstop10p == null || Top.kstop10p.equalsIgnoreCase("Ninguém")) {
			top10meta.setOwner("MHF_Question");
		} else {
			top10meta.setOwner(Top.kstop10p);
		}
		if (Top.kstop10p == null || Top.kstop10p.equalsIgnoreCase("Ninguém")) {
			top10meta.setDisplayName("§cNinguém");
		} else {
			top10meta.setDisplayName(getTag(Top.kstop10p) + Top.kstop10p);
		}
		List<String> loretop10 = new ArrayList<String>();
		if (Top.kstop10p == null || Top.kstop10p.equalsIgnoreCase("Ninguém")) {
			loretop10.add("");
			loretop10.add("§fJogador: §cNinguém");
			loretop10.add("§fColocação: §b10º");
			loretop10.add("§fGrupo: §c-=X=-");
			loretop10.add("§fKillStreak: §b0");
			loretop10.add("");
		} else {
			loretop10.add("");
			loretop10.add("§fJogador: §7" + Top.kstop10p);
			loretop10.add("§fColocação: §b10º");
			loretop10.add("§fGrupo: " + getTag(Top.kstop10p));
			loretop10.add("§fKillStreak: §b" + Top.kstop10v);
			loretop10.add("");
		}
		top10meta.setLore(loretop10);
		top10.setItemMeta(top10meta);

		kstop.setItem(12, top1);
		kstop.setItem(13, top2);
		kstop.setItem(14, top3);
		kstop.setItem(19, top4);
		kstop.setItem(20, top5);
		kstop.setItem(21, top6);
		kstop.setItem(22, top7);
		kstop.setItem(23, top8);
		kstop.setItem(24, top9);
		kstop.setItem(25, top10);
		s.openInventory(kstop);
	}

}
