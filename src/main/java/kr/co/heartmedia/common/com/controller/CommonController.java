package kr.co.heartmedia.common.com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heartmedia.common.com.service.CommonService;
import kr.co.heartmedia.vo.TnDeptTbVO;





@Controller
@RequestMapping(value = "/{hmSiteCode}/common")
public class CommonController {

	@Resource
	private CommonService commonService;

}
