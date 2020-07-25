package fr.maxlego08.ztournament.inventory.inventories;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.exceptions.InventoryOpenException;
import fr.maxlego08.ztournament.inventory.VInventory;
import fr.maxlego08.ztournament.zcore.utils.builder.ItemBuilder;
import fr.maxlego08.ztournament.zcore.utils.inventory.InventoryResult;

public class InventoryKitShow extends VInventory {

	@Override
	public InventoryResult openInventory(ZTournamentPlugin main, Player player, int page, Object... args)
			throws InventoryOpenException {

		Kit kit = (Kit) args[0];

		createInventory("§eKit", 54);

		for (int a = 9; a != 18; a++)
			addItem(a, new ItemBuilder(getMaterial(160), 1, 8));

		if (kit.getHelmet() != null)
			addItem(0, kit.getHelmet());

		if (kit.getChestplate() != null)
			addItem(1, kit.getChestplate());

		if (kit.getLeggings() != null)
			addItem(2, kit.getLeggings());

		if (kit.getBoots() != null)
			addItem(3, kit.getBoots());

		int slot = 18;
		kit.getItems().forEach((tmp, item) -> addItem(tmp + slot, item));

		return InventoryResult.SUCCESS;
	}

	@Override
	protected void onClose(InventoryCloseEvent event, ZTournamentPlugin plugin, Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDrag(InventoryDragEvent event, ZTournamentPlugin plugin, Player player) {
		// TODO Auto-generated method stub

	}

}
