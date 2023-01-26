package kr.co.heartmedia.system.userInfo.service;

import org.springframework.ui.ModelMap;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;

public interface AdminUserService {

	/**
	 * 사용자 목록
	 * @param TnUserTbExVO
	 * @param model
	 * @throws Exception
	*/
	public void selectAdminUserList(TnUserTbExVO TnUserTbExVO, ModelMap model) throws Exception;

	/**
	 * 사용자 상세
	 * @param TnUserTbExVO
	 * @param model
	 * @throws Exception
	*/
	public void selectAdminUserDetail(TnUserTbExVO TnUserTbExVO, ModelMap model) throws Exception;

	/**
	 * 사용자 수정
	 * @param sessionVO
	*/
	public int updateAdminUserProc(TnUserTbExVO sessionVO) throws Exception;

	/**
	 * 권한 변경 기록
	 * @param loginId
	 * @param authorCode
	 * @param useIp
	 * @throws Exception
	*/
	public void execChangeRoleHist(String loginId, String authorCode, String useIp) throws Exception;
}
