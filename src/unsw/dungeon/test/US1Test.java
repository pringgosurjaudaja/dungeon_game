package unsw.dungeon.test;


import org.junit.Assert;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;




public class US1Test {
	Dungeon dungeon = new Dungeon(10,10, null);
	
	
	@Test
	public void testMoveUp(){
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

		Assert.assertEquals(2, player.getX());
		Assert.assertEquals(1, player.getY());
		
		System.out.println("test");

}

	

	
	   
}