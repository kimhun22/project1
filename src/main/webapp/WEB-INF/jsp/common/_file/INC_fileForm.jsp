<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<c:choose>
	<c:when test="${!empty fileList}">
		<c:forEach items="${fileList}" var="file" varStatus="idx">
			<div class="fileItem">
                <input type="hidden" class="clsDelYn" value="N"/>
                <input type="hidden" class="clsDetailSeq" value="<c:out value='${file.fileSn}'/>"/>

				<input type="file" style="height: 30px;" id="<c:out value='${inputNm}'/>_${idx.count}" name="<c:out value='${inputNm}'/>" title="파일_${idx.count}"/>
				<span class="clsFileNm"><a onclick="gfn_fileDown('<c:out value="${file.fileNo}"/>','<c:out value="${file.fileSn}"/>','<c:out value="${file.hmSiteCode}"/>');" href="javascript:void(0);" >${file.orginlFileNm}</a></span>

				<label style="float: right;">
					<c:if test="${maxFileCount > 1 && idx.first}">
						<button type="button" class="button buttonS bGray" onclick="gfn_addFile($(this), '${maxFileCount}');">추가</button>
					</c:if>
					<button type="button" class="button buttonS bRed" onclick="gfn_delFile($(this));">삭제</button>
				</label>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="fileItem">
			<input type="hidden" class="clsDelYn" value="Y"/>
			<input type="file" style="width: 50%; height: 30px;" id="<c:out value='${inputNm}'/>_1" name="<c:out value='${inputNm}'/>" title="파일_1"/>

			<label style="float: right;">
				<c:if test="${maxFileCount > 1}">
					<button type="button" class="button buttonS bGray" onclick="gfn_addFile($(this), '${maxFileCount}');">추가</button>
				</c:if>
				<button type="button" class="button buttonS bRed" onclick="gfn_delFile($(this));">삭제</button>
			</label>
		</div>
	</c:otherwise>
</c:choose>
