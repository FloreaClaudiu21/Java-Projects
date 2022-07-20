package me.rankup.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import me.clip.placeholderapi.PlaceholderAPI;
import me.rankup.KNRankup;
import me.rankup.Configs.Players.Players;
import me.rankup.Configs.Ranks.Ranks;

public class Scoreboard {

	@SuppressWarnings("unused")
	private int task;
	private Player p1;
	private FileConfiguration c = new Ranks().config();

	public Scoreboard() {
	}

	public Scoreboard(Player p) {
		this.p1 = p;
	}

	// Send the scoreboard to the player
	public boolean send() {
		if (this.p1 == null) {
			return false;
		}
		org.bukkit.scoreboard.Scoreboard score = KNRankup.s.getScoreboardManager().getNewScoreboard();
		Objective o = score.registerNewObjective(getTitle(), "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		//////////////////////////////////////
		List<String> list = getLines();
		///////////////////////////////
		for (int i = 0; i < list.size(); i++) {
			String line = getLines().get(i);
			////////////////////////////////
			if ((line != null) && (!line.isEmpty())) {
				o.getScore(line).setScore(list.size() - i);
			}
		}
		this.p1.setScoreboard(score);
		return true;
	}

	// Remove the scoreboard
	public void remove() {
		this.p1.setScoreboard(KNRankup.s.getScoreboardManager().getMainScoreboard());
		return;
	}

	// Get the title
	public String getTitle() {
		String path = this.c.getString("Scoreboard");
		String path1 = this.c.getString("Scoreboard.Title");
		////////////////////////////////////////////////////
		if ((path != null) && (path1 != null) && (!path.isEmpty()) && (!path1.isEmpty())) {
			String title = KNRankup.color(path1);
			/////////////////////////////////////
			if (title.length() >= 16) {
				return title.substring(0, 16);
			} else {
				return title;
			}
		}
		return KNRankup.color("&cNot Found");
	}

	// Get the lines
	public List<String> getLines() {
		String path = this.c.getString("Scoreboard");
		String path1 = this.c.getString("Scoreboard.Lines");
		///////////////////////////////////////////////
		if ((path != null) && (path1 != null) && (!path.isEmpty()) && (!path1.isEmpty())) {
			List<String> lines = this.c.getStringList("Scoreboard.Lines");
			//////////////////////////////////////////////////////////////
			if ((lines != null) && (!lines.isEmpty())) {
				return applyVariablesL(lines);
			}
		}
		return Arrays.asList(KNRankup.color("&e"));
	}

	// Get the lines
	public List<String> getDisabledWorlds() {
		String path = this.c.getString("Scoreboard");
		String path1 = this.c.getString("Scoreboard.DisabledWorlds");
		/////////////////////////////////////////////////////////////
		if ((path != null) && (path1 != null) && (!path.isEmpty()) && (!path1.isEmpty())) {
			List<String> lines = this.c.getStringList("Scoreboard.DisabledWorlds");
			///////////////////////////////////////////////////////////////////////
			if ((lines != null) && (!lines.isEmpty())) {
				return lines;
			}
		}
		return Arrays.asList(KNRankup.color("&e"));
	}

	// Apply the variables to the list
	private List<String> applyVariablesL(List<String> list) {
		String currank = new Players().getCRank(p1);
		Rank rank = new Rank(currank);
		Rank nextrank = new Rank(rank.getNextRank());
		List<String> nList = new ArrayList<String>();
		/////////////////////////////////////////////
		for (String m : list) {
			String nm = PlaceholderAPI.setPlaceholders(this.p1, m);
			///////////////////////////////////////////////////////
			if (nm.length() > 40) {
				nm = nm.substring(0, 40);
			}
			nList.add(nm.replace("%rank%", rank.getPrefix()).replace("%nextrank%", nextrank.getPrefix())
					.replace("%server_name%", this.p1.getServer().getServerName()));
		}
		return nList;
	}

	// Start the scheduler
	public void start() {
		this.task = KNRankup.s.getScheduler().scheduleSyncRepeatingTask(KNRankup.getPlugin(), new Runnable() {
			public void run() {
				for (Player p : KNRankup.s.getOnlinePlayers()) {
					World w = p.getWorld();
					Players pl = new Players();
					Scoreboard score = new Scoreboard(p);
					/////////////////////////////////////
					if (w != null && p.hasPermission("rankup.scoreboard")) {
						if (!pl.getDisabledS().contains(p.getName().toLowerCase())) {
							if (getDisabledWorlds().contains(w.getName().toLowerCase())) {
								score.remove();
							} else {
								score.send();
							}
						} else {
							score.remove();
						}
					} else {
						score.remove();
					}
				}
			}
		}, 0L, 20L);
	}
}