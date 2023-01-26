package kr.co.heartmedia.vo.extend;

import org.apache.ibatis.type.Alias;

import kr.co.heartmedia.vo.TnAtchFileTbVO;



@Alias("TnAtchFileTbExVO")
public class TnAtchFileTbExVO extends TnAtchFileTbVO {

	private static final long serialVersionUID = -2586124040056547240L;

	/** 데이터 복사 파일 번호 */
	public Integer copyFileNo;

	public Integer getCopyFileNo() {
		return copyFileNo;
	}
	public void setCopyFileNo(Integer copyFileNo) {
		this.copyFileNo = copyFileNo;
	}

}
