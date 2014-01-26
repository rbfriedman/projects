package cellPhone.backend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CellPhone implements Serializable{
	private PhoneOwner owner;
	 private Call[] receivedCalls, dialedCalls,missedCalls,allCalls;
	 private AddressBook addressBook;
	 private boolean connectionEstablished;
	 private JButton[] callOptions = new JButton[CallOptions.values().length];
	private GregorianCalendar aTime;
	 private Message[] receivedMessages,sentMessages;
	 private int numCallReceived, numCallDialed,numMessReceived, numMessSent,numCallMissed,numCalls ;
	private Network aNetwork;
	private JFrame ringingPhone;
	private JPanel contentPanel;
	private Call currentCall;

	public CellPhone(PhoneOwner owner,Network network) throws FileNotFoundException, NumberAlreadyEnteredException{
	this.owner = owner;
	this.aNetwork = network;
	
	this.receivedCalls = new Call[100];
	this.dialedCalls = new Call[100];
	this.missedCalls = new Call[100];
	this.allCalls =new Call[300];
	this.numCallDialed =0;
	this.numCallReceived =0;
	this.numCallMissed = 0;
	this.numCalls =0;
	
	this.sentMessages = new Message[100];
	this.receivedMessages = new Message[100];
	this.numMessSent=0;
	this.numMessReceived =0;
	this.callOptions[0] =new JButton(CallOptions.ANSWER.toString());
	this.callOptions[1] =new JButton(CallOptions.IGNORE.toString());
	addressBook = new AddressBook();
	this.initializeAddressBook();
	 }
	
	public Call receiveCall(PhoneNumber phoneNum){
		
		
		aTime = (GregorianCalendar)GregorianCalendar.getInstance();
		//if(this.aNetwork.callConnected(this.aNetwork.isWithinNetwork(phoneNum.getPhoneNum()), this.getPhoneOwner().getPhoneNumber())){
		this.currentCall =this.allCalls[this.numCalls++%300] =receivedCalls[numCallReceived++%100] = new Call(phoneNum,CallType.RECEIVED); 
		//}
		return this.currentCall;
	}
	
	public Call dialCall(PhoneNumber phoneNum){
		this.currentCall =this.allCalls[this.numCalls++%300] =this.dialedCalls[this.numCallDialed++%100]= new Call(phoneNum,CallType.DIALED);
		
		
		return this.currentCall;
		
	}
	public Call ignoreCall(PhoneNumber phoneNum){
		this.currentCall =this.allCalls[this.numCalls++%300] = this.missedCalls[this.numCallMissed++%100] = new Call(phoneNum,CallType.MISSED);
		return this.currentCall;
	}
	public void endCall(PhoneNumber phoneNum){
		this.currentCall.endCall();
	}

	public void addContact(String name, PhoneNumber phoneNum) throws NumberAlreadyEnteredException{
		
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
	public PhoneOwner getPhoneOwner(){
		return this.owner;
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
	public Call[] getReceivedCalls() {
		return receivedCalls;
	}
	public void setReceivedCalls(Call[] receivedCalls) {
		this.receivedCalls = receivedCalls;
	}
	public Call[] getDialedCalls() {
		return dialedCalls;
	}
	public void setDialedCalls(Call[] dialedCalls) {
		this.dialedCalls = dialedCalls;
	}
	public Call[] getMissedCalls() {
		return missedCalls;
	}
	public Call[] getAllCalls(){
		return this.allCalls;
	}
	public void setMissedCalls(Call[] missedCalls) {
		this.missedCalls = missedCalls;
	}
	public int getNumCallReceived() {
		return numCallReceived;
	}
	public void setNumCallReceived(int numCallReceived) {
		this.numCallReceived = numCallReceived;
	}
	public int getNumCallDialed() {
		return numCallDialed;
	}
	public void setNumCallDialed(int numCallDialed) {
		this.numCallDialed = numCallDialed;
	}
	public int getNumCallMissed() {
		return numCallMissed;
	}
	public void setNumCallMissed(int numCallMissed) {
		this.numCallMissed = numCallMissed;
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
	public AddressBook getAdressBook() {
		// TODO Auto-generated method stub
		return this.addressBook;
	}
	public int getNumCalls() {
		// TODO Auto-generated method stub
		return this.numCalls;
	}
	public boolean hasContact(String phoneNum) {
		// TODO Auto-generated method stub
		if(this.addressBook.getContact(phoneNum)== null){
			return false;
		}
		return true;
	}
	public void setConnectionEstablished(PhoneNumber phoneNum){
		this.aNetwork.setConnectionEstablished(aNetwork.isWithinNetwork(phoneNum.toString()), this);
		
	}
	private void initializeAddressBook() throws FileNotFoundException, NumberAlreadyEnteredException{
		  this.addressBook.populateAddressBook();
		  addContact("John",new PhoneNumber(LineType.HOME,"3024564231"));
		  addContact("Jimmy",new PhoneNumber(LineType.HOME,"3034564231"));
		  addContact("Mary",new PhoneNumber(LineType.HOME,"3014564231"));
		  addContact("Mr. Suez",new PhoneNumber(LineType.MOBILE1,"300964231"));
		  addContact("Mr. Fill",new PhoneNumber(LineType.HOME,"3014564231"));
		  addContact("Lottie",new PhoneNumber(LineType.BUSINESS,"3014564231"));
	  }
	public JButton[] getCallOptions(){
		return this.callOptions;
	}
	public void isRinging(PhoneNumber phoneNum) {
		// TODO Auto-generated method stub
		Integer milliseconds = GregorianCalendar.getInstance().MILLISECOND;
		String message ="Incoming Call from ";
		Contact currentContact = this.findContact(phoneNum);
		if(currentContact!=null){
			message+=currentContact.getName();
		}
		else
			message+=phoneNum.getPhoneNum();
		
		
		ringingPhone = new JFrame();
		contentPanel = new JPanel();
		ringingPhone.setTitle(getPhoneOwner().getName()+"'s Phone");
		contentPanel.add(new JLabel(message));
		callOptions[0].addActionListener(new CallActionListener(callOptions[0].getText(),phoneNum));
		callOptions[1].addActionListener(new CallActionListener(callOptions[1].getText(),phoneNum));
		contentPanel.add(callOptions[0]);
		contentPanel.add(callOptions[1]);
		ringingPhone.add(contentPanel);
		ringingPhone.setSize(300, 200);
		ringingPhone.setLocationRelativeTo(null);
		ringingPhone.setVisible(true);
		
	
	}
	private class EndCallActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg){
			//closes the call connection and stops the timer
			
			finishCall(getPhoneOwner().getPhoneNumber());
			ringingPhone.dispose();
			System.out.println(this.getClass().toString() +": " + "Call Ended");
		}
	}
	 private class CallActionListener implements ActionListener{
		 
			private String buttonText;
			private PhoneNumber caller;
			private CallActionListener(String t,PhoneNumber phoneNum){
				this.buttonText =t;
				this.caller= phoneNum;
			}
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(this.buttonText.equals(CallOptions.ANSWER.toString())){
					//CallPanelWithTimer jpReceivedCall = new CallPanelWithTimer(receivedCalls[numCallReceived-1]);
					ringingPhone.setVisible(false);
					JPanel jpReceivedCall = new JPanel();
					if(findContact(receivedCalls[numCallReceived-1].getPhoneNumber())!=null){
						jpReceivedCall.add(new JLabel(findContact(receivedCalls[numCallReceived-1].getPhoneNumber()).getName()));
					}
					
					jpReceivedCall.add(new JLabel(receivedCalls[numCallReceived-1].toString()));
					JButton jbEndCall = new JButton("End Call");
					
					jbEndCall.addActionListener(new EndCallActionListener());
					
					ringingPhone.remove(contentPanel);
					ringingPhone.add(jpReceivedCall,BorderLayout.NORTH);
					ringingPhone.add(jbEndCall,BorderLayout.SOUTH);
					ringingPhone.setVisible(true);

					/*ActionListener taskPerformer = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							ringingPhone.repaint();
						}
					};

					timer= new Timer(1000, taskPerformer);
					timer.start();
					*/
					
				}
				else if(this.buttonText.equals(CallOptions.IGNORE.toString())){
					finishCall(this.caller);
					JPanel jpReceivedCall = new JPanel();
					jpReceivedCall.add(new JLabel(missedCalls[numCallMissed-1].toString()));
					
					currentCall.endCall();
					ringingPhone.remove(contentPanel);
					ringingPhone.add(jpReceivedCall);
					ringingPhone.revalidate();
				}
			}
			  
			 
		 


	
	 }
	public void serializeObject() {
		// TODO Auto-generated method stub
		try{
		     System.out.println("saving data to disk -1");
		     FileOutputStream cellPhone = new FileOutputStream("./cellPhone.ser");
		     System.out.println("saving data to disk -2");
		     ObjectOutputStream out = new ObjectOutputStream(cellPhone);
		     System.out.println("saving data to disk -3");
		     out.writeObject(cellPhone);
		     System.out.println("saving data to disk -4");
		     out.close();  //close the stream
		     cellPhone.close();  //close the file pointing to the stream
		     
	     }
		catch(IOException io){
			io.printStackTrace();
			JOptionPane.showMessageDialog(null,io.getMessage());
			System.out.println("Failed to save date to disk");
		} 
	
	}

	public void makeCall(PhoneNumber phoneNumber) {
		if(aNetwork.isWithinNetwork(phoneNumber.toString())!=null){
			setConnectionEstablished(phoneNumber);
		}
		else
			dialCall(phoneNumber);
		
	}
	public void finishCall(PhoneNumber phoneNum){
		if(aNetwork.isWithinNetwork(phoneNum.toString())!=null){
			this.aNetwork.detachConnection(aNetwork.isWithinNetwork(phoneNum.toString()), this);
		}
		else endCall(phoneNum);
		
	}
	public String toString(){
		return "Phone Owner: " +this.getPhoneOwner().toString() +
				"\nAll Calls: " + this.numCalls;

	}
	
}
