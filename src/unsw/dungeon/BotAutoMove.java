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

	private ArrayList<Point> getAllEntitiesCoordinates(){
		
		
		
		return null;
	}
	
	public BotAutoMove(Dungeon dungeon , Player player) {
		this.dungeon = dungeon;
		this.player = player;
		
		for(Entity e : dungeon.getEntities()){
			if(e instanceof Enemy)enemies.add((AutoMoveAction) e);
		}
	}
	

}
