package core.web.jstl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

import core.exception.HMException;

@SuppressWarnings("serial")
public class Select<T> extends TagSupport {
	
	private String cssClass;
	
	private String cssStyle;
	
	private String id;
	
	private String name;
	
	private String caption;
	
	private String size;
	
	private List<T> items;
	
	private String itemLabel = "name";
	
	private String itemValue = "code";
	
	private boolean useEmpty;
	
	private String emptyLabel;
	
	private List<Map<String, Object>> addItems;
	
	private Object selectedItems;
	
	private String selectedItemsValue;
	
	private String disabled;
	
	private String readonly;
	
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



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
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



	public boolean isUseEmpty() {
		return useEmpty;
	}



	public void setUseEmpty(boolean useEmpty) {
		this.useEmpty = useEmpty;
	}



	public String getEmptyLabel() {
		return emptyLabel;
	}



	public void setEmptyLabel(String emptyLabel) {
		this.emptyLabel = emptyLabel;
	}



	public List<Map<String, Object>> getAddItems() {
		//return items;
		List<Map<String, Object>> addItemsCp = new ArrayList<Map<String, Object>>();
		addItemsCp = addItems;
		return addItemsCp;
	}



	public void setAddItems(List<Map<String, Object>> addItems) {
		this.addItems = addItems;
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



	public String getDisabled() {
		return disabled;
	}



	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}



	public String getReadonly() {
		return readonly;
	}



	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}



	@Override
	public int doStartTag() throws JspException {
		TagWriter writer = new TagWriter(pageContext);
		
		writer.startTag("select");
		if(cssClass != null)
			writer.writeAttribute("class", cssClass);
		if(cssStyle != null)
			writer.writeAttribute("style", cssStyle);
		if(id != null)
			writer.writeAttribute("id", id);
		if(name != null)
			writer.writeAttribute("name", name);
		if(caption != null)
			writer.writeAttribute("caption", caption);
		if(size != null)
			writer.writeAttribute("size", size);
		if(disabled != null)
			writer.writeAttribute("disabled", disabled);
		if(readonly != null)
			writer.writeAttribute("readonly", readonly);
		
		//setting emptyLab
		if(emptyLabel == null){
			WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			MessageSource messageSource = (MessageSource)springContext.getBean("messageSource");
			emptyLabel = messageSource.getMessage("label.select", null, pageContext.getRequest().getLocale());
		}
		
		//empty label
		if(useEmpty){
			writer.startTag("option");
			writer.writeAttribute("value", "");
			writer.appendValue(":: " + emptyLabel + " ::");
			writer.endTag();
		}
		
		//Item Write
		if(items != null && items.size() > 0){
			for(T obj : items){
				writer.startTag("option");
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
									writer.writeAttribute("selected", "selected");
								}
							} else {
								String sItem = "";
								if(selectedItemsValue != null){
									sItem = PropertyUtils.getProperty(oValue, selectedItemsValue).toString();
								} else {
									sItem = PropertyUtils.getProperty(oValue, itemValue).toString();
								}
								
								if(value.equals(sItem)){
									writer.writeAttribute("selected", "selected");
								}
							}
						}
					}
					
					else if(selectedItems instanceof String[]){
						String[] sItems = (String[])selectedItems;
						for(int ii=0; ii<sItems.length; ii++){
							if(value.equals(sItems[ii])){
								writer.writeAttribute("selected", "selected");
							}
						}
					}
					
//					else if(selectedItems instanceof String){
//						String sItem = (String) selectedItems;
//						if(value.equals(sItem)){
//							writer.writeAttribute("selected", "selected");
//						}
//					}
					
					else if(selectedItems instanceof Object[]){
						String[] sItems = (String[])selectedItems;
						for(int ii=0; ii<sItems.length; ii++){
							if(value.equals(sItems[ii])){
								writer.writeAttribute("selected", "selected");
							}
						}
					}
					
					else {
						if(selectedItems != null){
							if(value.equals(selectedItems.toString())){
								writer.writeAttribute("selected", "selected");
							}
						}
					}
					// <-- selected items
					
					writer.appendValue(label);
				} catch (NullPointerException ne) {
					throw new HMException("Custom Tag Exeption!", ne);
				} catch (Exception e) {
					throw new HMException("Custom Tag Exeption!", e);
				}
				writer.endTag();
			}
		}
		
		//add itmes
		
		writer.endTag();
		
		return SKIP_BODY;
	}
}
