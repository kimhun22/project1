$(function(){
/*
	$('#sidenav .depth1 > li:not(.no_child) > a').on('click',function(e){
		e.preventDefault();
		var $target = $(this).parent();
		$('#sidenav .depth1 > li').not($target).removeClass('on');
		$target.toggleClass('on');
	});
	$('#sidenav .depth2 > li:not(.no_child) > a').on('click',function(e){
		e.preventDefault();
		var $target = $(this).parent();
		$('#sidenav .depth2 > li').not($target).removeClass('on');
		$target.toggleClass('on');
	});
*/
	/* footer tab */
	if($('#footer_tab_menu').length > 0) $( "#footer_tab_menu" ).sortable();
	function footerResizeFn(){
		$('#footer_tab_menu li').removeClass('hidden_list');
		$('#footer_tab_menu li').each(function (index, item) {
			var ty = $(this).position().top;
			if(ty!=0){
				$(this).addClass('hidden_list');
			}
		});
		$('#footer_tab_hidden_list').empty();
		$('#footer_tab_hidden_list').append($('#footer_tab_menu li.hidden_list').clone());

		var h_num = $('#footer_tab_menu .hidden_list').length;
		if(h_num>0){
			var index = $('.hidden_list').eq(0).index();
			var tx = $('#footer_tab_menu li').eq(index-1).position().left+$('#footer_tab_menu li').eq(index-1).innerWidth();
			$('.btn_list_more').show();
			$('.btn_list_more').css({'left':tx+'px'});
			$('.btn_list_more span').text(h_num);
		}else{
			$('#footer_tab_hidden_list').hide();
			$('.btn_list_more').hide();
		}

		$('#footer_tab_menu').on('click','.btn_tab_close',function(e){
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			var idx = $(this).parents('li').attr('data-index');
			console.log(idx);
			$(this).parents('li').remove();
			$('#footer_tab_menu li[data-index='+idx+']').remove();

			//컨텐츠 영역 삭제
			var code = $(this).parent('li').data('code');
			$('#frm-'+code).remove();

			//탭이 있을 경우 마지막 탭 클릭
			if($('.tab').length>0){
				$('.tab').last().click();
			}else{//TODO 없을 경우 대시보드

			}
			footerResizeFn();
		});
		$('#footer_tab_hidden_list').on('click','.btn_tab_close',function(e){
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			var idx = $(this).parents('li').attr('data-index');
			$(this).parents('li').remove();
			$('#footer_tab_hidden_list li[data-index='+idx+']').remove();

			//컨텐츠 영역 삭제
			var code = $(this).parent('li').data('code');
			$('#tab-'+code).remove();
			footerResizeFn();
		});


	}
	$(window).on('resize', function() {
		footerResizeFn();
	});
	footerResizeFn();
	$('#footer_tab .btn_list_more').bind('click',function(e){
		$('#footer_tab_hidden_list').toggle();
	});

	// layer pop
    $(document).on("click",".pop_dimmed_bg",function(){
		//$(this).parents('.layer_pop').hide();
		layerPopHide($(this));
	});
	$(document).on("click",".balloon_container .btn_balloon_view",function(){
		$(this).parents('.balloon_container').toggleClass('open');
	});

	// form_list 폼 추가삭제
	var fdata = [];
	$('.form_list').each(function (index, item) {
		$(this).attr('data-idx',index);
		fdata[index] = $(this).find('li').eq(0).clone();
	});
	$(document).on("click",".form_list .btn_del",function(){
		$(this).parents('li').remove();
	});
	$(document).on("click",".form_list .btn_add",function(){
		var idx = $(this).parents(".form_list").attr('data-idx');
		$(this).parents('.form_list').append(fdata[idx].clone());
	});


	var curDate = new Date();
	if(typeof $.datetimepicker !== 'undefined') {
		$.datetimepicker.setLocale('kr');
		$(".inp_date").datetimepicker({
				format: 'Y-m-d',
				formatDate: 'Y/m/d',
				step: 10,
				// yearStart: curDate.getFullYear(),
				// yearEnd: curDate.getFullYear() + 1,
				defaultDate: curDate,
	            timepicker:false, // 2021-02-22
		});
	}
	$.fn.fixedScrollHead = function(option) {
		var $this = this;
		var $top_wrap;
		var $cont_wrap;
		var data;
		var scrollHeight = option.scrollHeight;
		var minHeight = option.minHeight;
		var rows = option.rows;
		function config(){

			var data_len = $this.find('.data_table tbody td').length;

			if(data_len<=1){
				$this.addClass('overflow_auto');
				return;
			}

			data = $this.html();
			$this.empty();
			$this.append('<div class="top_wrap"><div>'+data+'</div></div>');
			$this.append('<div class="cont_wrap"><div></div></div>');
			$top_wrap = $this.find('.top_wrap');
			$cont_wrap = $this.find('.cont_wrap');
			$cont_wrap.find('> div').append($this.find('.data_table').clone());
			$top_wrap.find('tbody').remove();

			if(!scrollHeight){
				scrollHeight = 320;
			}

			/* scroll 컨테이너 위치잡기 */
			var th = $top_wrap.find('> div').height();
			var tw = $cont_wrap.find('> div').width();
			if(rows){
				var len = $cont_wrap.find('tbody tr').length;
				if(len<=rows){
					return;
				}
				scrollHeight = $cont_wrap.find('tbody tr').eq(rows-1).position().top+$cont_wrap.find('tbody tr').eq(rows-1).height();
			}


			$cont_wrap.css({'max-height':scrollHeight+'px'});
			$cont_wrap.css({'min-height':minHeight+'px'});
			$top_wrap.width(tw);
			// $top_wrap.height(th);

		}
		function resizeFn(){
			var th = $top_wrap.find('> div').height();
			var tw = $cont_wrap.find('> div').width();


			$top_wrap.width(tw);
			// $top_wrap.height(th);
		}

		$(window).on('resize', function() {
			resizeFn();
		});
		config();
		resizeFn();
	}
	$.fn.fixedScrollReset = function(option) {
		var $this = this;
		var $top_wrap = $this.find('.top_wrap');
		var $cont_wrap = $this.find('.cont_wrap');

		var th = $top_wrap.find('> div').height();
		var tw = $cont_wrap.find('> div').width();

		$top_wrap.width(tw);
		// $top_wrap.height(th);
	}


});


