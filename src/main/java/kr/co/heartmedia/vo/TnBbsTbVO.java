package kr.co.heartmedia.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("TnBbsTbVO")
public class TnBbsTbVO extends ComDefaultVO {

	private static final long serialVersionUID = -8806375071284782781L;

	/** 게시판_종류_코드 */
    private String bbsKndCode;
    /** 게시물_번호 */
    private Integer nttNo;
    /** 게시물_제목 */
    private String nttSj;
    /** 게시물_구분_코드 */
    private String nttSeCode;
    /** 업무_유형_코드 */
    private String jobTyCode;
    /** 관련_기관_명 */
    private String relateInsttNm;
    /** 게시물_일자 */
    private String nttDe;
    /** 게시물_내용_1 */
    private String nttCn1;
    /** 게시물_내용_2 */
    private String nttCn2;
    /** 키워드_1 */
    private String kwrd1;
    /** 키워드_2 */
    private String kwrd2;
    /** 키워드_3 */
    private String kwrd3;
    /** 키워드_4 */
    private String kwrd4;
    /** 키워드_5 */
    private String kwrd5;
    /** 키워드_6 */
    private String kwrd6;
    /** 키워드_7 */
    private String kwrd7;
    /** 키워드_8 */
    private String kwrd8;
    /** 키워드_9 */
    private String kwrd9;
    /** 키워드_10 */
    private String kwrd10;
    /** 첨부_파일_번호 */
    private Integer atchFileNo;
    /** 등록_사용자_아이디 */
    private String registUserId;
    /** 수정_사용자_아이디 */
    private String updateUserId;
    /** 등록_일시 */
    private Date registDt;
    /** 수정_일시 */
    private Date updateDt;

    /** 답변_일시 */
    private Date answerDt;

    /** 답변_등록자_아이디 */
    private String answerUserId;


    public void setBbsKndCode(String bbsKndCode) {
        this.bbsKndCode = bbsKndCode;
    }

    public String getBbsKndCode() {
        return bbsKndCode;
    }

    public void setNttNo(Integer nttNo) {
        this.nttNo = nttNo;
    }

    public Integer getNttNo() {
        return nttNo;
    }

    public void setNttSj(String nttSj) {
        this.nttSj = nttSj;
    }

    public String getNttSj() {
        return nttSj;
    }

    public void setNttSeCode(String nttSeCode) {
        this.nttSeCode = nttSeCode;
    }

    public String getNttSeCode() {
        return nttSeCode;
    }

    public void setJobTyCode(String jobTyCode) {
        this.jobTyCode = jobTyCode;
    }

    public String getJobTyCode() {
        return jobTyCode;
    }


    public void setNttCn1(String nttCn1) {
        this.nttCn1 = nttCn1;
    }

    public String getNttCn1() {
        return nttCn1;
    }

    public void setKwrd1(String kwrd1) {
        this.kwrd1 = kwrd1;
    }

    public String getKwrd1() {
        return kwrd1;
    }

    public void setKwrd2(String kwrd2) {
        this.kwrd2 = kwrd2;
    }

    public String getKwrd2() {
        return kwrd2;
    }

    public void setKwrd3(String kwrd3) {
        this.kwrd3 = kwrd3;
    }

    public String getKwrd3() {
        return kwrd3;
    }

    public void setKwrd4(String kwrd4) {
        this.kwrd4 = kwrd4;
    }

    public String getKwrd4() {
        return kwrd4;
    }

    public void setKwrd5(String kwrd5) {
        this.kwrd5 = kwrd5;
    }

    public String getKwrd5() {
        return kwrd5;
    }

    public void setKwrd6(String kwrd6) {
        this.kwrd6 = kwrd6;
    }

    public String getKwrd6() {
        return kwrd6;
    }

    public void setKwrd7(String kwrd7) {
        this.kwrd7 = kwrd7;
    }

    public String getKwrd7() {
        return kwrd7;
    }

    public void setKwrd8(String kwrd8) {
        this.kwrd8 = kwrd8;
    }

    public String getKwrd8() {
        return kwrd8;
    }

    public void setKwrd9(String kwrd9) {
        this.kwrd9 = kwrd9;
    }

    public String getKwrd9() {
        return kwrd9;
    }

    public void setKwrd10(String kwrd10) {
        this.kwrd10 = kwrd10;
    }

    public String getKwrd10() {
        return kwrd10;
    }

    public void setAtchFileNo(Integer atchFileNo) {
        this.atchFileNo = atchFileNo;
    }

    public Integer getAtchFileNo() {
        return atchFileNo;
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

    public void setRelateInsttNm(String relateInsttNm) {
        this.relateInsttNm = relateInsttNm;
    }

    public String getRelateInsttNm() {
        return relateInsttNm;
    }

    public void setNttDe(String nttDe) {
        this.nttDe = nttDe;
    }

    public String getNttDe() {
        return nttDe;
    }

    public void setNttCn2(String nttCn2) {
        this.nttCn2 = nttCn2;
    }

    public String getNttCn2() {
        return nttCn2;
    }

	public String getAnswerUserId() {
		return answerUserId;
	}

	public void setAnswerUserId(String answerUserId) {
		this.answerUserId = answerUserId;
	}

	public Date getAnswerDt() {
		return answerDt;
	}

	public void setAnswerDt(Date answerDt) {
		this.answerDt = answerDt;
	}




}