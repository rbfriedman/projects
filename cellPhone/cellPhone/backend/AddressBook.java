package cellPhone.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;



public class AddressBook implements Serializable{

	private Contact[] contacts;
	private int numContacts;
	private int capacity = 500;

	public AddressBook(){
		//Most phones have limited storage space for contacts
		contacts = new Contact[capacity];
		numContacts =0;
	}
	public Contact[] getAllContacts(){
		Contact[] c = new Contact[this.getNumContacts()];
		int i =0;
		while(i <c.length){
			c[i] = this.contacts[i];
			i++;
		}
		return c;
	}
	public boolean addContact(Contact c){
		if(getContactByName(c.getName()) == null){
			contacts[numContacts++] = c;
			return true;
		}
		return false;
		
	}
	public int getNumContacts(){
		return this.numContacts;
	}
	private void removeContact(Contact c){
		Contact found = null;
		for(int i=0; i <this.getNumContacts();i++){
			if(found!=null){
				contacts[i-1] = contacts[i];
			}
			if(this.contacts[i].getName().equals(c.getName())){
				found = contacts[i];
			}
			
		}
		numContacts-=1;
	}
	public boolean removeContact(String name){
		if(getContactByName(name)!= null){
			removeContact(getContactByName(name));
			return true;
		}
		return false;
	}
	/*
	 *    public void removeItem(String description)throws Exception{
	        int elementNumber = findItem(description);
	        if (elementNumber == -1)
	        	throw new NotFoundException();
	        else
	        {
	        	//assume you wish to keep the existing order in the list
	        	for (int j= elementNumber; j<numItems-1;j++){
	        		items[j] = items[j+1];
	        	}
	        	
	        	numItems--;   //one less item in the list
	        }
   }
	 */
	public void removePhoneNumber(PhoneNumber phoneNum, Contact aContact){
		if (aContact.getPhoneNumbers().size() > 1){
		for(PhoneNumber p: aContact.getPhoneNumbers()){
			if(p.equals(phoneNum)){
				aContact.removePhoneNumber(phoneNum);
			}
		}
		}
		else{//CHANGE TO DELETECONTACT EXCEPTION
			throw new InputMismatchException("Positive number required");//Exception asking if you would like to remove the contact
		}
	}
	public Contact getContactByName(String aName){
		
		Contact foundContact = null;
		for(int i=0; i <this.numContacts; i++){
			if(contacts[i].getName().trim().equalsIgnoreCase(aName)){
			
				foundContact = contacts[i];
				return foundContact;
			
			}
		}
		return null;
		
		
	}
	public Contact[] orderContacts(){
		Contact[] orderedContacts = new Contact[this.numContacts];
		for(int i=0;i <this.numContacts;i++){
			orderedContacts[i] = this.contacts[i];
		}
		int placeNum =0;
		Contact aContact = orderedContacts[0];
		for(int i=0; i <orderedContacts.length-1; i++){
			
			for(int j=i+1; j <orderedContacts.length;j++){
				placeNum = i;
				aContact = orderedContacts[i];
				if(orderedContacts[i].getName().compareTo(orderedContacts[j].getName())>0){
					placeNum = j;
					aContact = orderedContacts[j];
					
				}
				if(placeNum!= i){
					orderedContacts[placeNum] = orderedContacts[i];
					orderedContacts[i] = aContact;
					
					
				}
				
			}
				
		}
		return orderedContacts;
	}
	/*Recursive Calls*/
	public Contact[] MatchingContacts(String aString, String originalString,Contact[] contactList) throws InputMismatchException{
		/*Assumes ascending order*/
		/*First check if it begins with a letter*/
		
		int placemark =0;
		if(aString.equals(null)||aString.equals("")){
			throw new InputMismatchException();
		}
		else{
			//When checking for a matching string, after checking each letter against the contents of
			//cotact list, your subarray becomes smaller. 
			//References to the original string and the current string are included s parameters so that
			//the letter we will be checking against can be verified at each recursive call
			placemark = originalString.indexOf(aString.toCharArray()[0]);
		}
		Character aChar = aString.toCharArray()[0];
		contactList = resize(contactList.length,null,contactList);
		Contact[] contactSubList = new Contact[contactList.length];
		int k=0;
		/*Traversing the array: the input will always be an ordered Contact list, which means that
		 * the list was cut to size and that there should be no oppurtunity for an NPE. The list will
		 *  be further pared down.
		 * The final recursive call is when no change has been made to the previous array, so the array is cut to
		 * size
		 */
		for(int i=0; i <contactList.length; i++){
			if(contactList[i].getName().toCharArray()[placemark] == aChar){
				contactSubList[k++] = contactList[i];
				/*No reason to keep searching an alphabetized array**/
				
			}
			//This clause DEPENDS on the assertion that the contact list is ordered
			// and that the loop would only reach the else if, if there were no more letters to match
			//aChar
			else if(k>0)
				break;
			
		}
		/*contactSubList now points to a an array that is cut to size*/
		/*Because of the postfix, k is actually one larger than its contents
		 * which is exactly what the resize method was designed to accept*/
		
		if(contactList.length == k || aString.length() == 1){
			//indicates no change has been made OR there are no further checks to be made
			
			return resize(contactSubList.length,k,contactSubList);
		}
		else if(contactSubList.length ==0){
			throw new InputMismatchException();
		}
		//with each recursive call aString returns a smaller array
		
		return MatchingContacts(aString.substring(1),originalString,contactSubList);
		
	}
	public Contact getContact(String phoneNum) {
		/*Contact[] aContact = new Contact[1];
		if(this.listByPhoneNumber(phoneNum).length ==1){
			aContact = this.listByPhoneNumber(phoneNum);
			return aContact[0];
		}
		*/
		Contact foundContact = null;
		for(int i=0; i <this.numContacts;i++){
			for(int j =0; j <contacts[i].getPhoneNumbers().size(); j++){
			if(contacts[i].getPhoneNumbers().get(j).toString().equals(phoneNum.trim())){
				
				foundContact = contacts[i];
				return foundContact;
			}
			}
		}
		return null;
	}
	//As a number is typed contact with matching numbers are retrieved
	public Contact[] listByPhoneNumber(String phoneNumPart){
		Contact[] listContacts = new Contact[5];
		int listNum =0;
	    for(int i=0; i <this.contacts.length;i++){
	    	PhoneNumber[] tempPhoneNums = (PhoneNumber[])this.contacts[i].getPhoneNumbers().toArray();
	    	for(int j =0; j < tempPhoneNums.length; j++){
	    		if(tempPhoneNums[j].getPhoneNum().contains(phoneNumPart) ){
		    		
		    		if(listNum == listContacts.length){
		    			listContacts =resize(listNum, listNum *2, listContacts);
		    		}
		    		
		    		listContacts[listNum++] = contacts[i];
		    		break;//There is no need to add a contac ttwice, even if the numbers both have phoneNumPart
		    		
		    	}
	    	}
	    	
	    }
		return listContacts;
	}
	public void populateAddressBook() throws FileNotFoundException{
		String num =JOptionPane.showInputDialog("How many contacts would you like in a cellPhone's addressbook? You can have 5 -25");
		PhoneNumber aPhone;
		Scanner read = new Scanner(new File("Contacts.txt"));
		read.useDelimiter("\r\n");
		Contact temp;
		while(read.hasNext() && numContacts <Integer.valueOf(num)){
			String name ="";
			aPhone = PhoneNumber.generateRandomPhone();
			StringTokenizer f = new StringTokenizer(read.next());
			
			while(f.hasMoreTokens()){
			

				name+= (name.length()>0)?" " +f.nextToken().trim():f.nextToken();
			
			}
			name = name.trim();
			temp = new Contact(name,aPhone);
			this.addContact(temp);
			
		}
		initializeContactType();
		
	}
	private void initializeContactType(){
		Random generator = new Random();
		
		for(int i=0; i <this.numContacts;i++){
			
			this.contacts[i].setContactType(ContactType.values()[generator.nextInt(ContactType.values().length)]);
			
		}
	}
	
