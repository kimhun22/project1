package core.data.type;

public enum YesNoType {
	Yes("Y"), No("N");
	
	private final String value;
	
	private YesNoType(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
