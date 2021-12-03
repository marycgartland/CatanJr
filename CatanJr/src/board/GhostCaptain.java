package board;

// Note: Don't even know if the Ghost Captain should be under board still or not
public class GhostCaptain {
	protected int location; // location of Ghost captain
	// Create the ghost captain
	// Keep track of where the ghost captain is, and the islands it effects
	
	
// Constructor for ghost captain
	// ghost captain should be placed on spooky island when the game is first setup
public GhostCaptain(int island_number) {
	this.location = island_number;
	
}	

// update Ghost Captains location
public void updateLocationGC(int island_update) {
	this.location = island_update;
}


public int getGhostCaptainLocation() {
	return this.location;
}
	
}
