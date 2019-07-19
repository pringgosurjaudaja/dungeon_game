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
    //private int invincibilityCounter = 0;
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
	public void killEnemy() {
    	if(carryOns instanceof Sword && ((Sword) carryOns).getDurability()!=0) {
	    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
	    		Entity w = dungeon.getEntities().get(i);
	    		if(w instanceof Enemy) {
	    			if(w.getY() == getY() + 1 && w.getX() == getX()||w.getY() == getY() - 1 && w.getX() == getX()||w.getY() == getY() && w.getX() == getX()-1||w.getY() == getY() && w.getX() == getX()+1){	// if up is a treasure
		    			dungeon.getEntities().remove(w);
		    			((Sword) carryOns).reduceDurability();
	    			}
	    		}
	    	}
    	}
    }
    public void moveUp() {
    	Entity removedEntity = null;

    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);

    		if(w instanceof Wall) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// cannot move up because there's a wall
    				return ;
    			}
    		}
    		if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// up is an exit
    				System.out.println("You have successfully exit the dungeon.");
    			}
    		}

    		if(w instanceof Boulder) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a boulder
    				if(!((Boulder)(w)).checkBoulder(0 , -1 , dungeon.getEntities())){	//if there is another entity(other than switch) above the boulder
    					return ;
    				}else
    					moveBoulder(w , 0 , -1);
    			}
    		}
        if(w instanceof Bomb && ((Bomb) w).isLit()) {
    			((Bomb) w).countDownBomb();
    			dungeon.explode((Bomb) w);
    		}
        if(w instanceof Sword) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
            if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
        if(w instanceof Enemy) {
    			// collect treasure and remove treasureImage
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
<<<<<<< HEAD
    				if(invincibility !=null && invincibility.getCountdown()>0) {
=======
    				if(invincibilityCounter > 0) {
>>>>>>> second
    					System.out.println("Invincible");
    				}
    				System.out.println("Game Over");
    			}
    		}
    		if(w instanceof Treasure) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
        			removedEntity = w;
        			//w.getImage().setImage(null);
        			w.setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			}
    		}
    		if(w instanceof Invincibility) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
        			removedEntity = w;
        			//w.getImage().setImage(null);
<<<<<<< HEAD
        			invincibility = (Invincibility) w;
=======
        			w.setImage(null);
        			invincibilityCounter = 10;
