package me.rankup.Messages;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rankup.KNRankup;

public class Messages {

	private String path;
	private Object value;
	private File file = new File("plugins" + File.separator + "KNRankup" + File.separator + "messages.yml");

	public Messages() {
	}

	public Messages(Type type) {
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

	public Messages(String path, Object value) {
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
			c.addDefault("Messages.Title", "&8♦ &3K&7N&eRankup &8♦");
			c.addDefault("Messages.NoPerms", "&cYou don't have permission");
			c.addDefault("Messages.RankNotFound",
					"&8&o» &cYour current rank was not found in the config, reseting you to the default rank.");
			c.addDefault("Messages.NextRankNotFound",
					"&8&o» &cYour next rank was not found in the config, please contact an administrator.");
			c.addDefault("Messages.PriceLow",
					"&8&o» The rankup price was set too low in the config, please contact an administrator.");
			c.addDefault("Messages.NotEnoughtMoney", "&cYou need &e%price% &cto rankup");
			c.addDefault("Messages.Rankup", "&aYou ranked up to %rank%");
			c.addDefault("Messages.TargetNotFound", "&e%target% &cwas not found");
			c.addDefault("Messages.RankNotFoundTarget",
					"&8&o» &e%target% &ccurrent rank was not found in the config, reseting him to the default rank.");
			c.addDefault("Messages.CurentRankTarget", "&e%target% &dcurrent rank is %rank%");
			c.addDefault("Messages.CurentRank", "&dYour current rank is %rank%");
			c.addDefault("Messages.RankNotFoundSet",
					"&8&o» &d%rank% &crank was not found in the config, please contact an administrator.");
			c.addDefault("Messages.InventoryClose", "&c✘ Menu has been closed ✘");
			c.addDefault("Messages.CurentRankSet", "&bYou changed &e%target% &brank to &e%rank%");
			c.addDefault("Messages.CurentRankChanged", "&dYour rank have been changed to &e%rank%");
			c.addDefault("Messages.BroadcastRankup", "&8&o» &e%player% ranked up to the rank &e%rank%");
			c.addDefault("Messages.ScoreboardEnabled", "&aYour scoreboard has been enabled");
			c.addDefault("Messages.ScoreboardDisabled", "&cYour scoreboard has been disabled");
			c.options().copyDefaults(true);
			try {
				c.save(this.file);
			} catch (IOException e) {
				KNRankup.sendCM(e.getMessage());
			}
			KNRankup.sendCM("&7&o>> &aMessages config have been created!");
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
			KNRankup.sendCM("&7&o>> &cMessages config have been deleted!");
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
	public String get(Player p, String msg) {
		FileConfiguration c = config();
		String m = c.getString(msg);
		////////////////////////////
		if (m == null) {
			return KNRankup.color("&cMessage not found!");
		}
		if (m.isEmpty()) {
			return KNRankup.color("&cMessage not found!");
		}
		return PlaceholderAPI.setPlaceholders(p, m);
	}

	// Get the message
	public String get(OfflinePlayer target, String msg) {
		FileConfiguration c = config();
		String m = c.getString(msg);
		////////////////////////////
		if (m == null) {
			return KNRankup.color("&cMessage not found!");
		}
		if (m.isEmpty()) {
			return KNRankup.color("&cMessage not found!");
		}
		return PlaceholderAPI.setPlaceholders(null, m);
	}
}