package main;

import java.util.Scanner;
import java.util.*;
import gameplay.*;

import player.Player;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class main {
	
	public static void main(String[] args) {
		
		// ---------- TESTING: REMOVE ALL THIS LATER ----------//
		// Create a Person and make sure it is taking in what move the player wants to take 
		//Player player1 = new Player("Emma", "blue");
		//player1.yourTurn();

	
		PlayerSetup playerSetup = new PlayerSetup(); //setup players
		ResourceSetup resourceSetup = new ResourceSetup(playerSetup.Players()); //setup resources
		PlayerTurn turn1 = new PlayerTurn();
		turn1.takeTurn();
    }

}
