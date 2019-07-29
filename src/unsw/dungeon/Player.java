package unsw.dungeon;

import org.json.JSONObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Entity carryOns;
    private int treasure = 0;
    Invincibility invincibility;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
    public void setBomb() {
      if(carryOns instanceof Bomb) {
        dungeon.addEntity(carryOns);
        ((Bomb) carryOns).getState().countdown();
      }
    }

    public Invincibility getInvincibility() {
		return invincibility;
	}
    
    public void invincibilityChange() {
    	if(invincibility != null) {
    		if(invincibility.getState()== null) {
        		invincibility = null;
        	} else {
    			invincibility.countdownInvincibility();
    		}
    	}
    }
    
	public void killEnemy() {
    	if(carryOns instanceof Sword && ((Sword) carryOns).getDurability()!= 0) {
	    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
	    		Entity w = dungeon.getEntities().get(i);
	    		if(w instanceof Enemy) {
	    			if(w.getY() == getY() + 1 && w.getX() == getX()||w.getY() == getY() - 1 && w.getX() == getX()||w.getY() == getY() && w.getX() == getX()-1||w.getY() == getY() && w.getX() == getX()+1){	// if adjacent square is an enemy
		    			dungeon.getEntities().remove(w);
		    			((Sword) carryOns).reduceDurability();
		    			System.out.println("Killed an enemy using sword. Sword durability becomes = " + ((Sword) carryOns).getDurability());
		    			if(((Sword) carryOns).getDurability() == 0) {	// if durability of sword becomes 0
		    				carryOns = null;		// drop carryOns
		    			}
		    			dungeon.addRemovedEntity(w);
	    			}
	    		}
	    	}
    	}
    	//return null;
    }
	
    public void moveUp() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// up is an entity
    				removedEntity = w.interact(this);
    				if(removedEntity == null)item = 1;
    				break;
    			}
    	}
    	
    	if(item == 0 || removedEntity instanceof Boulder|| removedEntity instanceof LockedDoor) {
			if (getY() > 0) {
	            y().set(getY() - 1);// move up
			}
				invincibilityChange();
				dungeon.bombState();
    	}
    	
    	//remove entity from list of entities
    	if(removedEntity != null && !(removedEntity instanceof Exit)) dungeon.removeEntity(removedEntity);
    	
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }
		
		System.out.println(removedEntity);
		dungeon.addRemovedEntity(removedEntity);
		
    }

    public void moveDown() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// down is an entity
    				removedEntity = w.interact(this);
    				if(removedEntity == null)item = 1;
    				break;
    			}
    	}
    	if(item == 1 ) {
    		//return null; 
    	} else if(item == 0 || removedEntity instanceof Boulder|| removedEntity instanceof LockedDoor) {
			if (getY() > 0)
	            y().set(getY() + 1);// move down
				invincibilityChange();
				dungeon.bombState();
    	}
    	
    	//remove entity from list of entities
    	if(removedEntity != null) dungeon.removeEntity(removedEntity);
    	
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }
		
		System.out.println(removedEntity);
		dungeon.addRemovedEntity(removedEntity);
    }

    
    public void moveLeft() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() && w.getX() == getX()-1){	// left is an entity
    				removedEntity = w.interact(this);
    				if(removedEntity == null)item = 1;
    				break;
    			}
    	}
    	
    	if(item == 1) {
    		//return null; 
    	} else if(item == 0 || removedEntity instanceof Boulder|| removedEntity instanceof LockedDoor) {
			if (getY() > 0)
	            x().set(getX() - 1);	// move left
				invincibilityChange();
				dungeon.bombState();
    	}
    	
    	//remove entity from list of entities
    	if(removedEntity != null) dungeon.removeEntity(removedEntity);
    	
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }
		
		System.out.println(removedEntity);
		dungeon.addRemovedEntity(removedEntity);
    }

    public void moveRight() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() && w.getX() == getX()+1){	// right is an entity
    				removedEntity = w.interact(this);
    				if(removedEntity == null)item = 1;
    				break;
    			}
    	}
    	
    	if(item == 1) {
    		//return null; 
    	} else if(item == 0 || removedEntity instanceof Boulder|| removedEntity instanceof LockedDoor) {
			if (getY() > 0)
	            x().set(getX() + 1);	// move right
				invincibilityChange();
				dungeon.bombState();
    	}
    	
    	//remove entity from list of entities
    	if(removedEntity != null && !(removedEntity instanceof Exit)) dungeon.removeEntity(removedEntity);
    	
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }
		
		System.out.println(removedEntity);
		dungeon.addRemovedEntity(removedEntity);
    }

    // to drop the carry-ons by pressing SPACE on the keyboard
    // entities that can be dropped: key, bomb, sword?

    //grid input removed for Milestone 2


    //public void dropEntity(GridPane grid) {

    public Entity dropEntity() {
    	if(carryOns != null) {
        	if(carryOns instanceof Key) {
        		Key key = new Key(getX(), getY(), ((Key) carryOns).getId());
        		System.out.println("Dropped a key with id: " + ((Key) carryOns).getId() + " at "+ getX() + getY());
        		//Image keyImage = new Image("/key.png");
                //key.setImage(new ImageView(new Image("/key.png")));
                //grid.add(key.getImage() , getX() , getY());

        		dungeon.addEntity(key);
        		carryOns = null;
        		return key;
        	}
        	if(carryOns instanceof Sword) {
        		Sword sword = new Sword(getX(), getY(), ((Sword) carryOns).getDurability());
        		System.out.println("Dropped a sword with durability: " + ((Sword) carryOns).getDurability() + " at "+ getX() + getY());
                //sword.setImage(new ImageView(new Image("/greatsword_1_new.png")));
                //grid.add(sword.getImage() , getX() , getY());

        		dungeon.addEntity(sword);
        		carryOns = null;
        		return sword;
        	}
        	if(carryOns instanceof Bomb) {
        		Bomb bomb = new Bomb(getX(), getY());
        		System.out.println("Dropped a bomb at "+ getX() + getY());
                //bomb.setImage(new ImageView(new Image("/bomb_unlit.png")));
                //grid.add(bomb.getImage() , getX() , getY());
                //grid.add(bomb.getImage() , getX() , getY());

        		dungeon.addEntity(bomb);
        		carryOns = null;
        		bomb.getState().countdown();
        		return bomb;
        	}

    	}
    	return null;

    }
    
	public Entity getCarryOns() {
		return carryOns;
	}
	
	public void setCarryOns(Entity carryOns) {
		this.carryOns = carryOns;
	}
	public int getDurability() {
		return ((Sword) carryOns).getDurability();
	}
	public int getTreasure() {
		return treasure;
	}
	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}
	public void setInvincibility(Invincibility invincibility) {
		this.invincibility = invincibility;
	}
	public Dungeon getDungeon() {
		return dungeon;
	}
	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

}
