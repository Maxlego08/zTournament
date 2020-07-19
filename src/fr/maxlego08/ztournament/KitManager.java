package fr.maxlego08.ztournament;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.api.Kits;
import fr.maxlego08.ztournament.exceptions.KitCreateException;
import fr.maxlego08.ztournament.zcore.logger.Logger;
import fr.maxlego08.ztournament.zcore.logger.Logger.LogType;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;
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

		kits.forEach((name, kit) -> {

			AtomicInteger integer = new AtomicInteger(1);

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

			ItemStack helmet = loader.load(configuration, "kits." + kitId + ".helmet");
			ItemStack chestplate = loader.load(configuration, "kits." + kitId + ".chestplate");
			ItemStack leggings = loader.load(configuration, "kits." + kitId + ".leggings");
			ItemStack boots = loader.load(configuration, "kits." + kitId + ".boots");

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
		items.put(0, new ItemStack(Material.IRON_SWORD));
		items.put(1, new ItemStack(Material.GOLDEN_APPLE, 8));

		Kit kit = new fr.maxlego08.ztournament.Kit("default", new ItemStack(Material.LEATHER_HELMET),
				new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_LEGGINGS),
				new ItemStack(Material.LEATHER_BOOTS), items);

		kits.put("default", kit);

	}

	@Override
	public Kit getKit(String name) {
		return kits.getOrDefault(name, null);
	}

}
