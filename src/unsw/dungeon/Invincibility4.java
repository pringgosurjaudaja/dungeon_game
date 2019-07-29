package unsw.dungeon;

public class Invincibility4 implements InvincibilityState {

	Invincibility invincibility;
	public Invincibility4(Invincibility invincibility) {
		this.invincibility = invincibility;
	}

	@Override
	public void countdown() {
		invincibility.setState(new Invincibility5(invincibility));
	}

}
