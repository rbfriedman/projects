package cellPhone.backend;

public class PhoneOwner extends Contact{
	/*According to wikihow's guide for phone activation (via Verizon), only your SSN and phone number
	 * are needed to activate your phone, then Verizon will reboot and activate your phone within their service
	 * system
	 * Alternatively, Verizon can actiate your phone when you give your device ID
	 */
	private String name;
	private PhoneNumber phoneNum;
	private String ssn;
	private boolean activated;
	public PhoneOwner(String ssn, PhoneNumber phoneNum) {
		//When activating the phone through Verizon, you do not provide your contact name
		super(null,phoneNum);
		this.ssn= ssn;
		this.phoneNum = phoneNum;
		this.activated= true;
	}
	public void deactivatePhone(){
		this.activated= false;
	}
	public void setName(String name){
		this.name = name;
	}

}
