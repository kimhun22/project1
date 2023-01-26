package kr.co.heartmedia.system.authorMenu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heartmedia.common.code.service.CommCodeService;
import kr.co.heartmedia.common.menu.service.CommMenuService;
import kr.co.heartmedia.system.authorMenu.service.AuthorMenuService;
import kr.co.heartmedia.vo.TsAuthorMenuTbVO;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;
import kr.co.heartmedia.vo.extend.TsMenuTbExVO;



@Controller
@RequestMapping(value = "/{hmSiteCode}/system/authorMenu")
public class AuthorMenuController {

	@Resource
	private AuthorMenuService authorMenuService;

	@Resource
	private CommCodeService commCodeService;

	@Resource
	private CommMenuService commMenuService;


	/**
	 * 목록 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/authorMenuList.do")
	public String list(ModelMap model, HttpServletRequest request, TsCmmnCodeTbExVO vo) throws Exception {
		// 권한 코드 목록
//		model.addAttribute("authorCodeList", commCodeService.selectSubCodeListSiteCode("COM001", hmSiteCode));
		vo.setParntsCmmnCode("COM001");
		model.addAttribute("authorCodeList", commCodeService.selectSubCodeListSiteCode(vo));

		return "system/authorMenu/authorMenuList.cn";

	}

	/**
	 * 노드 목록 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getListAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getListAjax(@RequestParam(value = "authorCode") String authorCode
							, @ModelAttribute TsMenuTbExVO vo
							, @ModelAttribute TsAuthorMenuTbVO tsAuthorMenuTbVO) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 메뉴 목록
		resultMap.put("menuList", commMenuService.selectMenuList(vo));
		// 메뉴 권한 매핑 목록
		resultMap.put("authorMenuList", authorMenuService.selectAuthorMenuMappingList(tsAuthorMenuTbVO));
		return resultMap;
	}

	/**
	 * 수정
	 * @param tsAuthorMenuTbVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveAjax(@ModelAttribute TsAuthorMenuTbVO tsAuthorMenuTbVO, @RequestParam(value = "menuList[]") List<String> menuList) throws Exception {

		// 수정 처리
		return authorMenuService.saveAuthorMenuMapping(tsAuthorMenuTbVO, menuList);

	}

}
