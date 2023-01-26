package kr.co.heartmedia.common.code.service;

import java.util.List;

import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;


public interface CommCodeService {

	/**
	 * 하위 코드 목록 조회
	 * @param parntsCmmnCode
	 * @return
	 * @throws Exception
	 */
	//List<TsCmmnCodeTbExVO> selectSubCodeList(String parntsCmmnCode) throws Exception;

	/**
	 * 하위 코드 목록 조회 (사이트 코드 추가)
	 * @param parntsCmmnCode
	 * @return
	 * @throws Exception
	 */
	List<TsCmmnCodeTbExVO> selectSubCodeListSiteCode(TsCmmnCodeTbExVO vo) throws Exception;
}
