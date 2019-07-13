package unsw.dungeon;

public class ExplodingBomb implements BombState {
	Bomb bomb;
	public ExplodingBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("EXPLODE!");

	}

	@Override
	public void countdown() {
		System.out.println("Bomb Exploded");
	}

}
