package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LockedDoor extends Entity {

	private int id;
	private boolean isOpen = false;

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
    
	public void openDoor() {
		this.isOpen = true;
		//this.getImage().setImage(new Image("/open_door.png"));
	}
    
    
}
