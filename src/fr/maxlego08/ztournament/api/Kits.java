package fr.maxlego08.ztournament.api;

import fr.maxlego08.ztournament.zcore.utils.storage.Saveable;

public interface Kits extends Saveable{

	/**
	 * 
	 * @param name
	 * @return
	 */
	Kit getKit(String name);
	
}
