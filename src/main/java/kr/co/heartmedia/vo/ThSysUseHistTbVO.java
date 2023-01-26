package kr.co.heartmedia.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("ThSysUseHistTbVO")
public class ThSysUseHistTbVO  extends CommonVO {

	private static final long serialVersionUID = -16128839723237245L;

	/** 요청_순번 */
    private String requstSn;
    /** 로그인_요청_여부 */
    private String loginRequstAt;
    /** 요청_일시 */
    private Date requstDt;
    /** 요청_URL */
    private String requstUrl;
    /** 요청_IP */
    private String requstIp;
    /** 요청자 */
    private String rqester;


    public void setRequstSn(String requstSn) {
        this.requstSn = requstSn;
    }

    public String getRequstSn() {
        return requstSn;
    }

    public void setLoginRequstAt(String loginRequstAt) {
        this.loginRequstAt = loginRequstAt;
    }

    public String getLoginRequstAt() {
        return loginRequstAt;
    }

    public void setRequstDt(Date requstDt) {
        this.requstDt = requstDt;
    }

    public Date getRequstDt() {
        return requstDt;
    }

    public void setRequstUrl(String requstUrl) {
        this.requstUrl = requstUrl;
    }

    public String getRequstUrl() {
        return requstUrl;
    }

    public void setRequstIp(String requstIp) {
        this.requstIp = requstIp;
    }

    public String getRequstIp() {
        return requstIp;
    }

    public void setRqester(String rqester) {
        this.rqester = rqester;
    }

    public String getRqester() {
        return rqester;
    }

}