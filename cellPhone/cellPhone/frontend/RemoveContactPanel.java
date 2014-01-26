package cellPhone.frontend;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cellPhone.backend.AddressBook;

public class RemoveContactPanel extends JPanel{
	JLabel jlbRemove;
	JTextField jtfRemove;
	AddressBook addresses;
	ContactScreenPanel cSPanel;
	public RemoveContactPanel(AddressBook addresses, ContactScreenPanel cSPanel) {
		// TODO Auto-generated constructor stub
		this.cSPanel = cSPanel;
		this.addresses = addresses;
		this.setLayout(new GridLayout(1,2,2,2));
		jlbRemove = new JLabel("Name");
		jtfRemove = new JTextField(10);
		jtfRemove.addActionListener(new RemoveContactListener());
		this.add(jlbRemove);
		this.add(jtfRemove);
		
	}
	private class RemoveContactListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = jtfRemove.getText();
			int a =JOptionPane.showConfirmDialog(null,
					"Confirm", "Are you sure you would like to remove " +name +"?", JOptionPane.YES_NO_OPTION);
			
			boolean isRemoved =addresses.removeContact(name);
			
			if(a==0){
			if(isRemoved){
				cSPanel.removeContactButton(name);
				cSPanel.revalidate();
				
				JOptionPane.showMessageDialog(null, name + " was removed");
				jtfRemove.setText("");
			}
			else{
				JOptionPane.showMessageDialog(null, name + " could not be found");
			}
			}
		}
		
	}

}
