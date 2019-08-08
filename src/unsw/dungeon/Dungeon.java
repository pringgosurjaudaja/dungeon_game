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
    private List<Entity> removedEntity;
    private Player player;
    private JSONObject goals;
    private Goals dungeonGoal;
    
    /**
     * Constructor Dungeon
     * @param width  This is the width of the dungeon
     * @param height This is the height of the dungeon
     * @param goals  This is the goals of the dungeon
     */
    public Dungeon(int width, int height, JSONObject goals) {
        this.width = width;
        this.height = height;
        this.goals = goals;
        this.entities = new ArrayList<>();
        this.removedEntity = new ArrayList<>();
        this.player = null;
        goalToJSON(goals);
        //System.out.println("GOAL "+goals);
    }

    public String getGoals() {
		return goals.toString();
	}

	public int getWidth() {
        return width;
    }

    public List<Entity> getRemovedEntity() {
		return removedEntity;
	}

	public void addRemovedEntity(Entity e) {
		this.removedEntity.add(e);
	}

	public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    
    /**
     * This method is called when bomb explodes.
     * If there is enemy/boulder/hound entity in the top, bottom, left or right of the exploding bomb, they will die.
     * @param b	This is the bomb.
     */
    public void explode(Bomb b) {
    	//if(b.exploded()) {
    	if(getEntity(b.getX()+1, b.getY()) instanceof Enemy || getEntity(b.getX()+1, b.getY()) instanceof Boulder || getEntity(b.getX()+1, b.getY()) instanceof Hound) {
    		System.out.println("KILL");
    		if(getEntity(b.getX()+1, b.getY()) instanceof Enemy) {
    			((Enemy) getEntity(b.getX()+1, b.getY())).isDead = true;
    		} else if(getEntity(b.getX()+1, b.getY()) instanceof Hound) {
    			((Hound) getEntity(b.getX()+1, b.getY())).isDead = true;
    		}
    		addRemovedEntity(getEntity(b.getX()+1, b.getY()));
    	}
    	if(getEntity(b.getX()-1, b.getY()) instanceof Enemy || getEntity(b.getX()-1, b.getY()) instanceof Boulder || getEntity(b.getX()-1, b.getY()) instanceof Hound) {
    		System.out.println("KILL");
    		if(getEntity(b.getX()-1, b.getY()) instanceof Enemy) {
    			((Enemy) getEntity(b.getX()-1, b.getY())).isDead = true;
    		} else if(getEntity(b.getX()-1, b.getY()) instanceof Hound) {
    			((Hound) getEntity(b.getX()-1, b.getY())).isDead = true;
    		}
    		addRemovedEntity(getEntity(b.getX()-1, b.getY()));
    	}
    	if(getEntity(b.getX(), b.getY()+1) instanceof Enemy || getEntity(b.getX(), b.getY()+1) instanceof Boulder || getEntity(b.getX(), b.getY()+1) instanceof Hound) {
    		System.out.println("KILL");
    		if(getEntity(b.getX(), b.getY()+1) instanceof Enemy) {
    			((Enemy) getEntity(b.getX(), b.getY()+1)).isDead = true;
    		} else if(getEntity(b.getX(), b.getY()+1) instanceof Hound) {
    			((Hound) getEntity(b.getX(), b.getY()+1)).isDead = true;
    		}
    		addRemovedEntity(getEntity(b.getX(), b.getY()+1));
    	}
    	if(getEntity(b.getX(), b.getY()-1) instanceof Enemy || getEntity(b.getX(), b.getY()-1) instanceof Boulder || getEntity(b.getX(), b.getY()-1) instanceof Hound) {
    		System.out.println("KILL");
    		if(getEntity(b.getX(), b.getY()-1) instanceof Enemy) {
    			((Enemy) getEntity(b.getX(), b.getY()-1)).isDead = true;
    		} else if(getEntity(b.getX(), b.getY()-1) instanceof Hound) {
    			((Hound) getEntity(b.getX(), b.getY()-1)).isDead = true;
    		}
    		addRemovedEntity(getEntity(b.getX(), b.getY()-1));
    	}
    		//addRemovedEntity(getEntity(b.getX()+1, b.getY()));
    		//removeEntity(getEntity(b.getX()+1, b.getY()));
    		//addRemovedEntity(getEntity(b.getX()-1, b.getY()));
    		//addRemovedEntity(getEntity(b.getX(), b.getY()+1));
    		//addRemovedEntity(getEntity(b.getX(), b.getY()-1));
    		System.out.println("bomb removed");
    		//removeEntity(b);
    		//entities.remove(b);
    	//}
    }
    
    /**
     * If the bomb is in the postExplode state, this method will remove it, otherwise it will
     * call countDownBomb() and check if the bomb is in explodingBomb state.
     */
    public void bombState() {
    	for(Entity e : entities) {
    		if(e instanceof Bomb && ((Bomb) e).isLit()) {
    			if(((Bomb) e).postExplode()) { 
    				entities.remove(e);
    				break;
    			}
    			((Bomb) e).countDownBomb();
    			if(((Bomb) e).exploded()) {
    				explode((Bomb) e);
    				break;
    			}
    		}
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
	
	/**
	 * This method gets all the entity in (x,y)
	 * @param x Horizontal position of the entity
	 * @param y Vertical position of the entity
	 * @return enti if there is an entity in (x,y)
	 * @return null if there is no entity in (x,y)
	 */
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
	
	public Goals getDungeonGoal() {
		return dungeonGoal;
	}

	public void setDungeonGoal(Goals dungeonGoal) {
		this.dungeonGoal = dungeonGoal;
	}

	
	public void removeEntity(Entity e) {
		this.entities.remove(e);
	}
	public void removeRemoved() {
		this.removedEntity.clear();
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
	
	/**
	 * Check whether the player is dead or not.
	 * @return true if player is dead
	 */
	public boolean checkDead() {
		for(Entity e: this.entities) {
			if(e instanceof Enemy) {
				if(e.getX() == player.getX() && e.getY() == player.getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method check whether the goals have been fulfilled.
	 * @param g Goals of the dungeon
	 * @return true if single goal is fulfilled or false if otherwise.
	 * @return checkAndGoalComposite((CompositeGoal) g) if the Goals are ARE
	 * @return checkOrGoalComposite((CompositeGoal) g) if the Goals are OR
	 */
	public boolean checkGoal(Goals g) {
		if(g instanceof LeafGoal) {
			if(g.getGoal().equals("treasure")) {
				return checkTreasureGoal();
			}
			else if(g.getGoal().equals("enemies")) {
				return checkEnemyGoal();
			}
			else if(g.getGoal().equals("exit")) {
				return checkExitGoal();
			}
			else if(g.getGoal().equals("boulders")) {
				return checkBoulderGoal();
			}
		} else {
			if(g.getGoal().equals("AND")) {
				return checkAndGoalComposite((CompositeGoal) g);
			}
			if(g.getGoal().equals("OR")) {
				return checkOrGoalComposite((CompositeGoal) g);
			}
		}
		return false;
	}
	
	/**
	 * This method check whether the goals have been fulfilled.
	 * @param g Goals of the dungeon
	 * @return complete. This is true if some of the goals are fulfilled, or false otherwise.
	 */
/*	public boolean checkOrGoalComposite(CompositeGoal g) {
		boolean complete = false;
		for(Goals go : g.getGoalList()) {
			if(go instanceof LeafGoal) {
				complete = checkGoal(go);
			}
			if(go instanceof CompositeGoal) {
				if(go.getGoal().equals("AND")) {
					return checkAndGoalComposite((CompositeGoal)go);
				} else {
					return checkOrGoalComposite((CompositeGoal)go);
				}
			}
			if(complete == true) {
				break;
			}
		}
		return complete;
	}*/
	
	public boolean checkOrGoalComposite(CompositeGoal g) {
		boolean complete = false;
		for(Goals go : g.getGoalList()) {
			if(go instanceof LeafGoal) {
				complete = checkGoal(go);
			}
			if(go instanceof CompositeGoal) {
				if(go.getGoal().equals("AND")) {
					complete = checkAndGoalComposite((CompositeGoal)go);
				}
				else {
					complete = checkOrGoalComposite((CompositeGoal)go);
				}
			}
			if(complete == true) {
				break;
			}
		}
		return complete;
	}
	
	/**
	 * This method check whether the goals have been fulfilled.
	 * @param g Goals of the dungeon
	 * @return complete. This is true if some all of the goals are fulfilled, or false otherwise.
	 */
/*	public boolean checkAndGoalComposite(CompositeGoal g) {
		boolean complete = false;
		for(Goals go : g.getGoalList()) {
			if(go instanceof LeafGoal) {
				complete = checkGoal(go);
			}
			if(go instanceof CompositeGoal) {
				if(go.getGoal().equals("AND")) {
					return checkAndGoalComposite((CompositeGoal)go);
				} else {
					return checkOrGoalComposite((CompositeGoal)go);
				}
			}
			if(complete == false) {
				break;
			}
		}
		return complete;
	}*/
	
	public boolean checkAndGoalComposite(CompositeGoal g) {
		boolean complete = false;
		for(Goals go : g.getGoalList()) {
			if(go instanceof LeafGoal) {
				complete = checkGoal(go);
			}
			if(go instanceof CompositeGoal) {
				if(go.getGoal().equals("AND")) {
					complete = checkAndGoalComposite((CompositeGoal)go);
				}
				else {
					complete = checkOrGoalComposite((CompositeGoal)go);
				}
			}
			if( complete == false) {
				break;
			}
		}
		return complete;
	}
	
	/**
	 * Check if enemy goal is fulfilled
	 * @return true if all enemies are dead, or false otherwise.
	 */
	public boolean checkEnemyGoal() {
		for(Entity e : this.entities) {
			if(e instanceof Enemy || e instanceof Hound) {
				return false;
			}
		}
		System.out.println("GOAL ENEMY HAS BEEN REACHED");
		return true;
	}
	
	/**
	 * Check if treasure goal is fulfilled
	 * @return true if all treasures are collected, or false otherwise.
	 */
	public boolean checkTreasureGoal() {
		for(Entity e : this.entities) {
			if(e instanceof Treasure) {
				return false;
			}
		}
		System.out.println("GOAL TREASURE HAS BEEN REACHED");
		return true;
	}
	
	/**
	 * Check if exit goal is fulfilled
	 * @return true if player exits
	 */
	public boolean checkExitGoal() {
		for(Entity e: this.entities) {
			if(e instanceof Exit) {
				if(e.getX() == player.getX() && e.getY() == player.getY()) {
					System.out.println("GOAL EXIT HAS BEEN REACHED");
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if boulder goal is fulfilled
	 * @return true if all switched are switched on, or false otherwise.
	 */
	public boolean checkBoulderGoal() {
		for(Entity e: this.entities) {
			if(e instanceof Switch) {
				if(!((Switch) e).isSwitched()) {
					return false;
				}
			}
		}
		System.out.println("GOAL BOULDER HAS BEEN REACHED");
		return true;
	}
}
