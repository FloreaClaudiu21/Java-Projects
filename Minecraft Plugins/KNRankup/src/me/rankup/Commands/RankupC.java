package me.rankup.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import me.rankup.KNRankup;
import me.rankup.Configs.Logs.Logs;
import me.rankup.Configs.Players.Players;
import me.rankup.Messages.Messages;
import me.rankup.Utility.Rank;
import simple.brainsynder.api.ParticleMaker.Particle;

public class RankupC implements CommandExecutor {

	public RankupC() {
	}

	private Messages msgs = new Messages();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			return true;
		}
		Player p = (Player) sender;
		String name = p.getName().toLowerCase();
		////////////////////////////////////////
		if (!p.hasPermission("rankup.rankup")) {
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"), this.msgs.get(p, "Messages.NoPerms"));
			return false;
		}
		String cr = new Players().getCRank(p);
		//////////////////////////////////////
		if (cr == null) {
			p.sendMessage(this.msgs.get(p, "Messages.RankNotFound"));
			return false;
		}
		Rank currank = new Rank(cr);
		////////////////////////////
		if (!currank.exists()) {
			Rank.executeRankup(p, currank.getDefRank());
			new Players("Players." + name + ".Rank", new Rank(null).getDefRank()).set();
			p.sendMessage(this.msgs.get(p, "Messages.RankNotFound"));
			return false;
		}
		String nr = currank.getNextRank();
		//////////////////////////////////
		if (nr == null) {
			p.sendMessage(this.msgs.get(p, "Messages.NextRankNotFound"));
			return false;
		}
		Rank nextrank = new Rank(nr);
		/////////////////////////////
		if (!nextrank.exists()) {
			p.sendMessage(this.msgs.get(p, "Messages.NextRankNotFound"));
			return false;
		}
		int price = nextrank.getPrice();
		////////////////////////////////
		if (price <= 0) {
			p.sendMessage(this.msgs.get(p, "Messages.PriceLow"));
			return false;
		}
		int money = (int) KNRankup.economy.getBalance(p);
		///////////////////////////////////////////////
		if (money < price) {
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
					this.msgs.get(p, "Messages.NotEnoughtMoney").replace("%price%", String.valueOf(price)));
			return false;
		}
		Rank.executeRankup(p, nr);
		KNRankup.spawnFirework(p);
		new Logs().setRankupLog(p, nextrank.getPrefix());
		KNRankup.getAPI().sendParticle(p, Particle.CLOUD);
		KNRankup.s.broadcastMessage(this.msgs.get(p, "Messages.BroadcastRankup").replace("%player%", p.getDisplayName())
				.replace("%rank%", nextrank.getPrefix()));
		KNRankup.s.dispatchCommand(KNRankup.console, "eco take " + p.getName() + " " + price);
		KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
				this.msgs.get(p, "Messages.Rankup").replace("%rank%", nextrank.getPrefix()));
		new Players("Players." + name + ".Rank", nr).set();
		return true;
	}
}