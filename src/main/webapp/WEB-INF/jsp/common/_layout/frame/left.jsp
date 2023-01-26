<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<c:set var="nowURL" value="${requestScope['javax.servlet.forward.request_uri'] }"/>
<c:set var="nowURL" value="${fn:replace(nowURL,contextPath,'/')}"/>
<c:set var="nowSubURL" value="${fn:split(nowURL,'/')}"/>
<c:choose>
	<c:when test="${fn:length(nowSubURL) == 1}">
		<c:set var="nowSubURL" value="/${nowSubURL[fn:length(nowSubURL)-1]}"/>
	</c:when>
	<c:otherwise>
		<c:set var="nowSubURL" value="${nowSubURL[fn:length(nowSubURL)-1]}"/>
		<c:set var="nowSubURL" value="${fn:replace(nowURL,nowSubURL,'')}"/>
	</c:otherwise>
</c:choose>

<script>
</script>

<style>

#sidenav {overflow-y: auto;}

@media screen and (-webkit-min-device-pixel-ratio:0){
	#sidenav {overflow-y: scroll;}
	#sidenav li{cursor:pointer;}
	/* 스크롤바 설정*/
	#sidenav::-webkit-scrollbar{
	    width: 0px;
	}

	/* 스크롤바 막대 설정*/
	#sidenav::-webkit-scrollbar-thumb{
	    height: 17%;
	    background-color: rgba(33,133,133,1);
	    border-radius: 0;
	}

	/* 스크롤바 뒷 배경 설정*/
	#sidenav::-webkit-scrollbar-track{
	    background-color: rgba(33,133,133,0.33);
	}

	/* 스크롤바 설정*/
	#container::-webkit-scrollbar{
	    width: 0px;
	}

	/* 스크롤바 막대 설정*/
	#container::-webkit-scrollbar-thumb{
	    height: 17%;
	    background-color: rgba(33,133,133,1);
	    border-radius: 10px;
	}

	/* 스크롤바 뒷 배경 설정*/
	#container::-webkit-scrollbar-track{
	    background-color: rgba(33,133,133,0.33);
	}
}
.frm{height:100%;}
/* .frm iframe{overflow-x:hidden; overflow:auto; width:100%; min-height:500px; height: 80vh;} */
.frm .ifrm {width:100%;height: inherit;position: absolute;top: 0px;left: 0px;}
.depth4 {display:none;}

</style>
<script>
$(function (){

	//메뉴 클릭
	$('#sidenav li').click(function(e){

		var $this = $(this);
		var menuText = $this.children('a').text();
		var menuCode = $this.data('code');
		var menuUrl = $this.data('url');

		e.stopPropagation();
		e.preventDefault();

		$this.siblings().removeClass('on');
		if($this.parent().hasClass('depth1')){//1depth면
			$this.find('li').removeClass('on');
			$this.toggleClass('on');
		}else{
			$this.addClass('on');
		}

		//하위 a가 새창인 경우
		if($this.children('a').attr('target') === '_blank') {
            window.open($this.children('a').attr('href'), "_blank");
            return;
		}

		//하위 depth이 있으면 패스
		if($this.find('ul.depth3').length > 0) {
			return;
		}
		//url값이 존재하면 탭 생성,컨텐츠 호출
		if(menuUrl != "${pageContext.request.contextPath}" || $('#sub-'+menuCode).length>0 ){
			$('.frm').hide();

			//타이틀 생성
			titleText(menuCode);

			//tabMenu 체크
			if($('#sub-'+menuCode).length == 0 && $('#subMenu-'+menuCode).length == 0)
				tabMenuCheck(menuCode, menuText, menuUrl);

		}//if(menuUrl)

		//서브메뉴 체크
		//depth3 submenu
		if($('#sub-'+menuCode+' li').length == 0 && $('#subMenu-'+menuCode).length != 0) {
			subMenuCheck($('#subMenu-'+menuCode).parent().data('code'));
			$('#subMenu-'+menuCode).click();//해당 서브메뉴 클릭
		} else {
			subMenuCheck(menuCode);
			$('#sub-'+menuCode+' li').first().click();//첫번째 서브메뉴 클릭
		}
		//return false; // 부모 영역으로 이벤트 전파 방지
	});
});



</script>
 <div id="sidenav">
	<div class="logo_box" >
		<h1 class="logo goMain"><a href="#"><span class="sr_only">스마트 인허가</span></a></h1>
	</div>
   	<!-- 메뉴 data 반복 -->
    <c:set var="openDepth" value="1" />
    <c:set var="menuIcon" value="" />
     <ul class="depth1">
     <c:forEach items="${systemMenuList}" var="data" varStatus="status">
		<c:if test="${fn:indexOf('001,002,003,004,005,006,007,008,009', data.menuCode) != -1}">
			<c:set var="menuIcon" value="s${fn:substring(data.menuCode,1,3)}" />
			<c:choose>
					<c:when test="${data.childCnt eq 0}">
						<li class="${menuIcon}" id="menu-${data.menuCode}" data-code="${data.menuCode}" data-url="${pageContext.request.contextPath}${data.menuUrl}" data-job-url=""><a href="<c:url value='${data.menuUrl}'/>">${data.menuNm}</a></li>
					</c:when>
					<c:otherwise>
						<li class="${menuIcon}" id="menu-${data.menuCode}" data-code="${data.menuCode}" data-url="${pageContext.request.contextPath}${data.menuUrl}" data-job-url=""><a href="javascript:void(0);">${data.menuNm}</a>
						<ul class="depth${data.depth}"" data-parent="002">
							 <c:forEach items="${systemMenuList}" var="data2" varStatus="status">
							 	<c:choose>
							 		<c:when test="${data.menuCode eq data2.upperMenuCode}">
							 			<c:choose>
											<c:when test="${data2.childCnt eq 0}">
												<li class="no_child" id="menu-${data2.menuCode}" data-code="${data2.menuCode}" data-url="${pageContext.request.contextPath}${data2.menuUrl}" data-job-url=""><a href="<c:url value='${data2.menuUrl}'/>">${data2.menuNm}</a></li>
											</c:when>
											<c:otherwise>
												<li class="" id="menu-${data2.menuCode}" data-code="${data2.menuCode}" data-url="${pageContext.request.contextPath}${data2.menuUrl}" data-job-url=""><a href="javascript:void(0);">${data2.menuNm}</a>
													<ul class="depth${data2.depth}"" data-parent="003">
														 <c:forEach items="${systemMenuList}" var="data3" varStatus="status">
															<c:choose>
																<c:when test="${data2.menuCode eq data3.upperMenuCode}">

																	<c:choose>
																		<c:when test="${data3.newWindowAt eq '1'}"> <!-- 새창 -->
															        		<li class="${menuIcon}" id="menu-${data3.menuCode}"><a href="${data3.menuUrl}" target="_blank">${data3.menuNm}</a>
															        	</c:when>
																		<c:otherwise>
																			<li class="no_child" id="menu-${data3.menuCode}" data-code="${data3.menuCode}" data-url="${pageContext.request.contextPath}${data3.menuUrl}" data-job-url=""><a href="<c:url value='${data3.menuUrl}'/>">${data3.menuNm}</a></li>
																		</c:otherwise>
																	</c:choose>
																</c:when>
															</c:choose>
														 </c:forEach>

													</ul>
												</li>
											</c:otherwise>
										</c:choose>
									</c:when>
								</c:choose>
							 </c:forEach>
						</ul>
						</li>
					</c:otherwise>
				</c:choose>
		</c:if>

     </c:forEach>
     </ul>
</div>
<!-- // sidenav -->
