package fr.maxlego08.ztournament.api;

import java.util.List;
import java.util.Optional;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.zcore.utils.storage.Saveable;

public interface Kits extends Saveable{

	/**
	 * 
	 * @param name
	 * @return
	 */
	boolean existKit(String name);
	Optional<Kit> getKit(String name);
	
	/**
	 * 
	 * @param sender
	 * @param name
	 */
	void createKit(CommandSender sender, String name);
	
	/**
	 * 
	 * @param player
	 * @param name
	 */
	void editKit(Player player, String name);
	
	/**
	 * 
	 * @param player
	 * @param name
	 */
	void showKit(Player player, String name);
	
	/**
	 * 
	 * @param sender
	 * @param name
	 */
	void deleteKit(CommandSender sender, String name);
	
	/**
	 * 
	 * @return
	 */
	List<String> getNames();
}
