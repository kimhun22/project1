package kr.co.heartmedia.common.com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.heartmedia.common.com.mapper.CommonMapper;
import kr.co.heartmedia.common.com.service.CommonService;
import kr.co.heartmedia.vo.LoginVO;
import kr.co.heartmedia.vo.TnDeptTbVO;



@Aspect
@Service("commonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService {

	@Resource(name="commonMapper")
	private CommonMapper commonMapper;

//	@Resource(name="smsService")
//	private SmsService smsService;
//
//	@Resource(name="commonUtils")
//	private CommonUtils commonUtils;
//
//	@Resource(name="saeolService")
//	private SaeolService saeolService;
//
//	@Resource(name="faxService")
//	private FaxService faxService;

	@Override
	public void selectDepartmentList(TnDeptTbVO tnDeptTbVO,ModelMap model) throws Exception {

		List<LoginVO> departmentList = commonMapper.selectDepartmentList(tnDeptTbVO);
		model.addAttribute("departmentList", departmentList);
	}

	@Override
	public String backMsg(ModelMap model, String msg) throws Exception {
		model.addAttribute("msg", msg);
		return "common/error/backMsg";
	}

	@Override
	public String historyBackMsg(String msg) throws Exception {
		return "common/error/backMsg";
	}



}
