package unsw.dungeon;

public class Invincibility2 implements InvincibilityState {

	Invincibility invincibility;
	public Invincibility2(Invincibility invincibility) {
		this.invincibility = invincibility;
	}

	@Override
	public void countdown() {
		invincibility.setState(new Invincibility3(invincibility));
	}

}
