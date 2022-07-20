package me.rankup.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.rankup.KNRankup;
import me.rankup.Configs.Players.Players;
import me.rankup.Messages.Messages;

public class ToggleC implements CommandExecutor {

	private Messages msgs = new Messages();

	public ToggleC() {
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			return true;
		}
		Player p = (Player) sender;
		///////////////////////////
		if (!p.hasPermission("rankup.toggle")) {
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"), this.msgs.get(p, "Messages.NoPerms"));
			return false;
		}
		Players pl = new Players();
		List<String> ds = pl.getDisabledS();
		String name = p.getName().toLowerCase();
		////////////////////////////////////////
		if (ds.contains(name)) {
			ds.remove(name);
			new Players("DisabledS", ds).set();
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"), this.msgs.get(p, "Messages.ScoreboardEnabled"));
			return true;
		} else {
			ds.add(name);
			new Players("DisabledS", ds).set();
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"), this.msgs.get(p, "Messages.ScoreboardDisabled"));
			return true;
		}
	}
}