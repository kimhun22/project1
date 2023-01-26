/**
 * Heart Media 기본 스크립트
 *
 * -----------------------------------------------------------------
 * v0.1				2015.01.02			ysKo
 * -----------------------------------------------------------------
 *
 *
 */

//////////////////////// <기본설정> ///////////////////////////

/** 기본설정 **/

/* ContextPath 설정 */
var GLOBAL_ContextPath = "";

/** jQuery 설정 **/

/* jQuery Ajax 설정 */
$.ajaxSetup({
	type:"POST",
	contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	dataType : "JSON",
	cache: false,
	beforeSend: function(xhr) {
		xhr.setRequestHeader('AJAX', true);

		//$.blockUI();
	},
	complete: function() {
		//$.unblockUI();
	}
});

/* Ajax Error 설정 */
$(document).ajaxError( function( event, jqxhr, settings, exception ){
	if(jqxhr.status == "404"){
		alert("[Error:91] Ajax Error\n(CODE:404) " + $.i18n['common.alert.error.pageNotFound']);
	}
	else if(jqxhr.status == "405"){
		alert("[Error:92] 405 Method Not Allow\\n" + $.i18n['common.alert.error.notAllow']);
	}
	else if  ( jqxhr.status == 700 )  {
		alert("세션이 종료되었습니다.\n로그인 후 이용해주세요.");
		location.href= GLOBAL_ContextPath + "/login.do";
	}
	else{
		var ex;
		if(typeof(jqxhr.responseJSON) != 'undefined'){
			ex = jqxhr.responseJSON.exception;
		} else if(typeof(jqxhr.responseText) == 'object' || typeof(jqxhr.responseText) == 'string'){
			try{
				ex = $.parseJSON(jqxhr.responseText).exception;
			} catch (err){

			}
		}

		if(typeof(ex) == 'undefined'){
			alert($.i18n['common.alert.error.unknown']);
		} else if(ex.name == 'HMException'){
			alert(ex.message);
			if(ex.errorCode == 700){
				location.href= GLOBAL_ContextPath + "/login?url=" + encodeURI(location.pathname + location.search);
			}
			if(ex.errorCode == 701){
				location.href= GLOBAL_ContextPath + "/login?referer=" + encodeURI(location.pathname + location.search);
			}
		} else {
			if(typeof(ex.maxUploadSize) != 'undefined'){
				alert($.getLocaleMessage('common.alert.error.maxUploadSize', [bytesToSize(ex.maxUploadSize)]));
			} else {
				alert($.i18n['common.alert.error.unknown']);
			}
		}
	}
});

/** jQuery UI **/

///* jQuery UI Datepicker 설정 */
//$.datepicker.setDefaults( $.datepicker.regional["ko"] );
//var datepickerYearRange = (new Date().getFullYear()-50)+":"+(new Date().getFullYear()+50);
//$.datepicker.setDefaults({
//	dateFormat: 'yy-mm-dd',
//	changeYear:true,
//	changeMonth:true,
//	buttonImageOnly: true,
//	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
//	yearRange: datepickerYearRange
//});
//
//var monthpickerYearRange = (new Date().getFullYear()-50)+":"+(new Date().getFullYear()+50);
//var monthpicker_options = {
//	dateFormat : 'yy-mm',
//	buttonImageOnly: true,
//	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
//	yearRange: monthpickerYearRange
//};

/* validate form reset */
$.fn.reset = function(){
	if(this[0].tagName == "FORM"){
		this.validate().resetForm();
		this.get(0).reset();
	}
};


/** Ajax Submit Plugin 설정 **/
/* ajaForm setting */
$.ajaxSubmitSetup = function(){
	return {
		type:"POST",
		dataType: "json"
	};
};

var originAjaxSubmit = $.fn.ajaxSubmit;
$.fn.extend({
	ajaxSubmit: function(options){
		$.extend(options, $.ajaxSubmitSetup());
		return originAjaxSubmit.apply(this, [options]);
	}
});

//////////////////////// </기본설정> ///////////////////////////

////////////////////////<Attribute> ///////////////////////////

