package unsw.dungeon;

import org.json.JSONObject;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Entity carry_ons;
    private int treasure = 0;
   

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
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
    		if(w instanceof Treasure) {
    			// collect treasure and remove treasureImage    			
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
        			removedEntity = w;
        			w.getImage().setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			} 
    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a bomb
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a key
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
    		if(w instanceof Locked_Door) {
    			// If it fits, change door to unlockImage and change entity
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a door
        			if(((Locked_Door) w).isOpen() == false) {	// if the door is locked
    					if(carry_ons instanceof Key) {		// if player carries a key
    						if(((Key) carry_ons).getId() == ((Locked_Door) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carry_ons = null;
    							((Locked_Door) w).openDoor();
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
            y().set(getY() - 1);	// move up
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
    		if(w instanceof Treasure) {
    			// collect treasure and remove treasureImage    			
/*    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
        			dungeon.getEntities().remove(w);
        			removedEntity = i;        			
        			treasure++;
    			} */
    			
      			// collect treasure and remove treasureImage    			
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a treasure
        			removedEntity = w;
        			w.getImage().setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			} 
    			
    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a bomb
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return ;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a key
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof Locked_Door) {
    			// If it fits, change door to unlockImage and change entity
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a door
        			if(((Locked_Door) w).isOpen() == false) {	// if the door is locked
    					if(carry_ons instanceof Key) {		// if player carries a key
    						if(((Key) carry_ons).getId() == ((Locked_Door) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carry_ons = null;
    							((Locked_Door) w).openDoor();
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
    		if(w instanceof Treasure) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a treasure
        			removedEntity = w;
        			w.getImage().setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			} 
    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a bomb
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a key
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof Locked_Door) {
    			// If it fits, change door to unlockImage and change entity
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a door
        			if(((Locked_Door) w).isOpen() == false) {	// if the door is locked
    					if(carry_ons instanceof Key) {		// if player carries a key
    						if(((Key) carry_ons).getId() == ((Locked_Door) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carry_ons = null;
    							((Locked_Door) w).openDoor();
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
    }

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
    		if(w instanceof Boulder) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a boulder
    				if(!((Boulder)(w)).checkBoulder(1 , 0 , dungeon.getEntities())){	//if there is another entity(other than switch) at the right of the boulder
    					return;
    				}else
    					moveBoulder(w , 1 , 0);
    			} 
    		}
    		if(w instanceof Treasure) {
    			// collect treasure and remove treasureImage    			
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a treasure
        			removedEntity = w;
        			w.getImage().setImage(null);
        			treasure++;
        			System.out.println("treasure = " + treasure);
    			} 
    		}
    		if(w instanceof Bomb) {
    			// Collect the bomb
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a bomb
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a key
    				if(carry_ons == null) {		// if player is not carrying anything
    					carry_ons = w;
    					removedEntity = w;
    					w.getImage().setImage(null);
    				} else {	// if player is carrying another entity
    					return;
    				}
    			}
    		}
    		if(w instanceof Locked_Door) {
    			// If it fits, change door to unlockImage and change entity
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a door
        			if(((Locked_Door) w).isOpen() == false) {	// if the door is locked
    					if(carry_ons instanceof Key) {		// if player carries a key
    						if(((Key) carry_ons).getId() == ((Locked_Door) w).getId() ) {	// if the key fits the door
    							System.out.println("Opening the door");
    							carry_ons = null;
    							((Locked_Door) w).openDoor();
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


    }
    
    // to drop the carry-ons by pressing SPACE on the keyboard
    // entities that can be dropped: key, bomb, sword?
    public int dropEntity() {
    	int removedEntity = -1;
    	
    	if(carry_ons instanceof Key) {
    		carry_ons = null;
    		removedEntity = -2;
    		
    	}
	 	

    	return removedEntity;
    }

}
