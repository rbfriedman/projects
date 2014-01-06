package cellPhone.backend;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CellPhoneTestRunner {


	   public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(CellPhoneExecutionProcedure.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	   }
	} 


