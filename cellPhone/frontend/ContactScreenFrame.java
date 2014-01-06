package cellPhone.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cellPhone.backend.AddressBook;
import cellPhone.backend.Battery;
import cellPhone.backend.Contact;
import cellPhone.backend.ContactType;
import cellPhone.backend.LineType;
import cellPhone.backend.PhoneNumber;
import cellPhone.backend.Signal;

public class ContactScreenFrame extends JFrame {
	private Battery battery;
	private Signal signal;
	private AddressBook addresses;
	private ContactScreenPanel cSPanel;
	private JLabel jlbTitleLabel;
	private JMenuBar jMenu;
	public ContactScreenFrame() throws FileNotFoundException{
		
		this.setTitle("Phone");
		   this.setLocationRelativeTo(null);   
		   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		   this.setBackground(Color.white);
		   setUpMenu();
		   
		   this.setLayout(new BorderLayout());
		   

		   this.cSPanel = initializePanel();
		  
		   this.add(this.cSPanel);
		   this.setSize(new Dimension(400,500));
		   this.setResizable(false);
		   this.setVisible(true);
		   this.repaint();
		
		}
	
	public ContactScreenPanel initializePanel() throws FileNotFoundException{
		
		addresses = new AddressBook();
	    this.initializeAddressBook();
		ContactScreenPanel csPanel = new ContactScreenPanel(addresses,this);

		
		return csPanel;
		   
	}
	public String getUserInput(){
		return ( cSPanel).getUserInput();
	}
	  public static void main(String[] args) throws FileNotFoundException{
		 
		  ContactScreenFrame contactScreen = new ContactScreenFrame();
	  }	

	  
	private String getCurrentTime(){
			GregorianCalendar time =new GregorianCalendar();
			//(time.get(Calendar.HOUR)%10==0?String.valueOf(time.get(Calendar.HOUR))+"0":
			
			int hour = ((time.get(Calendar.HOUR_OF_DAY)==0)?12:time.get(Calendar.HOUR_OF_DAY));
			
			String minutes = (time.get(Calendar.MINUTE)<10?"0" +String.valueOf(time.get(Calendar.MINUTE)):(time.get(Calendar.MINUTE)%10==0?String.valueOf(time.get(Calendar.MINUTE)+"0"):String.valueOf(time.get(Calendar.MINUTE))));
			System.out.println(hour +minutes);
			
			return hour+":" + time.get(Calendar.MINUTE);
			
		}

	 private void setUpMenu(){
		 jMenu = new JMenuBar();
		 JMenuItem menuItem;
		 JLabel time = new JLabel(getCurrentTime());
		 time.setIcon(new ImageIcon(getClass().getResource("LowBattery.PNG")));
		 
		 JMenu menuBar = new JMenu("Options");
		 JMenu subMenu = new JMenu("Order By Contact Type");
		 menuBar.getAccessibleContext().setAccessibleDescription("Options Menu");
		 menuBar.add(subMenu);
		
		 jMenu.add(menuBar);
		 jMenu.add(Box.createHorizontalGlue());
		 jMenu.add(time);
		 for(MainMenuItems e:MainMenuItems.values()){
			 menuItem = new JMenuItem(e.toString());
			 menuItem.setName(e.toString());
			 menuItem.addActionListener(new JMenuActionListener(menuItem.getText()));
			 menuBar.add(menuItem);
		 }
		
		 for(ContactType c: ContactType.values()){
			 menuItem = new JMenuItem(c.toString());
			 menuItem.setName(c.toString());
			 menuItem.addActionListener(new JMenuActionListener(menuItem.getText()));
			 subMenu.add(menuItem);
		 }
		
		this.setJMenuBar(jMenu);
		 
		 
		 
	 }
	 private void initializeAddressBook() throws FileNotFoundException{
		  //addresses.populateAddressBook();
		  addresses.addContact(new Contact("John",new PhoneNumber(LineType.HOME,"3024564231")));
		   addresses.addContact(new Contact("Jimmy",new PhoneNumber(LineType.HOME,"3034564231")));
		   addresses.addContact(new Contact("Mary",new PhoneNumber(LineType.HOME,"3014564231")));
		   addresses.addContact(new Contact("Mr. Suez",new PhoneNumber(LineType.MOBILE1,"300964231")));
		   addresses.addContact(new Contact("Mr. Fill",new PhoneNumber(LineType.HOME,"3014564231")));
		   addresses.addContact(new Contact("Lottie",new PhoneNumber(LineType.BUSINESS,"3014564231")));
	  }

	 private class JMenuActionListener implements ActionListener{
	    private String buttonName;
		private JMenuActionListener(String buttonName){
			 this.buttonName = buttonName;
			 
		 }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(this.buttonName.equals(MainMenuItems.ADDCONTACT.toString())){
				
				AddContactPanel a = new AddContactPanel(addresses);
				JFrame c = new JFrame();
				c.setTitle(MainMenuItems.ADDCONTACT.toString());
				c.add(a);
				c.setLocationRelativeTo(null);
				c.setSize(new Dimension(200,200));
				c.setVisible(true);
				
			}
			else if(this.buttonName.equals(MainMenuItems.REMOVECONTACT.toString())){
				RemoveContactPanel a =new RemoveContactPanel(addresses);
				JFrame c = new JFrame();
				c.setTitle(MainMenuItems.REMOVECONTACT.toString());
				c.add(a);
				c.setLocationRelativeTo(null);
				c.setSize(new Dimension(200,200));
				c.setVisible(true);
			}
			else if(ContactType.valueOf(this.buttonName)!= null){
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
			
}

