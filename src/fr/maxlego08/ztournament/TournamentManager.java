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
import fr.maxlego08.ztournament.api.Kit;
import fr.maxlego08.ztournament.api.Kits;
import fr.maxlego08.ztournament.api.NMS;
import fr.maxlego08.ztournament.api.Team;
import fr.maxlego08.ztournament.api.Tournament;
import fr.maxlego08.ztournament.api.TournoisType;
import fr.maxlego08.ztournament.api.events.TournamentAutoEndWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentEvent;
import fr.maxlego08.ztournament.api.events.TournamentPostWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentPreWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartNoEnoughEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartNoEnoughRestartEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartTickEvent;
import fr.maxlego08.ztournament.api.events.TournamentStartWaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentStopEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamCreateEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamDisbandEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamInviteEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamJoinEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamJoinSuccessEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamLeaveEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamLooseEvent;
import fr.maxlego08.ztournament.api.events.TournamentTeamRewardEvent;
import fr.maxlego08.ztournament.api.events.TournamentWareArenaEvent;
import fr.maxlego08.ztournament.api.events.TournamentWaveCommandNextEvent;
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
import fr.maxlego08.ztournament.zcore.enums.Message;
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

	private static List<ArenaObject> arenas = new ArrayList<ArenaObject>();
	private static String location;
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
	private transient final Kits kits;
	private transient Kit kit;

	private transient final HashSet<String> substanceChars = new HashSet<String>(Arrays.asList(new String[] { "0", "1",
			"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" }));

	private transient int maxTeamPerArena = 1;
	private transient boolean isTimeBetweenWave;

	public TournamentManager(Kits kits) {

		double nms = ItemDecoder.getNMSVersion();

		if (nms == 1.7)
			this.nms = new NMS_1_7();
		else if (nms == 1.8)
			this.nms = new NMS_1_8();
		else if (nms == 1.9)
			this.nms = new NMS_1_9();
		else if (nms == 1.10)
			this.nms = new NMS_1_10();
		else if (nms == 1.11)
			this.nms = new NMS_1_11();
		else if (nms == 1.12)
			this.nms = new NMS_1_12();
		else if (nms == 1.13)
			this.nms = new NMS_1_13();
		else if (nms == 1.14)
			this.nms = new NMS_1_14();
		else if (nms == 1.15)
			this.nms = new NMS_1_15();
		else if (nms == 1.16)
			this.nms = new NMS_1_16();

		
		this.kits = kits;

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
			message(sender, Message.TOURNAMENT_ENABLE);
			return;
		}

		TournamentManager.location = changeLocationToStringEye(location);
		message(sender, Message.TOURNAMENT_LOBBY_CREATE);
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
			message(sender, Message.TOURNAMENT_ENABLE);
			return;
		}
		ArenaObject arena = new ArenaObject(pos1, pos2);
		arenas.add(arena);
		message(sender, Message.TOURNAMENT_ARENA_CREATE);
	}

	@Override
	public void sendArena(Player player) {
		if (isStart) {
			message(player, Message.TOURNAMENT_ENABLE);
			return;
		}

		if (arenas.size() == 0) {
			message(player, Message.TOURNAMENT_ARENA_NO);
			return;
		}

		TextComponent message = buildTextComponent("§eList of arenas§8: §6");
		for (int a = 0; a != arenas.size(); a++) {
			if (a == arenas.size() - 1 && a != 0) {
				message.addExtra("§7 et §6");
			} else if (a != 0) {
				message.addExtra("§7, §6");
			}
			Arena arena = arenas.get(a);
			TextComponent component = new TextComponent("§6" + arena.getId());
			component.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/ztournament delete " + arena.getId()));
			component.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
					new BaseComponent[] { new TextComponent("§8» §7Click to delete this arena\n"),
							new TextComponent(
									"§8» §7Location 1: §f" + arena.getPos1String().replace(",", " §8,§f ") + "\n"),
							new TextComponent("§8» §7Location 2: §f" + arena.getPos2String().replace(",", " §8,§f ")) }));
			message.addExtra(component);
		}
		player.spigot().sendMessage(message);
	}

	@Override
	public void deleteArena(CommandSender sender, UUID uuid) {
		if (isStart) {
			message(sender, Message.TOURNAMENT_ENABLE);
			return;
		}

		Arena a = arenas.stream().filter(arena -> {
			return arena.getId().equals(uuid);
		}).findAny().orElse(null);

		if (a == null) {
			message(sender, Message.TOURNAMENT_ARENA_NOT_FOUND);
			return;
		}

		arenas.remove(a);
		message(sender, Message.TOURNAMENT_ARENA_DELETE);
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
	public void startTournois(CommandSender sender, TournoisType type, Kit kit) {

		if (isStart || isWaiting) {
			message(sender, Message.TOURNAMENT_ENABLE);
			return;
		}

		if (arenas.size() == 0) {
			message(sender, Message.TOURNAMENT_START_ERROR_ARENA);
			return;
		}

		if (location == null) {
			message(sender, Message.TOURNAMENT_START_ERROR_LOOBY);
			return;
		}

		this.kit = kit;

		TournamentStartEvent event = new TournamentStartEvent(type, kit);
		event.callEvent();

		if (event.isCancelled())
			return;

		this.kit = event.getKit();
		this.type = event.getType();

		this.isStart = true;
		this.isWaiting = true;
		this.asNewTimer = false;

		this.duels.clear();
		this.teams.clear();

		this.maxTeams = 0;

		String pvp = type.getMax() + "v" + type.getMax();

		Message messageStart = Message.TOURNAMENT_START;
		messageStart.getMessages().forEach(message -> broadcast(message.replace("%type%", pvp)));

		/**
		 * A MODIF
		 */
		Message messages = Message.TITLE_START;
		if (messages.isUseTitle())
			Bukkit.getOnlinePlayers().forEach(player -> {
				nms.sendTitle(player.getPlayer(), messages.getTitle(), messages.getSubTitle().replace("%type%", pvp),
						(int) messages.getStart(), (int) messages.getTime(), (int) messages.getEnd());
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

				if (Config.displayTournamentInformations.contains(timer)) {
					broadcast(Message.TOURNAMENT_START_TIMER.replace("%timer%", TimerBuilder.getStringTime(timer)));

					Message startMessage = Message.TITLE_START_INFO;

					if (startMessage.isUse()) {
						String sub = startMessage.getSubTitle().replace("%timer%", TimerBuilder.getStringTime(timer));
						Bukkit.getOnlinePlayers().forEach(player -> {
							nms.sendTitle(player.getPlayer(), startMessage.getTitle(), sub,
									(int) startMessage.getStart(), (int) startMessage.getTime(),
									(int) startMessage.getEnd());
						});
					}
				}

				if (timer == 0) {

					if (teams.size() <= 1) {

						if (!asNewTimer && Config.restartTeamSearch) {

							event = new TournamentStartNoEnoughRestartEvent();
							event.callEvent();

							asNewTimer = true;
							timer = Config.timeStartTournamentInSecond;
							broadcast(Message.TOURNAMENT_START_ERROR_PLAYER);

							Message noPlayer = Message.TITLE_START_ERROR_PLAYER;
							Bukkit.getOnlinePlayers().forEach(player -> {
								nms.sendTitle(player.getPlayer(), noPlayer.getTitle(), noPlayer.getSubTitle(),
										(int) noPlayer.getStart(), (int) noPlayer.getTime(), (int) noPlayer.getEnd());
							});

						} else {

							event = new TournamentStartNoEnoughEvent();
							event.callEvent();

							isStart = false;
							isWaiting = false;
							asNewTimer = false;

							cancel();

							broadcast(Message.TOURNAMENT_START_ERROR_PLAYER_CANCEL);

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

		}.runTaskTimer(ZPlugin.z(), 0, 20);

	}

	@Override
	public void start() {
		if (teams.size() == 0) {
			broadcast(Message.TOURNAMENT_START_ERROR_TEAM);
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

		Message message = Message.TOURNAMENT_START_FIRST_WAVE;
		message.getMessages().forEach(e -> broadcast(e.replace("%size%", String.valueOf(teams.size()))));

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
			Message.TOURNAMENT_WAVE_AUTO.getMessages().forEach(e -> bypassTeam.message(e));

		Message.TOURNAMENT_WAVE_START.getMessages().forEach(message -> {
			broadcast(message.replace("%round%", String.valueOf(currentWave)).replace("%duel%",
					String.valueOf(duels.size())));
		});

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

		broadcast(Message.TOURNAMENT_WAVE_TIMER.replace("%round%", String.valueOf(currentWave)).replace("%timer%",
				TimerBuilder.getStringTime(Config.timeWaveEndInSecond)));
		new BukkitRunnable() {

			int timer = Config.timeWaveEndInSecond;

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

				if (Config.displayWaveEndInformations.contains(timer))
					broadcast(Message.TOURNAMENT_WAVE_TIMER.replace("%round%", String.valueOf(currentWave))
							.replace("%timer%", TimerBuilder.getStringTime(timer)));

				if (timer <= 0) {

					TournamentEvent event = new TournamentAutoEndWaveEvent();
					event.callEvent();

					cancel();
					broadcast(Message.TOURNAMENT_WAVE_END);

					duels.forEach(duel -> {

						duel.heal();

						Team winner = duel.getTeam();
						winner.setInDuel(false);
						winner.clear();
						winner.reMap();
						winner.show();
						winner.heal();
						winner.teleport(getLocation());

						Team looser = duel.getOpponant();
						looser.setInDuel(false);
						looser.clear();
						looser.heal();
						looser.show();
						looser.teleport(getLocation());

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
			broadcast(Message.TOURNAMENT_WIN_ERROR);
			return;
		}

		Team winner = teams.get(0);
		winner.clear();
		winner.getPlayers().forEach(player -> player.teleport(getLocation()));
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

		Message message = Message.TITLE_WIN;
		if (message.isUseTitle()) {
			String sub = message.getSubTitle().replace("%team%", winner.getName());
			Bukkit.getOnlinePlayers().forEach(player -> nms.sendTitle(player, message.getTitle(), sub,
					(int) message.getStart(), (int) message.getTime(), (int) message.getEnd()));
		}

		Message.TOURNAMENT_WIN.getMessages().forEach(e -> broadcast(e));

		if (Config.showRanking) {
			Pagination<Team> pagination = new Pagination<>();
			pagination.paginateReverse(eliminatedTeams, 3, 1).forEach(team -> {

				String position = Config.rankingPosition.getOrDefault(team.getPosition(),
						"Impossible de trouver la position, message de vérifier votre configuration.");

				String msg = Message.TOURNAMENT_CLASSEMENT.replace("%team%", team.getName()).replace("%position%",
						position);

				TextComponent component = buildTextComponent(msg);

				List<String> strings = new ArrayList<>();
				List<String> classementMessage = Message.TOURNAMENT_CLASSEMENT_HOVER.getMessages();
				classementMessage.forEach(m -> {

					if (m.equals("%players%"))
						team.getRealPlayers().forEach(pp -> {
							if (pp != null)
								strings.add(
										Message.TOURNAMENT_CLASSEMENT_HOVER_PLAYER.replace("%player%", pp.getName()));
						});
					else
						strings.add(m);

				});

				setHoverMessage(component, strings);

				Bukkit.getOnlinePlayers().forEach(bukkitPlayer -> {
					bukkitPlayer.spigot().sendMessage(component);
				});

			});
		}

		this.eliminatedTeams.forEach(team -> {

			Reward reward = this.getReward(team.getPosition());

			TournamentTeamRewardEvent tournamentEvent = new TournamentTeamRewardEvent(team, reward);
			tournamentEvent.callEvent();

			if (tournamentEvent.isCancelled())
				return;

			reward = tournamentEvent.getReward();

			if (reward != null) {

				reward.getCommands().forEach(command -> {

					String finalCommand = command.replace("%team%", team.getName());

					if (finalCommand.contains("%player%")) {
						team.getRealPlayers().forEach(player -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								finalCommand.replace("%player%", player.getName())));
					} else {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand);
					}

				});

			}

		});

		this.eliminatedTeams.clear();
	}

	@Override
	public void createTeam(Player player, String name) {
		if (isStart && !isWaiting) {
			message(player, Message.TOURNAMENT_CREATE_ERROR);
			return;
		}

		if (!isWaiting) {
			message(player, Message.TOURNAMENT_CREATE_ERROR);
			return;
		}

		// Verif si le mec a déjà une team
		Team team = getByPlayer(player);
		if (team != null) {
			message(player, Message.TOURNAMENT_CREATE_ERROR_PLAYER);
			return;
		}

		// Verif si la team existe
		team = getByName(name);
		if (team != null) {
			message(player, Message.TOURNAMENT_CREATE_ERROR_EXIT.replace("%name%", name));
			return;
		}

		// Verif de la taille maximal
		if (name.length() > Config.teamNameMaxName) {
			message(player,
					Message.TOURNAMENT_CREATE_ERROR_NAME_MAX.replace("%max%", String.valueOf(Config.teamNameMaxName)));
			return;
		}

		// Verif de la taille minimal
		if (name.length() < Config.teamNameMinName) {
			message(player,
					Message.TOURNAMENT_CREATE_ERROR_NAME_MIN.replace("%min%", String.valueOf(Config.teamNameMinName)));
			return;
		}

		// Verif des char
		for (char c : name.toCharArray()) {
			if (!substanceChars.contains(String.valueOf(c))) {
				message(player, Message.TOURNAMENT_CREATE_ERROR_NAME.replace("%char%", String.valueOf(c)));
				return;
			}
		}

		// Verif de l'inventaire
		if (inventoryHasItem(player)) {
			message(player, Message.TOURNAMENT_CREATE_ERROR_INVENOTRY);
			return;
		}

		TournamentTeamCreateEvent event = new TournamentTeamCreateEvent(
				new TeamObject(name, type.getMax(), player, kit), player, name);
		event.callEvent();

		if (event.isCancelled())
			return;

		name = event.getName();

		team = new TeamObject(name, type.getMax(), player, kit);
		this.teams.add(team);
		player.teleport(getLocation());
		clearPlayer(player);

		Message message = Message.TITLE_CREATE_SUCCESS;
		if (message.isUseTitle())
			nms.sendTitle(player.getPlayer(), message.getTitle(), message.getSubTitle().replace("%team%", name),
					(int) message.getStart(), (int) message.getTime(), (int) message.getEnd());

		broadcast(
				Message.TOURNAMENT_CREATE_TEAM_BROADCAST.replace("%player%", player.getName()).replace("%team%", name));
	}

	@Override
	public void joinTeam(Player player, String name) {
		if (isStart && !isWaiting) {
			message(player, Message.TOURNAMENT_JOIN_ERROR_JOIN);
			return;
		}

		Team team = getByPlayer(player);
		if (team != null) {
			message(player, Message.TOURNAMENT_JOIN_ERROR_TEAM_ALREADY.replace("%team%", name));
			return;
		}

		team = getByName(name);
		if (team == null) {
			message(player, Message.TOURNAMENT_JOIN_ERROR_TEAM.replace("%team%", name));
			return;
		}

		if (inventoryHasItem(player)) {

			message(player, Message.TOURNAMENT_JOIN_ERROR_INVENOTRY);

			return;
		}

		if (!team.isInvite(player)) {

			message(player, Message.TOURNAMENT_JOIN_ERROR_INVITE);

			return;
		}

		TournamentTeamJoinEvent joinTeamEvent = new TournamentTeamJoinEvent(team, player, team.join(player));
		joinTeamEvent.callEvent();

		if (joinTeamEvent.isCancelled())
			return;

		if (!joinTeamEvent.isCanJoin()) {

			message(player, Message.TOURNAMENT_JOIN_ERROR);

		} else {

			TournamentTeamJoinSuccessEvent event = new TournamentTeamJoinSuccessEvent(team, player);
			event.callEvent();

			if (event.isCancelled())
				return;

			team.message(Message.TOURNAMENT_JOIN_SUCCESS_INFO.replace("%player%", player.getName()));
			player.teleport(getLocation());
			clearPlayer(player);

			Message message = Message.TITLE_JOIN_SUCCESS;
			if (message.isUseTitle())
				nms.sendTitle(player.getPlayer(), message.getTitle(), message.getSubTitle().replace("%team%", name),
						(int) message.getStart(), (int) message.getTime(), (int) message.getEnd());

		}
	}

	@Override
	public void invitePlayer(Player player, Player target) {
		if (isStart && !isWaiting) {
			message(player, Message.TOURNAMENT_INVITE_ERROR);
			return;
		}

		if (!isWaiting) {
			message(player, Message.TOURNAMENT_INVITE_ERROR);
			return;
		}

		Team team = getByPlayer(player);
		if (team == null) {
			message(player, Message.TOURNAMENT_INVITE_ERROR_TEAM);
			return;
		}

		if (player.getUniqueId().equals(target.getUniqueId())) {
			message(player, Message.TOURNAMENT_INVITE_ERROR_PLAYER);
			return;
		}

		if (!team.isOwner(player)) {
			message(player, Message.TOURNAMENT_INVITE_ERROR_OWNER);
			return;
		}

		if (type.equals(TournoisType.V1)) {
			message(player, Message.TOURNAMENT_INVITE_ERROR_TYTE);
			return;
		}

		if (team.isInvite(target)) {
			team.removeInvite(target);
			team.message(Message.TOURNAMENT_INVITE_REMOVE.replace("%player%", player.getName()).replace("%target%",
					target.getName()));
			return;
		}

		TournamentEvent event = new TournamentTeamInviteEvent(team, player);
		event.callEvent();

		if (event.isCancelled())
			return;

		team.invite(target);
		team.message(Message.TOURNAMENT_INVITE_TEAM.replace("%player%", player.getName()).replace("%target%",
				target.getName()));

		message(target,
				Message.TOURNAMENT_INVITE_INFO.replace("%player%", player.getName()).replace("%team%", team.getName()));

		TextComponent component = buildTextComponent(
				Message.TOURNAMENT_INVITE_INFO_JSON.replace("%team%", team.getName()));
		setClickAction(component, Action.RUN_COMMAND, "/tournois join " + team.getName());
		setHoverMessage(component, Message.TOURNAMENT_INVITE_INFO_JSON_HOVER.replace("%team%", team.getName()));

		target.spigot().sendMessage(component);
	}

	@Override
	public void leave(Player player, boolean message) {
		if (isStart && !isWaiting) {
			if (message)
				message(player, Message.TOURNAMENT_TEAM_LEAVE_ERROR);
			return;
		}

		Team team = getByPlayer(player);
		if (team == null) {
			if (message)
				message(player, Message.TOURNAMENT_TEAM_PLAYER_ERROR);
			return;
		}

		if (team.getOwner().equals(player)) {

			TournamentTeamDisbandEvent event = new TournamentTeamDisbandEvent(team, player);
			event.callEvent();

			if (event.isCancelled())
				return;

			team.disband();
			this.teams.remove(team);

		} else {

			TournamentTeamLeaveEvent event = new TournamentTeamLeaveEvent(team, player);
			event.callEvent();

			if (event.isCancelled())
				return;

			if (message)
				message(player, Message.TOURNAMENT_TEAM_LEAVE);
			team.leave(player);
			player.teleport(getLocation());
			clearPlayer(player);

		}
	}

	@Override
	public void loose(Team team, Duel duel, Player player) {

		duel.onPlayerLoose(player);

		TournamentEvent event = new TournamentTeamLooseEvent(team, duel, player);
		event.callEvent();

		duel.message(Message.TOURNAMENT_PLAYER_LOOSE.replace("%player%", player.getName()));
		player.teleport(getLocation());

		clearPlayer(player);

		if (duel.hasWinner() && duel.isDuel()) {

			duel.heal();

			Team winner = duel.getWinner();
			winner.message(Message.TOURNAMENT_DUEL_WIN);
			winner.setInDuel(false);
			winner.clear();
			winner.reMap();
			winner.show();
			winner.heal();
			winner.teleport(getLocation());

			Team looser = duel.getLooser();
			looser.setInDuel(false);
			looser.clear();
			looser.heal();
			looser.show();
			looser.teleport(getLocation());

			looser.setPosition(currentTeams--);
			Message.TOURNAMENT_DUEL_LOOSE.getMessages().forEach(e -> {
				looser.message(e.replace("%position%", String.valueOf(looser.getPosition())).replace("%team%",
						String.valueOf(maxTeams)));
			});

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
			broadcast(Message.TOURNAMENT_WAVE_NEXT_TIME);

			isTimeBetweenWave = true;
			new BukkitRunnable() {

				@Override
				public void run() {

					if (!isStart)
						return;

					broadcast(Message.TOURNAMENT_WAVE_NEXT.replace("%team%", String.valueOf(teams.size())));

					isTimeBetweenWave = false;

					startWave();

				}
			}.runTaskLater(ZPlugin.z(), 20 * Config.timeWaveNextInSecond);
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
			message(sender, Message.TOURNAMENT_NOT_ENABLE);
			return;
		}

		if (isWaiting) {

			TournamentEvent event = new TournamentStopEvent();
			event.callEvent();

			isStart = false;
			isWaiting = false;
			broadcast(Message.TOURNAMENT_STOP);
			return;

		}

		if (isStart) {

			TournamentEvent event = new TournamentStopEvent();
			event.callEvent();

			isStart = false;
			isWaiting = false;
			broadcast(Message.TOURNAMENT_STOP);
			teams.forEach(e -> {
				e.clear();
				e.getRealPlayers().forEach(p -> {
					if (p.isOnline()) {
						p.getPlayer().teleport(getLocation());
						p.getPlayer().teleport(getLocation());
					}
				});
			});

			teams.clear();
			duels.clear();
		}
	}

	@Override
	public void nextWave(CommandSender sender) {
		if (!isStart) {
			message(sender, Message.TOURNAMENT_NOT_ENABLE);
			return;
		}

		TournamentEvent event = new TournamentWaveCommandNextEvent();
		event.callEvent();

		duels.forEach(duel -> {

			duel.heal();

			Team winner = duel.getTeam();
			winner.setInDuel(false);
			winner.clear();
			winner.reMap();
			winner.show();
			winner.heal();
			winner.teleport(getLocation());

			Team looser = duel.getOpponant();
			looser.setInDuel(false);
			looser.clear();
			looser.heal();
			looser.show();
			looser.teleport(getLocation());

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
		return changeStringLocationToLocationEye(location);
	}

	@Override
	public boolean isTimeBetweenWave() {
		return isTimeBetweenWave;
	}

	@Override
	public NMS getNMS() {
		return nms;
	}

	@Override
	public void onPluginDisable() {

		if (isWaiting) {

			isStart = false;
			isWaiting = false;
			broadcast(Message.TOURNAMENT_STOP);
			return;

		}

		if (isStart) {

			isStart = false;
			isWaiting = false;
			broadcast(Message.TOURNAMENT_STOP);
			teams.forEach(e -> {
				e.clear();
				e.getRealPlayers().forEach(p -> {
					if (p.isOnline()) {
						p.getPlayer().teleport(getLocation());
						p.getPlayer().teleport(getLocation());
					}
				});
			});

			teams.clear();
			duels.clear();
		}

	}

	@Override
	public Reward getReward(int position) {
		return Config.rewards.stream()
				.filter(reward -> reward.getMaxPosition() <= position && reward.getMinPosition() >= position).findAny()
				.orElse(null);
	}

	@Override
	public Kits getKits() {
		return kits;
	}

	@Override
	public Kit getKit() {
		return kit;
	}

}
