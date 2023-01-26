package core.web.jstl;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.form.TagWriter;

import core.exception.HMException;


@SuppressWarnings("serial")
public class EnumSelector extends TagSupport {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnumSelector.class);

	private String enumClass;
	

	private String type;
	

	private String valueField = "";
	

	private String textField = "";
	

	private String name;
	

	private String idPrefix;
	

	private String cssClass;
	

	private String cssStyle;
	

	private String caption;
	

	private Object selectedItems;

	
	
	public String getEnumClass() {
		return enumClass;
	}

	public void setEnumClass(String enumClass) {
		this.enumClass = enumClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdPrefix() {
		return idPrefix;
	}

	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}

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

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
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
		
		try {
			Class<?> e = Class.forName(enumClass);
			
			if(e.isEnum()){
				Object[] objs = e.getEnumConstants();
				
				if(type.toLowerCase().equals("select")){
					writer.startTag("select");
					if(name != null)
						writer.writeAttribute("name", name);
					if(idPrefix != null)
						writer.writeAttribute("id", idPrefix);
					if(cssClass != null)
						writer.writeAttribute("class", cssClass);
					if(cssStyle != null)
						writer.writeAttribute("style", cssStyle);
					if(caption != null)
						writer.writeAttribute("caption", caption);
					
					for(int i=0; i < objs.length; i++){
						writer.startTag("option");
						
						Class<?> v = objs[i].getClass();
						
						String value; 
						if(valueField.equals("")){
							value = objs[i].toString();
						} else {
							Method mthValue = v.getDeclaredMethod("get" + valueField.toLowerCase().substring(0,1).toUpperCase() + valueField.toLowerCase().substring(1, valueField.length()));
							value = mthValue.invoke(objs[i]).toString();
						}
						writer.writeAttribute("value", value);
						checkSelectedItems(type, value, writer);
						
						if(textField.equals(""))
							writer.appendValue(objs[i].toString());
						else{
							Method mthText = v.getDeclaredMethod("get" + textField.toLowerCase().substring(0,1).toUpperCase() + textField.toLowerCase().substring(1, textField.length()));
							writer.appendValue(mthText.invoke(objs[i]).toString());
						}
						
						writer.endTag();
					}
					
					writer.endTag();
					
				} else if(type.toLowerCase().equals("checkbox") || type.toLowerCase().equals("radio")){
					for(int i=0; i < objs.length; i++){
						String idSuffix = objs[i].toString();
						
						writer.startTag("input");
						writer.writeAttribute("type", type);
						
						if(name != null)
							writer.writeAttribute("name", name);
						if(idPrefix != null)
							writer.writeAttribute("id", idPrefix + idSuffix);
						if(cssClass != null)
							writer.writeAttribute("class", cssClass);
						if(cssStyle != null)
							writer.writeAttribute("style", cssStyle);
						if(caption != null)
							writer.writeAttribute("caption", caption);
						
						Class<?> v = objs[i].getClass();
						
						String value; 
						if(valueField.equals("")){
							value = objs[i].toString();
						} else {
							Method mthValue = v.getDeclaredMethod("get" + valueField.toLowerCase().substring(0,1).toUpperCase() + valueField.toLowerCase().substring(1, valueField.length()));
							value = mthValue.invoke(objs[i]).toString();
						}
						writer.writeAttribute("value", value);
						checkSelectedItems(type, value, writer);
						
						writer.startTag("label");
						if(idPrefix != null)
							writer.writeAttribute("for", idPrefix + idSuffix);
						
						if(textField.equals(""))
							writer.appendValue(idSuffix);
						else{
							Method mthText = v.getDeclaredMethod("get" + textField.toLowerCase().substring(0,1).toUpperCase() + textField.toLowerCase().substring(1, textField.length()));
							writer.appendValue(mthText.invoke(objs[i]).toString());
						}
						writer.endTag();
						
					}
				} else {
					throw new RuntimeException("EnumSelector의 type은 select, checkbox, radio만 사용할 수 있습니다.");
				}
			}
			
		} catch (NullPointerException ne) {
			LOGGER.error(ne.getMessage());
			throw new HMException(ne);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return SKIP_BODY;
	}
	
	private void checkSelectedItems(String type, String value, TagWriter writer) throws Exception{
		if(selectedItems != null){
			
			if(selectedItems instanceof List<?>){
				@SuppressWarnings("unchecked")
				Iterator<Object> itr = ((List<Object>) selectedItems).iterator();
				while(itr.hasNext()){
					Object oValue = itr.next();
					if(oValue instanceof String){
						if(value.equals(oValue)){
							if(type.equals("select")){
								writer.writeAttribute("selected", "selected");
							} else {
								writer.writeAttribute("checked", "checked");
							}
						}
					}
				}
			}
			
			else if(selectedItems instanceof String[]){
				String[] sItems = (String[]) selectedItems;
				for(int ii=0; ii<sItems.length; ii++){
					if(value.equals(sItems[ii])){
						if(type.equals("select")){
							writer.writeAttribute("selected", "selected");
						} else {
							writer.writeAttribute("checked", "checked");
						}
					}
				}
			}
			
			else if(selectedItems instanceof String){
				String sItem = (String) selectedItems;
				if(value.equals(sItem)){
					if(type.equals("select")){
						writer.writeAttribute("selected", "selected");
					} else {
						writer.writeAttribute("checked", "checked");
					}
				} else {
					String[] items = selectedItems.toString().split(",");
					if(items.length > 1){
						for(String item : items){
							if(value.equals(item)){
								if(type.equals("select")){
									writer.writeAttribute("selected", "selected");
								} else {
									writer.writeAttribute("checked", "checked");
								}
							}
						}
					}
				}
			}
			
			else{
				if(selectedItems.toString().equals(value)){
					if(type.equals("select")){
						writer.writeAttribute("selected", "selected");
					} else {
						writer.writeAttribute("checked", "checked");
					}
				}
			}
		}
	}
}
