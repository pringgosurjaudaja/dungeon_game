package unsw.dungeon.test;

import org.junit.Test;

import unsw.dungeon.Dungeon;

import static org.junit.Assert.assertEquals;

public class US1Test {

	Dungeon d = new Dungeon(int width, int height) {

	   Player p = new Player(Dungeon dungeon, 1, 1);

	
	
	   String message = "Hello World";	
	   MessageUtil messageUtil = new MessageUtil(message);

	   @Test
	   public void testPrintMessage() {
		  message = "New Word";
	      assertEquals(message,messageUtil.printMessage());
	   }
	   
}
