package kr.co.heartmedia.vo.extend;

import org.apache.ibatis.type.Alias;

import kr.co.heartmedia.vo.TsCmmnCodeTbVO;

@Alias("TsCmmnCodeTbExVO")
public class TsCmmnCodeTbExVO extends TsCmmnCodeTbVO {

	/** 상위 코드명 */
	public String parntsCmmnCodeNm;
	/** 상위 코드 사용여부 */
	public String parntsUseAt;
	/** 단계 */
	public Integer depth;
	/** 하위 코드 갯수 */
	public Integer childCnt;
	/** 상위코드 정렬순서 */
	public Integer parntsSortOrdr;


	public Integer getParntsSortOrdr() {
		return parntsSortOrdr;
	}
	public void setParntsSortOrdr(Integer parntsSortOrdr) {
		this.parntsSortOrdr = parntsSortOrdr;
	}
	public String getParntsCmmnCodeNm() {
		return parntsCmmnCodeNm;
	}
	public void setParntsCmmnCodeNm(String parntsCmmnCodeNm) {
		this.parntsCmmnCodeNm = parntsCmmnCodeNm;
	}
	public String getParntsUseAt() {
		return parntsUseAt;
	}
	public void setParntsUseAt(String parntsUseAt) {
		this.parntsUseAt = parntsUseAt;
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

}
