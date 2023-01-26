package kr.co.heartmedia.system.deptInfo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heartmedia.common.code.service.CommCodeService;
import kr.co.heartmedia.common.com.service.CommonService;
import kr.co.heartmedia.system.deptInfo.service.AdminDeptService;
import kr.co.heartmedia.system.menu.service.MenuService;
import kr.co.heartmedia.vo.TnDeptTbVO;




/**
 * @Class   	: UserInfoController.java
 * @Description : 사용자 정보 관련 Controller
 * @생성일자  : 2021. 9. 3
 */
@Controller
@RequestMapping(value = "/{hmSiteCode}/system/deptInfo")
public class DeptInfoController {


	@Resource
	private AdminDeptService adminDeptService;

	@Resource
	private CommCodeService commCodeService;

	@Resource
	private CommonService commonService;

	@Resource
	private MenuService menuService;

	/**
	 * 부서 목록 조회
	 * @param tnDeptTbVO
	 * @param model
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value = "/deptList.do")
	public String deptList(@ModelAttribute("TnUserTbExVO") TnDeptTbVO tnDeptTbVO, ModelMap model) throws Exception {

		adminDeptService.selectDeptList(tnDeptTbVO, model);
		commonService.selectDepartmentList(tnDeptTbVO, model);

		return "system/deptInfo/deptList.cn";

	}

	/**
	 * 부서 상세 조회
	 * @param tnDeptTbVO
	 * @param model
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value = "/selectDeptDetail.do")
	public String deptDetail(@ModelAttribute("TnUserTbExVO") TnDeptTbVO tnDeptTbVO, ModelMap model) throws Exception {

		adminDeptService.selectDeptDetail(tnDeptTbVO, model);

		return "system/deptInfo/deptUpdate.cn";

	}

}





