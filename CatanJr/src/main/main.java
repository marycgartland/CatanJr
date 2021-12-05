package main;

import gameplay.GameManager;

public class main {
	
	public static void main(String[] args) {
		
		GameManager gameManager = new GameManager();
		gameManager.startGame();
		
		// Check if these things are done:
			// place a lair and ship on the board for each player, by their color (is this under board setup or player setup?)
			// Assign each player a building cost tile that matches his/her colour
			// Add 1 wood and 1 molasses to users pocket
		
		
		// FROM COCOTILES CLASS:
			// Keep track of which player has the most tiles 
		
		
		// GHOST CAPTAIN:
			// Question: Don't even know if the Ghost Captain should be under board still or not (are we happy w it?)
		
		
		// MARKETPLACE:
			// If all 5 places are the same resource, then we need to reset the marketplace (need a reset method)
			// Method to let the user trade
			// SetupMethod will be called by the setup class: will restock the marketplace w 1 of each resource (1x: cutlass, goat, wood, gold, molasses)
			// CheckMethod - check that all resources in marketplace aren't the same. if it is , Setup Method is called & all resources are returned to stockpile
			// Swap method that can only be called once in a turn which tracks resources in marketplace
			// YourTurn Class should keep track of how many times the marketplace is swapped with per users turn
			// TODO: Problem with checkMarketPlace class: checks to see if there are 5 of the same element or not. 
					// this only works if the loop goes to <=3 and the counter==4, but I would say that those values 
					// need to be increased by one but it won't run like that
		
		

		// Question: Add a lair and ship for each player at their color location on the board (is this board setup or player setup? It's chosen by players...)
			// Think this is sorted?
		
		
		// TODO: Check that cocotiles are actually shuffled 
		
		
		// Keeping track of the most cocotiles:
			// If a player has the same number of tiles another, they should remove their pirates lair from spooky island
			// If a player has the most cocotiles, they get to place an unused pirates lair on spooky island
			// First player to buy a cocotile has the most at that moment
			// User keeps track the number of cocotiles they have - how we will determine who has the most in the game
		
		
		// More cocotile stuff 
			// YourTurn needs to determine the action based on the cocotiletype outputted by
			// user is given WoodMolasses/GoatCutlasses need to give out 2 of each of that resource etc
			// Check user pocker before they buy a cocotile to make sure they have sufficient funds
		
		
		// IN PLAYER CLASS
			// TODO: Change these back to 0. they are just set to 20 for testing purposes at the moment
			// TODO: We want methods to show people their resources at any time protected Item []
		
		
		// More things I have to do: 
			// TODO: close scanner in interactor class
			// TODO: Finish changing all user interactions to interactor class
			// TODO: Abstract classes and interfaces?
			// TODO: Limit any code repetition
			// TODO: move the creation of the dice object from playerTurn to Setup/gameplay
			// TODO: Error check for players buying cocotiles - entering incorrect options
		
		
		// TODO: possibly have a command to user can view they pocket whenever they wish
		
		
		// TODO: Make sure user can't build 2 of the same ships/lairs side by side
		
		
		// TODO: error checking if user doesn't choose ship/LAIR OPTION	
		
		
		// Question: Do we want to remove the pocket printing in the GameManager take turn loop? it was set for testing
		
		
		// IN STOCKPILE CLASS	
			// Keep track of the number of each resource
			// Method to make sure there is at least 1 of each resource left, otherwise all of that resource are returned to the stockpile
			// Stockpile needs to be called before the users are given their first  pieces and before the marketplace is setup
			// Need method to assign resources to different users, for example when the dice is rolled, the stockpile needs to assign the resources to the players
			// setupPlayers prints pocket - do we want to leave this in or take this out?
    }
}
