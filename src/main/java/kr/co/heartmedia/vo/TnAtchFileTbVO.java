package kr.co.heartmedia.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("TnAtchFileTbVO")
public class TnAtchFileTbVO extends CommonVO {

	private static final long serialVersionUID = -14099605550594459L;

	/** 파일_번호 */
    private Integer fileNo;
    /** 파일_순번 */
    private Integer fileSn;
    /** 파일_명 */
    private String fileNm;
    /** 파일_경로 */
    private String filePath;
    /** 원본_파일_명 */
    private String orginlFileNm;
    /** 파일_확장자 */
    private String fileExtsn;
    /** 파일_사이즈 */
    private Long fileSize;
    /** 삭제_여부 */
    private String deleteAt;
    /** 등록_사용자_아이디 */
    private String registUserId;
    /** 수정_사용자_아이디 */
    private String updateUserId;
    /** 등록_일시 */
    private Date registDt;
    /** 수정_일시 */
    private Date updateDt;


    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileSn(Integer fileSn) {
        this.fileSn = fileSn;
    }

    public Integer getFileSn() {
        return fileSn;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    public String getFileNm() {
        return fileNm;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setOrginlFileNm(String orginlFileNm) {
        this.orginlFileNm = orginlFileNm;
    }

    public String getOrginlFileNm() {
        return orginlFileNm;
    }

    public void setFileExtsn(String fileExtsn) {
        this.fileExtsn = fileExtsn;
    }

    public String getFileExtsn() {
        return fileExtsn;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setRegistUserId(String registUserId) {
        this.registUserId = registUserId;
    }

    public String getRegistUserId() {
        return registUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

    public Date getRegistDt() {
        return registDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }
}