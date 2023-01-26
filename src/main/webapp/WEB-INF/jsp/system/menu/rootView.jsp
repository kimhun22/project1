<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

	<h3 class="subTitle01">상세메뉴정보 <span>ROOT 메뉴는 수정이 불가 합니다. </span> </h3>
	
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
						<span class="spanLabel">메뉴 코드</span>
					</th>
					<td>
						<input type="text" class="full" name="menuCode" value="${data.menuCode}" disabled="disabled"/>
					</td>
					<th scope="row">
						<span class="spanLabel">메뉴명</span>
					</th>
					<td>
						<input type="text" class="full" name="menuNm" value="${data.menuNm}" disabled="disabled"/>
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
        <!-- <input type="button" class="button buttonM bGray" onclick="fn_addPopupOpen('${data.upperMenuCode}', '${data.menuCode}');" value="하위 메뉴 등록"/> -->

		<button type="button" class="ubtn gray03 hover" onclick="fn_addPopupOpen('${data.upperMenuCode}', '${data.menuCode}');"><i class="icon icon_addOnly"></i>하위 메뉴 등록</button>
    </div>
