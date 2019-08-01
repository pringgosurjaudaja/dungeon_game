package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

public class BotAutoMove extends Thread {
	private Dungeon dungeon;
	private Player player;
	private Entity enemy;
	private int timeDelay;

	@Override
	public void run() {

		AutoMoveAction move = new EnemyChase();

		while (true) {
			try {
				Thread.sleep(timeDelay);

				if (player.getCarryOns() instanceof Sword
						|| player.getCarryOns() instanceof Invincibility
						|| player.getCarryOns() instanceof Bomb) {
					move = new EnemyRunAway();
				} else {
					move = new EnemyChase();
				}

				// check if player bring potion or not
				// if player bring potion
				Point nextMove = move.autoMove(getAllEntitiesCoordinates(),
						new Point(enemy.getX(), enemy.getY()),
						new Point(player.getX(), player.getY()),
						dungeon.getWidth(), dungeon.getHeight());

				// System.out.println(nextMove.x + " " + nextMove.y);

				if (nextMove != null) {
					enemy.x().set(nextMove.x);
					enemy.y().set(nextMove.y);
				} else {
					if(enemy instanceof Enemy) {
						System.out.println("player lose");
						System.exit(1);
					} else if (enemy instanceof Hound) {
						player.life--;
						System.out.println(player.life);
						if(player.life == 0) {
							System.out.println("player lose");
							System.exit(1);
						}
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

			if (w instanceof LockedDoor) {

				if (!((LockedDoor) w).isOpen()) {
					Point a = new Point(w.getX(), w.getY());

					entityPoints.add(a);

				}
			} else if (!(w instanceof Enemy) && !(w instanceof Player)) {

				Point a = new Point(w.getX(), w.getY());

				entityPoints.add(a);

			}
		}

		return entityPoints;
	}

	public BotAutoMove(Dungeon dungeon, Player player , Entity enemy , int timeDelay) {
		this.dungeon = dungeon;
		this.player = player;
		this.enemy = enemy;
		this.timeDelay = timeDelay;

	}

}