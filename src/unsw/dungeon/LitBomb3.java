package unsw.dungeon;

import javafx.scene.image.Image;

public class LitBomb3 implements BombState {
	Bomb bomb;
	public LitBomb3(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void explode() {
		System.out.println("Exploding");

	}

	@Override
	public void countdown() {
		bomb.setState(bomb.getExplodingBomb());
		System.out.println("Bomb State = ExplodingBomb");	
		bomb.getImage().setImage(new Image("/BombExploding.png"));

	}

}
