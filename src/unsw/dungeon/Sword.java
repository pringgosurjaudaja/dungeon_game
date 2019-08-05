package unsw.dungeon;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * Class Sword
 *
 */
public class Sword extends Entity {

	private int durability;

	/**
	 * Constructor Sword
	 * @param x Horizontal position of the Sword
	 * @param y Vertical position of the Sword
	 * @param durability This is the durability of the sword, initially = 5
	 */
	public Sword(int x, int y, int durability) {
		super(x, y);
		this.durability = durability;
		//durability = 5;
	}

	public void reduceDurability() {
		durability--;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	/**
	 * 
	 * This method is when player interacts with Sword.
	 * If the player is not carrying anything, he will then carry this (Sword) entity,
	 * otherwise nothing happens because a player can only carry one item at a time.
	 * @param p This is the player
	 * @return Entity It returns either null or this entity (Sword)
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
			System.out.println("Can't pickup sword, already carrying something");
			return null;
		}
	}

	@Override
	public Entity drop() {
		return this;
	}
	@Override
	public boolean remove() {
		return true;
	}

	@Override
	public String toString() {
		return "Sword ,Durability: "+this.durability;
	}
	

}
