package unsw.dungeon;

public class UnlitBomb implements BombState {
	Bomb bomb;
	public UnlitBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("3 more state to exploding");
	}

	@Override
	public void countdown() {
		bomb.setState(bomb.getLitBomb1());
		System.out.println("Bomb State = LitBomb1");
	}

}
