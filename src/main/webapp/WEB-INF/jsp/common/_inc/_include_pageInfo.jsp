<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%
		String myIp = request.getRemoteAddr();

		if(myIp.contains("192.168") || myIp.contains("0:0:0")  || myIp.contains("127.0.0") ) {
%>
	<br/>
	<div >
		<div >
			<div>
			<ul>
				<li><strong>View File Path</strong> : ${viewFile}</li>
				<li>
				<strong>Validate</strong> :
				<a href="//validator.w3.org/check?uri=referer" target="_blank" title="새창">html</a>&nbsp;&nbsp;
				<a href="//jigsaw.w3.org/css-validator/check/referer" target="css" title="새창">css</a>
				</li>
			</ul>
			</div>
		</div>
	</div>

<%}%>