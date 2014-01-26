package cellPhone.frontend;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cellPhone.backend.Contact;

public class ContactDetailsPanel {
	private Contact aContact;
	private Image image;
	private JPanel jpTop;
	private JPanel jpDetails;
	
	private JScrollPane aPane;
	
	private JLabel jlbContact;
	private JLabel[] contactFields;
	public ContactDetailsPanel(Contact aContact) throws IOException {
		this.aContact = aContact;
		this.contactFields= new JLabel[3];
		this.jlbContact = new JLabel(aContact.getName());
		this.jlbContact.setIcon(new ImageIcon(getClass().getResource("contact_woman.png")));
		
		this.jpTop.add(new JLabel(aContact.getName()));
		
		
	}
	public void paint(Graphics pen){
		pen.drawImage(image, 50,50,null);
	}

}
