package unsw.dungeon;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * class Bomb
 *
 */
public class Bomb extends Entity {

	BombState unlitBomb;
	BombState litBomb1;
	BombState litBomb2;
	BombState litBomb3;
	BombState explodingBomb;
	BombState state = unlitBomb;
	BombState postExplosionBomb;
	private int countdown;
	
	/**
	 * Constructor Bomb
	 * Create new objects for each bomb state, set initial count down of bomb to 4 and set the bomb to be in unlit state initially.
	 * @param x Horizontal position of the bomb
	 * @param y Vertical position of the bomb
	 */
	public Bomb(int x, int y) {
		super(x, y);
		unlitBomb = new UnlitBomb(this);
		litBomb1 = new LitBomb1(this);
		litBomb2 = new LitBomb2(this);
		litBomb3 = new LitBomb3(this);
		explodingBomb = new ExplodingBomb(this);
		postExplosionBomb = new PostExplosionBomb(this);
		setCountdown(4);
		this.state = unlitBomb;
	}
	
	public int getCountdown() {
		return countdown;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	
	/**
	 * Calls countdown() and explode() method of the bomb depending on their state and 
	 * reduce the count down by 1.
	 */
	public void countDownBomb() {
		state.countdown();
		state.explode();
		countdown--;
	}
	public BombState getUnlitBomb() {
		return unlitBomb;
	}
	public void setUnlitBomb(BombState unlitBomb) {
		this.unlitBomb = unlitBomb;
	}
	public BombState getLitBomb1() {
		return litBomb1;
	}
	public void setLitBomb1(BombState litBomb1) {
		this.litBomb1 = litBomb1;
	}
	public BombState getLitBomb2() {
		return litBomb2;
	}
	public void setLitBomb2(BombState litBomb2) {
		this.litBomb2 = litBomb2;
	}
	public BombState getLitBomb3() {
		return litBomb3;
	}
	public void setLitBomb3(BombState litBomb3) {
		this.litBomb3 = litBomb3;
	}
	public BombState getExplodingBomb() {
		return explodingBomb;
	}
	public void setExplodingBomb(BombState explodingBomb) {
		this.explodingBomb = explodingBomb;
	}
	public BombState getState() {
		return state;
	}
	public void setState(BombState state) {
		this.state = state;
	}
	
	/**
	 * Check whether the bomb is lit or not
	 * @return true when it's lit or false when it is unlit
	 */
	public boolean isLit() {
		if(state == unlitBomb) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check whether the bomb is exploding or not
	 * @return true when it's exploding or false otherwise
	 */
	public boolean exploded() {
		if(state == explodingBomb) {
			return true;
		}
		return false;
	}
	public boolean postExplode() {
		if(state == postExplosionBomb) return true;
		return false;
	}
	public BombState getPostExplosionBomb() {
		return postExplosionBomb;
	}
	public void setPostExplosionBomb(BombState postExplosionBomb) {
		this.postExplosionBomb = postExplosionBomb;
	}
	
	/**
	 * 
	 * This method is when player interacts with Bomb.
	 * If the player is not carrying anything, he will then carry this (Bomb) entity,
	 * otherwise nothing happens because a player can only carry one item at a time.
	 * @param p This is the player
	 * @return Entity It returns either null or this entity (Bomb)
	 */
	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns() == null) {
			p.setCarryOns(this);
			p.getDungeon().removeEntity(this);
			String musicFile = "./sounds/PickUp.wav";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
			return this;
		} else {
			System.out.println("Couldn't pick up bomb, already carrying something");
			return null;
		}
	}
	
	/**
	 * This method will call countDownBomb()
	 */
	@Override
	public Entity drop() {
		countDownBomb();
		return this;
	}
	@Override
	public boolean remove() {
		return true;
	}
	@Override
	public String toString() {
		return "Bomb";
	}
	
	
}
