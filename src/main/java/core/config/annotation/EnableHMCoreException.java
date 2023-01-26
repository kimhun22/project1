package core.config.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Import;

import core.config.HMCoreGlobalExceptionHandler;

/**
 * HMException Global Handler
 * 
 * @author ysKo
 * @since 0.0.5-SNAPSHOT
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Import(HMCoreGlobalExceptionHandler.class)
public @interface EnableHMCoreException {
	String jsonViewName() default "jsonView";
	String errorViewPath();
}
