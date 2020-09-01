package fr.maxlego08.ztournament;

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
import fr.maxlego08.ztournament.zcore.enums.Permission;

public class TournamentListener extends ListenerAdapter {

	private final Tournament tournament;
	private boolean useLastVersion = true;

	/**
	 * @param tournament
	 */
	public TournamentListener(Tournament tournament) {
		super();
		this.tournament = tournament;
	}

	public void setUseLastVersion(boolean useLastVersion) {
		this.useLastVersion = useLastVersion;
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
					message(player, Message.TOURNAMENT_LEAVE_ARENA);

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
			message(player, Message.DROP_ITEM);

		}

	}

	@Override
	public void onPlayerDamageLow(EntityDamageByEntityEvent event, DamageCause cause, double damage, Player player,
			Player entity) {

		if (tournament.isStart() && !tournament.isWaiting()) {

			Team team = tournament.getByPlayer(player);

			if (team == null)
				return;

			if (!team.isAlive(player))
				return;

			if (!team.isInDuel())
				return;
			
			// on cancel l'event pour bypass tout les autres
			event.setCancelled(true);

		}

	}

	@Override
	public void onPlayerDamage(EntityDamageByEntityEvent event, DamageCause cause, double damage, Player damager,
			Player player) {

		if (tournament.isStart() && !tournament.isWaiting()) {

			Team team = tournament.getByPlayer(player);

			if (team == null)
				return;

			if (!team.isAlive(player))
				return;

			if (!team.isInDuel())
				return;
			
			if (team.contains(damager)) {

				event.setCancelled(true);
				actionMessage(player, Message.TEAM_DAMAGE);

			} else {

				event.setCancelled(false);
				
			}

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

			if (!team.isAlive(player))
				return;

			event.setCancelled(true);
			player.teleport(tournament.getLocation());

			tournament.loose(team, duel, player);
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

		message = message.toLowerCase();

		Team team = tournament.getByPlayer(player);

		if ((team != null) && (tournament.isStart() || tournament.isWaiting())
				&& (!message.contains("/tournois") && !message.contains("/tournament")
						&& !message.contains("/ztournament"))
				&& !player.hasPermission(Permission.ZTOURNAMENT_BYPASS_COMMAND.getPermission())) {

			event.setCancelled(true);
			message(player, Message.TOURNAMENT_COMMAND);

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
			}
			if (ZPlugin.z().getDescription().getFullName().toLowerCase().contains("dev")) {
				event.getPlayer().sendMessage(Message.PREFIX_END.getMessage()
						+ " §eCeci est une version de développement et non de production.");
			}

			if (!useLastVersion && ((player.hasPermission(Permission.ZTOURNAMENT_RELOAD.getPermission())
					|| event.getPlayer().getName().startsWith("Maxlego08")
					|| event.getPlayer().getName().startsWith("Sak")))) {
				message(player,
						"§cYou are not using the latest version of the plugin, remember to update the plugin quickly.");
				message(player, "§cDownload link: §fhttps://www.spigotmc.org/resources/81959/");
			}

		});

	}

}