/* numberOnly */
$(document).on("keydown", "input:text[numberOnly]", function(e) {
	if((e.which < 47 || e.which > 58) && (e.which != 0)  && e.which != 8 && e.which != 46 && e.which != 190 && e.which != 9
			&& (e.which < 96 || e.which > 105) && e.which != 110
			&& !(e.which >= 37 && e.which <= 40)){
		e.preventDefault();
	}

	if((e.which == 190 || e.which == 110) && $(this).val().indexOf(".") > -1){
		e.preventDefault();
	}
});
$(document).on("keyup", "input:text[numberOnly]", function(e) {
	if  ( e.which >= 37 && e.which <= 40 )  {
		e.preventDefault();
	}
	else  {
		$(this).val($(this).val().replace(/[^0-9:\.]/gi,""));
	}
});

/* numberFormat */
$(document).on("keyup", "input:text[numberFormat]", function(e) {
	if  ( e.which >= 37 && e.which <= 40 )  {
		e.preventDefault();
	}
	else  {
		if( $(this).val() != null && $(this).val() != '' ) {
	    	$(this).val( commify($(this).val().replace(",", "")) );
	    }
	}
});

function commify(n) {
	  var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
	  n += '';                          // 숫자를 문자열로 변환

	  while (reg.test(n))
	    n = n.replace(reg, '$1' + ',' + '$2');

	  return n;
}

$.numberFormat = function(value){
	value = value + "";
	var tmps = value.replace(/[^0-9]/g, '');
    var tmps2 = tmps.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
    return tmps2;
};

//////////////////////// </Attribute> ///////////////////////////

//////////////////////// <Function> ///////////////////////////

/* Locale Properties 가져오기 */
$.getLocaleMessage = function(key, params){
	var value = $.i18n[key];
	if(typeof(params) != undefined){
		var tmp = value;
		$.each(params, function(i, v){
			tmp = value.replace("{" + i + "}", v);
		});
		//console.log(params);
		return tmp;
	}
};

/* ### $.loadLayer ########################
: 로드한 페이지를 popup dialog로 보여주는 메서드
url : 로드할 페이지 URL
options : {title: "팝업타이틀", dialog: "jquery dialog options", data: "로드할 페이지의 POST data"}
target : 팝업대상 엘리먼트 (default: _loadLayer DIV를 생성하여 팝업을 띄움)

2014.12.10 ysko
*/
$.loadLayer = function(url, options, target){
	var dialogOption = {modal:true, width:"auto", focus: function(event, ui){$(':focus', this).blur()}};
	var data = {};

	if(typeof(target) == "undefined"){
		target = $("#_loadLayer");
		if(target.length == 0){
			target = $("<div>", {id: "_loadLayer", "class": "popup_area"}).appendTo("body");
		}
	}

	if(typeof(options) != "undefined"){
		if(typeof(options.title) != "undefined"){
			$.extend(dialogOption, {title: options.title});
		}

		if(typeof(options.dialog) != "undefined"){
			$.extend(dialogOption, options.dialog);
		}

		if(typeof(options.data) != "undefined"){
			$.extend(data, options.data);
		}
	}

	target.load(url, data, function(response, status, xhr){
		if (status != "error") {
			$(this).dialog(dialogOption);
		}
	});
};

/*
 * Ajax Loader
 */
$.ajaxLoading = function(mode){
	if(mode == "show"){
		return function(){$($("<div>", {"class": "ui-widget-overlay ui-front"}).append($("<div>", {"class": "ajaxLoader"}))).appendTo("body");};
	}
	else if(mode == "hide"){
		return function(){$(".ui-widget-overlay").remove();};
	}
};

/*
 * Ajax Layer Loader
 */
$.ajaxLayerLoading = function(mode, target){
	if(typeof(target) != 'undefined'){
		if(mode == 'show'){
			return function(){target.before($("<div>", {"class": "loadingArea"})); target.parent().addClass("loader")};
		}
		if(mode == 'hide'){
			return function(){target.prev(".loadingArea").remove(); target.parent().removeClass("loader");};
		}
	}
}

