package unsw.dungeon;

public class Key extends Entity {

	private int id;
	
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

	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns() == null) {
			p.setCarryOns(this);
			p.getDungeon().removeEntity(this);
			return this;
		}
		else {
			System.out.println("Cannot pick up key, already carrying something");
			return null;
		}
	}

	@Override
	public Entity drop() {
		return this;
	}
    
    
}
