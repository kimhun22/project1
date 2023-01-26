/** @define {boolean} */
var DEBUG = true;

//global 변수들
var rval="";
var timer=null;
var _checkIdValue = [];
var _checkUserNameValue = [];
var _checkgroupNameValue = [];
var _checkTeamIdValue = [];
var _checkTeamNameValue = [];
var _checkCnt = 0;

checker = function (obj,count,textlimitName){
    if(rval != obj.value){
        if(textlimitName && document.getElementById(textlimitName) ){
            document.getElementById(textlimitName).innerHTML = obj.value.bytes();
        }
        rval = obj.value;
    }
    if (obj.value.bytes() > count){
        alert("최대 " + count + "byte이므로 초과된 글자수는 자동으로 삭제됩니다.");
        obj.value = obj.value.cut(count, '');
        stopchecker();
    }
    if(textlimitName){
        timer = setTimeout(function(){checker(obj,count,textlimitName);},10);
    }else{
        timer = setTimeout(function(){checker(obj,count);},10);
    }
};

stopchecker = function (){
    clearTimeout(timer);
    //timer = null;
};

String.prototype.bytes = function()
{
    var str = this;
    var l = 0;
    for (var i=0; i<str.length; i++)
        l += (str.charCodeAt(i) > 128) ? 2 : 1;

    return l;
};

String.prototype.cut = function(len, tail)
{
    if(tail == null){tail = '...';}
    var str = this;
    var l = 0;
    for (var i=0; i<str.length; i++)
    {
        l += (str.charCodeAt(i) > 128) ? 2 : 1;
        if (l > len) return str.substring(0,i) + tail;
    }
    return str;
};

fnPopupOpen = function(_url, _name, _width, _height, _scroll, _resizable, _status) {

	var _tarurl =  typeof(_url) == "object" ?_url.href : _url;
	_width = _width || 600;
	_height = _height || 450;
	_name = _name || "Pop";
	var _Opts = "";
	_Opts += "width="+_width;
	_Opts += ",height="+_height;
	var left = (screen.availWidth - _width )/2;
	var top = (screen.availHeight - _height )/2;
	_Opts += ",left="+left;
	_Opts += ",top="+top;

	_Opts += ",scrollbars="+(_scroll?_scroll:'no');
	_Opts += ",resizable="+(_resizable?_resizable:'no');
	_Opts += ",status="+(_status?_status:'no');

	var newWin = window.open(_tarurl , _name , _Opts);
	return newWin;
};

//여백제거
trim = function(str) {
	str = str.replace(/(^\s*)|(\s*$)/gi, "");
	return str;
};

/**
 * 모든 체크박스 체크
 * parameter => obj : this
 *              eObj: 체크박스 객체 name
 *
 * */
fn_AllCheck = function( obj , eObj ) {
    var _checkbox = document.getElementsByName(eObj);
    var _flag = false;
    if( obj.checked ){
        _flag = true;
    }
    for(var i=0;i<_checkbox.length;i++){
        _checkbox[i].checked = _flag;
    }

};

/**
 * 페이지 이동함수
 * parameter => form:폼
 *              pageNo:pageIndex
 *              action:URL
 * */
_fn_paging = function(frm, pageNo, actUrl) {

    if( actUrl != ""){
        frm.action = actUrl;
    }
    frm.pageIndex.value = pageNo;
    frm.submit();

};

/**
 * 날짜 검색시 두 날짜 비교(두 날짜를 모두 입력해서 검색하는 경우에만 사용)
 * parameter => sDate: 검색시작일자
 *              eDate: 검색종료일자
 * */
_compareTwoDate = function(sDate, eDate) {

    var sDt, eDt;

    if( sDate || eDate ){

        if( sDate && eDate ){

            sDt = new Date(sDate);
            eDt = new Date(eDate);

            if( sDt > eDt) {
                alert("시작일자는 종료일자보다 이전일자여야 합니다.");
                return false;
            }
            return true;
        } else {
            alert("시작일과 종료일을 넣어 주시기 바랍니다.");
            return false;

        }
    } else {
        return true;
    }

};

/**
 * 검색어를 초기화 시킨다. selector는 ','로 구분한다.
 * parameter => selector: 초기화할 대상 selector
 *              target  : 초기화할 대상 type
 *              hiddenYn: hidden객체 초기화 여부
 * */
_initSearch = function(selector, target, hiddenYn) {
    //try {   console.log("_initSearch("+selector+","+target+","+hiddenYn+")");   } catch (e) {console.log("error")};
    var el = $(selector).find(target);
    var elType = "";
    var elName = "";

    $(el).each(function(idx){

        elType = $(this).attr("type");
        elName = this.nodeName; //this.tagName;
        if ( elType !== undefined) { elType = elType.toLowerCase(); };
        if ( elName !== undefined) { elName = elName.toLowerCase(); };

        if( elName == "select" ) {

            $(this).find("option:nth-child(1)").attr("selected", true);
            return true;

        } else if ( elName == "input" ) {

            if( elType == "text" ) {
                $(this).val("");
            } else if ( elType == "hidden" && hiddenYn == "Y") {
                $(this).val("");
            } else if ( elType == "radio" ) {
                $("[name='" + $(this).attr("name") + "']:nth-child(1)").attr("checked", true);
            }

        }

    });
};


