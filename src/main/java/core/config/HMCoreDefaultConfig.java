package core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import core.config.annotation.HMCoreDefaultConfigure;


@Configuration
public class HMCoreDefaultConfig {
	@Autowired(required=false) HMCoreDefaultConfigure configure;

	/**
	 * Properties 설정 1 : @PropertySource 을 사용하는 방법 (${aaa} 형태)
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer placeholderConfigure = new PropertySourcesPlaceholderConfigurer();
		/*
		config.setIgnoreResourceNotFound(true);
		config.setIgnoreUnresolvablePlaceholders(true);
		*/
		return placeholderConfigure;
	}

	/**
	 *  Properties 설정 2 : SpEL을 사용하는 방법 (전역으로 사용할 경우 static )
	 */
	@Bean(name="appConfig")
	public static PropertiesFactoryBean applicationProperties() {
	    PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
	    factoryBean.setLocation(new ClassPathResource("properties/globals.properties"));
	    return factoryBean;
	}

	/**
	 * jsonView (Exception 처리를 위한 View)
	 */
	@Bean(name="jsonView")
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}

	/**
	 * MultipartResolver
	 */
	@Bean(name="filterMultipartResolver")
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver mResolver = new CommonsMultipartResolver();
		configure.configureCommonsMultipartResolver(mResolver);

		return mResolver;
	}

}
