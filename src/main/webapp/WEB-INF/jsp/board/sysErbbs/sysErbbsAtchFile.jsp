<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<style>
    .clear{clear:both; padding-left: 10px;}
    .wrap>.fileBox{padding: 20px;}
    .fileBox input[type=text], textarea{width: 100%;}
    .fileBox textarea{resize:none;}
    .fileBox .fileBoard{display: inline-block;width: 700px;height: 75px;border: 1px solid #000;overflow: auto;}
    .fileBoard .sysErbbsFileList .fileName{padding-left: 20px;}
    .fileBoard .sysErbbsFileList .fileSize{padding-right: 20px; float:right;}
    .fileName input[type=checkbox] {margin-right: 5px;}
</style>

<%-- 첨부파일 --%>
<spring:eval var="sysErbbsmaxUploadSize" expression="@appConfig.getProperty('globals.upload.maxUploadSize')"></spring:eval>

<script type="text/javascript">
    //파일 정보를 담아 둘 배열
    var sysErbbsFileList = [];
    var atchFileSize = ${fn:length(atchFile)};
    var atchFileDeList = [];

    $(function(){
    	gfn_sysErbbsFileBoardDown(sysErbbsFileList, "${param.allowExt}", ${sysErbbsmaxUploadSize});

    	$('body').on('click', '#select-file-upload', function (event) {
    		$('#select-sysErbbsFile-input').trigger('click');
    	});
    	 $('#select-sysErbbsFile-input').change(function(e){
    		 fn_getFile(e);

    		 $("#select-sysErbbsFile-input").replaceWith( $("#select-sysErbbsFile-input").clone(true) );
    		 $("#select-sysErbbsFile-input").val('');
    	 });

    });

    /*첨부파일 삭제*/
    fn_sysErbbRemoveFile = function(event) {
        var sysErbbsFileList = $(event).closest(".sysErbbsFileList");
        var fileNo = sysErbbsFileList.data("fileno");
        var fileSn = sysErbbsFileList.data("filesn");

        if (fileSn != "") {
        	atchFileDeList.push(fileSn);
        	atchFileSize = atchFileSize - 1;
        } else {
            sysErbbsFileList.splice(sysErbbsFileList.index()-1, 1);
        }
        $(event).closest(".sysErbbsFileList").remove();
    }

    fn_getSysErbbsFileList = function() {
    	return sysErbbsFileList;
    }

    /* 첨부파일 초기화 */
    fn_sysErbbsResetFile = function(frm) {
    	sysErbbsFileList = [];
    	$(frm).find('.sysErbbsFileList').remove();
    }

    fn_getFile = function(e) {
    	var files;
    	if (e.target.files) {
    		files=e.target.files
    	} else if(e.originalEvent){
    		files=e.originalEvent.dataTransfer.files
    	} else {
    		files=e.dataTransfer.files
    	}
    	if (files.length==0) {
    	    return;
		}

        var file = files[0];
    	sysErbbsFileList.push(file);

    	var checkObject = gfn_sysErbbsDownFileCheck([file], '${param.allowExt}', ${sysErbbsmaxUploadSize});

        files = checkObject.files;
        if  ( files.length > 0 )  {
            var tag = "";
            for(i=0; i<files.length; i++){
                 var f = files[i];
                 var fileName = f.name;
                 var fileSize = f.size / 1024 / 1024;
                 fileSize = fileSize < 1 ? fileSize.toFixed(3) : fileSize.toFixed(1);
                 tag +=
                         "<div class='sysErbbsFileList' data-fileNo='' data-fileSn=''>" +
                             "<span class='fileName'>"+fileName+"</span>" +
                             "<span class='fileSize'>"+fileSize+" MB</span>" +
                             "<span class='clear'><i onclick='fn_sysErbbRemoveFile(this);' class='icon icon_del' style='cursor:pointer;'></i></span>" +
                         "</div>";
             }

             $('#sysErbbsFileBoard').append(tag);
        }

        if (checkObject.errorFileInfo != "") {
            window.alert(checkObject.errorFileInfo);
        }



    }

    //파일 드롭 다운
    gfn_sysErbbsFileBoardDown = function(sysErbbsFileList, allowFileType, maxUploadSize) {
        $("#sysErbbsFileBoard").on("dragenter", function(e){
            e.preventDefault();
            e.stopPropagation();
        }).on("dragover", function(e){
            e.preventDefault();
            e.stopPropagation();
            $(this).css("background-color", "#FFD8D8");
        }).on("dragleave", function(e){
            e.preventDefault();
            e.stopPropagation();
            $(this).css("background-color", "#FFF");
        }).on("drop", function(e){
            e.preventDefault();

            //fn_getFile(e);
            var files = e.originalEvent.dataTransfer.files;
            if(files != null && files != undefined){
               var checkObject = gfn_sysErbbsDownFileCheck(files, allowFileType, maxUploadSize);

               files = checkObject.files;
               if  ( files.length > 0 )  {
                   var tag = "";
                   for(i=0; i<files.length; i++){
                        var f = files[i];
                        sysErbbsFileList.push(f);
                        var fileName = f.name;
                        var fileSize = f.size / 1024 / 1024;
                        fileSize = fileSize < 1 ? fileSize.toFixed(3) : fileSize.toFixed(1);
                        tag +=
                                "<div class='sysErbbsFileList' data-fileNo='' data-fileSn=''>" +
                                    "<span class='fileName'>"+fileName+"</span>" +
                                    "<span class='fileSize'>"+fileSize+" MB</span>" +
                                    "<span class='clear'><i onclick='fn_sysErbbRemoveFile(this);' class='icon icon_del' style='cursor:pointer;'></i></span>" +
                                "</div>";
                    }

                    $(this).append(tag);
               }

               if (checkObject.errorFileInfo != "") {
                   window.alert(checkObject.errorFileInfo);
               }
            }

            $(this).css("background-color", "#FFF");
        });
    }

    gfn_sysErbbsDownFileCheck = function(files, allowFileType, maxUploadSize) {
       var checkFile = [];
       var errorFileInfo = "";
       var resultObject = {};
       for  ( var i = 0 ; i < files.length ; i++ )  {
           var fileNameArr = files[i].name.split("\.");
           var ext = fileNameArr[fileNameArr.length-1].toLowerCase();
           var fileSize = files[i].size;

           var allowFileTypeArry = allowFileType.split(",");
           if  ( $.inArray(ext, allowFileTypeArry) == -1 )  {
               errorFileInfo += ("\n- " + files[i].name + " : " + ext + " 확장자는 업로드를 할 수 없습니다.");
           }
           else if  ( fileSize > maxUploadSize )  {
               errorFileInfo += ("\n- " + files[i].name + " : " + "최대 업로드가능 용량(" + maxUploadSize + ")이 초과되었습니다.");
           }
           else  {
               checkFile.push(files[i]);
           }
       }

       resultObject.files = checkFile;
       resultObject.errorFileInfo = errorFileInfo;

       return resultObject;
    }

</script>
