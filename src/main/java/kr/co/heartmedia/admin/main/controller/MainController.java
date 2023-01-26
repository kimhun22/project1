package kr.co.heartmedia.admin.main.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heartmedia.admin.main.service.MainService;


@Controller
@RequestMapping(value = "/{hmSiteCode}")
public class MainController {

	@Resource
	private MainService mainService;


	/**
	 * 메인 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/main.do")
	public String main(
			ModelMap model) throws Exception {
		return "main/main.frame";

	}

}
