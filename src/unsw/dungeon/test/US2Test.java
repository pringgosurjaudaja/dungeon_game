package unsw.dungeon.test;


import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Boulder;
import unsw.dungeon.Treasure;
import unsw.dungeon.Switch;

/*
User-story 2: Move Boulder to Switch

Description: As a player, I want to move the boulders to the switches so that I can complete the goal and solve the puzzle.

Acceptance Criteria:
- The player can move the boulder by pushing it to left, right, top or bottom direction.
- The boulder won’t move when it’s being pushed against a wall.
- The boulder won’t move when it’s being pushed against another entity, except for switch.
- The player can push one boulder at a time.
- Boulder that is on top of a switch can still be pushed.
*/


public class US2Test {
	JSONObject json = new JSONObject();
	
	Dungeon dungeon;

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
	public void testMoveBoulderAllDirection(){
		
		Boulder boulder1 = new Boulder(3, 2);
		dungeon.addEntity(boulder1);
		Player player1 = new Player(dungeon, 3, 3);
		player1.moveUp();					// both player and boulders should move up, should succeed
		assertEquals(3, player1.getX());
		assertEquals(2, player1.getY());
		assertEquals(3, boulder1.getX());
		assertEquals(1, boulder1.getY());
		
		Boulder boulder2 = new Boulder(5, 2);
		dungeon.addEntity(boulder2);
		Player player2 = new Player(dungeon, 5, 1);
		player2.moveDown();					// both player and boulders should move down, should succeed
		assertEquals(5, player2.getX());
		assertEquals(2, player2.getY());
		assertEquals(5, boulder2.getX());
		assertEquals(3, boulder2.getY());
		
		Boulder boulder3 = new Boulder(1, 2);
		dungeon.addEntity(boulder3);
		Player player3 = new Player(dungeon, 2, 2);
		player3.moveLeft();					// both player and boulders should move left, should succeed
		assertEquals(1, player3.getX());
		assertEquals(2, player3.getY());
		assertEquals(0, boulder3.getX());
		assertEquals(2, boulder3.getY());
		
		Boulder boulder4 = new Boulder(8, 2);
		dungeon.addEntity(boulder4);
		Player player4 = new Player(dungeon, 7, 2);
		player4.moveRight();					// both player and boulders should move right, should succeed
		assertEquals(8, player4.getX());
		assertEquals(2, player4.getY());
		assertEquals(9, boulder4.getX());
		assertEquals(2, boulder4.getY());
		
		Boulder boulder5 = new Boulder(2, 4);
		dungeon.addEntity(boulder5);
		Player player5 = new Player(dungeon, 1, 4);
		player5.moveRight();					// both player and boulders should move right, should FAIL
		assertEquals(1, player5.getX());
		assertEquals(4, player5.getY());
		assertEquals(2, boulder5.getX());
		assertEquals(4, boulder5.getY());

	}
	
	@Test
	public void testMoveBoulderAgaintWall(){
		
		Boulder boulder1 = new Boulder(4, 1);
		dungeon.addEntity(boulder1);
		Player player1 = new Player(dungeon, 4, 2);
		player1.moveUp();					// up is wall, both player and boulders can't move up, should succeed
		assertEquals(4, player1.getX());
		assertEquals(2, player1.getY());
		assertEquals(4, boulder1.getX());
		assertEquals(1, boulder1.getY());
		
		player1.moveUp();					// up is wall, both player and boulders can't move up, should FAIL
		assertEquals(4, player1.getX());
		assertEquals(1, player1.getY());
		assertEquals(4, boulder1.getX());
		assertEquals(0, boulder1.getY());
		

	}
	
	@Test
	public void testMoveBoulderAgainstEntities(){
		
		Treasure treasure = new Treasure(0,3);
		Switch switch_ = new Switch(0,2);
		dungeon.addEntity(treasure);
		dungeon.addEntity(switch_);
		
		Boulder boulder0 = new Boulder(1, 2);
		dungeon.addEntity(boulder0);
		Player player0 = new Player(dungeon, 2, 2);
		player0.moveLeft();					// left is a switch, both player and boulders should move left, should succeed
		assertEquals(1, player0.getX());
		assertEquals(2, player0.getY());
		assertEquals(0, boulder0.getX());
		assertEquals(2, boulder0.getY());
		
		Boulder boulder1 = new Boulder(1, 3);
		dungeon.addEntity(boulder1);
		Player player1 = new Player(dungeon, 2, 3);
		player1.moveLeft();					// left is a Treasure, both player and boulders can't move left, should succeed
		assertEquals(2, player1.getX());
		assertEquals(3, player1.getY());
		assertEquals(1, boulder1.getX());
		assertEquals(3, boulder1.getY());
		

	}
	
	   
}
