package fr.maxlego08.ztournament.zcore.enums;

public enum Permission {
	ZTOURNAMENT_ARENA,
	ZTOURNAMENT_ARENA_SHOW, 
	ZTOURNAMENT_DELETE,
	
	ZTOURNAMENT_START, 
	ZTOURNAMENT_STOP,
	ZTOURNAMENT_WAVE, 
	ZTOURNAMENT_RELOAD,
	ZTOURNAMENT_HELP, 
	ZTOURNAMENT_KIT_USE, 
	
	
	
	ZTOURNAMENT_TEAM_CREATE, 
	ZTOURNAMENT_TEAM_JOIN,
	ZTOURNAMENT_TEAM_LEAVE,
	ZTOURNAMENT_TEAM_INVITE, 
	
	ZTOURNAMENT_SETLOBBY, 
	
	ZTOURNAMENT_BYPASS_COMMAND,

	;

	private String permission;

	private Permission() {
		this.permission = this.name().toLowerCase().replace("_", ".");
	}

	public String getPermission() {
		return permission;
	}

}
