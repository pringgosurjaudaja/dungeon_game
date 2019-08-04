package unsw.dungeon;

/**
 * 
 * Class Wall
 *
 */
public class Wall extends Entity {

	/**
	 * Constructor Wall
	 * @param x Horizontal position of the Wall
	 * @param y Vertical position of the Wall
	 */
    public Wall(int x, int y) {
        super(x, y);
    }

    /**
	 * 
	 * This method is when player interacts with Wall.
	 * Player can't move when there is a wall
	 * @param p This is the player
	 * @return this entity (Bomb)
	 */
	@Override
	public Entity interact(Player p) {
		System.out.println("Can't go forward, there is a wall!");
		return this;
	}

	@Override
	public boolean pass() {
		return false;
	}

}
