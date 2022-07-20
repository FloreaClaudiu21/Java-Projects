package me.afk;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private static File file = new File("plugins" + File.separator + "KNAFKXP" + File.separator + "config.yml");

	// Get the config
	public FileConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(file);
	}

	public boolean create() {
		if (!file.exists()) {
			FileConfiguration c = getConfig();
			//////////////////////////////////
			c.set("Time", 5);
			c.set("XPLevel", 1);
			c.set("MessageEnable", true);
			c.set("Message", "&a&l+ 1 XP");
			c.set("RegionName", "afkzone");
			c.options().copyDefaults(true);
			try {
				c.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
