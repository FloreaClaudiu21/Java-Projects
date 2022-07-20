package me.rankup.Utility;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rankup.KNRankup;
import me.rankup.Configs.Ranks.Ranks;

public class GUI {

	private FileConfiguration config = new Ranks().config();

	public GUI() {

	}

	// Create the ranks menu
	public Inventory menu(Player p) {
		Inventory i = KNRankup.s.createInventory(null, 54, menuName());
		/////////////////////////////////////////////////////////////
		for (String rank : ranks().getKeys(false)) {
			i.addItem(item(p, rank));
		}
		for (int num = 45; num < 54; num++) {
			i.setItem(num, barrier());
		}
		return i;
	}

	// Menu name
	public String menuName() {
		String path = this.config.getString("RanksGUI");
		String dn = this.config.getString("RanksGUI.Name");
		///////////////////////////////////////////////////
		if ((path != null) && (dn != null)) {
			if (dn.length() > 40) {
				return KNRankup.color(dn.substring(0, 40));
			} else {
				return KNRankup.color(dn);
			}
		}
		return KNRankup.color("&0&l&nRanks Gui");
	}

	// Get the ranks list
	private ConfigurationSection ranks() {
		String r = this.config.getString("Ranks");
		//////////////////////////////////////////
		if (r != null) {
			return this.config.getConfigurationSection("Ranks");
		}
		return null;
	}

	// Make the item
	private ItemStack barrier() {
		ItemStack i = new ItemStack(Material.BARRIER, 1, (short) 0);
		ItemMeta meta = i.getItemMeta();
		////////////////////////////////
		meta.addEnchant(Enchantment.ARROW_FIRE, 1, false);
		meta.setDisplayName(KNRankup.color("&c✘ Close ✘"));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(meta);
		return i;
	}

	// Make the item
	private ItemStack item(Player p, String rank) {
		Rank r = new Rank(rank);
		String path = this.config.getString("RanksGUI");
		String dn = this.config.getString("RanksGUI.ItemName");
		////////////////////////////////////////////////////////
		ItemStack i = r.getItem();
		ItemMeta meta = i.getItemMeta();
		meta.setLore(colorList(p, r, r.getItemLores()));
		if ((path != null) && (dn != null)) {
			if (!dn.isEmpty()) {
				meta.setDisplayName(KNRankup.color(dn.replace("%prefix%", r.getPrefix())));
			}
		} else {
			meta.setDisplayName(KNRankup.color(r.getPrefix()));
		}
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addEnchant(Enchantment.ARROW_FIRE, 1, false);
		i.setItemMeta(meta);
		return i;
	}

	// Color and translate the variables
	private List<String> colorList(Player p, Rank r, List<String> list) {
		Rank nr = new Rank(r.getNextRank());
		List<String> newList = new ArrayList<String>();
		///////////////////////////////////////////////
		for (String msg : list) {
			newList.add(PlaceholderAPI.setPlaceholders(p,
					msg.replace("%prefix%", r.getPrefix()).replace("%price%", String.valueOf(r.getPrice()))
							.replace("%nextrank%", nr.getPrefix())
							.replace("%money%", String.valueOf(KNRankup.economy.getBalance(p)))));
		}
		return newList;
	}
}
