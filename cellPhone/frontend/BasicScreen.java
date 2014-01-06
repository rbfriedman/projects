package cellPhone.frontend;

import introToGUI.WaterBottleOptions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.text.DateFormatter;

import cellPhone.backend.Battery;
import cellPhone.backend.LoadImageApp;
import cellPhone.backend.Signal;

public class BasicScreen extends JPanel{
	
	private Battery battery;
	private Signal signal;
	public BasicScreen(){
		this.setBackground(Color.white);
		this.add(new LoadImageApp());
		this.setSize(new Dimension(380,100));
		this.repaint();
	
		

		
		
		
		
	}
	public JPanel bottomPanel(){
		JPanel pane = new JPanel();
		ImageIcon icon = createImageIcon("images/homeicon.png");
		JLabel l = new JLabel();
		l.setIcon(icon);
		Dimension size = l.getPreferredSize();
		l.setBounds(200, 300, (int)size.getWidth(), (int)size.getHeight());
		pane.add(l);
		this.repaint();
		return pane;
	}
	public void paintComponent(Graphics pen){
		super.paintComponent(pen);
		StringBuilder pictureFile= new StringBuilder();
		
		super.paintComponent(pen);
		battery = Battery.THREEBARS;
		signal = Signal.THREEBARS;
		switch(battery){
		case THREEBARS:pictureFile.append(".75Battery.png");
		}
		String dir = System.getProperty("user.dir");
        dir = dir  + "/GUI-Icons/";
        pictureFile.insert(0,dir);
        System.out.println("getting picture from file " + pictureFile);
        Image batteryImg = Toolkit.getDefaultToolkit().getImage(pictureFile.toString());
        pictureFile.replace(pictureFile.indexOf(dir) + dir.length(), pictureFile.length(), "");
		
        switch(signal){
		case THREEBARS:pictureFile.append("TransmissionSignal.png");
		}
        Image transmissionImg = Toolkit.getDefaultToolkit().getImage(pictureFile.toString());
		System.out.println("getting picture from file " + pictureFile);
		pen.drawImage(transmissionImg,20,15, this); 
		pen.drawImage(batteryImg,310,5 , this); 
		pen.drawChars(getCurrentTime().toCharArray(), 0, getCurrentTime().length(), 350, 15);

		}
	public String getCurrentTime(){
		GregorianCalendar time =new GregorianCalendar();
		//(time.get(Calendar.HOUR)%10==0?String.valueOf(time.get(Calendar.HOUR))+"0":
		
		int hour = ((time.get(Calendar.HOUR_OF_DAY)==0)?12:time.get(Calendar.HOUR_OF_DAY));
		
		String minutes = (time.get(Calendar.MINUTE)<10?"0" +String.valueOf(time.get(Calendar.MINUTE)):String.valueOf(time.get(Calendar.MINUTE)));
		System.out.println(hour +minutes);
		
		return hour+":" + time.get(Calendar.MINUTE);
		
	}
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = BasicScreen.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path );
            return null;
        }
    }

		
		

		

}
