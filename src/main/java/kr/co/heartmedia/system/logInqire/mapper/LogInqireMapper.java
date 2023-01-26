package kr.co.heartmedia.system.logInqire.mapper;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.heartmedia.vo.extend.ThSysUseHistTbExVO;


/**
 * @Class   	: LogInqireMapper.java
 * @Description : 시스템 관리 - 로그조회 Mapper
 */

@Mapper
public interface LogInqireMapper {

	// 시스템 접속 이력 조회 리스트 Count
	int selectSystemConectListCnt(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;

	// 시스템 접속 이력 조회 리스트
	List<ThSysUseHistTbExVO> selectSystemConectList(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;

	// 시스템 접속 단일 조회
	ThSysUseHistTbExVO selectSystemConectOne(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;



	// 시스템 이용 이력 조회 리스트 Count
	int selectSystemUseListCnt(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;

	// 시스템 이용 이력 조회 리스트
	List<ThSysUseHistTbExVO> selectSystemUseList(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;

}
