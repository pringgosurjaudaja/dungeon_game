package unsw.dungeon;

public class Invincibility extends Entity {

	private int duration;
	public Invincibility(int x, int y) {
		super(x, y);
		duration = 10;
		// TODO Auto-generated constructor stub
	}
	
	public void reduceDuration() {
		this.duration--;
	}

}
