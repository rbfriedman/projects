package cellPhone.frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.Position;

import cellPhone.backend.AddressBook;
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
	/*AddressBook Information*/
	private AddressBook addressBook;
	private JButton[] contactList;
	private ContactScreenFrame csFrame;
	
	public ContactScreenPanel(AddressBook addressBook, ContactScreenFrame contactScreenFrame){
		//Intantiate the address book and contacts
		this.addressBook = addressBook;
		this.csFrame = contactScreenFrame;
		this.contactList = new JButton[this.addressBook.getNumContacts()];
		this.contactList =instantiateContacts();
		this.jtfSearch = new JTextField(20);
		this.jlbSearch = new JLabel("Go To");
		
		this.contactPanel = new JPanel();
		this.jlbTitle = new JLabel("Contact List");
		this.searchPanel = new JPanel();
		this.contactPanel =(contactsPanel(this.contactList));
		this.setBackground(Color.WHITE);
		this.jtfSearch.addActionListener(new SearchListener(this.csFrame));
		this.add(contactPanel);
		this.repaint();
	
		
	}
	
	private JPanel contactsPanel(JButton[] jbtContacts){
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
		for(int i=0; i <jbtContacts.length;i++){
			d.insets = new Insets(5,5,5,5);
			d.anchor = GridBagConstraints.LINE_START;
			d.gridx =0;
			d.gridy+=1;
			area.add(jbtContacts[i],d);
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
	private JButton[] instantiateContacts(){
		/*
		 * JRadioButton[] buttons = new JRadioButton[VerifyFormat.values().length];
		JRadioButton jrButton;
		String buttonText;
		for(VerifyFormat v:VerifyFormat.values()){
		buttonText = "Verify " + v.getDisplayMessage();
		jrButton = new JRadioButton(buttonText);
		buttons[v.ordinal()] =jrButton;
		buttons[v.ordinal()].setActionCommand(v.toString());
		}
		return buttons;
		 */
		JButton[] jbtContacts = new JButton[this.addressBook.getNumContacts()];
	
		Contact aContact;
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
			JOptionPane.showMessageDialog(null,c.toString());
		}
	 } 
	private class SearchListener implements ActionListener{
		private ContactScreenFrame csFrame;
		public SearchListener(ContactScreenFrame csf){
			this.csFrame = csf;
		}
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// use Regex to define non numerics
			if(!jtfSearch.getText().equals("")){
			
			Contact[] a = null;
			try{
				a[0] =addressBook.getContactByName(jtfSearch.getText());
			}
			catch(NullPointerException npe){
			
			try {
				a = addressBook.MatchingContacts(jtfSearch.getText(), jtfSearch.getText(), addressBook.orderContacts());
				/*Point focusPos =null;
				for(Component c:area.getComponents()){
					JLabel aLabel;
					if(c instanceof JLabel){
						aLabel =(JLabel) c;
						System.out.println(a[0].getName() + aLabel.getName());
						System.out.println(a[0].getName().equals(aLabel.getName()));
						if(aLabel.getName().equals(a[0].getName())){
							focusPos = (Point) c.getLocation();
						}
					}
					
				}
				*/
				aPane.getVerticalScrollBar().setValue(area.getComponent(addressBook.findPos(a[0])).getY());
			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}

			String b = "";
			for(Contact c:a){
				b+=c.toString() +"\n";
			}
			
			JOptionPane.showMessageDialog(null,b);
			}
			
		}
		
	}
}
