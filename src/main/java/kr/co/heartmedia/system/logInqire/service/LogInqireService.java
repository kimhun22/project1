package kr.co.heartmedia.system.logInqire.service;

import org.springframework.ui.ModelMap;

import kr.co.heartmedia.vo.extend.ThSysUseHistTbExVO;


/**
 * @Class   	: LogInqireService.java
 * @Description : 시스템 관리 - 로그조회 Service
 */

public interface LogInqireService {

	// 시스템 접속 이력 조회
	void selectSystemConectList(ModelMap model, ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;

	// 시스템 접속 단일 조회
	ThSysUseHistTbExVO selectSystemConectOne(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;

	// 시스템 이용 이력 조회
	void selectSystemUseList(ModelMap model, ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;

	// 시스템 이용 단일 조회
	ThSysUseHistTbExVO selectSystemUseOne(ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception;
}
