<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>


	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<title><c:if test="${sessionScope.loginVO.runMode != 'PROD'}">[<c:out value="${sessionScope.loginVO.runMode}" />] </c:if>스마트협의시스템 </title>

	<!-- 에디터 -->
	<script  src="<c:url value='/smart/editor/HuskyEZCreator.js'/>" charset="utf-8"></script>
	<script>
	/* 에디터와 jQuery 객체의 충돌을 방지 하기 위한 초기화 */
	var oEditors = [];
		/* 에디터의 구현부는 해당 writeKnowhow.jsp 에 정의 되어 있음 */
		jQuery(document).ready(function(){
				nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: "nttCn1",
				sSkinURI: "/smart/editor/SmartEditor2Skin.html",
				getContextPath : "/smart/editor",
				htParams : {
					bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					getContextPath : "/smart/editor"
				}, //boolean
				fCreator: "createSEditor2"
			});
		});

		function fn_save() {

			// 제목
			if  ( !validateBaseElement("nttSj", true, 400) )  return;
			// 내용
			oEditors.getById["nttCn1"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터 내용 textarea에 적용
			if  ( $("#nttCn1").val() == "" || $("#nttCn1").val() == "&nbsp;" || $("#nttCn1").val() == "<p>&nbsp;</p>" )  {
				alert($("#nttCn1").attr("title") + " 을/를 입력해주세요.");
				oEditors.getById["nttCn1"].exec("FOCUS");
				return;
			}
			  var fileList = fn_getSysErbbsFileList();
		        if(fileList.length > 0) {
		        	var b = new DataTransfer();
			        for(i=0; i<fileList.length; i++) {
			        	b.items.add(fileList[i]);
			        }

			        document.querySelector('#sysErbbsFile-input').files = b.files;
		        }

		        if(atchFileDeList.length > 0) {
					$('#atchFileDelSn').val(atchFileDeList.join(","));
		        }
			if  ( confirm("저장 하시겠습니까?") )  {
				$("#saveForm").submit();
			}
		};

		// 취소
		fn_cancle = function() {
			location.href = "/${hmSiteCode}/board/notice/noticeList.do";
		};
	</script>

	<section class="content">
		<article class="block">
			<div class="table_list2">
				<form action="/${hmSiteCode}/board/notice/noticeSaveProc.do" id="saveForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="nttNo" id="nttNo" value="${data.nttNo}" />

					<table class="table_t01 data_table">
						<colgroup>
							<col width="10%"/>
							<col width="40%"/>
							<col width="10%"/>
							<col width=""/>
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
								<input type="text" class="w_full" name="nttSj" id="nttSj" value="${data.nttSj}" title="제목" />
							</td>
							<th>작성자</th>
							<td>
								${not empty data.userNm ? data.userNm : sessionScope.loginInfo.userNm}
							</td>
						</tr>
						<tr>
							<th>구분</th>
							<td>
               					<input type="radio" name="importYn" id="importY" value="Y" <c:if test="${data.importYn eq 'Y'|| data.importYn eq null}">checked="checked"</c:if> /><label for="importY"> 중요 게시물</label><span style="padding-right: 30px;"></span>
								<input type="radio" name="importYn" id="importN" value="N" <c:if test="${data.importYn eq 'N'}">checked="checked"</c:if> /><label for="importN"> 일반 게시물</label>
							</td>
							<th>표출기간</th>
							<td>
								 <fmt:parseDate value="${data.showStartDt}" var="showStartDt" pattern="yyyyMMdd"/>
								 <fmt:parseDate value="${data.showEndDt}" var="showEndDt" pattern="yyyyMMdd"/>
								 <input type="text" id="showStartDt" name="showStartDt" class="inp_date w_140" value= "<fmt:formatDate value="${showStartDt}" pattern="yyyy-MM-dd"/>" title="시작일">
								 &nbsp;~&nbsp;
								 <input type="text" id="showEndDt" name="showEndDt" class="inp_date w_140" value= "<fmt:formatDate value="${showEndDt}" pattern="yyyy-MM-dd"/>" title="종료일">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3">
								<textarea class="w_full" name="nttCn1" id="nttCn1" style="resize: none;" title="내용">${data.nttCn1}</textarea>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
						<td colspan="3" class="t_left">
                            <div class="file_upload">
                                <div class="title">파일 (네모 박스안에 파일을 끌어다 놓으세요.) <button type="button" class="ubtn small gray03 hover" id="select-file-upload"><i class="icon icon_search"></i><span>파일 선택</span></button></div>
                                <div class="inbox">
                                    <div id="sysErbbsFileBoard" class="fileBoard drag_area" style="text-align:left;min-height:100px;">
                                        <c:forEach items="${atchFile}" var="codeData" varStatus="status">
                                            <div class="sysErbbsFileList" data-fileNo="${codeData.fileNo}" data-fileSn="${codeData.fileSn}">
                                                <span class="fileName">${codeData.orginlFileNm}</span>
                                                <span class='fileSize'><fmt:formatNumber type="number" maxFractionDigits="3" value="${codeData.fileSize / 1000000}" /> MB</span>
                                                <span class='clear'><i id="removeFile" onclick="fn_sysErbbRemoveFile(this);" class="icon icon_del" style="cursor:pointer;"></i></span>
                                            </div>
                                        </c:forEach>
                                        <input name="atchFile" type="file" id="sysErbbsFile-input" multiple style="display:none;" />
                                        <input type="file" id="select-sysErbbsFile-input" multiple style="display:none;" />
                                    </div>
                                </div>
                            </div>
						</td>
						<tr>
					</table>
				</form>
			</div>

			<div class="btn_area">
		    	<div class="f_right">
					<button type="button" id="btn-save" class="ubtn primary hover" onclick="fn_save();"><i class="icon icon_done"></i>저장</button>
					<button type="button" id="btn-cancle" class="ubtn gray02 hover" onclick="fn_cancle();"><i class="icon icon_menu"></i>목록</button>
		    	</div>
		    </div>
		</article>
	</section>
<%-- 첨부파일 --%>
<jsp:include page="../sysErbbs/sysErbbsAtchFile.jsp" flush="true">
    <jsp:param name="atchFileNo" value="${data.atchFileNo}" />
    <jsp:param name="allowExt" value="${allowFileType}" />
</jsp:include>