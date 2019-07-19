package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Treasure;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;


//import unsw.dungeon.AutoMoveAction;;

/*
User-story 7: Collecting Treasures

Description: As a player, I want to collect all the ​treasures available so that I can complete the goal and make the game fun and interesting.

Acceptance Criteria:
- There will be a maximum X amount of treasures on each dungeon.
- Once the player is in front of a treasure, players can pick up the treasure.
*/

// This JUnit just test the enemies

public class US7Test {
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
	public void testCollectTreasure(){
		
		
		Treasure treasure1 = new Treasure(1,2);
		Treasure treasure2 = new Treasure(1,3);
		Treasure treasure3 = new Treasure(2,3);
		Treasure treasure4 = new Treasure(3,3);
		Treasure treasure5 = new Treasure(3,2);
		dungeon.addEntity(treasure1);
		dungeon.addEntity(treasure2);
		dungeon.addEntity(treasure3);
		dungeon.addEntity(treasure4);
		dungeon.addEntity(treasure5);
			
		Player player = new Player(dungeon, 2, 2);

		player.moveLeft();
		player.moveDown();
		player.moveRight();
		player.moveRight();
		player.moveUp();
		
		assertEquals(5, player.getTreasure());		// player should collect 5 treasures, test should succeed


	}

	
	   
}
