package kr.co.heartmedia.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("TsMenuTbVO")
public class TsMenuTbVO extends CommonVO {

	private static final long serialVersionUID = -6023611128258993384L;

	/** 메뉴_코드 */
    private String menuCode;
    /** 상위_메뉴_코드 */
    private String upperMenuCode;
    /** 메뉴_명 */
    private String menuNm;
    /** 정렬_순서 */
    private Integer sortOrdr;
    /** 메뉴_URL */
    private String menuUrl;
    /** 비고 */
    private String rm;
    /** 사용_여부 */
    private String useAt;
    /** 삭제_여부 */
    private String deleteAt;
    /** 등록_사용자_아이디 */
    private String registUserId;
    /** 수정_사용자_아이디 */
    private String updateUserId;
    /** 등록_일시 */
    private Date registDt;
    /** 수정_일시 */
    private Date updateDt;
    /** 새창 여부 */
    private String newWindowAt;

    public void setNewWindowAt(String newWindowAt) {
        this.newWindowAt = newWindowAt;
    }

    public String getNewWindowAt() {
        return newWindowAt;
    }


    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setUpperMenuCode(String upperMenuCode) {
        this.upperMenuCode = upperMenuCode;
    }

    public String getUpperMenuCode() {
        return upperMenuCode;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setSortOrdr(Integer sortOrdr) {
        this.sortOrdr = sortOrdr;
    }

    public Integer getSortOrdr() {
        return sortOrdr;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public String getRm() {
        return rm;
    }

    public void setUseAt(String useAt) {
        this.useAt = useAt;
    }

    public String getUseAt() {
        return useAt;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setRegistUserId(String registUserId) {
        this.registUserId = registUserId;
    }

    public String getRegistUserId() {
        return registUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

    public Date getRegistDt() {
        return registDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }
}