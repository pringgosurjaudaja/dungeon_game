package unsw.dungeon;

public class Invincibility3 implements InvincibilityState {

	Invincibility invincibility;
	public Invincibility3(Invincibility invincibility) {
		this.invincibility = invincibility;
	}

	@Override
	public void countdown() {
		invincibility.setState(new Invincibility4(invincibility));
	}

}
