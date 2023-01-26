package core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import core.util.FileUtil;

/**
 * 파일 다운로드 View
 * @author heart
 *
 */
public class FileDownloadView extends AbstractView {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadView.class);

	public FileDownloadView() {
		setContentType("application/download; charset=utf-8");
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> fileMap = (Map<String, Object>) model.get("downloadFileMap");
		if(fileMap == null){
			setErrorMessage(response, "FileDownloadView에 downloadFileMap이 지정되지않았습니다.");
		}

		File file = (File) fileMap.get("file");
		if(file == null){
			setErrorMessage(response, "downloadFileMap에 file이 지정되지않았습니다.");
		}

		OutputStream out = null;
		FileInputStream fis = null;

		try {
			response.setContentType(getContentType());
			response.setContentLength((int) file.length());

			String fileName = new FileUtil().encodingFileName(request, (String) fileMap.get("fileName"));

			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Set-Cookie", "fileDownload=true; path=/");

			out = response.getOutputStream();
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			setErrorMessage(response, "파일 다운로드에 실패하였습니다.");
		} finally {
			 if (fis != null)
			 try {
			   	fis.close();
			 } catch (NullPointerException ne) {
				 	LOGGER.error(ne.getMessage());
				} catch (IOException ex) {
				 LOGGER.error(ex.getMessage());
			 }
		}

	}

	public static void setErrorMessage(HttpServletResponse response, String message) throws Exception {

		response.reset();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Set-Cookie", "fileDownload=false; path=/");
		response.setStatus(500);
		PrintWriter printWriter = response.getWriter();
		printWriter.print(message);
		printWriter.flush();
		printWriter.close();

	}

}

