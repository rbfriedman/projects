package cellPhone.frontend;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cellPhone.backend.AddressBook;
import cellPhone.backend.Contact;
import cellPhone.backend.LineType;
import cellPhone.backend.PhoneNumber;

public class AddContactPanel extends JPanel{
	private String name,email;
	private PhoneNumber mobile1,home,business,mobile2,fax;
	private JTextField jtfName,jtfMobile1,jtfHome,jtfBusiness,jtfMobile2,jtfFax,jtfEmail;
	private JLabel jlbName,jlbMobile1,jlbMobile2,jlbHome,jlbBusiness,jlbFax,jlbEmail;
	private JButton jbSubmit;
	private AddressBook addressBook;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private int textLength = 14;
	public AddContactPanel(AddressBook addressbook) {
		this.addressBook = addressBook;
		this.textLength = 16;
		this.topPanel = new JPanel();
		this.bottomPanel = new JPanel();
		GridLayout aGridLayout = new GridLayout(10,2);
		topPanel.setLayout(aGridLayout);
		
		jlbName = new JLabel("Name");
		jtfName = new JTextField(textLength);
		topPanel.add(jlbName);
		topPanel.add(jtfName);
		
		jlbMobile1 = new JLabel("Mobile 1");
		jtfMobile1 = new JTextField(textLength);
		topPanel.add(jlbMobile1);
		topPanel.add(jtfMobile1);
		
		jlbMobile2 = new JLabel("Mobile 2");
		jtfMobile2 = new JTextField(textLength);
		topPanel.add(jlbMobile2);
		topPanel.add(jtfMobile2);
		
		jlbHome = new JLabel("Home");
		jtfHome = new JTextField(textLength);
		topPanel.add(jlbHome);
		topPanel.add(jtfHome);
		
		jlbBusiness = new JLabel("Business");
		jtfBusiness = new JTextField();
		topPanel.add(jlbBusiness);
		topPanel.add(jtfBusiness);
		
		jlbFax = new JLabel("Fax");
		jtfFax = new JTextField(textLength);
		topPanel.add(jlbFax);
		topPanel.add(jtfFax);
		
		jlbEmail = new JLabel("Email");
		jtfEmail = new JTextField();
		topPanel.add(jlbEmail);
		topPanel.add(jtfEmail);
		//jtfName.setSize(30,8);
		jbSubmit = new JButton("Submit");
		jbSubmit.addActionListener(new AddContactListener());
		bottomPanel.add(jbSubmit);
		
		for(Component x:this.getComponents()){
			if(x.getClass().equals("JTextField")){
				x.setSize(100,8);
			}
		}
		this.add(topPanel);
		this.add(bottomPanel);
		
		
	}
	private class AddContactListener implements ActionListener{
		private String name;
		private String mobile1, mobile2,fax,home,business;
		private String address;
		private LineType lt;
		private Contact c;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boolean phoneNumberEntered = false;
			phoneNumberEntered = !jtfMobile1.getText().equals("") || !jtfFax.getText().equals("") || !jtfMobile2.getText().equals("")||
					!jtfHome.getText().equals("")|| !jtfBusiness.getText().equals("");
			if(!jtfName.getText().equals("") && phoneNumberEntered){
				Contact c = new Contact()
				this.name = jtfName.getText();
				
					

			}
			else{
				JOptionPane.showMessageDialog(null,"A Name and number must be entered");
			}
		}
		
	}

}
