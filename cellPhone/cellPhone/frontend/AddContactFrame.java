package cellPhone.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cellPhone.backend.AddressBook;
import cellPhone.backend.Contact;
import cellPhone.backend.ContactType;
import cellPhone.backend.LineType;
import cellPhone.backend.PhoneNumber;

public class AddContactFrame extends JFrame{
	/*Parameters*/
	private ContactScreenPanel csPanel;
	private AddressBook addressBook;
	/*Informational Fields*/
	private String name,email;
	private PhoneNumber mobile1,home,business,mobile2,fax;
	private JTextField jtfName,jtfMobile1,jtfHome,jtfBusiness,jtfMobile2,jtfFax,jtfEmail,jtfAddress;
	private JLabel jlbName,jlbMobile1,jlbMobile2,jlbHome,jlbBusiness,jlbFax,jlbEmail,jlbAddress,jlbContactType;
	private JComboBox jcbContactType;
	
	private JButton jbSubmit;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private int textLength = 5;
	
	public AddContactFrame(AddressBook addressbook,ContactScreenPanel csPanel) {

		this.setTitle(ContactScreenMenuItems.ADDCONTACT.toString());
		
		this.setLocationRelativeTo(null);
		
		this.csPanel = csPanel;
		this.addressBook = addressbook;
		this.textLength = 16;
		JPanel outerTopPanel = new JPanel();
		this.topPanel = new JPanel();
		this.bottomPanel = new JPanel();
		this.setLayout(new BorderLayout());
		GridLayout aGridLayout = new GridLayout(10,2,3,3);
		topPanel.setSize(300,300);
		topPanel.setLayout(aGridLayout);
		
		jlbName = new JLabel("Name");
		jtfName = new JTextField(10);
		topPanel.add(jlbName);
		topPanel.add(jtfName);
		
		jlbMobile1 = new JLabel("Mobile 1");
		jtfMobile1 = new JTextField();
		topPanel.add(jlbMobile1);
		topPanel.add(jtfMobile1);
		
		jlbMobile2 = new JLabel("Mobile 2");
		jtfMobile2 = new JTextField();
		topPanel.add(jlbMobile2);
		topPanel.add(jtfMobile2);
		
		jlbHome = new JLabel("Home");
		jtfHome = new JTextField();
		topPanel.add(jlbHome);
		topPanel.add(jtfHome);
		
		jlbBusiness = new JLabel("Business");
		jtfBusiness = new JTextField();
		topPanel.add(jlbBusiness);
		topPanel.add(jtfBusiness);
		
		jlbFax = new JLabel("Fax");
		jtfFax = new JTextField();
		topPanel.add(jlbFax);
		topPanel.add(jtfFax);
		jlbContactType = new JLabel("Contact Type");
		jcbContactType = new JComboBox(ContactType.values());
		jcbContactType.setSelectedItem(ContactType.NOTAVAILABLE);
		topPanel.add(jlbContactType);
		topPanel.add(jcbContactType);
		//jcbContactType.
		jlbAddress = new JLabel("Address");
		jtfAddress = new JTextField();
		topPanel.add(jlbAddress);
		topPanel.add(jtfAddress);
		
		jlbEmail = new JLabel("Email");
		jtfEmail = new JTextField();
		topPanel.add(jlbEmail);
		topPanel.add(jtfEmail);
		//jtfName.setSize(30,8);
		jbSubmit = new JButton("Submit");
		jbSubmit.addActionListener(new AddContactListener());
		bottomPanel.add(jbSubmit);
		
		outerTopPanel.add(topPanel);
		this.add(outerTopPanel, BorderLayout.CENTER);
		
		this.add(bottomPanel,BorderLayout.SOUTH);
		this.setSize(new Dimension(400,500));
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	private class AddContactListener implements ActionListener{
		private String name;
		private String mobile1, mobile2,fax,home,business;
		private String address,email;
		private LineType lt;
		private ContactType ct;
		
		private Contact c;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boolean phoneNumberEntered = false;
			
			phoneNumberEntered = getFirstPhoneNumEntered().equals("")?false:true;
			
			if(!jtfName.getText().equals("") && phoneNumberEntered){
				this.name = jtfName.getText();
				
				Contact c = new Contact(this.name,new PhoneNumber(lt,getFirstPhoneNumEntered()));
				
				this.mobile1 = jtfMobile1.getText();
				c.setPhoneNumber(new PhoneNumber(LineType.MOBILE1,this.mobile1));
				this.mobile2 = jtfMobile2.getText();
				c.setPhoneNumber(new PhoneNumber(LineType.MOBILE2,this.mobile2));
				this.fax = jtfFax.getText();
				c.setPhoneNumber(new PhoneNumber(LineType.FAX,this.fax));
				this.home = jtfHome.getText();
				c.setPhoneNumber(new PhoneNumber(LineType.HOME,this.home));
				this.business = jtfBusiness.getText();
				c.setPhoneNumber(new PhoneNumber(LineType.BUSINESS,this.business));
				this.address = jtfAddress.getText();
				c.setAddress(this.address);
				this.email = jtfEmail.getText();
				c.setEmail(this.email);
				this.ct = ContactType.valueOf(jcbContactType.getSelectedItem().toString());
				c.setContactType(ct);
				System.out.println(c.toString());
				if(addressBook.addContact(c)){
					JOptionPane.showMessageDialog(null,c.toString() +" added");
					csPanel.addContactButton();
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"That name is already present in the addressbook");
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"A Name and number must be entered");
			}
		}
		String getFirstPhoneNumEntered(){
			if(!jtfMobile1.getText().equals("")){
				lt = LineType.MOBILE1;
				return jtfMobile1.getText();
			}
			else if(!jtfHome.getText().equals("")){
				lt = LineType.HOME;
				return jtfHome.getText();
			}
			else if(!jtfFax.getText().equals("")){
				lt = LineType.FAX;
				return jtfFax.getText();
			}
			else if(!jtfMobile2.getText().equals("")){
				lt = LineType.MOBILE2;
				return jtfMobile2.getText();
			}
			else if(!jtfBusiness.getText().equals("")){
				lt = LineType.BUSINESS;
				return jtfBusiness.getText();
			}
			else
				return "";
			
		}
		
	}

}
