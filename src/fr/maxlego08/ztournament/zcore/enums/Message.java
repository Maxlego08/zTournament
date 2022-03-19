package fr.maxlego08.ztournament.zcore.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import fr.maxlego08.ztournament.zcore.utils.nms.NMSUtils;

public enum Message {

	PREFIX("§8(§6zTournament§8) "),
	PREFIX_END("§8(§6zTournament§8) "),

	TELEPORT_MOVE("§cVous ne devez pas bouger !", false), TELEPORT_MESSAGE("§7Téléportation dans §b%s §7secondes !",
			false), TELEPORT_ERROR("§cVous avez déjà une téléportation en cours !",
					false), TELEPORT_SUCCESS("§7Téléportation effectuée !", false),

	INVENTORY_NULL("§cImpossible de trouver l'inventaire avec l'id §6%s§c.", false), INVENTORY_CLONE_NULL(
			"§cLe clone de l'inventaire est null !",
			false), INVENTORY_OPEN_ERROR("§cUne erreur est survenue avec l'ouverture de l'inventaire §6%s§c.",
					false), INVENTORY_BUTTON_PREVIOUS("§f» §7Page précédente",
							false), INVENTORY_BUTTON_NEXT("§f» §7Page suivante", false),

	TIME_DAY("%02d day(s) %02d hour(s) %02d minute(s) %02d second(s)"),
	TIME_HOUR("%02d hour(s) %02d minute(s) %02d second(s)"),
	TIME_HOUR_SIMPLE("%02d:%02d:%02d"),
	TIME_MINUTE("%02d minute(s) %02d second(s)"),
	TIME_SECOND("%02d second(s)"),

	COMMAND_SYNTAXE_ERROR("§cYou must execute the command like this§7: §2%command%"),
	COMMAND_NO_PERMISSION("§cYou don't have the permission permission to use that!"),
	COMMAND_NO_CONSOLE("§cOnly players can execute this command"),
	COMMAND_NO_ARG("§cThis argument does not exist!"),
	COMMAND_SYNTAXE_HELP("§f%s §7» §b%s"),
	
	TOURNAMENT_NOT_ENABLE("§cNo tournaments in progress."),
	TOURNAMENT_ENABLE("§cNo tournaments in progress."),
	
	TOURNAMENT_ARENA_NOT_FOUND("§cCouldn't find the arena."),
	TOURNAMENT_ARENA_DELETE("§cYou just deleted an arena."),
	TOURNAMENT_ARENA_CREATE("§2You just created an arena."),
	
	TOURNAMENT_LOBBY_CREATE("§2Lobby's location has been set with success."),
	TOURNAMENT_ARENA_NO("§cNo arena available."),
	
	TOURNAMENT_START_ERROR_ARENA("§cYou have to create at least one arena in order to start."),
	TOURNAMENT_START_ERROR_LOOBY("§cYou have to set lobby's location in order to start.."),
	TOURNAMENT_START_ERROR_PLAYER(
			"§7Not enough players to start the tournament, you still have §b5 minutes §7to create a team."),
	TOURNAMENT_START_ERROR_PLAYER_CANCEL("§cNot enough players, event canceled!"),
	TOURNAMENT_START_ERROR_TEAM("§cNo teams has been found, canceling the tournament..."),
	TOURNAMENT_START_TIMER("§7Still §b%timer% §7before starting the PVP tournament."),
	TOURNAMENT_START_FIRST_WAVE("§7Starting the PVP tournament !", "§7Number of teams in the tournament§8: §f%size%"),
	TOURNAMENT_START("§fStarting a PVP tournament in §6%type% §7in §b5 minutes§7.",
			"§7Do §f/ztournament§7 to get informations."),
	
