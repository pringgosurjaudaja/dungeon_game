package unsw.dungeon;

public class Invincibility extends Entity {
	
	InvincibilityState state;
	private int countdown;
	Player temp;
	
    public Invincibility(int x, int y) {
        super(x, y);
        state = new Invincibility1(this, temp);
        setCountdown(6);
        
    }
    public void countdownInvincibility() {
    	state.countdown();
    	countdown--;
    }
	public int getCountdown() {
		return countdown;
	}
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	public InvincibilityState getState() {
		return state;
	}
	public void setState(InvincibilityState state) {
		this.state = state;
	}
	
	@Override
	public Entity interact(Player p) {
		if(p.getCarryOns() == null) {
			p.setCarryOns(this);
			p.setInvincibility(this);
			p.getDungeon().removeEntity(this);
			state = new Invincibility1(this, p);
			return this;
		} else {
			System.out.println("Can't pickup invincibility, already carrying something");
			return null;
		}

	}
	
}
