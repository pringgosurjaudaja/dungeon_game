package unsw.dungeon;

public class NoInvincibility implements InvincibilityState {

	Invincibility invincibility;
	Player player;
	public NoInvincibility(Invincibility invincibility, Player player) {
		this.invincibility = invincibility;
		this.player = player;
	}

	@Override
	public void countdown() {
		System.out.println("Invincibility ran out");
		player.setCarryOns(null);
		invincibility.setState(null);
	}

}
