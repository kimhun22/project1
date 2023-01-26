package kr.co.heartmedia.board.notice.controller;

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
import kr.co.heartmedia.board.notice.service.NoticeService;
import kr.co.heartmedia.common.code.service.CommCodeService;
import kr.co.heartmedia.common.file.service.CommFileService;
import kr.co.heartmedia.vo.TnAtchFileTbVO;
import kr.co.heartmedia.vo.extend.TnBbsTbExVO;



/**
 * @Class   	: NoticeController.java
 * @Description : 게시물관리 관련 Controller
 * @생성일자  : 2023. 1. 20
 */
@Controller
@RequestMapping(value = "/{hmSiteCode}/board/notice")
public class NoticeController {

	@Resource
	private NoticeService noticeService;

	@Resource
	private CommCodeService commCodeService;

	@Resource
	private CommFileService commFileService;


	/**
	 * 공지사항 목록 페이지
	 * @param model
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeList.do")
	public String list( ModelMap model, @ModelAttribute TnBbsTbExVO tnBbsTbExVO, @RequestParam(value = "goViewNttNo", required = false) Integer goViewNttNo) throws Exception {

		if  ( StringUtil.isNotEmpty(goViewNttNo) )  {
			//상세가기 번호가 존재하면 상세페이지 이동
			return "redirect:/{hmSiteCode}/board/notice/noticeView.do?nttNo="+goViewNttNo;
		}

		// 공지사항 목록
		noticeService.selectNoticeList(model, tnBbsTbExVO);

		return "board/notice/noticeList.cn";

	}

	/**
	 * 공지사항 등록/수정 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeSave.do")
	public String save(ModelMap model, @ModelAttribute TnBbsTbExVO tnBbsTbExVO) throws Exception {

		// 공지사항 상세
		if  ( StringUtil.isNotEmpty(tnBbsTbExVO.getNttNo()) )  {

			TnBbsTbExVO data =noticeService.selectNotice(tnBbsTbExVO);
			model.addAttribute("data", noticeService.selectNotice(tnBbsTbExVO));

			if (data != null && data.getAtchFileNo() != null) {

				TnAtchFileTbVO tnAtchFileTbVO = new TnAtchFileTbVO();

				tnAtchFileTbVO.setFileNo(data.getAtchFileNo());
				tnAtchFileTbVO.setHmSiteCode(tnBbsTbExVO.getHmSiteCode());

				model.addAttribute("atchFile", commFileService.selectFileList(tnAtchFileTbVO));
			}
		}

		String allowFileType = PropertiesUtil.getProperty("globals.sample.allowFileType");
		model.addAttribute("allowFileType", allowFileType);

		return "board/notice/noticeSave.cn";

	}

	/**
	 * 공지사항 저장 처리
	 * @param tnBbsTbExVO
	 * @param atchFiles
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeSaveProc.do", method = RequestMethod.POST)
	public String saveProc(TnBbsTbExVO tnBbsTbExVO, @RequestParam(value = "atchFile") List<MultipartFile> atchFiles) throws Exception {


 		tnBbsTbExVO.setAtchFiles(atchFiles);

		if  ( tnBbsTbExVO.getNttNo() == null || tnBbsTbExVO.getNttNo() == 0)  {
			// 등록
			noticeService.insertNotice(tnBbsTbExVO);
		}
		else  {
			// 수정
			noticeService.updateNotice(tnBbsTbExVO);
		}

		return "redirect:/{hmSiteCode}/board/notice/noticeList.do";

	}

	/**
	 * 공지사항 상세 페이지
	 * @param model
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeView.do")
	public String view(ModelMap model, @ModelAttribute TnBbsTbExVO tnBbsTbExVO) throws Exception {

		// 공지사항 상세
		model.addAttribute("data", noticeService.selectNotice(tnBbsTbExVO));

		return "board/notice/noticeView.cn";

	}

	/**
	 * 공지사항 삭제 처리
	 * @param tnBbsTbExVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeDeleteProc.do", method = RequestMethod.POST)
	public String deleteProc(TnBbsTbExVO tnBbsTbExVO) throws Exception {

		// 삭제
		noticeService.deleteNotice(tnBbsTbExVO);

		return "redirect:/{hmSiteCode}/board/notice/noticeList.do";

	}

}