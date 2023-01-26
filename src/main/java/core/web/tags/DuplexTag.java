package core.web.tags;

import java.util.Map;

public class DuplexTag extends SimplexTag {
	
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DuplexTag(String tagName) {
		super(tagName);
	}
	
	public DuplexTag(String tagName, String value) {
		super(tagName);
		this.value = value;
	}
	
	public DuplexTag(String tagName, Map<String, String> attribute){
		super(tagName, attribute);
	}
	
	public DuplexTag(String tagName, String value, Map<String, String> attribute){
		super(tagName, attribute);
		this.value = value;
	}
	
	@Override
	public String toString() {
		String r = "<" + getTagName();
		
		for(String key : getAttribute().keySet()){
			r += " " + key + "=\"" + this.getAttribute().get(key) + "\"";
		}
		r += ">";
		if(value != null)
			r += value;
		r += "</" + getTagName() + ">";
		
		return r;
	}

}