	TOURNAMENT_WAVE_AUTO("§7You are automatically qualified for the next round!"),
	TOURNAMENT_WAVE_START("§7Number of duels§8: §6%duel%", "§7Starting round number §6%round% §7! May the best win !"),
	TOURNAMENT_WAVE_TIMER("§7End of the round §6%round% §7in §b%timer%§7."),
	TOURNAMENT_WAVE_END("§7Time elapsed, the next round will start..."),
	TOURNAMENT_WAVE_NEXT_TIME("§7Starting the next round in §b10 seconds§7."),
	TOURNAMENT_WAVE_NEXT("§7Number of teams remaining§8: §f%team%"),
	TOURNAMENT_STOP("§cThe PVP tournament's just been cancelled."),
	TOURNAMENT_PLAYER_LOOSE("§f%player% §7has just been eliminated !"),
	TOURNAMENT_DUEL_WIN("§7You've just won your duel!"),
	TOURNAMENT_DUEL_LOOSE("§7You lost the tournament, your team's just been disqualified.",
			"§7You are at position §f%position% §7of §b%team%§7."),
	TOURNAMENT_TEAM_LEAVE("§cYou are leaving your team."),
	TOURNAMENT_TEAM_LEAVE_ERROR("§cYou cannot leave your team during the fight."),
	TOURNAMENT_TEAM_PLAYER_ERROR("§cYou are not in a team."),
	TOURNAMENT_INVITE_ERROR("§cYou cannot invite a player at this time."),
	TOURNAMENT_INVITE_ERROR_TEAM("§cYou don't have a team, you can't do this."),
	TOURNAMENT_INVITE_ERROR_PLAYER("§cYou cannot invite yourself to your own team."),
	TOURNAMENT_INVITE_ERROR_OWNER("§cOnly the leader of your team can invite players."),
	TOURNAMENT_INVITE_ERROR_TYTE("§cYou cannot invite a player for a §f1v1§c tournament."),
	TOURNAMENT_INVITE_REMOVE("§f%player% §7has canceled the invitation for §6%target% §7to join your team."),
	TOURNAMENT_INVITE_TEAM("§f%player% §asent an invitation to §6%target% §ato join your team."),
	TOURNAMENT_INVITE_INFO("§6%player% §ainvited you to join §f%team%§a."),
	TOURNAMENT_INVITE_INFO_JSON("§7Do §f/tournament join %team% §7tournaments to join this team. §8(§bClick here§8)"),
	TOURNAMENT_INVITE_INFO_JSON_HOVER("§7Click to join §f%team%§7."),
	TOURNAMENT_JOIN_SUCCESS_INFO("§f%player% §ajust joined your team."),
	TOURNAMENT_JOIN_ERROR("§cYou cannot join this team."),
	TOURNAMENT_JOIN_ERROR_JOIN("§cYou cannot join a team at the moment."),
	TOURNAMENT_JOIN_ERROR_INVITE("§cYou must be invited to join this team."),
	TOURNAMENT_JOIN_ERROR_INVENOTRY("§cYou must have an empty inventory to take part in tournaments §8(§7Your inventory will be deleted when you join a team§8)"),
	TOURNAMENT_JOIN_ERROR_TEAM("§cNo team with the name §f%team% §cexists."),
	TOURNAMENT_JOIN_ERROR_TEAM_ALREADY("§cYou are already in a team."),
	TOURNAMENT_CREATE_TEAM_BROADCAST("§f%player% §7created team §b%team%§7."),
	TOURNAMENT_CREATE_ERROR_INVENOTRY("§cYou must have an empty inventory to take part in tournaments §8(§7Your inventory will be deleted when you join a team§8)"),
	TOURNAMENT_CREATE_ERROR_NAME_MAX("§cThe name of your team must not exceed §f%max% §ccharacters."),
	TOURNAMENT_CREATE_ERROR_NAME_MIN("§cThe name of your team must have at least §f%min% §characters."),
	TOURNAMENT_CREATE_ERROR_NAME("§cThe name of your team must be alphanumeric, the character §f%char% §cis not allowed."),
	TOURNAMENT_CREATE_ERROR_EXIT("§cThe name §f%name% §cis already taken, please choose another one."),
	TOURNAMENT_CREATE_ERROR_PLAYER("§cYou already have a team, you cannot create another."),
	TOURNAMENT_CREATE_ERROR("§cYou cannot create a team at this time."),
	TOURNAMENT_KICK_ERROR("§cYou cannot kick a player at this time."),
	TOURNAMENT_KICK_ERROR_PLAYER("§cThe player has no team, you cannot expel him."),
	TOURNAMENT_KICK_ERROR_DUEL("§cThe player has no duel, you cannot expel him."),
	TOURNAMENT_KICK_SUCCESS("§aYou have just kick the player."),
	TOURNAMENT_WIN_ERROR("§cNobody has won the tournament event!"),
	TOURNAMENT_WIN("§ePvp tournament event over !", "", "§eTeam ranking"),
	TOURNAMENT_CLASSEMENT("§7The team §6%team% §7has finished §f%position% §7at tournaments."),
	TOURNAMENT_CLASSEMENT_HOVER("§7Players§7:", "%players%"),
	TOURNAMENT_CLASSEMENT_HOVER_PLAYER(" §f- §b%player%"),
	TOURNAMENT_HELP_HEADER("§8[§b?§8] §7Commands lists:"),
	TOURNAMENT_HELP("§6» §f/tournament create §8<§ateam name§8> §8- §7Create a team",
			"§6» §f/tournament join §f<§anom de team§f> §8- §7Join a team.",
			"§6» §f/tournament invite §8<§aplayer§8> §8- §7Invite a player to your team.",
			"§6» §f/tournament leave §8- §7Leave your team.",
			"§6» §f/tournament version §8- §7Show plugin's version."),
	TOURNAMENT_HELP_ADMIN("§6» §f/tournament arena §§8<§aname§8> §8<§aloc1§8> §8<§aloc2§8> §8- §7Create an arena.",
			"§6» §f/tournament delete §8<§auuid§8> §8- §7Allows you to delete an arena. Makes /ztournament list to retrieve the arena list (json message).",
			"§6» §f/tournament list §8- §7Show an arena.",
			"§6» §f/tournament setlobby §8- §7Set lobby's position.",
			"§6» §f/tournament stop §8- §7Shop a tournament.",
			"§6» §f/tournament wave §8- §7Start next wave.",
			"§6» §f/tournament start §f<§atype§f> §f<§bkit name§f>§8- §7Start tournament.",
			"§6» §f/tournament kit §8- §7Show kit commands.",
			"§6» §f/tournament axe §8- §7Get selection axe.",
			"§6» §f/tournament reload §8- §7Reload the plugin."),
	TOURNAMENT_HELP_ADMIN_KIT(
			"§6» §f/tournament kit list§8- §7Show kit list.",
			"§6» §f/tournament kit show §8<§aname§8>§8- §7Show kit content.",
			"§6» §f/tournament kit create §8<§aname§8>§8- §7Create an empty kit.",
			"§6» §f/tournament kit edit §8<§aname§8>§8- §7Edit a kit.",
			"§6» §f/tournament kit delete §8<§aname§8>§8- §7Edit a kit."
			),

