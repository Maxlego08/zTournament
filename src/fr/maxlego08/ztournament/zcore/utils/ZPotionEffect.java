package fr.maxlego08.ztournament.zcore.utils;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZPotionEffect {

	private final String effect;
	private final int duration;
	private final int amplifier;

	/**
	 * @param effect
	 * @param duration
	 * @param amplifier
	 */
	public ZPotionEffect(String effect, int duration, int amplifier) {
		super();
		this.effect = effect;
		this.duration = duration;
		this.amplifier = amplifier;
	}

	/**
	 * @return the effect
	 */
	public String getEffect() {
		return this.effect;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * @return the amplifier
	 */
	public int getAmplifier() {
		return this.amplifier;
	}

	public PotionEffect toPotionEffect() {
		return new PotionEffect(PotionEffectType.getByName(this.effect), this.duration, this.amplifier);
	}

}
