<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<section class="content">
    <article class="block">

        <form:form commandName="TnUserTbExVO" id="listForm" name="listForm" method="post" action="/${hmSiteCode}/system/deptInfo/deptList.do">
            <form:hidden path="pageIndex"/>
            <form:hidden path="deptCode"/>
            <form:hidden path="rnum"/>

		    <div class="board_search_type01">

			    <div class="frow">
					<div class="fitem fb_80">
						<span class="label">구분</span>
						<div class="input_w w_120">
							<form:select path="searchCondition" class="in_type searchSelect" onchange="fn_selectChange();">
								<form:option value="deptAllNm" label="부서명"></form:option>
								<form:option value="upperDeptCode" label="상위부서"></form:option>
							</form:select>
						</div>
						<div class="input_w w_240">
							<form:input path="searchKeyword" onkeydown="if(event.keyCode==13){javascript:fn_searchList(1);}" class="in_type width100"></form:input>
							<form:select path="upperDeptCode" cssClass="in_type searchSelect" style="width: 250px; display:none;">
								<form:option value="">전체</form:option>
								<c:forEach items="${departmentList}" var="list">
									<form:option value="${list.partcode}">${list.partname}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
			        <div class="ml-auto">
			        	<button type="button" class="ubtn normal primary hover" onclick="javascript:fn_searchList(1);"><i class="icon icon_search"></i>조회</button>
			        </div>
			    </div>
		    </div>

            <div id="table01" class="fixed_scroll_head mt-4">
                <table class="table_t01 data_table hover">
                    <colgroup>
                        <col width="20%">
                        <col width="25%">
                        <col width="15%">
                        <col width="15%">
                    </colgroup>
                    <thead>
                        <tr>
                            <th>부서코드</th>
                            <th>상위 부서명</th>
                            <th>부서명</th>
                            <th>등록일자</th>
                            <th>수정일자</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${fn:length(selectDeptList) != 0}">
                                <c:forEach items="${selectDeptList}" var="list" varStatus="i">
                                    <tr>
                                        <td id="<c:out value="${list.deptCode}"/>" name="detailView" style="cursor: pointer;"><c:out value='${list.deptCode}'/></td>
                                        <td onclick="javascript:fn_deptDetail('${list.rnum}','${list.deptCode}');" style="padding-left:10px; text-align:left;cursor: pointer;"><c:out value='${list.deptAllNm}'/></td>
                                        <td style="padding-left:10px; text-align:left;"><c:out value='${list.deptNm}'/></td>
                                        <td><fmt:formatDate value="${list.registDt}" pattern="yyyy-MM-dd"/></td>
                                        <td><fmt:formatDate value="${list.updateDt}" pattern="yyyy-MM-dd"/></td>
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
			    <p class="board_count">[총 <strong class="fc_primary">${selectDeptListCnt }</strong>건]</p>
			</div>
        </form:form>
    </article>
</section>
<script>
// 상세 이동
function fn_deptDetail(rnum, deptCode) {
    $("#rnum").val(rnum);
    $("#deptCode").val(deptCode);
    $("#listForm").attr("action","/${hmSiteCode}/system/deptInfo/selectDeptDetail.do");
    $("#listForm").submit();
};

//검색, 페이지 이동
function fn_searchList(pageNo) {
    showLoadingBar();
    $("#pageIndex").val(pageNo);
    $("#listForm").submit();
};

//검색조건 변경
function fn_selectChange(){
    var selectBox = $("#searchCondition option:selected").val();

    if(selectBox == "upperDeptCode"){
        $("#searchKeyword").val("").hide();
        $("#upperDeptCode").show();
    }else{
        $("#searchKeyword").show();
        $("#upperDeptCode").val("").hide();
    }
}

$(function() {

    fn_selectChange();

    $('[name=detailView]').click(function(){
        var trName = $(this).attr("id");
        if($("[name="+trName+"]").css("display") == "none"){
            $(".color001").hide();
            $("[name="+trName+"]").show();
        } else {
            $("[name="+trName+"]").hide();
        }
    });

});

</script>
