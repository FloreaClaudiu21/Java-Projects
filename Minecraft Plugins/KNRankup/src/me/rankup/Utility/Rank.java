package me.rankup.Utility;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.rankup.KNRankup;
import me.rankup.Configs.Ranks.Ranks;

public class Rank {

	private String r;

	public Rank(String rank) {
		this.r = rank;
	}

	// Check if the rank exists
	public boolean exists() {
		if (this.r == null) {
			return false;
		}
		if (this.r.isEmpty()) {
			return false;
		}
		String p1 = new Ranks().get("Ranks");
		String p2 = new Ranks().get("Ranks." + this.r);
		String p3 = new Ranks().get("Ranks." + this.r + ".Price");
		String p4 = new Ranks().get("Ranks." + this.r + ".Next_Rank");
		String p5 = new Ranks().get("Ranks." + this.r + ".Prefix");
		//////////////////////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) {
			return true;
		}
		return false;
	}

	// Get the default rank
	public String getDefRank() {
		String def = new Ranks().get("Default_Rank");
		/////////////////////////////////////////////
		if ((def != null) && (!def.isEmpty())) {
			return ChatColor.stripColor(def);
		}
		return "A";
	}

	// Execute the rankup cmd
	public static void executeRankup(Player p, String rank) {
		String def = new Ranks().get("Rankup_Cmd");
		///////////////////////////////////////////
		if ((def != null) && (!def.isEmpty())) {
			KNRankup.s.dispatchCommand(KNRankup.console, def.replace("%player%", p.getName()).replace("%group%", rank));
			return;
		}
	}
	
	// Execute the rankup cmd
	public static void executeRankup(OfflinePlayer p, String rank) {
		String def = new Ranks().get("Rankup_Cmd");
		///////////////////////////////////////////
		if ((def != null) && (!def.isEmpty())) {
			KNRankup.s.dispatchCommand(KNRankup.console, def.replace("%player%", p.getName()).replace("%group%", rank));
			return;
		}
	}

	// Get next rank
	public String getNextRank() {
		String p1 = new Ranks().get("Ranks");
		String p2 = new Ranks().get("Ranks." + this.r);
		String p3 = new Ranks().get("Ranks." + this.r + ".Price");
		String p4 = new Ranks().get("Ranks." + this.r + ".Next_Rank");
		String p5 = new Ranks().get("Ranks." + this.r + ".Prefix");
		//////////////////////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) {
			if (!p4.isEmpty()) {
				return ChatColor.stripColor(p4);
			}
		}
		return null;
	}

	// Get the item
	public ItemStack getItem() {
		String p1 = new Ranks().get("Ranks");
		String p2 = new Ranks().get("Ranks." + this.r);
		String p3 = new Ranks().get("Ranks." + this.r + ".Item");
		String p4 = new Ranks().get("Ranks." + this.r + ".Item.Material");
		String p5 = new Ranks().get("Ranks." + this.r + ".Item.ID");
		ItemStack i = new ItemStack(Material.STONE, 1, (short) 0);
		//////////////////////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) {
			int id = Integer.valueOf(p5);
			Material mat = Material.valueOf(p4.toUpperCase());
			//////////////////////////////////////////////////
			if (mat != null && id > 0) {
				i = new ItemStack(mat, 1, (short) id);
			}
		}
		return i;
	}

	// Get the item lores
	public List<String> getItemLores() {
		String p1 = new Ranks().get("Ranks");
		String p2 = new Ranks().get("Ranks." + this.r);
		String p3 = new Ranks().get("Ranks." + this.r + ".Item");
		String p4 = new Ranks().get("Ranks." + this.r + ".Item.Material");
		String p5 = new Ranks().get("Ranks." + this.r + ".Item.ID");
		String p6 = new Ranks().get("Ranks." + this.r + ".Item.Lores");
		///////////////////////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null && p6 != null) {
			List<String> lores = new Ranks().config().getStringList("Ranks." + this.r + ".Item.Lores");
			///////////////////////////////////////////////////////////////////////////////////////////
			if ((lores != null) && (!lores.isEmpty())) {
				return lores;
			}
		}
		return Arrays.asList("&e");
	}

	// Get rank price
	public int getPrice() {
		String p1 = new Ranks().get("Ranks");
		String p2 = new Ranks().get("Ranks." + this.r);
		String p3 = new Ranks().get("Ranks." + this.r + ".Price");
		String p4 = new Ranks().get("Ranks." + this.r + ".Next_Rank");
		String p5 = new Ranks().get("Ranks." + this.r + ".Prefix");
		//////////////////////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) {
			if (!p3.isEmpty()) {
				int p = Integer.valueOf(p3);
				////////////////////////////
				if (p > 0) {
					return p;
				}
			}
		}
		return 0;
	}

	// Get the prefix
	public String getPrefix() {
		String p1 = new Ranks().get("Ranks");
		String p2 = new Ranks().get("Ranks." + this.r);
		String p3 = new Ranks().get("Ranks." + this.r + ".Price");
		String p4 = new Ranks().get("Ranks." + this.r + ".Next_Rank");
		String p5 = new Ranks().get("Ranks." + this.r + ".Prefix");
		//////////////////////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) {
			if (!p5.isEmpty()) {
				return KNRankup.color(p5);
			}
		}
		return "Prefix not found";
	}
}