<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
/* 공통 script */
$(function (){

	//새로 고침 프레임 적용
	$(document).on("keydown", function(e) {
	    if (e.which === 116 || (e.which === 82 && e.ctrlKey) ) { //f5 , ctrl+r
			location.reload();
	        return false;
	    }
	});

});

</script>

<style>
/* 공통 css */

body{background: #f5f5f5;}

</style>