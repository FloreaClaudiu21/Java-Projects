package me.rankup.Versions;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rankup.Configs.Ranks.Ranks;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import simple.brainsynder.api.ParticleMaker;
import simple.brainsynder.api.ParticleMaker.Particle;
import simple.brainsynder.nms.IActionMessage;
import simple.brainsynder.utils.Reflection;

public class v1_12_1 implements VersionAPI {

	public v1_12_1() {
	}

	// Send the title to the player
	public void sendTitle(Player p, String title, String subtitle) {
		if (p == null) {
			return;
		}
		CraftPlayer cp = (CraftPlayer) p;
		PlayerConnection pc = cp.getHandle().playerConnection;
		//////////////////////////////////////////////////////
		PacketPlayOutTitle times = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, 40, 20);
		pc.sendPacket(times);
		/////////////////////
		if (title != null) {
			PacketPlayOutTitle t = new PacketPlayOutTitle(EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer
					.a("{\"text\": \"" + PlaceholderAPI.setPlaceholders(p, title) + "\"}"));
			pc.sendPacket(t);
		}
		if (subtitle != null) {
			PacketPlayOutTitle st = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer
					.a("{\"text\": \"" + PlaceholderAPI.setPlaceholders(p, subtitle) + "\"}"));
			pc.sendPacket(st);
		}
		return;
	}

	// Send the particle
	public void sendParticle(Player p, Particle par) {
		Location loc = p.getLocation();
		Ranks rank = new Ranks();
		FileConfiguration c = rank.config();
		boolean enabled = c.getBoolean("Particles");
		////////////////////////////////////////////
		if (enabled) {
			ParticleMaker pm = new ParticleMaker(par, 0.005F, 3000, 0.8, 1.3, 0.8);
			pm.sendToLocation(loc.add(0, 1, 0));
			return;
		}
		return;
	}

	// Send the actionbar
	public void sendActionBar(Player p, String msg) {
		if (p == null) {
			return;
		}
		if (msg == null) {
			return;
		}
		IActionMessage message = Reflection.getActionMessage();
		///////////////////////////////////////////////////////
		message.sendMessage(p, PlaceholderAPI.setPlaceholders(p, msg));
		return;
	}
}
