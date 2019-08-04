package unsw.dungeon;

/**
 * 
 * Class Key
 *
 */
public class Key extends Entity {

	private int id;
	
	/**
	 * Constructor Key
	 * @param x Horizontal position of the Key
	 * @param y Vertical position of the Key
	 */
    public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * This method is when player interacts with Key.
	 * If the player is not carrying anything, he will then carry this (Key) entity,
	 * otherwise nothing happens because a player can only carry one item at a time.
	 * @param p This is the player
	 * @return Entity It returns either null or this entity (Key)
	 */
	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns() == null) {
			p.setCarryOns(this);
			p.getDungeon().removeEntity(this);
			System.out.println("CARRIED KEY WITH ID = " +this.id);
			return this;
		} else {
			System.out.println("Cannot pick up key, already carrying something");
			return null;
		}
	}

	@Override
	public Entity drop() {
		return this;
	}

	@Override
	public boolean remove() {
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Key, ID: "+this.id;
	}
    
    
}
