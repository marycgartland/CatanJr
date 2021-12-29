package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import gameplay.Dice;

public class TestDice {

	private Dice testDice;
	
	@Before
	public void setUp() throws Exception{
		testDice = Dice.getInstance();
	}
	
	@After
	public void tearDown() {
		testDice = null;
		System.out.println("Teardown");
	}
	
	// ---------- Test that a dice roll is between 1 and 6 (only testing one case) ------------------
	@Test
	public void testGetDiceRollValueRange() {
		int roll = testDice.rollDice();
		boolean actual = (roll>=1 && roll<=6 ? true : false);
		assertEquals(true, actual, "Dice roll - correct range");
	}

	// ---------- Test that the getDiceRollValue() method returns the correct rolled value ----------
	@Test
	public void testGetDiceRollValue() {
		int expected = testDice.rollDice();
		int actual = testDice.getDiceRollValue();
		assertEquals(expected, actual, "Get dice roll - correct value returned");
	} 
}
