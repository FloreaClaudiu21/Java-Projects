package me.rankup;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.rankup.Commands.RankC;
import me.rankup.Commands.RanksC;
import me.rankup.Commands.RankupC;
import me.rankup.Commands.ToggleC;
import me.rankup.Configs.Logs.Logs;
import me.rankup.Configs.Players.Players;
import me.rankup.Configs.Ranks.Ranks;
import me.rankup.Events.onPlayerClickMenu;
import me.rankup.Events.onPlayerFirstJoin;
import me.rankup.Messages.Messages;
import me.rankup.Messages.Type;
import me.rankup.Utility.Scoreboard;
import me.rankup.Versions.VersionAPI;
import me.rankup.Versions.v1_12_1;
import net.milkbowl.vault.economy.Economy;

public class KNRankup extends JavaPlugin {

	private static Plugin pl;
	public static Economy economy = null;
	public static Server s = Bukkit.getServer();
	public static ConsoleCommandSender console = s.getConsoleSender();

	public KNRankup() {
	}

	// On plugin enable
	public void onEnable() {
		pl = this;
		PluginManager pm = s.getPluginManager();
		PluginDescriptionFile d = pl.getDescription();
		///////////////////////////////////////////////
		sendCM("&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		sendCM("&7&o>> &eName: KNRankup");
		sendCM("&7&o>> &eMain: " + d.getMain());
		sendCM("&7&o>> &eAuthor: " + d.getAuthors().toString().replace("[", ""));
		sendCM("&7&o>> &eVersion: &c" + d.getVersion());
		sendCM("&7&o>> &eDescription: " + d.getDescription());
		sendCM("&7&o>> &eBukkit Version: " + s.getBukkitVersion());
		sendCM("&e");
		sendCM("&2Plugin has been enabled, thanks for using it :)");
		sendCM("&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		setupEconomy();
		new Messages(Type.CREATE);
		new Messages(Type.SAVE);
		new Players(Type.CREATE);
		new Players(Type.SAVE);
		new Ranks(Type.CREATE);
		new Ranks(Type.SAVE);
		new Logs(Type.CREATE);
		new Logs(Type.SAVE);
		new Scoreboard().start();
		pm.registerEvents(new onPlayerFirstJoin(), this);
		pm.registerEvents(new onPlayerClickMenu(), this);
		this.getCommand("rankup").setExecutor(new RankupC());
		this.getCommand("rank").setExecutor(new RankC());
		this.getCommand("ranks").setExecutor(new RanksC());
		this.getCommand("toggle").setExecutor(new ToggleC());
		return;
	}

	// On plugin disable
	public void onDisable() {
		PluginDescriptionFile d = pl.getDescription();
		///////////////////////////////////////////////
		sendCM("&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		sendCM("&7&o>> &eName: KNRankup");
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
		console.sendMessage(KNRankup.color(m));
		return;
	}

	// Setup the economy
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	// Get the api
	public static VersionAPI getAPI() {
		return new v1_12_1();
	}

	// Spawn the firework
	public static void spawnFirework(Player p) {
		Firework fire = (Firework) p.getWorld().spawnEntity(p.getLocation().add(0, 1, 0), EntityType.FIREWORK);
		FireworkMeta meta = fire.getFireworkMeta();
		///////////////////////////////////////////
		for (Color color : colors()) {
			meta.addEffects(FireworkEffect.builder().with(FireworkEffect.Type.CREEPER).flicker(true).trail(true).withTrail().withFlicker().withColor(color)
					.withFade(color).build());
		}
		meta.setPower(7);
		fire.setFireworkMeta(meta);
		KNRankup.s.getScheduler().scheduleSyncDelayedTask(KNRankup.pl, new Runnable() {
			public void run() {
				fire.detonate();
			}
		}, 10L);
		return;
	}

	private static List<Color> colors() {
		List<Color> list = new ArrayList<Color>();
		//////////////////////////////////////////
		list.add(Color.AQUA);
		list.add(Color.BLACK);
		list.add(Color.ORANGE);
		list.add(Color.FUCHSIA);
		list.add(Color.MAROON);
		list.add(Color.RED);
		list.add(Color.YELLOW);
		list.add(Color.WHITE);
		return list;
	}

	// Color the message
	public static String color(String m) {
		return ChatColor.translateAlternateColorCodes('&', m);
	}
}