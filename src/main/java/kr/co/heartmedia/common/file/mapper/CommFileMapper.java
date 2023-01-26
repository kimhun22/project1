package kr.co.heartmedia.common.file.mapper;

import java.util.List;


import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.TnAtchFileTbVO;
import kr.co.heartmedia.vo.TnUserTbVO;
import kr.co.heartmedia.vo.extend.TnAtchFileTbExVO;

@Mapper
public interface CommFileMapper {

	/**
	 * 파일 목록 조회
	 * @param fileNo
	 * @return
	 */
	List<TnAtchFileTbVO> selectFileList(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;

	/**
	 * 파일 상세 조회
	 * @param tnAtchFileTbVO
	 * @return
	 */
	TnAtchFileTbVO selectFile(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;

	/**
	 * 파일 등록자 회원 정보 조회
	 * @param tnAtchFileTbVO
	 * @return
	 */
	TnUserTbVO selectFileRegUserInfo(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;

	/**
	 * 등록된 파일 갯수 조회
	 * @param fileNo
	 * @return
	 */
	int selectFileCnt(int fileNo) throws Exception;

	/**
	 * 파일 정보 등록(FILE_NO 생성)
	 * @param tnAtchFileTbVO
	 * @return
	 */
	int insertFileSelectKeyFileNo(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;

	/**
	 * 파일 정보 등록(FILE_SEQ 생성)
	 * @param tnAtchFileTbVO
	 * @return
	 */
	int insertFileSelectKeyFileSn(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;

	/**
	 * 파일 정보 복사 등록
	 * @param tnAtchFileTbVO
	 * @return
	 */
	int insertCopyFile(TnAtchFileTbExVO tnAtchFileTbVO) throws Exception;

	/**
	 * 파일 삭제
	 * @param tnAtchFileTbVO
	 * @return
	 */
	int deleteFile(TnAtchFileTbVO tnAtchFileTbVO) throws Exception;


}