/* URL Parameter 전부 가져오기 */
$.getUrlVars = function(){
    var vars = [], hash;
    var urlIndex = window.location.href.indexOf('?');
    if(urlIndex > 0){
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++) {
	        hash = hashes[i].split('=');
	        if($.trim(hash[0]).length > 0){
		        vars.push(hash[0]);
		        vars[hash[0]] = hash[1];
	        }
	    }
    }
    return vars;
};

/* URL Parameter 하나씩 가져오기 */
$.getUrlVar = function(name) {
	var val = $.getUrlVars()[name];
	if(typeof val === 'undefined')
		val = null;
    return val;
};

/*
* 검색조건 유지 returnUrl 만들기
*/
$.getAddParamUrl = function(basicUrl) {
	var urlVars = $.getUrlVars();

	for ( var i = 0 ; i < urlVars.length ; i++ ) {
		if ( null != $.getUrlVar(i) ) {
			if  ( basicUrl.indexOf("?" + urlVars[i] + "=") == -1 && basicUrl.indexOf("&" + urlVars[i] + "=") == -1 )  {
				if ( basicUrl.indexOf("?") == -1 ) {
					basicUrl += "?" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
				} else {
					basicUrl += "&" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
				}
			}
		}
	}

	return basicUrl;
};

/*
* deleteParam 제외 후 검색조건 유지 returnUrl 만들기
*/
$.getRemoveParamUrl = function(basicUrl, deleteParamArray) {
	var deleteParam = deleteParamArray.split(",");
	var urlVars = $.getUrlVars();
	var useAt = true;

	for ( var i = 0 ; i < urlVars.length ; i++ ) {
		if ( null != $.getUrlVar(i) ) {
			for  ( var j = 0 ; j < deleteParam.length ; j++ )  {
				if  ( deleteParam[j] == urlVars[i] )  {
					useAt = false;
					break;
				}  else  {
					useAt = true;
				}
			}

			if  ( useAt )  {
				if  ( basicUrl.indexOf("?" + urlVars[i] + "=") == -1 && basicUrl.indexOf("&" + urlVars[i] + "=") == -1 )  {
					if ( basicUrl.indexOf("?") == -1 ) {
						basicUrl += "?" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
					} else {
						basicUrl += "&" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
					}
				}
			}
		}
	}

	return basicUrl;
};

/*
 * SELECT readonly
 * @param : action = (true || false)
 */
$.fn.readonly = function(action){
	if(typeof(action) == "undefined")
		action = true;
	$.each($(this), function(i, v){
		if(v.tagName == "SELECT"){
			if(action || action == "true"){
				$(this).children("option").not(":selected").attr("disabled", "disabled");
			} else {
				$(this).children("option").not(":selected").removeAttr("disabled");
			}
		}
	});
};

/*
 * Common Function
 */
function bytesToSize(bytes) {
   var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
   if (bytes == 0) return '0 Byte';
   var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
   return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
};


/*
 * 박성영
 * DATE 타입을 yyyy-mm-dd 형식으로 변경
 * */
Date.prototype.yyyymmdd = function() {
    var yyyy = this.getFullYear().toString();
    var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based
    var dd  = this.getDate().toString();
    return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
};

/*
 * 박성영
 * yyyymmdd 형식을 yyyy-mm-dd 형식으로 변경
 * */
function yyyymmddFormatStr(dateStr, str) {
    if( dateStr.length >= 8 ){
    	return dateStr.substr(0,4)+str+dateStr.substr(4,2)+str+dateStr.substr(6,2);
    }else{
    	return dateStr;
    }
};

/*
 * 강동성
 * 문자열 byte 체크
 * @param elem  - 대상 문자열이 포함된 element
 * @param limit - 허용될 수 있는최대 byte
 * */
function checkByte(elem, limit) {
	var codeByte  = 0;
	var overCount = 0;

	for(var i=0; i<elem.value.length; i++) {
		if(escape(elem.value.charAt(i)).length > 4)	codeByte += 3;
		else	codeByte++;

		if(codeByte > limit)	overCount++;
	}

	if(codeByte > limit) {
		alert('입력하신 내용의 길이가 너무 길어 마지막 입력하신'+overCount+'자 만큼 제거됩니다.');
		elem.focus();

		elem.value = elem.value.substring(0, elem.value.length - overCount);

		return false;
	}
}

