<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>
<style>
#footer_tab_menu li {max-width: 216px;}
</style>
<div id="footer_tab">
    <ul id="footer_tab_menu"></ul>
    <ul id="footer_tab_hidden_list"></ul>
    <button type="button" class="btn_list_more"><span>4</span></button>
</div>
<script>
	$(function (){
		//탭 클릭
		$('#footer_tab_menu').on('click','li',function(e){
			e.stopPropagation();
			e.preventDefault();
			console.log('tab click',$(this).data('code'));
			var $this = $(this);
			$('.tab').removeClass('active');
			$this.addClass('active');
			var tabCode = $this.data('code');
			$('.frm').hide();
			$('#frm-'+tabCode).fadeIn();
			titleText(tabCode);
			subMenuCheck(tabCode);

		     //서브메뉴 체크
	        //depth3 submenu
	        if($('#sub-'+tabCode+' li').length == 0 && $('#subMenu-'+tabCode).length != 0) {
	            subMenuCheck($('#subMenu-'+tabCode).parent().data('code'));
	            $('#subMenu-'+tabCode).siblings().removeClass('on');
	            $('#subMenu-'+tabCode).addClass('on');
	        } else {
	        	subMenuCheck(tabCode);
	        }

		});
	});
</script>