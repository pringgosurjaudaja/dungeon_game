package unsw.dungeon;

public class Invincibility1 implements InvincibilityState {

	Invincibility invincibility;
	Player player;
	public Invincibility1(Invincibility invincibility, Player player) {
		this.invincibility = invincibility;
		this.player = player;
	}
	
	@Override
	public void countdown() {
		invincibility.setState(new Invincibility2(invincibility, player));
	}

}
