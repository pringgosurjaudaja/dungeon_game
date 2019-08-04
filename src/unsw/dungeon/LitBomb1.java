package unsw.dungeon;

/**
 * 
 * Class LitBomb1
 * One of the bomb states.
 *
 */
public class LitBomb1 implements BombState {
	Bomb bomb;
	
	/**
	 * Constructor LitBomb1
	 * @param bomb This is the bomb
	 */
	public LitBomb1(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("2 more state to exploding");
	}

	/**
	 * Change state of the bomb into LitBomb2
	 */
	@Override
	public void countdown() {
		bomb.setState(bomb.getLitBomb2());
		System.out.println("Bomb State = LitBomb2");
		//bomb.getImage().setImage(new Image("/bomb_lit_2.png"));

	}

}
