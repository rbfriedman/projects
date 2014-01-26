package cellPhone.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
//import javax.swing.Timer;

import cellPhone.backend.AddressBook;
import cellPhone.backend.Call;
import cellPhone.backend.CallType;
import cellPhone.backend.CellPhone;
import cellPhone.backend.Contact;

public class ContactScreenPanel extends JPanel{
	/*Components of screen*/
	private JPanel contactPanel;
	private JPanel searchPanel;
	private JPanel titlePanel;
	private JLabel jlbTitle;
	private JTextField jtfSearch;
	private JLabel jlbSearch;
	private JScrollPane aPane;
	private JPanel area;
	private JButton[] jbtContacts;
	/*AddressBook Information*/
	private CellPhone cellPhone;
	private JButton[] contactList;
	private CellPhoneFrame csFrame;
	private CallPanel callPanel;
	private AddressBook addressBook;
	
	public ContactScreenPanel(CellPhone cellPhone, CellPhoneFrame CellPhoneFrame){
		//Intantiate the address book and contacts
		this.cellPhone = cellPhone;
		this.csFrame = CellPhoneFrame;
		this.addressBook = cellPhone.getAdressBook();
		this.contactList = new JButton[this.addressBook.getNumContacts()];
		this.contactList =instantiateContacts();
		this.jtfSearch = new JTextField(20);
		this.jlbSearch = new JLabel("Go To");
		
		this.contactPanel = new JPanel();
		this.jlbTitle = new JLabel("Contact List");
		this.searchPanel = new JPanel();
		this.contactPanel =(getContactsPanel());
		this.setBackground(Color.WHITE);
		this.jtfSearch.addActionListener(new SearchListener(this.csFrame));
		this.add(contactPanel);
	
		
	}

	
	private JPanel getContactsPanel(){
		JPanel aPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		aPanel.setLayout(new GridBagLayout());
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx=2;
		c.insets = new Insets(5,5,5,5);
		c.gridy=0;
		aPanel.add(jlbTitle,c);
		area = new JPanel();
		area.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		for(int i=0; i <this.contactList.length;i++){
			d.insets = new Insets(5,5,5,5);
			d.anchor = GridBagConstraints.LINE_START;
			d.gridx =0;
			d.gridy+=1;
			area.add(this.contactList[i],d);
		}
		aPane = new JScrollPane(area);
		aPane.setPreferredSize(new Dimension(200, 200));
		aPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridwidth =3;
		c.gridy= 3;
		c.ipady=80;
		aPanel.add(aPane,c);
		
		
		jlbSearch.setIcon(new ImageIcon(getClass().getResource("Magnifying_glass_Icon.png")));
		//c.fill = GridBagConstraints.HORIZONTAL;
		
		searchPanel.add(jlbSearch);
		searchPanel.add(jtfSearch);
		c.gridx=0;
		c.gridy=4;
		c.ipady=0;
		aPanel.add(searchPanel,c);
		aPanel.setBackground(Color.WHITE);
		aPanel.setSize(new Dimension(300,300));
		return aPanel;
		
	}
	public void removeContactButton(String name){
		boolean found= false;
		for(int i=0; i <this.contactList.length; i++){
			if(found== true){
				this.contactList[i-1] = this.contactList[i];
				System.out.println((i) +this.contactList[i-1].getText());
			}
			if(this.contactList[i].getText().equals(name)){
				found = true;
				this.contactList[i] = null;
			}
		}
		this.remove(contactPanel);
		this.contactList = instantiateContacts();
		this.contactPanel = this.getContactsPanel();
		
		this.add(this.contactPanel);
		
		revalidate();
		
	}
	public void addContactButton(){
		this.remove(contactPanel);
		this.contactList = instantiateContacts();
		this.contactPanel = this.getContactsPanel();
		
		this.add(this.contactPanel);
		
		revalidate();
		
	}
	public CallPanel getCallPanel(){
		return this.callPanel;
	}
	private JButton[] instantiateContacts(){
		
		JButton[] jbtContacts = new JButton[this.addressBook.getNumContacts()];
	
		JButton jbtContact;
		for(int i=0; i<this.addressBook.getNumContacts();i++){
			ImageIcon icon;
			/*The icon for each phone should be corrsepondinding to the default line type*/
			switch(this.addressBook.getAllContacts()[i].getPhoneNumber().getLineType()){
			case MOBILE1: icon= new ImageIcon(getClass().getResource("cellPhone.png"));
			break;
			default:icon= new ImageIcon(getClass().getResource("cellPhone.png"));
			}
			jbtContact = new JButton(this.addressBook.orderContacts()[i].getName());
			
			jbtContact.setIcon(icon);
			jbtContacts[i] =jbtContact;
			jbtContacts[i].setBorder(BorderFactory.createEmptyBorder());
			jbtContacts[i].addActionListener(new JButtonContactListener(this.addressBook.orderContacts()[i]));
			
			
		}
		
		return jbtContacts;
	}

	public String getUserInput() {
		// TODO Auto-generated method stub
		return this.getUserInput();
	}
	 private class JButtonContactListener implements ActionListener{
		 private Contact c;
		 private JButtonContactListener(Contact c){
			 this.c =c;
		 }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Object[] options = {"Call","Exit"};
			int n = JOptionPane.showOptionDialog(null,
		c.toString(),
		"Call Contact", JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[0]);
			if(n==0){
				callPanel = new CallPanel(new Call(c.getPhoneNumber(),CallType.DIALED),cellPhone,csFrame);
				cellPhone.makeCall(c.getPhoneNumber());
				csFrame.setContentPanel(callPanel);
				csFrame.revalidate();
				/*
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						callPanel.repaint();
						csFrame.revalidate();
					}
				};

				new Timer(1000, taskPerformer).start();
				*/
			}
			
		}
		
	 } 
	private class SearchListener implements ActionListener{
		private CellPhoneFrame csFrame;
		public SearchListener(CellPhoneFrame csf){
			this.csFrame = csf;
		}
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// use Regex to define non numerics
			if(!jtfSearch.getText().equals("")){
			System.out.println("1)First Contacts are searched for a match, using full contact names\n" +
			"2)If that fails, a sublist of contacts is found and scrolled to");
			Contact[] a = null;
			try{
				a[0] =addressBook.getContactByName(jtfSearch.getText());
				System.out.println("Contact Found: " +a[0].toString());
			}
			catch(NullPointerException npe){
			
			try {
				
				a = addressBook.MatchingContacts(jtfSearch.getText(), jtfSearch.getText(), addressBook.orderContacts());
				aPane.getVerticalScrollBar().setValue(area.getComponent(addressBook.findPos(a[0])).getY());
				String b = "";
				for(Contact c:a){
					b+=c.toString() +"\n";
				}
				
				JOptionPane.showMessageDialog(null,b);
				
			
			} 
			catch (InputMismatchException IMME ) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "There are no contacts matching that entry!");
			}
			
			catch (ArrayIndexOutOfBoundsException AIOB) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "There are no contacts");
			}
			
			catch(Exception ex){
				ex.printStackTrace();
			}
			}
			
		
			
			}
			
		}
		
	}
	public JMenu getMenu() {
		// TODO Auto-generated method stub
		return this.callPanel.getMenu();
	}

}
