<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>


	<h3 class="subTitle01">상세코드정보 <span>ROOT 코드는 수정이 불가 합니다.<span> </h3>

	<div class="codeTable">
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
						<span class="spanLabel">코드</span>
					</th>
					<td>
						<input type="text" class="full" name="cmmnCode" value="${data.cmmnCode}" disabled="disabled"/>
					</td>
					<th scope="row">
						<span class="spanLabel">코드명</span>
					</th>
					<td>
						<input type="text" class="full" name="cmmnCodeNm" value="${data.cmmnCodeNm}" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<span class="spanLabel">순서</span>
					</th>
					<td>
						<input type="text" class="full" name="sortOrdr" value="${data.sortOrdr}" disabled="disabled"/>
					</td>
					<th scope="row">
						<span class="spanLabel">사용여부</span>
					</th>
					<td class="radios">
						<input type="radio" name="useAt" id="useY" <c:if test="${data.useAt eq '1'}">checked="checked"</c:if> disabled="disabled" /><label for="useY"> 사용</label><span style="padding-right: 30px;"></span>
						<input type="radio" name="useAt" id="useN" <c:if test="${data.useAt eq '0'}">checked="checked"</c:if> disabled="disabled" /><label for="useN"> 사용안함</label>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="btn_area a_right">
		<button type="button" class="ubtn gray03 hover" onclick="fn_addPopupOpen('${data.parntsCmmnCode}', '${data.cmmnCode}');" value="하위 코드 등록"><i class="icon icon_addOnly"></i>하위 메뉴 등록</button>
    </div>
