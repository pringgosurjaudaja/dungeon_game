package unsw.dungeon;

public class NoInvincibility implements InvincibilityState {

	Invincibility invincibility;
	public NoInvincibility(Invincibility invincibility) {
		this.invincibility = invincibility;
	}

	@Override
	public void countdown() {
		System.out.println("Invincibility ran out");
	}

}
