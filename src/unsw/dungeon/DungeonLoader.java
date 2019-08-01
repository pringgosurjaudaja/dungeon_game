package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    int keyId = 0;
    int doorId = 0;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");
        JSONObject goals = json.getJSONObject("goal-condition");

        Dungeon dungeon = new Dungeon(width, height, goals);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadGoal(Dungeon dungeon, JSONObject json){
    	JSONArray subgoals = new JSONArray();


    	String goal = json.getString("goal");
    	if(goal.equals("AND")) {
    		subgoals = json.getJSONArray("subgoals");
    	} else {
    		subgoals.put(goal);
    	}

    	//dungeon.setGoals(subgoals);

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
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "door":
        	LockedDoor door = new LockedDoor(x, y, doorId);
            onLoad(door);
            entity = door;
            doorId++;
        	break;
        case "key":
            Key key = new Key(x, y, keyId);
            onLoad(key);
            entity = key;
            keyId++;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch switch_ = new Switch(x, y);
            onLoad(switch_);
            entity = switch_;
            break;
        case "bomb":	// unlit bomb
        	Bomb bomb = new Bomb(x, y);
            onLoad(bomb);
            entity = bomb;
            break;
        //case "lit bomb":
        case "enemy":
        	Enemy enemy = new Enemy(x, y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
    	case "sword":
    		Sword sword = new Sword(x, y, 5);
    		onLoad(sword);
    		entity = sword;
    		break;
    	case "invincibility":
    		Invincibility invincibility = new Invincibility(x, y);
    		onLoad(invincibility);
    		entity = invincibility;
    		break;
        // TODO Handle other possible entities
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
        	break;
    	}

        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(LockedDoor door);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Switch switch_);
    public abstract void onLoad(Bomb bomb);	// unlit bomb
//  public abstract void onLoad(Lit bomb);
    public abstract void onLoad(Enemy enemy);
  	public abstract void onLoad(Sword sword);
    public abstract void onLoad(Invincibility invincibility);

 
   

}