/**
 * 검색어를 초기화 시킨다. except는 '@'를 양쪽에 감싸고 '@'로 구분한다.
 * parameter => targetId: 초기화할 대상 ID
 *              except: 초기화 예외 객체 ID (@pageIndex@searchKeywordFrom@searchKeywordTo@)
 * */
fn_searchInit = function(targetId, except) {

    var el = $("#"+targetId).find("input,select,textarea");
    var elType = "";
    var elName = "";

    $(el).each(function(idx){

        elName = this.nodeName;
        if ( elName !== undefined) { elName = elName.toLowerCase(); };
        var elId = $(this).attr("id");
        if(elId == "pageIndex" || elId == "sortSubject" || elId == "sortDescend" || except.indexOf("@"+elId+"@") != -1){
        	return true;
        }

        if( elName == "select" ) {
            $(this).find("option:nth-child(1)").attr("selected", true);
        } else if ( elName == "input" ) {
        	elType = $(this).attr("type");
            if ( elType !== undefined) { elType = elType.toLowerCase(); };
            if(elType == "text" || elType == "hidden") {
                $(this).val("");
            } else if ( elType == "radio" ) {
                $("[name='" + $(this).attr("name") + "']:nth-child(1)").attr("checked", true);
            } else if ( elType == "checkbox" ) {
	            $(this).attr("checked", false);
	        }
        }else if( elName == "textarea" ) {
        	$(this).val("");
        }
    });
};

/**
 * a태그의 href실행
 * ex) <a href="${basePath}/home/r/m/selectList.do" onclick="javascript:_jsHref(this); return false;">
 * */
function _jsHref(obj) {
    document.location.href = obj.href;
}


/*
 * ajax 사용시 로딩바 적용
 */

function showLoadingBar()
{
	try {
		$("#overlay").css({width   : $(document).outerWidth(), height  : $(document).outerHeight()});
		$("#img-load").css({top : ($(document).scrollTop() + ($(window).height()) / 2 - 50), left : (document.body.clientWidth / 2 - 50)});
		$("#overlay").fadeIn();
   		document.getElementById('img-load').src='/smart/images/ajax-loader.gif';
   		//*** Let's make the image visible ***
   		document.getElementById('img-load').style.visibility = 'visible';
	} catch(e) {

	};
};

/*
 * ajax 사용시 로딩바 숨기기
 */
function hideLoadingBar()
{
	$("#overlay").fadeOut();
};

/**
 * 1. 개요 : 테이블에 행추가
 * 2. 처리내용 :
 * 		tableObjId 테이블에 templateTableObjId 테이블(hidden으로 설정)의 Row를 추가
 *		position : 'first' 첫행에 추가, 'last' 마지막행에 추가
 *  	사용 예) <input type="button" onclick="javascript:addRow('table1','templateTable1','last');" />
 */
addRow = function(tableObjId,templateTableObjId,position) {
	var trObj = "";
	if(position == null || position == '' || position == 'first'){
		trObj=$("#"+templateTableObjId+" > tbody > tr").clone(true).prependTo($("#" + tableObjId + " > tbody"));
	} else if(position == 'last'){
		trObj=$("#"+templateTableObjId+" > tbody > tr").clone(true).appendTo($("#" + tableObjId + " > tbody"));
	}
};

/**
 * 테이블 row병합
 *
 */
$.fn.rowspan = function(colIdx, isStats) {
    return this.each(function(){
        var that;
        $('tr', this).each(function(row) {
            $('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {

                if ($(this).html() == $(that).html()
                    && (!isStats
                            || isStats && $(this).prev().html() == $(that).prev().html()
                            )
                    ) {
                    rowspan = $(that).attr("rowspan") || 1;
                    rowspan = Number(rowspan)+1;

                    $(that).attr("rowspan",rowspan);

                    // do your action for the colspan cell here
                    $(this).hide();

                    //$(this).remove();
                    // do your action for the old cell here

                } else {
                    that = this;
                }

                // set the that if not already set
                that = (that == null) ? this : that;
            });
        });
    });
};

//첨부파일 확장자 체크
fn_fileExtChk = function(fileNm) {
    var extention = fileNm.split(".");
    var ext = extention[(extention.length-1)].toLowerCase();
    var fChk = false;
    if( ext == 'xls' ||
        ext == 'xlsx' ||
        ext == 'doc' ||
        ext == 'docx' ||
        ext == 'hwp' ||
        ext == 'ppt' ||
        ext == 'pptx' ||
        ext == 'txt' ||
        ext == 'gif' ||
        ext == 'jpg' ||
        ext == 'jpeg' ||
        ext == 'png' ||
        ext == 'tar' ||
        ext == 'war' ||
        ext == 'zip' ||
        ext == 'jar' ||
        ext == 'bmp' ||
        ext == 'pdf' ){
        fChk = true;
    }
    return fChk;
}

