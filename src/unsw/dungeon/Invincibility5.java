package unsw.dungeon;

public class Invincibility5 implements InvincibilityState {

	Invincibility invincibility;
	public Invincibility5(Invincibility invincibility) {
		this.invincibility = invincibility;
	}

	@Override
	public void countdown() {
		invincibility.setState(new NoInvincibility(invincibility));
	}

}
