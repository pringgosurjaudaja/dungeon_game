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
	
	

}