function windowPop(url,title){
	var tw = Math.max(screen.width-200,1000);
	var th = screen.height-250;
	window.open(url,title,'width='+tw+', height='+th+', left=100, top=90, menubar=no, status=no, toolbar=no, resize=n0');
}
function layerPop(id){
	$('#'+id).show();
	var b = $('#'+id).hasClass('type_full');
	if(!b){
		var ty = $('#'+id).find('.pop_container').height()/2;
		$('#'+id).find('.pop_container').css({'marginTop':-ty+'px'});
	}
}
function layerPopHide(e){
	if( $(e).parents('.layer_pop').attr('id') == 'actionLmtt_pop01' ){//행위(입지)제한 조회 팝업일 경우
		if(confirm("행위(입지)제한 정보 팝업을 종료하시겠습니까?")){
			$(e).parents('.layer_pop').hide();

			$('#actionLmtt_pop01 .pnuDataList').empty();
			$('#actionLmtt_pop01 .fcltsDataList').empty();
			$('.resultArea').hide();
			$('.searchArea').show();
			$('#actionLmttResult').hide();

			if( $(e).parents('.layer_pop').data('type')=='popup' ){
				window.close();
			}
		}
	}else{
		$(e).parents('.layer_pop').hide();
	}
}
function fixedScrollReset(){
	$('.fixed_scroll_head').each(function (index, item) {
		$(this).fixedScrollReset();
	});
}