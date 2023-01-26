package kr.co.heartmedia.vo;

import org.apache.ibatis.type.Alias;

@Alias("ThAuthorChangeHistTbVO")
public class ThAuthorChangeHistTbVO extends CommonVO {

	private static final long serialVersionUID = 5000109849783941717L;

	private String histNo;
	private String loginId;
	private String useIp;
	private String roleChangeCn;

	public String getHistNo() {
		return histNo;
	}

	public void setHistNo(String histNo) {
		this.histNo = histNo;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUseIp() {
		return useIp;
	}

	public void setUseIp(String useIp) {
		this.useIp = useIp;
	}

	public String getRoleChangeCn() {
		return roleChangeCn;
	}

	public void setRoleChangeCn(String roleChangeCn) {
		this.roleChangeCn = roleChangeCn;
	}
}