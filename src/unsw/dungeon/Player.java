package unsw.dungeon;

import org.json.JSONObject;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Entity carryOns;
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

    public void grabItem(Entity e) {
    	dungeon.removeEntity(e);
    	carryOns = e;
    }
    public void grabTreasure(Treasure t) {
    	dungeon.removeEntity(t);
    	treasure++;
    	dungeon.checkTreasureGoal();
    }
    public void setBomb(Bomb b) {
    	if(carryOns instanceof Bomb) {
    		b.getState().countdown();
    	}
    }
    public int killEnemy() {
    	int removedEntity = -1;
    	if(true) {
	    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
	    		Entity w = dungeon.getEntities().get(i);
	    		
	    		
	    		if(w instanceof Enemy) {
	    			// collect treasure and remove treasureImage    			
	    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
	        		
	        				//System.out.println("sword: " + this.weapon);
		    				dungeon.getEntities().remove(w);
		        			removedEntity = i; 
	        			
	        			//treasure++;
	    			} 
	    		}
	    	}  
    	}
        return removedEntity;
    }
    public int moveUp() {
    	int removedEntity = -1;
    	 	
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    		
    		if(w instanceof Wall) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// cannot move up because there's a wall
    				return removedEntity;
    			}
    		}
    		else if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// up is an exit
    				System.out.println("You have successfully exit the dungeon.");
    			}
    		}
    		else if(w instanceof Sword) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
    				//weapon = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}
    		else if(w instanceof Boulder) {
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a boulder
    				if(!((Boulder)(w)).checkBoulder(0 , -1 , dungeon.getEntities())){	//if there is another entity(other than switch) above the boulder
    					return removedEntity;
    				}else
    					moveBoulder(w , 0 , -1);
    			} 
    		}
    		else if(w instanceof Treasure) {
    			// collect treasure and remove treasureImage    			
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
        			dungeon.getEntities().remove(w);
        			removedEntity = i;        			
        			treasure++;
    			} 
    		}
    		else if(w instanceof Enemy) {
    			// collect treasure and remove treasureImage    			
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a treasure
        			//dungeon.getEntities().remove(w);
    				System.out.println("Game Over");
        			removedEntity = i;        			
        			//treasure++;
    			} 
    		}
    		else if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getY() == getY() - 1 && w.getX() == getX()){	// if up is a key
    				carryOns = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}
    		
    	}

		if (getY() > 0)
            y().set(getY() - 1);	// move up
		        
		
		return removedEntity;
    }
    
    private void moveBoulder(Entity w, int x, int y) {
		//move boulder
    	w.y().set(w.getY() + y);
		w.x().set(w.getX() + x);
	}

    public int moveDown() {
    	int removedEntity = -1;
    	
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    		
    		if(w instanceof Wall) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// cannot move down because there's a wall
    				return removedEntity;
    			}
    		}
    		if(w instanceof Bomb && ((Bomb) w).isLit()) {
    			((Bomb) w).getState().explode();
    			((Bomb) w).getState().countdown();
    		}
    		else if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// down is an exit
    				// TO DO
    				System.out.println("You have successfully exit the dungeon.");
    			}
    		}
    		else if(w instanceof Enemy) {
    			// collect treasure and remove treasureImage    			
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
        			dungeon.getEntities().remove(w);
    				System.out.println("Game Over");
        			removedEntity = i;        			
        			//treasure++;
    			} 
    		}
    		else if(w instanceof Sword) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
    				//weapon = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}
    		else if(w instanceof Boulder) {
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a boulder
    				if(!((Boulder)(w)).checkBoulder(0 , 1 , dungeon.getEntities())){	//if there is another entity(other than switch) below the boulder
    					return removedEntity;
    				}else
    					moveBoulder(w , 0 , 1);
    			}
    		}
    		else if(w instanceof Treasure) {
    			// collect treasure and remove treasureImage    			
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if up is a treasure
        			dungeon.getEntities().remove(w);
        			removedEntity = i;        			
        			treasure++;
    			} 
    		}
    		else if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getY() == getY() + 1 && w.getX() == getX()){	// if down is a key
    				carryOns = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}

    	}
    	if(carryOns instanceof Invincibility) {
    		((Invincibility) carryOns).reduceDuration();
    	}
        if (getY() < dungeon.getHeight() - 1) // move down
            y().set(getY() + 1);
        
        return removedEntity;

    }

    public int moveLeft() {
    	int removedEntity = -1;
	 	
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    		
    		if(w instanceof Wall) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// cannot move left because there's a wall
    				return removedEntity;
    			}
    		}
    		if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// left is an exit
    				// TO DO
    				System.out.println("You have successfully exit the dungeon.");

    			}
    		}
    		if(w instanceof Sword) {
    			if(w.getY() == getY() && w.getX() == getX()-1){	// if up is a treasure
    				//weapon = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}
    		if(w instanceof Boulder) {
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a boulder
    				if(!((Boulder)(w)).checkBoulder(-1 , 0 , dungeon.getEntities())){	//if there is another entity(other than switch) at the left of the boulder
    					return removedEntity;
    				}else
    					moveBoulder(w , -1 , 0);
    			} 
    		}
    		if(w instanceof Treasure) {
    			// collect treasure and remove treasureImage    			
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if up is a treasure
        			dungeon.getEntities().remove(w);
        			removedEntity = i;        			
        			treasure++;
    			} 
    		}
    		if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getX() == getX() - 1 && w.getY() == getY()){	// if left is a key
    				carryOns = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}
    	}
        if (getX() > 0)	
            x().set(getX() - 1);	// move left
        
        return removedEntity;
    }

    public int moveRight() {
    	int removedEntity = -1;
	 	
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    		
    		if(w instanceof Wall) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// cannot move right because there's a wall
    				return removedEntity;
    			}
    		}
    		if(w instanceof Exit) {
    			// Exit the game TO DO
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// right is an exit
    				// TO DO
    				System.out.println("You have successfully exit the dungeon.");

    			}
    		}
    		if(w instanceof Sword) {
    			if(w.getY() == getY() && w.getX() == getX()+1){	// if up is a treasure
    				//weapon = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}
    		if(w instanceof Boulder) {
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a boulder
    				if(!((Boulder)(w)).checkBoulder(1 , 0 , dungeon.getEntities())){	//if there is another entity(other than switch) at the right of the boulder
    					return removedEntity;
    				}else
    					moveBoulder(w , 1 , 0);
    			} 
    		}
    		if(w instanceof Treasure) {
    			// collect treasure and remove treasureImage    			
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if up is a treasure
        			dungeon.getEntities().remove(w);
        			removedEntity = i;        			
        			treasure++;
    			} 
    		}
    		if(w instanceof Key) {
    			// Collect the key and remove keyImage
    			if(w.getX() == getX() + 1 && w.getY() == getY()){	// if right is a key
    				carryOns = w;
    				dungeon.getEntities().remove(w);
        			removedEntity = i;
    			}
    		}
    	}
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);	// move right
        
		return removedEntity;

    }
}
