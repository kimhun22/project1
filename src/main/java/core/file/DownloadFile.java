package core.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class DownloadFile {
	private String viewName = "download";
	private String charset = "utf-8";
	private File file;
	private String fileName;

	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public ModelAndView getView(){
		ModelAndView mav = new ModelAndView(viewName);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file", file);
		map.put("fileName", fileName);
		map.put("charset", charset);

		mav.addObject("downloadFileMap", map);

		return mav;
	}
}
