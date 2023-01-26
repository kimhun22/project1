<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="core.util.PropertiesUtil"%>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<%
		String hmSiteCode = PropertiesUtil.getProperty("globals.hm.site.code");
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Required meta tags always come first -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>스마트인허가</title>
	<link rel="stylesheet" href="/<%=hmSiteCode%>/css/bootstrap-grid.min.css"/>
	<link rel="stylesheet" href="/<%=hmSiteCode%>/css/common.css"/>
	<link rel="stylesheet" href="/<%=hmSiteCode%>/css/pretendard.css"/>
</head>

<body>
	<div id="wrap">
    <div class="err_page err01">
        <p class="t01">페이지를 찾을 수 없습니다.</p>
        <p class="t02">
            죄송합니다. 요청하신 페이지를 찾을 수 없습니다.<br><br>

            찾으시는 페이지의 주소가 잘못 입력되었거나,<br>
        	페이지의 주소가 변경 혹은 서버에서 삭제되었을 수 있습니다.
        </p>

        <a href="/<%=hmSiteCode%>/main.do" target="_parent" class="btn_home">  홈으로 돌아가기</a>
    </div>
</div>

</body>
</html>
