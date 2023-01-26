<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>




<script>
	// 등록 및 수정
	function fn_goSave() {
		$("#viewForm").attr("action","/${hmSiteCode}/board/sysErbbs/save.do");
		$("#viewForm").submit();
	}

	// 목록
	function fn_goList() {
		location.href = "/${hmSiteCode}/board/sysErbbs/sysErbbsList.do";
	}

	// 삭제
	function fn_delete() {
		if  ( confirm("삭제 하시겠습니까?") )  {
			$("#viewForm").attr("action","/${hmSiteCode}/board/sysErbbs/deleteProc.do");
			$("#viewForm").submit();
		}
	}
</script>

<section class="content">
	<article class="block">
		<div class="table_list2">
			<form id="viewForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="nttNo" id="nttNo" value="${data.nttNo}" />
				<h3 class="stitle01 mt-2">시스템오류신고 내용</h3>
				<table class="table_t01 data_table td_left hovertd">
					<colgroup>
						<col width="10%"/>
						<col width="40%"/>
						<col width="10%"/>
						<col width=""/>
					</colgroup>
					<tr>
						<th>구분</th>
						<td>
							${data.nttSeCodeNm}
						</td>
						<th>신고일자</th>
						<td>
							<fmt:formatDate value="${data.registDt}" pattern="yyyy-MM-dd HH:mm:ss" /> /${data.userNm}
						</td>
					</tr>
					<tr>
						<th rowspan="1">제목</th>
						<td colspan="3">
							${data.nttSj}
						</td>
					</tr>
					<tr>
						<th>오류내용</th>
						<td colspan="3">
							${hmFunction:unescapeXml(data.nttCn1)}
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3" class="t_left">
							<div class="fileForm">
								<c:import url="/comm/file/form.do">
									<c:param name="flag" value="view"/>
									<c:param name="fileNo" value="${data.atchFileNo}"/>
									<c:param name="hmSiteCode" value="${hmSiteCode}"/>
								</c:import>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${not empty data.nttCn2}">
				<h3 class="stitle01 mt-4">답변 내용</h3>
				<table class="table_t01 data_table td_left">
					<colgroup>
						<col width="10%"/>
						<col width="40%"/>
						<col width="10%"/>
						<col width=""/>
					</colgroup>
					<tr>
						<th>답변</th>
						<td colspan="3">
							${hmFunction:unescapeXml(data.nttCn2)}
						</td>
					</tr>
					<tr>
						<th>답변자</th>
						<td>
							${data.answerUserNm}
						</td>
						<th>답변일</th>
						<td>
							<fmt:formatDate value="${data.answerDt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
				</table>
				</c:if>
			</form>
		</div>

		<div class="btn_area">
	    	<div class="f_right">
				<button type="button" id="btn-save" class="ubtn red" onclick="fn_delete();"><i class="icon icon_delete"></i>삭제</button>
				<button type="button" id="btn-save" class="ubtn primary hover" onclick="fn_goSave();"><i class="icon icon_edit"></i>수정</button>
				<button type="button" id="btn-cancle" class="ubtn gray02 hover" onclick="fn_goList();"><i class="icon icon_menu"></i>목록</button>
	    	</div>
	    </div>
	</article>
</section>