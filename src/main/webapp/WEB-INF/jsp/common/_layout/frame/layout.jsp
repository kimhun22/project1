<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="core.util.PropertiesUtil"%>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="viewport" content="width=1200">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title></title>

<!-- css -->
<link rel="stylesheet" href="/${hmSiteCode}/css/bootstrap-grid.min.css" />
<link rel="stylesheet" href="/${hmSiteCode}/css/common.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/main.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/pretendard.css" />
<link rel="stylesheet" href="/${hmSiteCode}/css/jquery.datetimepicker.min.css" />
<link rel="stylesheet" href="/${hmSiteCode}/js/swiper/swiper.min.css" />

<!-- js -->
<script type="text/javascript" src="/${hmSiteCode}/js/lib/jquery-3.6.1.min.js"></script>
<script src="/${hmSiteCode}/js/lib/ui/1.13.2/jquery-ui.min.js"></script>
<script src="/${hmSiteCode}/js/swiper/swiper.min.js"></script>
<script src="/${hmSiteCode}/js/datetimepicker/jquery.datetimepicker.full.min.js"></script>
<script src="/${hmSiteCode}/js/script.js"></script>

<style>
#footer_tab .btn_map.active {border-top-color:#f47420;}
</style>
<script>
$(function (){
	//새로 고침 프레임 적용
	$(document).on("keydown", function(e) {
        if (e.which === 116 || (e.which === 82 && e.ctrlKey) ) { //f5 , ctrl+r
            if($('.frm:visible').attr('id') == "frm-main" ){
                if(confirm("새로고침 시 열려 있는 전체 탭이 닫힙니다.\n새로고침 하시겠습니까?")){
                    location.reload();
                }
            }else{
                var src = $('.frm:visible').children('iframe').attr('src');
                $('.frm:visible').children('iframe').attr('src',src);
            }
            return false;
        }
    });

	//메인 레이아웃에서 뒤로가기 방지
	history.pushState(null, document.title, location.href);
	window.addEventListener('popstate', function(event) {
		history.pushState(null, document.title, location.href);
	});


	//메인 화면 활성화
	$('.goMain').on('click',function(){
		$('.tab').removeClass('active');
		titleText('main');
		$('.frm').stop();
		$('.frm').hide();
		$('.main_lnb').hide();
		$('.main_lnb').removeClass('on');
		$('#frm-main').fadeIn();
	});

	//지도 화면 활성화
	$('.btn_map').on('click',function(){
		$('.tab').removeClass('active');
		$(this).addClass('active');
		titleText('map');
		$('.frm').stop();
		$('.frm').hide();
		$('.main_lnb').hide();
		$('.main_lnb').removeClass('on');
		$('#frm-map').fadeIn();

		$('.lnb+#container').css('top','96px');
	});


});

//iframe 높이 조절
function resizeIframe(obj) {
	//console.log("resizeIframe",typeof obj,obj.contentWindow.document.body.scrollHeight);

	//if(typeof obj == 'string'){
	//	obj = document.getElementById('ifrm-'+obj);
	//}
    //obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
}

function loadIframe(obj) {
  //console.log("resizeIframe",typeof obj,obj.contentWindow.document.body.scrollHeight);
  try {
	  if(typeof obj == 'string'){
	      obj = document.getElementById('ifrm-'+obj);
	  }
	  obj.contentWindow.document.getElementsByTagName('html')[0].classList.add('ifrm-layout-html');
	  obj.contentWindow.document.getElementsByTagName('html')[0].style['padding'] = '24px';
	  obj.contentWindow.document.body.style['min-height'] = obj.parentElement.scrollHeight + 'px';
  } catch (error) {
	  console.error(error);
  }
}



//타이틀 생성
function titleText(code){
	var title = '';
	var location = '<li class="home"><span class="sr_only">홈</span></li>';
	var menu1 = '';
	var menu2 = '';
	var menu3 = '';
	var menu4 = '';

	if(code=="main"){
		title = '대시보드';
		location = location + '<li>대시보드</li>';
	}else if(code=="map"){
		title = '지도보기';
		location = '<button type="button" class="btn_pop_close"><span class="sr_only">팝업 닫기</span></button>';
	}else{
		//서브 메뉴인 경우 상위 메뉴에 대한 값으로 변경
		if($('#subMenu-'+code).length == 0) title = $('#menu-'+code+'>a').text();
		else title = $('#menu-'+$('#subMenu-'+code).parent('ul').data('code')+'>a').text();

		if(code.length==3){
			menu1 = $('#menu-'+code+'>a').text();
			location = location + '<li>'+menu1+'</li>';
		}else if(code.length==6){
			menu1 = $('#menu-'+code.substring(0,3)+'>a').text();
			menu2 = $('#menu-'+code.substring(0,6)+'>a').text();
			location = location + '<li>'+menu1+'</li>'+ '<li>'+menu2+'</li>';
		}else if(code.length==9){
			menu1 = $('#menu-'+code.substring(0,3)+'>a').text();
			menu2 = $('#menu-'+code.substring(0,6)+'>a').text();
			menu3 = $('#menu-'+code.substring(0,9)+'>a').text();
			location = location + '<li>'+menu1+'</li>'+ '<li>'+menu2+'</li>'+ '<li>'+menu3+'</li>';
        }else if(code.length==12){
            menu1 = $('#menu-'+code.substring(0,3)+'>a').text();
            menu2 = $('#menu-'+code.substring(0,6)+'>a').text();
            menu3 = $('#menu-'+code.substring(0,9)+'>a').text();
            menu4 = $('#menu-'+code.substring(0,12)+'>a').text();
            location = location + '<li>'+menu1+'</li>'+ '<li>'+menu2+'</li>'+ '<li>'+menu3+'</li>'+ '<li>'+menu4+'</li>';
        }
	}

	$('#page_top h2').text(title);
	$('#page_top .location').html(location);
}

</script>

</head>
<body>
<div id="wrap">
	<header id="header">
		<t:insertAttribute name="top" />
		<t:insertAttribute name="left" />
		<t:insertAttribute name="title" />
	</header>
    <main id="main">
    	<t:insertAttribute name="sub" />
		<div id="container">
			<div class="frm" id="frm-main">
			<t:insertAttribute name="content" ignore="true"/>
			</div>
			<div class="frm" id="frm-map" style="display:none">
			<%-- <iframe class="ifrm" id="ifrm-map" onload="loadIframe(this)" data-code="map" src="${pageContext.request.contextPath}/openMap.do" frameborder="0"></iframe> --%>
			</div>
		</div>
	</main>
	<t:insertAttribute name="tab" />
</div>
<div id="overlay"><img src="/${hmSiteCode}/image/ajax-loader.gif" id="img-load"/></div>
</body>
</html>

