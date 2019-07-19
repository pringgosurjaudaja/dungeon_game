package unsw.dungeon;

import javafx.scene.image.Image;

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
		//bomb.getImage().setImage(new Image("/bomb_lit_3.png"));

	}

}
