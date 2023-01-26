package kr.co.heartmedia.common.sysUseHist.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.util.StringUtil;
import kr.co.heartmedia.common.sysUseHist.mapper.CommSysUseHistMapper;
import kr.co.heartmedia.common.sysUseHist.service.CommSysUseHistService;
import kr.co.heartmedia.vo.ThSysUseHistTbVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;


@Service
public class CommSysUseHistServiceImpl implements CommSysUseHistService {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private CommSysUseHistMapper commSysUseHistMapper;

	@Autowired
	private HttpSession session;


	@Override
	@Transactional
	public void insertSysUseHist(HttpServletRequest request, boolean loginAt) throws Exception {
		// 사이트 코드 가져 오기
		String hmSiteCode = (String)request.getAttribute("hmSiteCode");

		ThSysUseHistTbVO thSysUseHistTbVO = new ThSysUseHistTbVO();
		thSysUseHistTbVO.setLoginRequstAt(loginAt ? "1" : "0");
		thSysUseHistTbVO.setHmSiteCode(hmSiteCode);

		String requestUrl  = request.getRequestURI();

		thSysUseHistTbVO.setRequstUrl(requestUrl);
		thSysUseHistTbVO.setRequstIp(StringUtil.getRemoteAddr(request));
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");

		if  ( null != loginInfo ) {
			thSysUseHistTbVO.setRqester(loginInfo.getLoginId());
		}else{
			if  ( "/loginAjax.do".equals(request.getRequestURI()) ){
				String loginId = request.getParameter("loginId");

				if(!StringUtil.isEmpty(loginId)) {
					thSysUseHistTbVO.setRqester(loginId);
				}
			}
		}

		while(true) {
			try {
				int result = commSysUseHistMapper.insertSysUseHist(thSysUseHistTbVO);

				if  ( result > 0 )  {
					if  ( loginAt )  {
						session.setAttribute("loginSysUseHistSn", thSysUseHistTbVO.getRequstSn());
					}

					return;
				}

				return;
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}

	}

	@Override
	@Transactional
	public void updateSysUseHistRqester(String rqester) throws Exception {

		ThSysUseHistTbVO thSysUseHistTbVO = new ThSysUseHistTbVO();
		thSysUseHistTbVO.setRequstSn((String) session.getAttribute("loginSysUseHistSn"));
		thSysUseHistTbVO.setRqester(rqester);

		commSysUseHistMapper.updateSysUseHist(thSysUseHistTbVO);

	}

}
