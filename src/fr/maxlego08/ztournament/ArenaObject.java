package fr.maxlego08.ztournament;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;

import fr.maxlego08.ztournament.api.Arena;
import fr.maxlego08.ztournament.api.Duel;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;

public class ArenaObject extends ZUtils implements Arena {

	private final UUID id;
	private final Location pos1;
	private final Location pos2;
	private transient List<Duel> teams = new ArrayList<Duel>();

	/**
	 * 
	 * @param id
	 * @param pos1
	 * @param pos2
	 */
	public ArenaObject(Location pos1, Location pos2) {
		this.id = UUID.randomUUID();
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.teams = new ArrayList<Duel>();
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the pos1
	 */
	public Location getPos1() {
		return pos1;
	}

	/**
	 * @return the pos2
	 */
	public Location getPos2() {
		return pos2;
	}

	public List<Duel> getTeams() {
		if (teams == null)
			teams = new ArrayList<Duel>();
		return teams;
	}

	public int size() {
		if (teams == null)
			teams = new ArrayList<Duel>();
		return teams.size();
	}

	public boolean hasTeams() {
		return size() != 0;
	}

	public void addDuel(Duel team) {
		if (teams == null)
			teams = new ArrayList<Duel>();
		teams.add(team);
	}

	public void removeDuel(Duel team) {
		if (teams == null)
			teams = new ArrayList<Duel>();
		teams.remove(team);
	}

	public void clear() {
		if (teams == null)
			teams = new ArrayList<Duel>();
		if (teams != null)
			teams.clear();
	}

}
