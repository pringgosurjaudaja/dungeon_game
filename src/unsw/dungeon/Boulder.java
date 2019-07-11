package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity {
	
    public Boulder(int x, int y) {
        super(x, y);
    }
    
    // check if there is an entity other than switch in (paramX, paramY). If there is return false.
    public boolean checkBoulder(int paramX , int paramY , List<Entity> listEntities){
    	
 
    	for(Entity e : listEntities){
    		if (this.getX() + paramX == e.getX() && this.getY() + paramY == e.getY() && !(e instanceof Switch)){
    			return false;
    		}
    	}
    	
    	
    	return true;
    }


}
