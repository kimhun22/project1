<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<form:form commandName="TnUserTbExVO" id="listForm" name="listForm" method="post" action="/${hmSiteCode}/system/userInfo/userList.do">
    <form:hidden path="pageIndex"/>
    <form:hidden path="loginId"/>
    <form:hidden path="rnum"/>
    <div class="board_search_type01">
	    <div class="frow">
			<div class="fitem fb_80">
				<span class="label">구분</span>
				<div class="input_w w_120">
					<form:select path="searchCondition" class="in_type searchSelect" onchange="fn_selectChange();">
						<form:option value="userId"  label="아이디"></form:option>
						<form:option value="userName" label="이름"></form:option>
						<form:option value="deptCode" label="부서"></form:option>
						<form:option value="authorCode" label="권한"></form:option>
					</form:select>
				</div>
				<div class="input_w w_240">
					<form:input path="searchKeyword" class="in_type width100"></form:input>
					<form:select path="deptCode" cssClass="in_type searchSelect" style="width: 250px; display:none;">
						<form:option value="">전체</form:option>
						<c:forEach items="${departmentList}" var="list">
							<form:option value="${list.partcode}">${list.partname}</form:option>
						</c:forEach>
					</form:select>
					<form:select path="authorCode" cssClass="in_type searchSelect" style="width: 250px; display:none;">
						<form:option value="">전체</form:option>
						<c:forEach items="${authorList}" var="list">
							<form:option value="${list.cmmnCode}">${list.cmmnCodeNm}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
	        <div class="ml-auto">
	            <button type="button" class="ubtn normal primary hover" onclick="fn_searchList(1);"><i class="icon icon_search"></i>조회</button>
	        </div>
	    </div>
    </div>
    <div id="table01" class="fixed_scroll_head mt-4">
        <table class="table_t01 data_table userInfo hover">
            <colgroup>
                <col width="6%">
                <col width="12%">
                <col width="11%">
                <col width="27%">
                <col width="11%">
                <col width="11%">
                <col width="11%">
                <col width="11%">
            </colgroup>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>부서</th>
                    <th>직위</th>
                    <th>근무상태</th>
                    <th>권한</th>
                    <th>수정일자</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${fn:length(selectAdminUserList) != 0}">
                        <c:forEach items="${selectAdminUserList}" var="list" varStatus="i">
                            <tr>
                                <td><c:out value='${list.rnum}'/></td>
                                <td id="<c:out value="${list.loginId}"/>" name="detailView" style="cursor: pointer;"><c:out value='${list.loginId}'/></td>
                                <td onclick="javascript:fn_adminUserDetail('${list.rnum}','${list.loginId}','${list.authorCode}');" style="cursor: pointer;"><c:out value='${list.userNm}'/></td>
                                <td><c:out value='${list.deptAllNm}'/></td>
                                <td><c:out value='${list.ofcpsNm}'/></td>
                                <td>
                                	<c:choose>
                                		<c:when test="${list.workSttusNm eq '재직'}">
                                			<span class="fc_blue"><c:out value='${list.workSttusNm}'/></span>
                                		</c:when>
                                		<c:otherwise><c:out value='${list.workSttusNm}'/></c:otherwise>
                                	</c:choose>
                                </td>
                                <td><c:out value='${list.authorNm}'/></td>
                                <td><fmt:formatDate value="${list.updateDt}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr name="<c:out value="${list.loginId}"/>" class="color001" style="display: none;">
                                <td></td>
                                <td style="text-align: left;">직무명 : </td>
                                <td colspan="2" style="text-align: left;"><c:out value="${list.dtyNm}"/></td>
                                <td style="text-align: left;">이메일주소 : </td>
                                <td colspan="3" style="text-align: left;"><c:out value="${list.emailAdres}"/></td>
                            </tr>
                            <tr name="<c:out value="${list.loginId}"/>" class="color001" style="display: none;">
                                <td></td>
                                <td style="text-align: left;">전화번호 : </td>
                                <td colspan="2" style="text-align: left;"><c:out value="${list.tlphonNo}"/></td>
                                <td style="text-align: left;">팩스번호 : </td>
                                <td colspan="3" style="text-align: left;"><c:out value="${list.faxNo}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <td colspan="8">데이터가 없습니다.</td>
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
	    <p class="board_count">[총 <strong class="fc_primary">${selectAdminUserListCnt }</strong>건]</p>
	</div>
</form:form>

<script>
// 상세 이동
fn_adminUserDetail = function(rnum, loginId) {
    $("#rnum").val(rnum);
    $("#loginId").val(loginId);
    $("#listForm").attr("action","/${hmSiteCode}/system/userInfo/selectAdminUserDetail.do");
    $("#listForm").submit();
};


//검색, 페이지 이동
fn_searchList = function(pageNo) {
    showLoadingBar();
    $("#pageIndex").val(pageNo);
    $("#listForm").submit();
};


//검색조건 변경
fn_selectChange = function(){
    var selectBox = $("#searchCondition option:selected").val();

    if(selectBox == "deptCode"){
        $("#searchKeyword").val("").hide();
        $("#deptCode").show();
        $("#authorCode").val("Y").hide();
    }else if(selectBox == "authorCode"){
        $("#searchKeyword").val("").hide();
        $("#deptCode").val("").hide();
        $("#authorCode").show();
    }else{
        $("#searchKeyword").show();
        $("#deptCode").val("").hide();
        $("#authorCode").val("").hide();
    }
}

// 상세 데이터 세팅
function fn_treeSetting(authorCode) {
	$.ajax({
        url : "/${hmSiteCode}<c:url value='/system/authorMenu/getListAjax.do'/>",
        data : {
        	authorCode : authorCode
        },
        success : function(data) {

        	console.log(data);

        	// zTree Node 세팅
        	var zNodeList = [];
        	for  ( var i in data.menuList )  {
       			var zNode = {};

       			zNode.id = data.menuList[i].menuCode;
       			zNode.pId = data.menuList[i].upperMenuCode;
       			zNode.name = data.menuList[i].menuNm;
           		zNode.open = true;

           		for  ( var j in data.authorMenuList )  {
					if  ( data.menuList[i].menuCode == data.authorMenuList[j].menuCode )
						zNode.checked = true;
				}

           		zNodeList.push(zNode);
       		}

        	// zTree 세팅
    		var treeObj = $.fn.zTree.init($("#treeList"), setting, zNodeList);
    		// zTree 모두 펼치기
    		treeObj.expandAll(true);
        }
    });
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

