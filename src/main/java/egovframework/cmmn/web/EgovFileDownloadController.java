package egovframework.cmmn.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.cmmn.service.FileService;
import egovframework.cmmn.service.FileVO;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일      	수정자           수정내용
 *  ------------   --------    ---------------------------
 *   2009.03.25  	이삼섭          최초 생성
 *   2014.02.24		이기하          IE11 브라우저 한글 파일 다운로드시 에러 수정
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovFileDownloadController {

	@Resource(name = "fileService")
	private FileService fileService;

	private static final Logger LOG = Logger.getLogger(EgovFileDownloadController.class.getName());

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			// throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmm/fms/FileDown.do")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String atchFileId = (String) commandMap.get("attachedFileId");
		String fileSn = (String) commandMap.get("detailId");
		Boolean isAuthenticated = true;

		if (isAuthenticated) {

			FileVO fileVO = new FileVO();
			fileVO.setAttachedFileId(atchFileId);
			fileVO.setDetailId(fileSn);
			FileVO fvo = fileService.selectFileInf(fileVO);

			File uFile = new File(fvo.getFileURL(), fvo.getUploadedFileName());
			int fSize = (int) uFile.length();

			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				// response.setBufferSize(fSize); // OutOfMemeory 발생
				response.setContentType(mimetype);
				// response.setHeader("Content-Disposition",
				// "attachment; filename=\"" +
				// URLEncoder.encode(fvo.getOrignlFileNm(), "euc-kr") + "\"");
				setDisposition(fvo.getOriginalFileName(), request, response);
				response.setContentLength(fSize);

				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (Exception ex) {
					// ex.printStackTrace();
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error
					LOG.debug("IGNORED: " + ex.getMessage());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							// no-op
							LOG.debug("IGNORED: " + ignore.getMessage());
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							// no-op
							LOG.debug("IGNORED: " + ignore.getMessage());
						}
					}
				}

			} else { // 파일DB 에는 있지만 실제로 서버에 파일이 없는경우 (에러가 아닌 파일없다는 팝업창으로 대신)
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");

				PrintWriter printwriter = response.getWriter();
				printwriter.println("<html>");
				printwriter.println("<head>");
				printwriter.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
				printwriter.println("<link type='text/css' rel='stylesheet' href='" + request.getContextPath() + "/contents/css/popup.css'/>");
				printwriter.println("<link type='text/css' rel='stylesheet' href='" + request.getContextPath() + "/contents/theme/type2/css/type.css' /> ");
				printwriter.println("<title>첨부파일이 없습니다.</title>");
				printwriter.println("</head>");
				printwriter.println("<body>");
				printwriter.println("<div class='popup_wrap'>");
				printwriter.println("    <div class='popup_title_bx'>");
				printwriter.println("     <ul>");
				printwriter.println("         <li class='popup_title_bl'><h1>첨부파일이 없습니다.</h1></li> ");
				printwriter.println("            <li class='popup_close2'><a href='#'></a></li>");
				printwriter.println("        </ul> ");
				printwriter.println("    </div>");
				printwriter.println("    <div class='popup_ctbx'>");
				printwriter.println("        <ul class='nofile'>");
				printwriter.println("         <li class='filename'>" + fvo.getOriginalFileName() + "</li>");
				printwriter.println("         <li>해당 첨부파일을 찾을 수 없습니다. </li>");
				printwriter.println("     </ul>");
				printwriter.println("    </div>");
				printwriter.println("    <p class='popupbtn_bx'>  ");
				printwriter.println("     <a href='#' onclick='javascript:window.close();' class='btn_size2'><span>닫기</span></a>");
				printwriter.println("    </p>");
				printwriter.println("</div>");
				printwriter.println("</body>");
				printwriter.println("</html>");
				printwriter.flush();
				printwriter.close();
			}
		}
	}
}
