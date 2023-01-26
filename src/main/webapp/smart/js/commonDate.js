/**
 * 선택된 objs에 DatePicker를 적용시킨다.
 * @param obj : 선택된 objs
 */
try {
	document.execCommand("BackgroundImageCache", false, true);
} catch (e) {
}

/**
 * 해당 objs에 DatePicker 적용(objs옆에 img달력 추가)
 * function(objs:적용할 object, dformat:데이터포맷, param_maxlength:데이터 총길이,imgPath:이미지 경로)
 * */
DefaultDatePicker = function(objs, dformat, param_maxlength, imagePath)
{
    
    var maxlength = param_maxlength ? param_maxlength : 8;
//    var width = param_width ? param_width : 50;
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
    var day = ("0" + myDate.getDate()).slice(-2);
    
    objs.prop("maxlength", maxlength).datepicker({
            changeYear : true,
            changeMonth: true,
            dateFormat : dformat,
            yearSuffix : "",
            monthNames : ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            monthNamesShort : ['1','2','3','4','5','6','7','8','9','10','11','12'],
            dayNamesMin : ["일", "월", "화", "수", "목", "금", "토"],
            showMonthAfterYear : true,
            showOtherMonths: true,
            selectOtherMonths: true,
            showAnim : 'fold',
            yearRange: "c-10:c+10",
            showOn: "button",
            buttonImageOnly: true,
            buttonImage: imagePath+"/board/bl_calendar.gif",
            buttonText: "달력",
            beforeShow: function() {
                setTimeout(function(){
                    $('.ui-datepicker').css('z-index', 1000);
                }, 0);
            }
        });
};


/**
 * 날짜비교 (시작일과 종료일 비교)
 * */
fn_dateCompare = function (sDate,eDate){

	if( sDate && eDate ){
        
        if( sDate > eDate) {
            alert("시작일자는 종료일자과 같거나 이전일자여야 합니다.");
            return false;
        }
	}
	return true;
};
