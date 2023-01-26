package core.exception.upload;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import core.exception.HMException;

@SuppressWarnings("serial")
public class DenyExtentionException extends HMException {
	private static String message = "업로드 불가능한 확장자파일 입니다.";

	private String extension;

	private String[] denyExts;

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public static void setMessage(String message) {
		DenyExtentionException.message = message;
	}

	public String[] getDenyExts() {
		String[] denyExtsCp = Arrays.copyOf(denyExts, denyExts.length);
		return denyExtsCp;
	}

	public void setDenyExts(String[] denyExts) {
		
		this.denyExts = new String[denyExts.length];
		
		int i = 0;
		for  ( String data : denyExts ) {
			this.denyExts[i] = data;
			i++;
		}
	}

	public DenyExtentionException() {
		super(message);
	}

	public DenyExtentionException(String currentExtention) {
		super(currentExtention + " 파일은 " + message);
	}

	public DenyExtentionException(String[] denyExts) {
		super(message + "\n" + "업로드 불가능 확장자 (" + StringUtils.join(denyExts, ", ") + ")");
		
		this.denyExts = new String[denyExts.length];
		
		int i = 0;
		for  ( String data : denyExts ) {
			this.denyExts[i] = data;
			i++;
		}
	}

	public DenyExtentionException(String extension, String[] denyExts) {
		super(extension + " 파일은 " + message + "\n" + "업로드 불가능 확장자 (" + StringUtils.join(denyExts, ", ") + ")");
		this.extension = extension;
		
		this.denyExts = new String[denyExts.length];
		
		int i = 0;
		for  ( String data : denyExts ) {
			this.denyExts[i] = data;
			i++;
		}
	}
}
