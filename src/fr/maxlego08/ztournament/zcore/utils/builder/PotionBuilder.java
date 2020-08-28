package fr.maxlego08.ztournament.zcore.utils.builder;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

/**
 * 
 * @author Maxlego08
 *
 */
public class PotionBuilder extends ItemBuilder {

	private PotionEffectType type;

	public PotionBuilder(int amount, String name, List<String> lore, List<ItemFlag> flags,
			Map<Enchantment, Integer> enchantments) {
		super(Material.POTION, 0, amount, name, lore, flags, enchantments);
	}

	public PotionBuilder(int amount, int data) {
		super(Material.POTION, amount, data);
	}

	public PotionBuilder(int amount, String name) {
		super(Material.POTION, amount, name);
	}

	public PotionBuilder(int amount) {
		super(Material.POTION, amount);
	}

	public PotionBuilder(ItemFlag... flags) {
		super(Material.POTION, flags);
	}

	public PotionBuilder(String... lore) {
		super(Material.POTION, lore);
	}

	public PotionBuilder(String name) {
		super(Material.POTION, name);
	}

	public PotionBuilder(PotionEffectType type) {
		super(Material.POTION);
		this.type = type;
	}

	public void setType(PotionEffectType type) {
		this.type = type;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ItemStack build() {
		ItemStack itemStack = super.build();
		if (type != null) {
			PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
			potionMeta.setMainEffect(type);
			itemStack.setItemMeta(potionMeta);
		}
		return itemStack;
	}

}
