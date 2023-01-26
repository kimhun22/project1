$(document).ready(function() {
	// 파일 변경시
    $("input[type='file']").on('change',function() {
    	$(this).parent().find(".clsFileNm").text("");

    	var delYn = $(this).parent().find(".clsDelYn").val();
    	if  ( delYn == "N" )  {
			$(this).parent().find(".clsDelYn").val("Y");
            var deleteSeq = $(this).parent().find(".clsDetailSeq").val();
            gfn_setDeleteNo($(this).closest("td").find(".deleteSeqs"), deleteSeq);
            $(this).parent().find(".clsDetailSeq").val("");
    	}
    });
});

//찾아보기 버튼 클릭 시 파일 추가
function gfn_searchAddFile(obj, maxFileCount) {
    var fileCnt = obj.closest(".fileForm").find(".fileItem").length;
    var idx = fileCnt-1

    if(obj.closest(".fileForm").find(".fileItem").eq(idx).find('input[type=file]')[0].files.length != 0 || obj.closest(".fileForm").find(".fileItem").eq(idx).find('.btn-file-down').length != 0) {
        obj.closest(".fileForm").find(".fileItem").find(".btn-add").trigger('click');
        idx++;
        obj.closest(".fileForm").find(".fileItem").eq(idx).hide();
    }

    if  ( fileCnt >= maxFileCount )  {
        return;
    }

    obj.closest(".fileForm").find(".fileItem").eq(idx).find(".file-name").remove();

    $(obj.closest(".fileForm").find(".fileItem").eq(idx).find('input[type=file]')[0]).trigger('click')

}
//찾아보기 버튼으로 생성된 첨부파일의 값이 변할 경우
 $(document).on('change', '.search-file-item input[type=file]', function(){
    $(this).parent().find('.file-name').remove();
    $(this).parent().prepend('<span class="file-name">'+$(this).val()+"</span>");
    $(this).parent().show();
 });

// 파일 추가 버튼 클릭
function gfn_addFile(obj, maxFileCount) {
	var fileCnt = obj.closest(".fileForm").find(".fileItem").length;

    if  ( fileCnt >= maxFileCount )  {
        alert("파일 업로드는 최대  " + maxFileCount + "개 까지 가능합니다.");
        return;
    }

    var row = obj.closest(".fileForm").find(".fileItem").eq(0).clone(true);

	$(row.find("input[type='file']")).val("");
    $(row.find(".clsFileNm")).text("");
    $(row.find(".clsDetailSeq")).val("");
    $(row.find(".clsDelYn")).val("Y");
    $(row.find(".bGray")).remove();

    row.appendTo(obj.closest(".fileForm"));

    var name = row.find("input[type='file']").attr("name");

    gfn_setId(obj, name);
}


// 파일 삭제 버튼 클릭
function gfn_delFile(obj, maxFileCount) {
	var objFileForm = obj.closest(".fileForm");
    var objDiv = obj.closest(".fileItem");
    var name = objDiv.find("input[type='file']").attr("name");
    var objDelete = objFileForm.find(".deleteSeqs");
    var detailSeq = objDiv.find(".clsDetailSeq").val();
    var fileCnt = objFileForm.find(".fileItem").length;

    var fileNo = objFileForm.find(".hidFile").val();
    var delYn = objDiv.find(".clsDelYn").val();
    if  ( $.trim(fileNo).length > 0 && delYn == 'N' )  {
        gfn_setDeleteNo(objDelete, detailSeq);
    }

    if  ( fileCnt > 1 )  {
        objAdd = obj.siblings('.btn-add');
        objDiv.remove();

        if  ( objFileForm.find(".fileItem").eq("0").find(".bnt-add").length == 0 )  {
        	objFileForm.find(".fileItem").eq("0").find('.btn-del').before(objAdd);
        }
    }  else if  ( fileCnt == 1 )  {
        objDiv.find("input[type='file']").val("");
        objDiv.find(".clsFileNm").text("");
        objDiv.find(".clsDelYn").val("Y");
        objDiv.find(".clsDetailSeq").val("");
        objDiv.find(".clsDetailSeq").val("");
        objDiv.find('.file-name').remove();

        if($(objDiv).is('.search-file-item')) {
            $(objDiv).hide();
        }

    }

    gfn_setId(objFileForm, name);
}

// 파일 ID 셋팅
function gfn_setId(obj, name) {
    var idx = 1;
    obj.closest(".fileForm").find(".fileItem").each(function(i,v){
        $(this).find("input[type='file']").attr("id", name + "_" + idx)
        $(this).find("input[type='file']").attr("title", "파일_" + idx)
        idx++;
    });
}

// 파일 삭제 SN 셋팅
function gfn_setDeleteNo(objDelete, detailSeq){
	if  ( detailSeq != null && detailSeq.length > 0 )  {
        var deleteSeq = objDelete.val();

        if  ( $.trim(deleteSeq).length < 1 )  {
            objDelete.val(detailSeq);
        }  else  {
            objDelete.val(deleteSeq + ',' + detailSeq);
        }
    }
}

// 파일 다운로드
function gfn_fileDown(no, seq , hmSiteCode) {
	//$.blockUI();

	$.fileDownload("/comm/file/download.do?fileNo=" + no + "&fileSn=" + seq + "&hmSiteCode=" + hmSiteCode, {
		httpMethod: "POST",
		successCallback: function () {
			//$.unblockUI();
		},
		failCallback: function(message) {
			//$.unblockUI();
			alert(message);
		}
	});
}

// 파일 드롭 다운
function gfn_fileDropDown(dragFileList, allowFileType, maxUploadSize) {

    $("#fileDrop").on("dragenter", function(e){
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

        var files = e.originalEvent.dataTransfer.files;
        if(files != null && files != undefined){
        	var checkObject = gfn_dropDownFileCheck(files, allowFileType, maxUploadSize);

        	files = checkObject.files;
        	if  ( files.length > 0 )  {
        		var tag = "";
        		for(i=0; i<files.length; i++){
                    var f = files[i];
                    dragFileList.push(f);
                    var fileName = f.name;
                    var fileSize = f.size / 1024 / 1024;
                    fileSize = fileSize < 1 ? fileSize.toFixed(3) : fileSize.toFixed(1);
                    tag +=
                            "<div class='dragFileList'>" +
                                "<span class='fileName'><input type='checkbox' name='dropFileCheck' />"+fileName+"</span>" +
                                "<span class='fileSize'>"+fileSize+" MB</span>" +
                                "<span class='clear'></span>" +
                            "</div>";
                }
                $(this).append(tag);
        	}

        	if  ( checkObject.errorFileInfo != "" )  {
        		alert("업로드 실패 파일 정보\n-------------------------------------------------" + checkObject.errorFileInfo);
        	}
        }

        $(this).css("background-color", "#FFF");
    });

}

function gfn_dropDownFileCheck(files, allowFileType, maxUploadSize) {

	var checkFile = [];
	var errorFileInfo = "";
	var resultObject = {};

	for  ( var i = 0 ; i < files.length ; i++ )  {
		var fileNameArr = files[i].name.split("\.");
		var ext = fileNameArr[fileNameArr.length - 1];
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

function gfn_dropDownFileDel(dragFileList) {

	$("#fileDrop input[name=dropFileCheck]").each(function(e) {
		if  ( $(this).is(":checked") )  {
			dragFileList.splice($("#fileDrop input[name=dropFileCheck]").index(this), 1);
			$(this).closest(".dragFileList").remove();
		}
	});

}
