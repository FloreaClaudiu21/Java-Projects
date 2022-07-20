package me.rankup.Versions;

import org.bukkit.entity.Player;

import simple.brainsynder.api.ParticleMaker.Particle;

public interface VersionAPI {
	
	// Send the title
	public void sendTitle(Player p, String title, String subtitle);
	
	// Send the particle
	public void sendParticle(Player p, Particle par);
	
	// Send the actionbar
	public void sendActionBar(Player p, String msg);
}
