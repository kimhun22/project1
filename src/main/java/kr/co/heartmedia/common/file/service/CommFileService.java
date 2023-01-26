package kr.co.heartmedia.common.file.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.heartmedia.vo.TnAtchFileTbVO;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;



public interface CommFileService {

	/**
	 * 파일 목록 조회
	 * @param fileNo
	 * @return
	 */
	List<TnAtchFileTbVO> selectFileList(TnAtchFileTbVO tnAtchFileTbVO ) throws Exception;

	/**
	 * 파일 상세 조회
	 * @param tnAtchFileTbVO
	 * @return
	 */
	TnAtchFileTbVO selectFile(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;

	/**
	 * 파일 접근 권한 확인
	 * @param tnAtchFileTbVO
	 * @return
	 * @throws Exception
	 */
	boolean isAccessFile(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;

	/**
	 * 파일 crud 처리
	 * @param allowFileType
	 * @param boardType
	 * @param fileNo
	 * @param delSn
	 * @param maxFileUploadCount
	 * @param multiRequest
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> crudFiles(String allowFileType, String boardType, Integer fileNo, String delSn, int maxFileUploadCount, List<MultipartFile> multiRequest,String hmSiteCode) throws Exception;

	/**
	 * 파일 정보 복사 처리(신규 FILE_NO 생성)
	 * @param allowFileType
	 * @param boardType
	 * @param fileNo
	 * @param delSn
	 * @param maxFileUploadCount
	 * @param multiRequest
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> copyFiles(String allowFileType, String boardType, Integer fileNo, String delSn, int maxFileUploadCount, List<MultipartFile> multiRequest) throws Exception;
	/**
	 * 업로드 할 파일 생성
	 * @param files
	 * @return
	 * @throws Exception
	 */
	List<MultipartFile> makeUploadFile(List<MultipartFile> files) throws Exception;

}
