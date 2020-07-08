package fr.maxlego08.ztournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.maxlego08.ztournament.api.Arena;
import fr.maxlego08.ztournament.api.Duel;
import fr.maxlego08.ztournament.api.NMS;
import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.api.TournoisType;
import fr.maxlego08.ztournament.api.events.TournamentAutoEndWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentCreateTeamEvent;
import fr.maxlego08.ztournament.api.events.TournamentEvent;
import fr.maxlego08.ztournament.api.events.TournamentInviteTeamEvent;
import fr.maxlego08.ztournament.api.events.TournamentJoinSuccessTeamEvent;
import fr.maxlego08.ztournament.api.events.TournamentJoinTeamEvent;
import fr.maxlego08.ztournament.api.events.TournamentLooseTeamEvent;
import fr.maxlego08.ztournament.api.events.TournamentPostWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentPreWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentRewardTeamEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartNoEnoughEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartNoEnoughRestartEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartTickEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentWareArenaEvent;
import fr.maxlego08.ztournament.api.events.TournamentWinEvent;
import fr.maxlego08.ztournament.nms.NMS_1_10;
import fr.maxlego08.ztournament.nms.NMS_1_11;
import fr.maxlego08.ztournament.nms.NMS_1_12;
import fr.maxlego08.ztournament.nms.NMS_1_13;
import fr.maxlego08.ztournament.nms.NMS_1_14;
import fr.maxlego08.ztournament.nms.NMS_1_15;
import fr.maxlego08.ztournament.nms.NMS_1_16;
import fr.maxlego08.ztournament.nms.NMS_1_7;
import fr.maxlego08.ztournament.nms.NMS_1_8;
import fr.maxlego08.ztournament.nms.NMS_1_9;
import fr.maxlego08.ztournament.save.Config;
import fr.maxlego08.ztournament.zcore.ZPlugin;
import fr.maxlego08.ztournament.zcore.logger.Logger;
import fr.maxlego08.ztournament.zcore.logger.Logger.LogType;
import fr.maxlego08.ztournament.zcore.utils.ItemDecoder;
import fr.maxlego08.ztournament.zcore.utils.ZUtils;
import fr.maxlego08.ztournament.zcore.utils.builder.TimerBuilder;
import fr.maxlego08.ztournament.zcore.utils.inventory.Pagination;
import fr.maxlego08.ztournament.zcore.utils.storage.Persist;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TournamentManager extends ZUtils implements Tournament {

	private static List<Arena> arenas = new ArrayList<Arena>();
	private static Location location;
	private transient List<Team> teams = new ArrayList<>();
	private transient List<Team> eliminatedTeams = new ArrayList<>();
	private transient List<Duel> duels = new ArrayList<>();
	private transient int maxTeams;
	private transient int currentTeams;

	private transient boolean isStart = false;
	private transient boolean isWaiting = false;
	private transient TournoisType type;
	private transient int wave = 1;
	private transient int countTeam;
	private transient int timer = 300;
	private transient boolean asNewTimer = false;
	private transient NMS nms;

	private transient final HashSet<String> substanceChars = new HashSet<String>(Arrays.asList(new String[] { "0", "1",
			"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" }));

	private transient int maxTeamPerArena = 1;
	private transient boolean isTimeBetweenWave;

	public TournamentManager() {

		double nms = ItemDecoder.getNMSVersion();

		if (nms == 1.7)
			this.nms = new NMS_1_7();
		if (nms == 1.8)
			this.nms = new NMS_1_8();
		if (nms == 1.9)
			this.nms = new NMS_1_9();
		if (nms == 1.10)
			this.nms = new NMS_1_10();
		if (nms == 1.11)
			this.nms = new NMS_1_11();
		if (nms == 1.12)
			this.nms = new NMS_1_12();
		if (nms == 1.13)
			this.nms = new NMS_1_13();
		if (nms == 1.14)
			this.nms = new NMS_1_14();
		if (nms == 1.15)
			this.nms = new NMS_1_15();
		if (nms == 1.16)
			this.nms = new NMS_1_16();

		Logger.info("Loaded NMS " + nms + " !", LogType.INFO);

	}

	/**
	 * Permet de load la class
	 */
	@Override
	public void load(Persist p) {
		p.loadOrSaveDefault(this, TournamentManager.class, "tournaments");
		Logger.info("Number of arenas available: " + arenas.size(), LogType.SUCCESS);
	}

	/**
	 * Permet de save la class
	 */
	@Override
	public void save(Persist p) {
		p.save(this, "tournaments");
	}

	@Override
	public Team getByName(String name) {
		return teams.stream().filter(team -> team.getName().equals(name) || team.getOwner().getName().equals(name))
				.findAny().orElse(null);
	}

	@Override
	public Team getByPlayer(Player player) {
		return teams.stream().filter(team -> team.contains(player)).findAny().orElse(null);
	}

	@Override
	public Duel getDuel(Team team) {
		return duels.stream().filter(duel -> duel.match(team)).findAny().orElse(null);
	}

	@Override
	public Arena getAvaibleArena() {
		return arenas.stream().filter(arena -> arena.size() <= maxTeamPerArena - 1).findAny().orElse(null);
	}

	@Override
	public int getAvaibleArenaCount() {
		return (int) arenas.stream().filter(arena -> arena.size() <= maxTeamPerArena - 1).count();
	}

	@Override
	public Team getByPassTeam() {
		return teams.stream().filter(team -> !team.isInDuel()).findAny().orElse(null);
	}

	@Override
	public void clearPlayer(Player player) {
		player.getInventory().clear();
		player.getInventory().setBoots(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setHelmet(null);
		player.getPlayer().setItemOnCursor(null);
		player.getPlayer().setFireTicks(0);
		player.getPlayer().getOpenInventory().getTopInventory().clear();
		player.getPlayer().getActivePotionEffects().clear();
		player.setGameMode(GameMode.SURVIVAL);
		player.getPlayer().getActivePotionEffects().forEach(e -> {
			player.getPlayer().removePotionEffect(e.getType());
		});
	}

	@Override
	public Team getRandomTeam() {
		Random random = new Random();
		int rest = teams.size() % 2;
		if (rest == 1 && teams.size() - getDuelTeam() == 1)
			return null;
		Team team = teams.get(random.nextInt(teams.size()));
		if (team.isInDuel())
			return getRandomTeam();
		else {
			team.setInDuel(true);
			return team;
		}
	}

	@Override
	public int getDuelTeam() {
		return (int) teams.stream().filter(team -> team.isInDuel()).count();
	}

	@Override
	public void setLobbyLocation(Player sender, Location location) {
		if (isStart) {
			message(sender, "§cUn tournois est en cours, action impossible pour le moment.");
			return;
		}

		TournamentManager.location = location;
		message(sender, "§eVous venez de mettre la location pour le lobby.");
	}

	@Override
	public void createRandomDuel() {
		Team team = getRandomTeam();
		Team opponant = getRandomTeam();

		if (team == null || opponant == null)
			return;
		duels.add(new DuelObject(team, opponant));
		countTeam -= 2;
	}

	@Override
	public void createArena(CommandSender sender, Location pos1, Location pos2) {
		if (isStart) {
			message(sender, "§cUn tournois est en cours, action impossible pour le moment.");
			return;
		}
		Arena arena = new ArenaObject(pos1, pos2);
		arenas.add(arena);
		message(sender, "§eVous venez de créer une arène.");
	}

	@Override
	public void sendArena(Player player) {
		if (isStart) {
			message(player, "§cUn tournois est en cours, action impossible pour le moment.");
			return;
		}

		if (arenas.size() == 0) {
			message(player, "§cAucune arène de disponible.");
			return;
		}

		TextComponent message = buildTextComponent("§eListe des arènes§8: §6");
		for (int a = 0; a != arenas.size(); a++) {
			if (a == arenas.size() - 1 && a != 0) {
				message.addExtra("§7 et §6");
			} else if (a != 0) {
				message.addExtra("§7, §6");
			}
			Arena arena = arenas.get(a);
			TextComponent component = new TextComponent("§6" + arena.getId());
			component.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tournament delete " + arena.getId()));
			component.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
					new BaseComponent[] { new TextComponent("§f» §7Clique pour supprimer cette arène"),
							new TextComponent("§f» §7Coordonné 1: " + changeLocationToString(arena.getPos1())),
							new TextComponent("§f» §7Coordonné 2: " + changeLocationToString(arena.getPos2())) }));
			message.addExtra(component);
		}
		player.spigot().sendMessage(message);
	}

	@Override
	public void deleteArena(CommandSender sender, UUID uuid) {
		if (isStart) {
			message(sender, "§cUn tournois est en cours, action impossible pour le moment.");
			return;
		}

		Arena a = arenas.stream().filter(arena -> {
			return arena.getId().equals(uuid);
		}).findAny().orElse(null);

		if (a == null) {
			message(sender, "§cImpossible de trouver l'arène.");
			return;
		}

		arenas.remove(a);
		message(sender, "§aVous venez de supprimer une arène.");
	}

	@Override
	public boolean canHurt(Player player, Player damager) {
		Team teamA = getByPlayer(player);
		Team teamB = getByPlayer(damager);
		return teamA == null ? true : teamB == null ? true : teamA.equals(teamB);
	}

	@Override
	public boolean inventoryHasItem(Player player) {
		ItemStack itemStack = player.getInventory().getBoots();
		if (itemStack != null)
			return true;

		itemStack = player.getInventory().getChestplate();
		if (itemStack != null)
			return true;

		itemStack = player.getInventory().getLeggings();
		if (itemStack != null)
			return true;

		itemStack = player.getInventory().getHelmet();
		if (itemStack != null)
			return true;

		for (ItemStack itemStack1 : player.getInventory().getContents())
			if (itemStack1 != null)
				return true;

		return false;
	}

	@Override
	public void removeTeam(Player player) {
		Team team = getByPlayer(player);
		if (team == null)
			return;
		team.death(player);
		if (team.hasLoose())
			teams.remove(team);
	}

	@Override
	public void checkTeam() {
		Iterator<Team> iterator = teams.iterator();
		while (iterator.hasNext()) {
			Team team = iterator.next();
			if (!team.isValid())
				iterator.remove();
		}
	}

	@Override
	public void startTournois(CommandSender sender, TournoisType type) {

		if (isStart || isWaiting) {
			message(sender, "§cUn tournois est déjà en cours.");
			return;
		}

		if (arenas.size() == 0) {
			message(sender, "§cVous ne pouvez pas lancer de tournois sans aucune arène.");
			return;
		}

		if (location == null) {
			message(sender, "§cVous ne pouvez pas lancer de tournois sans la location de lobby.");
			return;
		}

		TournamentStartEvent event = new TournamentStartEvent(type);
		event.callEvent();

		if (event.isCancelled())
			return;

		this.isStart = true;
		this.isWaiting = true;
		this.type = type;
		this.asNewTimer = false;

		this.duels.clear();
		this.teams.clear();

		this.maxTeams = 0;

		String pvp = type.getMax() + "v" + type.getMax();
		broadcast("§eDébut d'un tournois PVP en §6%s §edans §65 §eminutes§e.", pvp);
		broadcast("§eFaire §f/tournois§e pour avoir toutes les commandess");

		/**
		 * A MODIF
		 */
		Bukkit.getOnlinePlayers().forEach(player -> {

			if (Config.sendMessageInTitle)
				nms.sendTitle(player.getPlayer(), "§f§kII§e Tournois §f§kII",
						"§eUn tournois §f" + pvp + " §evient de commencer !", 10, 20 * 3, 10);

		});

		timer = Config.timeStartTournamentInSecond;

		new BukkitRunnable() {

			@Override
			public void run() {

				if (!isWaiting) {
					cancel();
					return;
				}

				TournamentEvent event = new TournamentStartTickEvent(timer);
				event.callEvent();

				if (event.isCancelled()) {
					cancel();
					return;
				}

				if (Config.displayTournamentInformations.contains(timer))
					broadcast("§eEncore §6%s §eavant le début du tournois PVP.", TimerBuilder.getStringTime(timer));

				if (timer == 0) {
					
					if (teams.size() <= 1) {

						if (!asNewTimer && Config.restartTeamSearch) {

							event = new TournamentStartNoEnoughRestartEvent();
							event.callEvent();

							asNewTimer = true;
							timer = Config.timeStartTournamentInSecond;
							broadcast(
									"§ePas assez de joueur pour commencer le tournois, vous avez encore §65 §eminutes pour créer une équipe.");
						} else {

							event = new TournamentStartNoEnoughEvent();
							event.callEvent();

							isStart = false;
							isWaiting = false;
							asNewTimer = false;

							cancel();

							broadcast("§ePas assez de joueur, event annulé !");

						}
						
					} else {
						cancel();
						start();
					}
				}
				timer--;
				if (!isStart) {
					cancel();
				}

			}
			/**
			 * 
			 * 
			 * 
			 * A MODIF
			 * 
			 * 
			 * 
			 */

		}.runTaskTimer(ZPlugin.z(), 0, 1);

		/**
		 * 
		 * 
		 * 
		 * A MODIF
		 * 
		 * 
		 * 
		 */

	}

	@Override
	public void start() {
		if (teams.size() == 0) {
			broadcast("§cAucune team, le tournois est annulé !");
			return;
		}

		this.isWaiting = false;
		this.isStart = true;
		this.wave = 1;
		this.eliminatedTeams.clear();

		checkTeam();

		this.maxTeams = this.teams.size();
		this.currentTeams = this.teams.size();

		TournamentStartWaveEvent event = new TournamentStartWaveEvent(new ArrayList<>(teams), type);
		event.callEvent();
		if (event.isCancelled())
			return;

		broadcast("§eDébut du tournois PVP !");
		broadcast("§eNombre d'équipe dans le tournois§7: §f%s", teams.size());

		this.startWave();
	}

	@Override
	public void startWave() {

		if (!isStart)
			return;

		TournamentEvent event = new TournamentPreWaveEvent(new ArrayList<>(teams), type, true);
		event.callEvent();
		if (event.isCancelled())
			return;

		// Check
		checkTeam();

		int currentWave = wave;
		this.countTeam = teams.size();
		this.maxTeamPerArena = 1;

		arenas.forEach(arena -> arena.clear());

		if (countTeam == 1) {
			end();
			return;
		}

		int duel = countTeam >> 1;
		for (int a = 0; a < duel; a++) {
			createRandomDuel();
		}

		Team bypassTeam = getByPassTeam();

		event = new TournamentPostWaveEvent(new ArrayList<>(teams), new ArrayList<>(duels), new ArrayList<>(arenas),
				type, bypassTeam);
		event.callEvent();
		if (event.isCancelled())
			return;

		// Si il y a un nombre impair d'équipe
		if (bypassTeam != null)
			bypassTeam.message("§eVous êtes automatiquement qualifié pour la manche suivante !");

		broadcast("§eNombre de duels§7: §6%s", duel);
		broadcast("§eDébut de la manche §6" + currentWave + " §e! Que le meilleur gagne !");

		wave++;
		duels.forEach(currentDuel -> {

			if (getAvaibleArenaCount() == 0)
				maxTeamPerArena++;

			Arena arena = getAvaibleArena();

			TournamentEvent event2 = new TournamentWareArenaEvent(arena, currentDuel);
			event2.callEvent();

			arena.addDuel(currentDuel);
			currentDuel.startDuel(arena.getPos1(), arena.getPos2());
			currentDuel.setArenea(arena);

		});

		broadcast("§eFin de la manche §6" + currentWave + " §e dans §f5 minutes§e.");
		new BukkitRunnable() {

			int timer = 300;

			@Override
			public void run() {

				if (isTimeBetweenWave) {
					cancel();
					return;
				}

				if (!isStart) {
					cancel();
					return;
				}

				timer--;

				if (timer == 120 || timer == 60 || timer == 30 || timer == 10 || timer == 3 || timer == 2 || timer == 1)
					broadcast("§eFin de la manche §6" + currentWave + "§e dans §f%s§e.",
							TimerBuilder.getStringTime(timer));

				if (timer <= 0) {

					TournamentEvent event = new TournamentAutoEndWaveEvent();
					event.callEvent();

					cancel();
					broadcast("§eLe temps est écoulé, la prochaine manche va débuter... ");

					duels.forEach(duel -> {

						duel.heal();

						Team winner = duel.getTeam();
						winner.setInDuel(false);
						winner.clear();
						winner.reMap();
						winner.show();
						winner.heal();
						winner.teleport(location);

						Team looser = duel.getOpponant();
						looser.setInDuel(false);
						looser.clear();
						looser.heal();
						looser.show();
						looser.teleport(location);

					});

					duels.clear();

					canStartNextWave();

				}

			}
		}.runTaskTimer(ZPlugin.z(), 0, 20);
	}

	@Override
	public void end() {
		if (teams.size() == 0) {
			broadcast("§ePersonne n'a gagné l'event tournois !");
			return;
		}

		Team winner = teams.get(0);
		winner.clear();
		winner.getPlayers().forEach(player -> player.teleport(location));
		winner.show();
		winner.setPosition(1);
		if (!eliminatedTeams.contains(winner))
			eliminatedTeams.add(winner);
		
		TournamentEvent event = new TournamentWinEvent(new ArrayList<>(eliminatedTeams), winner);
		event.callEvent();
		
		if (event.isCancelled())
			return;
		
		this.isStart = false;
		this.isWaiting = false;
		this.teams.clear();
		this.duels.clear();
		this.wave = 0;
		
		broadcast("§eEvent tournois pvp terminé !");

		if (Config.sendMessageInTitle)
			Bukkit.getOnlinePlayers().forEach(player -> nms.sendTitle(player, "§f§kII§e Event tournois §f§kII",
					"§eFélicitation à l'équipe §f" + winner.getName() + "§e qui gagne le tournois !", 10, 20 * 5, 10));

		Bukkit.broadcastMessage("");
		broadcast("§eClassement des équipes§7:");
		Pagination<Team> pagination = new Pagination<>();
		pagination.paginateReverse(eliminatedTeams, 3, 1).forEach(team -> {

			String p = team.getPosition() == 1 ? "§4§lpremière"
					: team.getPosition() == 2 ? "§c§ldeuxième" : "§6§ltroisième";
			String msg = "§eL'équipe §6" + team.getName() + " §ea terminé §f" + p + " §eau tournois !";

			TextComponent component = buildTextComponent(msg);

			List<String> strings = new ArrayList<>();
			strings.add("§eJoueurs§7:");
			team.getRealPlayers().forEach(pp -> {
				if (pp != null && pp.isOnline())
					strings.add(" §7- §f" + pp.getName());
			});

			setHoverMessage(component, strings);

			Bukkit.getOnlinePlayers().forEach(bukkitPlayer -> {
				bukkitPlayer.spigot().sendMessage(component);
			});

		});

		this.eliminatedTeams.forEach(team -> {

			TournamentEvent event2 = new TournamentRewardTeamEvent(team);
			event2.callEvent();
			
		});

		this.eliminatedTeams.clear();
	}

	@Override
	public void createTeam(Player player, String name) {
		if (isStart && !isWaiting) {
			message(player, "§cVous ne pouvez pas créer de team pour le moment.");
			return;
		}

		if (!isWaiting) {
			message(player, "§cVous ne pouvez pas créer de team pour le moment.");
			return;
		}

		// Verif si le mec a déjà une team
		Team team = getByPlayer(player);
		if (team != null) {
			message(player, "§cVous avez déjà une équipe, vous ne pouvez pas en créer une autre.");
			return;
		}

		// Verif si la team existe
		team = getByName(name);
		if (team != null) {
			message(player, "§cLe nom §f%s§c est déjà pris pour une team, vous devez en choisir un autre.", name);
			return;
		}

		// Verif de la taille maximal
		if (name.length() > Config.teamNameMaxName) {
			message(player, "§cLe nom de votre team ne doit pas dépasser les §f%s §ccaractères.",
					Config.teamNameMaxName);
			return;
		}

		// Verif de la taille minimal
		if (name.length() < Config.teamNameMinName) {
			message(player, "§cLe nom de votre team doit avoir au minimum §f3 §ccaractères.");
			return;
		}

		// Verif des char
		for (char c : name.toCharArray()) {
			if (!substanceChars.contains(String.valueOf(c))) {
				message(player, "§cLe nom de faction doit être alphanumérique, le caractère §f%s§c n'est pas autorié.",
						c);
				return;
			}
		}

		// Verif de l'inventaire
		if (inventoryHasItem(player)) {
			message(player,
					"§cVous devez avoir un inventaire vide pour participer au tournois §8(§7Votre inventaire va être supprimer lorsque vous allez créer votre équipe§8)");
			return;
		}

		
		TournamentCreateTeamEvent event = new TournamentCreateTeamEvent(new TeamObject(name, type.getMax(), player), player, name);
		event.callEvent();
		
		if (event.isCancelled())
			return;
		
		name = event.getName();
		
		team = new TeamObject(name, type.getMax(), player);
		this.teams.add(team);
		player.teleport(location);
		clearPlayer(player);

		if (Config.sendMessageInTitle)
			nms.sendTitle(player.getPlayer(), "§f§kII§e Félicitation §f§kII",
					"§eVous venez de créer une team pour le tournois PVP", 10, 30, 10);

		broadcast("§f%s §evient de créer la team §6%s§e.", player.getName(), name);
	}

	@Override
	public void joinTeam(Player player, String name) {
		if (isStart && !isWaiting) {
			message(player, "§cVous ne pouvez pas créer de team pour le moment.");
			return;
		}

		Team team = getByPlayer(player);
		if (team != null) {
			message(player, "§cVous avez déjà rejoins une équipe. Vous ne pouvez pas rejoindre §f%s§c.", name);
			return;
		}

		team = getByName(name);
		if (team == null) {
			message(player, "§cAucune team avec le nom §f%s §cexiste.", name);
			return;
		}

		if (inventoryHasItem(player)) {

			message(player,
					"§cVous devez avoir un inventaire vide pour participer au tournois §8(§7Votre inventaire va être supprimer lorsque vous allez rejoindre une équipe§8)");

			return;
		}

		if (!team.isInvite(player)) {

			message(player, "§cVous n'êtes pas inviter à rejoindre cette équipe.");

			return;
		}

		TournamentJoinTeamEvent joinTeamEvent = new TournamentJoinTeamEvent(team, player, team.join(player));
		joinTeamEvent.callEvent();
		
		if (joinTeamEvent.isCancelled())
			return;
		
		if (!joinTeamEvent.isCanJoin()) {

			message(player, "§cVous ne pouvez pas rejoindre cette team.");

		} else {

			TournamentJoinSuccessTeamEvent event = new TournamentJoinSuccessTeamEvent(team, player);
			event.callEvent();
			
			if (event.isCancelled())
				return;
			
			team.message("§f%s §evient de rejoindre votre team !", player.getName());
			player.teleport(location);
			clearPlayer(player);
			nms.sendTitle(player.getPlayer(), "§f§kII§e Félicitation §f§kII",
					"§eVous venez de rejoindre la team §6" + name + "§e !", 10, 30, 10);

		}
	}

	@Override
	public void invitePlayer(Player player, Player target) {
		if (isStart && !isWaiting) {
			message(player, "§cVous ne pouvez pas inviter de joueur pour le moment.");
			return;
		}

		if (!isWaiting) {
			message(player, "§cVous ne pouvez pas inviter de joueur pour le moment.");
			return;
		}

		Team team = getByPlayer(player);
		if (team == null) {
			message(player, "§cVous n'avez pas d'équipe, vous ne pouvez pas faire ceci.");
			return;
		}

		if (player.getUniqueId().equals(target.getUniqueId())) {
			message(player, "§cVous ne pouvez pas vous inviter dans votre propre équipe.");
			return;
		}

		if (!team.isOwner(player)) {
			message(player, "§cSeul le chef de votre équipe peut inviter des joueurs.");
			return;
		}

		if (type.equals(TournoisType.V1)) {
			message(player, "§cVous ne pouvez pas inviter de joueur pour un tournois §f1v1§c.");
			return;
		}

		if (team.isInvite(target)) {
			team.removeInvite(target);
			team.message("§f%s §evient de retirer l'invitation de §6%s §eà rejoindre votre équipe.", player.getName(),
					target.getName());
			return;
		}

		TournamentEvent event = new TournamentInviteTeamEvent(team, player);
		event.callEvent();
		
		if (event.isCancelled())
			return;
		
		team.invite(target);
		team.message("§f%s §evient d'inviter §6%s §eà rejoindre votre équipe.", player.getName(), target.getName());
		message(target, "§eVous venez de reçevoir une inventation de §6%s §epour rejoindre l'équipe §f%s§e.",
				player.getName(), team.getName());

		TextComponent component = buildTextComponent("§eFaite §f/tournois §fjoin §f" + team.getName()
				+ " §epour §erejoindre §ecette §eéquipe§e. §8(§bClique §bici§8)");
		setClickAction(component, Action.RUN_COMMAND, "/tournois join " + team.getName());
		setHoverMessage(component, "§7Clique pour rejoindre l'équipe §f" + team.getName());

		target.spigot().sendMessage(component);
	}

	@Override
	public void leave(Player player, boolean message) {
		if (isStart && !isWaiting) {
			if (message)
				message(player, "§cVous ne pouvez pas quitter votre équipe durant le combat.");
			return;
		}

		Team team = getByPlayer(player);
		if (team == null) {
			if (message)
				message(player, "§cVous n'êtes pas dans une team.");
			return;
		}

		if (team.getOwner().equals(player)) {

			team.disband();
			this.teams.remove(team);

		} else {

			if (message)
				message(player, "§eVous venez de quitter votre équipe.");
			team.leave(player);
			player.teleport(location);
			clearPlayer(player);

		}
	}

	@Override
	public void loose(Team team, Duel duel, Player player) {
		
		duel.onPlayerLoose(player);
		
		TournamentEvent event = new TournamentLooseTeamEvent(team, duel, player);
		event.callEvent();
		
		duel.message("§f" + player.getName() + " §evient d'être éliminé !");
		player.teleport(location);

		clearPlayer(player);

		if (duel.hasWinner() && duel.isDuel()) {

			duel.heal();

			Team winner = duel.getWinner();
			winner.message("§eVous venez de gagner votre duel !");
			winner.setInDuel(false);
			winner.clear();
			winner.reMap();
			winner.show();
			winner.heal();
			winner.teleport(location);

			Team looser = duel.getLooser();
			looser.setInDuel(false);
			looser.clear();
			looser.heal();
			looser.show();
			looser.teleport(location);

			looser.setPosition(currentTeams--);
			looser.message("§eVous avez perdu le tournois, votre équipe est donc disqulifiée.");
			looser.message("§eVous êtes à la position §6" + looser.getPosition() + "§8/§6" + maxTeams);

			if (!eliminatedTeams.contains(looser))
				eliminatedTeams.add(looser);

			teams.remove(looser);
			duels.remove(duel);

			canStartNextWave();
		}
	}

	@Override
	public void canStartNextWave() {
		/**
		 * On termine l'event s'il reste qu'une seul team Sinon on commence une
		 * nouvelle manche
		 */

		if (teams.size() == 1) {

			teams.forEach(Team::show);
			arenas.forEach(Arena::clear);
			end();

		} else if (duels.size() == 0) {

			teams.forEach(Team::show);
			arenas.forEach(Arena::clear);
			broadcast("§eDébut de la prochaine manche dans §f10 §esecondes.");

			isTimeBetweenWave = true;
			new BukkitRunnable() {

				@Override
				public void run() {

					if (!isStart)
						return;

					startWave();

					broadcast("§eNombre de team§7: §6%s", teams.size());
					broadcast("§eNombre d'équipe restante: §6%s", teams.size());
					isTimeBetweenWave = false;

				}
			}.runTaskLater(ZPlugin.z(), 20 * 10);
		}
	}

	@Override
	public boolean canUseCommand(CommandSender sender) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		Team team = getByPlayer(player);
		return team != null && (isStart || isWaiting);
	}

	@Override
	public void stopTournois(CommandSender sender) {
		if (!isStart && !isWaiting) {
			message(sender, "§cAucun tournois en cours.");
			return;
		}

		if (isWaiting) {

			isStart = false;
			isWaiting = false;
			broadcast("§cLe tournois PVP vient d'être annulé.");
			return;

		}

		if (isStart) {

			isStart = false;
			isWaiting = false;
			broadcast("§cLe tournois PVP vient d'être annulé.");
			teams.forEach(e -> {
				e.getRealPlayers().forEach(p -> {
					p.teleport(location);
					p.teleport(location);
				});
			});

			teams.clear();
			duels.clear();
		}
	}

	@Override
	public void nextWave(CommandSender sender) {
		if (!isStart) {
			message(sender, "§cAucun tournois en cours.");
			return;
		}

		duels.forEach(duel -> {

			duel.heal();

			Team winner = duel.getTeam();
			winner.setInDuel(false);
			winner.clear();
			winner.reMap();
			winner.show();
			winner.heal();
			winner.teleport(location);

			Team looser = duel.getOpponant();
			looser.setInDuel(false);
			looser.clear();
			looser.heal();
			looser.show();
			looser.teleport(location);

		});

		duels.clear();
		canStartNextWave();
	}

	@Override
	public boolean isStart() {
		return isStart;
	}

	@Override
	public boolean isWaiting() {
		return isWaiting;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public boolean isTimeBetweenWave() {
		return isTimeBetweenWave;
	}

	@Override
	public NMS getNMS() {
		return nms;
	}

}
