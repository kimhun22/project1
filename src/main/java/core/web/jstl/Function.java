package core.web.jstl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
	public static Integer ceil(double value){
		Double r = Math.ceil(value);
		return r.intValue();
	}
	
	public static String cutString(String string, int length, String tail){
		String r = string;
		if(length > 0 && string.length() > length){
			r = string.substring(0, length);
			if(tail != null)
				r += tail;
		}
		return r;
	}
	
	public static String nl2br(String string){
		return (string != null) ? string.replaceAll("\n", "<br/>") : null;
	}
	
	public static String replaceAll(String str, String regex, String replacement){
		return str.replaceAll(regex, replacement);
	}
	
//	public static String replaceAllByList(String str, List<String> list, String replacement){
//		
//	}
	
	public static String escapeHtml(String str){
		Matcher mat; 
		
		Pattern entity = Pattern.compile("<[^>]*>");
		mat = entity.matcher(str);
		str = mat.replaceAll("");
		
		return str.replaceAll("&nbsp;"," ");
	}
}
