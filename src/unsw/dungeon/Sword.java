package unsw.dungeon;

public class Sword extends Entity {

	private int durability;
	
	public Sword(int x, int y) {
		super(x, y);
		durability = 5;
		// TODO Auto-generated constructor stub
	}
	
	public void reduceDurability() {
		durability--;
	}

	public int getDurability() {
		return durability;
	}

}
