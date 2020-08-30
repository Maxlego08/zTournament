package fr.maxlego08.ztournament.zcore.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Message {

	PREFIX("�8(�6zTournament�8)"),

	PREFIX_END("�8(�6zTournament�8)"),

	TELEPORT_MOVE("�cVous ne devez pas bouger !", false), TELEPORT_MESSAGE("�7T�l�portation dans �3%s �7secondes !",
			false), TELEPORT_ERROR("�cVous avez d�j� une t�l�portation en cours !",
					false), TELEPORT_SUCCESS("�7T�l�portation effectu�e !", false),

	INVENTORY_NULL("�cImpossible de trouver l'inventaire avec l'id �6%s�c.", false), INVENTORY_CLONE_NULL(
			"�cLe clone de l'inventaire est null !",
			false), INVENTORY_OPEN_ERROR("�cUne erreur est survenue avec l'ouverture de l'inventaire �6%s�c.",
					false), INVENTORY_BUTTON_PREVIOUS("�f� �7Page pr�c�dente",
							false), INVENTORY_BUTTON_NEXT("�f� �7Page suivante", false),

	TIME_DAY("%02d day(s) %02d hour(s) %02d minute(s) %02d second(s)"),

	TIME_HOUR("%02d hour(s) %02d minute(s) %02d second(s)"),

	TIME_HOUR_SIMPLE("%02d:%02d:%02d"),

	TIME_MINUTE("%02d minute(s) %02d second(s)"),

	TIME_SECOND("%02d second(s)"),

	COMMAND_SYNTAXE_ERROR("�cYou must execute the command like this�7: �2%command%"),

	COMMAND_NO_PERMISSION("�cYou don't have the permission permission to use that!"),

	COMMAND_NO_CONSOLE("�cOnly players can execute this command"),

	COMMAND_NO_ARG("�cThis argument does not exist!"),

	COMMAND_SYNTAXE_HELP("�f%s �7� �b%s"),

	TOURNAMENT_NOT_ENABLE("�cNo tournaments in progress."),

	TOURNAMENT_ENABLE("�cNo tournaments in progress."),

	TOURNAMENT_ARENA_NOT_FOUND("�cCouldn't find the arena."),

	TOURNAMENT_ARENA_DELETE("�cYou just deleted an arena."),

	TOURNAMENT_ARENA_CREATE("�2You just created an arena."),

	TOURNAMENT_LOBBY_CREATE("�2Lobby's location has been set with success."),

	TOURNAMENT_ARENA_NO("�cNo arena available."),

	TOURNAMENT_START_ERROR_ARENA("�cYou have to create at least one arena in order to start."),

	TOURNAMENT_START_ERROR_LOOBY("�cYou have to set lobby's location in order to start.."),

	TOURNAMENT_START_ERROR_PLAYER(
			"�eNot enough players to start the tournament, you still have �65 �eminutes to create a team."),

	TOURNAMENT_START_ERROR_PLAYER_CANCEL("�cNot enough players, event canceled!"),

	TOURNAMENT_START_ERROR_TEAM("�cNo teams has been found, canceling the tournament..."),

	TOURNAMENT_START_TIMER("�eStill �6%timer% �ebefore starting the PVP tournament."),

	TOURNAMENT_START_FIRST_WAVE("�eStarting the PVP tournament !", "�eNumber of teams in the tournament�7: �f%size%"),

	TOURNAMENT_START("�Starting a PVP tournament in �6%type% �ein �65 �eminutes�e.",
			"�eDo �f/tournois�e to have all commands"),

	TOURNAMENT_WAVE_AUTO("�eYou are automatically qualified for the next round!"),

	TOURNAMENT_WAVE_START("�eNumber of duels�7: �6%duel%", "�eStarting round number �6%round% �e! May the best win !"),

	TOURNAMENT_WAVE_TIMER("�eEnd of the round �6%round% �ein �f%timer%�e."),

	TOURNAMENT_WAVE_END("�eTime elapsed, the next round will start..."),

	TOURNAMENT_WAVE_NEXT_TIME("�eStarting the next round in �f10 �eseconds."),

	TOURNAMENT_WAVE_NEXT("�eNumber of teams remaining�7: �6%team%"),

	TOURNAMENT_STOP("�cThe PVP tournament's just been cancelled."),

	TOURNAMENT_PLAYER_LOOSE("�f%player% �ehas just been eliminated!"),

	TOURNAMENT_DUEL_WIN("�eYou've just won your duel!"),

	TOURNAMENT_DUEL_LOOSE("�eYou lost the tournament, your team's just been disqualified.",
			"�eYou are at position �f%position% �eof �6%team%�e."),

	TOURNAMENT_TEAM_LEAVE("�cYou are leaving your team."),

	TOURNAMENT_TEAM_LEAVE_ERROR("�cYou cannot leave your team during the fight."),

	TOURNAMENT_TEAM_PLAYER_ERROR("�cYou are not in a team."),

	TOURNAMENT_INVITE_ERROR("�cYou cannot invite a player at this time."),

	TOURNAMENT_INVITE_ERROR_TEAM("�cYou don't have a team, you can't do this."),

	TOURNAMENT_INVITE_ERROR_PLAYER("�cYou cannot invite yourself to your own team."),

	TOURNAMENT_INVITE_ERROR_OWNER("�cOnly the leader of your team can invite players."),

	TOURNAMENT_INVITE_ERROR_TYTE("�cYou cannot invite a player for a �f1v1�c tournament."),

	TOURNAMENT_INVITE_REMOVE("�f%player% �ehas canceled the invitation from �6%target% �eto join your team."),

	TOURNAMENT_INVITE_TEAM("�f%player% �esent an invitation to �6%target% �eto join your team."),

	TOURNAMENT_INVITE_INFO("�e�6%player% �etinvited you to join �f%team%"),

	TOURNAMENT_INVITE_INFO_JSON("�eDo �f/tournament join %team% �etournaments to join this team. �8(�bClick here�8)"),

	TOURNAMENT_INVITE_INFO_JSON_HOVER("�7Click to join �f%team%�7."),

	TOURNAMENT_JOIN_SUCCESS_INFO("�f%player% �ejust joined your team."),

	TOURNAMENT_JOIN_ERROR("�cYou cannot join this team."),

	TOURNAMENT_JOIN_ERROR_JOIN("�cYou cannot join a team at the moment."),

	TOURNAMENT_JOIN_ERROR_INVITE("�cYou must be invited to join this team."),

	TOURNAMENT_JOIN_ERROR_INVENOTRY(
			"�cYou must have an empty inventory to take part in tournaments �8(�7Your inventory will be deleted when you join a team�8)"),

	TOURNAMENT_JOIN_ERROR_TEAM("�cNo team with the name �f%team% �cexists."),

	TOURNAMENT_JOIN_ERROR_TEAM_ALREADY("�cYou are already in a team."),

	TOURNAMENT_CREATE_TEAM_BROADCAST("�f%player% �ecreated team �6%team%�e."),

	TOURNAMENT_CREATE_ERROR_INVENOTRY(
			"�cYou must have an empty inventory to take part in tournaments �8(�7Your inventory will be deleted when you join a team�8)"),

	TOURNAMENT_CREATE_ERROR_NAME_MAX("�cThe name of your team must not exceed �f%max% �ccharacters."),

	TOURNAMENT_CREATE_ERROR_NAME_MIN("�cThe name of your team must have at least �f%min% �characters."),

	TOURNAMENT_CREATE_ERROR_NAME(
			"�cThe name of your team must be alphanumeric, the character �f%char% �cis not allowed."),

	TOURNAMENT_CREATE_ERROR_EXIT("�cThe name �f%name% �cis already taken, please choose another one."),

	TOURNAMENT_CREATE_ERROR_PLAYER("�cYou already have a team, you cannot create another."),

	TOURNAMENT_CREATE_ERROR("�cYou cannot create a team at this time."),

	TOURNAMENT_WIN_ERROR("�cNobody has won the tournament event!"),

	TOURNAMENT_WIN("�ePvp tournament event over !", "", "�eTeam ranking"),

	TOURNAMENT_CLASSEMENT("�eThe team �6%team% �ehas finished �f%position% �eat tournaments."),

	TOURNAMENT_CLASSEMENT_HOVER("�ePlayers�7:", "%players%"),

	TOURNAMENT_CLASSEMENT_HOVER_PLAYER(" �f- �f%player%"),

	TOURNAMENT_HELP_HEADER("�8[�b?�8] �7Commands lists:"),
	
	TOURNAMENT_HELP("�6� �e/tournament create �f<�bnom de team�f> �8- �7Create a team",
			"�6� �e/tournament join �f<�bnom de team�f> �8- �7Join a team.",
			"�6� �e/tournament invite �f<�bplayer�f> �8- �7Invite a player to your team.",
			"�6� �e/tournament leave �8- �7Leave your team.",
			"�6� �e/tournament version �8- �7Show plugin's version."),

	TOURNAMENT_HELP_ADMIN("�6� �e/tournament arena �f<�bloc�f> �8- �7Create an arena.",
			"�6� �e/tournament delete �f<�buuid�f> �8- �7Allows you to delete an arena. Makes /ztournament list to retrieve the arena list (json message).",
			"�6� �e/tournament list �8- �7Show an arena.",
			"�6� �e/tournament setlobby �8- �7Set lobby's position.",
			"�6� �e/tournament stop �8- �7Shop a tournament.",
			"�6� �e/tournament wave �8- �7Start next wave.",
			"�6� �e/tournament start �f<�btype�f> �f<�bkit name�f>�8- �7Start tournament.",
			"�6� �e/tournament kit �8- �7Show kit commands.",
			"�6� �e/tournament reload �8- �7Reload the plugin."),
	
	TOURNAMENT_HELP_ADMIN_KIT(
			"�6� �e/tournament kit list�8- �7Show kit list.",
			"�6� �e/tournament kit show <name>�8- �7Show kit content.",
			"�6� �e/tournament kit create <name>�8- �7Create an empty kit.",
			"�6� �e/tournament kit edit <name>�8- �7Edit a kit.",
			"�6� �e/tournament kit delete <name>�8- �7Edit a kit."
			),

	TOURNAMENT_KIT_ALREADY_EXIST("�cKit �f%name% �calready exists"),
	TOURNAMENT_KIT_NOT_EXIST("�cKit �f%name% �cdoesn't exists"),
	TOURNAMENT_KIT_CREATE("�eYou created kit �6%name%�e."),
	TOURNAMENT_KIT_DELETE("�eYou deleted kit �6%name%�e."),
	TOURNAMENT_KIT_EDIT("�eYou edited the kit �f%s�e."),
	TEAM_DAMAGE("�cYou cannot do damage to a member of your team."),
	
	// title

	TITLE_START("�f�kII�e Tournois �f�kII", "�eA �f%type% �etournament just start !", 10, 60, 10),

	TITLE_JOIN_SUCCESS("�f�kII�e Congratulations �f�kII", "�eYou joined the team �f%team% �e!", 10, 60, 10),

	TITLE_CREATE_SUCCESS("�f�kII�e Congratulations �f�kII",
			"�eYou created a �fteam �efor the PVP tournament !", 10, 60, 10),

	TITLE_START_INFO("�f�kII�e Tournament �f�kII", "�eStill �6%timer% �ebefore starting PVP tournament.", 0, 60, 10),

	TITLE_WIN("�f�kII�e Tournament �f�kII", "�eCongratulations to the �f%team% �eteam that wins the tournament !", 10,
			100, 30),

	TITLE_START_ERROR_PLAYER("�cNot enough players to start the tournament",
			"�cyou still have �f5 �cminutes to create a team.", 10, 60, 10), 
	
	TOURNAMENT_COMMAND("�cYou cannot place orders during a tournament."), 
	TOURNAMENT_LEAVE_ARENA("�eYou cannot leave the arena."), 
	DROP_ITEM("�cYou may not throw items on the ground."), 
	

	;

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

}
