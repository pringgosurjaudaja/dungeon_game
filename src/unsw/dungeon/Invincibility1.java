package unsw.dungeon;

public class Invincibility1 implements InvincibilityState {

	Invincibility invincibility;
	public Invincibility1(Invincibility invincibility) {
		this.invincibility = invincibility;
	}
	@Override
	public void countdown() {
		invincibility.setState(invincibility.getNoInvincibility());
	}

}
