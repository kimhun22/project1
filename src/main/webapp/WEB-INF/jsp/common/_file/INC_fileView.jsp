<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<c:forEach items="${fileList}" var="file" varStatus="idx">
	<a onclick="gfn_fileDown('<c:out value="${file.fileNo}"/>','<c:out value="${file.fileSn}"/>','<c:out value="${file.hmSiteCode}"/>');" href="javascript:void(0);" >${file.orginlFileNm}</a>
	<br/>
</c:forEach>
