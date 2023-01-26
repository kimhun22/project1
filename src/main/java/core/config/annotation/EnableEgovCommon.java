package core.config.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Import;

import core.config.EgovCommonConfig;

/**
 *  Egov3.0 기본 설정 [Configure Adaptor : EgovCommonConfigureAdaptor]
 *  <pre>
 *  1) @Bean(name="leaveaTrace")
 *  2) @Bean(name="DefaultTraceHandleManager")
 *  </pre>
 * 
 * @author ysKo
 * @since 0.0.5-SNAPSHOT
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Import(EgovCommonConfig.class)
public @interface EnableEgovCommon {

}
