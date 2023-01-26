package core.exception.upload;

import core.exception.HMException;

@SuppressWarnings("serial")
public class NullUploadPathException extends HMException {
	private static String message = "업로드경로가 지정되지 않았습니다.";
	
	public NullUploadPathException(){
		super(message);
	}

}
