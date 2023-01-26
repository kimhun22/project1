package kr.co.heartmedia.system.code.service;

import java.util.List;
import java.util.Map;

import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;



public interface CodeService {

	/**
	 * 목록 조회
	 * @return
	 * @throws Exception
	 */
	List<TsCmmnCodeTbExVO> selectCodeList(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 상세 조회
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	TsCmmnCodeTbExVO selectCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 다음 정보 조회(순번)
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	TsCmmnCodeTbExVO selectNextCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 등록
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> insertCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 수정
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> updateCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 삭제
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> deleteCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

}
