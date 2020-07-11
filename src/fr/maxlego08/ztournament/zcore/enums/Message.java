package fr.maxlego08.ztournament.zcore.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Message {

	PREFIX("§8(§6zTournament§8)"),

	PREFIX_END("§8(§6zTournament§8)"),

	TELEPORT_MOVE("§cVous ne devez pas bouger !", false), TELEPORT_MESSAGE("§7Téléportatio dans §3%s §7secondes !",
			false), TELEPORT_ERROR("§cVous avez déjà une téléportation en cours !",
					false), TELEPORT_SUCCESS("§7Téléportation effectué !", false),

	INVENTORY_NULL("§cImpossible de trouver l'inventaire avec l'id §6%s§c.", false), INVENTORY_CLONE_NULL(
			"§cLe clone de l'inventaire est null !",
			false), INVENTORY_OPEN_ERROR("§cUne erreur est survenu avec l'ouverture de l'inventaire §6%s§c.",
					false), INVENTORY_BUTTON_PREVIOUS("§f» §7Page précédente",
							false), INVENTORY_BUTTON_NEXT("§f» §7Page suivante", false),

	TIME_DAY("%02d day(s) %02d hour(s) %02d minute(s) %02d second(s)"),

	TIME_HOUR("%02d hour(s) %02d minute(s) %02d second(s)"),

	TIME_HOUR_SIMPLE("%02d:%02d:%02d"),

	TIME_MINUTE("%02d minute(s) %02d second(s)"),

	TIME_SECOND("%02d second(s)"),

	COMMAND_SYNTAXE_ERROR("§cYou must execute the command like this§7: §2%command%"),

	COMMAND_NO_PERMISSION("§cYou don't have permission !"),

	COMMAND_NO_CONSOLE("§cOnly player can execute this command"),

	COMMAND_NO_ARG("§cThis argument does not exist!"),

	COMMAND_SYNTAXE_HELP("§f%s §7» §b%s"),

	TOURNAMENT_NOT_ENABLE("§cNo tournaments in progress."),

	TOURNAMENT_ENABLE("§cNo tournaments in progress."),

	TOURNAMENT_ARENA_NOT_FOUND("§cCould not find the arena."),

	TOURNAMENT_ARENA_DELETE("§cYou have just deleted an arena."),

	TOURNAMENT_ARENA_CREATE("§2Vous venez de créer une arène."),

	TOURNAMENT_LOBBY_CREATE("§2You just put the rental for the lobby."),

	TOURNAMENT_ARENA_NO("§cAucune arène de disponible."),

	TOURNAMENT_START_ERROR_ARENA("§cYou cannot launch tournaments without any arena."),

	TOURNAMENT_START_ERROR_LOOBY("§cYou cannot launch tournaments without the lobby."),

	TOURNAMENT_START_ERROR_PLAYER(
			"§ePas assez de joueur pour commencer le tournois, vous avez encore §65 §eminutes pour créer une équipe."),

	TOURNAMENT_START_ERROR_PLAYER_CANCEL("§cNot enough player, event canceled!"),
	
	TOURNAMENT_START_ERROR_TEAM("§cNo team, the tournament is canceled!"),

	TOURNAMENT_START_TIMER("§eEncore §6%timer% §eavant le début du tournois PVP."),
	
	TOURNAMENT_START_FIRST_WAVE("§eDébut du tournois PVP !", "§eNombre d'équipe dans le tournois§7: §f%size%"),

	TOURNAMENT_START("§eDébut d'un tournois PVP en §6%type% §edans §65 §eminutes§e.",
			"§eFaire §f/tournois§e pour avoir toutes les commandess"),
	
	TOURNAMENT_WAVE_AUTO("§eYou are automatically qualified for the next round!"),
	
	TOURNAMENT_WAVE_START("§eNumber of duels§7: §6%duel%", "§eStart of the round §6%round% §e! May the best win !"),
	
	TOURNAMENT_WAVE_TIMER("§eEnd of the round §6%round% §ein §f%timer%§e."),
	
	TOURNAMENT_WAVE_END("§eTime is up, the next round will start..."),
	
	TOURNAMENT_WAVE_NEXT_TIME("§eStart of the next round in §f10 §eseconds."),
	
	TOURNAMENT_WAVE_NEXT("§eeNumber of remaining teams§7: §6%team%"),
	
	TOURNAMENT_STOP("§cThe PVP tournament has just been canceled"),
	
	TOURNAMENT_PLAYER_LOOSE("§f%player% §ehas just been eliminated!"),
	
	TOURNAMENT_DUEL_WIN("§eYou have just won your duel!"),
	
	TOURNAMENT_DUEL_LOOSE("§eYou lost the tournament, so your team is disqulified.", "§eYou are at position §f%position% §eof §6%team%§e."),
	
	TOURNAMENT_TEAM_LEAVE("§cYou just left your team."),
	
	TOURNAMENT_TEAM_LEAVE_ERROR("§cYou cannot leave your team during the fight."),
	
	TOURNAMENT_TEAM_PLAYER_ERROR("§cYou are not in a team."),

	TOURNAMENT_INVITE_ERROR("§cYou cannot invite a player at this time."),
	
	TOURNAMENT_INVITE_ERROR_TEAM("§cYou don't have a team, you can't do this."),
	
	TOURNAMENT_INVITE_ERROR_PLAYER("§cYou cannot invite yourself to your own team."),
	
	TOURNAMENT_INVITE_ERROR_OWNER("§cOnly the leader of your team can invite players."),
	
	TOURNAMENT_INVITE_ERROR_TYTE("§cYou cannot invite a player for a §f1v1§c tournament."),
	
	TOURNAMENT_INVITE_REMOVE("§f%player% §e withdrawing the invitation from §6%target% §eto join your team."),
	
	TOURNAMENT_INVITE_TEAM("§f%player% §ebecomes to invite §6%target% §eto join your team."),
	
	TOURNAMENT_INVITE_INFO("§eYou have just received an invitation from §6%player% §eto join the §f%team% §eteam."),
	
	TOURNAMENT_INVITE_INFO_JSON("§eMake §f/tournament join %team% §etournaments to join this team. §8(§bClick here§8)"),
	
	TOURNAMENT_INVITE_INFO_JSON_HOVER("§7Click to join the team §f%team%§7."),
	
	TOURNAMENT_JOIN_SUCCESS_INFO("§f%player% §ejust joined your team."),
	
	TOURNAMENT_JOIN_ERROR("§cYou cannot join this team."),
	
	TOURNAMENT_JOIN_ERROR_JOIN("§cYou cannot join a team at the moment."),
	
	TOURNAMENT_JOIN_ERROR_INVITE("§cYou are not invited to join this team."),
	
	TOURNAMENT_JOIN_ERROR_INVENOTRY("§cYou must have an empty inventory to participate in the tournaments §8(§7Your inventory will be deleted when you join a team§8)"),
	
	TOURNAMENT_JOIN_ERROR_TEAM("§cNo team with the name §f%team% §cexiste."),
	
	TOURNAMENT_JOIN_ERROR_TEAM_ALREADY("§cYou have already joined a team."),
		
	TOURNAMENT_CREATE_TEAM_BROADCAST("§f%player% §ecreated the team §6%team%§e."),
	
	TOURNAMENT_CREATE_ERROR_INVENOTRY("§cYou must have an empty inventory to participate in the tournaments §8(§7Your inventory will be deleted when you join a team§8)"),
	
	
	
	TOURNAMENT_CREATE_ERROR_NAME_MAX("§cThe name of your team must not exceed §f%max% §ccaractères."),
	
	TOURNAMENT_CREATE_ERROR_NAME_MIN("§cThe name of your team must have at least §f%mine% §character."),
	
	TOURNAMENT_CREATE_ERROR_NAME("§cThe name of your team must be alphanumeric, the character §f%char% §cis not allowed."),
	
	TOURNAMENT_CREATE_ERROR_EXIT("§cThe name §f%name% §cis already taken for a team, you must choose another one."),
	
	TOURNAMENT_CREATE_ERROR_PLAYER("§cYou already have a team, you cannot create another."),
	
	TOURNAMENT_CREATE_ERROR("§cYou cannot create a team at this time."),
	
	TOURNAMENT_WIN_ERROR("§cNobody has won the tournament event!"),
	
	TOURNAMENT_WIN("§eEvent tournois pvp terminé !", "", "§eTeam ranking"),
	
	TOURNAMENT_CLASSEMENT("§eThe team §6%team% §has finished §f%position% §eat tournaments."),
	
	TOURNAMENT_CLASSEMENT_HOVER("§ePlayers§7:", "%players%"),
	
	TOURNAMENT_CLASSEMENT_HOVER_PLAYER(" §f- §f%player%"),
	
	TOURNAMENT_HELP("§6» §e/tournois create §f<§bnom de team§f> §8- §7Créer une nouvelle team.",
			"§6» §e/tournois join §f<§bnom de team§f> §8- §7Rejoindre une team.",
			"§6» §e/tournois invite §f<§bplayer§f> §8- §7Inviter un joueur.",
			"§6» §e/tournois leave §8- §7Quitter votre team.",
			"§6» §e/tournois version §8- §7Voir la version du plugin."
			),
	
	TOURNAMENT_HELP_ADMIN(
			"§6» §e/tournois arena §f<§bloc1§f> §f<§bloc2§f> §8- §7Créer une arène.",
			"§6» §e/tournois delete §f<§buuid§f> §8- §7Supprimer une arène.",
			"§6» §e/tournois list §8- §7Voir la liste des arènes.",
			"§6» §e/tournois setlobby §8- §7Mettre la position du lobby.",
			"§6» §e/tournois stop §8- §7Permet de stop un tournois.",
			"§6» §e/tournois wave §8- §7Commencer la prochaine manche.",
			"§6» §e/tournois start §f<§btype3§f> §8- §7Lancer un tournois.",
			"§6» §e/tournois reload §8- §7Reload le plugin."
			),
	
	
	// title

	TITLE_START("§f§kII§e Tournois §f§kII", "§eUn tournois §f%type% §evient de commencer !", 10, 60, 10),
	
	TITLE_JOIN_SUCCESS("§f§kII§e Congratulations §f§kII", "§eYou have just joined the team §f%team% §e!", 10, 60, 10),
	
	TITLE_CREATE_SUCCESS("§f§kII§e Congratulations §f§kII", "§eYou have just created a §fteam §efor the PVP tournament !", 10, 60, 10),

	TITLE_START_INFO("§f§kII§e Tournament §f§kII", "§eEncore §6%timer% §eavant le début du tournois PVP.", 10, 60, 10),
	
	TITLE_WIN("§f§kII§e Tournament §f§kII", "§eCongratulations to the §f%team% §eteam who wins the tournament !", 10, 100, 30),

	TITLE_START_ERROR_PLAYER("§cPas assez de joueur pour commencer le tournois",
			"§cvous avez encore §f5 §cminutes pour créer une équipe.", 10, 60, 10),

	;

	private List<String> messages;
	private String message;
	private Map<String, Object> titles = new HashMap<>();
	private boolean use = true;

	/**
	 * 
	 * @param message
	 */
	private Message(String message) {
		this.message = message;
		this.use = true;
	}

	private Message(String title, String subTitle, int a, int b, int c) {
		this.use = true;
		this.titles.put("title", title);
		this.titles.put("subtitle", subTitle);
		this.titles.put("start", a);
		this.titles.put("time", b);
		this.titles.put("end", c);
		this.titles.put("isUse", true);
	}

	/**
	 * 
	 * @param message
	 */
	private Message(String... message) {
		this.messages = Arrays.asList(message);
		this.use = true;
	}

	/**
	 * 
	 * @param message
	 * @param use
	 */
	private Message(String message, boolean use) {
		this.message = message;
		this.use = use;
	}

	public String getMessage() {
		return message;
	}

	public String toMsg() {
		return message;
	}

	public String msg() {
		return message;
	}

	public boolean isUse() {
		return use;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessages() {
		return messages == null ? Arrays.asList(message) : messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public boolean isMessage() {
		return messages != null && messages.size() > 1;
	}

	public String getTitle() {
		return (String) titles.get("title");
	}

	public Map<String, Object> getTitles() {
		return titles;
	}

	public void setTitles(Map<String, Object> titles) {
		this.titles = titles;
	}

	public String getSubTitle() {
		return (String) titles.get("subtitle");
	}

	public boolean isTitle() {
		return titles.containsKey("title");
	}

	public double getStart() {
		return (double) titles.get("start");
	}

	public double getEnd() {
		return (double) titles.get("end");
	}

	public double getTime() {
		return (double) titles.get("time");
	}

	public boolean isUseTitle() {
		return (boolean) titles.getOrDefault("isUse", "true");
	}

	public String replace(String a, String b) {
		return message.replace(a, b);
	}

}
