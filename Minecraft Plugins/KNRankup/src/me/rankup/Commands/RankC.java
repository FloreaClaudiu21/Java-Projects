package me.rankup.Commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.rankup.KNRankup;
import me.rankup.Configs.Players.Players;
import me.rankup.Messages.Messages;
import me.rankup.Utility.Rank;
import simple.brainsynder.api.ParticleMaker.Particle;

public class RankC implements CommandExecutor {

	private Messages msgs = new Messages();

	public RankC() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			return true;
		}
		Player p = (Player) sender;
		String name = p.getName().toLowerCase();
		////////////////////////////////////////
		if (args.length == 0) {
			if (!p.hasPermission("rankup.rank")) {
				KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
						this.msgs.get(p, "Messages.NoPerms"));
				return false;
			}
			String cr = new Players().getCRank(p);
			//////////////////////////////////////
			if (cr == null) {
				p.sendMessage(this.msgs.get(p, "Messages.RankNotFound"));
				return false;
			}
			Rank curentRank = new Rank(cr);
			///////////////////////////////
			if (!curentRank.exists()) {
				Rank.executeRankup(p, curentRank.getDefRank());
				new Players("Players." + name + ".Rank", curentRank.getDefRank()).set();
				p.sendMessage(this.msgs.get(p, "Messages.RankNotFound"));
				return false;
			}
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
					this.msgs.get(p, "Messages.CurentRank").replace("%rank%", curentRank.getPrefix()));
			return true;
		}
		if (args.length == 1) {
			if (!p.hasPermission("rankup.rank.others")) {
				KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
						this.msgs.get(p, "Messages.NoPerms"));
				return false;
			}
			String t = args[0];
			///////////////////
			if (t == null) {
				return false;
			}
			if (t.isEmpty()) {
				return false;
			}
			@SuppressWarnings("deprecation")
			OfflinePlayer target = KNRankup.s.getOfflinePlayer(t);
			//////////////////////////////////////////////////////
			if (target == null) {
				KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
						this.msgs.get(target, "Messages.TargetNotFound").replace("%target%", t));
				return false;
			}
			String cr = new Players().getCRank(target);
			String tname = target.getName().toLowerCase();
			//////////////////////////////////////////////
			if (cr == null) {
				if (target.isOnline()) {
					Player pt = (Player) target;
					////////////////////////////
					pt.sendMessage(this.msgs.get(pt, "Messages.RankNotFound"));
					p.sendMessage(
							this.msgs.get(p, "Messages.RankNotFoundTarget").replace("%target%", pt.getDisplayName()));
					return false;
				} else {
					p.sendMessage(
							this.msgs.get(p, "Messages.RankNotFoundTarget").replace("%target%", target.getName()));
					return false;
				}
			}
			Rank curentRank = new Rank(cr);
			///////////////////////////////
			if (!curentRank.exists()) {
				Rank.executeRankup(target, curentRank.getDefRank());
				new Players("Players." + tname + ".Rank", curentRank.getDefRank()).set();
				if (target.isOnline()) {
					Player pt = (Player) target;
					////////////////////////////
					pt.sendMessage(this.msgs.get(pt, "Messages.RankNotFound"));
					p.sendMessage(
							this.msgs.get(p, "Messages.RankNotFoundTarget").replace("%target%", pt.getDisplayName()));
					return false;
				} else {
					p.sendMessage(
							this.msgs.get(p, "Messages.RankNotFoundTarget").replace("%target%", target.getName()));
					return false;
				}
			}
			String tname1 = target.getName();
			/////////////////////////////////
			if (target.isOnline()) {
				Player pt = (Player) target;
				////////////////////////////
				tname1 = pt.getDisplayName();
			}
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
					this.msgs.get(p, "Messages.CurentRankTarget").replace("%rank%", curentRank.getPrefix())
							.replace("%target%", tname1));
			return true;
		}
		if (args.length == 2) {
			if (!p.hasPermission("rankup.rank.set")) {
				KNRankup.getAPI().sendParticle(p, Particle.FLAME);
				KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
						this.msgs.get(p, "Messages.NoPerms"));
				return false;
			}
			String t = args[0];
			String rank = args[1];
			//////////////////////
			if (t == null) {
				return false;
			}
			if (t.isEmpty()) {
				return false;
			}
			if (rank == null) {
				return false;
			}
			if (rank.isEmpty()) {
				return false;
			}
			Player target = KNRankup.s.getPlayer(t);
			//////////////////////////////////////////////
			if (target == null) {
				KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
						this.msgs.get(target, "Messages.TargetNotFound").replace("%target%", t));
				return false;
			}
			if (!target.isOnline()) {
				KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
						this.msgs.get(target, "Messages.TargetNotFound").replace("%target%", t));
				return false;
			}
			Rank r = new Rank(rank);
			String tname = target.getName().toLowerCase();
			//////////////////////////////////////////////
			if (!r.exists()) {
				p.sendMessage(this.msgs.get(p, "Messages.RankNotFoundSet").replace("%rank%", rank));
				return false;
			}
			Rank.executeRankup(target, rank);
			new Players("Players." + tname + ".Rank", rank).set();
			KNRankup.getAPI().sendTitle(target, this.msgs.get(p, "Messages.Title"),
					this.msgs.get(target, "Messages.CurentRankChanged").replace("%rank%", r.getPrefix()));
			p.sendMessage(this.msgs.get(p, "Messages.CurentRankSet").replace("%rank%", r.getPrefix())
					.replace("%target%", target.getDisplayName()));
			return true;
		}
		return false;
	}
}