package kr.co.heartmedia.vo.extend;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import kr.co.heartmedia.vo.SampleVO;


@Alias("SampleExVO")
public class SampleExVO extends SampleVO {

	/** 첨부파일 */
	public List<MultipartFile> files;
	/** 첨부파일 삭제 seq */
	public String fileDelSn;

	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public String getFileDelSn() {
		return fileDelSn;
	}
	public void setFileDelSn(String fileDelSn) {
		this.fileDelSn = fileDelSn;
	}

}
