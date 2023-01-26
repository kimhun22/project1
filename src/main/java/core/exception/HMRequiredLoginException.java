package core.exception;

@SuppressWarnings("serial")
public class HMRequiredLoginException extends HMException {
	private static final String MESSAGE = "로그인이 필요한 서비스입니다.";
	
	public HMRequiredLoginException(){
		super(MESSAGE);
	}
	
	public HMRequiredLoginException(String message){
		super(message);
	}
}
