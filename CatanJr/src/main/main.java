package main;

import java.util.Scanner;
import java.util.*;
import gameplay.*;
import board.*;

import player.Player;
import resources.Resources;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class main {
	
	public static void main(String[] args) {
		
		GameManager gameManager = new GameManager();
		gameManager.startGame();
    }

}
