package me.rankup.Configs.Players;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.rankup.KNRankup;
import me.rankup.Messages.Type;
import me.rankup.Utility.Rank;

public class Players {

	private String path;
	private Object value;
	private File file = new File("plugins" + File.separator + "KNRankup" + File.separator + "players.yml");

	public Players() {
	}

	public Players(Type type) {
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

	public Players(String path, Object value) {
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
			c.addDefault("Players", "");
			c.addDefault("DisabledS", Arrays.asList(""));
			c.options().copyDefaults(true);
			try {
				c.save(this.file);
			} catch (IOException e) {
				KNRankup.sendCM(e.getMessage());
			}
			KNRankup.sendCM("&7&o>> &aPlayers config have been created!");
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
			KNRankup.sendCM("&7&o>> &cPlayers config have been deleted!");
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

	// Get the current rank
	public String getCRank(Player p) {
		if (p == null) {
			return null;
		}
		String name = p.getName().toLowerCase();
		////////////////////////////////////////
		String p1 = get("Players");
		String p2 = get("Players." + name);
		String p3 = get("Players." + name + ".Rank");
		/////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null) {
			if (!p3.isEmpty()) {
				return ChatColor.stripColor(p3);
			}
		}
		Rank.executeRankup(p, new Rank(null).getDefRank());
		new Players("Players." + name + ".Rank", new Rank(null).getDefRank()).set();
		return new Rank(null).getDefRank();
	}

	// Get the current rank
	public List<String> getDisabledS() {
		String p1 = get("DisabledS");
		/////////////////////////////
		if (p1 != null) {
			List<String> list = config().getStringList("DisabledS");
			////////////////////////////////////////////////////////
			if ((list != null) && (!list.isEmpty())) {
				return list;
			}
		}
		return Arrays.asList("");
	}

	// Get the message
	public String get(String msg) {
		FileConfiguration c = config();
		String m = c.getString(msg);
		return m;
	}

	// Get the default rank
	public String getCRank(OfflinePlayer p) {
		if (p == null) {
			return null;
		}
		String name = p.getName().toLowerCase();
		////////////////////////////////////////
		String p1 = get("Players");
		String p2 = get("Players." + name);
		String p3 = get("Players." + name + ".Rank");
		/////////////////////////////////////////////
		if (p1 != null && p2 != null && p3 != null) {
			if (!p3.isEmpty()) {
				return ChatColor.stripColor(p3);
			}
		}
		Rank.executeRankup(p, new Rank(null).getDefRank());
		new Players("Players." + name + ".Rank", new Rank(null).getDefRank()).set();
		return new Rank(null).getDefRank();
	}
}