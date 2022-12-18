package fr.maxlego08.ztournament.inventory.inventories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.exceptions.InventoryOpenException;
import fr.maxlego08.ztournament.inventory.VInventory;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.utils.inventory.InventoryResult;

public class InventoryKitEdit extends VInventory {

	private Kit kit;

	public InventoryKitEdit() {

		this.setDisableClick(false);

	}

	@Override
	public InventoryResult openInventory(ZTournamentPlugin main, Player player, int page, Object... args)
			throws InventoryOpenException {

		Optional<Kit> optional = (Optional<Kit>) args[0];
		if (!optional.isPresent()){
			return InventoryResult.ERROR;
		}
		
		this.kit = optional.get();

		createInventory("§eEdit", 54);

		for (int a = 4; a != 18; a++) {
			addItem(a, getGlass()).setClick(e -> e.setCancelled(true));
		}

		if (this.kit.getHelmet() != null) {
			addItem(0, this.kit.getHelmet());
		}

		if (this.kit.getChestplate() != null) {
			addItem(1, this.kit.getChestplate());
		}

		if (this.kit.getLeggings() != null) {
			addItem(2, this.kit.getLeggings());
		}

		if (this.kit.getBoots() != null) {
			addItem(3, this.kit.getBoots());
		}

		int slot = 18;
		this.kit.getItems().forEach((tmp, item) -> addItem(tmp + slot, item));

		return InventoryResult.SUCCESS;
	}

	@Override
	protected void onClose(InventoryCloseEvent event, ZTournamentPlugin plugin, Player player) {

		org.bukkit.inventory.Inventory inventory = event.getInventory();

		ItemStack helmet = inventory.getItem(0);
		ItemStack chestplate = inventory.getItem(1);
		ItemStack leggings = inventory.getItem(2);
		ItemStack boots = inventory.getItem(3);

		this.kit.setHelmet(helmet);
		this.kit.setChestplate(chestplate);
		this.kit.setLeggings(leggings);
		this.kit.setBoots(boots);

		ItemStack[] contents = inventory.getContents();
		Map<Integer, ItemStack> map = new HashMap<Integer, ItemStack>();
		for (int a = 18; a != 54; a++) {

			int slot = 18;
			if (contents[a] != null)
				map.put(a - slot, contents[a]);

		}

		this.kit.setItems(map);

		message(player, Message.TOURNAMENT_KIT_EDIT, "%name%", this.kit.getName());
		plugin.getSavers().forEach(e -> e.save(plugin.getPersist()));
	}

	@Override
	protected void onDrag(InventoryDragEvent event, ZTournamentPlugin plugin, Player player) {
		// TODO Auto-generated method stub

	}

}
