package core.web.jstl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.servlet.tags.form.TagWriter;

@SuppressWarnings("serial")
public class SelectYN extends TagSupport {
	private String cssClass;
	
	private String cssStyle;
	
	private String id;
	
	private String name;
	
	private String caption;
	
	private String labelY = "Y";
	private String labelN = "N";
	
	private String emptyLabel;
	
	private String value;
	
	
	
	public String getCssClass() {
		return cssClass;
	}



	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}



	public String getCssStyle() {
		return cssStyle;
	}



	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCaption() {
		return caption;
	}



	public void setCaption(String caption) {
		this.caption = caption;
	}



	public String getLabelY() {
		return labelY;
	}



	public void setLabelY(String labelY) {
		this.labelY = labelY;
	}



	public String getLabelN() {
		return labelN;
	}



	public void setLabelN(String labelN) {
		this.labelN = labelN;
	}



	public String getEmptyLabel() {
		return emptyLabel;
	}



	public void setEmptyLabel(String emptyLabel) {
		this.emptyLabel = emptyLabel;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	@Override
	public int doStartTag() throws JspException {
		TagWriter writer = new TagWriter(pageContext);
		
		writer.startTag("select");
		
		if(id != null)
			writer.writeAttribute("id", id);
		if(cssClass != null)
			writer.writeAttribute("class", cssClass);
		if(cssStyle != null)
			writer.writeAttribute("style", cssStyle);
		if(name != null)
			writer.writeAttribute("name", name);
		if(caption != null)
			writer.writeAttribute("caption", caption);
		
		writer.startTag("option");
		writer.writeAttribute("value", "Y");
		if(value != null && value.equals("Y"))
			writer.writeAttribute("selected", "selected");
		writer.appendValue(labelY);
		writer.endTag();
		
		writer.startTag("option");
		writer.writeAttribute("value", "N");
		if(value != null && value.equals("N"))
			writer.writeAttribute("selected", "selected");
		writer.appendValue(labelN);
		writer.endTag();
		
		writer.endTag();
		
		return SKIP_BODY;
	}
}
