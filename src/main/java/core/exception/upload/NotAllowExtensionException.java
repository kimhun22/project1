package core.exception.upload;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import core.exception.HMException;

@SuppressWarnings("serial")
public class NotAllowExtensionException extends HMException {
	private static String message = "업로드 불가능한 확장자파일 입니다.";
	
	private String[] allowExts;
	
	private String extension;

	public static void setMessage(String message) {
		NotAllowExtensionException.message = message;
	}

	public String[] getAllowExts() {
		String[] allowExtsCp = Arrays.copyOf(allowExts, allowExts.length);
		return allowExtsCp;
	}

	public void setAllowExts(String[] allowExts) {

		this.allowExts = new String[allowExts.length];
		
		int i = 0;
		for  ( String data : allowExts ) {
			this.allowExts[i] = data;
			i++;
		}
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public NotAllowExtensionException(){
		super(message);
	}
	
	public NotAllowExtensionException(String extension){
		super(extension + " 파일은 " + message);
		this.extension = extension;
	}
	
	public NotAllowExtensionException(String[] allowExts){
		super(message + "\n" + "업로드 가능 확장자 (" + StringUtils.join(allowExts, ", ") + ")");
		
		this.allowExts = new String[allowExts.length];
		
		int i = 0;
		for  ( String data : allowExts ) {
			this.allowExts[i] = data;
			i++;
		}
	}
	
	public NotAllowExtensionException(String extension, String[] allowExts){
		super(extension + " 파일은 " + message + "\n" + "업로드 가능 확장자 (" + StringUtils.join(allowExts, ", ") + ")");
		this.extension = extension;

		this.allowExts = new String[allowExts.length];
		
		int i = 0;
		for  ( String data : allowExts ) {
			this.allowExts[i] = data;
			i++;
		}
	}
}
