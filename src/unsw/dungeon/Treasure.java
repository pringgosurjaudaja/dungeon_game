package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * 
 * Class Treasure
 *
 */
public class Treasure extends Entity implements Runnable{

	/**
	 * Constructor Treasure
	 * @param x Horizontal position of the Treasure
	 * @param y Vertical position of the Treasure
	 */
    public Treasure(int x, int y) {
        super(x, y);
    }

    /**
	 * 
	 * This method is when player interacts with Treasure.
	 * Player will always collect the treasure.
	 * @param p This is the player
	 * @return this entity (Treasure)
	 */
	@Override
	public Entity interact(Player p) {
		p.setTreasure(p.getTreasure()+1);
		p.getDungeon().removeEntity(this);
		return this;
	}

	@Override
	public boolean remove() {
		return true;
	}

	/**
	 * This is the thread to show image /shine.png after player collect the treasure
	 */
	@Override
	public void run() {
		this.getImage().setImage(new Image("/shine.png"));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.getImage().setImage(null);
		
	}
    
}