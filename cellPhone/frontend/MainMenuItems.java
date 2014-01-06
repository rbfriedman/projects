package cellPhone.frontend;

public enum MainMenuItems {
	ADDCONTACT("Add Contact"),REMOVECONTACT("Remove Contact");
	private String desc;
	

MainMenuItems(String desc){
	this.desc = desc;
}
public String toString(){
	return desc;
}
}
