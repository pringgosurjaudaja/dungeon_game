package unsw.dungeon;

import javafx.scene.image.Image;

public class Treasure extends Entity implements Runnable{

    public Treasure(int x, int y) {
        super(x, y);
    }

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