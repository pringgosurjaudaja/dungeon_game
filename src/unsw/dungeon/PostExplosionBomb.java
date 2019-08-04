package unsw.dungeon;

/**
 * 
 * Class PostExplosionBomb
 * One of the bomb states.
 *
 */
public class PostExplosionBomb implements BombState {
	Bomb bomb;
	
	/**
	 * Constructor PostExplosionBomb
	 * @param bomb This is the bomb
	 */
	public PostExplosionBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub

	}

	/**
	 * Change state of the bomb into null
	 */
	@Override
	public void countdown() {
		bomb.setState(null);

	}

}
