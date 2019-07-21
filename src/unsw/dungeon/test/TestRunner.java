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
		   
		   Result result1 = JUnitCore.runClasses(US1Test.class);
		   Result result2 = JUnitCore.runClasses(US2Test.class);
		   Result result6 = JUnitCore.runClasses(US6Test.class);
		   Result result7 = JUnitCore.runClasses(US7Test.class);
		   Result result8 = JUnitCore.runClasses(US8Test.class); 

		   res.add(result1);
		   res.add(result2);
		   res.add(result6);
		   res.add(result7);
		   res.add(result8); 

		   for(Result r : res) {
			   for (Failure failure : r.getFailures()) {
				   System.out.println(failure.toString());
			   }
			   System.out.println(r.wasSuccessful());
		   }
				
	   }
	
	   
}
