package core.web.jstl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.servlet.tags.form.TagWriter;

import core.data.type.YesNoType;

@SuppressWarnings("serial")
public class CheckBoxYN extends TagSupport {
	private String cssClass;
	
	private String cssStyle;
	
	private String idSurfix;
	
	private String name;
	
	private String caption;
	
	private String labelY = "Y";
	
	private String labelN = "N";
	
	private Object selectedItems;
	
	
	
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



	public String getIdSurfix() {
		return idSurfix;
	}



	public void setIdSurfix(String idSurfix) {
		this.idSurfix = idSurfix;
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



	public Object getSelectedItems() {
		return selectedItems;
	}



	public void setSelectedItems(Object selectedItems) {
		this.selectedItems = selectedItems;
	}



	@Override
	public int doStartTag() throws JspException {
		TagWriter writer = new TagWriter(pageContext);
		
		int i=1;
		for(YesNoType yn : YesNoType.values()){
			writer.startTag("input");
			writer.writeAttribute("type", "checkbox");
			
			if(cssClass != null)
				writer.writeAttribute("class", cssClass);
			if(cssStyle != null)
				writer.writeAttribute("style", cssStyle);
			if(idSurfix != null)
				writer.writeAttribute("id", idSurfix + "_" + i);
			if(name != null)
				writer.writeAttribute("name", name);
			if(caption != null)
				writer.writeAttribute("caption", caption);
			
			writer.writeAttribute("value", yn.getValue());

			if(selectedItems != null){
				
				if(selectedItems instanceof String[]){
					String[] values = (String[]) selectedItems;
					
					for(int ii=0; ii<values.length; ii++){
						if(yn.getValue().equals(values[ii])){
							writer.writeAttribute("checked", "checked");
						}
					}
				}
				
				if(selectedItems instanceof String){
					String value = selectedItems.toString();
					
					if(yn.getValue().equals(value)){
						writer.writeAttribute("checked", "checked");
					}
				}
				
			}
			
			if(yn.equals(YesNoType.Yes))
				writer.appendValue(labelY);
			
			if(yn.equals(YesNoType.No))
				writer.appendValue(labelN);
			
			writer.endTag();
			i++;
		}
		
		return SKIP_BODY;
	}
}
