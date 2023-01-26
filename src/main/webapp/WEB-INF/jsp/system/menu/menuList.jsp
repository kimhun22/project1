<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<link rel="stylesheet" href="<c:url value='/${hmSiteCode}/js/lib/zTree/zTreeStyle.css' />" type="text/css">
<script type="text/javascript" src="<c:url value='/${hmSiteCode}/js/lib/zTree/jquery.ztree.core.js' />"></script>
<script type="text/javascript" src="<c:url value='/${hmSiteCode}/js/lib/zTree/jquery.ztree.exedit.js' />"></script>
<script type="text/javascript" src="<c:url value='/${hmSiteCode}/js/lib/zTree/jquery.ztree.exhide.js' />"></script>
<script type="text/javascript" src="<c:url value='/${hmSiteCode}/js/lib/zTree/fuzzysearch.js' />"></script>


<script>
	// zTree 세팅
	var setting = {
        edit: {
            enable: true,
            showRemoveBtn: false,
            showRenameBtn: false,
            drag: {
            	isCopy : false,
            	isMove : false
            }
        },
        data: {
            simpleData: {
                enable:true
            }
        }
	};

	$(document).ready(function() {
		// zTree on
		fn_treeNodeSetting();
	});

	// zTree on
	function fn_treeNodeSetting(selectedNode) {
		var treeObj;

		$.ajax({
			url : "/${hmSiteCode}<c:url value='/system/menu/getListAjax.do'/>",
	        success : function(data) {
				var zNodeList = [];
	        	for  ( var i in data )  {
					var node = {};
					node.id = data[i].menuCode;
					node.pId = data[i].upperMenuCode;
					node.name = data[i].menuNm;
					node.click = "javascript:fn_view('"+ data[i].upperMenuCode +"', '"+ data[i].menuCode +"')";
					if  ( data[i].useAt == "0" )  {
						node.icon = "<c:url value='/${hmSiteCode}/js/lib/zTree/img/ztree_close.ico'/>";
					}

					zNodeList.push(node);
				}

	        	treeObj = $.fn.zTree.init($("#treeList"), setting, zNodeList);

	        	if  ( null == selectedNode )  {

	        		treeObj.selectNode(treeObj.getNodes()[0]);
	        		treeObj.expandNode(treeObj.getNodes()[0], true);

	        		// 상세 페이지 세팅
	        		fn_view('000', treeObj.getNodes()[0].id);
	        	}
	        	else {

	        		var node = treeObj.getNodeByTId(selectedNode.tId);
	        		treeObj.selectNode(node);
	        		treeObj.expandNode(node, true);

	        		// 상세 페이지 세팅
	        		fn_view(node.pId, node.id);
	        	}

	        	// 검색 모듈 on
	            fuzzySearch('treeList', '#Search', null, true);
	        }
		});
	}

	// 상세 페이지 세팅
	function fn_view(upperMenuCode, menuCode) {
	    $.ajax({
	        url : "/${hmSiteCode}<c:url value='/system/menu/view.do'/>",
	        data : {
	        	upperMenuCode : upperMenuCode
	        	, menuCode : menuCode
	        },
	        dataType : 'html',
	        success : function(data) {
	            $("#viewDIV").html(data);
	        }
	    });
	};

	// zTree 모두 펼치기
	function openAll() {
	    var openAll = $.fn.zTree.getZTreeObj("treeList");
	    openAll.expandAll(true);
	};

	// zTree 모두 접기
	function closeAll() {
	    var openAll = $.fn.zTree.getZTreeObj("treeList");
	    openAll.expandAll(false);
	};

	// 하위 메뉴 등록 팝업 오픈
	function fn_addPopupOpen(upperMenuCode, menuCode) {
	    $("#DIV_ADD").load("/${hmSiteCode}<c:url value='/system/menu/addPopup.do?upperMenuCode="+ upperMenuCode +"&menuCode="+ menuCode +"'/>",
	        function(response, status, xhr) {
	            if  ( status != "error" )  {
	                $(this).dialog({
	                    title : "메뉴 등록",
	                    modal : true,
	                    width : "850px",
	                    height : "auto"
	                });
	                $(this).dialog("open");
	                $.unblockUI();
	            }
	        }
	    );
	}
</script>

	<div class="cont_area">
		<div class="tab_content" style="overflow: hidden;">
			<div class="left_div" style="width: 40%;">
				<div class="div_sub_area">
					<div class="">
						<h3 class="subTitle01">메뉴 정보</h3>
					</div>
					<div class="mgb_8">
						<input type="text" class="mgr_4 md_w_full" id="Search" name="Search" placeholder="메뉴명을 입력하세요.">
			            <!-- <input type="button" class="button buttonS bGray" onclick="openAll();" value="+ 모두 펼치기"/>
						<input type="button" class="button buttonS bGray" onclick="closeAll();" value="- 모두 접기"/> -->
						<div class="tree_btn_box">
							<button type="button" class="ubtn gray02 medium hover" onclick="openAll();"><i class="icon icon_addOnly"></i>모두펼치기</button>
							<button type="button" class="ubtn gray02 medium hover" onclick="closeAll();"><i class="icon icon_removeOnly"></i>모두 접기</button>
						</div>
					</div>

					<div class="treeArea div_scroll">
						<div id="treeList" class="ztree"></div>
					</div>
				</div>
			</div>

			<div class="right_div" style="width: calc(60% - 24px);">
				<div class="div_sub_area">
					<div id="viewDIV" class="treeTable"></div>
				</div>
			</div>
		</div>
	</div>

	<div id="DIV_ADD"></div>
