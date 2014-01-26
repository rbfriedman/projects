package cellPhone.backend;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Contact implements Serializable{
private String name;
private ArrayList<PhoneNumber> phoneNum = new ArrayList<PhoneNumber>();
private int phoneNumsStored=0;
private String address;
private String email;

private ContactType contactType;

public Contact(String name,ContactType ct,Object...objects){
	
	this.name = name;
	for(int i= 0;i <objects.length; i++){
		//The cell phone owner may want to enter many phone nums at once
		if(objects[i]!= null){
		phoneNum.add((PhoneNumber)objects[i]);
		phoneNumsStored++;
		}
	}
	this.email ="";
	this.contactType = ct;
	this.address ="";
} 
public Contact(String name, PhoneNumber phoneNum){
	this(name,null, phoneNum);
	this.contactType =ContactType.NOTAVAILABLE;

}


public Contact(Contact copyContact) {
	// TODO Auto-generated constructor stub
	if(copyContact instanceof Contact ){
	Contact aCopyContact = (Contact)copyContact;
	this.name = aCopyContact.getName();
	this.contactType =aCopyContact.getContactType()!=null?aCopyContact.getContactType():null;
	this.phoneNum =aCopyContact.getPhoneNumbers();
	this.phoneNumsStored = aCopyContact.phoneNumsStored;
	
	this.address =copyContact.address.equals(null)?"":copyContact.address;
	}
}
public PhoneNumber getPhoneNumber() {
	/*
	 *Here we are assuming that we would only want to retrieve the first cellPhoneNumber
	 */
	return this.phoneNum.get(0);
}
public ArrayList<PhoneNumber> getPhoneNumbers() {
	this.phoneNum.trimToSize();
	return this.phoneNum;
}

public ArrayList<Field> getFields(){
	ArrayList<Field> fields = new  ArrayList<Field>();
    Field[] allFields = Contact.class.getDeclaredFields();
    for (Field field : allFields) {
        if(field.getType() == String.class || field.getType() == PhoneNumber[].class){
        	
            fields.add(field);
        }
    
    }
    return fields;
    }
public ArrayList<Field> getFilledFields() throws IllegalArgumentException, IllegalAccessException{
	ArrayList<Field> fields = new  ArrayList<Field>();
    Field[] allFields = Contact.class.getDeclaredFields();
    for (Field field : allFields) {
        if((field.getType() == String.class || field.getType() == PhoneNumber[].class) && !field.get(this).equals(""))
            fields.add(field);
        }
    return fields;
    }

public void setPhoneNumber(PhoneNumber Num) {
	boolean phoneNumAdded = false;
	//
	for(int i=0; i < this.phoneNumsStored; i++){
		//if there is LineType of the same type we overrwrite the num
		if(Num.getLineType() == phoneNum.get(i).getLineType()){
			phoneNum.set(i, Num);
			phoneNumAdded = true;
		}
	}
	//if there is no LineType of the same type we can safely add a new number
	if(phoneNumAdded != true){
		phoneNum.add(Num);
	}
}
public void removePhoneNumber(PhoneNumber num){
	int elementNum =-1;
	for(int i=0; i <this.phoneNumsStored; i++){
		if(this.phoneNum.get(i).equals(num)){
			elementNum =i;//phoneNum[i] = phoneNum[i+1];
		}
	}
	for (int j= elementNum; j<this.phoneNumsStored-1;j++){
		this.phoneNum.set(j,this.phoneNum.get(j+1));
	}
	phoneNumsStored--;
	
}
public ContactType getContactType() {
	
	return contactType;
}
public void setContactType(ContactType contactType) {
	this.contactType = contactType;
}
public void setAddress(String anAddress){
	this.address =anAddress;
}
public String getAddress(){
	return this.address;
}
public void setName(String name){
	this.name = name;
}
public String getName(){
	return this.name;
}
public void setEmail(String email){
	this.email = email;
}
public String getEmail(){
	return this.email;
}
public String toString(){
	String aString = "Name: ";
	aString += this.name;
	for(int i=0; i < this.phoneNumsStored; i++){
		aString+=" " +this.getPhoneNumbers().get(i).toString();
	}
	aString+=this.address.equals("")?"":"Address: " +this.address;
	aString+=this.contactType.equals(null)?"":"Contact Type: " + this.contactType;

	return aString;
}

}
