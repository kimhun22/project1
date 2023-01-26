/**
 * 
 */
package egovframework.cmmn.service;

import java.util.List;
import java.util.Map;

/**
 * @author mjkim
 *
 */
public interface FileService {
	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> selectFileInfs(FileVO fileVO) throws Exception;

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param fileVO
	 * @throws Exception
	 */
	public String insertFileInf(FileVO fileVO) throws Exception;

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param fileVOList
	 * @throws Exception
	 */
	public String insertFileInfs(List<?> fileList) throws Exception;

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void updateFileInfs(List<?> fileList) throws Exception;

	/**
	 * 여러 개의 파일을 삭제한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void deleteFileInfs(List<?> fileList) throws Exception;

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteFileInf(FileVO fileVO) throws Exception;

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	public FileVO selectFileInf(FileVO fileVO) throws Exception;

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	public int getMaxFileSN(FileVO fileVO) throws Exception;

	/**
	 * 전체 파일을 삭제한다.
	 *
	 * @param fileVO
	 * @throws Exception
	 */
	public void deleteAllFileInf(FileVO fileVO) throws Exception;

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectFileListByFileNm(FileVO fileVO) throws Exception;

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception;
	
	/**
	 * 온나라 PDF 파일을 등록한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertPdfFile(FileVO fileVO) throws Exception;
}
