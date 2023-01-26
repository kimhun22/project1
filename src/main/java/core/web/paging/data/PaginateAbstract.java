package core.web.paging.data;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.exception.HMException;
import core.web.tags.A;

@SuppressWarnings("serial")
public abstract class PaginateAbstract extends TagSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaginateAbstract.class);

	protected String cssClass;
	protected String cssStyle;
	protected String id;
	protected Paging items;
	protected String linkScript;
	protected String linkUrl;
	protected String pageParam = "nowPage";



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

	public Paging getItems() {
		return items;
	}

	public void setItems(Paging items) {
		this.items = items;
	}

	public String getLinkScript() {
		return linkScript;
	}

	public void setLinkScript(String linkScript) {
		this.linkScript = linkScript;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getPageParam() {
		return pageParam;
	}

	public void setPageParam(String pageParam) {
		this.pageParam = pageParam;
	}

	@Override
	public abstract int doStartTag() throws JspException;

	protected String getUrlParams(Map<String, String[]> requestParams, int page){
		Map<String, String[]> newParams = new HashMap<String, String[]>();
		newParams.put(pageParam, new String[]{String.valueOf(page)});
		return getUrlParams(requestParams, newParams);
	}

	protected String getUrlParams(Map<String, String[]> requestParams, Map<String, String[]> params){
		Map<String, String[]> newParams = new HashMap<String, String[]>();
		newParams.putAll(requestParams);
		newParams.putAll(params);

		return getUrlParams(newParams);
	}

	protected String getUrlParams(Map<String, String[]> params){
		try {
			StringBuilder sb = new StringBuilder();

			List<String> stringParams = new ArrayList<String>();
			for(String key : params.keySet()){
				Object obj = params.get(key);
				if(obj instanceof String){
					stringParams.add(key + "=" +  URLEncoder.encode((String) obj, "UTF-8"));
				}

				if(obj instanceof String[]){
					String[] array = (String[]) obj;
					for(int i=0; i<array.length; i++){
						stringParams.add(key + "=" +  URLEncoder.encode(array[i], "UTF-8"));
					}
				}
			}

			sb.append(StringUtils.join(stringParams, "&"));

			return sb.toString();
		} catch (IllegalStateException e) {
			LOGGER.error(e.getMessage());
			throw new HMException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new HMException("페이징 에러");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map<String, String[]> getUrlParams(String[] excludeKeys){
		Map<String, String[]> requestParams = pageContext.getRequest().getParameterMap();
		Map params = new HashMap();

		for(String key : requestParams.keySet()){
			for(int i=0; i<excludeKeys.length; i++){
				if(!key.equals(excludeKeys[i]))
					params.put(key, requestParams.get(key));
			}
		}

		return params;
	}

	@SuppressWarnings({ "rawtypes" })
	public Map getParameterMap() {
		return Collections.unmodifiableMap(pageContext.getRequest().getParameterMap());
	}


	protected void createHref(A aTag, Map<String, String[]> params, int page){
		if(linkUrl != null || (linkUrl == null && linkScript == null)){
			aTag.setHref(linkUrl, getUrlParams(params, page));
		} else if(linkScript != null){
			aTag.setHref("javascript:" + linkScript + "('" + getUrlParams(params, page) + "');");
		}
	}

	protected String getHref(Map<String, String[]> params, int page){
		if(linkUrl != null || (linkUrl == null && linkScript == null)){
			if(linkUrl == null)
				return "?" + getUrlParams(params, page);
			else
				return linkUrl + "?" + getUrlParams(params, page);
		} else if(linkScript != null){
			return "javascript:" + linkScript + "('" + getUrlParams(params, page) + "');";
		} else {
			return "";
		}
	}
}
