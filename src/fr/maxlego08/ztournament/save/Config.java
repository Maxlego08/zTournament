package fr.maxlego08.ztournament.save;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.maxlego08.ztournament.Reward;
import fr.maxlego08.ztournament.zcore.utils.storage.Persist;
import fr.maxlego08.ztournament.zcore.utils.storage.Saveable;

public class Config implements Saveable {

	public static boolean sendMessageInAction = true;
	public static boolean restartTeamSearch = true;
	public static boolean showRanking = true;
	public static boolean enableDropItems = false;
	public static boolean enableUpdateNotification = true;
	public static boolean disablePotionAndPearl = false;
	public static boolean enableProtocollibEntityHider = false;
	public static boolean enableDebug = false;
	public static boolean enableAutoUpdate = false;
	public static boolean enableEliminationOfThePlayerWhoMadeTheFewestDamage = false;
	public static boolean enableTeleportDelay = false;
	public static int teleportDelayInSeconds = 10;

	public static List<String> commandAliases = Arrays.asList("tournois", "tournament");
	
	public static int rankingSize = 3;
	public static Map<Integer, String> rankingPosition = new HashMap<Integer, String>();

	public static int teamNameMinName = 3;
	public static int teamNameMaxName = 14;
	public static int timeStartTournamentInSecond = 300;
	public static int timeWaveEndInSecond = 300;
	public static int timeWaveNextInSecond = 10;
	public static List<Integer> displayTournamentInformations = Arrays.asList(1, 2, 3, 4, 5, 10, 15, 30, 60, 120, 180,
			4 * 60, 300);
	public static List<Integer> displayWaveEndInformations = Arrays.asList(1, 2, 3, 4, 5, 10, 15, 30, 60, 120, 180,
			4 * 60, 300);

	public static List<Reward> rewards = new ArrayList<Reward>();
	public static boolean randomLooseTeam = false;

	public static boolean giveEffectPotionsToPlayerAfter = true;

	/**
	 * static Singleton instance.
	 */
	private static volatile Config instance;
	public static String noTeamPlaceholder = "no team";

	static {

		rankingPosition.put(1, "�4first");
		rankingPosition.put(2, "�csecond");
		rankingPosition.put(3, "�6third");
		rankingPosition.put(4, "�dfourth");
		rankingPosition.put(5, "�bfive");

		rewards.add(new Reward(1, 1, Arrays.asList("bc %team% is strong")));
		rewards.add(new Reward(2, 2, Arrays.asList("bc %player% is not really strong")));
		rewards.add(new Reward(3, 5, Arrays.asList("bc %team% is bad")));
		rewards.add(new Reward(6, 10, Arrays.asList("bc %team% is poop")));

	}

	/**
	 * Private constructor for singleton.
	 */
	private Config() {
	}

	/**
	 * Return a singleton instance of Config.
	 */
	public static Config getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (Config.class) {
				if (instance == null) {
					instance = new Config();
				}
			}
		}
		return instance;
	}

	public void save(Persist persist) {
		persist.save(getInstance());
	}

	public void load(Persist persist) {
		persist.loadOrSaveDefault(getInstance(), Config.class);

		if (teamNameMinName < 0)
			teamNameMinName = 1;
		if (teamNameMaxName > 64)
			teamNameMaxName = 64;

	}

}