/*
 * ysKo
 * 우편번호 팝업
 * [사용방법] javascript:$.openFindAddress('zipCode1', 'zipCode2', 'address1');
 * 			input의 id 또는 name으로 파라미터 전송
*/
$.openFindAddress = function(z1, z2, addr){
	url = GLOBAL_ContextPath + "/common/address/findRoad?z1=" + z1 + "&z2=" + z2 + "&addr=" + addr;
	window.open(url, "도로명주소", "resizable=no, status=no, scrollbars=yes, toolbar=no, menubar=no, width=450px, height=600px");
}

/*
 * ysKo
 * 글자 자르기
*/
$.cutString = function(str, len, extra) {
	var s = 0;
	for (var i=0; i<str.length; i++) {
		s += (str.charCodeAt(i) > 128) ? 2 : 1;
		if (s > len) return str.substring(0,i) + extra;
	}
	return str;
}

// 기본 element 밸리데이션 체크
function validateBaseElement(id, required, maxLength, editorAt) {
	var data = $("#"+id);
	if  ( typeof data.val() == "undefined" )  {
		data = $("[id^="+ id +"]");   // 다중 element 찾기
	}

	// 필수값 일 경우 입력 여부 체크
	if  ( required )  {
		var errorMessage = "";
		// select
		if  ( data.prop("type") == "select-one" )  {
			if  ( $.trim(data.val()) == "" )  {
				errorMessage = data.attr("title") + " 을/를 선택해주세요.";
			}
		}
		// radio, checkbox
		else if  ( data.prop("type") == "radio" || data.prop("type") == "checkbox" )  {
			if  ( !data.is(":checked") )  {
				errorMessage = data.attr("title") + " 을/를 선택해주세요.";
			}
		}
		else  {
			if  ( $.trim(data.val()) == "" )  {
				errorMessage = data.attr("title") + " 을/를 입력해주세요.";
			}
		}

		// error message가 존재할 경우
		if  ( errorMessage != "" )  {
			validateSetMessageEvent(data, errorMessage, editorAt);

			return false;
		}
	}

	// 최대 길이 체크
	if  ( maxLength != null )  {
		if  ( !validateMaxLength(data, maxLength, editorAt) )  return false;
	}

	return true;
}

// 밸리데이션 message 처리 및 hide event 적용
function validateSetMessageEvent(targetObj, errorMessage, editorAt) {
	// 유형 별 message target 세팅
	var messageTarget;
	if  ( targetObj.length > 1 )  {
		messageTarget = targetObj.closest("div").nextAll("small.error-message");
	}
	else  {
		messageTarget = targetObj.nextAll("small.error-message");
	}

	// message view 및 유형 별 hide event 적용
	if  ( messageTarget.length > 0 )  {
		messageTarget.html(errorMessage); messageTarget.show();

		// 에디터 유형 일 경우
		if  ( null != editorAt && editorAt )  {
			CKEDITOR.instances[targetObj.attr("id")].on("change", function(e) {
				messageTarget.empty(); messageTarget.hide();
			});
		}
		else  {
			$(targetObj).on("change", function(e) {
				messageTarget.empty(); messageTarget.hide();
			});
		}
	}
	else  {
		alert(errorMessage);
	}

	// 유형 별 focus 적용
	if  ( targetObj.length > 1 )  {
		targetObj.eq("0").focus();
	}
	else if  ( null != editorAt && editorAt )  {
		CKEDITOR.instances[targetObj.attr("id")].focus();
	}
	else  {
		targetObj.focus();
	}
}

// 밸리데이션 최대 길이 체크
function validateMaxLength(targetObj, maxLength, editorAt) {
	// byte 체크
	if  ( !textMaxLengthCheck(targetObj, maxLength) )  {
		var errorMessage = targetObj.attr("title") + " 이/가 입력가능 문자수를 초과하였습니다.";

		if  ( errorMessage != "" )  {
			validateSetMessageEvent(targetObj, errorMessage, editorAt);

			return false;
		}
	}

	return true;
}

