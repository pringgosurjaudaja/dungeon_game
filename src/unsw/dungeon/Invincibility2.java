package unsw.dungeon;

public class Invincibility2 implements InvincibilityState {

	Invincibility invincibility;
	Player player;
	public Invincibility2(Invincibility invincibility, Player player) {
		this.invincibility = invincibility;
		this.player = player;
	}

	@Override
	public void countdown() {
		invincibility.setState(new Invincibility3(invincibility, player));
	}

}
