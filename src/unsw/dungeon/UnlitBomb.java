package unsw.dungeon;

import javafx.scene.image.Image;

public class UnlitBomb implements BombState {
	Bomb bomb;
	public UnlitBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("3 more state to exploding");
	}

	@Override
	public void countdown() {
		bomb.setState(bomb.getLitBomb1());
		System.out.println("Bomb State = LitBomb1");
		bomb.getImage().setImage(new Image("/bomb_lit_1.png"));
	}

}
