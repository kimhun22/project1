package kr.co.heartmedia.board.sysErbbs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import core.util.PropertiesUtil;
import core.util.StringUtil;
import kr.co.heartmedia.board.sysErbbs.service.SysErbbsService;
import kr.co.heartmedia.common.code.service.CommCodeService;
import kr.co.heartmedia.common.file.service.CommFileService;
import kr.co.heartmedia.vo.TnAtchFileTbVO;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;
import kr.co.heartmedia.vo.extend.TnUserTbExVO;
import kr.co.heartmedia.vo.extend.TsCmmnCodeTbExVO;

@Controller
@RequestMapping(value = "/{hmSiteCode}/board/sysErbbs")
public class SysErbbsController {

	@Resource
	private SysErbbsService sysErbbsService;

	@Resource
	private CommCodeService commCodeService;

	@Resource
	private CommFileService commFileService;

	/**
	 * 목록 페이지
	 *
	 * @param model
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sysErbbsList.do")
	public String list(ModelMap model, @ModelAttribute TnBbsTbExVO tnBbsTbExVO, TnUserTbExVO sessionVO)throws Exception {

		// 게시물 구분 코드
		TsCmmnCodeTbExVO tsCmmnCodeTbExVO = new TsCmmnCodeTbExVO();
		tsCmmnCodeTbExVO.setHmSiteCode(tnBbsTbExVO.getHmSiteCode());
		tsCmmnCodeTbExVO.setParntsCmmnCode("RPT001");
		model.addAttribute("nttSeCodeList", commCodeService.selectSubCodeListSiteCode(tsCmmnCodeTbExVO));

		// 시스템 오류게시판 목록
		sysErbbsService.selectSysErbbsList(model, tnBbsTbExVO);

		return "board/sysErbbs/sysErbbsList.cn";

	}

	/**
	 * 시스템 오류게시판 등록/수정 페이지
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save.do")
	public String save(ModelMap model, @ModelAttribute TnBbsTbExVO tnBbsTbExVO) throws Exception {

		// 게시물 구분 코드
		TsCmmnCodeTbExVO tsCmmnCodeTbExVO = new TsCmmnCodeTbExVO();
		tsCmmnCodeTbExVO.setHmSiteCode(tnBbsTbExVO.getHmSiteCode());
		tsCmmnCodeTbExVO.setParntsCmmnCode("RPT001");
		model.addAttribute("nttSeCodeList", commCodeService.selectSubCodeListSiteCode(tsCmmnCodeTbExVO));

		// 시스템 오류게시판 상세
		if (StringUtil.isNotEmpty(tnBbsTbExVO.getNttNo())) {

			TnBbsTbExVO data = sysErbbsService.selectSysErbbs(tnBbsTbExVO);
			model.addAttribute("data", data);

			TnAtchFileTbVO tnAtchFileTbVO = new TnAtchFileTbVO();
			if (data != null && data.getAtchFileNo() != null) {

				tnAtchFileTbVO.setFileNo(data.getAtchFileNo());
				tnAtchFileTbVO.setHmSiteCode(tnBbsTbExVO.getHmSiteCode());

				model.addAttribute("atchFile", commFileService.selectFileList(tnAtchFileTbVO));
			}
		}

		String allowFileType = PropertiesUtil.getProperty("globals.sample.allowFileType");
		model.addAttribute("allowFileType", allowFileType);

		return "board/sysErbbs/sysErbbsSave.cn";

	}

	/**
	 * 시스템 오류게시판 저장 처리
	 *
	 * @param tnBbsTbExVO
	 * @param atchFiles
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/saveProc.do", method = RequestMethod.POST)
	public String saveProc(TnBbsTbExVO tnBbsTbExVO, TnUserTbExVO sessionVO, @RequestParam(value = "atchFile") List<MultipartFile> atchFiles) throws Exception {

		tnBbsTbExVO.setAtchFiles(commFileService.makeUploadFile(atchFiles));

		if (StringUtil.isEmpty(tnBbsTbExVO.getAnswerUserId())) {
			tnBbsTbExVO.setAnswerUserId(sessionVO.getLoginId());

		}

		if (StringUtil.isEmpty(tnBbsTbExVO.getNttNo())) {
			sysErbbsService.insertSysErbbs(tnBbsTbExVO);
		} else {
			sysErbbsService.updateSysErbbs(tnBbsTbExVO);
		}

		return "redirect:/{hmSiteCode}/board/sysErbbs/sysErbbsList.do";

	}

	/**
	 * 시스템 오류게시판 상세 페이지
	 *
	 * @param model
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view.do")
	public String view(ModelMap model, @ModelAttribute TnBbsTbExVO tnBbsTbExVO) throws Exception {

		// 게시물 구분 코드
		TsCmmnCodeTbExVO tsCmmnCodeTbExVO = new TsCmmnCodeTbExVO();
		tsCmmnCodeTbExVO.setParntsCmmnCode("RPT001");
		model.addAttribute("nttSeCodeList", commCodeService.selectSubCodeListSiteCode(tsCmmnCodeTbExVO));

		// 시스템 오류게시판 상세
		model.addAttribute("data", sysErbbsService.selectSysErbbs(tnBbsTbExVO));

		return "board/sysErbbs/sysErbbsView.cn";

	}

	/**
	 * 시스템 오류게시판 삭제 처리
	 *
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteProc.do", method = RequestMethod.POST)
	public String deleteProc(TnBbsTbExVO tnBbsTbExVO) throws Exception {

		sysErbbsService.deleteSysErbbs(tnBbsTbExVO);

		return "redirect:/{hmSiteCode}/board/sysErbbs/sysErbbsList.do";

	}

}
