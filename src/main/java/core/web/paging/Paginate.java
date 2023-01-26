package core.web.paging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import core.web.paging.data.Paging;
import core.web.tags.A;

public class Paginate extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 3343793229144299551L;

	static enum BLOCK_TEXT_TYPE{
		NONE(0), SQUARE(1), ROUND(2);

		private final int value;

		BLOCK_TEXT_TYPE(int value){
			this.value = value;
		}

		public int getValue(){
			return this.value;
		}
	}

	private String cssClass = "paging_area";

	private String cssStyle;

	private String id;

	private Paging items;

	private String firstPageText = "<<";

	private String prevPageText = "<";

	private String lastPageText = ">>";

	private String nextPageText = ">";

	private String blockTextType = "0";

	private String linkScript;

//	private String linkParam = null;

	private String linkUrl;

	private String pageParam = "nowPage";

//	private String pagePerRow = "p_rownum";

//	private String pagePerBlock = "p_blocknum";

//	private String seperator = "";



	@Override
	public int doStartTag() throws JspException {
		if(items == null)
			return SKIP_BODY;

		BLOCK_TEXT_TYPE blockType = BLOCK_TEXT_TYPE.NONE;

		if(blockTextType.equals("1") || blockTextType.equals("SQUARE"))
			blockType = BLOCK_TEXT_TYPE.SQUARE;
		else if(blockTextType.equals("2") || blockTextType.equals("ROUND"))
			blockType = BLOCK_TEXT_TYPE.ROUND;

		String prefixBlockText = "";
		String surfixBlockText = "";

		if(blockType == BLOCK_TEXT_TYPE.SQUARE){
			prefixBlockText = "[";
			surfixBlockText = "]";
		}
		else if(blockType == BLOCK_TEXT_TYPE.ROUND){
			prefixBlockText = "(";
			surfixBlockText = ")";
		}

		StringBuffer sb = new StringBuffer();

		sb.append("<div");
		if(cssClass != null)
			sb.append(" class=\"" + cssClass + "\"");
		if(cssStyle != null)
			sb.append(" style=\"" + cssStyle + "\"");
		sb.append(">");

		//1.링크만들기 페이지변수 전달 (page, p_rownum, p_blocknum)
		Map<String, String[]> requestParams = getUrlParams(new String[]{pageParam});

		//2.page seperator

		//맨처음, 이번블럭
		if(items.getPrevBlock() > 0){
			A firstPageA = new A(firstPageText);
			A prevPageA = new A(prevPageText);
			firstPageA.addAttribute("class", "btn_first");
			prevPageA.addAttribute("class", "btn_prev");

//			if(linkUrl != null || (linkUrl == null && linkScript == null)){
//				firstPageA.setHref(linkUrl, getUrlParams(requestParams, 1));
//				prevPageA.setHref(linkUrl, getUrlParams(requestParams, items.getPrevBlock()));
//			} else if(linkScript != null){
//				firstPageA.setHref("javascript:" + linkScript);
//				prevPageA.setHref("javascript:" + linkScript);
//			}
			createHref(firstPageA, requestParams, 1);
			createHref(prevPageA, requestParams, items.getPrevBlock());

			sb.append(firstPageA.toString());
			sb.append(prevPageA.toString());
		}

		//페이지
		for(int i=items.getStartPage(); i<=items.getEndPage(); i++){
			if(items.getNowPage() == i){
				sb.append("<strong>" + i + "</strong>");
			} else {
				A a = new A(prefixBlockText + i + surfixBlockText);
//				if(linkUrl != null || (linkUrl == null && linkScript == null)){
//					a.setHref(linkUrl, getUrlParams(requestParams, i));
//				} else if(linkScript != null){
//					a.setHref("javascript:" + linkScript);
//				}
				createHref(a, requestParams, i);
				sb.append(a.toString());
			}
		}

		//다음블럭, 마지막
		if(items.getNextBlock() > items.getEndPage()){
			A nextPageA = new A(nextPageText);
			A lastPageA = new A(lastPageText);
			nextPageA.addAttribute("class", "btn_next");
			lastPageA.addAttribute("class", "btn_last");

//			if(linkUrl != null || (linkUrl == null && linkScript == null)){
//				nextPageA.setHref(linkUrl, getUrlParams(requestParams, items.getNextBlock()));
//				lastPageA.setHref(linkUrl, getUrlParams(requestParams, items.getLastPage()));
//			} else if(linkScript != null){
//				nextPageA.setHref("javascript:" + linkScript);
//				lastPageA.setHref("javascript:" + linkScript);
//			}
			createHref(nextPageA, requestParams, items.getNextBlock());
			createHref(lastPageA, requestParams, items.getLastPage());

			sb.append(nextPageA.toString());
			sb.append(lastPageA.toString());
		}

		sb.append("</div>");

		try {
			pageContext.getOut().print(sb);
		} catch (IOException ioe) {
			throw new JspException("Error: " + ioe.getMessage());
		}

		return SKIP_BODY;
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

	public String getFirstPageText() {
		return firstPageText;
	}

	public void setFirstPageText(String firstPageText) {
		this.firstPageText = firstPageText;
	}

	public String getPrevPageText() {
		return prevPageText;
	}

	public void setPrevPageText(String prevPageText) {
		this.prevPageText = prevPageText;
	}

	public String getLastPageText() {
		return lastPageText;
	}

	public void setLastPageText(String lastPageText) {
		this.lastPageText = lastPageText;
	}

	public String getNextPageText() {
		return nextPageText;
	}

	public void setNextPageText(String nextPageText) {
		this.nextPageText = nextPageText;
	}

	public String getBlockTextType() {
		return blockTextType;
	}

	public void setBlockTextType(String blockTextType) {
		this.blockTextType = blockTextType;
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

	private String getUrlParams(Map<String, String[]> requestParams, int page){
		Map<String, String[]> newParams = new HashMap<String, String[]>();
		newParams.put(pageParam, new String[]{String.valueOf(page)});
		return getUrlParams(requestParams, newParams);
	}

	private String getUrlParams(Map<String, String[]> requestParams, Map<String, String[]> params){
		Map<String, String[]> newParams = new HashMap<String, String[]>();
		newParams.putAll(requestParams);
		newParams.putAll(params);

		return getUrlParams(newParams);
	}

	private String getUrlParams(Map<String, String[]> params){
		StringBuilder sb = new StringBuilder();

		List<String> stringParams = new ArrayList<String>();
		for(String key : params.keySet()){
			Object obj = params.get(key);
			if(obj instanceof String){
				stringParams.add(key + "=" +  (String) obj);
			}

			if(obj instanceof String[]){
				String[] array = (String[]) obj;
				for(int i=0; i<array.length; i++){
					stringParams.add(key + "=" +  array[i]);
				}
			}
		}

		sb.append(StringUtils.join(stringParams, "&"));

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private Map<String, String[]> getUrlParams(String[] excludeKeys){
		//StringBuilder sb = new StringBuilder();
		Map<String, String[]> requestParams = pageContext.getRequest().getParameterMap();
		Map<String, String[]> params = new HashMap<String, String[]>();

		for(String key : requestParams.keySet()){
			for(int i=0; i<excludeKeys.length; i++){
				if(!key.equals(excludeKeys[i]))
					params.put(key, requestParams.get(key));
			}
		}

		return params;
	}

	private void createHref(A aTag, Map<String, String[]> params, int page){
		if(linkUrl != null || (linkUrl == null && linkScript == null)){
			aTag.setHref(linkUrl, getUrlParams(params, page));
		} else if(linkScript != null){
			aTag.setHref("javascript:" + linkScript + "('" + getUrlParams(params, page) + "');");
		}
	}
}
