package kr.co.heartmedia.system.userInfo.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import core.util.EncryptionUtils;
import core.util.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.heartmedia.system.userInfo.mapper.AdminUserMapper;
import kr.co.heartmedia.system.userInfo.service.AdminUserService;
import kr.co.heartmedia.vo.ThAuthorChangeHistTbVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;


@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Resource
	private AdminUserMapper adminUserMapper;
	@Autowired
	private HttpSession session;

	@Override
	public void selectAdminUserList(TnUserTbExVO sessionVO, ModelMap model) throws Exception {

		// 페이징
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sessionVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sessionVO.getPageUnit());
		paginationInfo.setPageSize(sessionVO.getPageSize());

		sessionVO.setFirstIndex(paginationInfo.getFirstRecordIndex() + 1);
		sessionVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sessionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TnUserTbExVO> selectList = null;

		// 총 카운트
		int cnt = adminUserMapper.selectAdminUserListCnt(sessionVO);
		paginationInfo.setTotalRecordCount(cnt);

		if (cnt > 0) {
			// 리스트
			selectList = adminUserMapper.selectAdminUserList(sessionVO);
		}

		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("selectAdminUserList", selectList);
		model.addAttribute("selectAdminUserListCnt", cnt);
	}

	@Override
	public void selectAdminUserDetail(TnUserTbExVO TnUserTbExVO, ModelMap model) throws Exception {
		model.addAttribute("adminUserDetail", adminUserMapper.selectAdminUserDetail(TnUserTbExVO));
	}

	@Override
	public int updateAdminUserProc(TnUserTbExVO TnUserTbExvo) throws Exception {

		String userPW = TnUserTbExvo.getUserPwd();
		int result = 0;
		userPW = StringUtil.nvl( userPW , "");
		if(!"".equals(userPW)){
			String passwd = EncryptionUtils.encryptSha256(userPW, new byte[10]);
			TnUserTbExvo.setUserPwd(passwd);
		}

		result = adminUserMapper.updateAdminUserProc(TnUserTbExvo);

		return result;
	}


	@Override
	public void execChangeRoleHist(String loginId, String authorCode, String useIp) throws Exception {

		TnUserTbExVO inqTnUserTbExVo = new TnUserTbExVO();
		inqTnUserTbExVo.setLoginId(loginId);

		// 기존 역할 가져오기
		String oldAuthorCode = adminUserMapper.selectOldUserRole(inqTnUserTbExVo);

		String roleChangeCn = "";
		String execStatus = "";

		if( StringUtil.isNotEmpty(oldAuthorCode) ) { // 기존 역할이 있는 경우

			if( StringUtil.isEmpty(authorCode) ) { // 역할 삭제(신규 역할이 없는 경우)
				execStatus = "DELETE";
				roleChangeCn = roleChangeCn + " 기존 역할 (" + oldAuthorCode + ") 삭제";
			} else { // 역할 변경(신규 역할이 있는 경우)
				execStatus = "UPDATE";
				roleChangeCn = roleChangeCn + " 기존 역할 (" + oldAuthorCode + ") 에서 신규 역할(" + authorCode + ")로 변경";
			}

		} else {
			if( StringUtil.isNotEmpty(authorCode) ) { // 역할 부여(신규 역할이 있는 경우)
				execStatus = "INSERT";
				roleChangeCn = roleChangeCn + " 신규 역할 (" + authorCode + ") 부여";
			}
		}

		if( StringUtil.isNotEmpty(execStatus) ) { // 변경내용이 있을 경우
			ThAuthorChangeHistTbVO thAuthorChangeHistTbVO= new ThAuthorChangeHistTbVO();
			thAuthorChangeHistTbVO.setLoginId(loginId);
			thAuthorChangeHistTbVO.setUseIp(useIp);
			thAuthorChangeHistTbVO.setRoleChangeCn(roleChangeCn);

			// 권한 변경 이력 기록
			adminUserMapper.insertRoleChangeHist(thAuthorChangeHistTbVO);
			TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
			TnUserTbExVO tnUserTbExVO = new TnUserTbExVO();
			tnUserTbExVO.setAuthorCode(authorCode);
			tnUserTbExVO.setLoginId(loginId);
			tnUserTbExVO.setUpdateUserId(loginInfo.getLoginId());

			if("DELETE".equals(execStatus)) {
				adminUserMapper.deleteUserRole(tnUserTbExVO);
			} else if("UPDATE".equals(execStatus)) {
				adminUserMapper.updateUserRole(tnUserTbExVO);
			} else {
				adminUserMapper.insertUserRole(tnUserTbExVO);
			}
		}
	}
}
