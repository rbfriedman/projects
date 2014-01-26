package cellPhone.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cellPhone.backend.Call;
import cellPhone.backend.CellPhone;

public class CallsPanel extends JPanel{
	private CallMenuItems callMenuItem;
	private CellPhone cellPhone;
	private Call[] calls;
	private int listLength;
	private JScrollPane scrollPane;
	private JPanel jpArea;
	public CallsPanel(CallMenuItems callMenuItem, CellPhone cellPhone) {
		// TODO Auto-generated constructor stub
		this.cellPhone = cellPhone;
		this.callMenuItem = callMenuItem;
		this.jpArea = new JPanel();
		this.determineListType();
		this.setLayout(new BorderLayout());
		this.scrollPane = new JScrollPane(this.listCalls());
		this.add(new JLabel(callMenuItem.toString()),BorderLayout.NORTH);
		this.add(this.scrollPane,BorderLayout.CENTER);
		
		
		
		
	} 
	private JPanel listCalls(){
		JLabel aLabel;
		String desc ="";
		this.jpArea.setLayout(new GridLayout(listLength,1,5,5));
		for(int i=0; i <this.listLength;i++){
			desc ="";
			aLabel = new JLabel();
			if(cellPhone.hasContact(calls[i].getPhoneNumber().getPhoneNum())){
				desc += cellPhone.getAdressBook().getContact(calls[i].getPhoneNumber().getPhoneNum()).getName() +" ";
				aLabel = new JLabel();
				aLabel.setIcon(new ImageIcon(getClass().getResource("cellPhone.png")));
			}
			desc += calls[i].toString();
			aLabel.setText(desc);
			this.jpArea.add(aLabel);
			
		}
		return this.jpArea;
	}
	private void determineListType(){
		switch(callMenuItem){
		case ALLCALLS:
			calls = cellPhone.getAllCalls();
			listLength = cellPhone.getNumCalls()>=300? 300:cellPhone.getNumCalls();
			break;
		case DIALEDCALLS:
			calls= cellPhone.getDialedCalls();
			listLength = cellPhone.getNumCallDialed()>=100?100:cellPhone.getNumCallDialed();
			break;
		case MISSEDCALLS:
			calls = cellPhone.getMissedCalls();
			listLength = cellPhone.getNumCallMissed()>=100?100:cellPhone.getNumCallMissed();
			break;
		case RECEIVEDCALLS:
			calls = cellPhone.getReceivedCalls();
			listLength = cellPhone.getNumCallReceived()>=100?100:cellPhone.getNumCallReceived();
			break;
		default:
			break;
		}
	}

}
