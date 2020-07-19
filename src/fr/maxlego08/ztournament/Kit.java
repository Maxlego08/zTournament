package fr.maxlego08.ztournament;

import java.util.Map;

import org.bukkit.inventory.ItemStack;

public class Kit implements fr.maxlego08.ztournament.api.Kit {

	private final String name;
	private final ItemStack helmet;
	private final ItemStack chestplate;
	private final ItemStack leggings;
	private final ItemStack boots;
	private final Map<Integer, ItemStack> items;

	/**
	 * @param name
	 * @param helmet
	 * @param chestplate
	 * @param leggings
	 * @param boots
	 * @param items
	 */
	public Kit(String name, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots,
			Map<Integer, ItemStack> items) {
		super();
		this.name = name;
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
		this.items = items;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ItemStack getLeggings() {
		return leggings;
	}

	@Override
	public ItemStack getHelmet() {
		return helmet;
	}

	@Override
	public ItemStack getChestplate() {
		return chestplate;
	}

	@Override
	public ItemStack getBoots() {
		return boots;
	}

	@Override
	public Map<Integer, ItemStack> getItems() {
		return items;
	}

}
