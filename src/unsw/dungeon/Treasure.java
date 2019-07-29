package unsw.dungeon;

public class Treasure extends Entity {

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
    
}