/**
 * 검색화면 기간 자동표기(1주일, 1개월, 3개월, 6개월)
 * @param type = 표기할 날짜(d7, m1, m3, m6)
 */
fn_period = function(type) {
	var nowDt = new Date();
	var vDate = null;

	if ((type == '-d7') || (type == '-m1') || (type == '-m3') || (type == '-m6')){
		vDate = $("#startDate").val();
	} else {
		vDate = $("#endDate").val();
	}
	var date = "";
	if(!vDate){
		if ((type == '-d7') || (type == '-m1') || (type == '-m3') || (type == '-m6')){
			$("#startDate").val(nowDt.getFullYear() + '-' + prependZero(nowDt.getMonth()+1,2) + '-' + prependZero(nowDt.getDate(),2));
		} else {
			$("#endDate").val(nowDt.getFullYear() + '-' + prependZero(nowDt.getMonth()+1,2) + '-' + prependZero(nowDt.getDate(),2));
		}
	}
	if ((type == '-d7') || (type == '-m1') || (type == '-m3') || (type == '-m6')){
		date = $("#startDate").val().replace(/\/|\-/g, "");
	} else {
		date = $("#endDate").val().replace(/\/|\-/g, "");
	}

	var yy = parseInt(date.substr(0, 4), 10);
	var mm = parseInt(date.substr(4, 2), 10);
	var dd = parseInt(date.substr(6, 2), 10);
	var endDt = new Date(yy, mm -1, dd);

	switch(type){
		case 'd7':
			endDt.setDate(endDt.getDate() - 7);
			$('#startDate').val(endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2));
			break;
		case 'm1':
			endDt.setMonth(endDt.getMonth() - 1);
			$('#startDate').val(endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2));
			break;
		case 'm3':
			endDt.setMonth(endDt.getMonth() - 3);
			$('#startDate').val(endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2));
			break;
		case 'm6':
			endDt.setMonth(endDt.getMonth() - 6);
			$('#startDate').val(endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2));
			break;
			// 현재부터 이후로...
		case '-d7':
			endDt.setDate(endDt.getDate() + 7);
			$('#endDate').val(endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2));
			break;
		case '-m1':
			endDt.setMonth(endDt.getMonth() + 1);
			$('#endDate').val(endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2));
			break;
		case '-m3':
			endDt.setMonth(endDt.getMonth() + 3);
			var str = endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2);
			$('#endDate').val(str);
			break;
		case '-m6':
			endDt.setMonth(endDt.getMonth() + 6);
			$('#endDate').val(endDt.getFullYear() + '-' + prependZero(endDt.getMonth()+1,2) + '-' + prependZero(endDt.getDate(),2));
			break;
		default:
			break;
	}
};

/**
 * 지정한 수만큼 빈값에 0 채우기
 * @param num :  기본값
 * @param len : 지정한 수
 * @returns {String}
 */
prependZero = function(num, len) {
//	if (num == null || num == undefined) num = 0;
	while(num.toString().length < len) {
		num = "0" + num;
	}
	return num;
};

/**
 * 부서 Layer 팝업
 * @param id :  부서 Code를 입력 받을 아이디
 * @param name : 부서 Name을 입력 받을 아이디
 * @returns {String}
 */
fn_departmentChoice = function(id, name) {
	var chkType = $("[name=radioDept]").is(":checked");
	var chkVal = $("[name=radioDept]:checked").val();

	if(chkType == false){
		$("#"+id+"").val("");
		$("#"+name+"").text("");
	}else{
		$("#"+id+"").val(chkVal.split(";;")[0]);
		$("#"+name+"").text(chkVal.split(";;")[1]);
	}

	$.unblockUI();
	$("#windowPopup").empty();
}

/**
 * 부서 Window 팝업 - 선택
 * @param id :  Code를 입력 받을 아이디
 * @param name : Name을 입력 받을 아이디
 * @returns {String}
 */
fn_departmentWindowChoice = function(id, name) {
	var deptIdArray = new Array;
	var deptNameArray = new Array;
	$('[name=checkboxDept]:checked').each(function() {
		deptIdArray.push($(this).val());
		deptNameArray.push($(this).attr("partName"));
	});

	$("#"+id+"", opener.document).val(deptIdArray);
	$("#"+name+"", opener.document).text(deptNameArray);

	window.open("","_self").close();
};

/**
 * 팀별 Layer 팝업
 * @param _nameId : 팀 Name을 입력 받을 아이디
 * @param _codeId : 팀 Code를 입력 받을 아이디
 * @param _inputType : radio, checkbox 구분
 * @param _basePath : basePath
 * @returns {String}
 */
