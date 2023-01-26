package kr.co.heartmedia.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CommonVO extends ComDefaultVO implements Serializable {

	/** 현재 페이지 */
	public int nowPage;
	/** 페이지에 보여질 개시물 갯수 */
	public int pagePerRow;
	/** 페이징에 보여질 페이지 갯수 */
	public int pagePerBlock;

	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPagePerRow() {
		return pagePerRow;
	}
	public void setPagePerRow(int pagePerRow) {
		this.pagePerRow = pagePerRow;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public CommonVO() {
		this.nowPage = 1;
		this.pagePerRow = 10;
		this.pagePerBlock = 10;
	}
}
