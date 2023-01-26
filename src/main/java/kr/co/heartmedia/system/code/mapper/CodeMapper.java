package kr.co.heartmedia.system.code.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;


@Mapper
public interface CodeMapper {

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
	 * 하위 목록 조회
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	List<TsCmmnCodeTbExVO> selectSubCodeList(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 다음 순서 조회
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	int selectNextCodeOrd(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 코드 중복 체크
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	int selectOverlapCodeCheck(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 등록
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	int insertCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 수정
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	int updateCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

	/**
	 * 삭제
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	int deleteCode(TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception;

}
