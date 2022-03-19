package fr.maxlego08.ztournament;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.maxlego08.ztournament.api.Arena;
import fr.maxlego08.ztournament.api.Duel;
import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.zcore.enums.Message;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;

public class ZDuel extends ZUtils implements Duel {

	private final Team team;
	private final Team opponant;
	private Arena arena;

	public ZDuel(Team team, Team opponant) {
		this.team = team;
		this.opponant = opponant;
	}

	public Team getOpponant() {
		return opponant;
	}

	public Team getTeam() {
		return team;
	}

	public void startDuel(Location location, Location location2) {

		// On show
		team.show(opponant);
		opponant.show(team);

		// On heal
		team.heal();
		opponant.heal();

		// On clear + give
		team.give();
		opponant.give();

		// On téléporte
		team.teleport(location);
		opponant.teleport(location2);
	}

	public boolean match(Team team) {
		return this.team.equals(team) || opponant.equals(team);
	}

	public void onPlayerLoose(Player player) {
		team.death(player);
		opponant.death(player);
	}

	public boolean hasWinner() {
		return getWinner() != null;
	}

	public Team getWinner() {
		return team.hasLoose() ? opponant : opponant.hasLoose() ? team : null;
	}

	public Team getLooser() {
		return team.hasLoose() ? team : opponant.hasLoose() ? opponant : null;
	}

	public void message(Message message, Object... objects) {
		team.message(message);
		opponant.message(message);
	}

	public void heal() {
		opponant.heal();
		team.heal();
	}

	public Team getOpponent(Team team) {
		return this.team.equals(team) ? opponant : this.team;
	}

	public boolean isDuel() {
		return team.isInDuel() && opponant.isInDuel();
	}

	public void setArenea(Arena arena) {
		this.arena = arena;
	}

	public Arena getArena() {
		return arena;
	}

	@Override
	public boolean contains(Player player) {
		return this.team.contains(player) || this.opponant.contains(player);
	}

	@Override
	public Team getRandomTeamLessDamage(Map<UUID, Double> playerDamageCount) {

		double teamDamage = playerDamageCount.entrySet().stream().filter(e -> this.team.contains(e.getKey()))
				.map(e -> e.getValue()).reduce(0d, Double::sum);
		double opponantDamage = playerDamageCount.entrySet().stream().filter(e -> this.opponant.contains(e.getKey()))
				.map(e -> e.getValue()).reduce(0d, Double::sum);

		return teamDamage > opponantDamage ? this.opponant : this.team;
	}

}
