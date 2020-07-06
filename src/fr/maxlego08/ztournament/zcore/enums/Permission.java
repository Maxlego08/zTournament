package fr.maxlego08.ztournament.zcore.enums;

public enum Permission {
	ZTOURNAMENT_ARENA, ZTOURNAMENT_ARENA_SHOW, ZTOURNAMENT_DELETE, ZTOURNAMENT_START, ZTOURNAMENT_STOP, ZTOURNAMENT_WAVE

	;

	private String permission;

	private Permission() {
		this.permission = this.name().toLowerCase().replace("_", ".");
	}

	public String getPermission() {
		return permission;
	}

}
