package unsw.dungeon;

public class Invincibility4 implements InvincibilityState {

	Invincibility invincibility;
	Player player;
	public Invincibility4(Invincibility invincibility, Player player) {
		this.invincibility = invincibility;
		this.player = player;
	}

	@Override
	public void countdown() {
		invincibility.setState(new Invincibility5(invincibility, player));
	}

}
