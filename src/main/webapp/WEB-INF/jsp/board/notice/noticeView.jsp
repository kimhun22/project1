<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>



<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<title><c:if test="${sessionScope.loginVO.runMode != 'PROD'}">[<c:out value="${sessionScope.loginVO.runMode}" />] </c:if>스마트협의시스템 </title>
</head>
<body style="overflow-x:hidden">
	<script>
		// 등록 및 수정
		fn_goSave = function() {
			$("#viewForm").attr("action","/${hmSiteCode}/board/notice/noticeSave.do");
			$("#viewForm").submit();
		};

		// 목록
		fn_goList = function() {
			location.href = "/${hmSiteCode}/board/notice/noticeList.do";
		};

		// 삭제
		fn_delete = function() {
			if  ( confirm("삭제 하시겠습니까?") )  {
				$("#viewForm").attr("action","/${hmSiteCode}/board/notice/noticeDeleteProc.do");
				$("#viewForm").submit();
			}
		};
	</script>

	<section class="content">
		<article class="block">
			<div class="table_list2">
				<form id="viewForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="nttNo" id="nttNo" value="${data.nttNo}" />

					<table class="table_t01 data_table td_left hovertd">
						<colgroup>
							<col width="10%"/>
							<col width="40%"/>
							<col width="10%"/>
							<col width=""/>
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
								${data.nttSj}
							</td>
							<th>작성자</th>
							<td>
								${data.userNm}
							</td>
						</tr>
						<tr>
							<th>구분</th>
							<td>
               					<c:if test="${data.importYn eq 'Y'}">중요 게시물</c:if>
								<c:if test="${data.importYn eq 'N'}">일반 게시물</c:if>
							</td>
							<th>표출기간</th>
							<td>
								 <fmt:parseDate value="${data.showStartDt}" var="showStartDt" pattern="yyyyMMdd"/>
								 <fmt:parseDate value="${data.showEndDt}" var="showEndDt" pattern="yyyyMMdd"/>
								 <fmt:formatDate value="${showStartDt}" pattern="yyyy-MM-dd"/>
								 &nbsp;~&nbsp;
								 <fmt:formatDate value="${showEndDt}" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3">
								${hmFunction:unescapeXml(data.nttCn1)}
							</td>
						</tr>
						<tr>
							<th>키워드</th>
							<td colspan="3">
								<c:set var="allKwrd" value="${data.kwrd1}" />
								<c:if test="${not empty allKwrd && not empty data.kwrd2}"><c:set var="allKwrd" value="${allKwrd}, ${data.kwrd2}" /></c:if>
								<c:if test="${not empty allKwrd && not empty data.kwrd3}"><c:set var="allKwrd" value="${allKwrd}, ${data.kwrd3}" /></c:if>
								<c:if test="${not empty allKwrd && not empty data.kwrd4}"><c:set var="allKwrd" value="${allKwrd}, ${data.kwrd4}" /></c:if>
								<c:if test="${not empty allKwrd && not empty data.kwrd5}"><c:set var="allKwrd" value="${allKwrd}, ${data.kwrd5}" /></c:if>
								${allKwrd}
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3" class="t_left">
								<div class="fileForm">
									<c:import url="/comm/file/form.do">
										<c:param name="flag" value="view"/>
										<c:param name="fileNo" value="${data.atchFileNo}"/>
										<c:param name="hmSiteCode" value="${hmSiteCode}"/>
									</c:import>
								</div>
							</td>
						<tr>
					</table>
				</form>
			</div>

			<div class="btn_area">
		    	<div class="f_right">
					<button type="button" id="btn-save" class="ubtn red" onclick="fn_delete();"><i class="icon icon_delete"></i>삭제</button>
					<button type="button" id="btn-save" class="ubtn primary hover" onclick="fn_goSave();"><i class="icon icon_edit"></i>수정</button>
					<button type="button" id="btn-cancle" class="ubtn gray02 hover" onclick="fn_goList();"><i class="icon icon_menu"></i>목록</button>
		    	</div>
		    </div>
		</article>
	</section>
</body>
</html>