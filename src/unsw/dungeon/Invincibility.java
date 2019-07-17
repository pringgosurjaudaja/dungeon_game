package unsw.dungeon;

public class Invincibility extends Entity {

	private int duration;
	public Invincibility(int x, int y) {
		super(x, y);
		duration = 10;
	}
	
	public void reduceDuration() {
		this.duration--;
	}

}
