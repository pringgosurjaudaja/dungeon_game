package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * Class LockedDoor
 *
 */
public class LockedDoor extends Entity {

	private int id;
	private boolean isOpen = false;

	/**
	 * Constructor LockedDoor
	 * @param x Horizontal position of the LockedDoor
	 * @param y Vertical position of the LockedDoor
	 * @param id This is the ID of the key
	 */
    public LockedDoor(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * If the door is opened, change its image to /open_door.png
	 */
	public void openDoor() {
		this.isOpen = true;
		this.getImage().setImage(new Image("/open_door.png"));
	}

	/**
	 * 
	 * This method is when player interacts with LockedDoor.
	 * If the player is carrying a key and the key fits this (LockedDoor) entity, 
	 * open the door, otherwise nothing happens.
	 * @param p This is the player
	 * @return Entity It returns either null or this entity (LockedDoor)
	 */
	@Override
	public Entity interact(Player p) {
		if(this.isOpen) {
			System.out.println("Door already opened");
			return this;
		} else {
			if(p.getCarryOns() instanceof Key && ((Key) p.getCarryOns()).getId() == this.getId()) {
				this.openDoor();	// open the door
				p.setCarryOns(null);
		    	String musicFile = "./sounds/UnlockDoor.wav";
				Media sound = new Media(new File(musicFile).toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.play();
				return this;
			} else {
				System.out.println("Key Doesn't Fit or Player is not carrying any key.");
			}
		}
		return null;
	}

	/**
	 * @return true if door is opened, and false otherwise.
	 */
	@Override
	public boolean pass() {
		if(isOpen) return true;
		return false;
	}


}
