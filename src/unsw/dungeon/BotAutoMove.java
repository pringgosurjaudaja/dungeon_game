package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

public class BotAutoMove extends Thread {
	private Dungeon dungeon;
	private Player player;
	private ArrayList<Entity> enemies = new ArrayList<Entity>();

	@Override
	public void run() {
		
		AutoMoveAction move = new EnemyChase();

		while (true) {
			try {
				Thread.sleep(1000);
				
				if(player.getCarryOns() instanceof Sword || player.getCarryOns() instanceof Invincibility || player.getCarryOns() instanceof Bomb) {
					move = new EnemyRunAway();
				} else {
					move = new EnemyChase();
				}

				for (Entity a : enemies) {
					// check if player bring potion or not
					// if player bring potion
					Point nextMove = move.autoMove(getAllEntitiesCoordinates(),
							new Point(a.getX(), a.getY()),
							new Point(player.getX(), player.getY()),
							dungeon.getWidth(), dungeon.getHeight());
					
					System.out.println(nextMove.x + " " + nextMove.y);
					
					if (nextMove != null) {
						a.x().set(nextMove.x);
						a.y().set(nextMove.y);
					}else{
						System.out.println("player lose");
					}

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * @Override public void run() { while(true){ try { Thread.sleep(1000);
	 * 
	 * for(AutoMoveAction a : enemies){ a.autoMove(getAllEntitiesCoordinates(),
	 * player); }
	 * 
	 * } catch (InterruptedException e) { e.printStackTrace(); } }
	 * 
	 * }
	 */

	// private ArrayList<Point> getAllEntitiesCoordinates(){
	public ArrayList<Point> getAllEntitiesCoordinates() {

		ArrayList<Point> entityPoints = new ArrayList<Point>();

		for (int i = 0; i < dungeon.getEntities().size(); i++) {
			Entity w = dungeon.getEntities().get(i);

			if (!(w instanceof Enemy) && !(w instanceof Player)) {
				Point a = new Point(w.getX(), w.getY());
				entityPoints.add(a);

			}
		}

		return entityPoints;
	}

	public BotAutoMove(Dungeon dungeon, Player player) {
		this.dungeon = dungeon;
		this.player = player;

		for (Entity e : dungeon.getEntities()) {
			if (e instanceof Enemy)
				enemies.add(e);
		}
	}

}
