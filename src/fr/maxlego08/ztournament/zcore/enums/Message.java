package fr.maxlego08.ztournament.zcore.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Message {

	PREFIX("�8(�6zTournament�8)"),

	PREFIX_END("�8(�6zTournament�8)"),

	TELEPORT_MOVE("�cVous ne devez pas bouger !", false), TELEPORT_MESSAGE("�7T�l�portatio dans �3%s �7secondes !",
			false), TELEPORT_ERROR("�cVous avez d�j� une t�l�portation en cours !",
					false), TELEPORT_SUCCESS("�7T�l�portation effectu� !", false),

	INVENTORY_NULL("�cImpossible de trouver l'inventaire avec l'id �6%s�c.", false), INVENTORY_CLONE_NULL(
			"�cLe clone de l'inventaire est null !",
			false), INVENTORY_OPEN_ERROR("�cUne erreur est survenu avec l'ouverture de l'inventaire �6%s�c.",
					false), INVENTORY_BUTTON_PREVIOUS("�f� �7Page pr�c�dente",
							false), INVENTORY_BUTTON_NEXT("�f� �7Page suivante", false),

	TIME_DAY("%02d day(s) %02d hour(s) %02d minute(s) %02d second(s)"),

	TIME_HOUR("%02d hour(s) %02d minute(s) %02d second(s)"),

	TIME_HOUR_SIMPLE("%02d:%02d:%02d"),

	TIME_MINUTE("%02d minute(s) %02d second(s)"),

	TIME_SECOND("%02d second(s)"),

	COMMAND_SYNTAXE_ERROR("�cYou must execute the command like this�7: �2%command%"),

	COMMAND_NO_PERMISSION("�cYou don't have permission !"),

	COMMAND_NO_CONSOLE("�cOnly player can execute this command"),

	COMMAND_NO_ARG("�cThis argument does not exist!"),

	COMMAND_SYNTAXE_HELP("�f%s �7� �b%s"),

	TOURNAMENT_NOT_ENABLE("�cNo tournaments in progress."),

	TOURNAMENT_ENABLE("�cNo tournaments in progress."),

	TOURNAMENT_ARENA_NOT_FOUND("�cCould not find the arena."),

	TOURNAMENT_ARENA_DELETE("�cYou have just deleted an arena."),

	TOURNAMENT_ARENA_CREATE("�2Vous venez de cr�er une ar�ne."),

	TOURNAMENT_LOBBY_CREATE("�2You just put the rental for the lobby."),

	TOURNAMENT_ARENA_NO("�cAucune ar�ne de disponible."),

	TOURNAMENT_START_ERROR_ARENA("�cYou cannot launch tournaments without any arena."),

	TOURNAMENT_START_ERROR_LOOBY("�cYou cannot launch tournaments without the lobby."),

	TOURNAMENT_START_ERROR_PLAYER(
			"�ePas assez de joueur pour commencer le tournois, vous avez encore �65 �eminutes pour cr�er une �quipe."),

	TOURNAMENT_START_ERROR_PLAYER_CANCEL("�cNot enough player, event canceled!"),
	
	TOURNAMENT_START_ERROR_TEAM("�cNo team, the tournament is canceled!"),

	TOURNAMENT_START_TIMER("�eEncore �6%timer% �eavant le d�but du tournois PVP."),
	
	TOURNAMENT_START_FIRST_WAVE("�eD�but du tournois PVP !", "�eNombre d'�quipe dans le tournois�7: �f%size%"),

	TOURNAMENT_START("�eD�but d'un tournois PVP en �6%type% �edans �65 �eminutes�e.",
			"�eFaire �f/tournois�e pour avoir toutes les commandess"),
	
	TOURNAMENT_WAVE_AUTO("�eYou are automatically qualified for the next round!"),
	
	TOURNAMENT_WAVE_START("�eNumber of duels�7: �6%duel%", "�eStart of the round �6%round% �e! May the best win !"),
	
	TOURNAMENT_WAVE_TIMER("�eEnd of the round �6%round% �ein �f%timer%�e."),
	
	TOURNAMENT_WAVE_END("�eTime is up, the next round will start..."),
	
	TOURNAMENT_WAVE_NEXT_TIME("�eStart of the next round in �f10 �eseconds."),
	
	TOURNAMENT_WAVE_NEXT("�eeNumber of remaining teams�7: �6%team%"),
	
	TOURNAMENT_STOP("�cThe PVP tournament has just been canceled"),
	
	TOURNAMENT_PLAYER_LOOSE("�f%player% �ehas just been eliminated!"),
	
	TOURNAMENT_DUEL_WIN("�eYou have just won your duel!"),
	
	TOURNAMENT_DUEL_LOOSE("�eYou lost the tournament, so your team is disqulified.", "�eYou are at position �f%position% �eof �6%team%�e."),
	
	TOURNAMENT_TEAM_LEAVE("�cYou just left your team."),
	
	TOURNAMENT_TEAM_LEAVE_ERROR("�cYou cannot leave your team during the fight."),
	
	TOURNAMENT_TEAM_PLAYER_ERROR("�cYou are not in a team."),

	// title

	TITLE_START("�f�kII�e Tournois �f�kII", "�eUn tournois �f%type% �evient de commencer !", 10, 60, 10),

	TITLE_START_INFO("�f�kII�e Tournois �f�kII", "�eEncore �6%timer% �eavant le d�but du tournois PVP.", 10, 60, 10),

	TITLE_START_ERROR_PLAYER("�cPas assez de joueur pour commencer le tournois",
			"�cvous avez encore �f5 �cminutes pour cr�er une �quipe.", 10, 60, 10),

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
