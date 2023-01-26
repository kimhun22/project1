package kr.co.heartmedia.admin.login.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heartmedia.admin.login.service.LoginService;
import kr.co.heartmedia.vo.TnUserTbVO;

@Controller
@RequestMapping(value = "/{hmSiteCode}")
public class LoginController {

	@Resource
	private LoginService loginService;


	/**
	 * 로그인 페이지
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.do")
	public String login(ModelMap model, HttpServletRequest request) throws Exception {
		String hmSiteCode = (String)request.getAttribute("hmSiteCode");
		return "/login/login";
	}

	/**
	 * 로그인
	 * @param request
	 * @param TnUserTbVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loginAjax(
			HttpServletRequest request
			, @ModelAttribute TnUserTbVO TnUserTbVO) throws Exception {

		// 로그인 처리
		return loginService.userLogin(request, TnUserTbVO);

	}

	/**
	 * 로그아웃
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(
			HttpServletRequest request) throws Exception {

		// 로그아웃 처리
		loginService.logout(request);

		return "redirect:/{hmSiteCode}/login.do";

	}

}
