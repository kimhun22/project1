<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=1200">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>스마트협의시스템</title>

<script src="${basePath }/js/jquery-3.2.1.min.js"></script>
<script src="${basePath }/js/script.js"></script>

<script src="<c:url value='/admin/js/lib/ui/1.11.1/jquery-ui.min.js' />"></script>
<script src="<c:url value='/admin/js/lib/jquery-migrate-3.0.0.min.js' />"></script>
<script src="<c:url value='/admin/js/lib/jquery.cookie.js' />"></script>
<script src="<c:url value='/admin/js/lib/jquery.form.js' />"></script>
<script src="<c:url value='/admin/js/lib/picker/jquery.mtz.monthpicker.js' />"></script>
<script src="<c:url value='/admin/js/lib/blockUI/jquery.blockUI.js' />"></script>
<script src="<c:url value='/admin/js/hm/i18n_ko.js' />"></script>
<script src="<c:url value='/admin/js/hm/hm-common-0.1.js' />"></script>

<script type="text/javascript">
	// 로그인
	function fn_login() {

		// 아이디
		var loginId = '${param.loginId}';

		if(loginId==""){
			alert("아이디 값이 없습니다.");
		}

		$("#loginForm").ajaxSubmit({
			url : "/${hmSiteCode}<c:url value='/loginAjax.do'/>",
			success : function(data) {
				if  ( data.result )  {
					location.href = "<c:url value='/main.do'/>";
                }  else  {
                    alert(data.message);
                }
			}
		});
	}
</script>
</head>
<body>
<form id="loginForm">
    <input type="hidden" name="loginId" id="loginId" placeholder="사용자 아이디" title="사용자 아이디" value="${param.loginId}"/>
	<input type="hidden" name="ssoLogin" id="ssoLogin" placeholder="ssoLogin" title="ssoLogin" value="y"/>
</form>
</body>
<script>
fn_login();
</script>
</html>