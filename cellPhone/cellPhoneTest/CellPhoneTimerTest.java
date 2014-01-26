package cellPhoneTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cellPhone.backend.Call;
import cellPhone.backend.CallType;
import cellPhone.backend.LineType;
import cellPhone.backend.PhoneNumber;

public class CellPhoneTimerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Call a = new Call(new PhoneNumber(LineType.HOME,"3333333333"),CallType.DIALED);
		int i =0;
		while(i<50){
			System.out.println(a.getTimer());
			i++;
		}
		
	}

}
