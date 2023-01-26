package kr.co.heartmedia.admin.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import core.session.UserCountManager;
import core.util.StringUtil;
import kr.co.heartmedia.common.sysUseHist.service.CommSysUseHistService;

public class AllInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private CommSysUseHistService commSysUseHistService;

	@Override
	public boolean preHandle( HttpServletRequest request,	HttpServletResponse response, Object handler) throws Exception {

		ModelMap model = new ModelMap();
		String requestServletPath = StringUtil.nvl(request.getServletPath(), "");

		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String hmSiteCode = (String)pathVariables.get("hmSiteCode");
		model.addAttribute("hmSiteCode", hmSiteCode);
		request.setAttribute("hmSiteCode", hmSiteCode);

		/*
		 * <c:import>, include 에서 jsp 페이지가 읽어들어는 부분은 인터셉터를 태우지 않고 넘긴다.
		 * */
		if( requestServletPath.contains("/WEB-INF/jsp/") ){
			return super.preHandle(request, response, handler);
		}

		/************************************************************************
		 * 1. 접속자 수 체크
		 ************************************************************************/
		if  ( !isAjax(request) )  {
			if  ( UserCountManager.isNewSessinCheck(request.getSession().getId()) )  {
				request.getSession().setMaxInactiveInterval(60*5);   // timeout 5분 지정
				new UserCountManager().setSession(request.getSession());
			}
		}
		/************************************************************************
		 * 2. 시스템 이력 & 로그인 이력 추가
		 ************************************************************************/
		boolean loginAt = false;
		if  ( "/loginAjax.do".equals(request.getRequestURI()) ) {
			loginAt = true;
		}

		commSysUseHistService.insertSysUseHist(request, loginAt);

		request.setAttribute("mav", model);
		return super.preHandle(request, response, handler);

	}

	private boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {

		if  ( !isAjax(request) )  {
			mav.getModelMap().addAllAttributes((ModelMap)request.getAttribute("mav"));

			StringBuffer viewFile = new StringBuffer("");
			String viewName = mav.getViewName();

			if( null != viewName ) {
				int extIdx = viewName.lastIndexOf(".");
				String ext = viewName.substring(extIdx+1);

				if( null != ext ) {
					if(extIdx > 0) {
						viewName = viewName.substring(0, extIdx);
					}
					viewFile.append("/WEB-INF/jsp/").append(viewName).append(".jsp");
				}
			}

			request.setAttribute("viewFile", viewFile.toString());

		}
	}


}
