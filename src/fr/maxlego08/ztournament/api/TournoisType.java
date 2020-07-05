package fr.maxlego08.ztournament.api;

public enum TournoisType {

	V1(1),
	V2(2),
	V3(3),
	V4(4),
	V5(5),
	V6(6),
	V7(7),
	V8(8),
	V9(9),
	V10(10),
	
	;
	
	private final int max;

	private TournoisType(int max) {
		this.max = max;
	}
	
	public int getMax() {
		return max;
	}
	
}
