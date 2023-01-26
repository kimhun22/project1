<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<div class="board_search_type01">
    <form:form commandName="tnBbsTbExVO" id="listForm" name="listForm" method="post" action="/${hmSiteCode}/board/notice/noticeList.do">
		<form:hidden path="pageIndex"/>
		<form:hidden path="nttNo"/>

	    <div class="frow">
			<div class="fitem fb_80">
				<span class="label">구분</span>
				<div class="input_w w_120">
					<form:select path="searchCondition" class="in_type searchSelect">
						<form:option value="nttSj" label="제목"/>
						<form:option value="kwrd" label="키워드"/>
					</form:select>
				</div>
				<div class="input_w w_240">
					<form:input path="searchKeyword" class="in_type" onkeypress="if(event.keyCode == 13) fn_searchList(1);"/>
				</div>
			</div>
	    </div>
	    <div class="frow">
			<div class="fitem fb_80">
				<span class="label">등록일</span>
				<div class="input_w w_140">
				   <form:input path="startDate" type="text" class="inp_date hasDatepicker" size="10" readonly="true"/>
				</div>
				<div class="input_w">~</div>
				<div class="input_w w_140">
				   <form:input path="endDate" type="text" class="inp_date hasDatepicker" size="10" readonly="true"/>
				</div>
				<div id="datePeriod" class="btn_g">
					<button type="button" class="ubtn normal border select" onclick="fn_period('d7');">1주일</button>
					<button type="button" class="ubtn normal border" onclick="fn_period('m1');">1개월</button>
					<button type="button" class="ubtn normal border" onclick="fn_period('m3');">3개월</button>
					<button type="button" class="ubtn normal border" onclick="javascript:document.getElementById('startDate').value='';document.getElementById('endDate').value='';"><i class="icon icon_reset"></i>초기화</button>
				</div>
			</div>
			<div class="ml-auto">
				<button type="button" class="ubtn gray02 hover" onclick="fn_goSave();"><i class="icon icon_addOnly"></i>등록</button>
				<button type="button" class="ubtn normal primary hover" onclick="fn_searchList(1);"><i class="icon icon_search"></i>조회</button>
			</div>
	    </div>
    </form:form>
</div>

<!-- noticeList table 제목 hover -->
<style>
.data_table.noticeList tbody tr td:nth-child(2){font-weight: 500;}
.data_table.noticeList tbody tr:hover td:nth-child(2){ text-decoration: underline;}
</style>

<div class="d-flex align-items-center mt-4">
</div>
<div id="table01" class="fixed_scroll_head mt-2">
	<table class="table_t01 data_table hover noticeList ta_left_2">
		<colgroup>
			<col width="6%">
            <col width="">
            <col width="15%">
            <col width="15%">
            <col width="15%">
		</colgroup>
	    <thead>
	    	<tr>
				<th scope="row">순번</th>
                <th scope="row">제목</th>
                <th scope="row">작성일</th>
                <th scope="row">수정일</th>
                <th scope="row">작성자 부서</th>
			</tr>
	    </thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) != 0}">
					<c:forEach items="${list}" var="list" varStatus="status">
					<tr>
						<td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index}</td>
						<td onclick="javascript:fn_goView('${list.nttNo}');" style="cursor: pointer;">${list.nttSj}</td>
						<td><fmt:formatDate value="${list.registDt}" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${list.updateDt}" pattern="yyyy-MM-dd"/></td>
						<td>${list.deptNm}</td>
					</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="5">데이터가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>

<!-- 페이징 -->
<div class="board_bottom mt-4">
    <div class="paginate">
        <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_searchList" />
    </div>
    <p class="board_count">[총 <strong class="fc_primary">${listCnt }</strong>건]</p>
</div>


<script>
$(document).ready(function() {
	console.log("qwer");
});

function fn_searchList(pageNo) {
    showLoadingBar();
    $("#pageIndex").val(pageNo);
    $("#listForm").submit();
};

// 상세 이동
function fn_goView(nttNo) {
    $("#nttNo").val(nttNo);
    $("#listForm").attr("action","/${hmSiteCode}/board/notice/noticeView.do");
    $("#listForm").submit();
};

// 등록 및 수정
function fn_goSave(nttNo) {
	$("#nttNo").val(nttNo);
	$("#listForm").attr("action","/${hmSiteCode}/board/notice/noticeSave.do");
	$("#listForm").submit();
};

</script>