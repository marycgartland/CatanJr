package main;

import java.util.Scanner;

import player.Player;
import resources.Resources;
import resources.CocoTileTypes;
import gameplay.GameManager;

// --------------------------------------------------------------------------------------- //
// This class is called to interact with user, taking in values, outputting to console etc //
//---------------------------------------------------------------------------------------- //

public class Interactor {
	// ---------------------------------------------------------------------------------
	// ---------- Constructor ----------------------------------------------------------
	// ---------------------------------------------------------------------------------
	public Interactor() {
	}

	
	// ---------------------------------------------------------------------------------
	// --------- Methods: printMessage -------------------------------------------------
	// ---------------------------------------------------------------------------------
	
	// ----- Prints message with 1 input string value included -------------------------
	public void printMessage(String instruction, String value) {
		switch (instruction) {
		// Cases from 'Board' class
		case "invalid island":
			System.out.println("This is not a valid island number, please choose again\n");
			break;
		
		// Cases from 'GameManager' class
		case "winner":
			System.out.println("Congratulations " + value + "! You're the Winner!\n");
			break;
		
		// Cases from 'Marketplace' class
		case "View Marketplace":
			System.out.println("Marketplace Contents: " + value + "\n");
			break;
			
		// Cases from 'PlayerSetup' class
		case "player info":
			System.out.println(value + "\n");
			break;
			
		// Cases from 'PlayerTurn' class 
		case "trade in resource":
			System.out.println("You have chosen to trade in " + value + "\n");
			break;
		case "trade out resource":
			System.out.println("You have chosen to obtain " + value + "\n");
			break;
		}
	}

	
	// ----- Prints message with 2 input values (1 Resource value, 1 String value) -----
	public void printMessage(String instruction, Resources value1, String value2) {
		switch (instruction) {
		// Cases from 'Island' class
		case "Island check: success":
			System.out.println("Add a " + value1 + " for player " + value2);
			break;
		}
	}

	
	// ----- Prints message based on instruction given ---------------------------------
	public void printMessage(String instruction) {
		switch (instruction) {
		// Cases from 'Board' class - as they appear
		case "move ghost captain":
			System.out.println("Which island would you like to move the ghost captain to?:\n");
			break;
		case "GC moved":
			System.out.println("Ghost Captain has been moved\n");
			break;
		case "Number layout":
			System.out.println("Island Number Layout:");
			break;
		case "build ship option":
			System.out.println("Which number option would you like to build your ship at?: ");
			break;
		case "ship built":
			System.out.println("Ship has been successfully built\n");
			break;
		case "invalid option":
			System.out.println("This is not a valid option number, please choose again\n");
			break;
		case "no ships":
			System.out.println("There are no available spaces to build your ship, ships must be connected to a lair\n");
			break;
		case "build lair option":
			System.out.println("Which number option would you like to build your lair at?: ");
			break;
		case "lair built":
			System.out.println("Lair has been successfully built\n");
			break;
		case "no lairs":
			System.out.println("There are no available spaces to build your lair, lairs must be connected to a ship\n");
			break;
			
		// Cases from 'CocoTiles' class
		case "no cocotiles":
			System.out.println("There are no Coco Tiles left to purchase");
			break;

		// Cases from 'Island' class
		case "Island check: ghost captain":
			System.out.println("The ghost captain is on one island with this rolled value. Resources will  not be distributed from this island.");
			break;

		// Cases from 'Marketplace' class
		case "successful marketplace trade":
			System.out.println("You successfully traded with the marketplace\n");
			break;
			
		// Cases from 'PlayerSetup' class
		case "number of players":
			System.out.println("Enter the number of players(3-4): ");
			break;
		case "player name":
			System.out.println("\nEnter a players name: ");
			break;
		case "invalid players":
			System.out.println("Entered value is invalid. Please enter either 3 or 4.\n");
			break;
			
		// Cases from 'PlayerTurn' class
		case "your turn":
			System.out.println("Would you like to Buy [B], Build [Bd], Trade [T], or End turn [E]? ");
			break;
		case "turn: buy":
			System.out.println("You have chosen to buy.\n");
			break;
		case "turn: build":
			System.out.println("You have chosen to build.\n");
			break;
		case "turn: trade":
			System.out.println("You have chosen to trade.\n");
			break;
		case "turn: end":
			System.out.println("You have chosen to End your turn.\n");
			break;
		case "turn: null":
			System.out.println("You did not select one of the available options. Please try again. ");
			break;
		case "cocotile price":
			System.out.println("Cost of a Cocotile: 1 Cutlass, 1 Molasses, and 1 Gold\n");
			break;
		case "buy cocotile?":
			System.out.println("Would you like to buy a cocotile? [Y/N] ");
			break;
		case "bought cocotile":
			System.out.println("* Collecting Cocotile *\n");
			break;
		case "cannot buy cocotile":
			System.out.println("You do not currently have the resources to buy a cocotile\n");
			break;
		case "cocotile ship/lair":
			System.out.println("Would you like to build a ship[S] or lair[L]:");
			break;
		case "ship/lair price":
			System.out.println("Costs:\n	Ship: 1 Wood, 1 Goat\n	Lair: 1 Wood, 1 Goat, 1 Molasses, 1 Cutlass\n");
			break;
		case "build ship/lair":
			System.out.println("Would you like to build a ship [S] or a lair [L]? ");
			break;
		case "build ship?":
			System.out.println("Would you like to build a ship? [Y/N] ");
			break;
		case "cannot buy ship/lair":
			System.out.println("You do not have enough resources to build either a ship or a lair.\n");
			break;
		case "build: ship":
			System.out.println("* Building ship *\n");
			break;
		case "build: lair":
			System.out.println("* Building lair *\n");
			break;
		case "trade M/S?":
			System.out.println("Would you like to trade with the marketplace [M], stockpile [S], or neither [N]?");
			break;
		case "trade S?":
			System.out.println("Would you like to trade with the stockpile? [Y/N] ");
			break;
		case "trade: M":
			System.out.println("You have chosen to trade with the marketplace.\n");
			break;
		case "trade: S":
			System.out.println("You have chosen to trade with the stockpile.\n");
			break;
		case "trade: N":
			System.out.println("You have chosen not to trade. \n");
			break;
		case "trade in":
			System.out.println("What item would to trade in (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? ");
			break;
		case "trade out":
			System.out.println("What item would you like to obtain (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? ");
			break;
		case "invalid resource":
			System.out.println("Choice is invalid. Please enter a valid choice.\n");
			break;
		case "marketplace: in-stock":
			System.out.println("The Marketplace has this resource");
			break;
		case "marketplace: out-of-stock":
			System.out.println("The Marketplace does not have resource");
			break;
		case "cannot trade":
			System.out.println("You do not currently have the resources to complete this trade\n");
			break;
		}
	}
	