// 텍스트 최대 길이 체크
function textMaxLengthCheck(targetObj, maxLength) {
	// byte 계산
	var strByte = 0;
	for  ( var i = 0 ; i < targetObj.val().length; i++ )  {
		var charVal = escape(targetObj.val().charAt(i));
		if  ( charVal.length == 1 )  {
			strByte ++;
		}
		// 2 byte 문자 일 경우
		else if  ( charVal.indexOf("%u") != -1 )  {
			strByte += 2;
		}
		// 1 byte 문자 일 경우
		else if  ( charVal.indexOf("%") != -1 )  {
			strByte ++;
		}
	}

	// byte 체크
	if  ( Number(strByte) > Number(maxLength) )  {
		return false;
	}

	return true;
}

// 첨부파일 밸리데이션 체크
function validateFileElement(id, strFileNm) {
	data = $("[id^="+ id +"]");

	// 입력 여부 체크
	var valCheck = false;
	$.each(data, function(i, v) {
		if  ( ($(this).next(".clsFileNm").length > 0 && $.trim($(this).next(".clsFileNm").text()) != "") || $(this).val() != "" )  {
			valCheck = true;
			return false;
		}
	});

	// 입력 여부 불합격 시
	if  ( !valCheck )  {
		var messageTarget = data.closest(".fileForm").nextAll("small.error-message");

		var errorMessage = strFileNm + " 파일을 업로드해주세요.";
		if  ( messageTarget.length > 0 )  {
			messageTarget.html(errorMessage);
			messageTarget.show();
			data.eq("0").focus();

			$(data).on("change", function(e) {
				messageTarget.empty(); messageTarget.hide();
			});
		}
		else  {
			alert(errorMessage);
		}

		return false;
	}

	return true;
}

/**
 * 메일형식 유효성 체크
 * @return 올바른 메일형식일 경우 true, 아닐경우 false 리턴
 * @type Boolean
 */
function isEmail(str) {
    return (/^\w+((-|\.)\w+)*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]{2,4}$/.test(str));
}

/**
 * 전화번호형식 유효성 체크
 * @return 올바른 전화번호형식일 경우 true, 아닐경우 false 리턴
 * @type Boolean
 */
function isPhoneNo(str) {
    return (/^\d{2,3}-\d{3,4}-\d{4}$/.test(str));
}

/**
 * 핸드폰번호형식 유효성 체크
 * @return 올바른 핸드폰번호형식일 경우 true, 아닐경우 false 리턴
 * @type Boolean
 */
function isHandPhoneNo(str) {
    return (/^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$/.test(str));
}

// 숫자 정수형 유효성 체크
function isNumber(str) {
	return (/^[0-9]*$/).test(str);
}

// 숫자 실수형 유효성 체크
function isDouble(str) {
	var errorMessage = "";
	if  ( str == "" )  {
		errorMessage = "데이터가 없습니다.";
	}
	else if  ( str.substr(str.length - 1) == "." )  {
		errorMessage = "올바른 숫자를 입력해주세요.";
	}
	else if  ( isNaN(Number(str)) )  {
		errorMessage = "숫자만 입력할 수 있습니다.";
	}

	return errorMessage;
}

// IP 유효성 체크
function isIp(str) {
	return (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/).test(str);
}

