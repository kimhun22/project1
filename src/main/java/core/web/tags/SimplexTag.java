package core.web.tags;

import java.util.HashMap;
import java.util.Map;

public class SimplexTag {
	
	private String tagName;
	
	private Map<String, String> attribute;
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Map<String, String> getAttribute() {
		Map<String, String> attributeCp = new HashMap<String, String>();
		attributeCp = attribute;
		return attributeCp;
		//return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	public SimplexTag(String tagName){
		this.tagName = tagName;
		this.attribute = new HashMap<String, String>();
	}
	
	public SimplexTag(String tagName, Map<String, String> attribute){
		this.tagName = tagName;
		this.attribute = attribute;
	}
	
	public void addAttribute(String key, String value){
		this.attribute.put(key, value);
	}
	
	public String getAttribute(String key){
		return this.attribute.get(key);
	}
	
	public String toString(){
		String r = "<" + tagName;
		
		for(String key : this.attribute.keySet()){
			r += " " + key + "=\"" + this.attribute.get(key) + "\"";
		}
		
		r += " />";
		return r;
	}
}
