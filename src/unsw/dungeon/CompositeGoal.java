package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CompositeGoal implements Goals {

	List<Goals> goalList;
	String logicalOperator;
	public CompositeGoal(String logicalOperator, JSONArray subgoals) {
		goalList = new ArrayList<Goals>();
		this.logicalOperator = logicalOperator;
		goalGenerate(subgoals);
	}

	@Override
	public void getGoal() {
		for(Goals g: this.goalList) {
			System.out.println(g);
		}
	}
	
	public void addGoal(Goals g) {
		this.goalList.add(g);
	}
	public void goalGenerate(JSONArray goals) {
		System.out.println("ROPSFDS");
		for(Object o : goals) {
			String goal = ((JSONObject) o).getString("goal");
			if(goal.equals("OR") || goal.equals("AND")) {
				JSONArray subgoal = ((JSONObject) o).getJSONArray("subgoal");
				CompositeGoal cg = new CompositeGoal(goal, subgoal );
				this.goalList.add(cg);
				System.out.println("CG= "+ cg);
			}
			else {
				LeafGoal lg = new LeafGoal(goal);
				this.goalList.add(lg);
				System.out.println("goal leaf= "+ goal);
			}
		}
	}
}
