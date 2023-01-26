package kr.co.heartmedia.system.logInqire.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heartmedia.system.logInqire.service.LogInqireService;
import kr.co.heartmedia.vo.extend.ThSysUseHistTbExVO;

/**
 * @Class   	: LogInqireController.java
 * @Description : 시스템 관리 - 로그조회 Controller
 */
@Controller
@RequestMapping(value = "/{hmSiteCode}/system/log")
public class LogInqireController {

	// 시스템 관리 - 로그조회 Service
	@Resource
	private LogInqireService logInqireService;

	/**
	 * 시스템 접속 이력 목록 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/systemConectList.do")
	public String systemConectList(ModelMap model, @ModelAttribute ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception {

		logInqireService.selectSystemConectList(model, thSysUseHistTbExVO);

		return "system/log/systemConectList.cn";

	}

	/**
	 * 시스템 이용 이력 목록 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/systemUseList.do")
	public String systemUseList(ModelMap model, @ModelAttribute ThSysUseHistTbExVO thSysUseHistTbExVO) throws Exception {

		logInqireService.selectSystemUseList(model, thSysUseHistTbExVO);

		return "system/log/systemUseList.cn";

	}


}
