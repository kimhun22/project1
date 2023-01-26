<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title><c:if test="${sessionScope.loginVO.runMode != 'PROD'}">[<c:out value="${sessionScope.loginVO.runMode}" />] </c:if>스마트 인허가 </title>
<!-- 스크립트 선언 -->
<link rel="stylesheet" type="text/css" href="/smart/css/smart.css" />
<link rel="stylesheet" type="text/css" href="/smart/css/admin-layout.css" />
</head>
<body style="overflow-x:hidden">
<section class="content">
<article class="block">

<form:form commandName="TnUserTbExVO" id="detailForm" name="detailForm" method="post" action="/${hmSiteCode}/system/deptInfo/deptList.do">
	<form:hidden path="pageIndex"/>
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="partcode" />
	<form:hidden path="userId"/>
	<form:hidden path="rnum"/>
		<div class="table_list2 paddingTop20 hover">
			<table>
				<colgroup>
					<col width="180">
					<col>
				</colgroup>
				<caption>부서정보</caption>
				<tbody>
									 <tr>
										<th>부서 코드</th>
										<td><c:out value="${deptDetail.deptCode}" /></td>
									</tr>
									 <tr>
										<th>기관 코드</th>
										<td><c:out value="${deptDetail.insttCode}" /></td>
									</tr>
									 <tr>
										<th>최상위 부서 코드</th>
										<td><c:out value="${deptDetail.supperDeptCode}" /></td>
									</tr>
									<tr>
										<th>상위 부서 코드</th>
										<td><c:out value="${deptDetail.upperDeptCode}" /></td>
									</tr>
									<tr>
										<th>부서 명</th>
										<td><c:out value="${deptDetail.deptNm}" /></td>
									</tr>
									 <tr>
										<th>부서 전체 명</th>
										<td><c:out value="${deptDetail.deptAllNm}" /></td>
									</tr>
									 <tr>
										<th>조직 구분 코드</th>
										<td><c:out value="${deptDetail.orgnztSeCode}" /></td>
									</tr>
                                     <tr>
                                        <th>조직 순서</th>
                                        <td><c:out value="${deptDetail.orgnztOrdr}" /></td>
                                    </tr>
                                     <tr>
                                        <th>정렬 순서</th>
                                        <td><c:out value="${deptDetail.sortOrdr}" /></td>
                                    </tr>
								</tbody>
			</table>
			<ul class="listBTN">
				<li><button type="button" class="ubtn gray02 hover" onclick="fn_deptList();"><i class="icon icon_menu"></i>목록</button> </li>
			</ul>
		</div>
</form:form>
</article>
</section>
<script>

//목록
 function fn_deptList() {

    $("#detailForm").submit();
};

$(function() {
});
</script>


</body>
</html>