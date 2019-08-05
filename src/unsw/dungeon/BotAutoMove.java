package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * This BotAutoMove class is used for the Enemy and Hound to move.
 *
 */
public class BotAutoMove extends Thread {
	private Dungeon dungeon;
	private Player player;
	private Entity enemy;
	private int timeDelay;

	/**
	 * This method is to run the thread and move the enemy and hound. 
	 * If the player is carrying sword/invincbility/bomb, enemy/hound will run away from player. If player is carrying nothing, enemy/hound will chase him.
	 * Hound moves faster than enemy.
	 * Player died when killed by Enemy once, or when killed by Hound thrice.
	 */
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

				Point nextMove = move.autoMove(getAllEntitiesCoordinates(),
						new Point(enemy.getX(), enemy.getY()),
						new Point(player.getX(), player.getY()),
						dungeon.getWidth(), dungeon.getHeight());

				// System.out.println(nextMove.x + " " + nextMove.y);

				if (nextMove != null) {
					enemy.x().set(nextMove.x);
					enemy.y().set(nextMove.y);
					
					if (enemy.getX() == player.getX() && enemy.getY() == player.getY()){
						if(enemy instanceof Enemy) {
							System.out.println("player lose");
							player.setDead(true);
						} else if (enemy instanceof Hound) {
							player.life--;
							System.out.println("Player's Life = " + player.life);
							if(player.life == 0) {
								System.out.println("player lose");
								player.setDead(true);
							}
						}	
					}
				} /*else {
					System.out.println("asd");
					
				}*/

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * This method gets all the coordinates of all entities except opened door, enemy and player in the dungeon.
	 * @return entityPoints This is the array list of the coordinates
	 */
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

	/**
	 * Constructor BotAutoMove
	 * @param dungeon This is the dungeon
	 * @param player  This is the player
	 * @param enemy   This is the type of enemy
	 * @param timeDelay This is the time delay which differs depending on the type of enemy
	 */
	public BotAutoMove(Dungeon dungeon, Player player , Entity enemy , int timeDelay) {
		this.dungeon = dungeon;
		this.player = player;
		this.enemy = enemy;
		this.timeDelay = timeDelay;

	}

}
