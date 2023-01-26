package core.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.dao.DataAccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import core.config.annotation.EnableHMCoreException;
import core.exception.HMException;

@Configuration
@ControllerAdvice
public class HMCoreGlobalExceptionHandler implements ImportAware {

	private String jsonViewName;
	private String errorViewPath;

	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public ModelAndView sqlExceptionHandler(
			Exception ex,
			HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Locale locale){

		return userExceptionHandler(new HMException("DB Error!", ex.getCause()), request, response, session, locale);
	}

	@ExceptionHandler(HMException.class)
	public ModelAndView userExceptionHandler(
			HMException ex,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale){
		ModelAndView mav = new ModelAndView();

		mav.addObject("exception", ex);

		if(isAjax(request)){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			mav.setViewName(jsonViewName);
		} else {
			mav.setViewName(errorViewPath);
		}
		return mav;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public void incorrectAccessException(
			HttpRequestMethodNotSupportedException ex,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) throws IOException{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxxUploadSizeExceededExceptionHandler(
			MaxUploadSizeExceededException ex,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale){
		return userExceptionHandler(new HMException("최대 업로드가능 용량(" + FileUtils.byteCountToDisplaySize(ex.getMaxUploadSize()) + ")이 초과되었습니다."), request, response, session, locale);
	}

	private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		this.jsonViewName = (String) importMetadata.getAnnotationAttributes(EnableHMCoreException.class.getName()).get("jsonViewName");
		this.errorViewPath = (String) importMetadata.getAnnotationAttributes(EnableHMCoreException.class.getName()).get("errorViewPath");
	}
}
