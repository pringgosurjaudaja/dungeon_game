package unsw.dungeon;

public class Treasure extends Entity {

    public Treasure(int x, int y) {
        super(x, y);
    }

	@Override
	public void interact(Player p) {
		p.setTreasure(p.getTreasure()+1);
	}
    
}
