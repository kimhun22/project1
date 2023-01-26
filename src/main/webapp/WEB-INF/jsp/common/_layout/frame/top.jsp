<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>
<div id="header_top">
	<div class="left_area" >
		<!--
		<div class="logo_box" >
			<h1 class="logo goMain"><a href="#"><span class="sr_only">스마트인허가</span></a></h1>
		</div>
		-->
		<div class="btn_box" >
			<button class="btn_sideControll tooltip" type="text" value="메뉴접기" data-tooltip="메뉴접기"></button>
		</div>
	</div>

    <ul class="top_util">
        <li><a href="#" class="goMain">대시보드</a></li>
        <li class="logout"><a href="/${hmSiteCode}<c:url value="/logout.do" />">로그아웃</a></li>
    </ul>
</div>
<!-- // header_top -->

<script>

$('.btn_sideControll').click(function(){
	var $this = $('.btn_sideControll')
	var $target = $('.btn_sideControll,#sidenav,.logo_box,#container,#header_top,#page_top,#footer_tab');
	if ( $this.hasClass('on')){
		$this.attr({'value':'메뉴접기','data-tooltip':'메뉴접기'});
		$target.removeClass('on');
	} else {
		$this.attr({'value':'메뉴열기','data-tooltip':'메뉴열기'});
		$target.addClass('on')
	}
});



</script>