>>>>>>> second
    			}
    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a bomb
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a key
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
    		if(w instanceof LockedDoor) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a door
        			if(((LockedDoor) w).isOpen() == false) {	// if the door is locked
    					if(carryOns instanceof Key) {		// if player carries a key
    						if(((Key) carryOns).getId() == ((LockedDoor) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carryOns = null;
    							((LockedDoor) w).openDoor();
    						} else {
    							System.out.println("Key doesn't fit");
    							return;
    						}
    					}else {		// if player doesn't carries any key
    						System.out.println("Player doesn't carry any key");
    						return ;
    					}
    				}
    			}

    		}

    	}

    	//remove entity from list of entities
    	if(removedEntity != null)dungeon.getEntities().remove(removedEntity);

		if (getY() > 0)
            y().set(getY() - 1);// move up
			if(invincibility != null) invincibility.countdownInvincibility();
    }

    private void moveBoulder(Entity w, int x, int y) {
		//move boulder
    	w.y().set(w.getY() + y);
		w.x().set(w.getX() + x);
	}

    public void moveDown() {
    	Entity removedEntity = null;

    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);

    		if(w instanceof Wall) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// cannot move down because there's a wall
    				return;
    			}
    		}
    		if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// down is an exit
    				// TO DO
    				System.out.println("You have successfully exit the dungeon.");
    			}
    		}
    		if(w instanceof Boulder) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a boulder
    				if(!((Boulder)(w)).checkBoulder(0 , 1 , dungeon.getEntities())){	//if there is another entity(other than switch) below the boulder
    					return;
    				}else
    					moveBoulder(w , 0 , 1);
    			}
    		}
        if(w instanceof Bomb && ((Bomb) w).isLit()) {
    			((Bomb) w).countDownBomb();
    			dungeon.explode((Bomb) w);
    		}
        if(w instanceof Sword) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
            if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
        if(w instanceof Invincibility) {
			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
    			removedEntity = w;
    			//w.getImage().setImage(null);
<<<<<<< HEAD
    			invincibility = (Invincibility) w;
=======
    			w.setImage(null);
    			invincibilityCounter = 10;
>>>>>>> second
			}
		}
        if(w instanceof Enemy) {
    			// collect treasure and remove treasureImage
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
<<<<<<< HEAD
    				if(invincibility !=null && invincibility.getCountdown()>0) {
=======
    				if(invincibilityCounter > 0) {
>>>>>>> second
    					System.out.println("Invincible");
    				}
    				System.out.println("Game Over");}
    		}
    		if(w instanceof Treasure) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a treasure
        			removedEntity = w;
        			//w.getImage().setImage(null);
        			w.setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			}

    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a bomb
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a key
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof LockedDoor) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a door
        			if(((LockedDoor) w).isOpen() == false) {	// if the door is locked
    					if(carryOns instanceof Key) {		// if player carries a key
    						if(((Key) carryOns).getId() == ((LockedDoor) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carryOns = null;
    							((LockedDoor) w).openDoor();
    						} else {
    							System.out.println("Key doesn't fit");
    							return;
    						}
    					}else {		// if player doesn't carries any key
    						System.out.println("Player doesn't carry any key");
    						return ;
    					}
    				}
    			}

    		}

    	}

    	//remove entity from list of entities
    	if(removedEntity != null)dungeon.getEntities().remove(removedEntity);

        if (getY() < dungeon.getHeight() - 1) // move down
            y().set(getY() + 1);
        if(invincibility != null) invincibility.countdownInvincibility();
    }

    public void moveLeft() {
    	Entity removedEntity = null;

    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);

    		if(w instanceof Wall) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// cannot move left because there's a wall
    				return;
    			}
    		}
    		if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// left is an exit
    				// TO DO
    				System.out.println("You have successfully exit the dungeon.");

    			}
    		}
    		if(w instanceof Boulder) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a boulder
    				if(!((Boulder)(w)).checkBoulder(-1 , 0 , dungeon.getEntities())){	//if there is another entity(other than switch) at the left of the boulder
    					return;
    				}else
    					moveBoulder(w , -1 , 0);
    			}
    		}
        if(w instanceof Bomb && ((Bomb) w).isLit()) {
    			((Bomb) w).countDownBomb();
    			dungeon.explode((Bomb) w);
    		}
        if(w instanceof Sword) {
    			if(w.getY() == getY()  && w.getX() == getX()-1){	// if up is a treasure
            if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
        if(w instanceof Invincibility) {
			if(w.getY() == getY() && w.getX() == getX()-1){	// if up is a treasure
    			removedEntity = w;
    			//w.getImage().setImage(null);
<<<<<<< HEAD
    			invincibility = (Invincibility) w;
=======
    			w.setImage(null);
    			invincibilityCounter = 10;
>>>>>>> second
			}
		}
        if(w instanceof Enemy) {
    			// collect treasure and remove treasureImage
    			if(w.getY() == getY() && w.getX() == getX()-1){	// if up is a treasure
<<<<<<< HEAD
    				if(invincibility !=null && invincibility.getCountdown()>0) {
=======
    				if(invincibilityCounter > 0) {
>>>>>>> second
    					System.out.println("Invincible");
    				}
    				System.out.println("Game Over");    			}
    		}
    		if(w instanceof Treasure) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a treasure
        			removedEntity = w;
        			//w.getImage().setImage(null);
        			w.setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			}
    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a bomb
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a key
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof LockedDoor) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a door
        			if(((LockedDoor) w).isOpen() == false) {	// if the door is locked
    					if(carryOns instanceof Key) {		// if player carries a key
    						if(((Key) carryOns).getId() == ((LockedDoor) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carryOns = null;
    							((LockedDoor) w).openDoor();
    						} else {
    							System.out.println("Key doesn't fit");
    							return;
    						}
    					}else {		// if player doesn't carries any key
    						System.out.println("Player doesn't carry any key");
    						return ;
    					}
    				}
    			}

    		}
    	}

    	//remove entity from list of entities
    	if(removedEntity != null)dungeon.getEntities().remove(removedEntity);

        if (getX() > 0)
            x().set(getX() - 1);	// move left
        if(invincibility != null) invincibility.countdownInvincibility();    }

    public void moveRight() {
    	Entity removedEntity = null;

    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);

    		if(w instanceof Wall) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// cannot move right because there's a wall
    				return;
    			}
    		}
    		if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// right is an exit
    				// TO DO
    				System.out.println("You have successfully exit the dungeon.");

    			}
    		}
        if(w instanceof Bomb && ((Bomb) w).isLit()) {
    			((Bomb) w).countDownBomb();
    			System.out.println(((Bomb)w).getCountdown());
    			dungeon.explode((Bomb) w);
    		}
        if(w instanceof Sword) {
    			if(w.getY() == getY() && w.getX() == getX()+1){	// if up is a treasure
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
        if(w instanceof Invincibility) {
			if(w.getY() == getY() && w.getX() == getX()+1){	// if up is a treasure
    			removedEntity = w;
    			//w.getImage().setImage(null);
<<<<<<< HEAD
    			invincibility = (Invincibility) w;
=======
    			w.setImage(null);
    			invincibilityCounter = 10;
>>>>>>> second
			}
		}
        if(w instanceof Enemy) {
    			// collect treasure and remove treasureImage
    			if(w.getY() == getY() && w.getX() == getX()+1){	// if up is a treasure
    				if(invincibility !=null && invincibility.getCountdown()>0) {
    					System.out.println("Invincible");
    				}
    				System.out.println("Game Over");    			}
    		}
    		if(w instanceof Boulder) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a boulder
    				if(!((Boulder)(w)).checkBoulder(1 , 0 , dungeon.getEntities())){	//if there is another entity(other than switch) at the right of the boulder
    					return;
    				}else
    					moveBoulder(w , 1 , 0);
    			}
    		}
    		if(w instanceof Treasure) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a treasure
        			removedEntity = w;
        			//w.getImage().setImage(null);
        			w.setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			}
    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a bomb
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a key
    				if(carryOns == null) {		// if player is not carrying anything
    					carryOns = w;
    					removedEntity = w;
    					//w.getImage().setImage(null);
    					w.setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof LockedDoor) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a door
        			if(((LockedDoor) w).isOpen() == false) {	// if the door is locked
    					if(carryOns instanceof Key) {		// if player carries a key
    						if(((Key) carryOns).getId() == ((LockedDoor) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carryOns = null;
    							((LockedDoor) w).openDoor();
    						} else {
    							System.out.println("Key doesn't fit");
    							return;
    						}
    					}else {		// if player doesn't carries any key
    						System.out.println("Player doesn't carry any key");
    						return ;
    					}
    				}
    			}

    		}

    	}

    	//remove entity from list of entities
    	if(removedEntity != null)dungeon.getEntities().remove(removedEntity);


        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);	// move right
        if(invincibility != null) invincibility.countdownInvincibility();
    }

    // to drop the carry-ons by pressing SPACE on the keyboard
    // entities that can be dropped: key, bomb, sword?
