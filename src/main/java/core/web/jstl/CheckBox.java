package core.web.jstl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.web.servlet.tags.form.TagWriter;

import core.exception.HMException;

@SuppressWarnings("serial")
public class CheckBox<T> extends TagSupport {
	
	Logger logger = (Logger) LogManager.getLogger(CheckBox.class);

	private String cssClass;
	
	private String cssStyle;
	
	private String idSurfix;
	
	private String name;
	
	private String caption;
	
	private List<T> items;
	
	private String itemLabel = "code";
	
	private String itemValue = "idx";
	
	private Object selectedItems;
	
	private String selectedItemsValue;
	
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



	public List<T> getItems() {
		//return items;
		List<T> itemsCp = new ArrayList<T>();
		itemsCp.addAll(items);
		return itemsCp;
	}



	public void setItems(List<T> items) {
		this.items = new ArrayList<T>();
		for  ( T data : items )  {
			this.items.add(data);
		}
	}



	public String getItemLabel() {
		return itemLabel;
	}



	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}



	public String getItemValue() {
		return itemValue;
	}



	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}



	public Object getSelectedItems() {
		return selectedItems;
	}



	public void setSelectedItems(Object selectedItems) {
		this.selectedItems = selectedItems;
	}



	public String getSelectedItemsValue() {
		return selectedItemsValue;
	}



	public void setSelectedItemsValue(String selectedItemsValue) {
		this.selectedItemsValue = selectedItemsValue;
	}



	@Override
	public int doStartTag() throws JspException {
		TagWriter writer = new TagWriter(pageContext);
		
		if(items != null){
			int i=1;
			for(T obj : items){
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
				
				try {
					String value = PropertyUtils.getProperty(obj, itemValue).toString();
					String label = PropertyUtils.getProperty(obj, itemLabel).toString();
					
					writer.writeAttribute("value", value);

					//selected items -->
					if(selectedItems instanceof List<?>){
						@SuppressWarnings("unchecked")
						Iterator<Object> itr = ((List<Object>) selectedItems).iterator();
						while(itr.hasNext()){
							Object oValue = itr.next();
							if(oValue instanceof String){
								if(value.equals(oValue)){
									writer.writeAttribute("checked", "checked");
								}
							} else {
								String sItem = "";
								if(selectedItemsValue != null){
									sItem = PropertyUtils.getProperty(oValue, selectedItemsValue).toString();
								} else {
									sItem = PropertyUtils.getProperty(oValue, itemValue).toString();
								}
								
								if(value.equals(sItem)){
									writer.writeAttribute("checked", "checked");
								}
							}
						}
					}
					
					if(selectedItems instanceof String[]){
						String[] sItems = (String[])selectedItems;
						for(int ii=0; ii<sItems.length; ii++){
							if(value.equals(sItems[ii])){
								writer.writeAttribute("checked", "checked");
							}
						}
					}
					
					if(selectedItems instanceof String){
						String sItem = (String) selectedItems;
						if(value.equals(sItem)){
							writer.writeAttribute("checked", "checked");
						}
					}
					// <-- selected items
					
					writer.appendValue(label);
				} catch (NullPointerException ne) {
					logger.error(ne.getMessage());
					throw new HMException(ne);
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new HMException("Custom Tag Exeption!", e);
				}
				
				i++;
			}
		}
		
		return SKIP_BODY;
	}
}
