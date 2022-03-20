package fr.maxlego08.ztournament.api;

import org.bukkit.entity.Player;

public interface Hider {

	/**
	 * Permet de cacher un joueur
	 * 
	 * @param player
	 * @param target
	 */
	public void hide(Player player, Player target);
	
	/**
	 * Permet de montrer un joueur
	 * 
	 * @param player
	 * @param target
	 */
	public void show( Player player, Player target);
	
}
