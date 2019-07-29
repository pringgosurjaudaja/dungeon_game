package unsw.dungeon;

public class Invincibility5 implements InvincibilityState {

	Invincibility invincibility;
	Player player;
	public Invincibility5(Invincibility invincibility, Player player) {
		this.invincibility = invincibility;
		this.player = player;
	}

	@Override
	public void countdown() {
		invincibility.setState(new NoInvincibility(invincibility, player));
	}

}
