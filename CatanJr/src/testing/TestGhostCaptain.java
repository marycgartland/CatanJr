package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import board.GhostCaptain;

class TestGhostCaptain {

	@Test
	// --------------- Testing Changing Ghost Captain Location ---------------
	void testGhostCaptain() {
		GhostCaptain testGC = GhostCaptain.getInstance();								// Intialize
		assertEquals(10, testGC.getGhostCaptainLocation(), "Testing Ghost Captain");	// Test
		testGC.updateLocationGC(8);														// Change
		assertEquals(8, testGC.getGhostCaptainLocation(), "Testing Ghost Captain");		// Test
	}

}