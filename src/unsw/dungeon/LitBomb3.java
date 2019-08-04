package unsw.dungeon;

/**
 * 
 * Class LitBomb3
 * One of the bomb states.
 *
 */
public class LitBomb3 implements BombState {
	Bomb bomb;
	
	/**
	 * Constructor LitBomb3
	 * @param bomb This is the bomb
	 */
	public LitBomb3(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("Exploding");

	}

	/**
	 * Change state of the bomb into ExplodingBomb
	 */
	@Override
	public void countdown() {
		bomb.setState(bomb.getExplodingBomb());
		System.out.println("Bomb State = ExplodingBomb");	
		//bomb.getImage().setImage(new Image("/BombExploding.png"));

	}

}
