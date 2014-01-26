package cellPhone.frontend;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cellPhone.backend.CellPhone;
import cellPhone.backend.Contact;
import cellPhone.backend.LineType;
import cellPhone.backend.Network;
import cellPhone.backend.NumberAlreadyEnteredException;
import cellPhone.backend.PhoneNumber;
import cellPhone.backend.PhoneOwner;

public class RunCellPhone {
	private PhoneNumber aPhone;
	private Network aNetwork;
	private Contact contact; 
	private CellPhone aCell;
	private CellPhoneFrame aFrame;
	private String fileName;
	private static int numCellPhones=0;
	public RunCellPhone(String name,String fileName,Network network,PhoneNumber phoneNum) throws ClassNotFoundException, IOException,FileNotFoundException, NumberAlreadyEnteredException {
		// TODO Auto-generated constructor stub
		aPhone = phoneNum;
		aNetwork = network;
		contact= new PhoneOwner(name,aPhone);
		this.fileName = fileName;
		
		try {
			this.readFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.aNetwork.addNumberToNetwork(aCell);
		aFrame.addWindowListener(new SerializeObjectListener());
		this.numCellPhones++;
		
	}
	public RunCellPhone(String name,String fileName,Network network) throws ClassNotFoundException, FileNotFoundException, IOException, NumberAlreadyEnteredException{
		this(name,fileName,network,PhoneNumber.generateRandomPhone());
	}
	
	public static void main(String[] args) throws NumberAlreadyEnteredException, FileNotFoundException{
		  Network aNetwork = new Network();
		  PhoneNumber aPhone = new PhoneNumber(LineType.MOBILE1,"3016513831");
		  PhoneNumber anotherPhone = new PhoneNumber(LineType.MOBILE1,"9176823411");
		
		  Contact rena = new PhoneOwner("Rena Friedman",aPhone);
		  Contact Professor =new PhoneOwner("Professor Plonczack",anotherPhone);
		  RunCellPhone a = null,b=null;

		  try{
		  a= new RunCellPhone(rena.getName(),"./renaCellPhone.ser",aNetwork,rena.getPhoneNumber());
		  
		  //a.printValidationMessage();
		  }
		   catch(IOException io){
			   JOptionPane.showMessageDialog(null, "file not found - call IT");
			   System.exit(0);
		   }
		   catch(ClassNotFoundException cnf){
			   JOptionPane.showMessageDialog(null,"class discrepancy");
			   System.exit(0);
		   }
		  catch(Exception e){
			   JOptionPane.showMessageDialog(null,"incorrect file names couldnt set up phone");
			   e.printStackTrace();
			   System.exit(0);
		   }
		  try{
		  b = new RunCellPhone(Professor.getName(),"./professorCellPhone.ser",aNetwork,Professor.getPhoneNumber());
		  
		  //b.printValidationMessage();
			  }
		  catch(IOException io){
			   JOptionPane.showMessageDialog(null, "file not found - call IT");
			   System.exit(0);
		   }
		   catch(ClassNotFoundException cnf){
			   JOptionPane.showMessageDialog(null,"class discrepancy");
			   System.exit(0);
		   }
		  catch(Exception e){
			   JOptionPane.showMessageDialog(null,"incorrect file names couldnt set up bank");
			   System.exit(0);
		   }
		  try{
			  System.out.println("Adding Contacts...");
			  a.aCell.addContact(Professor.getName(),Professor.getPhoneNumber());
			
			  a.aFrame.refresh();
			  b.aCell.addContact(rena.getName(), rena.getPhoneNumber());
			  
			  b.aFrame.refresh();
			  System.out.println("Added");
		  }
		  catch(NumberAlreadyEnteredException e){
			  System.out.println("Numbers already added");
		  }
		  
		   
		
		 
		  
	  }	

	  
	public void printValidationMessage(){
		System.out.println( this.getClass().toString() +"\n[ " +this.toString() + "]");
		
	}
	public String getFileName(){
		return  this.fileName;
	}
	public void readFile() throws Exception{
		File aFile = new File(fileName);
		if (!aFile.exists()){   //this is a new cell  , no pre-existing data
			   try{
				 aCell = new CellPhone((PhoneOwner)contact,aNetwork);
			     aFrame = new CellPhoneFrame(aCell);
			     
			   }
			   catch(Exception e){
				   throw e;
			   }
		   }
		   else {
			   try{
			   FileInputStream fileIn = new FileInputStream(fileName)	;
			   ObjectInputStream in = new ObjectInputStream(fileIn);
			   aCell = (CellPhone) in.readObject();
			   aFrame = new CellPhoneFrame(aCell);
			   in.close();
			   fileIn.close();
			   }
			   catch(IOException io){
				   throw io;
			   }
			   catch(ClassNotFoundException cnf){
				   throw cnf;
			   }
		   }
	}
	public String toString(){
		String info ="";
		for(int i=0; i <this.getClass().getFields().length;i++){
			try {
				info +=this.getClass().getField(this.getClass().getFields()[i].toString()).toString();
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return info;
	}
	private int getNumCellPhones() {
		// TODO Auto-generated method stub
		return this.numCellPhones;
	}
	private class SerializeObjectListener implements WindowListener{
		

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			try{
			     System.out.println("saving data to disk");
			     FileOutputStream c = new FileOutputStream(getFileName());
			     System.out.println("saving data to disk");
			     ObjectOutputStream out = new ObjectOutputStream(c);
			     System.out.println("saving data to disk");
			     out.writeObject(aCell);
			     System.out.println("saving data to disk");
			     out.close();  //close the stream
			     c.close();  //close the file pointing to the stream
			     aFrame.dispose();
			    if(getNumCellPhones()==1){
			    	 System.exit(0);
			    	 
			     }
			     else{
			    	 numCellPhones--;
			    	 aFrame.dispose();
			     }
			     
		     }
			catch(IOException io){
				io.printStackTrace();
				JOptionPane.showMessageDialog(null,io.getMessage());
				System.out.println("Failed");
			} 
		

			
		}

		

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		}		

}
