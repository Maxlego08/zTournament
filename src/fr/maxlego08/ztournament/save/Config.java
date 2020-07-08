package fr.maxlego08.ztournament.save;

import fr.maxlego08.ztournament.zcore.utils.storage.Persist;
import fr.maxlego08.ztournament.zcore.utils.storage.Saveable;

public class Config implements Saveable {

	public static boolean sendMessageInTitle = true;
	public static boolean sendMessageInAction = true;
	public static int teamNameMinName = 3;
	public static int teamNameMaxName = 14;
	
	/**
	 * static Singleton instance.
	 */
	private static volatile Config instance;

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
