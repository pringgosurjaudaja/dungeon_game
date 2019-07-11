package unsw.dungeon;

public class Locked_Door extends Entity {

	private int id;

    public Locked_Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
    
}
