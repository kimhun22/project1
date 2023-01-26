<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=1200">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>스마트협의시스템</title>
<link rel="stylesheet" href="/${hmSiteCode}/css/bootstrap-grid.min.css" />
<link rel="stylesheet" href="/${hmSiteCode}/css/common.css" />
<link rel="stylesheet" href="/${hmSiteCode}/css/main.css" />
<link rel="stylesheet" href="/${hmSiteCode}/js/swiper/swiper.min.css" />

<script src="/${hmSiteCode}/js/lib/jquery-3.6.1.min.js" ></script>
<script src="/${hmSiteCode}/js/hm/hm-common-0.1.js"></script>

<script type="text/javascript">
	$(function (){
		$("#loginForm input").keydown(function(event){
			if(event.keyCode == 13){
				fn_login();
			}
		});

		// 아이디 저장 세팅
		var cookieId = getCookie("amanoTicketAdmin_cookieId");
		if  ( $.trim(cookieId).length > 0 )  {
	    	$("#id_save").attr("checked", true);
	    	$("#loginId").val(cookieId);
	    }
	});

	// 로그인
	function fn_login() {

		// 아이디
		if  ( !validateBaseElement("loginId", true) )  return false;
		// 비밀번호
		if  ( !validateBaseElement("userPwd", true) )  return false;

		var params = {
				loginId      : $("#loginId").val()
	          , userPwd       : $("#userPwd").val()
	  	}

		$.ajax({
	         type : "POST",            // HTTP method type(GET, POST) 형식이다.
	         url : "/${hmSiteCode}/loginAjax.do",      // 컨트롤러에서 대기중인 URL 주소이다.
	         data : params,            // Json 형식의 데이터이다.
	         success : function(data){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
	             // 응답코드 > 0000
	           if  ( data.result )  {
					if  ( $("#id_save").is(":checked") )  {
						setCookie("amanoTicketAdmin_cookieId", $("#loginId").val(), 7);
					} else {
						delCookie("amanoTicketAdmin_cookieId");
					}
					console.log(1111);
					location.href = "/${hmSiteCode}<c:url value='/main.do'/>";
	            }  else  {
	                alert(data.message);
	            }
	         },
	         error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
	             alert("통신 실패.")
	         }
	     });

	}
</script>
<style>

</style>
</head>
<body>

<div id="login" class="d-flex align-items-stretch">
	<div class="login_box m-auto">
		<form id="loginForm">
			<h1><img src="/${hmSiteCode}/image/login/login_logo.png" alt="스마트협의시스템"/></h1>
			<p class="txt">아이디와 비밀번호를 입력해주세요.</p>
			<p class="input id"><input type="text" name="loginId" id="loginId" placeholder="사용자 아이디" title="사용자 아이디"/></p>
			<p class="input password"><input type="password" name="userPwd" id="userPwd" placeholder="비밀번호" title="비밀번호"/></p>
			<div class="id_save">
				<p class="checkbox"><input type="checkbox" id="id_save"><label for="id_save">아이디 저장</label></p>
			</div>
			<input type="button" value="로그인" class="ubtn orange hover" onclick="fn_login();"/>
		</form>
	</div>
</div>
<!-- // wrap -->
</body>
<!-- // body -->
</html>

