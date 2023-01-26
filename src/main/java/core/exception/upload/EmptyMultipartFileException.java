package core.exception.upload;

import core.exception.HMException;

@SuppressWarnings("serial")
public class EmptyMultipartFileException extends HMException {
	private static String message = "잘못된(비어있는) 파일입니다.";
	
	public EmptyMultipartFileException(){
		super(message);
	}
}
