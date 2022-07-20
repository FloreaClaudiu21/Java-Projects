package me.rankup.Configs.Logs;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.rankup.KNRankup;
import me.rankup.Messages.Type;

public class Logs {

	private String path;
	private Object value;
	private File file = new File("plugins" + File.separator + "KNRankup" + File.separator + "logs.txt");
	private String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	public Logs() {
	}

	public Logs(Type type) {
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

	public Logs(String path, Object value) {
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
			c.addDefault("Default", "");
			c.options().copyDefaults(true);
			try {
				c.save(this.file);
			} catch (IOException e) {
				KNRankup.sendCM(e.getMessage());
			}
			KNRankup.sendCM("&7&o>> &aLogs config have been created!");
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
			KNRankup.sendCM("&7&o>> &cLogs config have been deleted!");
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
	public void setRankupLog(Player p, String rank) {
		if (p == null) {
			return;
		}
		FileConfiguration co = config();
		GregorianCalendar c = new GregorianCalendar();
		//////////////////////////////////////////////
		String format = "[" + c.get(Calendar.DAY_OF_MONTH) + months[c.get(Calendar.MONTH)] +
				+ c.get(Calendar.YEAR) + "/" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND) + "] ";
		co.set(format + p.getName() + " ranked up to the rank " + rank, "");
		save(co);
		return;
	}

	// Get the message
	public String get(String msg) {
		FileConfiguration c = config();
		String m = c.getString(msg);
		return m;
	}
}