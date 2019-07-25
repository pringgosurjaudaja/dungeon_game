package unsw.dungeon;

public class Invincibility extends Entity {
	
	InvincibilityState state;
	InvincibilityState invincibility5;
	InvincibilityState invincibility4;
	InvincibilityState invincibility3;
	InvincibilityState invincibility2;
	InvincibilityState invincibility1;
	InvincibilityState noInvincibility;
	private int countdown;
    public Invincibility(int x, int y) {
        super(x, y);
        invincibility5 = new Invincibility5(this);
        invincibility4 = new Invincibility4(this);
        invincibility3 = new Invincibility3(this);
        invincibility2 = new Invincibility2(this);
        invincibility1 = new Invincibility1(this);
        noInvincibility = new NoInvincibility(this);
        state = invincibility5;
        setCountdown(5);
        
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
	public InvincibilityState getInvincibility5() {
		return invincibility5;
	}
	public void setInvincibility5(InvincibilityState invincibility5) {
		this.invincibility5 = invincibility5;
	}
	public InvincibilityState getInvincibility4() {
		return invincibility4;
	}
	public void setInvincibility4(InvincibilityState invincibility4) {
		this.invincibility4 = invincibility4;
	}
	public InvincibilityState getInvincibility3() {
		return invincibility3;
	}
	public void setInvincibility3(InvincibilityState invincibility3) {
		this.invincibility3 = invincibility3;
	}
	public InvincibilityState getInvincibility2() {
		return invincibility2;
	}
	public void setInvincibility2(InvincibilityState invincibility2) {
		this.invincibility2 = invincibility2;
	}
	public InvincibilityState getInvincibility1() {
		return invincibility1;
	}
	public void setInvincibility1(InvincibilityState invincibility1) {
		this.invincibility1 = invincibility1;
	}
	public InvincibilityState getNoInvincibility() {
		return noInvincibility;
	}
	public void setNoInvincibility(InvincibilityState noInvincibility) {
		this.noInvincibility = noInvincibility;
	}
	@Override
	public void interact(Player p) {
		p.setInvincibility(this);
	}
	
}
