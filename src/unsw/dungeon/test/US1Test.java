package unsw.dungeon.test;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

/*
User-story 1: Moving around

Description: As a player, I want to be able to move around the dungeon so that I can do tasks and achieve my goals.

Acceptance Criteria:
- The player will be able to move around the dungeon in 4 directions namely up, down, left and right.
- The player won’t be able to go through the walls of the dungeon.
*/


public class US1Test {
	JSONObject json = new JSONObject();
	
	Dungeon dungeon ;
	
	
	@Test
	public void testMoveAllDirections(){
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
		Player player = new Player(dungeon, 2, 1);
		player.moveUp();
		
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		
		
		Player player1 = new Player(dungeon, 3, 2);
		player1.moveUp();	// check moving up, should succeed
		assertEquals(3, player1.getX());
		assertEquals(1, player1.getY());
		
		player1.moveDown();	// check moving down, should succeed
		assertEquals(3, player1.getX());
		assertEquals(2, player1.getY());
		
		player1.moveLeft();	// check moving left, should succeed
		assertEquals(2, player1.getX());
		assertEquals(2, player1.getY());
		
		player1.moveRight();	// check moving right, should succeed
		assertEquals(3, player1.getX());
		assertEquals(2, player1.getY());
		
/*		player1.moveRight();	// check moving right, should FAIL
		assertEquals(3, player1.getX());
		assertEquals(2, player1.getY()); */

	}
	
	@Test
	public void testMoveWall(){
		json.put("goal", "treasure");
		dungeon = new Dungeon(10 , 10 , json);
		dungeon.addEntity(new Wall(7, 1));
		dungeon.addEntity(new Wall(8, 0));
		dungeon.addEntity(new Wall(8, 2));
		dungeon.addEntity(new Wall(9, 0));
		dungeon.addEntity(new Wall(9, 1));
		dungeon.addEntity(new Wall(9, 2));
		dungeon.addEntity(new Wall(9, 3));
		dungeon.addEntity(new Wall(9, 4));
		dungeon.addEntity(new Wall(9, 5));
		dungeon.addEntity(new Wall(9, 6));
		dungeon.addEntity(new Wall(9, 7));
		dungeon.addEntity(new Wall(9, 8));
		dungeon.addEntity(new Wall(9, 9));
		
		
		Player player = new Player(dungeon, 8, 1);

		player.moveUp();		// moving up to wall, should success
		assertEquals(8, player.getX());
		assertEquals(1, player.getY());
		
		player.moveDown();		// moving down to wall, should success
		assertEquals(8, player.getX());
		assertEquals(1, player.getY());
		
		player.moveLeft();		// moving left to wall, should success
		assertEquals(8, player.getX());
		assertEquals(1, player.getY());
		
		player.moveRight();		// moving right to wall, should success
		assertEquals(8, player.getX());
		assertEquals(1, player.getY());
		
	/*	player.moveRight();		// moving right to wall, should FAIL
		assertEquals(9, player.getX());
		assertEquals(1, player.getY());  */
	
	}

	
	
	

	
	   
}
