package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import core.config.adaptor.HMCoreDefaultConfigureAdaptor;
import core.config.annotation.EnableHMCoreDefault;
import core.config.annotation.EnableHMCoreException;
import core.util.PropertiesUtil;

@Configuration
@EnableHMCoreDefault
@EnableHMCoreException(errorViewPath="common/error/alertMessage")
public class CommonConfig extends HMCoreDefaultConfigureAdaptor {

	@Override
	public void configureCommonsMultipartResolver(CommonsMultipartResolver multipartResolver) {
		multipartResolver.setMaxUploadSize(Integer.parseInt(PropertiesUtil.getProperty("globals.upload.maxUploadSize")));
    	multipartResolver.setMaxInMemorySize(10485760);	//10MB
    	multipartResolver.setDefaultEncoding("UTF-8");
    	multipartResolver.setResolveLazily(true);
	}

}
