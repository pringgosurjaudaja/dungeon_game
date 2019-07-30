package unsw.dungeon;

public class Sword extends Entity {

	private int durability;

	public Sword(int x, int y, int durability) {
		super(x, y);
		this.durability = durability;
		//durability = 5;
		// TODO Auto-generated constructor stub
	}

	public void reduceDurability() {
		durability--;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns() == null) {
			p.setCarryOns(this);
			p.getDungeon().removeEntity(this);
			return this;
		} else {
			System.out.println("Can't pickup sword, already carrying something");
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
	

}
