package unsw.dungeon;

public class Hound extends Entity {

	public Hound(int x, int y) {
		super(x, y);
	}
	
	@Override
	public Entity interact(Player p) {
		if(p.getInvincibility() != null) {
			//dead = true;
			p.getDungeon().addRemovedEntity(this);
			p.getDungeon().removeEntity(this);
			return p;
		}
		else {
			p.setDead(true);
		}
		return null;
	}

	@Override
	public boolean remove() {
		return true;
	}
	
	
}
