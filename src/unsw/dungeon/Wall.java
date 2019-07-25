package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public Entity interact(Player p) {
		System.out.println("Can't go forward, there is a wall!");
		return null;
	}

}
