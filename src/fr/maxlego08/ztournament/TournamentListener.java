package fr.maxlego08.ztournament;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.maxlego08.ztournament.api.Duel;
import fr.maxlego08.ztournament.api.Team;
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

	@Override
	protected void onMove(PlayerMoveEvent event, Player player) {
		if (!tournament.isStart())
			return;

		if (event.getTo().getY() < 0) {

			Team team = tournament.getByPlayer(player);
			if (team != null) {
				Duel duel = tournament.getDuel(team);
				if (duel != null) {
					Location location = duel.getArena().getPos1();
					event.setTo(location);
					message(player, "§eVous ne pouvez pas sortir de l'arene.");

				}
			}
		}
	}

	@Override
	public void onDrop(PlayerDropItemEvent event, Player player) {

		if (tournament.isStart() && !tournament.isWaiting()) {
			Team team = tournament.getByPlayer(player);

			if (team == null)
				return;

			event.setCancelled(true);
			message(player, "§cVous ne pouvez pas jeter d'item au sol.");

		}

	}

	@Override
	public void onPlayerDamage(EntityDamageByEntityEvent event, Player player) {

		if (tournament.isStart() && !tournament.isWaiting() && player.getHealth() - event.getFinalDamage() <= 0) {
			Team team = tournament.getByPlayer(player);

			if (team == null)
				return;

			Duel duel = tournament.getDuel(team);
			if (duel == null)
				return;

			event.setCancelled(true);
			player.teleport(tournament.getLocation());

			tournament.loose(team, duel, player);
		}
	}

	@Override
	public void onDamageByEntity(EntityDamageByEntityEvent event, DamageCause cause, double damage,
			LivingEntity damager, LivingEntity entity) {

		if (tournament.isStart() && !tournament.isWaiting()) {

			if (entity instanceof Player && damager instanceof Player) {

				Player player = (Player) entity;
				Player player2 = (Player) damager;

				Team team = tournament.getByPlayer(player);

				if (team == null)
					return;

				if (team.contains(player2)) {

					event.setCancelled(true);
					actionMessage(player, "§eVous ne pouvez pas faire des dégâts à un membre de votre équipe.");

				}

			}

		}
	}

	@Override
	protected void onQuit(PlayerQuitEvent event, Player player) {

		if (tournament.isWaiting())
			tournament.leave(player, false);

		/**
		 * Si l'event est start et qu'on peut pas le rejoindre
		 */
		if (tournament.isStart() && !tournament.isWaiting()) {

			if (tournament.isTimeBetweenWave()) {
				tournament.removeTeam(player);
				return;
			}

			Team team = tournament.getByPlayer(player);
			if (team == null)
				return;

			Duel duel = tournament.getDuel(team);
			if (duel == null)
				return;

			tournament.loose(team, duel, player);
			player.teleport(tournament.getLocation());

		} else if (tournament.isStart() && !tournament.isWaiting())

			tournament.removeTeam(player);
	}

	@Override
	protected void onCommand(PlayerCommandPreprocessEvent event, Player player, String message) {
		Team team = tournament.getByPlayer(player);
		if (team != null && (tournament.isStart() || tournament.isWaiting()) && !message.startsWith("/tournois")
				&& !message.startsWith("/tournament")) {

			event.setCancelled(true);
			message(player, "§cVous ne pouvez pas faire de commande pendant un tournois.");

		}
	}

}
