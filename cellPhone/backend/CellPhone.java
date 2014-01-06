package cellPhone.backend;

import java.io.FileNotFoundException;
import java.util.GregorianCalendar;

public class CellPhone {
	private PhoneOwner owner;
	 private Call[] receivedCalls, dialedCalls;
	 private AddressBook addressBook;
	 private GregorianCalendar aTime;
	 private Message[] receivedMessages,sentMessages;
	 private int numCallReceived, numCallDialed,numMessReceived, numMessSent ;

	public CellPhone(PhoneOwner owner) throws FileNotFoundException{
	this.owner = owner;
	
	this.receivedCalls = new Call[100];
	this.dialedCalls = new Call[100];
	this.numCallDialed =0;
	this.numCallReceived =0;
	
	this.sentMessages = new Message[100];
	this.receivedMessages = new Message[100];
	this.numMessSent=0;
	this.numMessReceived =0;
	
	addressBook = new AddressBook();
	try {
		addressBook.populateAddressBook();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		throw e;
	}
	
	 }
	public void receiveCall(PhoneNumber phoneNum){
		
		Contact currentContact = this.findContact(phoneNum);
		
		aTime = (GregorianCalendar)GregorianCalendar.getInstance();
		
		receivedCalls[numCallReceived++] = new Call(phoneNum,CallType.RECEIVED); 
		
	}
	public void dialCall(PhoneNumber phoneNum){
		Contact currentContact = this.findContact(phoneNum);
		aTime = (GregorianCalendar)GregorianCalendar.getInstance();
		
		this.dialedCalls[this.numCallDialed++]= new Call(phoneNum,CallType.DIALED);
	}
	public void ignoreCall(PhoneNumber phoneNum){
		Contact currentContact = this.findContact(phoneNum);
	}
	public void addContact(String name, PhoneNumber phoneNum) throws Exception{
		
		if(this.addressBook.getContactByName(name) == null){
		
			Contact newContact = new Contact(name,phoneNum);
			addressBook.addContact(newContact);
			
		}
		else{
			throw new NumberAlreadyEnteredException("Number Already Exists In Another Entry");
		}
	}
	public Contact findContact(String name){
		//Main will deal with case of a null find
		return addressBook.getContactByName(name);
	}
	public Contact findContact(PhoneNumber phoneNum){
		//Main will deal with case of a null find
		//in scenarios
		//a. searching through numbers
		//b. dialing a phone call (automatically searches contacts)
		
		
		
		return addressBook.getContact(phoneNum.getPhoneNum());
	}
	public void endCall(PhoneNumber phoneNum){
		this.endCall(phoneNum);
	}
	public void sendMessage(Message aMessage){
		this.sentMessages[this.numMessSent++] = aMessage;
	}
	public void receiveMessage(String message, PhoneNumber phoneNum){
		this.receivedMessages[this.numMessReceived] = new Message(phoneNum, message,addressBook);
		
	}
	public Message[] getReceivedMessages(){
		return this.receivedMessages;
	}
	public Message[] getSentMessages(){
		return this.sentMessages;
	}
	public void removeMessage(GregorianCalendar dateTime){
		
	}
	public Contact[] getContactsByPhone(){
		return this.addressBook.listByPhoneNumber("");
	}
	public String allContactsToString(){
		return this.addressBook.toString();
	}
	public int getNumContacts(){
		return this.addressBook.getNumContacts();
	}
	public Contact[] getContactsByType(ContactType ct){
		return this.addressBook.listByContactType(ct);
	}
	
	
}
