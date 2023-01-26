package core.exception.upload;

import core.exception.HMException;

@SuppressWarnings("serial")
public class SaveFailException extends HMException {
	private static String message = "파일저장에 실패하였습니다.";
	
	public SaveFailException(){
		super(message);
	}
}