fn_teamLayer = function(_nameId, _codeId, _inputType, _basePath) {
	$.ajax({
		url : _basePath + "/common/a/n/teamPop.do",
		data : {
			codeId : _codeId,
			nameId : _nameId,
			inputType : _inputType
		}
	}).done(function(res) {
		$("#windowPopup").html(res);
		_checkCnt = 0;
	});

	var popWidth  = '480'; // 팝업사이즈 너비
	var popHeight = '650'; // 팝업사이즈 높이
	var popLeft = ( $(window).scrollLeft() + ($(window).width() - popWidth) / 2 );
	var popTop = ( $(window).scrollTop() + ($(window).height() - popHeight) / 2 );

	$("#windowPopup").css({width : popWidth, height : popHeight});
	$.blockUI({message:$("#windowPopup"),css:{width:"0px",height:"0px",left:popLeft,top:popTop}});
};

/**
 * 팀별 Layer 팝업 - 선택
 * @param id :  팀별 Code를 입력 받을 아이디
 * @param name : 팀별 Name을 입력 받을 아이디
 * @returns {String}
 */
fn_teamChoice = function(id, name) {
	var teamIdArray = new Array;
	var teamNameArray = new Array;
	$('[name=popTeam]:checked').each(function() {
		teamIdArray.push($(this).val());
		teamNameArray.push($(this).attr("fullName"));
	});

	if($("#inputTypeChk").val() == "true") {
		if(_checkCnt <= 0) {
			_checkIdValue = [];
			_checkUserNameValue = [];
		}
		$("#"+id+"").val(_checkIdValue);
		$("#"+name+"").text(_checkUserNameValue);
	} else {
		$("#"+id+"").val(teamIdArray);
		$("#"+name+"").text(teamNameArray);
	}

	$.unblockUI();
	$("#windowPopup").empty();
};

/**
 * 팀별 Window 팝업 - 선택
 * @param id :  팀별 Code를 입력 받을 아이디
 * @param name : 팀별 Name을 입력 받을 아이디
 * @returns {String}
 */
fn_teamWindowChoice = function(id, name) {
	var teamIdArray = new Array;
	var teamNameArray = new Array;
	$('[name=popTeam]:checked').each(function() {
		teamIdArray.push($(this).val());
		teamNameArray.push($(this).attr("fullName"));
	});

	$("#"+id+"", opener.document).val(teamIdArray);
	$("#"+name+"", opener.document).text(teamNameArray);

	window.open("","_self").close();
};

/**
 * 유저 Layer 팝업
 * @param _nameId :  유저 Name을 입력 받을 아이디
 * @param _codeId : 유저 Code를 입력 받을 아이디
 * @param _inputType : radio, checkbox 구분
 * @param _basePath : basePath
 * @returns {String}
 */
fn_userLayer = function(_nameId, _codeId, _inputType, _basePath) {

	if(parent != null) {
		$.ajax({
			url : _basePath + "/common/a/n/userPop.do",
			data : {
				codeId : _codeId,
				nameId : _nameId,
				inputType : _inputType,
				vr : arguments[4]
			}
		}).done(function(res) {
			parent.$("#windowPopup").html(res);
			_checkCnt = 0;
		});

		var popWidth  = ($(parent.window).width()  > 580) ? 580 : $(parent.window).width() -20; // 팝업사이즈 너비
		var popHeight = ($(parent.window).height() > 670) ? 670 : $(parent.window).height()-20; // 팝업사이즈 높이

		parent.$("#windowPopup").css({width: popWidth, height: popHeight});
	    var popLeft = ( ($(parent.window).width() - parent.$("#windowPopup").outerWidth() ) / 2 + $(parent.window).scrollLeft() );
		var popTop  = ( ($(parent.window).height()- parent.$("#windowPopup").outerHeight()) / 2 + $(parent.window).scrollTop()  );

		parent.$.blockUI({message:parent.$("#windowPopup"),css:{width:"0px", height:"0px", left:popLeft, top:popTop}});
	}
	else {
//		console.log("undefined="+arguments[4]);
		$.ajax({
			url : _basePath + "/common/a/n/userPop.do",
			data : {
				codeId : _codeId,
				nameId : _nameId,
				inputType : _inputType,
				vr : arguments[4]
			}
		}).done(function(res) {
			$("#windowPopup").html(res);
			_checkCnt = 0;
		});

		var popWidth  = '580'; // 팝업사이즈 너비
		var popHeight = '670'; // 팝업사이즈 높이
		var popLeft = ( $(window).scrollLeft() + ($(window).width() - popWidth) / 2 );
		var popTop = ( $(window).scrollTop() + ($(window).height() - popHeight) / 2 );

		$("#windowPopup").css({width : popWidth, height : popHeight});
		$.blockUI({message:$("#windowPopup"),css:{width:"0px",height:"0px",left:popLeft,top:popTop}});
	}
};

/**
 * 유저 Layer 팝업 - 선택
 * @param id :  유저 Code를 입력 받을 아이디
 * @param name : 유저 Name을 입력 받을 아이디
 * @returns {String}
 */
