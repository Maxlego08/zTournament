package fr.maxlego08.ztournament.placeholder;

import java.util.Optional;

import org.bukkit.OfflinePlayer;

import fr.maxlego08.ztournament.ZTournamentPlugin;
import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.save.Config;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class TournamentExpansion extends PlaceholderExpansion {

	private final ZTournamentPlugin plugin;

	/**
	 * @param plugin
	 */
	public TournamentExpansion(ZTournamentPlugin plugin) {
		super();
		this.plugin = plugin;
	}

	@Override
	public String getAuthor() {
		return "Maxlego08";
	}

	@Override
	public String getIdentifier() {
		return "ztournament";
	}

	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}

	@Override
	public String onRequest(OfflinePlayer player, String params) {

		Tournament tournament = plugin.getTournament();

		if (params.equalsIgnoreCase("team_name")) {

			Optional<Team> optional = tournament.getByOfflinePlayer(player);
			if (optional.isPresent()) {
				Team team = optional.get();
				return team.getName();
			} else {
				return Config.noTeamPlaceholder;
			}

		} else if (params.equalsIgnoreCase("team_position")) {

			Optional<Team> optional = tournament.getByOfflinePlayer(player);
			if (optional.isPresent()) {
				Team team = optional.get();
				return String.valueOf(team.getPosition());
			} else {
				return String.valueOf(0);
			}

		}

		return null;
	}

}
