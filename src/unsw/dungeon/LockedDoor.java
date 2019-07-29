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

	@Override
	public Entity interact(Player p) {
		if(this.isOpen) {
			System.out.println("Door already opened");
			return this;
		}
		else {
			if(p.getCarryOns() instanceof Key) {
				if(((Key) p.getCarryOns()).getId()== this.getId()) {
					this.openDoor();
					return this;
				}
				
			}
			else {
				System.out.println("Key Doesn't Fit");
			}
		}
		return null;
	}

	@Override
	public boolean pass() {
		if(isOpen) return true;
		return false;
	}
    
    
}
