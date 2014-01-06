package cellPhone.backend;

import java.util.GregorianCalendar;

public class Call {
	private PhoneNumber phoneNumber;
	private Integer durationInMillisec;
	private GregorianCalendar dateTimeCalled;
	private CallType callType;
	/*
	 * A Call is contructed upon the dialer's call; the call duration is set when the caller ends the call
	 */
	public Call(PhoneNumber phoneNumber,CallType callType){
		this.phoneNumber = phoneNumber;
		this.durationInMillisec = 0;
		this.dateTimeCalled = (GregorianCalendar) GregorianCalendar.getInstance();
		this.callType = callType;
		//a call is dialed, and then is  changed to received or missed
		
		
		
	}
	public void endCall(){
		Integer milliseconds = (int) (GregorianCalendar.getInstance().getTimeInMillis() - this.dateTimeCalled.getTimeInMillis());
		this.durationInMillisec =milliseconds;
		//this.setCallType(callType)
	}
	public void setCallType(CallType callType){
		this.callType = callType;
	}
	public String getCallType(){
		if(this.callType == null){
			return "This call type has not been set yet";
		}
		else
			return this.callType.toString();
	}
}
