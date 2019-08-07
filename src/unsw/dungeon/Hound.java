package unsw.dungeon;

/**
 * 
 * Hound Class
 *
 */
public class Hound extends Entity {

	boolean isDead = false;
	
	/**
	 * Constructor Hound
	 * @param x Horizontal position of the Hound
	 * @param y Vertical position of the Hound
	 */
	public Hound(int x, int y) {
		super(x, y);
		this.isDead = false;
	}
	
	/**
	 * This method is when player interacts with Hound.
	 * If the player is carrying invincibility potion, this (Hound) entity will die, 
	 * otherwise the player will die if it is killed by this (Hound) entity 3 times.
	 * @param p This is the player
	 * @return p if the Hound die or null if the player die.
	 */
	@Override
	public Entity interact(Player p) {
		if(p.getInvincibility() != null) {
			//dead = true;
			p.getDungeon().addRemovedEntity(this);
			p.getDungeon().removeEntity(this);
			this.isDead = true;
			return p;
		} else {
			//p.setDead(true);
			if(p.life == 0) {
				p.setDead(true);
			} else {
				p.life--;
				System.out.println("Player's Life = " + p.life);
			}
		}
		return null;
	}

	@Override
	public boolean remove() {
		return true;
	}
	
	
}