	TOURNAMENT_KIT_ALREADY_EXIST("§cKit §f%name% §calready exists"),
	TOURNAMENT_KIT_NOT_EXIST("§cKit §f%name% §cdoesn't exists"),
	TOURNAMENT_KIT_CREATE("§aYou created kit §6%name%§a."),
	TOURNAMENT_KIT_DELETE("§aYou deleted kit §6%name%§a."),
	TOURNAMENT_KIT_EDIT("§aYou edited the kit §f%s§a."),
	TEAM_DAMAGE("§cYou cannot do damage to a member of your team."),
	
	// title

	TITLE_START("§a§kII§7 Tournament §a§kII", "§eA §f%type% §7tournament just start !", 10, 60, 10),

	TITLE_JOIN_SUCCESS("§a§kII§7 Congratulations §a§kII§", "§7You joined the team §f%team% §7!", 10, 60, 10),

	TITLE_CREATE_SUCCESS("§a§kII§7 Congratulations §a§kII§",
			"§7You created a §fteam §7for the PVP tournament !", 10, 60, 10),

	TITLE_START_INFO("§a§kII§7 Tournament §a§kII§", "§7Still §b%timer% §7before starting the tournament.", 0, 60, 10),

	TITLE_WIN("§a§kII§7 Tournament §a§kII§", "§7Congratulations to the §f%team% §7team for winning the tournament !", 10,
			100, 30),

