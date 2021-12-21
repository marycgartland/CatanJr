package board;

public class GhostCaptain {
	// -----------------------------------------------------
	// ---------- Variables --------------------------------
	// -----------------------------------------------------
	protected int location; // location of Ghost captain
	
	// -----------------------------------------------------
	// ---------- Constructor ------------------------------
	// Ghost captain will be placed on spooky island to start
	// -----------------------------------------------------
	public GhostCaptain(int island_number) {
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
}
