package fr.maxlego08.ztournament.zcore.enums;

public enum Inventory {

	INVENTORY_TEST(1),
	INVENTORY_KIT_CREATE(2),
	INVENTORY_KIT_SHOW(3),
	
	;
	
	private final int id;

	private Inventory(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
