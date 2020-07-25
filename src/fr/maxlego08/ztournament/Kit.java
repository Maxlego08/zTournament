package fr.maxlego08.ztournament;

import java.util.Map;

import org.bukkit.inventory.ItemStack;

public class Kit implements fr.maxlego08.ztournament.api.Kit {

	private final String name;
	private ItemStack helmet;
	private ItemStack chestplate;
	private ItemStack leggings;
	private ItemStack boots;
	private Map<Integer, ItemStack> items;

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

	/**
	 * @param helmet
	 *            the helmet to set
	 */
	public void setHelmet(ItemStack helmet) {
		this.helmet = helmet;
	}

	/**
	 * @param chestplate
	 *            the chestplate to set
	 */
	public void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}

	/**
	 * @param leggings
	 *            the leggings to set
	 */
	public void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}

	/**
	 * @param boots
	 *            the boots to set
	 */
	public void setBoots(ItemStack boots) {
		this.boots = boots;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(Map<Integer, ItemStack> items) {
		this.items = items;
	}

}
