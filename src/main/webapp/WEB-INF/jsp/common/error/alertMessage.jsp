<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

	<!-- js -->
	<script src="/${hmSiteCode}<c:url value='/js/lib/jquery-3.6.1.min.js' />"></script>

	<script type="text/javascript">
		alert("${exception.message}");

		if  ( self !== top )  {
			if  ( "${exception.redirectUrl}" != "" )  {
				window.parent.location.href="${exception.redirectUrl}";
			}
		}
		else  {
			if  ( "${exception.redirectUrl}" != "" )  {
				location.href="${exception.redirectUrl}";
			}
			else  {
				history.go(-1);
			}
		}
	</script>