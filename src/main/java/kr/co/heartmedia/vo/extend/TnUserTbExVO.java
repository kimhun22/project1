package kr.co.heartmedia.vo.extend;

import org.apache.ibatis.type.Alias;

import kr.co.heartmedia.vo.TnUserTbVO;

@SuppressWarnings("serial")
@Alias("TnUserTbExVO")
public class TnUserTbExVO extends TnUserTbVO {

	public String authorCode;
	public String authorNm;
	public String ssoLogin;
	public String lawDongUserCnt;
	public String authorCodeNot;
	public String updateCode;

	public String getUpdateCode() {
		return updateCode;
	}
	public void setUpdateCode(String updateCode) {
		this.updateCode = authorCode;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getAuthorNm() {
		return authorNm;
	}
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}
	public String getSsoLogin() {
		return ssoLogin;
	}
	public void setSsoLogin(String ssoLogin) {
		this.ssoLogin = ssoLogin;
	}
	public String getLawDongUserCnt() {
		return lawDongUserCnt;
	}
	public void setLawDongUserCnt(String lawDongUserCnt) {
		this.lawDongUserCnt = lawDongUserCnt;
	}
	public String getAuthorCodeNot() {
		return authorCodeNot;
	}
	public void setAuthorCodeNot(String authorCodeNot) {
		this.authorCodeNot = authorCodeNot;
	}



}
