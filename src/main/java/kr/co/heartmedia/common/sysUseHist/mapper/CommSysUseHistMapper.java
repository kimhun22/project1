package kr.co.heartmedia.common.sysUseHist.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.ThSysUseHistTbVO;



@Mapper
public interface CommSysUseHistMapper {

	/**
	 * 시스템 이용 이력 추가
	 * @param ThSysUseHistTbVO
	 * @return
	 * @throws Exception
	 */
	int insertSysUseHist(ThSysUseHistTbVO thSysUseHistTbVO) throws Exception;

	/**
	 * 로그인 이력 요청자 정보 수정
	 * @param ThSysUseHistTbVO
	 * @return
	 * @throws Exception
	 */
	int updateSysUseHist(ThSysUseHistTbVO thSysUseHistTbVO) throws Exception;

}