	TITLE_START_ERROR_PLAYER("§cNot enough players to start the tournament",
			"§cyou still have §b5 minutes §cto create a team.", 10, 60, 10), 
	
	TOURNAMENT_COMMAND("§cYou cannot place orders during a tournament."), 
	TOURNAMENT_LEAVE_ARENA("§cYou cannot leave the arena."), 
	DROP_ITEM("§cYou may not throw items on the ground."), 
	
	TOURNAMENT_AXE_POS1("§7You have just put the first position in §f%world%§7, §f%x%§7, §f%y%§7, §f%z%§7."),
	TOURNAMENT_AXE_POS2("§7You have just put the second position in §f%world%§7, §f%x%§7, §f%y%§7, §f%z%§7."), 
	TOURNAMENT_CREATE_ERROR_SELECTION("§cYou must select a zone with the command §f/ztournament axe§c."), 
	TOURNAMENT_ARENA_POS1_CHANGE("§aYou have just changed the first position of the arena."),
	TOURNAMENT_ARENA_POS2_CHANGE("§aYou have just changed the second position of the arena."), 
	TOURNAMENT_ARENA_ALREADY_EXIST("§cImpossible to create an arena with this name, it already exists."),
	

	;

	private List<String> messages;
	private String message;
	private Map<String, Object> titles = new HashMap<>();
	private boolean use = true;
	private MessageType type = MessageType.TCHAT;

	private ItemStack itemStack;
	
	/**
	 * 
	 * @param message
	 */
	private Message(String message) {
		this.message = message;
		this.use = true;
	}

	/**
	 * 
	 * @param title
	 * @param subTitle
	 * @param a
	 * @param b
	 * @param c
	 */
	private Message(String title, String subTitle, int a, int b, int c) {
		this.use = true;
		this.titles.put("title", title);
		this.titles.put("subtitle", subTitle);
		this.titles.put("start", a);
		this.titles.put("time", b);
		this.titles.put("end", c);
		this.titles.put("isUse", true);
		this.type = MessageType.TITLE;
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
	 */
	private Message(MessageType type, String... message) {
		this.messages = Arrays.asList(message);
		this.use = true;
		this.type = type;
	}
	
	/**
	 * 
	 * @param message
	 */
	private Message(MessageType type, String message) {
		this.message = message;
		this.use = true;
		this.type = type;
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
		this.type = MessageType.TITLE;
	}

	public String getSubTitle() {
		return (String) titles.get("subtitle");
	}

	public boolean isTitle() {
		return titles.containsKey("title");
	}

	public int getStart() {
		return ((Number) titles.get("start")).intValue();
	}

	public int getEnd() {
		return ((Number) titles.get("end")).intValue();
	}

	public int getTime() {
		return ((Number) titles.get("time")).intValue();
	}

	public boolean isUseTitle() {
		return (boolean) titles.getOrDefault("isUse", "true");
	}

	public String replace(String a, String b) {
		return message.replace(a, b);
	}

	public MessageType getType() {
		return type.equals(MessageType.ACTION) && NMSUtils.isVeryOldVersion() ? MessageType.TCHAT : type;
	}
	
	public ItemStack getItemStack() {
		return itemStack;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

}
