<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>


<script>
	// 팝업 닫기
	function popupClose() {
		$("#DIV_ADD").dialog("close");
	}

	// 입력값 확인
	function fn_poupValidCheck() {
		// 코드
		if  ( !validateBaseElement("popupCmmnCode", true, 50) )  return false;
		// 코드명
		if  ( !validateBaseElement("popupCmmnCodeNm", true, 100) )  return false;
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
	            url : "/${hmSiteCode}<c:url value='/system/code/addAjax.do'/>",
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
								<span class="spanLabel">상위<br>코드</span>
							</th>
							<td>
								<input type="text" class="full" name="parntsCmmnCode" id="popupParntsCmmnCode" value="${parntsCmmnCodeData.cmmnCode}" title="상위 코드는 변경이 불가합니다." readonly="readonly" />
								<input type="hidden" name="upperParntsCmmnCode" value="${parntsCmmnCodeData.parntsCmmnCode}">
							</td>
							<th scope="row">
								<span class="spanLabel">상위<br>코드명</span>
							</th>
							<td>
								<input type="text" class="full" name="parntsCmmnCodeNm" id="popupParntsCmmnCodeNm" value="${parntsCmmnCodeData.cmmnCodeNm}" title="상위 코드명은 변경이 불가합니다." readonly="readonly" />
								<input type="hidden" name="upperCmmnCode" value="${parntsCmmnCodeData.cmmnCode}">
							</td>
						</tr>
						<tr>
							<th scope="row">
								<span class="spanLabel">코드</span>
							</th>
							<td>
								<input type="text" class="full" name="cmmnCode" id="popupCmmnCode" title="코드" />
							</td>
							<th scope="row">
								<span class="spanLabel required">코드명</span>
							</th>
							<td>
								<input type="text" class="full" name="cmmnCodeNm" id="popupCmmnCodeNm" title="코드명"/>
							</td>
						</tr>
						<tr>
							<th scope="row">
								<span class="spanLabel required">순서</span>
							</th>
							<td>
								<input type="text" class="full" name="sortOrdr" id="popupSortOrdr" value="${nextCmmnCodeData.sortOrdr}" title="순서" numberOnly />
							</td>
							<th scope="row">
								<span class="spanLabel required">사용여부</span>
							</th>
							<td class="radios">
								<input type="radio" name="useAt" id="popupUseAt_1" value="1" title="사용여부" checked="checked"/><label for="popupUseAt_1"> 사용</label><span style="padding-right: 30px;"></span>
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
			<input type="button" class="ubtn primary hover" onclick="fn_add();" value="저장"/>
			<input type="button" class="ubtn gray02 hover" onclick="popupClose();" value="닫기"/>
	    </div>
	</div>
