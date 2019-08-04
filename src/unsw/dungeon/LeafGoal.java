package unsw.dungeon;

/**
 * 
 * Class LeafGoal
 *
 */
public class LeafGoal implements Goals {

	private String goal;
	
	/**
	 * Constructor LeafGoal
	 * @param goal This is the goal of the dungeon
	 */
	public LeafGoal(String goal) {
		this.goal = goal;
	}

	@Override
	public String getGoal() {
		//System.out.println(goal);
		return goal;
	}

	@Override
	public String toString() {
		return "goal: "+ goal;
	}

}
