package core.xssFilter;

import java.util.regex.Pattern;

import com.navercorp.lucy.security.xss.servletfilter.defender.Defender;

public class XSSFilter implements Defender {
	
	@Override
	public void init(String[] values) {
	}
	
	private static String[] fillterName = {
			 "innerHTML"
			,"javascript"	
			,"eval"
			,"onmousewheel"	
			,"onactive"
			,"onfocusout"
			,"expression"	
			,"charset"	
			,"ondataavailable"	
			,"oncut"	
			,"onkeyup"
			,"applet"	
			,"document"	
			,"onafteripudate"	
			,"onclick"	
			,"onkeypress"
			,"meta"
			,"xml"
			,"blink"
			,"link"
			,"script"
			,"embed"
			,"object"
			,"iframe"
			,"frame"
			,"frameset"
			,"ilayer"
			,"layer"
			,"bgsound"
			,"onbefore"
			,"onmouseup"
			,"onrowenter"
			,"oncontextmenu"
			,"string"
			,"create"
			,"append"
			,"binding"
			,"alert"
			,"msgbox"
			,"refresh"
			,"void"
			,"cookie"
			,"onpaste"
			,"onresize"
			,"onselect"
			,"base"
			,"onblur"
			,"onstart"
			,"onfocusin"
			,"onhelp"
			,"onmousedown"
			,"onbeforeactivate"
			,"onbeforecopy"
			,"onbeforedeactivate"
			,"ondatasetchaged"
			,"cnbeforeprint"
			,"cnbeforepaste"
			,"onbeforeeditfocus"
			,"onbeforeuload"
			,"onbeforeupdate"
			,"onpropertychange"
			,"ondatasetcomplete"
			,"oncellchange"
			,"onlayoutcomplete"
			,"onselectionchange"
			,"onrowsinserted"
			,"oncontrolselected"
			,"onreadystatechange"
			,"onchange"
			,"onbeforecut"
			,"ondbclick"
			,"ondeactivate"
			,"ondrag"
			,"ondragend"
			,"ondragenter"
			,"ondragleave"
			,"ondragover"
			,"ondragstart"
			,"ondrop"
			,"onerror"
			,"onfinish"
			,"onfocus"
			,"vbscript"
			,"onkeydown"
			,"onrowsdelete"
			,"onmouseleave"
			,"onload"
			,"onbounce"
			,"onmouseenter"
			,"onmouseout"
			,"onmouseover"
			,"onsubmit"
			,"onmouseend"
			,"onresizestart"
			,"onuload"
			,"onselectstart"
			,"onreset"
			,"onmove"
			,"onstop"
			,"onrowexit"
			,"onerrorupdate"
			,"onfilterchage"
			,"onlosecapture"
			,"onmousemove"
			
	};
	
	@Override
	public String doFilter(String value) {
		String data = value;
		
		if  ( data != null )  {
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			data = scriptPattern.matcher(data).replaceAll("");

			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			data = scriptPattern.matcher(data).replaceAll("");

			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			data = scriptPattern.matcher(data).replaceAll("");

			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			data = scriptPattern.matcher(data).replaceAll("");

			scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			data = scriptPattern.matcher(data).replaceAll("");

			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			data = scriptPattern.matcher(data).replaceAll("");

			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			data = scriptPattern.matcher(data).replaceAll("");

			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			data = scriptPattern.matcher(data).replaceAll("");
			
			scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			data = scriptPattern.matcher(data).replaceAll("");
			
			for( int i=0; i<fillterName.length; i++ ) {
				scriptPattern = Pattern.compile(fillterName[i], Pattern.CASE_INSENSITIVE);
				data = scriptPattern.matcher(data).replaceAll("");
			} 
			
		}
		
		return data;
	}
	
}
