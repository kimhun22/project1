package core.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import egovframework.cmmn.web.EgovImgPaginationRenderer;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import egovframework.rte.fdl.cmmn.trace.handler.TraceHandler;
import egovframework.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

@Configuration
public class EgovCommonConfig {

	/**
	 * 전자정부 leaveaTrace 설정
	 */
	@Bean(name="leaveaTrace")
	public LeaveaTrace leaveaTrace(){
		LeaveaTrace leaveaTrace = new LeaveaTrace();
		leaveaTrace.setTraceHandlerServices(traceHandlerServices());
		return leaveaTrace;
	}

	@Bean(name="DefaultTraceHandleManager")
	public DefaultTraceHandleManager[] traceHandlerServices(){
		DefaultTraceHandleManager traceHandleManager = new DefaultTraceHandleManager();
		traceHandleManager.setReqExpMatcher(new AntPathMatcher());
		traceHandleManager.setPatterns(new String[]{"*"});
		traceHandleManager.setHandlers(new TraceHandler[]{new DefaultTraceHandler()});

		DefaultTraceHandleManager[] traceHandleManagers = new DefaultTraceHandleManager[]{traceHandleManager};

		return traceHandleManagers;
	}

	@Bean(name="imageRender")
	public EgovImgPaginationRenderer imageRender() {
		EgovImgPaginationRenderer egovImgPaginationRenderer = new EgovImgPaginationRenderer();
		return egovImgPaginationRenderer;
	}

	@Bean(name="paginationManager")
	public DefaultPaginationManager paginationManager(EgovImgPaginationRenderer imageRenderer) {
		DefaultPaginationManager defaultPaginationManager = new DefaultPaginationManager();
		Map<String, PaginationRenderer> rendererType = new HashMap<String, PaginationRenderer>();
		rendererType.put("image", imageRenderer);
		defaultPaginationManager.setRendererType(rendererType);
		return defaultPaginationManager;
	}
}
