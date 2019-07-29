package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity {
	
    public Boulder(int x, int y) {
        super(x, y);
    }
    
    // check if there is an entity other than switch in (paramX, paramY). If there is return false.
    public boolean checkBoulder(int paramX , int paramY , List<Entity> listEntities){
    	
 
    	for(Entity e : listEntities){
    		System.out.println("X: "+ this.getX());
    		System.out.println("Y: "+ this.getY());
    		System.out.println("Xi: "+ e.getX());
    		System.out.println("Yi: "+ e.getY());
    		System.out.println(e instanceof Switch);
    		if ((this.getX() + paramX == e.getX() && this.getY() + paramY == e.getY() && !(e instanceof Switch))){
    			return true;
    		}
    	}
    	return false;
    }
    public void moveBoulder(int x, int y) {
    	System.out.println("ERGERFS");
    	this.y().set(getY()+y);
    	this.x().set(getX()+x);
    }
	@Override
	public Entity interact(Player p) {
		System.out.println("ERGERFS");
		if(p.getX() == this.getX()) {
			if(this.getY() == p.getY()-1) {
				if(checkBoulder(0,-1,p.getDungeon().getEntities())) {
					return null;
				}
				else {
					moveBoulder(0,-1);
					return null;
				}
			}
			if(this.getY() == p.getY()+1) {
				if(checkBoulder(0,1,p.getDungeon().getEntities())) {
					return null;
				}
				else {
					moveBoulder(0,1);
					return null;
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
					return null;
				}
			}
			if(this.getX() == p.getX()+1) {
				if(checkBoulder(1,0,p.getDungeon().getEntities())) {
					return null;
				}
				else {
					moveBoulder(1,0);
					return null;
				}
			}
		}
		
		return null;
	}
    

}
