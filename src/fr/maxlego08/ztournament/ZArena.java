package fr.maxlego08.ztournament;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;

import fr.maxlego08.ztournament.api.Arena;
import fr.maxlego08.ztournament.api.Duel;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;

public class ZArena extends ZUtils implements Arena {

	private String name;
	private final UUID id;
	private String pos1;
	private String pos2;
	private transient List<Duel> teams = new ArrayList<Duel>();

	/**
	 * 
	 * @param id
	 * @param pos1
	 * @param pos2
	 */
	public ZArena(String name, Location pos1, Location pos2) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.pos1 = changeLocationToStringEye(pos1);
		this.pos2 = changeLocationToStringEye(pos2);
		this.teams = new ArrayList<Duel>();
	}

	/**
	 * 
	 * @param id
	 * @param pos1
	 * @param pos2
	 */
	public ZArena(String name, UUID uuid, Location pos1, Location pos2) {
		this.name = name;
		this.id = uuid;
		this.pos1 = changeLocationToStringEye(pos1);
		this.pos2 = changeLocationToStringEye(pos2);
		this.teams = new ArrayList<Duel>();
	}

	/**
	 * @param id
	 * @param pos1
	 * @param pos2
	 */
	public ZArena(UUID id, String name, String pos1, String pos2) {
		super();
		this.name = name;
		this.id = id;
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
		return changeStringLocationToLocationEye(pos1);
	}

	/**
	 * @return the pos2
	 */
	public Location getPos2() {
		return changeStringLocationToLocationEye(pos2);
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

	@Override
	public String getPos1String() {
		return pos1;
	}

	@Override
	public String getPos2String() {
		return pos2;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public UUID getUniqueId() {
		return id;
	}

	@Override
	public void setPos1(Location location) {
		this.pos1 = changeLocationToStringEye(location);
	}

	@Override
	public void setPos2(Location location) {
		this.pos2 = changeLocationToStringEye(location);
	}

}
