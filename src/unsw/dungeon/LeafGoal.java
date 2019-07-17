package unsw.dungeon;

public class LeafGoal implements Goals {

	private String goal;
	public LeafGoal(String goal) {
		this.goal = goal;
	}

	@Override
	public void getGoal() {
		System.out.println(goal);
	}

	@Override
	public String toString() {
		return "goal: "+ goal;
	}

}