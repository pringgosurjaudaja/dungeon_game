package unsw.dungeon;

public class PostExplosionBomb implements BombState {
	Bomb bomb;
	public PostExplosionBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void countdown() {
		bomb.setState(null);

	}

}
