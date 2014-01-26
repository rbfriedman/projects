package cellPhone.frontend;

public enum CallMenuItems {
	RECEIVEDCALLS("Received Calls"),DIALEDCALLS("Dialed Calls"),MISSEDCALLS("Misssed Calls"),ALLCALLS("All Calls"),CONTACTSCREEN("Back to Contact Screen");
	private String desc;
	CallMenuItems(String desc){
		this.desc = desc;
	}
	public String toString(){
		return desc;
	}
}
