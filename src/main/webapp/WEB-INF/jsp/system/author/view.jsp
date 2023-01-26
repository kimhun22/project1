<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<script>
	// 입력값 확인
	function fn_validCheck() {
		// 코드명
		if  ( !validateBaseElement("cmmnCodeNm", true, 100) )  return false;
		// 순서
		if  ( !validateBaseElement("sortOrdr", true) )  return false;
		// 사용여부
		if  ( !validateBaseElement("useAt", true) )  return false;
		// 비고
		if  ( !validateBaseElement("rm", false, 4000) )  return false;

		return true;
	}

	// 수정
	function fn_edit() {
		// 입력값 확인
		if  ( !fn_validCheck() )  return false;

		if  ( confirm("저장 하시겠습니까?") )  {
			$("#editForm").ajaxSubmit({
				url : "/${hmSiteCode}<c:url value='/system/code/editAjax.do'/>",
				success: function(data) {
					if ( data.result )  {
	                    alert(data.message);

	                    fn_treeNodeSetting($.fn.zTree.getZTreeObj("treeList").getSelectedNodes()[0]);
	                }  else  {
	                	alert(data.message);
	                }
				}
			});
		}
	}

	// 삭제
	function fn_delete() {
		if  ( confirm("삭제 하시겠습니까?") )  {
			$.ajax({
	            url : "/${hmSiteCode}<c:url value='/system/code/deleteAjax.do'/>",
	            data : {
	            	parntsCmmnCode : '${data.parntsCmmnCode}'
	            	, cmmnCode : '${data.cmmnCode}'
	            },
	            success : function(data) {
	                if  ( data.result )  {
	                	alert(data.message);

	                	fn_treeNodeSetting($.fn.zTree.getZTreeObj("treeList").getSelectedNodes()[0].getParentNode());
	                } else {
	                	alert(data.message);
	                }
	            }
	        });
		}
	}
</script>

	<h3 class="subTitle01">상세코드정보</h3>

	<form id="editForm">
		<div class="">
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
							<input type="text" class="full" name="parntsCmmnCode" id="parntsCmmnCode" value="${data.parntsCmmnCode}" title="상위 코드" readonly="readonly" />
						</td>
						<th scope="row">
							<span class="spanLabel">상위<br>코드명</span>
						</th>
						<td>
							<input type="text" class="full" name="parntsCmmnCodeNm" id="parntsCmmnCodeNm" value="${data.parntsCmmnCodeNm}" title="상위 코드명" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th scope="row">
							<span class="spanLabel">코드</span>
						</th>
						<td>
							<input type="text" class="full" name="cmmnCode" id="cmmnCode" value="${data.cmmnCode}" title="코드" readonly="readonly" />
						</td>
						<th scope="row">
							<span class="spanLabel required">코드명</span>
						</th>
						<td>
							<input type="text" class="full" name="cmmnCodeNm" id="cmmnCodeNm" value="${data.cmmnCodeNm}" title="코드명"/>
						</td>
					</tr>
					<tr>
						<th scope="row">
							<span class="spanLabel required">순서</span>
						</th>
						<td>
							<input type="text" class="full" name="sortOrdr" id="sortOrdr" value="${data.sortOrdr}" title="순서" numberOnly="true"  />
						</td>
						<th scope="row">
							<span class="spanLabel required">사용여부</span>
						</th>
						<td class="radios">
							<input type="radio" name="useAt" id="useAt_1" value="1" <c:if test="${data.parntsUseAt eq '0'}">disabled="disabled"</c:if> <c:if test="${data.useAt eq '1'}">checked="checked"</c:if> title="사용여부" /><label for="useY"> 사용</label><span style="padding-right: 30px;"></span>
							<input type="radio" name="useAt" id="useAt_2" value="0" <c:if test="${data.useAt eq '0'}">checked="checked"</c:if> title="사용여부" /><label for="useN"> 사용안함</label>
						</td>
					</tr>
					<tr>
						<th scope="row">
							<span class="spanLabel required">비고</span>
						</th>
						<td colspan="3">
							<textarea class="full" name="rm" id="rm" rows="5" title="비고" >${data.rm}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>

	<div class="btn_area a_right">
        <!-- <input type="button" class="button buttonM bOrange" onclick="fn_edit();" value="저장"/> -->
		<button type="button" class="ubtn primary hover" onclick="fn_edit();"><i class="icon icon_done"></i>저장</button>

        <c:if test="${data.childCnt < 1}">
        	<!-- <input type="button" class="button buttonM bRed" onclick="fn_delete();" value="삭제"/> -->
			<button type="button" class="ubtn red hover" onclick="fn_delete();"><i class="icon icon_delete"></i>삭제</button>
        </c:if>
    </div>
