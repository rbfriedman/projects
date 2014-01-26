package cellPhone.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Message implements Serializable{
	private char[] message;
	private Contact contact;
	private PhoneNumber phoneNum;

	private GregorianCalendar dateTime;
	
	public Message(PhoneNumber phoneNum, String message, AddressBook addressBook){
		
		this.message= (char[])message.toCharArray();
		/*for(int i=0; i < message.length(); i++){
			if(i % 160 == 0){
				this.startNewMessage();
			}
		}
		*/
		if(addressBook.getContact(phoneNum.getPhoneNum())==null)
		this.contact = null;
		else{
			this.contact = addressBook.getContact(phoneNum.getPhoneNum());
		}
		
	}
	public Message(Message aMessage){
		this.message = aMessage.getMessage();
		this.phoneNum = aMessage.getPhoneNum();
		
	}
	
	public char[] getMessage() {
		return message;
	}
	public void setMessage(char[] message) {
		this.message = message;
	}
	public PhoneNumber getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(PhoneNumber phoneNum) {
		this.phoneNum = phoneNum;
	}
	public GregorianCalendar getDateTime() {
		return dateTime;
	}
	public void setDateTime(GregorianCalendar dateTime) {
		this.dateTime = dateTime;
	}
	public void setUpContact(String message, PhoneNumber phoneNum){
		
	}
	public ArrayList<String> extractContact(){
		String subStr ="";
		ArrayList<String> possibleContacts = new ArrayList<String>();
		int num=0;
		for(int i=0; i <this.message.length - 10; i++){
			
			if(Character.isDigit(this.message[i]) && Character.isDigit(this.message[i+10])){
				subStr = this.message.toString().substring(i, i+10);
				possibleContacts.add(subStr);
			}
			
		}
		/*
		 * For every substring that is possibly a phone number,
		 * we evaluate every index point of the substring o see if it is a ten digit code,
		 * if so, it is evaluated as a phone number and still included in the possibleContacts array
		 */
		boolean exclude = false;
		for(int i=0; i <possibleContacts.size(); i++){
			for(int j=0; j <possibleContacts.get(i).length(); j++){
				if(!Character.isDigit(possibleContacts.get(i).charAt(j))){
					exclude = true;
				}
			}
			if(exclude){
				possibleContacts.remove(i);
			}
		}
		return possibleContacts;
	}
}
