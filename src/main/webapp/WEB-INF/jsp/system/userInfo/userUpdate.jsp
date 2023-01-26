<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<link rel="stylesheet" type="text/css" href="/smart/css/smart.css" />
<link rel="stylesheet" type="text/css" href="/smart/css/admin-layout.css" />

<section class="content">
	<article class="block">
	<form:form commandName="TnUserTbExVO" id="detailForm" name="detailForm" method="post" action="/${hmSiteCode}/system/userInfo/userList.do">
		<form:hidden path="pageIndex"/>
		<form:hidden path="searchCondition" />
		<form:hidden path="searchKeyword" />
		<form:hidden path="partcode" />
		<form:hidden path="loginId"/>
		<form:hidden path="rnum"/>

			<div class="table_list2 paddingTop20 hover">
				<table>
					<colgroup>
						<col width="180">
						<col>
					</colgroup>
					<caption>사용자정보</caption>
					<tbody>
						 <tr>
							<th>아이디</th>
							<td><c:out value="${adminUserDetail.loginId}" /></td>
						</tr>
						 <tr>
							<th>이름</th>
							<td><c:out value="${adminUserDetail.userNm}" /></td>
						</tr>
						 <tr>
							<th>소속</th>
							<td><c:out value="${adminUserDetail.deptNm}" /></td>
						</tr>
						<tr>
							<th>직책</th>
							<td><c:out value="${adminUserDetail.clsfNm}" /></td>
						</tr>
						<tr>
							<th>이메일주소</th>
							<td><c:out value="${adminUserDetail.emailAdres}" /></td>
						</tr>
						 <tr>
							<th>전화번호</th>
							<td><c:out value="${adminUserDetail.tlphonNo}" /></td>
						</tr>
						 <tr>
							<th>팩스번호</th>
							<td><c:out value="${adminUserDetail.faxNo}" /></td>
						</tr>
						  <tr>
						<th><span class="spanLabel required">시스템 사용 권한</span></th>
						<td>
							<select class="wp_90" name="authorCode" id="authorCode" title="권한">
                         	    <option value="">-권한설정-</option>
								<c:forEach items="${authorCodeList}" var="codeData" varStatus="status">
									<option value="${codeData.cmmnCode}"<c:if test="${adminUserDetail.authorCode eq codeData.cmmnCode}"> selected</c:if> >${codeData.cmmnCodeNm}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
<!-- 						<tr>
							<th>비밀번호</th>
							<td>
								<input type="password" style="width: 200px;" name="userPwd">
							</td>
						</tr> -->
					</tbody>
				</table>
				<BR>
				<ul class="listBTN">
					<li><button type="text" class="ubtn gray02 hover" onclick="fn_userList();"><i class="icon icon_menu"></i>목록</button></li>
					<li><button type="button" class="ubtn primary hover" onclick="fn_adminUserUpdate();"><i class="icon icon_done"></i>저장</button></li>
				</ul>
			</div>

	</form:form>
	</article>
</section>

<script>

//목록
function fn_userList() {
	$('#detailForm').submit();
}

//저장
function fn_adminUserUpdate() {

	if (confirm("저장 하시겠습니까?")) {
		$("a").blur(); // A태그 focus 제거(엔터키로 중복 sumbit 방지)
		//showLoadingBar();
		//$('#detailForm').attr("action", "${basePath}/system/userInfo/updateAdminUserProc.do");
		//$('#detailForm').submit();

		var formData = new FormData($("#detailForm")[0]);

		$.ajax({
		    url: "/${hmSiteCode}/system/userInfo/updateAdminUserProc.do",
		    async: true,
		    type: "POST",
		    data: formData,
		    processData: false,
		    contentType: false,
		    dataType: "json",
		    success: function(response) {
			    // 전송이 성공한 경우 받는 응답 처리
			    alert("정상적으로 처리되었습니다.");
		    },
		    error: function(error) {
		    	// 전송이 실패한 경우 받는 응답 처리
		    	hideLoadingBar();
				 alert("현재  서비스가 원할하지 않습니다.\n잠시후 다시 이용해 주십시요.");
				 return;
		    }
		});
	}
};



</script>

