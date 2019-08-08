package unsw.dungeon;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class US5Test {
	Dungeon testDungeon;
	Player player;
	@Test
	public void test() throws JSONException, FileNotFoundException {
		JSONObject json = new JSONObject(new JSONTokener(new FileReader("dungeons/InvincibilityTest.json")));
		JSONObject goals = json.getJSONObject("goal-condition");
		int width = json.getInt("width");
	    int height = json.getInt("height");
        JSONArray jsonEntities = json.getJSONArray("entities");
        testDungeon = new Dungeon(width, height, goals);
        for (int i = 0; i < jsonEntities.length(); i++) {
        	//System.out.println("HELLo");
            loadEntity(testDungeon, jsonEntities.getJSONObject(i));
        }
        player = testDungeon.getPlayer();
        Invincibility invin = new Invincibility(1,1);
        player.moveDown();
        assertNotNull(player.getInvincibility());
        assertEquals(invin.getClass(), player.getInvincibility().getClass());
        //assertEquals(invin.getState().getClass(), player.getInvincibility().getState().getClass());
        player.moveDown();
        invin.countdownInvincibility();
        //assertEquals(invin.getState().getClass(), player.getInvincibility().getState().getClass());
        player.moveDown();
        invin.countdownInvincibility();
        //assertEquals(invin.getState().getClass(), player.getInvincibility().getState().getClass());
        player.moveDown();
        invin.countdownInvincibility();
        //assertEquals(invin.getState().getClass(), player.getInvincibility().getState().getClass());
        player.moveDown();
        //enemy is in front of player
        assertNotNull(testDungeon.getEntity(1, 6));
        invin.countdownInvincibility();
        //assertEquals(invin.getState().getClass(), player.getInvincibility().getState().getClass());
        player.moveRight();
        //assert enemy is dead because hit by invincibility potion
        assertNull(testDungeon.getEntity(1, 6));
	}
	public void loadEntity(Dungeon dungeon, JSONObject json) {
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
