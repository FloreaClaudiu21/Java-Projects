package me.afk;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.md_5.bungee.api.ChatColor;
import simple.brainsynder.nms.IActionMessage;
import simple.brainsynder.utils.Reflection;

public class KNAFK extends JavaPlugin {

	@SuppressWarnings("unused")
	private int task = 0;
	public static Plugin pl;
	public static Plugin wg;
	public static Server s = Bukkit.getServer();
	public static ConsoleCommandSender console = s.getConsoleSender();

	/*
	 * ####################################### ############# KNAFKXP MAIN
	 * ############## #######################################
	 */

	// Enable the plugin
	public void onEnable() {
		pl = this;
		wg = worldguard();
		scheduler();
		new Config().create();
		PluginDescriptionFile d = pl.getDescription();
		///////////////////////////////////////////////
		sendCM("&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		sendCM("&7&o>> &eName: KNAFKXP");
		sendCM("&7&o>> &eMain: " + d.getMain());
		sendCM("&7&o>> &eAuthor: " + d.getAuthors().toString().replace("[", ""));
		sendCM("&7&o>> &eVersion: &c" + d.getVersion());
		sendCM("&7&o>> &eDescription: " + d.getDescription());
		sendCM("&7&o>> &eBukkit Version: " + s.getBukkitVersion());
		sendCM("&e");
		sendCM("&2Plugin has been enabled, thanks for using it :)");
		sendCM("&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		///////////////////////////////////////////////
		console.sendMessage(color("&b&lKNAFKXP &8>> &dWorldGuard plugin found. Starting the plugin...."));
		return;
	}

	// Disable the plugin
	public void onDisable() {
		PluginDescriptionFile d = pl.getDescription();
		//////////////////////////////////////////////
		sendCM("&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		sendCM("&7&o>> &eName: KNAFKXP");
		sendCM("&7&o>> &eMain: " + d.getMain());
		sendCM("&7&o>> &eAuthor: " + d.getAuthors().toString().replace("[", ""));
		sendCM("&7&o>> &eVersion: &c" + d.getVersion());
		sendCM("&7&o>> &eDescription: " + d.getDescription());
		sendCM("&7&o>> &eBukkit Version: " + s.getBukkitVersion());
		sendCM("&e");
		sendCM("&cPlugin has been disabled :(");
		sendCM("&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		return;
	}

	/*
	 * ####################################### ########### KNAFKXP METHODS
	 * ################ ##########################################
	 */

	// Color the messages
	public static String color(String msg) {
		if (msg == null) {
			return ChatColor.translateAlternateColorCodes('&', "&cMessage not found");
		}
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	// Get the worldguard manager
	public WorldGuardPlugin wgmanager() {
		WorldGuardPlugin wg = (WorldGuardPlugin) worldguard();
		//////////////////////////////////////////////////////
		if (wg == null) {
			return null;
		}
		return wg;
	}

	// Get the worldguard plugin
	public Plugin worldguard() {
		Plugin plugin = s.getPluginManager().getPlugin("WorldGuard");
		///////////////////////////////////////////////////////////////////////
		if (plugin == null) {
			return null;
		}
		if (!plugin.isEnabled()) {
			return null;
		}
		return plugin;
	}

	// Get the plugin
	public static Plugin getPlugin() {
		return pl;
	}

	// Send console msg
	public static void sendCM(String m) {
		if (m == null) {
			return;
		}
		if (m.isEmpty()) {
			return;
		}
		console.sendMessage(color(m));
		return;
	}

	/*
	 * ####################################### ########### AFKXP SCHEDULER
	 * ############## ##########################################
	 */

	// Schedule the xp
	public int scheduler() {
		FileConfiguration c = new Config().getConfig();
		int time = c.getInt("Time");
		//////////////////////////////////////////////////////////////////////////////////////////////////
		console.sendMessage(color("&b&lKNAFKXP &8>> &aScheduler started!"));
		return this.task = s.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Player p : s.getOnlinePlayers()) {
					World world = p.getWorld();
					Location loc = p.getLocation();
					int xp = c.getInt("XPLevel");
					String regionn = c.getString("RegionName");
					boolean msg = c.getBoolean("MessageEnable");
					String msg1 = color(c.getString("Message"));
					/////////////////////////////////////////////
					RegionManager rm = wgmanager().getRegionManager(world);
					ApplicableRegionSet regions = rm.getApplicableRegions(loc);
					///////////////////////////////////////////////////////////
					for (ProtectedRegion region : regions.getRegions()) {
						if (region.getId().equalsIgnoreCase(regionn)) {
							p.giveExp(xp);
							if (msg) {
								IActionMessage message = Reflection.getActionMessage();
								///////////////////////////////////////////////////////
								message.sendMessage(p, msg1);
							}
						}
					}
				}
			}
		}, 0L, time * 20L);
	}

	/*
	 * =============================================================================
	 * =====================================================
	 */
}