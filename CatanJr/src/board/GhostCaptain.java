package board;

// -----------------------------------------------------------------------------------------------------------
// This is a singleton class for the Ghost Captain. It is used to keep track of/move the location of the GC.
// -----------------------------------------------------------------------------------------------------------

public class GhostCaptain {
	
	protected int location; 										// Location of Ghost captain
	private static GhostCaptain instance = new GhostCaptain(13); 	// Create instance of ghost captain
	
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
