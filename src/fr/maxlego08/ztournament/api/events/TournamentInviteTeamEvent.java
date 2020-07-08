package fr.maxlego08.ztournament.api.events;

import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.api.Team;

public class TournamentInviteTeamEvent extends TournamentEvent {

	private final Team team;
	private final Player player;

	/**
	 * @param team
	 * @param player
	 */
	public TournamentInviteTeamEvent(Team team, Player player) {
		super();
		this.team = team;
		this.player = player;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}
