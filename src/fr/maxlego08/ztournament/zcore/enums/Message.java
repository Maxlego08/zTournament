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

	TITLE_START("§f§kII§e Tournois §f§kII", "§eUn tournois §f%type% §evient de commencer !", 10, 60, 10),

	TITLE_START_INFO("§f§kII§e Tournois §f§kII", "§eEncore §6%timer% §eavant le début du tournois PVP.", 10, 60, 10),

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
		return messages;
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

}
