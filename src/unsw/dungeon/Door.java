package unsw.dungeon;

public class Door extends Entity {

	private int doorID;
	public Door(int x, int y, int ID) {
		super(x, y);
		doorID = ID;
	}

}
