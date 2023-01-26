package kr.co.heartmedia.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


@Alias("LoginVO")
@SuppressWarnings("serial")
public class LoginVO extends TnUserTbVO implements Serializable{

	private String usrId;

	private String usrSid;

	private String userKey;

	private String roleType;

	private String complate;

	public String getComplate() {
		return complate;
	}

	public void setComplate(String complate) {
		this.complate = complate;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getUsrSid() {
		return usrSid;
	}

	public void setUsrSid(String usrSid) {
		this.usrSid = usrSid;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
}
