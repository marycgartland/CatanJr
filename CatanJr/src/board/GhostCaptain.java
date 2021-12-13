package board;

//-----------------------------------------------------
// ------ A Singleton class for the Ghost Captain -----
// -----------------------------------------------------

public class GhostCaptain {
	
	protected int location; // location of Ghost captain
	private static GhostCaptain instance = new GhostCaptain(13); // create instance of ghost captain
	
	// -----------------------------------------------------
	// ---------- Constructor ------------------------------
	// Ghost captain will be placed on spooky island to start
	// -----------------------------------------------------
	private GhostCaptain(int island_number) {
		this.location = island_number;
	}
	
	// -----------------------------------------------------
	// ---------- Method: updateLocationGC -----------------
	// Update the ghost captains location
	// -----------------------------------------------------
	public void updateLocationGC(int island_update) {
		this.location = island_update;
	}

	// -----------------------------------------------------
	// ---------- Method: getGhostCaptainLocation ----------
	// -----------------------------------------------------
	public int getGhostCaptainLocation() {
		return this.location;
	}
	
	// ----------------------------------------------------
	// ---------- Method: getInstance ---------------------
	// ----------------------------------------------------
	public static GhostCaptain getInstance() {
		return instance;
	}
}
