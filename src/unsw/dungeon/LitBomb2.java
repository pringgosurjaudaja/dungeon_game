package unsw.dungeon;

public class LitBomb2 implements BombState {
	Bomb bomb;
	public LitBomb2(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("1 more state to exploding");
	}

	@Override
	public void countdown() {
		bomb.setState(bomb.getLitBomb3());
		System.out.println("Bomb State = LitBomb3");		
	}

}
