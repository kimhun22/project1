package core.data.type;

public enum ControllerTaskType {
	view("view"), list("list"), add("add"), edit("edit");
	
	private final String value;
	
	private ControllerTaskType(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