	// ----- Prints message with 2 input values (1 int value, 1 String value) ----------
	public void printMessage(String instruction, String value1, int value2) {
		switch (instruction) {
		case "Player roll":
			System.out.println("It is your turn, " + value1 + ". You rolled a " + value2);
			break;
		}
	}
	
	// ----- Prints message with 1 CocoTyleTypes input value ---------------------------
		public void printMessage(String instruction, CocoTileTypes value1) {
			switch (instruction) {
			// In 'PlayerTurn' class
			case "Cocotile bought":
				System.out.println("Your cocotile is " + value1 + "\n");
				break;
			}
		}
	
	// ---------------------------------------------------------------------------------
	// --------- Methods: printBoard ---------------------------------------------------
	// This prints the board design for user view
	// ---------------------------------------------------------------------------------
	public void printBoard(char[][] design) {
		System.out.println("Board Layout:");
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				System.out.print(design[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}


	// ---------------------------------------------------------------------------------
	// --------- Methods: printPocket --------------------------------------------------
	// Prints the users pocket (GameManager class)
	// ---------------------------------------------------------------------------------
	public void printPocket(Player player) {
		System.out.println(player.viewPocket());
	}

	
	// ---------------------------------------------------------------------------------
	// --------- Methods: takeInAnswer -------------------------------------------------
	// Scans in users responses/inputs
	// ---------------------------------------------------------------------------------
	public String takeInAnswer() {
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}
}
