package fr.maxlego08.ztournament.api.events;

import fr.maxlego08.ztournament.api.Team;

public class TournamentTeamRewardEvent extends TournamentEvent {

	private final Team team;

	/**
	 * @param team
	 */
	public TournamentTeamRewardEvent(Team team) {
		super();
		this.team = team;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

}
