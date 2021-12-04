package main;

import java.util.Scanner;

import player.Player;
import resources.Resources;
import board.Board;

//this class is called to interact with user, taking in values, outputting to console etc
public class Interactor {

	// Constructor
	public Interactor() {
	}

	// Prints out message including one supplied value in the string
	public void printMessage(String instruction, String value) {
		switch (instruction) {
		case "trade in resource":
			System.out.println("You have chosen to trade in " + value + "\n");
			break;
		case "trade out resource":
			System.out.println("You have chosen to obtain " + value + "\n");
			break;
		case "winner":
			System.out.println("Congratulations " + value + "! You're the Winner!\n");
		}
	}
	
	// Prints out message including two supplied values in the string
		public void printMessage(String instruction, Resources value1, String value2) {
			switch (instruction) {
			case "Island check: success":
				System.out.println("Add a " + value1 + " for player " + value2);
				break;
			}
		}
	

	// Prints out message based on instruction given
	public void printMessage(String instruction) {
		switch (instruction) {
		case "number of players":
			System.out.println("Enter the number of players(2-4): ");
			break;
		case "player name":
			System.out.println("\nEnter a players name: ");
			break;
		case "invalid players":
			System.out.println("Entered value is invalid. Please enter a number between 1 and 4.\n");
			break;
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
		case "ship/lair price":
			System.out.println("Costs:\n	Ship: 1 Wood, 1 Goat\n	Lair: 1 Wood, 1 Goat, 1 Molasses, 1 Cutlass\n");
			break;
		case "build ship/lair":
			System.out.println("Would you like to build a ship [S] or a lair [L]? ");
			break;
		case "build ship?":
			System.out.println("Would you like to build a ship? [Y/N] ");
			break;
		case "build ship option":
			System.out.println("Which number option would you like to build your ship at?: ");
			break;
		case "build lair option":
			System.out.println("Which number option would you like to build your lair at?: "); 
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
			System.out.println(
					"What item would to trade in (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? ");
			break;
		case "trade out":
			System.out.println("What item would you like to obtain (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? ");
			break;
		case "invalid resource":
			System.out.println("Choice is invalid. Please enter a valid choice.\n");
			break;
		case "no cocotiles":
			System.out.println("There are no Coco Tiles left to purchase");
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
		case "successful marketplace trade":
			System.out.println("You successfully traded with the marketplace\n");
			break;
		case "move ghost captain":
			System.out.println("Where which island would you like to move the ghost captain to?:\n");
			break;
		case "GC moved":
			System.out.println("Ghost Captain has been moved\n");
			break;
		case "cocotile ship/lair":
			System.out.println("Would you like to build a ship[S] or lair[L]:");
			break;
		case "Island check: ghost captain":
			System.out.println("The ghost captain is on one island with this rolled value. Resources will  not be distributed from this island.");
			break;
		}
	}
	
	// Printing the layout of the board
	//protected char[][] design;
	//getDesign()
	public void printBoard(char[][] design) {
		System.out.println("Board Layout:");
		//design = board.getDesign();
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				System.out.print(design[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	// Prints out message including two supplied values (one string, one int)
	public void printMessage(String instruction, String value1, int value2) {
		switch (instruction) {
		case "Player roll":
			System.out.println("It is your turn, "+ value1 + ". You rolled a " + value2);
			break;
			}
		}
			

	// Scan in users answer
	public String takeInAnswer() {
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}

}
