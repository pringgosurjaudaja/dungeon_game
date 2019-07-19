package unsw.dungeon;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class US4Test {
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
        	//System.out.println("HELLo");
            loadEntity(testDungeon, jsonEntities.getJSONObject(i));
        }
        player = testDungeon.getPlayer();
        Bomb bomb = new Bomb(1,1);
        for(int i = 0; i < 10; i++) {
        	player.moveRight();
        }
        for(int i = 0;i<3;i++) {
        	player.moveDown();
        }
        for(int i = 0; i < 2; i++) {
        	player.moveRight();
        }
        //assert player have sword
        assertNotNull(player.getCarryOns());
        player.dropEntity();
        //assert carryon is null since item dropped
        assertNull(player.getCarryOns());
        player.moveRight();
        //assert not null carry on since player picked up bomb
        assertNotNull(player.getCarryOns());
        bomb.countDownBomb();
        //assert that item picked up is bomb
        Bomb dropBomb = (Bomb) player.getCarryOns();
        assertTrue(player.getCarryOns().getClass().equals(bomb.getClass()));
        player.dropEntity();
        assertNull(player.getCarryOns());
        //assert bomb countdown is 4 as bomb is just dropped
        assertEquals(4, dropBomb.getCountdown());
        player.moveLeft();
        bomb.countDownBomb(); //bomb should be in LitBomb2
        //System.out.println(testDungeon.getEntity(player.getX()+1, player.getY()));
        Bomb droppedBomb = (Bomb) testDungeon.getEntity(player.getX()+1, player.getY());
        //System.out.println(droppedBomb.getState());
        assertEquals(bomb.getState().getClass(), droppedBomb.getState().getClass());
        assertEquals(3, droppedBomb.getCountdown());
        player.moveLeft();
        bomb.countDownBomb(); //bomb should be at LitBomb3
        assertEquals(bomb.getState().getClass(), droppedBomb.getState().getClass());
        assertEquals(2, droppedBomb.getCountdown());
        player.moveLeft();
        bomb.countDownBomb(); //bomb should be at ExplodingBomb
        assertEquals(bomb.getState().getClass(), droppedBomb.getState().getClass());
        assertEquals(1, droppedBomb.getCountdown());
        //assert enemy died
        assertNull(testDungeon.getEntity(13,3));
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
