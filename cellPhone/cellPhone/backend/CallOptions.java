package cellPhone.backend;

public enum CallOptions {
	IGNORE("Ignore"), ANSWER("Answer");
	private String message;
	private CallOptions(String mess){
		this.message = mess;
	}
	public String toString(){
		return this.message;
	}
}
