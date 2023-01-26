package core.web.jstl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.servlet.tags.form.TagWriter;

@SuppressWarnings("serial")
public class MultiUpload extends TagSupport {
	private TagWriter writer;
	
	
	private String name;
	
	
	private String popupUrl;
	
	
	private String uploadUrl;
	
	
	private String savePath;
	
	private Object value;
	
	
	private String addButtonValue = "추가";
	
	
	private Integer maxSize;
	
	
	private Integer maxFileNum;
	
	
	private String allowExts;
	
	
	private String denyExts;
	
	
	private String params;
	
	
	
	public TagWriter getWriter() {
		return writer;
	}

	public void setWriter(TagWriter writer) {
		this.writer = writer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPopupUrl() {
		return popupUrl;
	}

	public void setPopupUrl(String popupUrl) {
		this.popupUrl = popupUrl;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getAddButtonValue() {
		return addButtonValue;
	}

	public void setAddButtonValue(String addButtonValue) {
		this.addButtonValue = addButtonValue;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public Integer getMaxFileNum() {
		return maxFileNum;
	}

	public void setMaxFileNum(Integer maxFileNum) {
		this.maxFileNum = maxFileNum;
	}

	public String getAllowExts() {
		return allowExts;
	}

	public void setAllowExts(String allowExts) {
		this.allowExts = allowExts;
	}

	public String getDenyExts() {
		return denyExts;
	}

	public void setDenyExts(String denyExts) {
		this.denyExts = denyExts;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Override
	public int doStartTag() throws JspException {
		writer = new TagWriter(pageContext);
		
		writer.startTag("input");
		writer.writeAttribute("class", "hmcore-file-add");
		writer.writeAttribute("type", "button");
		writer.writeAttribute("name", name);
		writer.writeAttribute("uploadUrl", uploadUrl);
		writer.writeAttribute("popupUrl", popupUrl);
		if(savePath != null){
			writer.writeAttribute("savePath", savePath);
		}
		writer.writeAttribute("value", addButtonValue);
		if(maxSize != null && maxSize != 0)
			writer.writeAttribute("maxSize", maxSize.toString());
		if(maxFileNum != null && maxFileNum != 0)
			writer.writeAttribute("maxFileNum", maxFileNum.toString());
		if(allowExts != null)
			writer.writeAttribute("allowExts", allowExts);
		if(denyExts != null)
			writer.writeAttribute("denyExts", denyExts);
		if(params != null)
			writer.writeAttribute("params", params);
		writer.endTag();
		
		writer.startTag("input");
		writer.writeAttribute("type", "hidden");
		writer.writeAttribute("name", name);
		if(value != null)
			writer.writeAttribute("value", value.toString());
		//value
		writer.endTag();
		
		writer.startTag("div");
		writer.writeAttribute("class", "hmcore-file-area");
		writer.writeAttribute("name", name);
		writer.appendValue("");

		
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		writer.endTag();
		
		return SKIP_BODY;
	}
}
