package core.web.tags;

import java.util.Map;

public class A extends DuplexTag {
	private static final String TAG_NAME = "a";

	public A(){
		super(TAG_NAME);
	}
	
	public A(String value){
		super(TAG_NAME);
		setValue(value);
	}
	
	public A(Map<String, String> attribute){
		super(TAG_NAME, attribute);
	}
	
	public A(String value, Map<String, String> attribute){
		super(TAG_NAME, attribute);
		setValue(value);
	}
	
	public String getHref(){
		return getAttribute("href");
	}
	
	public void setHref(String href){
		addAttribute("href", href);
	}
	
	public void setHref(String url, String parameter){
		if(url == null)
			url = "";
		addAttribute("href", url + "?" + parameter);
	}
}
