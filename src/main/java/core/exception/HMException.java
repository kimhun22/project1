package core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.config.adaptor.MybatisConfigureAdaptor;


@SuppressWarnings("serial")
public class HMException extends RuntimeException {

	private static Logger log = LoggerFactory.getLogger(MybatisConfigureAdaptor.class);

	private final static String NAME = "HMException";

	private int errorCode;

	private String redirectUrl;

	public int getErrorCode() {
		return errorCode;
	}

	public String getName() {
		return NAME;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public HMException(){
		super();
		if(log.isDebugEnabled()){
			log.debug("HMException");
		}
	}

	public HMException(String message){
		super(message);
		if(log.isDebugEnabled()){
			log.debug("HMException : " + message);
		}
	}

	public HMException(int errorCode){
		super();
		this.errorCode = errorCode;
		if(log.isDebugEnabled()){
			log.debug("HMException : (ERROR Code: " + errorCode + ")");
		}
	}

	public HMException(String message, int errorCode){
		super(message);
		this.errorCode = errorCode;
		if(log.isDebugEnabled()){
			log.debug("HMException : (ERROR Code: " + errorCode + ") " + message);
		}
	}

	public HMException(Throwable cause){
		super(cause);
		if(log.isDebugEnabled()){
			log.debug("HMException : (Cause) " + cause.getLocalizedMessage());
		}
	}

	public HMException(String message, Throwable cause){
		super(message, cause);
		if(log.isDebugEnabled()){
			log.debug("HMException : " + cause.getLocalizedMessage());
		}
	}

	public HMException(String message, Throwable cause, int errorCode){
		super(message, cause);
		this.errorCode = errorCode;
		if(log.isDebugEnabled()){
			log.debug("HMException : " + cause.getLocalizedMessage());
		}
	}

	public HMException(String message, String redirectUrl, boolean logAt) {
		super(message);
		this.redirectUrl = redirectUrl;
		if  ( logAt && log.isDebugEnabled() )  {
			log.debug("HMException : " + message + "(redirectUrl : " + redirectUrl + ")");
		}
	}

}
