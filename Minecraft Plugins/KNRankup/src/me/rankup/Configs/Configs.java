package me.rankup.Configs;

import java.util.HashMap;
import java.util.Map;

import me.rankup.Configs.Players.Players;
import me.rankup.Configs.Ranks.Ranks;

public class Configs {

	public Map<Players, Ranks> hlist = new HashMap<Players, Ranks>();

	public Configs() {
		return;
	}
}
