package core.config.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Import;

import core.config.HMCoreDefaultConfig;

/**
 * HMCore 기본 설정 [Configure Adaptor : HMCoreDefaultConfigureAdaptor]
 * <pre>
 *  1) static PropertySourcesPlaceholderConfigurer
 *  2) static PropertiesFactoryBean (@Bean : appConfig) // bean name 및 Resource 위치를 변경하고 싶을때 재정의하여 사용
 *  3) MappingJacksonJsonView (@Bean : jsonView)
 *  4) CommonsMultipartResolver (configureCommonsMultipartResolver)
 *  </pre>
 * 
 * @author ysKo
 * @since 0.0.5-SNAPSHOT
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Import(HMCoreDefaultConfig.class)
public @interface EnableHMCoreDefault {

}
