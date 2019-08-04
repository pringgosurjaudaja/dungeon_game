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
    private boolean dead;
    public int life = 3;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.dead = false;
    }

    /**
     * Check is player is dead
     * @return true if player is dead, and false otherwise
     */
    public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * If player is carrying bomb, this method calls countdown() depending on the state of the Bomb
	 */
	public void setBomb() {
      if(carryOns instanceof Bomb) {
        dungeon.addEntity(carryOns);
        ((Bomb) carryOns).getState().countdown();
      }
    }

    public void setTreasure(int treasure) {
		this.treasure = treasure;
	}

	public Invincibility getInvincibility() {
		return invincibility;
	}

	/**
	 * If there is invincibility, reduce its countdown.
	 * If it's countdown is 0, remove it from carryOns. 
	 */
    public void invincibilityChange() {
    	if(invincibility != null) {
    		if(invincibility.getCountdown() == 0) {
        		invincibility = null;
        		carryOns = null;
        	} else {
    			invincibility.countdownInvincibility();
    		}
    	}
    }

    /**
     * This method is used to kills enemies: Enemy and Hound, using sword.
     * If the sword is used, its durability decreases by one.
     */
	public void killEnemy() {
    	if(carryOns instanceof Sword && ((Sword) carryOns).getDurability() != 0) {
	    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
	    		Entity w = dungeon.getEntities().get(i);
	    		if(w instanceof Enemy || w instanceof Hound) {
	    			if(w.getY() == getY() + 1 && w.getX() == getX() || w.getY() == getY() - 1 && w.getX() == getX() || w.getY() == getY() && w.getX() == getX()-1 || w.getY() == getY() && w.getX() == getX()+1){	// if adjacent square is an enemy
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

	/**
	 * This method is to move player upwards if possible.
	 * It also calls interact method if there is an entity above the player.
	 */
    public void moveUp() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() - 1 && w.getX() == getX() && !(w instanceof Switch)){	// up is an entity and NOT a switch
    				removedEntity = w.interact(this);
    				if(removedEntity == null || removedEntity instanceof Wall)item = 1;
    				//break;
    			}
    	}

    	if((removedEntity != null && removedEntity.pass())|| item == 0) {
			if (getY() > 0) {
	            y().set(getY() - 1);// move up
			}
			invincibilityChange();
			dungeon.bombState();
			System.out.println("CARRY ONS: " + carryOns);
    	}
    	//remove entity from list of entities
    	if(removedEntity != null) {
    		if(removedEntity.remove()) {
    			dungeon.removeEntity(removedEntity);
        		dungeon.addRemovedEntity(removedEntity);
    		}
    	}
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }

		//System.out.println(removedEntity);
    }

	/**
	 * This method is to move player downwards if possible.
	 * It also calls interact method if there is an entity below the player.
	 */
    public void moveDown() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() + 1 && w.getX() == getX() && !(w instanceof Switch)){	// down is an entity and NOT a switch
    				removedEntity = w.interact(this);
    				if(removedEntity == null||removedEntity instanceof Wall)item = 1;
    				break;
    			}
    	}
    	if((removedEntity != null && removedEntity.pass())|| item ==0) {
			if (getY() > 0)
	            y().set(getY() + 1);// move up
				invincibilityChange();
				dungeon.bombState();
    	}
    	//remove entity from list of entities
    	if(removedEntity != null) {
    		if(removedEntity.remove()) {
    			dungeon.removeEntity(removedEntity);
        		dungeon.addRemovedEntity(removedEntity);
    		}
    	}
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }

		//System.out.println(removedEntity);
		//dungeon.addRemovedEntity(removedEntity);
    }

	/**
	 * This method is to move player to the left if possible.
	 * It also calls interact method if there is an entity on the left of the player.
	 */
    public void moveLeft() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() && w.getX() == getX()-1 && !(w instanceof Switch)){	// left is an entity and NOT a switch
    				removedEntity = w.interact(this);
    				if(removedEntity == null|| removedEntity instanceof Wall)item = 1;
    				break;
    			}
    	}
    	if((removedEntity != null && removedEntity.pass())|| item ==0) {
			if (getY() > 0)
	            x().set(getX() - 1);// move up
				invincibilityChange();
				dungeon.bombState();
    	}
    	//remove entity from list of entities
    	if(removedEntity != null) {
    		if(removedEntity.remove()) {
    			dungeon.removeEntity(removedEntity);
        		dungeon.addRemovedEntity(removedEntity);
    		}
    	}
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }

		//System.out.println(removedEntity);
		//dungeon.addRemovedEntity(removedEntity);
    }

	/**
	 * This method is to move player to the right if possible.
	 * It also calls interact method if there is an entity on the right of the player.
	 */
    public void moveRight() {
    	Entity removedEntity = null;
    	int item = 0;
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    			if(w.getY() == getY() && w.getX() == getX()+1 && !(w instanceof Switch)){	// right is an entity and NOT a switch
    				removedEntity = w.interact(this);
    				if(removedEntity == null||removedEntity instanceof Wall)item = 1;
    				break;
    			}
    	}
    	if((removedEntity != null && removedEntity.pass())|| item ==0) {
			if (getY() > 0)
	            x().set(getX() + 1);// move up
				invincibilityChange();
				dungeon.bombState();
    	}
    	//remove entity from list of entities
    	if(removedEntity != null) {
    		if(removedEntity.remove()) {
    			dungeon.removeEntity(removedEntity);
        		dungeon.addRemovedEntity(removedEntity);
    		}
    	}
		//if(invincibility != null&& invinNew == 0) invincibility.countdownInvincibility();
		if(dungeon.checkGoal(dungeon.getDungeonGoal())) {
        	System.out.println("Puzzle Completed");
        }

		//System.out.println(removedEntity);
		//dungeon.addRemovedEntity(removedEntity);
    }

    /**
     * This method is used to drop carry ons of type key, sword and bomb in current position of the player.
     * @return entity Returns either key, sword or bomb entity.
     */
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
	public void setInvincibility(Invincibility invincibility) {
		this.invincibility = invincibility;
	}
	public Dungeon getDungeon() {
		return dungeon;
	}

	

}
