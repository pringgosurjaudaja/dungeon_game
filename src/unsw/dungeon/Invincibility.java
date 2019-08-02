package unsw.dungeon;

public class Invincibility extends Entity {
	
	private int countdown;
	Player temp;
	
    public Invincibility(int x, int y) {
        super(x, y);
        //state = new Invincibility1(this, temp);
        setCountdown(6);
        
    }
    public void countdownInvincibility() {
    	//state.countdown();
    	if(countdown == 0) {
    		temp.setCarryOns(null);
    	}
    	else {
    		countdown--;
    	}
    }
	public int getCountdown() {
		return countdown;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	
	
	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns() == null) {
			p.setCarryOns(this);
			p.setInvincibility(this);
			p.getDungeon().removeEntity(this);
			return this;
		} else {
			System.out.println("Can't pickup invincibility, already carrying something");
			return null;
		}

	}
	@Override
	public boolean remove() {
		return true;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Invincibility, Countdown:"+this.countdown;
	}
	
}
