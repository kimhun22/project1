package kr.co.heartmedia.vo.extend;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import kr.co.heartmedia.vo.TnBbsTbVO;

@Alias("TnBbsTbExVO")
@SuppressWarnings("serial")
public class TnBbsTbExVO extends TnBbsTbVO {

	/** 부서_명 */
	private String deptNm;
	/** 사용자_명 */
    private String userNm;
    /** 게시물_구분_코드_명 */
    private String nttSeCodeNm;
    /** 업무_유형_코드_명 */
    private String jobTyCodeNm;
    /** 첨부_파일_순번 */
    private String atchFileSn;
    /** 첨부_파일_명 */
    private String atchFileNm;
	/** 첨부파일 */
	private List<MultipartFile> atchFiles;
	/** 첨부파일 삭제 sn */
	private String atchFileDelSn;
	/** 검색조건 - 게시물 구분 */
	private String searchNttSeCode;
	/** 검색조건 - 업무_유형_코드 */
	private String searchJobTyCode;
	/** 검색조건 - 기관_명 */
	private String searchRelateInsttNm;
	/** 검색조건 - 판례일(시작) */
	private String searchStartNttDe;
	/** 검색조건 - 판례일(종료) */
	private String searchEndNttDe;

	/** 검새조건 - 작성일(시작) **/
	private String searchRegistStartDe;
	/** 검새조건 - 작성일(종료) **/
	private String searchRegistEndDe;

	/** 검새조건 - 답변일(시작) **/
	private String searchStartAnswerDt;
	/** 검새조건 - 답변일(종료) **/
	private String searchEndAnswerDt;

	/** 답변 등록자 이름 */
	private String answerUserNm;

	/** 답변 등록자 부서 */
	private String answerDeptNm;

	/** 중요 게시물 유무 */
	private String importYn;

	/** 표시 시작일 */
	private String showStartDt;

	/** 표시 종료일 */
	private String showEndDt;

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getNttSeCodeNm() {
		return nttSeCodeNm;
	}

	public void setNttSeCodeNm(String nttSeCodeNm) {
		this.nttSeCodeNm = nttSeCodeNm;
	}

	public String getJobTyCodeNm() {
		return jobTyCodeNm;
	}

	public void setJobTyCodeNm(String jobTyCodeNm) {
		this.jobTyCodeNm = jobTyCodeNm;
	}

	public String getAtchFileSn() {
		return atchFileSn;
	}

	public void setAtchFileSn(String atchFileSn) {
		this.atchFileSn = atchFileSn;
	}

	public String getAtchFileNm() {
		return atchFileNm;
	}

	public void setAtchFileNm(String atchFileNm) {
		this.atchFileNm = atchFileNm;
	}

	public List<MultipartFile> getAtchFiles() {
		return atchFiles;
	}

	public void setAtchFiles(List<MultipartFile> atchFiles) {
		this.atchFiles = atchFiles;
	}

	public String getAtchFileDelSn() {
		return atchFileDelSn;
	}

	public void setAtchFileDelSn(String atchFileDelSn) {
		this.atchFileDelSn = atchFileDelSn;
	}

	public String getSearchNttSeCode() {
		return searchNttSeCode;
	}

	public void setSearchNttSeCode(String searchNttSeCode) {
		this.searchNttSeCode = searchNttSeCode;
	}

	public String getSearchJobTyCode() {
		return searchJobTyCode;
	}

	public void setSearchJobTyCode(String searchJobTyCode) {
		this.searchJobTyCode = searchJobTyCode;
	}

	public String getSearchRelateInsttNm() {
		return searchRelateInsttNm;
	}

	public void setSearchRelateInsttNm(String searchRelateInsttNm) {
		this.searchRelateInsttNm = searchRelateInsttNm;
	}

	public String getSearchStartNttDe() {
		return searchStartNttDe;
	}

	public void setSearchStartNttDe(String searchStartNttDe) {
		this.searchStartNttDe = searchStartNttDe;
	}

	public String getSearchEndNttDe() {
		return searchEndNttDe;
	}

	public void setSearchEndNttDe(String searchEndNttDe) {
		this.searchEndNttDe = searchEndNttDe;
	}

	public String getAnswerUserNm() {
		return answerUserNm;
	}

	public void setAnswerUserNm(String answerUserNm) {
		this.answerUserNm = answerUserNm;
	}

	public String getSearchRegistStartDe() {
        return searchRegistStartDe;
    }

    public void setSearchRegistStartDe(String searchRegistStartDe) {
        this.searchRegistStartDe = searchRegistStartDe;
    }

    public String getSearchRegistEndDe() {
        return searchRegistEndDe;
    }

    public void setSearchRegistEndDe(String searchRegistEndDe) {
        this.searchRegistEndDe = searchRegistEndDe;
    }

	public String getSearchStartAnswerDt() {
		return searchStartAnswerDt;
	}

	public void setSearchStartAnswerDt(String searchStartAnswerDt) {
		this.searchStartAnswerDt = searchStartAnswerDt;
	}

	public String getSearchEndAnswerDt() {
		return searchEndAnswerDt;
	}

	public void setSearchEndAnswerDt(String searchEndAnswerDt) {
		this.searchEndAnswerDt = searchEndAnswerDt;
	}

	public String getAnswerDeptNm() {
		return answerDeptNm;
	}

	public void setAnswerDeptNm(String answerDeptNm) {
		this.answerDeptNm = answerDeptNm;
	}

	public String getImportYn() {
		return importYn;
	}

	public void setImportYn(String importYn) {
		this.importYn = importYn;
	}

	public String getShowStartDt() {
		return showStartDt;
	}

	public void setShowStartDt(String showStartDt) {
		this.showStartDt = showStartDt;
	}

	public String getShowEndDt() {
		return showEndDt;
	}

	public void setShowEndDt(String showEndDt) {
		this.showEndDt = showEndDt;
	}




}
