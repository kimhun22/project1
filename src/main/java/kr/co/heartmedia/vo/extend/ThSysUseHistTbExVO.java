package kr.co.heartmedia.vo.extend;

import org.apache.ibatis.type.Alias;

import kr.co.heartmedia.vo.ThSysUseHistTbVO;




/**
 * @name 시스템 이용이력 Extend VO
 * @table TH_SYS_USE_HIST_TB
 */

@Alias("ThSysUseHistTbExVO")
public class ThSysUseHistTbExVO extends ThSysUseHistTbVO {

	private static final long serialVersionUID = -5053748238340001884L;

	/** 검색조건 - 로그인 요청 여부 **/
	private String searchLoginRequstAt;
	/** 검색조건 - url 주소 **/
	private String searchRequstUrl;
	/** 검색조건 - 요청자 **/
	private String searchRqester;
	/** 검색조건 - 요청자 이름 **/
	private String searchRqesterNm;


	/** 검색조건 - 요청일시(시작) **/
	private String searchRequstStartDt;
	/** 검색조건 - 요청일시(종료) **/
	private String searchRequstEndDt;


	//paging
	/** 현재페이지 */
	private int pageIndex = 1;
	/** 페이지갯수 */
	private int pageUnit = 10;
	/** 페이지사이즈 */
	private int pageSize = 10;
	/** firstIndex */
	private int firstIndex = 1;
	/** lastIndex */
	private int lastIndex = 1;
	/** recordCountPerPage */
	private int recordCountPerPage = 10;

	/** 요청자 이름 */
	private String userNm;
	/** 요청자 부서 */
	private String deptNm;

	/** url 구분 case */
	private String urlSe;



	public String getSearchLoginRequstAt() {
		return searchLoginRequstAt;
	}
	public void setSearchLoginRequstAt(String searchLoginRequstAt) {
		this.searchLoginRequstAt = searchLoginRequstAt;
	}

	public String getSearchRequstUrl() {
		return searchRequstUrl;
	}
	public void setSearchRequstUrl(String searchRequstUrl) {
		this.searchRequstUrl = searchRequstUrl;
	}

	public String getSearchRqester() {
		return searchRqester;
	}
	public void setSearchRqester(String searchRqester) {
		this.searchRqester = searchRqester;
	}

	public String getSearchRequstStartDt() {
		return searchRequstStartDt;
	}
	public void setSearchRequstStartDt(String searchRequstStartDt) {
		this.searchRequstStartDt = searchRequstStartDt;
	}

	public String getSearchRequstEndDt() {
		return searchRequstEndDt;
	}
	public void setSearchRequstEndDt(String searchRequstEndDt) {
		this.searchRequstEndDt = searchRequstEndDt;
	}

	public String getSearchRqesterNm() {
		return searchRqesterNm;
	}
	public void setSearchRqesterNm(String searchRqesterNm) {
		this.searchRqesterNm = searchRqesterNm;
	}


	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getUrlSe() {
		return urlSe;
	}
	public void setUrlSe(String urlSe) {
		this.urlSe = urlSe;
	}



}