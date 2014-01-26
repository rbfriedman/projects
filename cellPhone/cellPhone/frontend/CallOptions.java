package cellPhone.frontend;

public enum CallOptions {
	CALL("Call"),IGNORE("Ignore");
	private String message;
	private CallOptions(String mess){
		this.message = message;
	}
	public String toString(){
		return this.message;
	}
}
