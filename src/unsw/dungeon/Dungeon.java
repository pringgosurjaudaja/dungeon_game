/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private JSONArray goals;
    private Goals dungeonGoal;
    public Dungeon(int width, int height, JSONObject goals) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        goalToJSON(goals);
        System.out.println(dungeonGoal.toString());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    public void explode(Bomb b) {
    	if(b.exploded()) {
    		removeEntity(getEntity(b.getX()+1, b.getY()));
    		removeEntity(getEntity(b.getX()-1, b.getY()));
    		removeEntity(getEntity(b.getX(), b.getY()+1));
    		removeEntity(getEntity(b.getX(), b.getY()-1));
    	}
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

	public List<Entity> getEntities() {
		return entities;
	}
	public Entity getEntity(int x, int y) {
		for(Entity enti: this.entities) {
			//System.out.println("x "+ enti.getX());
			//System.out.println("x s = "+ x);
			//System.out.println("y "+enti.getY());
			//System.out.println("y s = "+ y);
			if(enti.getX() == x && enti.getY()== y) {
				return enti;
			}
		}
		return null;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public JSONArray getGoals() {
		return goals;
	}

	public void setGoals(JSONArray goals) {
		this.goals = goals;
	}
	public void removeEntity(Entity e) {
		this.entities.remove(e);
	}
	public void goalToJSON(JSONObject goalJSON) {
		String goal = goalJSON.getString("goal");
		if(goal.equals("AND") || goal.equals("OR")) {
			CompositeGoal cg = new CompositeGoal(goal, goalJSON.getJSONArray("subgoals"));
			this.dungeonGoal = cg;
		}
		else {
			LeafGoal lg = new LeafGoal(goal);
			this.dungeonGoal = lg;
		}
	}
	public boolean checkGoal(Goals g) {
		if(g instanceof LeafGoal) {
			if(g.getGoal().equals("treasure")) {
				return checkTreasureGoal();
			}
			else if(g.getGoal().equals("enemies")) {
				return checkEnemyGoal();
			}
			else if(g.getGoal().equals("exit")) {

			}
			else if(g.getGoal().equals("boulders")) {

			}
		}
		else {
			if(g.getGoal().equals("AND")) {
				return checkAndGoalComposite((CompositeGoal) g);
			}
			if(g.getGoal().equals("OR")) {
				return checkOrGoalComposite((CompositeGoal) g);
			}
		}
		return false;
	}
	public boolean checkOrGoalComposite(CompositeGoal g) {
		boolean complete = false;
		for(Goals go : g.getGoalList()) {
			if(go instanceof LeafGoal) {
				complete = checkGoal(go);
			}
			if(go instanceof CompositeGoal) {
				if(go.getGoal().equals("AND")) {
					return checkAndGoalComposite((CompositeGoal)go);
				}
				else {
					return checkOrGoalComposite((CompositeGoal)go);
				}
			}
			if( complete == true) {
				break;
			}
		}
		return complete;
	}
	public boolean checkAndGoalComposite(CompositeGoal g) {
		boolean complete = false;
		for(Goals go : g.getGoalList()) {
			if(go instanceof LeafGoal) {
				complete = checkGoal(go);
			}
			if(go instanceof CompositeGoal) {
				if(go.getGoal().equals("AND")) {
					return checkAndGoalComposite((CompositeGoal)go);
				}
				else {
					return checkOrGoalComposite((CompositeGoal)go);
				}
			}
			if( complete == false) {
				break;
			}
		}
		return complete;
	}
	public boolean checkEnemyGoal() {
		for(Entity e : this.entities) {
			if(e instanceof Enemy) {
				return false;
			}
		}
		return true;
	}
	public boolean checkTreasureGoal() {
		for(Entity e : this.entities) {
			if(e instanceof Treasure) {
				return false;
			}
		}
		return true;
	}
}
