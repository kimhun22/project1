package kr.co.heartmedia.system.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.heartmedia.system.menu.mapper.MenuMapper;
import kr.co.heartmedia.system.menu.service.MenuService;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;



@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuMapper menuMapper;

	@Autowired
	private HttpSession session;


	@Override
	public List<TsMenuTbExVO> selectMenuList(TsMenuTbExVO vo) throws Exception {

		List<TsMenuTbExVO> list = menuMapper.selectMenuList(vo);

		return list;

	}

	@Override
	public TsMenuTbExVO selectMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception {

		TsMenuTbExVO data = menuMapper.selectMenu(tsMenuTbExVO);

		return data;

	}

	@Override
	public TsMenuTbExVO selectNextMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception {

		TsMenuTbExVO nextMenuData = new TsMenuTbExVO();
		nextMenuData.setSortOrdr(menuMapper.selectNextMenuOrd(tsMenuTbExVO));
		nextMenuData.setMenuCode(menuMapper.selectNextMenuId(tsMenuTbExVO));

		return nextMenuData;

	}

	@Override
	@Transactional
	public Map<String, Object> insertMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 등록 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tsMenuTbExVO.setRegistUserId(loginInfo.getLoginId());
		tsMenuTbExVO.setUpdateUserId(loginInfo.getLoginId());

		// 등록 처리
		int result = menuMapper.insertMenu(tsMenuTbExVO);

		// 성공
		if  ( result > 0 )  {
			resultMap.put("result", true);
			resultMap.put("message", "저장 되었습니다.");
		}
		// 실패
		else  {
			resultMap.put("result", false);
			resultMap.put("message", "저장에 실패하였습니다.");
		}

		return resultMap;

	}

	@Override
	@Transactional
	public Map<String, Object> updateMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 수정 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tsMenuTbExVO.setUpdateUserId(loginInfo.getLoginId());

		// 수정 처리
		int result = menuMapper.updateMenu(tsMenuTbExVO);

		// 성공
		if  ( result > 0 )  {
			// 사용 여부가 0(사용 안함) 일 경우
			if  ( "0".equals(tsMenuTbExVO.getUseAt()) )  {
				// 하위 메뉴 전체 사용 여부 0(사용 안함) 처리
				for  ( TsMenuTbExVO subMenuData : menuMapper.selectSubMenuList(tsMenuTbExVO) )  {
					subMenuData.setUpdateUserId(loginInfo.getLoginId());
					subMenuData.setUseAt("0");
					menuMapper.updateMenu(subMenuData);
				}
			}

			resultMap.put("result", true);
			resultMap.put("message", "저장 되었습니다.");
		}
		// 실패
		else  {
			resultMap.put("result", false);
			resultMap.put("message", "저장에 실패하였습니다.");
		}

		return resultMap;

	}

	@Override
	@Transactional
	public Map<String, Object> deleteMenu(TsMenuTbExVO tsMenuTbExVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		/***********************************************************************
		 * 1. 하위 메뉴 존재 여부 검증
		 ***********************************************************************/
		// 상세 조회
		TsMenuTbExVO menuData = menuMapper.selectMenu(tsMenuTbExVO);
		// 하위 메뉴가 존재할 경우
		if  ( menuData.getChildCnt() > 0 )  {
			resultMap.put("result", false);
			resultMap.put("message", "하위 메뉴가 존재하여 삭제할 수 없습니다.");
		}

		/***********************************************************************
		 * 2. 삭제 처리
		 ***********************************************************************/
		TnUserTbExVO loginInfo = (TnUserTbExVO) session.getAttribute("loginInfo");
		tsMenuTbExVO.setUpdateUserId(loginInfo.getLoginId());

		// 삭제 처리
		int result = menuMapper.deleteMenu(tsMenuTbExVO);

		// 성공
		if  ( result > 0 )  {
			resultMap.put("result", true);
			resultMap.put("message", "삭제 되었습니다.");
		}
		// 실패
		else  {
			resultMap.put("result", false);
			resultMap.put("message", "삭제에 실패하였습니다.");
		}

		return resultMap;

	}

}
