package cellPhone.backend;

public class Contact{
private String name;
private PhoneNumber[] phoneNum = new PhoneNumber[LineType.values().length];
private int phoneNumsStored=0;
private String address;

private ContactType contactType;

public Contact(String name,ContactType ct,Object...objects){
	this.name = name;
	for(int i= 0;i <objects.length; i++){
		//The cell phone owner may want to enter many phone nums at once
		if(objects[i]!= null){
		phoneNum[i] = (PhoneNumber)objects[i];
		phoneNumsStored++;
		}
	}
	
	this.contactType = ct;
	this.address ="";
} 
public Contact(String name, PhoneNumber phoneNum){
	this(name,null, phoneNum);
	this.contactType =ContactType.NOTAVAILABLE;

}


public Contact(Contact copyContact) {
	// TODO Auto-generated constructor stub
	this(copyContact.getName(),(copyContact.getContactType()!=null?copyContact.getContactType():null),copyContact.getPhoneNumbers());
	this.address =copyContact.address.equals(null)?"":copyContact.address;
	
}
public PhoneNumber getPhoneNumber() {
	return this.phoneNum[0];
}
public PhoneNumber[] getPhoneNumbers() {
	return this.phoneNum;
}

public void setPhoneNumber(PhoneNumber Num) {
	boolean phoneNumAdded = false;
	//
	for(int i=0; i < this.phoneNumsStored; i++){
		//if there is LineType of the same type we overrwrite the num
		if(Num.getLineType() != phoneNum[i].getLineType()){
			phoneNum[i] = Num;
			phoneNumAdded = true;
		}
	}
	//if there is no LineType of the same type we can safely add a new number
	if(phoneNumAdded != true){
		phoneNum[phoneNumsStored] = Num;
	}
}
public void removePhoneNumber(PhoneNumber num){
	int elementNum =-1;
	for(int i=0; i <this.phoneNumsStored; i++){
		if(this.phoneNum[i].equals(num)){
			elementNum =i;//phoneNum[i] = phoneNum[i+1];
		}
	}
	for (int j= elementNum; j<this.phoneNumsStored-1;j++){
		this.phoneNum[j] = this.phoneNum[j+1];
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
public String toString(){
	String aString = "Name: ";
	aString += this.name;
	for(int i=0; i < this.phoneNumsStored; i++){
		aString+=" " +this.getPhoneNumbers()[i].toString();
	}
	aString+=this.address.equals("")?"":"Address: " +this.address;
	aString+=this.contactType.equals(ContactType.NOTAVAILABLE)?"":"Contact Type: " + this.contactType;

	return aString;
}

}
