package core.exception.upload;

import core.exception.HMException;

@SuppressWarnings("serial")
public class EmptySavedFileNameException extends HMException {
	private static String message = "저장된 파일명이 지정되지 않았습니다.";
	
	public EmptySavedFileNameException(){
		super(message);
	}

}
