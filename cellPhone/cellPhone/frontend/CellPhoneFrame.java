package cellPhone.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import cellPhone.backend.AddressBook;
import cellPhone.backend.CallOptions;
import cellPhone.backend.CellPhone;
import cellPhone.backend.ContactType;


public class CellPhoneFrame extends SuperClassFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1483444555951734628L;
	private CellPhone cellPhone;
	private ContactScreenPanel cSPanel;
	private CallsPanel callsPanel;
	private JPanel jpContentPanel;
	private JMenu menuBar;
	private AddressBook addresses;
	private Timer timer;
	public CellPhoneFrame(CellPhone cellPhone) throws FileNotFoundException{
		
		this.setTitle(cellPhone.getPhoneOwner().getName() + "'s " +"Phone");
		this.setLocationRelativeTo(null);   
	    this.setBackground(Color.white);
		this.cellPhone = cellPhone;
		this.addresses = cellPhone.getAdressBook();
		this.jpContentPanel = new JPanel();
		
		timer = new Timer(1000,new updateClockAction());
		
		   this.setLayout(new BorderLayout());
		   
		   initializePanel();
		   setUpCSMenu();
		   initializeCellPhoneFunctionalities();
		   this.jpContentPanel.add(cSPanel);
		   this.add(this.jpContentPanel);
		   this.setSize(new Dimension(400,500));
		   this.setResizable(false);
		   this.setVisible(true);
		   this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   
		   timer.start();
		
		}
	
	public void initializeCellPhoneFunctionalities(){
		JButton[] callOptions =this.cellPhone.getCallOptions();
		for(int i =0; i <callOptions.length;i++){
			//callOptions[i].addActionListener(new CallActionListener(callOptions[i].getText()));
		}
	}
	public void initializePanel() throws FileNotFoundException{
		
		
		ContactScreenPanel csPanel = new ContactScreenPanel(this.cellPhone,this);

		
		this.cSPanel = csPanel;
		cSPanel.repaint();
		
		this.revalidate();
		   
	}
	public String getUserInput(){
		return ( cSPanel).getUserInput();
	}
	  

	 public JMenu getMenu(){
		 return this.menuBar;
	 }
	 public void refresh(){
		 this.cSPanel.addContactButton();
	 }
	 private  void setUpCSMenu(){
		
		 JMenuItem menuItem;
		 this.menuBar = new JMenu("Options");
		 JMenu subMenu;
		 menuBar.getAccessibleContext().setAccessibleDescription("Options Menu");
		
		
		 this.getJMenuBar().add(menuBar);
		 
		 for(ContactScreenMenuItems e:ContactScreenMenuItems.values()){
			 if(e.toString() == ContactScreenMenuItems.ORDERBYCONTACTS.toString()){
				 subMenu = new JMenu("Order By Contact Type");
				 for(ContactType c: ContactType.values()){
					 menuItem = new JMenuItem(c.toString());
					 menuItem.setName(c.toString());
					 menuItem.addActionListener(new JMenuActionListener(menuItem.getText()));
					 subMenu.add(menuItem);
				 }
				 menuBar.add(subMenu);
			 }
			 else{
			 menuItem = new JMenuItem(e.toString());
			 menuItem.setName(e.toString());
			 menuItem.addActionListener(new JMenuActionListener(menuItem.getText()));
			 menuBar.add(menuItem);
			 }
		 }
		 this.getJMenuBar().add(menuBar);
		
		 this.getJMenuBar().add(menuBar);
		 this.setJMenuBar(this.getJMenuBar());
		 
		 
		 
	 }
	 
	 protected JMenuActionListener getJMenuActionListener(String name){
			return new JMenuActionListener(name);
		}
	 private class CallActionListener implements ActionListener{
		 
		private String buttonText;
		private CallActionListener(String t){
			this.buttonText =t;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(this.buttonText.equals(CallOptions.ANSWER.toString())){
				JPanel jpReceivedCall = new JPanel();
				setUpMainMenu();
				jpReceivedCall.add(new JLabel("LLLLL"));
				setContentPanel(jpReceivedCall);
				revalidate();
				//jpReceivedCall.add(comp)
			}
			else if(this.buttonText.equals(CallOptions.IGNORE.toString())){
				
			}
		}
		 
	 }
	 private class JMenuActionListener implements ActionListener{
	    private String buttonName;
		private JMenuActionListener(String buttonName){
			 this.buttonName = buttonName;
			 
		 }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(this.buttonName.equals(ContactScreenMenuItems.ADDCONTACT.toString())){
				
				AddContactFrame a = new AddContactFrame(addresses,cSPanel);
				
				
			}
			else if(this.buttonName.equals(ContactScreenMenuItems.REMOVECONTACT.toString())){
				RemoveContactPanel a =new RemoveContactPanel(addresses,cSPanel);
				
				JFrame c = new JFrame();
				c.setTitle(ContactScreenMenuItems.REMOVECONTACT.toString());
				c.add(a);
				c.setLocationRelativeTo(null);
				c.setSize(new Dimension(200,200));
				c.setVisible(true);	
			}
			else if (this.buttonName.equals(CallMenuItems.DIALEDCALLS.toString())){
				callsPanel = new CallsPanel(CallMenuItems.DIALEDCALLS,cellPhone);
				setContentPanel(callsPanel);
				setUpMainMenu();
				revalidate();
			}
			else if (this.buttonName.equals(CallMenuItems.ALLCALLS.toString())){
				callsPanel = new CallsPanel(CallMenuItems.ALLCALLS,cellPhone);
				setContentPanel(callsPanel);
				setUpMainMenu();
				revalidate();
			}
			else if (this.buttonName.equals(CallMenuItems.MISSEDCALLS.toString())){
				callsPanel = new CallsPanel(CallMenuItems.MISSEDCALLS,cellPhone);
				setContentPanel(callsPanel);
				setUpMainMenu();
				revalidate();
			}
			else if (this.buttonName.equals(CallMenuItems.RECEIVEDCALLS.toString())){
				callsPanel = new CallsPanel(CallMenuItems.RECEIVEDCALLS,cellPhone);
				setContentPanel(callsPanel);
				setUpMainMenu();
				revalidate();
				
			}
			else if (this.buttonName.equals(CallMenuItems.CONTACTSCREEN.toString())){
				getJMenuBar().remove(getMenu());
				setUpCSMenu();
				try {
					initializePanel();
					setContentPanel(cSPanel);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				revalidate();
			}
			else if(ContactType.class==ContactType.valueOf(this.buttonName).getDeclaringClass()){
				ContactType aType = ContactType.NOTAVAILABLE;
				switch(ContactType.valueOf(this.buttonName)){
				case COLLEAGUE:
					aType = ContactType.COLLEAGUE;
					break;
				case FRIEND:
					aType = ContactType.FRIEND;
					break;
				case FAMILY:
					aType = ContactType.FAMILY;
					break;
				case NOTAVAILABLE:
					aType = ContactType.NOTAVAILABLE;
					break;
				case MEDICAL:
					aType = ContactType.MEDICAL;
					break;
				}
				String message ="";
					if(!addresses.listByContactType(aType).equals(null)){
						for(int i=0; i <addresses.listByContactType(aType).length;i++)
						message +=addresses.listByContactType(aType)[i].toString() +"\n";
					}
					
				JOptionPane.showMessageDialog(null, message);
			}

	
		
			
		}
	 }

	public Component getcsPanel() {
		// TODO Auto-generated method stub
		return this.cSPanel;
	}
	
	public void setMenu(JMenu aMenu) {
		// TODO Auto-generated method stub
		this.menuBar = aMenu;
		
	}
	public Component getContentPanel() {
		// TODO Auto-generated method stub
		return this.jpContentPanel;
	}
	public void setContentPanel(JPanel jp) {
		// TODO Auto-generated method stub
		this.jpContentPanel.removeAll();
		this.jpContentPanel.add(jp);
		this.repaint();
	}
	private void setUpMainMenu(){
		this.getJMenuBar().remove(getMenu());
		JMenuItem menuItem;
		menuBar = new JMenu("Options");
		
		for(CallMenuItems e: CallMenuItems.values()){
			menuItem = new JMenuItem(e.toString());
			menuItem.setName(e.toString());
			menuItem.addActionListener(getJMenuActionListener(menuItem.getText()));
			menuBar.add(menuItem);
		}
		this.setMenu(menuBar);
		this.getJMenuBar().add(menuBar);
		this.setJMenuBar(getJMenuBar());
		this.revalidate();
	}
	
}

