package unsw.dungeon;

/**
 * 
 * Class Switch
 *
 */
public class Switch extends Entity {
	private boolean switched;
	
	/**
	 * Constructor Switch
	 * @param x Horizontal position of the Switch
	 * @param y Vertical position of the Switch
	 */
    public Switch(int x, int y) {
        super(x, y);
        switched = false;
    }

    /**
     * This method changes the status of switched.
     * This method is called when pushing boulder onto switch
     */
    public void boulderSwitch() {
    	if(switched == false) this.switched = true;
    	else if(switched == true) this.switched = false;
    	
    	System.out.println("SWITCH "+ switched);
    }


	public boolean isSwitched() {
		return switched;
	}

	@Override
	public boolean pass() {
		if(switched == false) return true;
		else if(switched == true) return false;
		else return false;
	}

	/**
	 * 
	 * This method is when player interacts with Switch.
	 * @param p This is the player
	 * @return null is the switch is switched and this (Switch) entity otherwise.
	 */
	@Override
	public Entity interact(Player p) {
		if(isSwitched()) return null;
		return this;
	}

}
