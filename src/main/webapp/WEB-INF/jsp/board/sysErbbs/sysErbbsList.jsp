<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<div class="board_search_type01">
    <form:form commandName="tnBbsTbExVO" id="listForm" name="listForm" method="post" action="/${hmSiteCode}/board/sysErbbs/sysErbbsList.do">
		<form:hidden path="pageIndex"/>
		<form:hidden path="nttNo"/>
	    <div class="frow">
			<div class="fitem fb_40">
				<span class="label">구분</span>
				<div class="input_w w_full">
					<select name="searchNttSeCode" id="searchNttSeCode">
						<option value="">구분 전체</option>
						<c:forEach items="${nttSeCodeList}" var="nttSeCodeData">
								<option value="${nttSeCodeData.cmmnCode}" <c:if test="${param.searchNttSeCode eq nttSeCodeData.cmmnCode}">selected="selected"</c:if>>${nttSeCodeData.cmmnCodeNm}</option>
							</c:forEach>
					</select>
				</div>
			</div>
        </div>
        <div class="frow">
			<div class="fitem fb_40">
				<span class="label">제목</span>
				<div class="input_w w_full">
					<form:input path="nttSj" class="in_type" onkeypress="if(event.keyCode == 13) fn_searchList(1);"/>
				</div>
			</div>
			<div class="fitem fb_40">
				<span class="label">신고일</span>
				<div class="input_w w_140">
					<form:input path="searchRegistStartDe" class="inp_date" />
				</div>
				<div class="input_w">~</div>
				<div class="input_w w_140">
					<form:input path="searchRegistEndDe" class="inp_date" />
				</div>
			</div>
	    </div>
        <div class="frow">
			<div class="fitem fb_40">
				<span class="label">답변여부</span>
				<div class="input_w">
					<p class="radio"><form:radiobutton path="kwrd7" value="" label="전체" /></p>
					<p class="radio"><form:radiobutton path="kwrd7" value="Y" label="예" /></p>
					<p class="radio"><form:radiobutton path="kwrd7" value="N" label="아니요" /></p>
				</div>
			</div>
			<div class="fitem fb_40">
				<span class="label">답변일</span>
				<div class="input_w w_140">
					<form:input path="searchStartNttDe" class="inp_date" />
				</div>
				<div class="input_w">~</div>
				<div class="input_w w_140">
					<form:input path="searchEndNttDe" class="inp_date" />
				</div>
			</div>
            <div class="ml-auto">
                <button type="button" class="ubtn gray02 hover" onclick="fn_goSave();"><i class="icon icon_addOnly"></i>등록</button>
                <button type="button" class="ubtn normal primary hover" onclick="fn_searchList(1);"><i class="icon icon_search"></i>조회</button>
            </div>
        </div>
    </form:form>
</div>

<!-- sysErbbsList table 제목 hover -->
<style>
.data_table.sysErbbsList tbody tr td:nth-child(3){font-weight: 500;}
.data_table.sysErbbsList tbody tr:hover td:nth-child(3){ text-decoration: underline;}
</style>


<div class="d-flex align-items-center mt-4">
</div>
<div id="table01" class="fixed_scroll_head mt-2">
	<table class="table_t01 data_table sysErbbsList hover ta_left_3">
		<colgroup>
			<col width="6%">
            <col width="8%">
            <col width="">
            <col width="15%">
            <col width="15%">
            <col width="15%">
            <col width="11%">
		</colgroup>
	    <thead>
	    	<tr>
				<th scope="row">순번</th>
                <th scope="row">구분</th>
                <th scope="row">제목</th>
                <th scope="row">신고일</th>
                <th scope="row">작성부서/작성자</th>
                <th scope="row">답변일</th>
                <th scope="row">답변자</th>
			</tr>
	    </thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) != 0}">
					<c:forEach items="${list}" var="list" varStatus="status">
					<tr>
						<td>
							${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index}
						</td>
						<td>
							${list.nttSeCodeNm}
						</td>
						<td onclick="javascript:fn_goView('${list.nttNo}');" style="cursor: pointer;">
                            ${list.nttSj}
						</td>
						<td>
							<fmt:formatDate value="${list.registDt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
                            ${list.deptNm}/${list.userNm }
						</td>
						<td>
                            <fmt:formatDate value="${list.answerDt}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>${list.answerUserNm }</td>
					</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="7">데이터가 없습니다.</td>
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
//검색, 페이지 이동
function fn_searchList(pageNo) {
    showLoadingBar();
    $("#pageIndex").val(pageNo);
    $("#listForm").submit();
}

// 상세 이동
function fn_goView(nttNo) {
    $("#nttNo").val(nttNo);
    $("#listForm").attr("action","/${hmSiteCode}/board/sysErbbs/view.do");
    $("#listForm").submit();
}

// 등록 및 수정
function fn_goSave(nttNo) {
	$("#nttNo").val(nttNo);
	$("#listForm").attr("action","/${hmSiteCode}/board/sysErbbs/save.do");
	$("#listForm").submit();
}
</script>