<<<<<<< HEAD
    //grid input removed for Milestone 2
=======
    
    //public void dropEntity(GridPane grid) {
>>>>>>> second
    public void dropEntity() {
    	if(carryOns != null) {
        	if(carryOns instanceof Key) {
        		Key key = new Key(getX(), getY(), ((Key) carryOns).getId());
        		System.out.println("Dropped a key with id: " + ((Key) carryOns).getId() + " at "+ getX() + getY());
<<<<<<< HEAD
        		//Image keyImage = new Image("/key.png");
=======
>>>>>>> second
                //key.setImage(new ImageView(new Image("/key.png")));
                //grid.add(key.getImage() , getX() , getY());
                
        		dungeon.addEntity(key);
        		carryOns = null;
        		}
        	if(carryOns instanceof Sword) {
        		Sword sword = new Sword(getX(), getY(), ((Sword) carryOns).getDurability());
        		System.out.println("Dropped a sword with durability: " + ((Sword) carryOns).getDurability() + " at "+ getX() + getY());
                //sword.setImage(new ImageView(new Image("/greatsword_1_new.png")));
                //grid.add(sword.getImage() , getX() , getY());
    
        		dungeon.addEntity(sword);
        		carryOns = null;
        	}
        	if(carryOns instanceof Bomb) {
        		Bomb bomb = new Bomb(getX(), getY());
        		System.out.println("Dropped a bomb at "+ getX() + getY());
                //bomb.setImage(new ImageView(new Image("/bomb_unlit.png")));
<<<<<<< HEAD
               // grid.add(bomb.getImage() , getX() , getY());
=======
                //grid.add(bomb.getImage() , getX() , getY());
>>>>>>> second
    
        		dungeon.addEntity(bomb);
        		carryOns = null;
        		bomb.getState().countdown();
        	}
        	
    	}

    }
	public Entity getCarryOns() {
		return carryOns;
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

}
