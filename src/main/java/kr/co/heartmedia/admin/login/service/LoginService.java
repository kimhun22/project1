package kr.co.heartmedia.admin.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.heartmedia.vo.TnUserTbVO;



public interface LoginService {

	/**
	 * 사용자 로그인
	 * @param request
	 * @param TnUserTbVO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> userLogin(HttpServletRequest request, TnUserTbVO TnUserTbVO) throws Exception;

	/**
	 * 로그아웃
	 * @param request
	 * @throws Exception
	 */
	void logout(HttpServletRequest request) throws Exception;

}