// 비밀번호 검증 체크
function passwordCheck(str) {
	var errorMessage = "";

	// 숫자
	var num = /[0-9]/g;
	// 영어 소문자
	var eng = /[a-z]/ig;
	// 특수문자
	var spe = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

	// 연속되는 알파벳
	var continuEng = /(abc)|(bcd)|(cde)|(def)|(efg)|(fgh)|(ghi)|(hij)|(ijk)|(jkl)|(klm)|(lmn)|(mno)|(nop)|(opq)|(pqr)|(qrs)|(rst)|(stu)|(tuv)|(uvw)|(vwx)|(wxy)|(xyz)/;
	// 연속되는 알파벳 대문자
	var continuUpperEng = /(ABC)|(BCD)|(CDE)|(DEF)|(EFG)|(FGH)|(GHI)|(HIJ)|(IJK)|(jkl)|(KLM)|(LMN)|(MNO)|(NOP)|(OPQ)|(PQR)|(QRS)|(RST)|(STU)|(TUV)|(UVW)|(VWX)|(WXY)|(XYZ)/;
	// 연속되는 숫자
	var continuNumber = /(012)|(123)|(234)|(345)|(456)|(567)|(678)|(789)/;
	// 같은 문자 3개
	var continuSameText = /(\w)\1\1/;

	// 8자 이상 20자 이하 체크
	if  ( str.length < 8 || str.length > 20 )  {
		errorMessage = "8자리 ~ 20자리 이내로 입력해주세요.";
	}
	else if  ( str.search(/\s/) != -1 )  {
		errorMessage = "공백을 사용할 수 없습니다.";
	}
	else if  ( str.search(num) < 0 || str.search(eng) < 0 || str.search(spe) < 0 )  {
		errorMessage = "영문, 숫자, 특수문자를 혼합하여 입력해주세요.";
	}
	else if  ( continuSameText.test(str) )  {
		errorMessage = "같은 문자를 3개 이상 붙여 사용할 수 없습니다.";
	}
	else if  ( continuEng.test(str) )  {
		errorMessage = "연속되는 문자를 3개 이상 붙여 사용할 수 없습니다.";
	}
	else if  ( continuUpperEng.test(str) )  {
		errorMessage = "연속되는 문자를 3개 이상 붙여 사용할 수 없습니다.";
	}
	else if  ( continuNumber.test(str) )  {
		errorMessage = "연속되는 문자를 3개 이상 붙여 사용할 수 없습니다.";
	}

	return errorMessage;

}

/**
* 사업자번호 형식 유효성 체크
* @return 올바른 사업자번호 형식일 경우 true, 아닐경우 false 리턴
* @type Boolean
*/
function isSaupNo(saupNo) {
   // bizID는 숫자만 10자리로 해서 문자열로 넘긴다.
	var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
	var tmpBizID, i, chkSum = 0, c2, remander;
	var bizID = saupNo.replace(/-/gi, '');

	for  ( i = 0; i <= 7; i++ )  {
		chkSum += checkID[i] * bizID.charAt(i);
	}
	c2 = "0" + (checkID[8] * bizID.charAt(8));
	c2 = c2.substring(c2.length - 2, c2.length);
	chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
	remander = (10 - (chkSum % 10)) % 10;

	if  ( Math.floor(bizID.charAt(9)) == remander )
		return true;
	else
		return false;
}

// 쿠키 가져오기
function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = "";
	if  ( start != -1 )  {
		start += cookieName.length;
		var end = cookieData.indexOf(";", start);
		if  ( end == -1 )  {
			end = cookieData.length;
		}
		cookieValue = cookieData.substring(start, end);
	}

	return unescape(cookieValue);
}

// 쿠키 저장
function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

// 쿠키 삭제
function delCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}


function unescapeHtml(str) {
	var escapeStr = str.replace(/&amp;/g, "&")
								.replace(/&gt;/g, ">")
								.replace(/&lt;/g, "<")
								.replace(/&quot;/g, "\"")
								.replace(/&#39;/g, "'")
								.replace(/&middot;/g, "·");

	return escapeStr;
}

// 검색 form element 값 초기화
function searchFormElementReset(obj, hiddenResetIdArr) {
	$(obj).find("input, textarea").not(":hidden").val("");
	$(obj).find("input, textarea").not(":hidden").next("label").removeClass("active");
	$(obj).find("select").not(":hidden").find("option:first").prop("selected", true);

	if  ( null != hiddenResetIdArr )  {
		var hiddenResetNm = hiddenResetIdArr.split(",");
		for  ( var i in hiddenResetNm )  {
			$(obj).find("#" + $.trim(hiddenResetNm[i])).val("");
		}
	}
}

// 타이틀 label 존재하는 element active 기능 적용
function elementLabelActive() {
	$("input+label, textarea+label").prev("input, textarea").focus(function() {
		$(this).next("label").addClass("active");
	});

	$("input+label, textarea+label").prev("input, textarea").blur(function() {
		if  ( $.trim($(this).val()) == "" )  {
			$(this).next("label").removeClass("active");
		}
	});
}