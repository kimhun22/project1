package kr.co.heartmedia.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("TsCmmnCodeTbVO")
public class TsCmmnCodeTbVO extends CommonVO {

	private static final long serialVersionUID = 7586032400592443287L;

	/** 부모_공통_코드 */
    private String parntsCmmnCode;
    /** 공통_코드 */
    private String cmmnCode;
    /** 공통_코드_명 */
    private String cmmnCodeNm;
    /** 상위_부모_공통_코드 */
    private String upperParntsCmmnCode;
    /** 상위_공통_코드 */
    private String upperCmmnCode;
    /** 정렬_순서 */
    private Integer sortOrdr;
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
    /** 단계  코드*/
    private String stepCode;
    /** 레벨 코드  */
    private String levelStep;



    public String getStepCode() {
		return stepCode;
	}

	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}

	public String getLevelStep() {
		return levelStep;
	}

	public void setLevelStep(String levelStep) {
		this.levelStep = levelStep;
	}

	public void setParntsCmmnCode(String parntsCmmnCode) {
        this.parntsCmmnCode = parntsCmmnCode;
    }

    public String getParntsCmmnCode() {
        return parntsCmmnCode;
    }

    public void setCmmnCode(String cmmnCode) {
        this.cmmnCode = cmmnCode;
    }

    public String getCmmnCode() {
        return cmmnCode;
    }

    public void setCmmnCodeNm(String cmmnCodeNm) {
        this.cmmnCodeNm = cmmnCodeNm;
    }

    public String getCmmnCodeNm() {
        return cmmnCodeNm;
    }

    public void setUpperParntsCmmnCode(String upperParntsCmmnCode) {
        this.upperParntsCmmnCode = upperParntsCmmnCode;
    }

    public String getUpperParntsCmmnCode() {
        return upperParntsCmmnCode;
    }

    public void setUpperCmmnCode(String upperCmmnCode) {
        this.upperCmmnCode = upperCmmnCode;
    }

    public String getUpperCmmnCode() {
        return upperCmmnCode;
    }

    public void setSortOrdr(Integer sortOrdr) {
        this.sortOrdr = sortOrdr;
    }

    public Integer getSortOrdr() {
        return sortOrdr;
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