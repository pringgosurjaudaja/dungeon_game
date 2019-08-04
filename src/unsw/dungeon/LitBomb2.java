package unsw.dungeon;


/**
 * 
 * Class LitBomb2
 * One of the bomb states.
 *
 */

public class LitBomb2 implements BombState {
	Bomb bomb;
	
	/**
	 * Constructor LitBomb2
	 * @param bomb This is the bomb
	 */
	public LitBomb2(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("1 more state to exploding");
	}

	/**
	 * Change state of the bomb into LitBomb3
	 */
	@Override
	public void countdown() {
		bomb.setState(bomb.getLitBomb3());
		System.out.println("Bomb State = LitBomb3");
		//bomb.getImage().setImage(new Image("/bomb_lit_3.png"));

	}

}
