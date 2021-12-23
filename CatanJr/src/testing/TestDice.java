package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import gameplay.Dice;
import player.Player;
import setup.BoardSetup;

class TestDice {
	// Test that a dice roll is between 1 and 6 (only testing one case)
	@Test
	void testDiceRollRange() {
		Dice dice = Dice.getInstance();
		int roll = dice.rollDice();
		boolean actual = (roll>=1 && roll<=6 ? true : false);
		assertEquals(true, actual, "Dice roll - correct range");
	}
	
	// Test that the getDiceRollValue() method returns the correct rolled value
	@Test
	void testGetDiceRollValue() {
		Dice dice = Dice.getInstance();
		int expected = dice.rollDice();
		int actual = dice.getDiceRollValue();
		assertEquals(expected, actual, "Get dice roll - correct value returned");
	}
}
