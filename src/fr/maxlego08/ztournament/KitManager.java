package fr.maxlego08.ztournament;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.api.Kits;
import fr.maxlego08.ztournament.exceptions.KitCreateException;
import fr.maxlego08.ztournament.zcore.enums.Inventory;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.logger.Logger;
import fr.maxlego08.ztournament.zcore.logger.Logger.LogType;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;
import fr.maxlego08.ztournament.zcore.utils.builder.ItemBuilder;
import fr.maxlego08.ztournament.zcore.utils.loader.ItemStackYAMLoader;
import fr.maxlego08.ztournament.zcore.utils.loader.Loader;
import fr.maxlego08.ztournament.zcore.utils.storage.Persist;

public class KitManager extends ZUtils implements Kits {

	private Map<String, Kit> kits = new HashMap<String, Kit>();

	@Override
	public void save(Persist persist) {

		if (kits.size() == 0)
			createDefaultKits();

		File file = new File(plugin.getDataFolder() + File.separator + "kits.yml");
		if (file.exists())
			file.delete();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		Loader<ItemStack> loader = new ItemStackYAMLoader();

		AtomicInteger integer = new AtomicInteger(1);
		kits.forEach((name, kit) -> {

			String path = "kits." + integer.getAndIncrement() + ".";

			configuration.set(path + "name", kit.getName());

			if (kit.getHelmet() != null)
				loader.save(kit.getHelmet(), configuration, path + ".helmet.");
			else
				configuration.set(path + ".helmet", null);
			if (kit.getChestplate() != null)
				loader.save(kit.getChestplate(), configuration, path + ".chestplate.");
			else
				configuration.set(path + ".chestplate", null);
			if (kit.getLeggings() != null)
				loader.save(kit.getLeggings(), configuration, path + ".leggings.");
			else
				configuration.set(path + ".leggings", null);
			if (kit.getBoots() != null)
				loader.save(kit.getBoots(), configuration, path + ".boots.");
			else
				configuration.set(path + ".boots", null);

			kit.getItems().forEach((slot, item) -> {

				String currentPath = path + "items." + slot + ".";
				if (item != null) {
					loader.save(item, configuration, currentPath);
					configuration.set(currentPath + "slot", slot);
				} else
					configuration.set(currentPath, null);

			});

		});

		try {
			configuration.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void load(Persist persist) {

		File file = new File(plugin.getDataFolder() + File.separator + "kits.yml");

		if (!file.exists()) {
			// On va save les kits
			save(persist);
		}

		YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

		if (configuration.getString("kits") == null) {
			Logger.info("Impossible de charger les items !", LogType.ERROR);
			return;
		}

		Loader<ItemStack> loader = new ItemStackYAMLoader();

		for (String kitId : configuration.getConfigurationSection("kits.").getKeys(false)) {

			String name = configuration.getString("kits." + kitId + ".name");

			if (name == null || name.length() == 1)
				throw new KitCreateException("Cannot create kit witout name (kits." + kitId + ".name is null)");

			ItemStack helmet = loader.load(configuration, "kits." + kitId + ".helmet.");
			ItemStack chestplate = loader.load(configuration, "kits." + kitId + ".chestplate.");
			ItemStack leggings = loader.load(configuration, "kits." + kitId + ".leggings.");
			ItemStack boots = loader.load(configuration, "kits." + kitId + ".boots.");

			Map<Integer, ItemStack> itemstacks = new HashMap<>();

			for (String itemId : configuration.getConfigurationSection("kits." + kitId + ".items.").getKeys(false)) {

				String currentPath = "kits." + kitId + ".items." + itemId + ".";
				int slot = configuration.getInt(currentPath + "slot", 0);
				itemstacks.put(slot, loader.load(configuration, currentPath));

			}

			Kit kit = new fr.maxlego08.ztournament.Kit(name, helmet, chestplate, leggings, boots, itemstacks);
			kits.put(kit.getName(), kit);

		}

		Logger.info(file.getAbsolutePath() + " loaded successfully !", LogType.SUCCESS);
		Logger.info("Loading " + kits.size() + " kits", LogType.SUCCESS);

	}

	/**
	 * 
	 */
	private void createDefaultKits() {

		kits.clear();

		Map<Integer, ItemStack> items = new HashMap<>();
		items.put(0, new ItemStack(getMaterial(272)));
		items.put(1, new ItemStack(getMaterial(261)));
		items.put(2, new ItemStack(getMaterial(322), 2));
		items.put(3, new ItemStack(getMaterial(320), 9));
		items.put(4, new ItemBuilder(getMaterial(373), 1, 81923).build());

		items.put(9, new ItemStack(getMaterial(262), 8));

		Kit kit = new fr.maxlego08.ztournament.Kit("default", new ItemStack(getMaterial(298)),
				new ItemStack(getMaterial(299)), new ItemStack(getMaterial(300)), new ItemStack(getMaterial(301)),
				items);

		kits.put("default", kit);

		
		// 2
		items = new HashMap<>();
		items.put(0, new ItemBuilder(getMaterial(276)).addEnchant(Enchantment.DAMAGE_ALL, 1).build());
		items.put(1, new ItemBuilder(getMaterial(261)).addEnchant(Enchantment.ARROW_DAMAGE, 2).build());
		items.put(2, new ItemStack(getMaterial(322), 4));
		items.put(3, new ItemStack(getMaterial(400), 13));
		items.put(4, new ItemBuilder(getMaterial(373), 1, 16421).build());
		items.put(5, new ItemBuilder(getMaterial(373), 1, 16421).build());
		items.put(6, new ItemBuilder(getMaterial(373), 1, 16421).build());
		items.put(7, new ItemBuilder(getMaterial(373), 1, 16421).build());
		items.put(8, new ItemBuilder(getMaterial(373), 1, 16421).build());
		items.put(9, new ItemBuilder(getMaterial(373), 1, 8226).build());
		items.put(10, new ItemBuilder(getMaterial(373), 1, 8226).build());

		items.put(24, new ItemStack(getMaterial(262), 16));

		ItemStack helmet = new ItemBuilder(getMaterial(306)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
				.build();
		ItemStack chestplate = new ItemBuilder(getMaterial(307)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
				.build();
		ItemStack leggings = new ItemBuilder(getMaterial(308)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
				.build();
		ItemStack boots = new ItemBuilder(getMaterial(309)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build();

		kit = new fr.maxlego08.ztournament.Kit("iron", helmet, chestplate, leggings, boots, items);

		kits.put("iron", kit);

	}

	@Override
	public Kit getKit(String name) {
		return kits.values().stream().filter(e -> e.getName().equalsIgnoreCase(name)).findAny().orElse(null);
	}

	@Override
	public boolean existKit(String name) {
		return kits.keySet().stream().filter(e -> e.equalsIgnoreCase(name)).findAny().isPresent();
	}

	@Override
	public void createKit(CommandSender sender, String name) {

		if (existKit(name)) {
			message(sender, Message.TOURNAMENT_KIT_ALREADY_EXIST.replace("%name%", name));
			return;
		}

		Kit kit = new fr.maxlego08.ztournament.Kit(name, null, null, null, null, new HashMap<>());
		kits.put(name, kit);
		message(sender, Message.TOURNAMENT_KIT_CREATE.replace("%name%", name));

	}

	@Override
	public void editKit(Player player, String name) {

		if (!existKit(name)) {
			message(player, Message.TOURNAMENT_KIT_NOT_EXIST.replace("%name%", name));
			return;
		}

		createInventory(player, Inventory.INVENTORY_KIT_CREATE, 1, getKit(name));

	}

	@Override
	public void showKit(Player player, String name) {
		if (!existKit(name)) {
			message(player, Message.TOURNAMENT_KIT_NOT_EXIST.replace("%name%", name));
			return;
		}

		createInventory(player, Inventory.INVENTORY_KIT_SHOW, 1, getKit(name));
	}

	@Override
	public void deleteKit(CommandSender sender, String name) {
		if (!existKit(name)) {
			message(sender, Message.TOURNAMENT_KIT_NOT_EXIST.replace("%name%", name));
			return;
		}

		Kit kit = getKit(name);
		kits.remove(kit.getName());

		message(sender, Message.TOURNAMENT_KIT_DELETE.replace("%name%", name));
	}

	@Override
	public List<String> getNames() {
		return new ArrayList<String>(kits.keySet());
	}

}