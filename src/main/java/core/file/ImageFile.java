package core.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class ImageFile {
	private String viewName = "imageFileView";
	private String charset = "utf-8";
	private File file;
	private String extension;

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
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	public ModelAndView getView(){
		ModelAndView mav = new ModelAndView(viewName);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file", file);
		map.put("extension", extension);
		map.put("charset", charset);

		mav.addObject("imageFileMap", map);

		return mav;
	}
}
