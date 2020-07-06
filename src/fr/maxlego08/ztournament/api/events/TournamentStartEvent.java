package fr.maxlego08.ztournament.api.events;

import fr.maxlego08.ztournament.api.TournoisType;

public class TournamentStartEvent extends TournamentEvent {

	private final TournoisType type;

	/**
	 * @param type
	 */
	public TournamentStartEvent(TournoisType type) {
		super();
		this.type = type;
	}

	public TournoisType getType() {
		return type;
	}

}
