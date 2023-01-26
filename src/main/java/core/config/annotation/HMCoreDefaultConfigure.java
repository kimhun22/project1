package core.config.annotation;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public interface HMCoreDefaultConfigure {
	public void configureCommonsMultipartResolver(CommonsMultipartResolver multipartResolver);
}
