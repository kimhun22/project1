package config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import core.file.FileDownloadView;
import core.file.ImageFileView;
import core.util.MessageUtils;
import kr.co.heartmedia.admin.interceptor.AllInterceptor;
import kr.co.heartmedia.admin.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(
	basePackages={"kr.co"},
	excludeFilters={@Filter(Configuration.class)}
)
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/image/**").addResourceLocations("/image/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	}

	/**
	 * MessageConverter
	 *
	 * Jackson MessageConverter 는 pom에 org.codehaus.jackson 를 추가하면 자동으로 등록됨
	 * IE9 이하에서 ajaxSumbit() 처리시 오류가 발생하므로 (XMLHttpRequest 헤더가 안넘어옴) MediaType을 text/html로 변경하여 처리함
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(new MediaType("text", "html", Charset.forName("UTF-8")));
		mediaTypes.add(new MediaType("text", "xml", Charset.forName("UTF-8")));
		jsonMessageConverter.setSupportedMediaTypes(mediaTypes);
		converters.add(jsonMessageConverter);
	}

	/**
	 * tiles configuration
	 */
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{"classpath:config/tiles/layout.xml"});
		return tilesConfigurer;
	}

	/**
	 * bean name resolver
	 */
	@Bean
	public ViewResolver beanNameViewResolver(){
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
	}

	/**
	 * tiles resolver
	 */
	@Bean
	public ViewResolver tilesResolver(){
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(TilesView.class);
		resolver.setOrder(1);
		return resolver;
	}

	/**
	 * resolver config
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setOrder(2);
		return resolver;
	}

	/**
	 * 언어별 Message 설정
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(5);
		messageSource.setBasename("classpath:message/message");
		return messageSource;
	}

	/**
	 * 언어별 Message Accsessor
	 */
	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource());
	}

	/**
	 * 언어별 Message Utils
	 */
	@Bean
	public MessageUtils message() {
		MessageUtils message = new MessageUtils();
		message.setMessageSourceAccessor(messageSourceAccessor());
		return message;
	}

	@Bean
    public LocaleResolver sessionLocaleResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }

	@Bean
	public AllInterceptor allInterceptor(){
		return new AllInterceptor();
	}
	/**
	 * Login Interceptor
	 */
	@Bean
	public LoginInterceptor loginInterceptor(){
		return new LoginInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(allInterceptor()).addPathPatterns("/**/*.do");

		registry.addInterceptor(loginInterceptor())
		.addPathPatterns("/**/*.do")
		.excludePathPatterns("/**/login.do").excludePathPatterns("/**/loginAjax.do").excludePathPatterns("/**/login/**/*.do").excludePathPatterns("/**/logout.do")
		.excludePathPatterns("/**/comm/file/testFileDownload.do");
	}

	@Bean(name="download")
	public FileDownloadView downloadFile(){
	    FileDownloadView downloadFile = new FileDownloadView();
	    return downloadFile;
	}

	@Bean(name="imageFileView")
	public ImageFileView imageFileView(){
		return new ImageFileView();
	}

}
