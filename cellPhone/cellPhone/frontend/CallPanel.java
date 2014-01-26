package cellPhone.frontend;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cellPhone.backend.AddressBook;
import cellPhone.backend.Call;
import cellPhone.backend.CallType;
import cellPhone.backend.CellPhone;
import cellPhone.backend.Contact;
import cellPhone.backend.PhoneNumber;

public class CallPanel extends JPanel{
	/*parameters*/
	private Call call;
	
	private JLabel jlbTimestamp;
	private JLabel jlbTimer;
	private JLabel jlbStatus;
	private JLabel jlbContact;
	private CellPhoneFrame csFrame;
	private AddressBook addressBook;
	private CellPhone cellPhone;
	private JMenu menuBar;
	private CallPanelWithTimer timerPanel;
	
	
	public CallPanel(Call call,CellPhone cellPhone,CellPhoneFrame csFrame){
		this.csFrame = csFrame;
		this.cellPhone = cellPhone;
		this.addressBook = cellPhone.getAdressBook();
		this.call = call;
		//this.timerPanel = this.getCallStatus();
		//addCall();
		determineCaller();
		determineCallStatus();
		this.setUpMenu();
		this.jlbTimestamp = new JLabel(this.csFrame.getCurrentDate());
		this.setLayout(new GridLayout(6,1,5,5));
		//this.add(timerPanel);
		this.add(jlbTimestamp);
		this.add(jlbStatus);
		this.add(jlbContact);
		
		
		
	}
	/*
	 * Originally, calls were made from within this panel, but then I realized it was
	 *  best to call from the back end
	 * private void addCall(){
		switch (CallType.valueOf(this.call.getCallType())){
		case MISSED:cellPhone.ignoreCall(call.getPhoneNumber());
			break;
		case RECEIVED:cellPhone.receiveCall(call.getPhoneNumber());
			break;
		case DIALED:cellPhone.dialCall(call.getPhoneNumber());
			
			break;
			
		}
	}
	*/
	public void clearPanel(){
		csFrame.getJMenuBar().remove(csFrame.getMenu());
			
		csFrame.remove(this);
	}
	private void setUpMenu(){
		csFrame.getJMenuBar().remove(csFrame.getMenu());
		csFrame.revalidate();
		JMenuItem menuItem;
		menuBar = new JMenu("Options");
		
		for(CallMenuItems e: CallMenuItems.values()){
			menuItem = new JMenuItem(e.toString());
			menuItem.setName(e.toString());
			menuItem.addActionListener(csFrame.getJMenuActionListener(menuItem.getText()));
			menuBar.add(menuItem);
		}
		csFrame.setMenu(menuBar);
		csFrame.getJMenuBar().add(menuBar);
		csFrame.setJMenuBar(csFrame.getJMenuBar());
		csFrame.revalidate();
	}
	public JMenu getMenu(){
		return this.csFrame.getMenu();
	}
	private void determineCallStatus() {
		/*
		 * Check CallType and set accordingly
		 */
		this.jlbStatus = new JLabel(this.call.getCallType());
		
	}
	/*
	public CallPanelWithTimer getCallStatus(){
		timerPanel = new CallPanelWithTimer(call);
		return timerPanel;
	}
	*/
	

	private void determineCaller() {
		// TODO Auto-generated method stub
		/*
		 * Find if caller is in addressbook
		 * if not, caller is just a phone number
		 */
		PhoneNumber phoneNum = call.getPhoneNumber();
		Contact contact =this.addressBook.getContact(phoneNum.getPhoneNum());
		if(contact!=null){
			jlbContact = new JLabel(contact.getName());
		}
		else
			jlbContact = new JLabel(phoneNum.getPhoneNum());
		
		jlbContact.setIcon(new ImageIcon(getClass().getResource("cellPhone.png")));
	}
	

}