package fr.maxlego08.ztournament;

import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.listener.ListenerAdapter;

public class TournamentListener extends ListenerAdapter {

	private final Tournament tournament;

	/**
	 * @param tournament
	 */
	public TournamentListener(Tournament tournament) {
		super();
		this.tournament = tournament;
	}

}
