package cellPhone.frontend;

public enum ContactScreenMenuItems {
	ADDCONTACT("Add Contact"),REMOVECONTACT("Remove Contact"),ORDERBYCONTACTS("Order All Contacts By Contact Type");
	private String desc;
	

ContactScreenMenuItems(String desc){
	this.desc = desc;
}
public String toString(){
	return desc;
}
}
