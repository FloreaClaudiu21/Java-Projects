package me.rankup.Configs.Ranks;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.rankup.KNRankup;
import me.rankup.Messages.Type;

public class Ranks {

	private String path;
	private Object value;
	private File file = new File("plugins" + File.separator + "KNRankup" + File.separator + "ranks.yml");

	public Ranks() {
	}

	public Ranks(Type type) {
		switch (type) {
		case CREATE: {
			create();
			break;
		}
		case DELETE: {
			delete(true);
			break;
		}
		case SAVE: {
			save();
			break;
		}
		default:
			break;
		}
	}

	public Ranks(String path, Object value) {
		this.path = path;
		this.value = value;
	}

	// Get the config
	public FileConfiguration config() {
		return YamlConfiguration.loadConfiguration(file);
	}

	// Create the config
	public boolean create() {
		if (!this.file.exists()) {
			FileConfiguration c = config();
			///////////////////////////////
			c.addDefault("Particles", true);
			c.addDefault("Default_Rank", "A");
			c.addDefault("Rankup_Cmd", "manuadd %player% %group%");
			c.addDefault("Ranks.A.Price", 0);
			c.addDefault("Ranks.A.Next_Rank", "B");
			c.addDefault("Ranks.A.Prefix", "&8[&b&lA&8]");
			c.addDefault("Ranks.A.Item.Material", "stone");
			c.addDefault("Ranks.A.Item.ID", "1");
			c.addDefault("Ranks.A.Item.Lores", Arrays.asList("&e", "&7> &bPrefix: %prefix%", "&7> &bNext Rank: %nextrank%", "&7> &bCost: &c%price%$", "&7> &bBalance: &a%money%$", "&8&o-=-=-=-=-=-=-=-=-=-=-=-"));
			c.addDefault("Ranks.B.Price", 10000);
			c.addDefault("Ranks.B.Next_Rank", "C");
			c.addDefault("Ranks.B.Prefix", "&8[&d&lB&8]");
			c.addDefault("Ranks.B.Item.Material", "stone");
			c.addDefault("Ranks.B.Item.ID", "3");
			c.addDefault("Ranks.B.Item.Lores", Arrays.asList("&e", "&7> &bPrefix: %prefix%", "&7> &bNext Rank: %nextrank%", "&7> &bCost: &c%price%$", "&7> &bBalance: &a%money%$", "&8&o-=-=-=-=-=-=-=-=-=-=-=-"));
			c.addDefault("RanksGUI.Name", "&0&l&nAvailable ranks:");
			c.addDefault("RanksGUI.ItemName", "&k&6>>>> &f%prefix% &k&6<<<<");
			c.addDefault("Scoreboard.Title", "&3K&7N&eRankup");
			c.addDefault("Scoreboard.Lines", Arrays.asList("&e", "&6Player Statistics", "  &8&o» &bPing: &f%player_ping%", "  &8&o» &bPlayer: &f%player_name%", "  &8&o» &bWorld: &f%player_world%", "  &8&o» &bRank: &f%rank%", "  &8&o» &bMoney: %vault_eco_balance_formatted%", "  &8&o» &bNext Rank: &f%nextrank%", "&a", "&6Server Statistics", "  &8&o» &bName: &f%server_name%", "  &8&o» &bTPS: &f%server_tps%", "&8-=-=-=-=-=-=-=-=-=-=-=-"));
			c.addDefault("Scoreboard.DisabledWorlds", Arrays.asList("world_the_end"));
			c.options().copyDefaults(true);
			try {
				c.save(this.file);
			} catch (IOException e) {
				KNRankup.sendCM(e.getMessage());
			}
			KNRankup.sendCM("&7&o>> &aRanks config have been created!");
			return true;
		}
		return false;
	}

	// Delete the config
	public boolean delete(boolean save) {
		if (this.file.exists()) {
			if (save) {
				save(config());
			}
			this.file.delete();
			KNRankup.sendCM("&7&o>> &cRanks config have been deleted!");
			return true;
		}
		return false;
	}

	// Save the config
	public boolean save() {
		try {
			config().save(file);
		} catch (IOException e) {
			KNRankup.sendCM(e.getMessage());
		}
		return true;
	}

	// Save the config
	public boolean save(FileConfiguration c) {
		try {
			c.save(file);
		} catch (IOException e) {
			KNRankup.sendCM(e.getMessage());
		}
		return true;
	}

	// Set the path
	public boolean set() {
		FileConfiguration c = config();
		///////////////////////////////
		if ((this.path != null) && (!this.path.isEmpty())) {
			c.set(this.path, this.value);
			save(c);
			return true;
		}
		return false;
	}

	// Get the message
	public String get(String msg) {
		FileConfiguration c = config();
		String m = c.getString(msg);
		return m;
	}
}