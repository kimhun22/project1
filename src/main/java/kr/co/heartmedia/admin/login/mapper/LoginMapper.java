package kr.co.heartmedia.admin.login.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.TnUserTbVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;


@Mapper
public interface LoginMapper {

	/**
	 * 사용자 정보 조회
	 * @param TnUserTbVO
	 * @return
	 * @throws Exception
	 */
	TnUserTbExVO selectUserInfo(TnUserTbVO TnUserTbVO) throws Exception;

	/**
	 * 접속 이력 정보 변경(비밀번호 실패 횟수, 최근 접속 일시)
	 * @param TnUserTbVO
	 * @return
	 * @throws Exception
	 */
	int updateConectInfo(TnUserTbVO TnUserTbVO) throws Exception;

	/**
	 * 비밀번호 실패 횟수 증가
	 * @param TnUserTbVO
	 * @return
	 * @throws Exception
	 */
	int updatePwFailrCnt(TnUserTbVO TnUserTbVO) throws Exception;

}
