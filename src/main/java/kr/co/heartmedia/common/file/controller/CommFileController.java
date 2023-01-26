package kr.co.heartmedia.common.file.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.exception.HMException;
import core.file.FileDownloadView;

import core.util.StringUtil;
import kr.co.heartmedia.common.file.service.impl.CommFileServiceImpl;
import kr.co.heartmedia.vo.TnAtchFileTbVO;


@Controller
@RequestMapping(value = "/comm/file")
public class CommFileController {

	@Resource
	private CommFileServiceImpl commFileService;


	/**
	 * 첨부파일 폼 제공
	 * @param model
	 * @param flag
	 * @param inputNm
	 * @param maxFileCount
	 * @param fileNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form.do")
	public String fileList (ModelMap model, @RequestParam(value="flag", required=true) String flag, @RequestParam(value="inputNm", required=false) String inputNm, @RequestParam(value="maxFileCount", required=false) String maxFileCount, @RequestParam(value="fileNo", required=false) Integer fileNo, @RequestParam(value="hmSiteCode", required=false) String hmSiteCode) throws Exception {

		model.addAttribute("flag", flag);
		model.addAttribute("inputNm", inputNm);
		model.addAttribute("maxFileCount", maxFileCount);

		if  ( StringUtil.isNotEmpty(fileNo) )  {

			TnAtchFileTbVO tnAtchFileTbVO = new TnAtchFileTbVO();
			tnAtchFileTbVO.setFileNo(fileNo);
			tnAtchFileTbVO.setHmSiteCode(hmSiteCode);
			model.addAttribute("fileList", commFileService.selectFileList(tnAtchFileTbVO));
		}

		String returnUrl = "common/_file";
		if("add".equals(flag)) returnUrl += "/INC_fileForm";
		else if("edit".equals(flag)) returnUrl += "/INC_fileForm";
		else if("view".equals(flag)) returnUrl += "/INC_fileView";

		return returnUrl;

	}

	/**
	 * 첨부파일 다운로드
	 * @param response
	 * @param tnAtchFileTbVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download.do")
	public ModelAndView fileDownload(HttpServletResponse response, @ModelAttribute TnAtchFileTbVO tnAtchFileTbVO) throws Exception {

		// 파일 접근 권한이 없을 경우
//		if  ( !commFileService.isAccessFile(paramTnAtchFileTbVO) )  {
//			throw new HMException("접근 권한이 없습니다.");
//		}

		tnAtchFileTbVO = commFileService.selectFile(tnAtchFileTbVO);

		File file = new File(tnAtchFileTbVO.getFilePath() + File.separator + tnAtchFileTbVO.getFileNm());

		if  ( !file.isFile() )  {
			FileDownloadView.setErrorMessage(response, "파일이 존재하지 않습니다.");
			return null;
		}

		Map<String, Object> downloadMap = new HashMap<String, Object>();
		downloadMap.put("file", file);
		downloadMap.put("fileName", tnAtchFileTbVO.getOrginlFileNm());

        return new ModelAndView("download", "downloadFileMap", downloadMap);

	}

	/**
	 * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
	 * @param tnAtchFileTbVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/imageView.do")
	public ModelAndView getImageInf(@ModelAttribute TnAtchFileTbVO tnAtchFileTbVO) throws Exception {

		// 파일 접근 권한이 없을 경우
//		if  ( !commFileService.isAccessFile(paramTnAtchFileTbVO) )  {
//			throw new HMException("접근 권한이 없습니다.");
//		}

		tnAtchFileTbVO = commFileService.selectFile(tnAtchFileTbVO);

		String[] allowFileTypeArr = {"gif", "png", "jpg", "bmp", "jpeg"};
		boolean isAllow = false;
		for  ( String type : allowFileTypeArr )  {
			if  ( type.equals(tnAtchFileTbVO.getFileExtsn()) )  {
				isAllow = true;
				break;
			}
		}

		if  ( !isAllow )  {
			throw new HMException("이미지가 아닌 확장자 파일은 이용할 수 없습니다.");
		}

		File file = new File(tnAtchFileTbVO.getFilePath(), tnAtchFileTbVO.getFileNm());

		Map<String, Object> imageFileMap = new HashMap<String, Object>();
		imageFileMap.put("file", file);
		imageFileMap.put("extension", tnAtchFileTbVO.getFileExtsn());

        return new ModelAndView("imageFileView", "imageFileMap", imageFileMap);

	}

}
