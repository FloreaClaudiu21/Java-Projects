package me.rankup.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.rankup.KNRankup;
import me.rankup.Messages.Messages;
import me.rankup.Utility.GUI;

public class onPlayerClickMenu implements Listener {
	
	private Messages msgs = new Messages();

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		///////////////////////////////
		if (i == null) {
			return;
		}
		String name = i.getName();
		//////////////////////////
		if (name.equalsIgnoreCase(new GUI().menuName())) {
			ItemStack item = e.getCurrentItem();
			Player p = (Player) e.getWhoClicked();
			//////////////////////////////////////
			if (item == null) {
				return;
			}
			if (dActions().contains(e.getAction())) {
				e.setCancelled(true);
				return;
			}
			if (item.getType() == Material.BARRIER) {
				e.setCancelled(true);
				KNRankup.s.getScheduler().scheduleSyncDelayedTask(KNRankup.getPlugin(), new Runnable() {
					public void run() {
						p.closeInventory();
						KNRankup.getAPI().sendActionBar(p, msgs.get(p, "Messages.InventoryClose"));
					}
				}, 10L);
				return;
			}
			e.setCancelled(true);
			return;
		}
	}

	// Disable the inventory actions
	private List<InventoryAction> dActions() {
		List<InventoryAction> list = new ArrayList<InventoryAction>();
		//////////////////////////////////////////////////////////////
		list.add(InventoryAction.CLONE_STACK);
		list.add(InventoryAction.COLLECT_TO_CURSOR);
		list.add(InventoryAction.HOTBAR_MOVE_AND_READD);
		list.add(InventoryAction.MOVE_TO_OTHER_INVENTORY);
		list.add(InventoryAction.DROP_ALL_CURSOR);
		list.add(InventoryAction.DROP_ALL_SLOT);
		list.add(InventoryAction.DROP_ONE_CURSOR);
		list.add(InventoryAction.DROP_ONE_SLOT);
		list.add(InventoryAction.PLACE_ALL);
		list.add(InventoryAction.PLACE_ONE);
		list.add(InventoryAction.PLACE_SOME);
		list.add(InventoryAction.SWAP_WITH_CURSOR);
		//
		return list;
	}
}