package unsw.dungeon;

public class Invincibility extends Entity {
	
	InvincibilityState state;
	private int countdown;
    public Invincibility(int x, int y) {
        super(x, y);
        state = new Invincibility1(this);
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
		p.setInvincibility(this);
		p.getDungeon().removeEntity(this);
		return this;
	}
	@Override
	public boolean remove() {
		return true;
	}
	
}
