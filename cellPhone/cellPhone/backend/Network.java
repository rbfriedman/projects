package cellPhone.backend;

import java.io.Serializable;

public class Network implements Serializable  {
	private CellPhone[] phoneOwners;
	private int numPhones;
	private boolean connectionEstablished;
	public Network() {
		this.phoneOwners = new CellPhone[20];
		this.numPhones =0;
	}
	public boolean addNumberToNetwork(CellPhone phoneOwner){
		if(numPhones<this.phoneOwners.length){
			this.phoneOwners[this.numPhones++] = phoneOwner;
			return true;
		}
		return false;
	}
	public void setConnectionEstablished(CellPhone a, CellPhone b){
		b.dialCall(a.getPhoneOwner().getPhoneNumber());
		a.receiveCall(b.getPhoneOwner().getPhoneNumber());
		a.isRinging(b.getPhoneOwner().getPhoneNumber());
		System.out.println(this.getClass().toString() +" curently dialing, using network: \n"+ this.toString());
		this.connectionEstablished = true;
	}
	public void detachConnection(CellPhone a, CellPhone b){
		b.endCall(a.getPhoneOwner().getPhoneNumber());
		a.endCall(b.getPhoneOwner().getPhoneNumber());
		System.out.println(this.getClass().toString() +" call disconnected, using network: \n"+ this.toString());
		this.connectionEstablished = false;
	}

	
	public CellPhone isWithinNetwork(String phoneNum) throws NullPointerException {
		
		for(int i=0; i <this.numPhones;i++){
			if(phoneNum.equals(this.phoneOwners[i].getPhoneOwner().getPhoneNumber().getPhoneNum())){
				return this.phoneOwners[i];
			}
		}
		return null;
	}
	public String toString(){
		String info =this.numPhones+ " ";
		for(int i=0; i <this.numPhones; i++){
			
			info += this.phoneOwners[i].toString();
			
		}
		return info;
	}
	
}
