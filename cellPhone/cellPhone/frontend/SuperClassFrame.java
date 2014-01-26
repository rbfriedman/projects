package cellPhone.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.Timer;

public class SuperClassFrame extends JFrame{
	private GregorianCalendar date;
	private JMenuBar jmenuBar;
	private Timer timer;
	private JLabel time;
	public SuperClassFrame(){
		this.date = new GregorianCalendar();
		timer = new Timer(1000, new updateClockAction());
		timer.start();
		this.jmenuBar = new JMenuBar();
		time = new JLabel(getCurrentTime());
	    time.setIcon(getBatteryImageIcon());
		jmenuBar.add(time);
		jmenuBar.add(Box.createHorizontalGlue());
		
		
		this.setBackground(Color.white);
		this.setSize(new Dimension(380,100));
		this.setVisible(true);
	
		

		
		
		
		
	}
	public JMenuBar getJMenuBar(){
		return this.jmenuBar;
	}
	protected  String getCurrentDate(){
		
	
		String timeOfDay ="";
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM DD hh:mm aa");
		timeOfDay =formatter.format(date.getTime());
		return timeOfDay;
		
	}

	protected  String getCurrentTime(){
		
	
		String timeOfDay ="";
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
		timeOfDay =formatter.format(date.getTime());
		return timeOfDay;
		
	}
	protected  ImageIcon getBatteryImageIcon(){
		return new ImageIcon(getClass().getResource("LowBattery.PNG"));
	}
	
	protected class  updateClockAction implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
		      // Assumes clock is a custom component
		      // OR
		      // Assumes clock is a JLabel
			  date =new GregorianCalendar(); 
			  time = new JLabel(getCurrentTime());
			  time.repaint();
		      getJMenuBar().repaint();
		    }
		}
	
	 public void paintComponent(Graphics g) {
	          
	        time = new JLabel(getCurrentTime());
	        
	        // Draw Text
	        g.drawString(getCurrentTime(),10,20);
	    } 
	

}

