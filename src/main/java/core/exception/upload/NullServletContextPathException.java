package core.exception.upload;

import core.exception.HMException;

@SuppressWarnings("serial")
public class NullServletContextPathException extends HMException {
	private static String message = "서블릿경로가 지정되지 않았습니다.";
	
	public NullServletContextPathException(){
		super(message);
	}
}
