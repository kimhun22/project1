package kr.co.heartmedia.vo.extend;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import kr.co.heartmedia.vo.TsMenuTbVO;

@Alias("TsMenuTbExVO")
public class TsMenuTbExVO extends TsMenuTbVO implements Serializable {

	private static final long serialVersionUID = 3949200098698890066L;

	/** 권한코드 */
	private String authCode;
	/** 상위 메뉴명 */
	public String upperMenuNm;
	/** 상위 메뉴 사용여부 */
	public String upperUseAt;
	/** 단계 */
	public Integer depth;
	/** 하위 메뉴 갯수 */
	public Integer childCnt;

	public String getUpperMenuNm() {
		return upperMenuNm;
	}
	public void setUpperMenuNm(String upperMenuNm) {
		this.upperMenuNm = upperMenuNm;
	}
	public String getUpperUseAt() {
		return upperUseAt;
	}
	public void setUpperUseAt(String upperUseAt) {
		this.upperUseAt = upperUseAt;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public Integer getChildCnt() {
		return childCnt;
	}
	public void setChildCnt(Integer childCnt) {
		this.childCnt = childCnt;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
