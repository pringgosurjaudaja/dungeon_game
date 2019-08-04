package unsw.dungeon;

/**
 * 
 * Class ExplodingBomb
 * One of the bomb states.
 *
 */
public class ExplodingBomb implements BombState {
	Bomb bomb;
	
	/**
	 * Constructor ExplodingBomb
	 * @param bomb
	 */
	public ExplodingBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("EXPLODE!");

	}

	/**
	 * Change state of the bomb into PostExplosionBomb.
	 */
	@Override
	public void countdown() {
		bomb.setState(bomb.getPostExplosionBomb());
		System.out.println("Bomb Exploded");
	}

}
