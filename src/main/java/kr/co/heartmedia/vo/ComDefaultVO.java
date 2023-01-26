package kr.co.heartmedia.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class ComDefaultVO implements Serializable {
	/** 사이트 코드 */
	private String hmSiteCode;
	/** 검색조건 */
	private String searchCondition = "";
	/** 검색조건 */
	private String searchCondition2 = "";
	/** 검색조건 */
	private String searchCondition3 = "";
	/** 검색조건 부서 */
	private String searchDept = "";
	/** 검색Keyword */
	private String searchKeyword = "";
	/** 검색Keyword2 */
	private String searchKeyword2 = "";
	/** 검색Keyword3 */
	private String searchKeyword3 = "";
	/** 검색사용여부 */
	private String searchUseYn = "";
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
	/** 순번 */
	private int rnum;
	/** 최신글 역순을 위한 순번 */
	private int rn;
	/** 갯수 **/
	private int rcnt;
	/** 사용자 아이디 */
	private String userId;
	/** 작성자 아이디 */
	private String registerUserId;
	private String registUserId;
	private String registUserNm;
	/** 수정자 아이디 */
	private String updateUserId;
	private String updateUserNm;
	/** 작성일 */
	private String registerDate;
	private String registDate;
	/** 수정일 */
	private String updateDate;
	/** 파일 아이디 */
	private String attachedFileId;
	/**  */
	private String attachFlag;
	/** 사용자 이름 */
	private String userName;
	/** 사용자 이름 */
	private String[] userName_Arr;
	public String[] getUserName_Arr() {
		return userName_Arr;
	}

	public void setUserName_Arr(String[] userName_Arr) {
		this.userName_Arr = userName_Arr;
	}

	/** 사용자 비밀번호 */
	private String userPassword;
	/** 시작 일 */
	private String startDate ="";
	/** 종료 일 */
	private String endDate ="";
	/** 완료 목표 일  */
	private String dueDate;
	/** leftmenu 상태 */
	private String leftStatus;
	/** 부서코드 */
	private String partcode;
	/** 부서이름 */
	private String partname;
	/** 현재단계코드(프로세스) */
	private String currstatuscode;
	/** 팀코드 */
	private String groupCode;
	/** 팀이름 */
	private String groupName;
	/** 부서,팀 전체이름 */
	private String fullName;
	/** 관리자 유무 */
	private String adminYn;
	/** 운영중인 모드 확인 */
	private String runMode;

	private String positionCode;

	public String getHmSiteCode() {
		return hmSiteCode;
	}

	public void setHmSiteCode(String hmSiteCode) {
		this.hmSiteCode = hmSiteCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	private int totalCnt;

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getRunMode() {
		return runMode;
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public String getSearchDept() {
		return searchDept;
	}

	public void setSearchDept(String searchDept) {
		this.searchDept = searchDept;
	}

	public String getSearchKeyword2() {
		return searchKeyword2;
	}

	public void setSearchKeyword2(String searchKeyword2) {
		this.searchKeyword2 = searchKeyword2;
	}

	public String getSearchKeyword3() {
		return searchKeyword3;
	}

	public void setSearchKeyword3(String searchKeyword3) {
		this.searchKeyword3 = searchKeyword3;
	}

	public String getSearchCondition3() {
		return searchCondition3;
	}

	public void setSearchCondition3(String searchCondition3) {
		this.searchCondition3 = searchCondition3;
	}

	public String getAdminYn() {
		return adminYn;
	}

	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCurrstatuscode() {
		return currstatuscode;
	}

	public void setCurrstatuscode(String currstatuscode) {
		this.currstatuscode = currstatuscode;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserNm() {
		return updateUserNm;
	}

	public void setUpdateUserNm(String updateUserNm) {
		this.updateUserNm = updateUserNm;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getLeftStatus() {
		return leftStatus;
	}

	public void setLeftStatus(String leftStatus) {
		this.leftStatus = leftStatus;
	}

	public String getSearchCondition2() {
		return searchCondition2;
	}

	public void setSearchCondition2(String searchCondition2) {
		this.searchCondition2 = searchCondition2;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getAttachFlag() {
		return attachFlag;
	}

	public void setAttachFlag(String attachFlag) {
		this.attachFlag = attachFlag;
	}

	public String getAttachedFileId() {
		return attachedFileId;
	}

	public void setAttachedFileId(String attachedFileId) {
		this.attachedFileId = attachedFileId;
	}

	public String getRegisterUserId() {
		return registerUserId;
	}

	public void setRegisterUserId(String registerUserId) {
		this.registerUserId = registerUserId;
	}

	public String getRegistUserId() {
		return registUserId;
	}

	public void setRegistUserId(String registUserId) {
		this.registUserId = registUserId;
	}

	public String getRegistUserNm() {
		return registUserNm;
	}

	public void setRegistUserNm(String registUserNm) {
		this.registUserNm = registUserNm;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getRcnt() {
		return rcnt;
	}

	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}

	public String getPartcode() {
		return partcode;
	}

	public void setPartcode(String partcode) {
		this.partcode = partcode;
	}

	public String getPartname() {
		return partname;
	}

	public void setPartname(String partname) {
		this.partname = partname;
	}

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
