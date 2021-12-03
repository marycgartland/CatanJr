// Set up as just a temporary file that we can use to run small tests as we work
// Delete later :)
package main;

import java.util.Scanner;
import java.util.*;
import gameplay.*;
import board.*;

import player.Player;
import resources.Resources;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class TempTest {
	
	public static void main(String[] args) {
		GameManager gameManager = new GameManager();
		gameManager.startGame();
		Board testboard = new Board();
		Dice dice = new Dice();
		//dice.rollDice();
		//testboard.checkArray();
		//(Dice dice, Island[] islands, Player[] players)
		//testboard.checkDiceRoll(dice, islands, players);
		//testboard.checkDiceRoll(dice); // uncomment this mary is working on it, the arguments are wrong
		testboard.setUpIslands();

    }

}
