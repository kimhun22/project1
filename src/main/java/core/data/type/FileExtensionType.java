package core.data.type;

import org.apache.commons.lang.ArrayUtils;

public enum FileExtensionType {
	IMAGE(new String[]{"gif", "png", "jpg", "bmp", "jpeg"}), 
	DOCUMENT(new String[]{"txt", "hwp", "ppt", "pptx", "csv", "xls", "xlsx", "doc", "docs"}),
	ImageAndDocument((String[]) ArrayUtils.addAll(IMAGE.getValue(), DOCUMENT.getValue())),
	EXEC(new String[]{"exe", "js", "com", "tmp", "bat", "sys"});
	
	private final String[] value;
	
	private FileExtensionType(String[] value) {
		this.value = value;
	}
	
	public String[] getValue(){
		return value;
	}
}
