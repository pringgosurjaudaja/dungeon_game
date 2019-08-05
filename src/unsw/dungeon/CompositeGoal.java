package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * This is CompositeGoal class
 *
 */
public class CompositeGoal implements Goals {

	List<Goals> goalList;
	String logicalOperator;
	

	/**
	 * Constructor CompositeGoal
	 * @param logicalOperator either AND or OR
	 * @param subgoals	These are the goals
	 */
	public CompositeGoal(String logicalOperator, JSONArray subgoals) {
		goalList = new ArrayList<Goals>();
		this.logicalOperator = logicalOperator;
		goalGenerate(subgoals);
	}

	/**
	 * @return logicalOperator This is either AND or OR
	 */
	@Override
	public String getGoal() {
		for(Goals g: this.goalList) {
			System.out.println(g);
		}
		return logicalOperator;
	}
	
	public void addGoal(Goals g) {
		this.goalList.add(g);
	}
	
	public List<Goals> getGoalList() {
		return goalList;
	}

	public void setGoalList(List<Goals> goalList) {
		this.goalList = goalList;
	}

	/**
	 * Generate goals. The goals use composite pattern.
	 * @param goals This is the JSONArray of goals
	 */
	public void goalGenerate(JSONArray goals) {
		System.out.println("ROPSFDS");
		for(Object o : goals) {
			String goal = ((JSONObject) o).getString("goal");
			if(goal.equals("OR") || goal.equals("AND")) {
				JSONArray subgoal = ((JSONObject) o).getJSONArray("subgoals");
				CompositeGoal cg = new CompositeGoal(goal, subgoal);
				this.goalList.add(cg);
				System.out.println("CG = "+ cg);
			} else {
				LeafGoal lg = new LeafGoal(goal);
				this.goalList.add(lg);
				System.out.println("goal leaf = "+ goal);
			}
		}
	}
}
