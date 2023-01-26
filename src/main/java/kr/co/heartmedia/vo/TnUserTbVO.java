package kr.co.heartmedia.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("TnUserTbVO")
public class TnUserTbVO extends CommonVO {

	private static final long serialVersionUID = 1363247387216037883L;

	/** 로그인_아이디 */
    private String loginId;
    /** 사용자_명 */
    private String userNm;
    /** 사용자_패스워드 */
    private String userPwd;
    /** 근무_상태_코드 */
    private String workSttusCode;
    /** 근무_상태_명 */
    private String workSttusNm;
    /** 기관_코드 */
    private String insttCode;
    /** 기관_코드_명 */
    private String insttCodeNm;
    /** 상위_부서_코드 */
    private String upperDeptCode;
    /** 부서_코드 */
    private String deptCode;
    /** 부서_명 */
    private String deptNm;
    /** 부서_전체_명 */
    private String deptAllNm;
    /** 직급_코드 */
    private String clsfCode;
    /** 직급_명 */
    private String clsfNm;
    /** 직위_코드 */
    private String ofcpsCode;
    /** 직위_명 */
    private String ofcpsNm;
    /** 직무_코드 */
    private String dtyCode;
    /** 직무_명 */
    private String dtyNm;
    /** 계급_코드 */
    private String clssCode;
    /** 계급_명 */
    private String clssNm;
    /** 이메일_주소 */
    private String emailAdres;
    /** 전화_번호 */
    private String tlphonNo;
    /** 팩스_번호 */
    private String faxNo;
    /** 사용_IP */
    private String useIp;
    /** 입사_일자 */
    private String ecnyDe;
    /** 상위_팀_코드 */
    private String upperTeamCode;
    /** 팀_코드 */
    private String teamCode;
    /** 팀_명 */
    private String teamNm;
    /** 팀_여부 */
    private String teamAt;
    /** 팀_전체_코드_경로 */
    private String teamAllCodePath;
    /** 팀_전체_명 */
    private String teamAllNm;
    /** 사용_여부 */
    private String useAt;
    /** 등록_사용자_아이디 */
    private String registUserId;
    /** 수정_사용자_아이디 */
    private String updateUserId;
    /** 등록_일시 */
    private Date registDt;
    /** 수정_일시 */
    private Date updateDt;


    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setWorkSttusCode(String workSttusCode) {
        this.workSttusCode = workSttusCode;
    }

    public String getWorkSttusCode() {
        return workSttusCode;
    }

    public void setWorkSttusNm(String workSttusNm) {
        this.workSttusNm = workSttusNm;
    }

    public String getWorkSttusNm() {
        return workSttusNm;
    }

    public void setInsttCode(String insttCode) {
        this.insttCode = insttCode;
    }

    public String getInsttCode() {
        return insttCode;
    }

    public void setInsttCodeNm(String insttCodeNm) {
        this.insttCodeNm = insttCodeNm;
    }

    public String getInsttCodeNm() {
        return insttCodeNm;
    }

    public void setUpperDeptCode(String upperDeptCode) {
        this.upperDeptCode = upperDeptCode;
    }

    public String getUpperDeptCode() {
        return upperDeptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptAllNm(String deptAllNm) {
        this.deptAllNm = deptAllNm;
    }

    public String getDeptAllNm() {
        return deptAllNm;
    }

    public void setClsfCode(String clsfCode) {
        this.clsfCode = clsfCode;
    }

    public String getClsfCode() {
        return clsfCode;
    }

    public void setClsfNm(String clsfNm) {
        this.clsfNm = clsfNm;
    }

    public String getClsfNm() {
        return clsfNm;
    }

    public void setOfcpsCode(String ofcpsCode) {
        this.ofcpsCode = ofcpsCode;
    }

    public String getOfcpsCode() {
        return ofcpsCode;
    }

    public void setOfcpsNm(String ofcpsNm) {
        this.ofcpsNm = ofcpsNm;
    }

    public String getOfcpsNm() {
        return ofcpsNm;
    }

    public void setDtyCode(String dtyCode) {
        this.dtyCode = dtyCode;
    }

    public String getDtyCode() {
        return dtyCode;
    }

    public void setDtyNm(String dtyNm) {
        this.dtyNm = dtyNm;
    }

    public String getDtyNm() {
        return dtyNm;
    }

    public void setClssCode(String clssCode) {
        this.clssCode = clssCode;
    }

    public String getClssCode() {
        return clssCode;
    }

    public void setClssNm(String clssNm) {
        this.clssNm = clssNm;
    }

    public String getClssNm() {
        return clssNm;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setTlphonNo(String tlphonNo) {
        this.tlphonNo = tlphonNo;
    }

    public String getTlphonNo() {
        return tlphonNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setUseIp(String useIp) {
        this.useIp = useIp;
    }

    public String getUseIp() {
        return useIp;
    }

    public void setEcnyDe(String ecnyDe) {
        this.ecnyDe = ecnyDe;
    }

    public String getEcnyDe() {
        return ecnyDe;
    }

    public void setUpperTeamCode(String upperTeamCode) {
        this.upperTeamCode = upperTeamCode;
    }

    public String getUpperTeamCode() {
        return upperTeamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamNm(String teamNm) {
        this.teamNm = teamNm;
    }

    public String getTeamNm() {
        return teamNm;
    }

    public void setTeamAt(String teamAt) {
        this.teamAt = teamAt;
    }

    public String getTeamAt() {
        return teamAt;
    }

    public void setTeamAllCodePath(String teamAllCodePath) {
        this.teamAllCodePath = teamAllCodePath;
    }

    public String getTeamAllCodePath() {
        return teamAllCodePath;
    }

    public void setTeamAllNm(String teamAllNm) {
        this.teamAllNm = teamAllNm;
    }

    public String getTeamAllNm() {
        return teamAllNm;
    }

    public void setUseAt(String useAt) {
        this.useAt = useAt;
    }

    public String getUseAt() {
        return useAt;
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