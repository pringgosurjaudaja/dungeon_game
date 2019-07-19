package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.layout.GridPane;
import unsw.dungeon.Key;
import unsw.dungeon.LockedDoor;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;


//import unsw.dungeon.AutoMoveAction;;

/*
User-story 8: Using keys to open doors

Description: As a player, I want to collect and use keys to open the doors, so that I can complete the goal and make the game fun and interesting.

Acceptance Criteria:
- Each key can only open its corresponding door.
- The player can carry only one key at a time.
- The player can drop the key it's carrying at any time and pick it back again later.
- The key disappears once it is used to open its corresponding door.
- Once opened, the door stays unlocked.
*/

public class US8Test {
	JSONObject json = new JSONObject();
	
	Dungeon dungeon ;
	
	@Before
	public void init() {
		json.put("goal", "treasure");
		dungeon = new Dungeon(10 , 10 , json);
		dungeon.addEntity(new Wall(0, 0));
		dungeon.addEntity(new Wall(1, 0));
		dungeon.addEntity(new Wall(2, 0));
		dungeon.addEntity(new Wall(3, 0));
		dungeon.addEntity(new Wall(4, 0));
		dungeon.addEntity(new Wall(5, 0));
		dungeon.addEntity(new Wall(6, 0));
		dungeon.addEntity(new Wall(7, 0));
		dungeon.addEntity(new Wall(8, 0));
		dungeon.addEntity(new Wall(9, 0));
	}	
	
	@Test
	public void testOpeningDoorWithRightKey(){
		
		Key key0 = new Key(5, 2, 0);
		Key key1 = new Key(4, 3, 1);
		LockedDoor door0 = new LockedDoor(4,2,0);
		LockedDoor door2 = new LockedDoor(5,3,2);
		dungeon.addEntity(key0);
		dungeon.addEntity(key1);
		dungeon.addEntity(door0);
		dungeon.addEntity(door2);
			
		Player player = new Player(dungeon, 6, 2);

		player.moveLeft();	// took a key with id = 0
		assertEquals(key0, player.getCarryOns());			// carry_ons should be key0, test should succeed
		player.moveLeft();	// open a door with id = 0
		assertEquals(true, door0.isOpen());					// door should be open, test should succeed
		assertEquals(null, player.getCarryOns());			// carry_ons should be null, test should succeed
		
		player.moveDown();	// took a key with id = 1
		assertEquals(key1, player.getCarryOns());			// carry_ons should be key1, test should succeed
		player.moveRight(); // try to open door with id = 2
		assertEquals(false, door2.isOpen());				// door should be closed, test should succeed
		assertEquals(key1, player.getCarryOns());			// carry_ons should still be key1, test should succeed
		assertEquals(4, player.getX());
		assertEquals(3, player.getY());						// player shouldn't be able to move right, test should succeed 
		

	}

	@Test
	public void dropKey(){
		
		Key key3 = new Key(2, 4, 3);
		LockedDoor door3 = new LockedDoor(5,4,3);
		dungeon.addEntity(key3);
		dungeon.addEntity(door3);
			
		Player player = new Player(dungeon, 1, 4);

		player.moveRight();	// took a key with id = 3
		assertEquals(key3, player.getCarryOns());			// carry_ons should be key3, test should succeed
		player.moveRight();	
		player.dropEntity();	// drop key with id = 3
		assertEquals(null, player.getCarryOns());			// carry_ons should be null, test should succeed
		player.moveRight();
		player.moveLeft();	// took a key with id = 3
		player.moveRight();
		player.moveRight();	// open a door with id = 3
		assertEquals(true, door3.isOpen());					// door should be open, test should succeed
		assertEquals(null, player.getCarryOns());			// carry_ons should be null, test should succeed

		

	}
	   
}
