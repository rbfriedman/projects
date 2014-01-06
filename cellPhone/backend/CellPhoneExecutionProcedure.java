package cellPhone.backend;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CellPhoneExecutionProcedure {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("in before class");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("in after class");
	}
	  @Before
	   public void before() {
	      System.out.println("in before");
	   }
		
	   //execute for each test, after executing test
	   @After
	   public void after() {
	      System.out.println("in after");
	   }



	@Test
	public void testOrderContacts() throws FileNotFoundException {
		AddressBook a = new AddressBook();
		System.out.println(a.toString());
		a.populateAddressBook();
		System.out.println(a.toString()  +"\n");
		Contact[] b = a.orderContacts();
		
		
		assertEquals(b.length,a.getNumContacts());

	
	}
	@Test
	public void testGetContactsByName() throws FileNotFoundException{
		AddressBook a = new AddressBook();
		a.populateAddressBook();
		/*Fails because Scanner reads extra space*/
		assertNotNull(a.getContactByName("Young Lineberry"));
		assertNotNull(a.getContactByName("Trey Michie"));
		assertNotNull(a.getContactByName("Alyse Fidler"));
		
	}
	
	@Test
	public void testMatchingContacts() throws Exception{
		AddressBook a = new AddressBook();
		a.populateAddressBook();
		Contact[] b = a.MatchingContacts("Royy", "Royy", a.orderContacts());
		assertEquals(a.getContactByName("Royal Rux"),b[0]);
		b = a.MatchingContacts("A", "A", a.orderContacts());
		assertTrue(b.length>4);
		b=a.MatchingContacts("Stef", "Stef", a.orderContacts());
		assertEquals(a.getContactByName("Stefani Cherry"),b[0]);
		
	}
	@Test 
	public void testGetContactsByPhone() throws FileNotFoundException{
		AddressBook a = new AddressBook();
		a.populateAddressBook();
		PhoneNumber f = new PhoneNumber(LineType.BUSINESS,"4445556666");
		PhoneNumber g = new PhoneNumber(LineType.MOBILE1,"3445556666");
		PhoneNumber h = new PhoneNumber(LineType.FAX,"2445556666");
		a.getAllContacts()[0].setPhoneNumber(g);
		JOptionPane.showMessageDialog(null, a.getAllContacts()[0].toString());
		a.getAllContacts()[0].setPhoneNumber(f);
		JOptionPane.showMessageDialog(null, a.getAllContacts()[0].toString());
		a.getAllContacts()[0].setPhoneNumber(h);
		JOptionPane.showMessageDialog(null, a.getAllContacts()[0].toString());
		Contact c =new Contact(a.getAllContacts()[0]);
		

		assertNotSame(c.toString(),a.getAllContacts()[0].toString());
		f = new PhoneNumber(LineType.BUSINESS,"4445557777");
		c.setName("GGGGG");
		System.out.println(c.toString()+"\n" +a.getAllContacts()[0].toString());
		assertNotSame(c,a.getAllContacts()[0]);
		
		assertNotSame(a.getAllContacts()[0].toString(),c.toString());
	}
	@Test
	public void testGetContactsByType() throws FileNotFoundException{
		AddressBook a = new AddressBook();
		Random v = new Random();
		a.populateAddressBook();
		for(int i =0; i <a.getNumContacts();i++){
			a.getAllContacts()[i].setContactType(ContactType.values()[v.nextInt(3)]);
		}
		Contact[] d = a.listByContactType(ContactType.FAMILY);
		
		for(int i =0; i <a.getNumContacts(); i++){
			System.out.println(a.getAllContacts()[i].toString() + a.getAllContacts()[i].getContactType());
		}
		System.out.println("Array Length: " +d.length);
		for(int i =0; i <d.length;i++){
			if(d[i]!= null){
				System.out.println(Integer.valueOf(i+1) +" " +d[i].toString());
			}
		}
	
		
		
		
	}
	@Test
	public void TestAddContact() throws FileNotFoundException{
		AddressBook a = new AddressBook();
		a.populateAddressBook();
		Integer oldCount = a.getNumContacts();
		Contact c = new Contact("Marsha Bamberger",ContactType.COLLEAGUE,new PhoneNumber(LineType.FAX,"2343455467")
		,new PhoneNumber(LineType.MOBILE1,"1111111111"));
		a.addContact(c);
		assertNotSame(oldCount,a.getNumContacts());
		Contact b =a.getContactByName("Marsha Bamberger");
		a.removeContact(b.getName());
		assertSame(oldCount,a.getNumContacts());
		assertNull(a.getContactByName("Marsha Bamberger"));
	}
	
	

}
