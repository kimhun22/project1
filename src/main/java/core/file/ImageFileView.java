package core.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import core.exception.HMException;

public class ImageFileView extends AbstractView {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageFileView.class);

	@Override
	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> fileMap = (Map<String, Object>) map.get("imageFileMap");
		if(fileMap == null){
			throw new HMException("ImageFileView에 imageFileMap이 지정되지않았습니다.");
		}

		File file = (File) fileMap.get("file");
		if(file == null){
			throw new HMException("imageFileMap에 file이 지정되지않았습니다.");
		}

		FileInputStream fis = null;

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;

		try {
			fis = new FileInputStream(file);
			in = new BufferedInputStream(fis);
			bStream = new ByteArrayOutputStream();

			int imgByte;
			while ((imgByte = in.read()) != -1) {
				bStream.write(imgByte);
			}

			String type = "";
			if ("jpg".equals(fileMap.get("extension"))) {
				type = "image/jpeg";
			} else {
				type = "image/" + ((String) fileMap.get("extension")).toLowerCase();
			}
			// 개행 치환
			type = type.replaceAll("[\\r\\n]", "");

			response.setHeader("Content-Type", type);
			response.setHeader("Accept-Ranges", "bytes");
			//X-Frame-Options: DENY
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

			bStream.close();
			in.close();
			fis.close();
		} catch (Exception e) {
			throw new HMException(e.getMessage());
		} finally {
			try {
				if  ( fis != null )  fis.close();
				if  ( in != null )  in.close();
				if  ( bStream != null )  bStream.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

}
