package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

public class BotAutoMove extends Thread{
	private Dungeon dungeon;
	private Player player;
	private ArrayList<AutoMoveAction> enemies = new ArrayList<AutoMoveAction>();
	
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				
				for(AutoMoveAction a : enemies){
					a.autoMove(getAllEntitiesCoordinates());
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}

//	private ArrayList<Point> getAllEntitiesCoordinates(){
	public ArrayList<Point> getAllEntitiesCoordinates(){

		
		ArrayList<Point> entityPoints = new ArrayList<Point>();
		
    	for (int i = 0 ; i < dungeon.getEntities().size() ; i++) {
    		Entity w = dungeon.getEntities().get(i);
    		
    		if(!(w instanceof Enemy) && !(w instanceof Player)) {
    			Point a = new Point(w.getX(), w.getY());
    			entityPoints.add(a);
    			
    		}
    	}
		
		
		return entityPoints;
	}
	
	public BotAutoMove(Dungeon dungeon , Player player) {
		this.dungeon = dungeon;
		this.player = player;
		
		for(Entity e : dungeon.getEntities()){
			if(e instanceof Enemy) enemies.add((AutoMoveAction) e);
		}
	}
	

}
