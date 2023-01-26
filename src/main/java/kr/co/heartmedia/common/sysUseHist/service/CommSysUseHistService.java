package kr.co.heartmedia.common.sysUseHist.service;

import javax.servlet.http.HttpServletRequest;

public interface CommSysUseHistService {



	void insertSysUseHist(HttpServletRequest request, boolean loginAt) throws Exception;

	void updateSysUseHistRqester(String rqester) throws Exception;

}
