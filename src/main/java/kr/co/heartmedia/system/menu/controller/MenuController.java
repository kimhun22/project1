package kr.co.heartmedia.system.menu.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heartmedia.system.menu.service.MenuService;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;



@Controller
@RequestMapping(value = "/{hmSiteCode}/system/menu")
public class MenuController {

	@Resource
	private MenuService menuService;


	/**
	 * 목록 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuList.do")
	public String list(ModelMap model, HttpServletRequest request) throws Exception {

		return "system/menu/menuList.cn";

	}

	/**
	 * 노드 목록 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getListAjax.do", method = RequestMethod.POST)
	public @ResponseBody List<TsMenuTbExVO> getListAjax(ModelMap model, HttpServletRequest request, TsMenuTbExVO vo) throws Exception {
		// 목록 조회
		List<TsMenuTbExVO> list = menuService.selectMenuList(vo);

		return list;
	}

	/**
	 * 상세보기 및 수정 페이지
	 * @param model
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view.do")
	public String view(ModelMap model, @ModelAttribute TsMenuTbExVO tsMenuTbExVO) throws Exception {
		// 상세 조회
		model.addAttribute("data", menuService.selectMenu(tsMenuTbExVO));

		if  ( "ROOT".equals(tsMenuTbExVO.getMenuCode()) )
			return "system/menu/rootView";
		else
			return "system/menu/view";

	}

	/**
	 * 수정
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editAjax(@ModelAttribute TsMenuTbExVO tsMenuTbExVO) throws Exception {

		// 수정 처리
		return menuService.updateMenu(tsMenuTbExVO);

	}

	/**
	 * 삭제
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteAjax(@ModelAttribute TsMenuTbExVO tsMenuTbExVO) throws Exception {

		// 삭제 처리
		return menuService.deleteMenu(tsMenuTbExVO);

	}

	/**
	 * 하위 코드 등록 팝업
	 * @param model
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPopup.do")
	public String addPopup(ModelMap model, @ModelAttribute TsMenuTbExVO tsMenuTbExVO) throws Exception {

		// 선택 코드 상세 조회
		TsMenuTbExVO upperMenuData = menuService.selectMenu(tsMenuTbExVO);
		model.addAttribute("upperMenuData", upperMenuData);

		// 다음 코드 조회
		TsMenuTbExVO nextMenuData = menuService.selectNextMenu(upperMenuData);
		model.addAttribute("nextMenuData", nextMenuData);

		return "system/menu/popup/addPopup";

	}

	/**
	 * 등록
	 * @param tsMenuTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addAjax(@ModelAttribute TsMenuTbExVO tsMenuTbExVO) throws Exception {

		// 등록 처리
		return menuService.insertMenu(tsMenuTbExVO);

	}

}
