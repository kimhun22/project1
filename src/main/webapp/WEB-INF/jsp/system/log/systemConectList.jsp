<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<%
 /**
  * @Class Name  : systemConectList.jsp
  * @Description : 시스템 관리 - 로그조회 - 시스템 접속 이력 조회
  *
  */
%>

<div class="board_search_type01">
	<form:form commandName="thSysUseHistTbExVO" id="listForm" name="listForm" method="post" action="/${hmSiteCode}/system/log/systemConectList.do">
		<form:hidden path="pageIndex"/>
		<form:hidden path="requstSn"/>

		<div class="frow">
			<div class="fitem fb_40">
				<span class="label">접속자 ID</span>
				<div class="input_w w_full">
					<form:input path="searchRqester" class="in_type" onkeypress="if(event.keyCode == 13) fn_searchList(1);"/>
				</div>
			</div>
			<div class="fitem fb_40">
				<span class="label">접속일</span>
				<div class="input_w w_140">
					<form:input path="searchRequstStartDt" class="inp_date" />
				</div>
				<div class="input_w">~</div>
				<div class="input_w w_140">
					<form:input path="searchRequstEndDt" class="inp_date" />
				</div>
			</div>
			<div class="ml-auto">
                <button type="button" class="ubtn normal primary hover ml-2" onclick="fn_searchList(1);"><i class="icon icon_search"></i>조회</button>
            </div>
	    </div>

	</form:form>
</div>

<div id="table01" class="fixed_scroll_head mt-4">
	<table class="table_t01 data_table hover">
		<colgroup>
			<col width="6%">
            <col width="20%">
            <col width="">
            <col width="11%">
            <col width="11%">
            <col width="11%">
		</colgroup>
		<thead>
	    	<tr>
				<th scope="row">순번</th>
                <th scope="row">접속일시</th>
                <th scope="row">접속 IP</th>
                <th scope="row">접속자 ID</th>
                <th scope="row">접속자 이름</th>
                <th scope="row">접속자 부서</th>
			</tr>
	    </thead>
	    <tbody>
	    	<c:choose>
	    		<c:when test="${fn:length(systemConectList) != 0}">
	    			<c:forEach items="${systemConectList}" var="list" varStatus="status">
		    			<tr>
		    				<td>
								${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index}
							</td>
							<td>
								<fmt:formatDate value="${list.requstDt}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${list.requstIp}
							</td>
							<td>
								${list.rqester}
							</td>
							<td>
								${list.userNm}
							</td>
							<td>
								${list.deptNm}
							</td>
		    			</tr>
		    		</c:forEach>
	    		</c:when>
	    		<c:otherwise>
					<td colspan="6">데이터가 없습니다.</td>
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
fn_searchList = function(pageNo) {
    //showLoadingBar();
    $("#pageIndex").val(pageNo);
    $("#listForm").submit();
};

$(document).ready(function() {

	fn_setDate();
});

fn_setDate = function(){
	if(document.getElementById('searchRequstStartDt').value =="" || document.getElementById('searchRequstStartDt').value == "&nbsp;" || document.getElementById('searchRequstStartDt').value == "<p>&nbsp;</p>" )  {
		var now = new Date();
		var lastMonth = new Date(now.setMonth(now.getMonth() - 1));
		document.getElementById('searchRequstStartDt').value= lastMonth.toISOString().slice(0, 10);
		document.getElementById('searchRequstEndDt').value= new Date().toISOString().slice(0, 10);
	}
};
</script>


