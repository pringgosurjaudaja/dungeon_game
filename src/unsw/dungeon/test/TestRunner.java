package unsw.dungeon.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import unsw.dungeon.Entity;

public class TestRunner {

	   public static void main(String[] args) {
		   
		   ArrayList<Result> res = new ArrayList<Result>();
		   
		      Result result = JUnitCore.runClasses(US1Test.class);
		      Result result1 = JUnitCore.runClasses(US2Test.class);
		      res.add(result);
		      res.add(result1);
		      
		      for(Result r : res) {
			      for (Failure failure : r.getFailures()) {
				         System.out.println(failure.toString());
				      }
						
				      System.out.println(r.wasSuccessful());
		      }
				

		   }
	
}
