package me.rankup.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.rankup.KNRankup;
import me.rankup.Configs.Players.Players;
import me.rankup.Utility.Rank;

public class onPlayerFirstJoin implements Listener {

	public onPlayerFirstJoin() {
	}

	@EventHandler
	public void onFirstJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();
		//////////////////////////
		if (!p.hasPlayedBefore()) {
			new Players("Players." + name.toLowerCase() + ".Rank", new Rank(null).getDefRank()).set();
			KNRankup.sendCM("&7&o>> &e" + name + "&d joined for the first time, created the config successfully...");
			return;
		}
	}
}