package unsw.dungeon;

/**
 * 
 * Class UnlitBomb
 * One of the bomb states.
 *
 */
public class UnlitBomb implements BombState {
	Bomb bomb;
	
	/**
	 * Constructor UnlitBomb
	 * @param bomb This is the bomb
	 */
	public UnlitBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("3 more state to exploding");
	}

	/**
	 * Change state of the bomb into LitBomb1
	 */
	@Override
	public void countdown() {
		bomb.setState(bomb.getLitBomb1());
		System.out.println("Bomb State = LitBomb1");
		//bomb.getImage().setImage(new Image("/bomb_lit_1.png"));
	}

}
