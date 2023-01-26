package kr.co.heartmedia.common.code.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;

@Mapper
public interface CommCodeMapper {

	/**
	 * 하위 코드 목록 조회
	 * @param parntsCmmnCode
	 * @return
	 * @throws Exception
	 */
	//List<TsCmmnCodeTbExVO> selectSubCodeList(String parntsCmmnCode) throws Exception;

	/**
	 * 하위 코드 목록 조회 (사이트 코드 포함)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<TsCmmnCodeTbExVO> selectSubCodeListSiteCode(TsCmmnCodeTbExVO vo) throws Exception;

}