fn_userChoice = function(id, name) {
	console.log('fn_userChoice', arguments);
	var userIdArray = new Array;
	var userNameArray = new Array;
	$('[name=checkboxUser]:checked').each(function() {
		userIdArray.push($(this).val());
		userNameArray.push($(this).attr("userName"));
	});

	if($("#inputTypeChk").val() == "true") {
		if(_checkCnt <= 0) {
			_checkIdValue = [];
			_checkUserNameValue = [];
		}
		$("#"+id+"").val(_checkIdValue);
		$("#"+name+"").text(_checkUserNameValue);
		//vr일 경우만 유저명을 줄바꿈표기함
		if(arguments[2]!=undefined){
//			console.log(common_fn.fn_isnull($("#"+name+"").text().match(/,/g),"").length);
			$('#user_totalCnt').text($("#"+name+"").text()!="" ? common_fn.fn_isnull($("#"+name+"").text().match(/,/g),"").length+1 : 0);
			$("#"+name+"").html($("#"+name+"").text().replace(/,/g,"<br/>"));
		}
	} else {
		$("#"+id+"").val(userIdArray);
		$("#"+name+"").text(userNameArray);
	}

	$.unblockUI();
	$("#windowPopup").empty();
	console.log('fn_userChoice.exit');
};

/**
 * 유저 Window 팝업 - 선택
 * @param id :  Code를 입력 받을 아이디
 * @param name : Name을 입력 받을 아이디
 * @returns {String}
 */
fn_userWindowChoice = function(id, name) {
	var userIdArray = new Array;
	var userNameArray = new Array;
	$('[name=checkboxUser]:checked').each(function() {
		userIdArray.push($(this).val());
		userNameArray.push($(this).attr("userName"));
	});

	$("#"+id+"", opener.document).val(userIdArray);
	$("#"+name+"", opener.document).text(userNameArray);

	window.open("","_self").close();
};

/**
 * PNU Layer 팝업
 * @param _nameId :  PNU Name을 입력 받을 아이디
 * @param _codeId : PNU Code를 입력 받을 아이디
 * @param _basePath : basePath
 * @returns {String}
 */
fn_lawAddress = function(_nameId, _codeId, _basePath,_frameId) {
	$.ajax({
		url : _basePath + "/system/code/lawAddressPop.do",
		data : {
			codeId : _codeId,
			nameId : _nameId
		}
	}).done(function(res) {
		$("#windowPopup").html(res);
		//parent.resizeIframe(_frameId);
	});

	var popWidth  = '480'; // 팝업사이즈 너비
	var popHeight = '650'; // 팝업사이즈 높이
	var popLeft = ( $(window).scrollLeft() + ($(window).width() - popWidth) / 2 );
	var popTop = ( $(window).scrollTop() + ($(window).height() - popHeight) / 2 );

	$("#windowPopup").css({width : popWidth, height : popHeight});
	$.blockUI({message:$("#windowPopup"),css:{width:"0px",height:"0px",left:popLeft,top:popTop}});
};

/**
 * PNU Layer 팝업 - 선택
 * @param id :  PNU Code를 입력 받을 아이디
 * @param name : PNU Name을 입력 받을 아이디
 * @returns {String}
 */
fn_lawAddressChoice = function(id, name) {
	var pnuIdArray = new Array;
	var pnuNameArray = new Array;
	$('[name=lawAddressChoice]:checked').each(function() {
		pnuIdArray.push($(this).val());
		pnuNameArray.push($(this).attr("pnuName"));
	});

	$("#"+id+"").val(pnuIdArray);
	$("#"+name+"").val(pnuNameArray);

	$.unblockUI();
	$("#windowPopup").empty();
};

/**
 * 숫자만 입력
 * @param event :  event
 * @param obj : this
 * @param leng : maxlength size
 * @returns {String}
 * *************예제**************** : onkeydown="return fn_comOnlyNumber(event, this, 4);"
 * *************필독**************** : ie이외에 브라우저는 ime-mode가 안먹히므로 input에 onkeyup 적용.
 * *************예제**************** : onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"
 */
fn_comOnlyNumber = function(event, obj, leng){
	$(obj).attr("maxlength", leng);
	$(obj).css("ime-mode", "disabled");

	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 9)
		return;
	else
		return false;
}

$(function(){
	var $iFrm = $('<IFRAME id="iFrm" frameBorder="1" name="iFrm" scrolling="no" src="" style="display:none"></IFRAME>');
	$iFrm.appendTo('body');
});

