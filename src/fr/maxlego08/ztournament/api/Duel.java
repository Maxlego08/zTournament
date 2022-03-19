package fr.maxlego08.ztournament.api;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.zcore.enums.Message;

public interface Duel {

	/**
	 * 
	 * @return team
	 */
	Team getOpponant();

	/**
	 * 
	 * @return
	 */
	Team getTeam();

	/**
	 * 
	 * @param location
	 * @param location2
	 */
	void startDuel(Location location, Location location2);

	/**
	 * 
	 * @param team
	 * @return
	 */
	boolean match(Team team);

	/**
	 * 
	 * @param player
	 */
	void onPlayerLoose(Player player);

	/**
	 * 
	 * @return
	 */
	boolean hasWinner();

	/**
	 * 
	 * @return
	 */
	Team getWinner();

	/**
	 * 
	 * @return
	 */
	Team getLooser();

	/**
	 * 
	 * @param tournamentPlayerLoose
	 * @param string
	 * @param string2
	 */
	void message(Message tournamentPlayerLoose, Object... objects);

	/**
	 * 
	 */
	void heal();

	/**
	 * 
	 * @param team
	 * @return
	 */
	Team getOpponent(Team team);

	/**
	 * 
	 * @return
	 */
	boolean isDuel();

	/**
	 * 
	 * @param arena
	 */
	void setArenea(Arena arena);

	/**
	 * 
	 * @return
	 */
	Arena getArena();

	/**
	 * 
	 * @param player
	 * @return
	 */
	boolean contains(Player player);

	Team getRandomTeamLessDamage(Map<UUID, Double> playerDamageCount);
}
