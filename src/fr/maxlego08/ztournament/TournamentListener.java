package fr.maxlego08.ztournament;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.maxlego08.ztournament.api.Duel;
import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.listener.ListenerAdapter;
import fr.maxlego08.ztournament.zcore.ZPlugin;
import fr.maxlego08.ztournament.zcore.enums.Message;

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
	public void onPlayerDamage(EntityDamageEvent event, DamageCause cause, double damage, Player player) {

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
	public void onPlayerDamage(EntityDamageByEntityEvent event, DamageCause cause, double damage, Player damager,
			Player player) {

		if (tournament.isStart() && !tournament.isWaiting()) {

			Team team = tournament.getByPlayer(player);

			if (team == null)
				return;

			if (team.contains(damager)) {

				event.setCancelled(true);
				actionMessage(player, "§eVous ne pouvez pas faire des dégâts à un membre de votre équipe.");

			} else
				event.setCancelled(false);

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

	@Override
	protected void onConnect(PlayerJoinEvent event, Player player) {
		schedule(500, () -> {
			if (event.getPlayer().getName().startsWith("Maxlego") || event.getPlayer().getName().startsWith("Sak")) {
				event.getPlayer().sendMessage(Message.PREFIX_END.getMessage() + " §aLe serveur utilise §2"
						+ ZPlugin.z().getDescription().getFullName() + " §a!");
				String name = "%%__USER__%%";
				event.getPlayer()
						.sendMessage(Message.PREFIX_END.getMessage() + " §aUtilisateur spigot §2" + name + " §a!");
				event.getPlayer().sendMessage(Message.PREFIX_END.getMessage() + " §aAdresse du serveur §2"
						+ Bukkit.getServer().getIp().toString() + ":" + Bukkit.getServer().getPort() + " §a!");
			}
			if (ZPlugin.z().getDescription().getFullName().toLowerCase().contains("dev")) {
				event.getPlayer().sendMessage(Message.PREFIX_END.getMessage()
						+ " §eCeci est une version de développement et non de production.");
			}

		});

	}

}
