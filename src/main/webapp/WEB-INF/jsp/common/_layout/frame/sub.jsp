<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>
<style>
.sub{display:none;}
.subMenu{cursor:pointer;}

/**
스크롤 레이아웃 스타일 적용 2100907
*/
.subFrm {height:inherit;}
.frm{height:100%;}
.frm .ifrm {width:100%;height: inherit;position: absolute;top: 0px;left: 0px;}
</style>
<script>
$(function (){

	//서브 메뉴 생성

	var subMenu = '';
	//depth4, 행위제한 조견표
	$('#sidenav .depth4, #sidenav ul.depth3[data-parent="006002"]').each(function(){

		var $ul = $(this);
		subMenu += '<ul class="sub" data-code="'+$ul.data('parent')+'" id="sub-'+$ul.data('parent')+'">';
			if($ul.find('li').length>0){
				$ul.find('li').each(function(){
					var $li = $(this);
					subMenu += '<li class="subMenu" id="subMenu-'+$li.data('code')+'" data-code="'+$li.data('code')+'" data-url="'+$li.data('url')+'" >';
					subMenu += '<a>'+$li.find('a').text()+'</a></li>';
				});
			}
		subMenu += '</ul>';
	});

	$('.main_lnb').html(subMenu);

	//서브 메뉴 클릭
	$('.main_lnb').on('click','.subMenu',function(e){
		$this = $(this);
		e.stopPropagation();
		e.preventDefault();

		var menuText = $("#page_top h2").text() + '>' +  $this.children('a').text();
		var menuCode = $this.data('code');
		var menuUrl = $this.data('url');
		var parentCode = $this.parent('ul').data('code');

	    //depth3 submenu인 경우 tab도 생성해야함
        if($('#sub-'+menuCode+' li').length == 0 && $('#subMenu-'+menuCode).length != 0) {
        	tabMenuCheck(menuCode, menuText, menuUrl);
        }

		//$('.subMenu').removeClass('on');
		$this.siblings().removeClass('on');
		$this.addClass('on');

		//프레임 활성화
		$('#frm-'+parentCode).show();
		$('#frm-'+parentCode+' .subFrm').hide();
		$('#frm-'+parentCode+' #subFrm-'+menuCode).fadeIn();

	});
});
function subMenuCheck(code){
	if($('#sub-'+code).length>0){
		$('.sub').hide();
		$('#sub-'+code).show();
		$('.main_lnb').show();
		$('.main_lnb').addClass('on');
		$('.lnb+#container').css('top','143px');
		//$('#sub-'+menuCode+' li').first().click();//첫번째 서브메뉴 클릭
	}else{
		$('.lnb+#container').css('top','96px');
		$('.main_lnb').hide();
	}
}

function tabMenuCheck(menuCode, menuText, menuUrl) {

	//URL값이 일치하지 않으면 제외
	if($('#sidenav li#menu-'+menuCode).find('a')[0].getAttribute('href') !== menuUrl.replace(/\?.*/,'')) {
		return;
	}

	//업무 URL이 있으면 menuUrl변경
	if($('#sidenav li#menu-'+menuCode).data('jobUrl') != '') {
		menuUrl = $('#sidenav li#menu-'+menuCode).data('jobUrl');
	}

    //생성 된 프레임이 있을 경우
    if($('#frm-'+menuCode).length > 0){
        //$('#frm-'+menuCode).fadeIn();
        $('#tab-'+menuCode).click();//탭 클릭
    } else {//프레임이 없을 경우 생성
        //하단 탭 생성
        var $tab_ul = $('#footer_tab_menu');
        var tab_li = '<li class="tab" id="tab-'+menuCode+'" data-code="'+menuCode+'" title="'+menuText+'"><a>'+menuText+'</a><button class="btn_tab_close"><span class="sr_only">닫기</span></button></li>';
        $tab_ul.append(tab_li);
        //footerResizeFn();

		if($('#sidenav li#menu-'+menuCode).data('jobUrl') != '') {
	        if(menuUrl){

	            var newForm = $('<form></form>');
	            newForm.attr('name','ifrm-'+menuCode);
	            newForm.attr('target','ifrm-'+menuCode);
	            newForm.attr('method','post');
	            newForm.attr('action', menuUrl.split('?')[0]);
	            newForm.appendTo('body');

	            var qs = menuUrl.substring(menuUrl.indexOf('?') + 1).split('&');
	            for(var i = 0, result = {}; i < qs.length; i++){
	            	if(qs[i].indexOf('=')) {
		            	var param = qs[i].split('=');
						$('<input type="hidden" name="'+param[0]+'" value="'+ param[1] + '" />').appendTo(newForm);
	            	}
	            }

	            var frame = '<div class="frm" id="frm-'+menuCode+'" data-code="'+menuCode+'" style="display:none;"><iframe class="ifrm" id="ifrm-'+menuCode+'" name="ifrm-'+menuCode+'" onload="loadIframe(this)" data-code="'+menuCode+'" src="'+menuUrl.split('?')[0]+'" frameborder="0" /></div>';
	            $('#container').append(frame);


	            newForm.submit();
	            newForm.remove();
	        }
		} else {
	        if(menuUrl){
	            var frame = '<div class="frm" id="frm-'+menuCode+'" data-code="'+menuCode+'" style="display:none;"><iframe class="ifrm" id="ifrm-'+menuCode+'" name="ifrm-'+menuCode+'" onload="loadIframe(this)" data-code="'+menuCode+'" src="'+menuUrl+'" frameborder="0" /></div>';
	            $('#container').append(frame);
	        }
		}
        $('#tab-'+menuCode).click();
    }
    //jobUrl 초기화
    $('#sidenav li#menu-'+menuCode).data('jobUrl', '');
}
</script>
<div class="lnb main_lnb" style="display:none;"></div>
