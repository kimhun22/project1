package kr.co.heartmedia.system.userInfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import core.util.CommonUtils;
import kr.co.heartmedia.common.code.service.CommCodeService;
import kr.co.heartmedia.common.com.service.CommonService;
import kr.co.heartmedia.system.deptInfo.service.AdminDeptService;
import kr.co.heartmedia.system.userInfo.service.AdminUserService;
import kr.co.heartmedia.vo.TnDeptTbVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;

/**
 * @Class   	: UserInfoController.java
 * @Description : 사용자 정보 관련 Controller
 * @생성일자  : 2021. 9. 3
 */
@Controller
@RequestMapping(value = "/{hmSiteCode}/system/userInfo")
public class UserInfoController {

	@Resource
	private AdminUserService adminUserService;
	@Resource
	private AdminDeptService adminDeptService;
	@Resource
	private CommCodeService commCodeService;

	@Resource
	private CommonService commonService;

	/**
	 * 사용자 목록
	 * @param TnUserTbExVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userList.do")
	public String userList(@ModelAttribute("TnUserTbExVO") TnUserTbExVO TnUserTbExVO, ModelMap model) throws Exception {

		adminUserService.selectAdminUserList(TnUserTbExVO, model);

		//권한 리스트
		TsCmmnCodeTbExVO tsCmmnCodeTbExVO = new TsCmmnCodeTbExVO();
		tsCmmnCodeTbExVO.setParntsCmmnCode("COM001");
		tsCmmnCodeTbExVO.setHmSiteCode(TnUserTbExVO.getHmSiteCode());
		model.addAttribute("authorList", commCodeService.selectSubCodeListSiteCode(tsCmmnCodeTbExVO));

		//부서
		TnDeptTbVO tnDeptTbVO = new TnDeptTbVO();
		tnDeptTbVO.setHmSiteCode(TnUserTbExVO.getHmSiteCode());
		commonService.selectDepartmentList(tnDeptTbVO, model);

		return "system/userInfo/userList.cn";

	}

	/**
	 * 사용자상세정보
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectAdminUserDetail.do")
	public String userDetail(@ModelAttribute("TnUserTbExVO") TnUserTbExVO tnUserTbExVO, ModelMap model) throws Exception {
		// 권한 코드 목록
		TsCmmnCodeTbExVO tsCmmnCodeTbExVO = new TsCmmnCodeTbExVO();
		tsCmmnCodeTbExVO.setParntsCmmnCode("COM001");
		tsCmmnCodeTbExVO.setHmSiteCode(tnUserTbExVO.getHmSiteCode());
		model.addAttribute("authorCodeList", commCodeService.selectSubCodeListSiteCode(tsCmmnCodeTbExVO));

		adminUserService.selectAdminUserDetail(tnUserTbExVO, model);

		return "system/userInfo/userUpdate.cn";
	}

	/**
	 * 사용자정보 수정
	 * @param TnUserTbExVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAdminUserProc.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateAdminUserProc(HttpServletRequest request, @ModelAttribute("TnUserTbExVO") TnUserTbExVO TnUserTbExVO, SessionStatus status, ModelMap model) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;

		// 수정자 입력
		TnUserTbExVO.setUpdateUserId(new CommonUtils().getLoginUserId());
		result = adminUserService.updateAdminUserProc(TnUserTbExVO);
		status.setComplete(); // 중복 submit 방지

		// 권한 변경 기록
		String useIp = getIp(request);
		adminUserService.execChangeRoleHist(TnUserTbExVO.getLoginId(), TnUserTbExVO.getAuthorCode(), useIp);

		if( result == 1 ){
    		resultMap.put("result", true );
    	}else{
    		resultMap.put("result", false );
    	}

		return resultMap;

	}

	private String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }

	    return ip;

	}
}
