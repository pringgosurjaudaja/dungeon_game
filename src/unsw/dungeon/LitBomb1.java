package unsw.dungeon;

import javafx.scene.image.Image;

public class LitBomb1 implements BombState {
	Bomb bomb;
	public LitBomb1(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("2 more state to exploding");
	}

	@Override
	public void countdown() {
		bomb.setState(bomb.getLitBomb2());
		System.out.println("Bomb State = LitBomb2");
		//bomb.getImage().setImage(new Image("/bomb_lit_2.png"));

	}

}
