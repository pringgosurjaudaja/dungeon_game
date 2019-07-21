package unsw.dungeon;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;


public class US3Test {
	Dungeon testDungeon;
	Player player;
	@Test
	public void test() throws JSONException, FileNotFoundException {
		JSONObject json = new JSONObject(new JSONTokener(new FileReader("dungeons/testingdungeon.json")));
		JSONObject goals = json.getJSONObject("goal-condition");
		int width = json.getInt("width");
	    int height = json.getInt("height");
        JSONArray jsonEntities = json.getJSONArray("entities");
        testDungeon = new Dungeon(width, height, goals);
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(testDungeon, jsonEntities.getJSONObject(i));
        }
        player = testDungeon.getPlayer();
		//Player should be in (1,1)
		assertEquals(1, player.getX());
		assertEquals(1, player.getY());
		for(int i = 0; i < 4; i++) {
			player.moveRight();
		}
		//To the right of player should be a sword
		assertNull(player.getCarryOns());
		player.moveRight();
		assertNotNull(player.getCarryOns());
		assertEquals(5, player.getDurability());
		player.killEnemy();
		//since no enemy durability should still be 5
		assertEquals(5, player.getDurability());
		for(int i = 0; i < 10; i++) {
			player.moveRight();
		}
		for(int i = 0; i < 8; i++) {
			player.moveDown();
		}
		//in front should be enemy
		assertNotNull(testDungeon.getEntity(16, 10));
		assertTrue(testDungeon.getEntity(16, 10) instanceof Enemy);
		player.killEnemy();
		assertEquals(4, player.getDurability());
		//enemy should be dead
		assertNull(testDungeon.getEntity(16, 10));
		player.moveLeft();
		for(int i = 0; i < 5; i++) {
			player.moveUp();
		}
		player.moveLeft();
		//tries to pick up another item (bomb), but can't
		player.moveLeft();
		assertNotNull(testDungeon.getEntity(13, 4));
	}
	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            entity = wall;
            break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	entity = boulder;
        	break;
        case "switch":
        	Switch switch_ = new Switch(x, y);
            entity = switch_;
            break;
        case "bomb":	// unlit bomb
        	Bomb bomb = new Bomb(x, y);
            entity = bomb;
            break;
        //case "lit bomb":
        case "enemy":
        	Enemy enemy = new Enemy(x, y);
        	entity = enemy;
        	break;
    	case "sword":
    		Sword sword = new Sword(x, y,5);
    		entity = sword;
    		break;
    	case "invincibility":
    		Invincibility invincibility = new Invincibility(x, y);
    		entity = invincibility;
    		break;
        // TODO Handle other possible entities
        case "exit":
            Exit exit = new Exit(x, y);
            entity = exit;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            entity = treasure;
        	break;
    	}

        dungeon.addEntity(entity);
    }
}
