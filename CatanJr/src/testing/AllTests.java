package testing;

// -----------------------------------------------------------------------------------------------------------
// This test can be used to call and run all of the JUnit tests setup at the same time.
// -----------------------------------------------------------------------------------------------------------

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestBoard.class,
	TestBoardSetup.class,
	TestCocoTiles.class,
	TestDice.class,
	TestIsland.class,
	TestMarketPlace.class,
    TestPlayer.class,
	TestPlayerTurn.class,
    TestStockpile.class,
})

public class AllTests {
}