package kr.co.heartmedia.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("TnDeptTbVO")
public class TnDeptTbVO  extends ComDefaultVO {

	private static final long serialVersionUID = 4794510649070545872L;

	/**
	 * 부서_코드
	 */
	private String deptCode;
	/**
	 * 기관_코드
	 */
	private String insttCode;
	/**
	 * 최상위_부서_코드
	 */
	private String supperDeptCode;
	/**
	 * 상위_부서_코드
	 */
	private String upperDeptCode;
	/**
	 * 부서_명
	 */
	private String deptNm;
	/**
	 * 부서_전체_명
	 */
	private String deptAllNm;
	/**
	 * 조직_구분_코드
	 */
	private String orgnztSeCode;
	/**
	 * 조직_순서
	 */
	private String orgnztOrdr;
	/**
	 * 정렬_순서
	 */
	private String sortOrdr;
	/**
	 * 등록_사용자_아이디
	 */
	private String registUserId;
	/**
	 * 수정_사용자_아이디
	 */
	private String updateUserId;
	/**
	 * 등록_일시
	 */
	private Date registDt;
	/**
	 * 수정_일시
	 */
	private Date updateDt;
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getInsttCode() {
		return insttCode;
	}
	public void setInsttCode(String insttCode) {
		this.insttCode = insttCode;
	}
	public String getSupperDeptCode() {
		return supperDeptCode;
	}
	public void setSupperDeptCode(String supperDeptCode) {
		this.supperDeptCode = supperDeptCode;
	}
	public String getUpperDeptCode() {
		return upperDeptCode;
	}
	public void setUpperDeptCode(String upperDeptCode) {
		this.upperDeptCode = upperDeptCode;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getDeptAllNm() {
		return deptAllNm;
	}
	public void setDeptAllNm(String deptAllNm) {
		this.deptAllNm = deptAllNm;
	}
	public String getOrgnztSeCode() {
		return orgnztSeCode;
	}
	public void setOrgnztSeCode(String orgnztSeCode) {
		this.orgnztSeCode = orgnztSeCode;
	}
	public String getOrgnztOrdr() {
		return orgnztOrdr;
	}
	public void setOrgnztOrdr(String orgnztOrdr) {
		this.orgnztOrdr = orgnztOrdr;
	}
	public String getSortOrdr() {
		return sortOrdr;
	}
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	public String getRegistUserId() {
		return registUserId;
	}
	public void setRegistUserId(String registUserId) {
		this.registUserId = registUserId;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Date getRegistDt() {
		return registDt;
	}
	public void setRegistDt(Date registDt) {
		this.registDt = registDt;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}


}
