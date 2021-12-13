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
	// This singleton class controls/runs the game
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
	protected Dice dice;				// Create a dice
	protected PlayerTurn playerTurn;	// Create player turn
	protected boolean playGame = true; 	// Variable to control game play
	private static GameManager instance = new GameManager(); // Create instance of GameManager
    protected Player subject;



	// ----- Create an interactor object --------------------------
	Interactor interactor = new Interactor();

	// -------------------------------------------------------------
	// ---------- Constructor --------------------------------------
	// -----private constructor so that it cannot be instantiated---
	// -------------------------------------------------------------
	private GameManager() {
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
		dice = Dice.getInstance(); 														// Create dice object
		board = boardSetup.getBoard();
		this.stockPile = resourceSetup.getStockpile(); 							// Set up the games stockpile
		this.cocoTiles = resourceSetup.getCocoTiles(); 							// Set up cocotiles
		this.marketPlace = resourceSetup.getMarketplace(); 						// Set up the resources in the marketplace
		// ----- While there is no winner declared, rotate players turns -----
		int player_turn = 0;
		while (playGame) {
			this.playerTurn = new PlayerTurn(playerSetup.Players().get(player_turn), marketPlace, stockPile, cocoTiles, board);
			board.mostCocotiles(playerSetup.Players());				// Check if player w/ most cocotiles can place lair on spooky island
			this.subject = playerSetup.Players().get(player_turn); 	// Assign the player as the subject for the observer
			subject.attach(GameManager.this); 						// Attach observer to subject
			playerTurn.takeTurn(board.getIslands(), playerSetup.Players(), dice); 	// Player - take turn
			player_turn = (player_turn + 1) % (playerSetup.Players().size());		// Loop through the players
		}
	}
	
	
		// -------------------------------------------------------------
		// ---------- Method: checkWinner ------------------------------
		// observer method to check if players lair count is equal to 7
		// -------------------------------------------------------------
		public void checkWinner(Player player) {
			if (player.getLairCount() >= 7) { 
				// ----- If there is a winner declared, announce the winner ----------
				interactor.printMessage("winner", player.PlayerName());
				this.playGame = false;		// end game play loop
				this.playerTurn.endGame();  // end players turn
			}
		}
		
		// -------------------------------------------------------------
		// ---------- Method: getInstance ------------------------------
		// -- method for getting the instance of GameManager----------------
		// -------------------------------------------------------------
		public static GameManager getInstance() {
			return instance;
		}
}