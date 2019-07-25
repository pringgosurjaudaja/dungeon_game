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
    public void moveBoulder(int x, int y) {
    	this.y().set(getY()+y);
    	this.x().set(getX()+x);
    }
	@Override
	public Entity interact(Player p) {
		if(p.getX() == this.getX()) {
			if(this.getY() == p.getY()-1) {
				if(checkBoulder(0,-1,p.getDungeon().getEntities())) {
					return null;
				}
				else {
					moveBoulder(0,-1);
					return this;
				}
			}
			if(this.getY() == p.getY()+1) {
				if(checkBoulder(0,1,p.getDungeon().getEntities())) {
					return null;
				}
				else {
					moveBoulder(0,1);
					return this;
				}
			}
		}
		else if(p.getY()==this.getY()) {
			if(this.getX() == p.getX()-1) {
				if(checkBoulder(-1,0,p.getDungeon().getEntities())) {
					return null;
				}
				else {
					moveBoulder(-1,0);
					return this;
				}
			}
			if(this.getX() == p.getX()+1) {
				if(checkBoulder(1,0,p.getDungeon().getEntities())) {
					return null;
				}
				else {
					moveBoulder(1,0);
					return this;
				}
			}
		}
		
		return null;
	}
    

}
