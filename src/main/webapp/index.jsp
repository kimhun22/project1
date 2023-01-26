<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="core.util.PropertiesUtil"%>

<%
	String hmSiteCode = PropertiesUtil.getProperty("globals.hm.site.code");
	response.sendRedirect("/"+hmSiteCode+"/login.do");
%>