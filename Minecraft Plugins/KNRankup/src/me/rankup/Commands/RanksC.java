package me.rankup.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.rankup.KNRankup;
import me.rankup.Messages.Messages;
import me.rankup.Utility.GUI;

public class RanksC implements CommandExecutor {

	private Messages msgs = new Messages();

	public RanksC() {
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			return true;
		}
		Player p = (Player) sender;
		///////////////////////////
		if (!p.hasPermission("rankup.rank")) {
			KNRankup.getAPI().sendTitle(p, this.msgs.get(p, "Messages.Title"),
					this.msgs.get(p, "Messages.NoPerms"));
			return false;
		}
		p.openInventory(new GUI().menu(p));
		return false;
	}
}