package unsw.dungeon;

public class Invincibility3 implements InvincibilityState {

	Invincibility invincibility;
	Player player;
	public Invincibility3(Invincibility invincibility, Player player) {
		this.invincibility = invincibility;
		this.player = player;
	}

	@Override
	public void countdown() {
		invincibility.setState(new Invincibility4(invincibility, player));
	}

}
