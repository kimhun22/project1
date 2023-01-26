package kr.co.heartmedia.admin.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import core.exception.HMException;
import core.util.StringUtil;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	@SuppressWarnings("unchecked")
	public boolean preHandle(
			HttpServletRequest request,	HttpServletResponse response, Object handler) throws Exception {

		String requestServletPath = StringUtil.nvl(request.getServletPath(), "");

		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String hmSiteCode = (String)pathVariables.get("hmSiteCode");


		// <c:import>, include 에서 jsp 페이지가 읽어들어는 부분은 인터셉터를 태우지 않고 넘긴다.
		if  ( requestServletPath.contains("/WEB-INF/jsp/") )  {
			return super.preHandle(request, response, handler);
		}

		/********************************************************************
		 *  세션 존재 여부 확인
		 ********************************************************************/
		// 로그인 session 정보 조회
		TnUserTbExVO loginInfo = (TnUserTbExVO) request.getSession().getAttribute("loginInfo");
		if  ( null == loginInfo )  {
			if  ( isAjax(request) )  {
				response.sendError(700);
				return false;
			}  else  {
				response.reset();
				throw new HMException("세션이 종료되었습니다.\\n로그인 후 이용해 주세요.", "/"+hmSiteCode+"/login.do", false);
			}
		}
		else  {
			// 첨부파일 관련 URL 일 경우
			if  ( requestServletPath.contains("/comm/file") )  {
				return super.preHandle(request, response, handler);
			}

			// 접근 권한 체크 제외 URL
			boolean accessCheck = false;
			if  ( !requestServletPath.contains("/main") && !requestServletPath.contains("/mypage") )  {
				String menuUrl = requestServletPath.substring(0, requestServletPath.lastIndexOf("/") + 1);

				List<TsMenuTbExVO> systemMenuList = (List<TsMenuTbExVO>) request.getSession().getAttribute("systemMenuList");

				for  ( TsMenuTbExVO systemMenu : systemMenuList )  {
					if  ( StringUtil.isEmpty(systemMenu.getMenuUrl()) )
						continue;

					// 접근 권한이 있는 URL 일 경우
					if  ( systemMenu.getMenuUrl().contains(menuUrl) )  {
						accessCheck = true;
						break;
					}
				}
			}
			else  {
				accessCheck = true;
			}

			// 접근 권한이 없을 경우
			if  ( !accessCheck )  {
				throw new HMException("["+ requestServletPath +"] 해당 URL에 접근 권한이 없습니다.");
			}

			/********************************************************************
			 *  비밀번호 변경 대상 체크
			 ********************************************************************/
//			if  ( !requestServletPath.contains("/mypage/userPw") && !requestServletPath.contains("/mypage/pwCrtfc") )  {
//				boolean passwordChangeAt = false;
//
//				Calendar cal = Calendar.getInstance();
//				cal.setTime(new Date());
//				cal.add(Calendar.MONTH, -3);
//				Integer dateVal = Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(cal.getTime()));
//				Integer pwUpdateDt = Integer.valueOf(loginInfo.getPwChangeDt().substring(0, 8));
//
//				// 초기 비밀번호 사용 일 경우
//				if  ( "1".equals(loginInfo.getEryPwAt()) )  {
//					passwordChangeAt = true;
//				}
//				// 비밀번호 변경일이 3개월 지난 경우
//				else if  ( dateVal > pwUpdateDt )  {
//					passwordChangeAt = true;
//				}
//
//				// 비밀번호 변경 대상 일 경우
//				if  ( passwordChangeAt )  {
//					throw new HMException("비밀번호 변경 대상자 입니다. 변경 후 이용해 주세요.", "/mypage/userPw.do", false);
//				}
//			}
		}

		return super.preHandle(request, response, handler);

	}

	private boolean isAjax(HttpServletRequest request){
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

}
