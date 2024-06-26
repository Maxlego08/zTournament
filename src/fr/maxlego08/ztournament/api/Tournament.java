package fr.maxlego08.ztournament.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.Reward;
import fr.maxlego08.ztournament.zcore.utils.storage.Saveable;

public interface Tournament extends Saveable {

	/**
	 * 
	 * @param name
	 * @return
	 */
	Team getByName(String name);

	/**
	 * 
	 * @param player
	 * @return
	 */
	Team getByPlayer(Player player);
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	Optional<Team> getByOfflinePlayer(OfflinePlayer player);

	/**
	 * 
	 * @param team
	 * @return
	 */
	Duel getDuel(Team team);

	/**
	 * 
	 * @return
	 */
	Arena getAvaibleArena();

	/**
	 * 
	 * @return
	 */
	int getAvaibleArenaCount();

	/**
	 * 
	 * @return
	 */
	Team getByPassTeam();

	/**
	 * 
	 * @param player
	 */
	void clearPlayer(Player player, ClearReason reason);

	void givePotions(Player player);
	
	/**
	 * 
	 * @return
	 */
	Team getRandomTeam();

	/**
	 * 
	 * @return
	 */
	int getDuelTeam();

	/**
	 * 
	 * @param sender
	 * @param location
	 */
	void setLobbyLocation(Player sender, Location location);

	/**
	 * 
	 */
	void createRandomDuel();

	/**
	 * 
	 * @param sender
	 * @param pos1
	 * @param pos2
	 */
	void createArena(CommandSender sender, String name, Location pos1, Location pos2);

	/**
	 * 
	 * @param player
	 */
	void sendArena(Player player);

	/**
	 * 
	 * @param sender
	 * @param uuid
	 */
	void deleteArena(CommandSender sender, UUID uuid);

	/**
	 * 
	 * @param player
	 * @param damager
	 * @return
	 */
	boolean canHurt(Player player, Player damager);

	/**
	 * 
	 * @param player
	 * @return
	 */
	boolean inventoryHasItem(Player player);

	/**
	 * 
	 * @param player
	 */
	void removeTeam(Player player);

	/**
	 * 
	 */
	void checkTeam();

	/**
	 * 
	 * @param sender
	 * @param type
	 */
	void startTournois(CommandSender sender, TournoisType type, Kit kit);

	/**
	 * 
	 */
	void start();

	/**
	 * 
	 */
	void startWave();

	/**
	 * 
	 */
	void end();

	/**
	 * 
	 * @param player
	 * @param name
	 */
	void createTeam(Player player, String name);

	/**
	 * 
	 * @param player
	 * @param name
	 */
	void joinTeam(Player player, String name);

	/**
	 * 
	 * @param player
	 * @param target
	 */
	void invitePlayer(Player player, Player target);

	/**
	 * 
	 * @param player
	 * @param message
	 */
	void leave(Player player, boolean message);

	/**
	 * 
	 * @param team
	 * @param duel
	 * @param player
	 */
	void loose(Team team, Duel duel, Player player);

	/**
	 * 
	 */
	void canStartNextWave();

	/**
	 * 
	 * @param sender
	 * @return
	 */
	boolean canUseCommand(CommandSender sender);

	/**
	 * 
	 * @param sender
	 */
	void stopTournois(CommandSender sender);

	/**
	 * 
	 * @param sender
	 */
	void nextWave(CommandSender sender);

	/**
	 * 
	 * @return
	 */
	boolean isStart();

	/**
	 * 
	 * @return
	 */
	boolean isWaiting();

	/**
	 * 
	 * @return
	 */
	Location getLocation();

	/**
	 * 
	 * @return
	 */
	boolean isTimeBetweenWave();
	
	/**
	 * 
	 */
	void onPluginDisable();

	/**
	 * 
	 * @param position
	 * @return reward
	 */
	Reward getReward(int position);
	
	/**
	 * 
	 * @return kits manager
	 */
	Kits getKits();
	
	/**
	 * 
	 * @return current kit use
	 */
	Kit getKit();

	/**
	 * 
	 * @param isPos1
	 * @param sender
	 * @param name
	 * @param location
	 */
	void setPosition(boolean isPos1, CommandSender sender, String name, Location location);

	/**
	 * Permet de kick un membre
	 * 
	 * @param sender
	 * @param player
	 */
	void kick(CommandSender sender, Player player);

	/**
	 * Permet d'appliquer les damages
	 * 
	 * @param damager
	 * @param finalDamage
	 */
	void countDamage(Player damager, double finalDamage);
	
	/**
	 * Return arena names
	 * 
	 * @return name
	 */
	public List<String> getArenaNames();
	
}
