package core.util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 페이지정보 클래스
 *
 * @author HeartMedia
 */

/**
 * Required Fields
 * - 이 필드들은 페이징 계산을 위해 반드시 입력되어야 하는 필드 값들이다.
 *
 * currentPageNo : 현재 페이지 번호
 * recordCountPerPage : 한 페이지당 게시되는 게시물 건 수
 * pageSize : 페이지 리스트에 게시되는 페이지 건수,
 * totalRecordCount : 전체 게시물 건 수.
 */

public class HmPaginationInfo extends PaginationInfo{
	private String dbConnType = "";

	/** 현재 페이지 목록의 시작 번호 */
	private int currentPageStartNo;

	public HmPaginationInfo() {
		if( StringUtil.isEmpty(dbConnType) ) dbConnType = PropertiesUtil.getProperty("globals.db.type");
	}

	/**
     * 페이징 처음번호를 돌려준다
     * @param
     * @return  int
     * @exception
     */
	public int getFirstRecordIndex() {

		int firstRecordIndex = 0;

		if( "cubrid".equals(dbConnType) ) {
			firstRecordIndex = (getCurrentPageNo() * getRecordCountPerPage()) - (getRecordCountPerPage() - 1);
		} else if( "oracle".equals(dbConnType) ) {
			firstRecordIndex = ( getCurrentPageNo() - 1 ) * getRecordCountPerPage();
		} else if( "mysql".equals(dbConnType) ) {
			firstRecordIndex = ( getCurrentPageNo() - 1 ) * getRecordCountPerPage();
		}

		return firstRecordIndex;

	}

    /**
     * 페이징 마지막번호를 돌려준다
     * @param
     * @return  int
     * @exception
     */
	public int getLastRecordIndex() {

		int lastRecordIndex = 0;

		if( "cubrid".equals(dbConnType) ) {
			lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		} else if( "oracle".equals(dbConnType) ) {
			lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		} else if( "mysql".equals(dbConnType) ) {
			lastRecordIndex = getRecordCountPerPage();
		} else if( "mariadb".equals(dbConnType) ) {
			lastRecordIndex = getRecordCountPerPage();
		}

		return lastRecordIndex;

	}

    /**
     * 현재 페이지 목록의 시작 번호를 돌려준다
     * @param
     * @return  int
     * @exception
     */
	public int getCurrentPageStartNo() {
		currentPageStartNo = getTotalRecordCount() - getRecordCountPerPage() * (getCurrentPageNo() - 1);
		return currentPageStartNo;
	}

	public void setCurrentPageStartNo(int currentPageStartNo) {
		this.currentPageStartNo = currentPageStartNo;
	}

}
