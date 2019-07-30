package unsw.dungeon;

public class Exit extends Entity {

    public Exit(int x, int y) {
        super(x, y);
    }

	@Override
	public Entity interact(Player p) {
		System.out.println("EXIT");
		return this;
	}
    
}
