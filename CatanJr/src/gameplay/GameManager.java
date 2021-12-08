package gameplay;

import java.util.ArrayList;

import board.Board;
import board.Marketplace;
import board.Stockpile;
import main.Interactor;
import player.Player;
import resources.CocoTiles;
import setup.BoardSetup;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class GameManager {
	// -------------------------------------------------------------------------------------------------------
	// This class controls/runs the game
	// It controls the turns, it determines the winner
	// How it determines a winner: when a user has placed 7 lairs (including if they
	// have on on spooky island)
	// -------------------------------------------------------------------------------------------------------

	// -------------------------------------------------------------
	// ---------- Variables ----------------------------------------
	// -------------------------------------------------------------
	protected Board board; 				// Create a game board
	protected Stockpile stockPile; 		// Create a stockpile
	protected CocoTiles cocoTiles; 		// Create cocoTiles
	protected Marketplace marketPlace;	// Create a marketPlace
	protected String winner; 			// Create a string to hold the winner
	protected Dice dice;				// Create a dice
	
	// ----- Create an interactor object --------------------------
	Interactor interactor = new Interactor();

	// -------------------------------------------------------------
	// ---------- Constructor --------------------------------------
	// -------------------------------------------------------------
	public GameManager() {
	}

	// -------------------------------------------------------------
	// ---------- Method: StartGame() ------------------------------
	// This method sets up the game to begin 
	// -------------------------------------------------------------
	public void startGame() {
		// ----- Game setup --------------------------------------------------
		PlayerSetup playerSetup = new PlayerSetup(); 							// Set up players
		ResourceSetup resourceSetup = new ResourceSetup(playerSetup.Players()); // Set up resources
		BoardSetup boardSetup = new BoardSetup(playerSetup.Players());			// Set up board
		dice = new Dice(); 														// Create dice object
		board = boardSetup.getBoard();
		this.stockPile = resourceSetup.getStockpile(); 							// Set up the games stockpile
		this.cocoTiles = resourceSetup.getCocoTiles(); 							// Set up cocotiles
		this.marketPlace = resourceSetup.getMarketplace(); 						// Set up the resources in the marketplace
		// ----- While there is no winner declared, rotate players turns -----
		int player_turn = 0;
		while (!checkWinner(playerSetup.Players())) {
			PlayerTurn playerTurn = new PlayerTurn(playerSetup.Players().get(player_turn), marketPlace, stockPile, cocoTiles, board);
			board.mostCocotiles(playerSetup.Players());		// Check if player w/ most cocotiles can place lair on spooky island
			playerTurn.takeTurn(board.getIslands(), playerSetup.Players(), dice); 	// Player - take turn
			player_turn = (player_turn + 1) % (playerSetup.Players().size());		// Loop through the players
		}
		// ----- If there is a winner declared, announce the winner ----------
		interactor.printMessage("winner", winner);
	}

	// -------------------------------------------------------------
	// ---------- Method: checkWinner ------------------------------
	// This method checks players layers count to check for a winner
	// It does this by checking if a player has 7 or more layers
	// -------------------------------------------------------------
	public boolean checkWinner(ArrayList<Player> players) {
		for (int i = 0; i <= players.size() - 1; i++) {
			if (players.get(i).getLairCount() >= 7) {
				this.winner = players.get(i).PlayerName();
				return true; 
			}
		}
		return false;
	}

	// -------------------------------------------------------------
	// ---------- Method: checkWinner ------------------------------
	// This method checks players layers count to check for a winner
	// It does this by checking if a player has 7 or more layers
	// -------------------------------------------------------------
	public String getWinner() {
		return winner;
	}
}