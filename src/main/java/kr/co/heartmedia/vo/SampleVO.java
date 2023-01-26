package kr.co.heartmedia.vo;

import org.apache.ibatis.type.Alias;

@Alias("SampleVO")
public class SampleVO extends CommonVO {

	/** 첨부파일 NO */
	public Integer fileNo;

	public Integer getFileNo() {
		return fileNo;
	}
	public void setFileNo(Integer fileNo) {
		this.fileNo = fileNo;
	}

}
