package core.web.jstl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.servlet.tags.form.TagWriter;

@SuppressWarnings("serial")
public class RadioYN extends TagSupport {
	private String cssClass;
	
	private String cssStyle;
	
	private String id;
	
	private String name;
	
	private String caption;
	
	private String labelYesText = "Y";
	private String labelYesClass;
	private String labelYesStyle;
	
	private String labelNoText = "N";
	private String labelNoClass;
	private String labelNoStyle;
	
	private String value;
	
	private String defaultChecked;
	
	
	
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

	public String getLabelYesText() {
		return labelYesText;
	}

	public void setLabelYesText(String labelYesText) {
		this.labelYesText = labelYesText;
	}

	public String getLabelYesClass() {
		return labelYesClass;
	}

	public void setLabelYesClass(String labelYesClass) {
		this.labelYesClass = labelYesClass;
	}

	public String getLabelYesStyle() {
		return labelYesStyle;
	}

	public void setLabelYesStyle(String labelYesStyle) {
		this.labelYesStyle = labelYesStyle;
	}

	public String getLabelNoText() {
		return labelNoText;
	}

	public void setLabelNoText(String labelNoText) {
		this.labelNoText = labelNoText;
	}

	public String getLabelNoClass() {
		return labelNoClass;
	}

	public void setLabelNoClass(String labelNoClass) {
		this.labelNoClass = labelNoClass;
	}

	public String getLabelNoStyle() {
		return labelNoStyle;
	}

	public void setLabelNoStyle(String labelNoStyle) {
		this.labelNoStyle = labelNoStyle;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultChecked() {
		return defaultChecked;
	}

	public void setDefaultChecked(String defaultChecked) {
		this.defaultChecked = defaultChecked;
	}

	@Override
	public int doStartTag() throws JspException {
		
		createRadioTag("Y", id, cssClass, cssStyle, name, caption, labelYesText, labelYesClass, labelYesStyle, value);
		createRadioTag("N", id, cssClass, cssStyle, name, caption, labelNoText, labelNoClass, labelNoStyle, value);
		
		return SKIP_BODY;
	}
	
	private void createRadioTag(String yn, String id, String cssClass, String cssStyle, String name, String caption, String labelText, String labelClass, String labelStyle, String value)  throws JspException{
		TagWriter writer = new TagWriter(pageContext);
		
		writer.startTag("input");
		writer.writeAttribute("type", "radio");
		writer.writeAttribute("value", yn);
		
		if(id != null)
			writer.writeAttribute("id", id + yn);
		if(cssClass != null)
			writer.writeAttribute("class", cssClass);
		if(cssStyle != null)
			writer.writeAttribute("style", cssStyle);
		if(name != null)
			writer.writeAttribute("name", name);
		if(caption != null)
			writer.writeAttribute("caption", caption);
		
		if(value != null && value.equals(yn))
			writer.writeAttribute("checked", "checked");
		
		if(value == null && defaultChecked.equals(yn)){
			writer.writeAttribute("checked", "checked");
		}
		
		writer.endTag();
		
		writer.startTag("label");
		if(id != null)
			writer.writeAttribute("for", id + yn);
		if(labelYesClass != null)
			writer.writeAttribute("class", labelClass);
		if(labelYesStyle != null)
			writer.writeAttribute("style", labelStyle);
		
		writer.appendValue(labelText);
		writer.endTag();
	}
}