common_fn = {

	//숫자 콤마
	numberWithCommas:function(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	},

	//스크롤 시 fixed되는 문제를 해결하기 위한 스크립트 추가
	fn_scroll:function(obj, def_top){
		var top = $(window).scrollTop();
		if(top>=def_top){
			top = -1000;
		}
		var totalcnt_top = def_top - top;
//		console.log(obj.css('top'));
		obj.css('top',totalcnt_top+"px");
	},
	//엑셀 다운로드
	fn_excel:function(url,form){
		showLoadingBar();
		var param = $('#'+form).serialize();
//		console.log(param);
		var vUrl = url+"?"+param;
		$('#iFrm').attr('src',vUrl);
		common_fn.fn_LoadingComplate();
	},

	fn_LoadingComplate:function(){
		$.ajax({
			url : "LoadingComplate.do"
			,dataType:"text"
			, method:"post"
			,success : function(res) {
				if(res== "complate"){
					hideLoadingBar();
				}else{
					setTimeout("common_fn.fn_LoadingComplate()",1000);
				}
			}, error : function(res){
				hideLoadingBar();
			}

		});
	},

	fn_isnull:function(obj, val){
		if(typeof obj != "undefined" && obj == null){
			obj = val;
		}
		return obj;
	},
	//그리드 조회
	fn_search:function(myGrid, vUrl, vParam, func){
		showLoadingBar();
		$.ajax({
			url : vUrl
			,dataType : "json"
			,async : false
			,method : "POST"
			,data : "type=select&"+vParam
			,success : function(res) {
				myGrid.clearAll(false);
				myGrid.parse(res.jsonData,func,"js");
			}
			,complete : function(res){
				hideLoadingBar();
			},
			error : function(res){
				hideLoadingBar();
			}
		});
	},
	//등록할 내용 취합
	fn_GridInsertData:function(myGrid){
		var insertListArr = new Array();
		if(myGrid.insertRowNoArr.length > 0){
			var col_len = myGrid.getColumnsNum();
			var col_value = "";
			var insertRowNo = myGrid.insertRowNoArr.toString().split(",");
			for(var i=0; i<insertRowNo.length; i++){
				var insertListStr = {};
				for(var grid_i=0;grid_i<col_len;grid_i++){
					if(myGrid.getColType(grid_i) == "ron" || myGrid.getColType(grid_i) == "edn"){
						col_value = myGrid.cells(insertRowNo[i], grid_i).getValue().length == 0 ? 0 : myGrid.cells(insertRowNo[i], grid_i).getValue();
					}else{
						col_value = myGrid.cells(insertRowNo[i], grid_i).getValue().length == 0 ? "" : myGrid.cells(insertRowNo[i], grid_i).getValue();
						col_value = col_value.replace(/\n/g, "");
						col_value = col_value.replace(/\r/g, "");

					}

					eval("insertListStr." + myGrid.getColumnId(grid_i)+"="+"\""+col_value+"\";");
				}
				insertListArr.push(insertListStr);
			}
		}
		return insertListArr;
	},
	//수정할 내용 취합
	fn_GridUpdateData:function(myGrid){
		var _updateId = myGrid.getChangedRows() // 수정된 로우
		var updateId = _updateId.toString().split(",");

	 	// 수정된 로우 ID값 안에 새로 추가된 로우ID값까지 포함되기때문에 추가된 로우ID를 제외하고 수정된 로우ID만 updateRowNoArr에 담음 + 중복제거
	 	for(var i=0; i<updateId.length; i++){
	 		if(parseInt(updateId[i]) <= parseInt(myGrid._lastRowNo)){
	 			if(myGrid.updateRowNoArr.indexOf(updateId[i]) < 0){
	 				myGrid.updateRowNoArr.push(parseInt(updateId[i]));
	 			}
	 		}
	 	}

		var updateListArr = new Array();
		if(myGrid.updateRowNoArr.length > 0){
			var col_len = myGrid.getColumnsNum();
			var col_value = "";
			var updateRowNo = myGrid.updateRowNoArr.toString().split(",");
			for(var i=0; i<updateRowNo.length; i++){
				var updateListStr = {};
				for(var grid_i=0;grid_i<col_len;grid_i++){

					if(myGrid.getColType(grid_i) == "ron" || myGrid.getColType(grid_i) == "edn"){
						col_value = myGrid.cells(updateRowNo[i], grid_i).getValue().length == 0 ? 0 : myGrid.cells(updateRowNo[i], grid_i).getValue();
					}else{
						col_value = myGrid.cells(updateRowNo[i], grid_i).getValue().length == 0 ? "" : myGrid.cells(updateRowNo[i], grid_i).getValue();
						col_value = col_value.replace(/\n/g, "");
						col_value = col_value.replace(/\r/g, "");
					}
					eval("updateListStr." + myGrid.getColumnId(grid_i)+"="+"\""+col_value+"\";");
				}
				updateListArr.push(updateListStr);
			}
		}
		return updateListArr;
	},
	//삭제할 내용 취합
	fn_GridDeleteData:function(myGrid){
		var deleteListArr = new Array();
		if(myGrid.deleteRowNoArr.length > 0){
			var col_len = myGrid.getColumnsNum();
			var col_value = "";
			var deleteRowNo = myGrid.deleteRowNoArr.toString().split(",");
			for(var i=0; i<deleteRowNo.length; i++){
				var deleteListStr = {};
				for(var grid_i=0;grid_i<col_len;grid_i++){
					if(myGrid.getColType(grid_i) == "ron" || myGrid.getColType(grid_i) == "edn"){
						col_value = myGrid.cells(deleteRowNo[i], grid_i).getValue().length == 0 ? 0 : myGrid.cells(deleteRowNo[i], grid_i).getValue();
					}else{
						col_value = myGrid.cells(deleteRowNo[i], grid_i).getValue().length == 0 ? "" : myGrid.cells(deleteRowNo[i], grid_i).getValue();
						col_value = col_value.replace(/\n/g, "");
						col_value = col_value.replace(/\r/g, "");
					}
					eval("deleteListStr." + myGrid.getColumnId(grid_i)+"="+"\""+col_value+"\";");
				}
				deleteListArr.push(deleteListStr);
			}
		}
		return deleteListArr;
	},

	//그리드 db저장 ajax
	fn_GridSaveData:function(obj,func){
		$.ajax({
			url: obj.url,
			type: 'POST',
			async: true,
			traditional : true,
			data : {
				insertListArr : obj.insertListArr != undefined ? JSON.stringify(obj.insertListArr) : JSON.stringify(null),
				updateListArr : obj.updateListArr != undefined ? JSON.stringify(obj.updateListArr) : JSON.stringify(null),
				deleteListArr : obj.deleteListArr != undefined ? JSON.stringify(obj.deleteListArr) : JSON.stringify(null)
			},
			beforeSend: function(){
				if(obj.insertListArr == null && obj.updateListArr == null && obj.deleteListArr == null){
					return false;
				}
				showLoadingBar();
			},
			error: function(){
				 alert("현재 조회 서비스가 원할하지 않습니다.\n잠시후 다시 이용해 주십시요.");
				 location.reload();
				 return;
			},
			success: function(r) {
				if (typeof(func) == "function") {
					func();
				}
				hideLoadingBar();
			}
		});
	},

	//그리드 초기화
	fn_GridInit:function(gridId, gridObj, imgpath){
		var myGrid = new dhtmlXGridObject(gridId);

		var _header = "";
		var _columnId = "";
		var _width = "";
		var _align = "";
		var _type = "";
		var _attachheader = [];
		var _sort = "";
		var gridlen = gridObj.length;
		for(var grid_i = 0; grid_i<gridlen;grid_i++){
			_header += _header == "" ? "" : ","; _header += gridObj[grid_i].header.replace(/,/g,'&#44;');
			_columnId += _columnId == "" ? "" : ","; _columnId += gridObj[grid_i].columnId;
			_width += _width == "" ? "" : ","; _width += gridObj[grid_i].width;
			_align += _align == "" ? "" : ","; _align += gridObj[grid_i].align;
			_type += _type == "" ? "" : ","; _type += gridObj[grid_i].type;

			if(gridObj[grid_i].hidden != undefined){
				myGrid.setColumnHidden(grid_i,gridObj[grid_i].hidden); // hidden
			}

			if(gridObj[grid_i].attachHeader != undefined){
				var _attachheader_split = gridObj[grid_i].attachHeader.split(",");
				var _attachheader_len = _attachheader_split.length;
				for(_attachheader_i=0;_attachheader_i<_attachheader_len;_attachheader_i++){
					_attachheader[_attachheader_i] += _attachheader[_attachheader_i] == "" ? "" : ",";
					_attachheader[_attachheader_i] += _attachheader_split[_attachheader_i];
				}
			}else{
				_attachheader[0] += ",";
			}

			if(gridObj[grid_i].sort != undefined){
				_sort += _sort == "" ? "" : ","; _sort += gridObj[grid_i].sort;
			}else{
				_sort +=  _sort == "" ? " " : ","; ;
			}

			if(gridObj[grid_i].format != undefined){
				if(gridObj[grid_i].type=="edn"){
					myGrid.setNumberFormat(gridObj[grid_i].format,grid_i,".",",");
				}else{
					myGrid.setDateFormat(gridObj[grid_i].format);
				}
			}
		}
		myGrid.setHeader(_header);
		var _attachheader_len = _attachheader.length;
		for(var _attachheader_i=0;_attachheader_i<_attachheader_len;_attachheader_i++){
			myGrid.attachHeader(_attachheader[_attachheader_i].replace("undefined,",""));
		}

		myGrid.setColumnIds(_columnId);
		myGrid.setInitWidths(_width);
		myGrid.setColAlign(_align);
		myGrid.setColTypes(_type);
		myGrid.setColSorting(_sort);
		myGrid.setStyle("font-weight:bold; text-align:center; color:#58595b; vertical-align:middle; font-family: Dotum,'돋음';", "font-family: Dotum,'돋음'; font-size: 12px; ", "", "");
		myGrid.enableMultiline(true); // 셀의 멀티라인
		myGrid.attachEvent("onXLE", function(grid_obj,count){
			myGrid._lastRowNo = myGrid.getRowsNum();
			myGrid.insertRowNoArr = new Array();
			myGrid.updateRowNoArr = new Array();
			myGrid.deleteRowNoArr = new Array();
			if($("#"+gridId+"_totalCnt").length < 1){
				var $iCnt = $('<div class="totalcnt" id="'+gridId+'_totalCnt">'+common_fn.numberWithCommas(myGrid._lastRowNo)+'</div>');
				$iCnt.prependTo($(".dhtmlx_grid"));
			}else{
				$("#"+gridId+"_totalCnt").text(common_fn.numberWithCommas(myGrid._lastRowNo));
			}
		});
		myGrid.attachEvent("onRowCreated", function(rId,rObj,rXml){
			for(var grid_i = 0; grid_i<gridlen;grid_i++){
				if(gridObj[grid_i].cssstyle != undefined){
					myGrid.setCellTextStyle(rId,grid_i,gridObj[grid_i].cssstyle);
				}
			}
		});

		myGrid._toExcel = function(url){
			myGrid.toExcel(url);
		};

		myGrid._lastRowNo = 0;
		myGrid.enableValidation(true);
		myGrid.insertRowNoArr = new Array();
		myGrid.updateRowNoArr = new Array();
		myGrid.deleteRowNoArr = new Array();
		myGrid.init();

		return myGrid;
	}

};
//항공사진
fn_airview = function(_pnuCode) {
	var title = "항공사진";
	var url = "/smartWorkNavi/work/r/n/work_airview.do?addr="+_pnuCode+"&serviceType=up";

	var popWidth  = '720'; // 팝업사이즈 너비
	var popHeight = '810'; // 팝업사이즈 높이
	var popLeft = (document.body.clientWidth / 2) - (popWidth / 2);
	popLeft += window.screenLeft;
	var popTop = (screen.availHeight / 2) - (popHeight / 2);

	window.open(url, title, "scrollbars=no, width="+popWidth+", height="+popHeight+", left="+popLeft+", top="+popTop);

	fn_appendLog("2190", "", "", "", _pnuCode, "http://upis.go.kr/upispweb/up/viewUpisList.do");
};

