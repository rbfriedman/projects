package cellPhone.backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;


public class Call implements Serializable{
	private PhoneNumber phoneNumber;
	private long duration;
	private GregorianCalendar dateTimeCalled;
	private CallType callType;
	private Timer timer;
	private String timerString;
	private Date aDate;
	private StopWatch stopWatch;
	/*
	 * A Call is contructed upon the dialer's call; the call duration is set when the caller ends the call
	 */
	public Call(PhoneNumber phoneNumber,CallType callType){
		this.phoneNumber = phoneNumber;
		this.duration = 0L;
		this.dateTimeCalled = (GregorianCalendar) GregorianCalendar.getInstance();
		this.callType = callType;
		//a call is dialed, and then is  changed to received or missed
		this.timerString ="";
		this.aDate = new Date();
		/*
		timer = new Timer(1000, new updateClockAction());
		timer.start();
		this.stopWatch = new StopWatch();
		*/
		
		
	}
	public void receiveCall(){
		//this.stopWatch.start();
	}
	
	public void endCall(){
		Integer milliseconds = (int) (GregorianCalendar.getInstance().getTimeInMillis() - this.dateTimeCalled.getTimeInMillis());
		this.duration = milliseconds;
		
		//this.stopWatch.stop();
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
	public PhoneNumber getPhoneNumber(){
		return this.phoneNumber;
	}
	public SimpleDateFormat formatDate(String pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter;
	}
	
	public String toString(){
		return this.phoneNumber.toString() + " Time of Call:" + formatDate("EEE MMM dd, hh:mm").format(this.dateTimeCalled.getTime()) +
				" Duration: " + formatDate("mm:ss").format(this.duration);
	}
	public String getTimer(){
		return timerString ;
	}
	private class  updateClockAction implements ActionListener {
		
		    public void actionPerformed(ActionEvent evt) {
			//...Update the progress bar...
		    	
		    	timerString =formatDate("hh:mm:ss").format(new Date().getTime()-aDate.getTime()); 
		    	System.out.println(timerString);
		    	System.out.println(formatDate("hh:mm:ss").format(stopWatch.getElapsedTime()));
		        }
		
		
		}
}


