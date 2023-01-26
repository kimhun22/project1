package kr.co.heartmedia.system.code.controller;

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

import kr.co.heartmedia.system.code.service.CodeService;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;


@Controller
@RequestMapping(value = "{hmSiteCode}/system/code")
public class CodeController {

	@Resource
	private CodeService codeService;


	/**
	 * 목록 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/codeList.do")
	public String list(ModelMap model) throws Exception {

		return "system/code/codeList.cn";

	}

	/**
	 * 노드 목록 조회
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getListAjax.do", method = RequestMethod.POST)
	public @ResponseBody List<TsCmmnCodeTbExVO> getListAjax(
						 ModelMap model
						, HttpServletRequest request
						,@ModelAttribute TsCmmnCodeTbExVO tsCmmnCodeTbExVO
					) throws Exception {
		// 목록 조회
		List<TsCmmnCodeTbExVO> list = codeService.selectCodeList(tsCmmnCodeTbExVO);
		return list;
	}

	/**
	 * 상세보기 및 수정 페이지
	 * @param model
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view.do")
	public String view(ModelMap model, @ModelAttribute TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		// 상세 조회
		model.addAttribute("data", codeService.selectCode(tsCmmnCodeTbExVO));

		if  ( "ROOT".equals(tsCmmnCodeTbExVO.getCmmnCode()) )
			return "system/code/rootView";
		else
			return "system/code/view";

	}

	/**
	 * 수정
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editAjax(@ModelAttribute TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		// 수정 처리
		return codeService.updateCode(tsCmmnCodeTbExVO);

	}

	/**
	 * 삭제
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteAjax(@ModelAttribute TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		// 삭제 처리
		return codeService.deleteCode(tsCmmnCodeTbExVO);

	}

	/**
	 * 하위 코드 등록 팝업
	 * @param model
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPopup.do")
	public String addPopup(ModelMap model, @ModelAttribute TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		// 선택 코드 상세 조회
		TsCmmnCodeTbExVO upperCodeData = codeService.selectCode(tsCmmnCodeTbExVO);
		model.addAttribute("parntsCmmnCodeData", upperCodeData);

		// 다음 코드 조회
		TsCmmnCodeTbExVO nextCodeData = codeService.selectNextCode(upperCodeData);
		model.addAttribute("nextCmmnCodeData", nextCodeData);

		return "system/code/popup/addPopup";

	}

	/**
	 * 등록
	 * @param tsCmmnCodeTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addAjax.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addAjax(@ModelAttribute TsCmmnCodeTbExVO tsCmmnCodeTbExVO) throws Exception {

		// 등록 처리
		return codeService.insertCode(tsCmmnCodeTbExVO);

	}

}
