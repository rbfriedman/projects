package cellPhone.backend;

import java.io.Serializable;
import java.util.Random;

public class PhoneNumber implements Serializable{
	private LineType lineType;
	private String phoneNum;
	
	public PhoneNumber(LineType aLineType, String aPhoneNum){
		this.lineType = aLineType;
		this.phoneNum = aPhoneNum;
	}
	public LineType getLineType() {
		return lineType;
	}
	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public boolean equals(PhoneNumber aPhoneNum){
		//this functionality is used to compare the actual phoneNumbers, the actual
		//memory location OR landline is unimportant when a user is searching for a phone number
		return (this.getPhoneNum().equals(aPhoneNum.getPhoneNum()));
	}
	public static PhoneNumber generateRandomPhone(){
		Random rand = new Random();
		String tempPhone ="";
		LineType aLine = LineType.MOBILE1;
		while(tempPhone.length()<10){
		tempPhone+= rand.nextInt(9);
		}
		for(LineType l: LineType.values()){
			if(l.ordinal() == rand.nextInt(LineType.values().length)){
				aLine = l;
			}
		}
		return new PhoneNumber(aLine, tempPhone);
	
	}
	public String toString(){
		return phoneNum;
		//phoneNum.substring(0, 3) +"-" + phoneNum.substring(3, 6) + "-"+ phoneNum.substring(6, 10);
		
	}
}
