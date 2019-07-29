package unsw.dungeon;

public class Switch extends Entity {
	
	private boolean switched;
    public Switch(int x, int y) {
        super(x, y);
        switched = false;
    }
    
    public void boulderSwitch() {
    	if(switched ==false) this.switched = true;
    	if(switched == true) this.switched = false;
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

	@Override
	public Entity interact(Player p) {
		if(isSwitched()) return null;
		return this;
	}
    
}
