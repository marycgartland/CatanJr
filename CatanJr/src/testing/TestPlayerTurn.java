package testing;

// -----------------------------------------------------------------------------------------------------------
// This JUnit test tests the playerTurn class of the gameplay package. It runs tests on the cocotileAction()
// method of the class. The rest of the methods in this class are covered by system testing or require user
// interaction
// -----------------------------------------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.*;

import board.Board;
import board.Marketplace;
import board.Stockpile;
import player.Player;
import resources.CocoTileTypes;
import resources.CocoTiles;
import resources.Resources;
import setup.BoardSetup;
import setup.ResourceSetup;
import gameplay.PlayerTurn;

public class TestPlayerTurn {

	private ArrayList<Player> testPlayerList;
	private ResourceSetup resourceSetup;
	private BoardSetup testboardSetup;
	private Board testBoard;
	private Marketplace testMarketplace;
	private Stockpile testStockpile;
	private PlayerTurn testTurn;
	private CocoTiles testCocoTiles;
	
	@Before
	public void setUp() throws Exception{
		// Players Setup
		testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		
		// Resource Setup
		resourceSetup = new ResourceSetup(testPlayerList);
		
		// Board Setup
		testboardSetup = new BoardSetup(testPlayerList);
		testBoard = Board.getInstance();
		testBoard = testboardSetup.getBoard();
		
		// Setup Marketplace, Stockpile and Cocotiles for testing
		testMarketplace = resourceSetup.getMarketplace();
		testStockpile = resourceSetup.getStockpile();
		testCocoTiles = new CocoTiles();
		
		// PlayerTurn
		testTurn = new PlayerTurn(testPlayerList.get(0),testMarketplace, testStockpile, testCocoTiles, testBoard);
		
		System.out.println("Setup");
	}
	
	@After
	public void tearDown() {
		testPlayerList.clear();
		testBoard = null;
		testboardSetup = null;
		resourceSetup = null;
		testTurn = null;
		testMarketplace = null;
		testStockpile = null;
		testCocoTiles = null;
		testTurn = null;
		
		System.out.println("Teardown");
	}
	
	// -----------------------------------------------------------------------------
	// ---------- Section 1 Testing : Testing method cocotileAction() --------------
	// Ensure that when a cocotile is bought, the actions occur accordingly
	//------------------------------------------------------------------------------
	// Test 1.1: goat and cutlass cocotile; check players pocket
	@Test
	public void testCocotileActionGCP() {
		// Call MUT
		testTurn.cocotileAction(CocoTileTypes.GoatCutlasses);
		// Test
		int playerCountGoat = testPlayerList.get(0).checkPocketResourcesType(Resources.Goats);
		int playerCountCutlasses = testPlayerList.get(0).checkPocketResourcesType(Resources.Cutlasses);
		assertEquals(2, playerCountGoat, "cocotileAction() - Goats/Cutlasses - Goats to player");
		assertEquals(2, playerCountCutlasses, "cocotileAction() - Goats/Cutlasses - Cutlasses to player");
	} 
	
	// Test 1.2: goat and cutlass cocotile; check stockpile
	@Test
	public void testCocotileActionGCS() {
		// Call MUT
		int SInitGoat = testStockpile.getResourceCount(Resources.Goats);
		int SInitCutlasses = testStockpile.getResourceCount(Resources.Cutlasses);
		testTurn.cocotileAction(CocoTileTypes.GoatCutlasses);
		// Test
		int SFinalGoat = testStockpile.getResourceCount(Resources.Goats);
		int SFinalCutlasses = testStockpile.getResourceCount(Resources.Cutlasses);
		assertEquals(2, SInitGoat - SFinalGoat, "cocotileAction() - Goats/Cutlasses - Goats from stockpile");
		assertEquals(2, SInitCutlasses - SFinalCutlasses, "cocotileAction() - Goats/Cutlasses - Cutlasses from stockpile");
	} 
	
	// Test 1.3: wood and molasses cocotile; check players pocket
	@Test
	public void testCocotileActionWMP() {
		// Initial counts
		int woodInitial = testPlayerList.get(0).checkPocketResourcesType(Resources.Wood);
		int molassesInitial = testPlayerList.get(0).checkPocketResourcesType(Resources.Molasses);
		// Call MUT
		testTurn.cocotileAction(CocoTileTypes.WoodMolasses);
		// Test
		int woodFinal = testPlayerList.get(0).checkPocketResourcesType(Resources.Wood);
		int MolassesFinal = testPlayerList.get(0).checkPocketResourcesType(Resources.Molasses);
		assertEquals(2, woodFinal - woodInitial, "cocotileAction() - Wood/molasses - Wood to player");
		assertEquals(2, MolassesFinal - molassesInitial, "cocotileAction() - Wood/molasses - Molasses to player");
	} 
	
	// Test 1.4. wood and molasses cocotile; check stockpile
	@Test
	public void testCocotileActionWMS() {
		// Call MUT
		int woodInitial = testStockpile.getResourceCount(Resources.Wood);
		int molassesInitial = testStockpile.getResourceCount(Resources.Molasses);
		testTurn.cocotileAction(CocoTileTypes.WoodMolasses);
		// Test
		int woodFinal = testStockpile.getResourceCount(Resources.Wood);
		int molassesFinal = testStockpile.getResourceCount(Resources.Molasses);
		assertEquals(2, woodInitial - woodFinal, "cocotileAction() - Wood/molasses - Wood from stockpile");
		assertEquals(2, molassesInitial - molassesFinal, "cocotileAction() - Wood/molasses - Molasses from stockpile");
	} 
}