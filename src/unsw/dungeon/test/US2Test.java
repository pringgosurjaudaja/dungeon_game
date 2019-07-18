package unsw.dungeon.test;


import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Boulder;




public class US2Test {
	JSONObject json = new JSONObject();
	
	Dungeon dungeon ;
	
	
	@Test
	public void testMoveBoulder(){
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
		
		Boulder boulder = new Boulder(1, 2);
		dungeon.addEntity(boulder);
		Player player = new Player(dungeon, 2, 2);
		
		player.moveLeft();	// both player and boulders should move left, should succeed
		assertEquals(1, player.getX());
		assertEquals(2, player.getY());
		assertEquals(0, boulder.getX());
		assertEquals(2, boulder.getY());
		
		Boulder boulder1 = new Boulder(3, 2);
		dungeon.addEntity(boulder1);
		Player player1 = new Player(dungeon, 3, 3);
		
		player.moveUp();	// both player and boulders should move up, should succeed
		assertEquals(3, player1.getX());
		assertEquals(2, player1.getY());
		assertEquals(3, boulder1.getX());
		assertEquals(1, boulder1.getY());
		
		player.moveUp();	// both player and boulders should stay, should fail
		assertEquals(3, player1.getX());
		assertEquals(1, player1.getY());
		assertEquals(3, boulder1.getX());
		assertEquals(0, boulder1.getY());
		

	}
	

	
	   
}