if(window.attachEvent && !window.addEventListener ) {
 	window.attachEvent("onunload", function() {
	 	console.log('window.onunload');
     	for ( var id in jQuery.cache ) {
         	if ( jQuery.cache[ id ].handle ) {
             	// Try/Catch is to handle iframes being unloaded, see #4280
             	try {
                 	jQuery.event.remove( jQuery.cache[ id ].handle.elem   );
             	} catch(e) {}
         	}
     	}
 	});
}

//addEventListener support for IE8
function bindEvent(element, eventName, eventHandler) {
    if (element.addEventListener){
        element.addEventListener(eventName, eventHandler, false);
    } else if (element.attachEvent) {
        element.attachEvent('on' + eventName, eventHandler);
    }
}

// 법령 조문 링크 Event
function fn_appendLink(link) {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	var contextPath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	$.ajax({
		url : contextPath + '/common/a/n/log.do'
		, data : { taskCode : '9999', remark: link }
	});
}

var linkHandler = function(e) {
	fn_appendLink(decodeURI(e.target.href));
}

jQuery(document).ready(function() {
	var docLinks = document.links;
	for (var i = 0 ; i < docLinks.length ; i++) {
		var docLink = docLinks[i];
		if (typeof(docLink.href) != 'undefined' && docLink.href.indexOf('http://www.law.go.kr') > -1) {
			docLink.onclick = linkHandler;
		}
	}

	window.open = function(open) {
		return function(url, name, features) {
			if (typeof(url) != 'undefined' && url.indexOf('http://www.law.go.kr') > -1) {
				fn_appendLink(url);
			}
			return open.call(window, url, name, features);
		}
	}(window.open);
});



function isElement($el) {
    if($el.length == 0) {
        return false;
    }
    return true;
}

//data값이 있으면 data출력 없으면 preset
function makeIfNull(data, preset) {
    if(data == null) {
        return preset;
    }
    return data;
}

//data값이 null이면 preset을 출력하고 있으면 value을 출력
function makeIfNotNullValue(data, value, preset) {
    if(data == null) {
        return preset;
    }
    return value;
}

// fn_blockUI 팝업
function fn_blockUI_popup(url, objData, objPopup, popWidth, popHeight, method, popLeft, popTop) {

    if(popLeft == 0 ) {
        popLeft = ( $(window).scrollLeft() + ($(window).width() - popWidth) / 2 );
        popTop  = ( $(window).scrollTop() + ($(window).height() - popHeight) / 2 );
    }

    if(method == '') method = 'GET';

    $.ajax({
        url : url,
        data: objData.serialize(),
        type: method
    }).done(function(res) {
        objPopup.html(res);
    });

    objPopup.css({width : popWidth, height : popHeight});
    $.blockUI({message:objPopup,css:{width:"0px",height:"0px",left:popLeft,top:popTop}});
}
