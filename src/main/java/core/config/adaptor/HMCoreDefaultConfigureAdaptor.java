package core.config.adaptor;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import core.config.annotation.HMCoreDefaultConfigure;

/**
 * HMCore Common Configure Adaptor
 * <pre>
 * configureCommonsMultipartResolver
 *  : CommonMultipartResolver 설정
 *    ex)
 *    	multipartResolver.setMaxUploadSize(maxUploadSize);
 *    	multipartResolver.setMaxInMemorySize(10485760);	//10MB
 *    	multipartResolver.setDefaultEncoding("UTF-8");
 * </pre>
 * 
 * 
 * @author ysKo
 * @since 0.0.5-SNAPSHOT
 *
 */
public abstract class HMCoreDefaultConfigureAdaptor implements HMCoreDefaultConfigure {
	
	@Override
	public void configureCommonsMultipartResolver(CommonsMultipartResolver multipartResolver) {
		// TODO
	}
}
