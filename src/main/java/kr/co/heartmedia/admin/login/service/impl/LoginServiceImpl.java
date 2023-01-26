package kr.co.heartmedia.admin.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.session.UserCountManager;
import core.util.EncryptionUtils;
import kr.co.heartmedia.admin.login.mapper.LoginMapper;
import kr.co.heartmedia.admin.login.service.LoginService;
import kr.co.heartmedia.common.menu.service.CommMenuService;
import kr.co.heartmedia.vo.TnUserTbVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;


@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	private LoginMapper loginMapper;

	@Resource
	private CommMenuService commMenuService;


	@Override
	@Transactional
	public Map<String, Object> userLogin(HttpServletRequest request, TnUserTbVO TnUserTbVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 사용자 정보 검증
		 ***********************************************************************/
		// 사용자 정보 조회
		TnUserTbExVO userInfo = loginMapper.selectUserInfo(TnUserTbVO);

		// 사용자 정보가 없을 경우
		if  ( null == userInfo )  {
			resultMap.put("result", false);
			resultMap.put("message", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");

			return resultMap;
		}

		/***********************************************************************
		 * 2. 검증
		 ***********************************************************************/
		// 비밀번호 체크
		// 로그인 시도 5회 이상 실패일 경우
//		if  ( userInfo.getPwFailrCnt() >= 5 )  {
//			resultMap.put("result", false);
//			resultMap.put("message", "잘못된 비밀번호를 5회 이상 입력하여 잠금 처리 되었습니다.");
//
//			return resultMap;
//		}
//		else  {
			String encryptionPassword = EncryptionUtils.encryptSha256(TnUserTbVO.getUserPwd(), new byte[10]);

			if  ( !encryptionPassword.equals(userInfo.getUserPwd()) )  {
				// 비밀번호 실패 횟수 증가
//				loginMapper.updatePwFailrCnt(userInfo);

				resultMap.put("result", false);
				resultMap.put("message", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");

				return resultMap;
			}
//		}
			//사용안함 권한 코드확인
			if  (userInfo.getAuthorCode().equals("5") )  {
				resultMap.put("result", false);
				resultMap.put("message", "권한이 부여되지 않았습니다. 관리자에게 문의하시기 바랍니다.");

				return resultMap;
			}


		/************************************************************************
		 * 2. 메뉴 세팅
		 ************************************************************************/
		TsMenuTbExVO vo = new TsMenuTbExVO();
		vo.setAuthCode(userInfo.getAuthorCode());
		vo.setHmSiteCode(request.getAttribute("hmSiteCode")+"");
		request.getSession().setAttribute("systemMenuList", commMenuService.selectAuthMenuListSite(vo));

		/***********************************************************************
		 * 3. 로그인 성공 처리
		 ***********************************************************************/
		// 접속 이력 정보 변경(비밀번호 실패 횟수, 최근 접속 일시)
//		loginMapper.updateConectInfo(userInfo);

		// 세션 저장
		request.getSession().setAttribute("loginInfo", userInfo);
		request.getSession().setMaxInactiveInterval(60*30);   // session timeout 30분 지정

		// 접속자 로그인 여부 수정(로그인)
		UserCountManager.putUserInfo(request.getSession(), userInfo.getLoginId());

		resultMap.put("result", true);

		return resultMap;

	}

	@Override
	public void logout(HttpServletRequest request) throws Exception {

		request.getSession().invalidate();

	}

}
