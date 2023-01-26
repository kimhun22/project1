<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<script>
	// 팝업 닫기
	function popupClose() {
		$("#DIV_ADD").dialog("close");
	}

	// 입력값 확인
	function fn_poupValidCheck() {
		// 메뉴명
		if  ( !validateBaseElement("popupMenuNm", true, 100) )  return false;
		// 메뉴 URL
		if  ( !validateBaseElement("popupMenuUrl", false, 500) )  return false;
		// 순서
		if  ( !validateBaseElement("popupSortOrdr", true) )  return false;
		// 사용여부
		if  ( !validateBaseElement("popupUseAt", true) )  return false;
		// 비고
		if  ( !validateBaseElement("popupRm", false, 4000) )  return false;

	    return true;
	}

	// 등록
	function fn_add() {
		// 입력값 확인
	    if ( !fn_poupValidCheck() )  return false;

	    if  ( confirm("저장 하시겠습니까?") )  {
	        $("#addForm").ajaxSubmit({
	            url : "/${hmSiteCode}<c:url value='/system/menu/addAjax.do'/>",
	            success : function(data) {
	                if ( data.result )  {
	                	alert(data.message);

	                	popupClose();
	                	fn_treeNodeSetting($.fn.zTree.getZTreeObj("treeList").getSelectedNodes()[0]);
	                }  else  {
	                    alert(data.message);
	                }
	            }
	        });
	    }
	}
</script>

	<div class="marginT10">
	    <form id="addForm">
			<div class="treeTablePop">
				<table class="table_t01 data_table td_left">
					<colgroup>
						<col width="15%"/>
						<col width="35%"/>
						<col width="15%"/>
						<col width="35%"/>
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">
								<span class="spanLabel">상위 메뉴 코드</span>
							</th>
							<td>
								<input type="text" class="full" name="upperMenuCode" id="popupUpperMenuCode" value="${upperMenuData.menuCode}" title="상위 메뉴 코드" readonly="readonly" />
							</td>
							<th scope="row">
								<span class="spanLabel">상위 메뉴명</span>
							</th>
							<td>
								<input type="text" class="full" name="upperMenuNm" id="popupUpperMenuNm" value="${upperMenuData.menuNm}" title="상위 메뉴명" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th scope="row">
								<span class="spanLabel">메뉴 코드</span>
							</th>
							<td>
								<input type="text" class="full" name="menuCode" id="popupMenuCode" value="${nextMenuData.menuCode}" title="메뉴 코드" readonly="readonly" />
							</td>
							<th scope="row">
								<span class="spanLabel required">메뉴명</span>
							</th>
							<td>
								<input type="text" class="full" name="menuNm" id="popupMenuNm" title="메뉴명"/>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<span class="spanLabel required">메뉴 URL</span>
							</th>
							<td colspan="3">
								<input type="text" class="full" name="menuUrl" id="popupMenuUrl" title="메뉴 URL"/>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<span class="spanLabel required">순서</span>
							</th>
							<td>
								<input type="text" class="full" name="sortOrdr" id="popupSortOrdr" value="${nextMenuData.sortOrdr}" title="순서" numberOnly />
							</td>
							<th scope="row">
								<span class="spanLabel required">사용여부</span>
							</th>
							<td class="radios">
								<input type="radio" name="useAt" id="popupUseAt_1" value="1" checked="checked" title="사용여부" /><label for="popupUseAt_1"> 사용</label><span style="padding-right: 30px;"></span>
								<input type="radio" name="useAt" id="popupUseAt_2" value="0" title="사용여부" /><label for="popupUseAt_2"> 사용안함</label>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<span class="spanLabel required">비고</span>
							</th>
							<td colspan="3">
								<textarea class="full" name="rm" id="popupRm" rows="5" title="비고"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>

		<div class="btn_area a_right">
			<input type="button" class="ubtn primary large hover" onclick="fn_add();" value="저장"/>
			<input type="button" class="ubtn gray02 large hover" onclick="popupClose();" value="닫기"/>
	    </div>
	</div>
