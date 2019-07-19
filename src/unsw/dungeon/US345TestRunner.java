package unsw.dungeon;
import java.util.ArrayList;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class US345TestRunner {

	public static void main(String[] args) {
		 ArrayList<Result> res = new ArrayList<Result>();
		   
	     Result result1 = JUnitCore.runClasses(US3Test.class);
	     Result result2 = JUnitCore.runClasses(US4Test.class);
	     Result result3 = JUnitCore.runClasses(US5Test.class);
	     res.add(result1);
	     res.add(result2);
	     res.add(result3);
	     for(Result r : res) {
		      for (Failure failure : r.getFailures()) {
			         System.out.println(failure.toString());
			      }
					
			      System.out.println(r.wasSuccessful());
	      }


	}

}
