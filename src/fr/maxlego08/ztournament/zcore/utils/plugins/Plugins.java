package fr.maxlego08.ztournament.zcore.utils.plugins;

public enum Plugins {

	VAULT("Vault"),
	ESSENTIALS("Essentials"), 
	PLACEHOLDER("PlaceholderAPI"),
	PROTOCOLLIB("ProtocolLib"),
	
	;

	private final String name;

	private Plugins(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
