<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<spring:eval var="sampleMaxFileCnt" expression="@appConfig.getProperty('globals.sample.maxFileCnt')"></spring:eval>

<script>

</script>

	<div class="m_top">
		<h2 class="ctitle">Sample</h2>
	</div>

<%@include file="/WEB-INF/jsp/common/_inc/_footer.jsp" %>
