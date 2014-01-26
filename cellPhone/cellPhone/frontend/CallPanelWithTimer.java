package cellPhone.frontend;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import cellPhone.backend.Call;

public class CallPanelWithTimer extends JPanel{
	private Call call;
	public CallPanelWithTimer(Call call) {
		// TODO Auto-generated constructor stub
		this.call = call;
		//add(new JLabel(call.toString()));
		
		
	}
	 public void paintComponent(Graphics g) {
	        super.paintComponent(g);       

	        // Draw Text
	        g.drawString("Duration " + call.getTimer(),10,20);
	    } 
}
