package unsw.dungeon;

/**
 * 
 * Class Exit
 *
 */
public class Exit extends Entity {

	/**
	 * Constructor Exit
	 * @param x Horizontal position of the exit
	 * @param y Vertical position of the exit
	 */
    public Exit(int x, int y) {
        super(x, y);
    }

    /**
     * This method is when player interacts with Exit.
     * @return this Returns this (Exit) entity.
     */
	@Override
	public Entity interact(Player p) {
		System.out.println("EXIT");
		return this;
	}
    
}