	public Contact[] listByContactType(ContactType ct){
		Contact[] listContacts = new Contact[5];
		int numC =0;
		for(int i=0;i <this.numContacts; i++){
			
					if(contacts[i].getContactType().equals(ct)){
				listContacts[numC++] = contacts[i];
				if(numC == listContacts.length){
					listContacts =resize(numC, numC*2, listContacts);
				
				}
			}
			
		}
		listContacts = resize(numC,null,listContacts);
		return listContacts;
		/*Code to sort through ALL contacts by type
		 * 
		 * 
		
		Contact placeContact = null;
		int position;
		for(int i=0; i <=numContacts-1 ; i++){
			placeContact =copyContact(listContacts[i]);
			position = i;
			for(int j=i+1; j <listContacts.length; j++){
				
				if(listContacts[j].getContactType().compareTo(listContacts[i].getContactType()) < 0){
						placeContact = copyContact(listContacts[i]);
						position = j;
				}
			
			if(position != i){
				listContacts[position] = listContacts[i];
				listContacts[i] = placeContact;
			}
			}
			}
			*/
	}
	//this is a deep copy maker
	public static Contact copyContact(Contact c){
		Contact deepCopy = new Contact(c.getName(),c.getContactType(),(Object)c.getPhoneNumber());
		return deepCopy;
	}
	public Contact[] resize(int currentSize, Integer capacity, Contact[] c){
		   Contact[] copy;
		 
		  if (capacity == null){
			   int j=0;
			   while(j<c.length){
				   
				   if(c[j] == null)
					   break;
				   j++;
			   }
			   copy = new Contact[j];
			   for(int i=0; i <copy.length;i++){
				   copy[i] = c[i];
			   }
			   return copy;
		   }
		  copy= new Contact[capacity];
		  if(capacity > currentSize){
		   for(int i =0; i < currentSize; i++){
			   copy[i] = c[i];
		   }
		   }
		   else{
			   
			   for(int i =0; i < capacity; i++){
				   copy[i] = c[i];
			   }
		   }
	
		   return copy;
		   
	
	}
	public Integer findPos(Contact c){
		for(int i=0; i <this.getNumContacts();i++){
			if(this.orderContacts()[i] == c){
				return i;
			}
		}
		return null;
		
	}

	public String toString(){
		String aString = "";
		for(int i=0; i <this.numContacts; i++){
			aString += this.contacts[i].toString() + "\n";
		}
		return aString;
	}
}
