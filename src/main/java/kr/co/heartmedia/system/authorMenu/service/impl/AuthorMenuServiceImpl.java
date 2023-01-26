package kr.co.heartmedia.system.authorMenu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.heartmedia.system.authorMenu.mapper.AuthorMenuMapper;
import kr.co.heartmedia.system.authorMenu.service.AuthorMenuService;
import kr.co.heartmedia.vo.TsAuthorMenuTbVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;



@Service
public class AuthorMenuServiceImpl implements AuthorMenuService {

	@Resource
	private AuthorMenuMapper authorMenuMapper;

	@Autowired
	private HttpSession session;


	@Override
	public List<TsAuthorMenuTbVO> selectAuthorMenuMappingList(TsAuthorMenuTbVO tsAuthorMenuTbVO) throws Exception {
		List<TsAuthorMenuTbVO> list = authorMenuMapper.selectAuthorMenuMappingList(tsAuthorMenuTbVO);
		return list;
	}

	@Override
	@Transactional
	public Map<String, Object> saveAuthorMenuMapping(TsAuthorMenuTbVO tsAuthorMenuTbVO, List<String> menuList) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 기존 메뉴 권한 매핑 삭제
		 ***********************************************************************/
		// 메뉴 권한 매핑 일괄 삭제
		authorMenuMapper.deleteAuthorMenuMapping(tsAuthorMenuTbVO);

		/***********************************************************************
		 * 2. 등록
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		int resultCnt = 0;
		for  ( String menuInfo : menuList )  {
			tsAuthorMenuTbVO.setMenuCode(menuInfo);
			tsAuthorMenuTbVO.setRegistUserId(loginInfo.getLoginId());
			tsAuthorMenuTbVO.setUpdateUserId(loginInfo.getLoginId());

			// 메뉴 권한 매핑 등록
			resultCnt += authorMenuMapper.insertAuthorMenuMapping(tsAuthorMenuTbVO);
		}

		// 성공
		if  ( resultCnt == menuList.size() )  {
			resultMap.put("result", true);
			resultMap.put("message", "저장 되었습니다.");
		}
		// 실패
		else  {
			resultMap.put("result", false);
			resultMap.put("message", "저장에 실패하였습니다.");

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return resultMap;

	}

}
