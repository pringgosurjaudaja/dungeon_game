package unsw.dungeon.test;


import static org.junit.Assert.*;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.*;

import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Enemy;
import unsw.dungeon.BotAutoMove;
//import unsw.dungeon.AutoMoveAction;;

/*
User-story 6: Moving Boulders to Trap Enemies

Description: As a player, I want to push or destroy boulders that are in the way or even use them to trap the enemies, so that I can complete the goal of the game.

Acceptance Criteria:
- The boulders can be pushed by the player into adjacent squares.
- The player can push only one boulder at a time.
- Boulders that are located at adjacent squares to the lit bomb can be destroyed.
- The boulder won’t move when it’s being pushed against a wall.
- Assuming that the enemy can’t push a boulder, the player can push boulders to surround the enemies to trap them.
- Enemies cannot walk through the walls.
- Enemies can walk freely throughout the dungeon
*/

// This JUnit just test the enemies

public class US6Test {
	JSONObject json = new JSONObject();
	
	Dungeon dungeon ;
	
	
	@Test
	public void testMoveEnemy(){
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
		
		Boulder boulder = new Boulder(1, 2);
		dungeon.addEntity(boulder);
		Enemy enemy = new Enemy(4,2);
		dungeon.addEntity(enemy);
	
		Point p = new Point(enemy.getX(), enemy.getY());	// point before the enemy moves
		
		BotAutoMove b = new BotAutoMove(dungeon, player);
		enemy.autoMove(b.getAllEntitiesCoordinates());

		assertThat(p, not(new Point(enemy.getX(), enemy.getY())));	// check that the point before enemy moves != point after enemy moves, should succeed


	}

	
	   
}
