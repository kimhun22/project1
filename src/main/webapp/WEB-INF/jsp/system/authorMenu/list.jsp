<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!--
 /**
  * @Class Name  : authorMenuList.jsp
  * @Description :  메뉴 권한 설정
  */
-->
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<link rel="stylesheet" href="/${siteName}/js/lib/zTree/zTreeStyle.css " type="text/css">

<script type="text/javascript" src="/${siteName}/js/lib/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/${siteName}/js/lib/zTree/jquery.ztree.excheck.js"></script>

<script>
	//zTree 세팅
	var setting = {
		check: {
			enable: true,
			checkboxType : { "Y" : "ps", "N" : "ps" }
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick : nodeClick
		},
		view: {
			showIcon : false
		}
	};

	$(document).ready(function() {
		// 상세 데이터 세팅
		fn_treeSetting($("#authorCode").val());

		// 권한 변경 시 상세 데이터 세팅
		$("#authorCode").on('change', function() {
			fn_treeSetting($("#authorCode").val());
		});
	});

	// 상세 데이터 세팅
	function fn_treeSetting(authorCode) {
		$.ajax({
            url : "/${hmSiteCode}<c:url value='/system/authorMenu/getListAjax.do'/>",
            data : {
            	authorCode : authorCode
            },
            success : function(data) {

            	// zTree Node 세팅
            	var zNodeList = [];
            	for  ( var i in data.menuList )  {
           			var zNode = {};

           			zNode.id = data.menuList[i].menuCode;
           			zNode.pId = data.menuList[i].upperMenuCode;
           			zNode.name = data.menuList[i].menuNm;
               		zNode.open = true;

               		for  ( var j in data.authorMenuList )  {
						if  ( data.menuList[i].menuCode == data.authorMenuList[j].menuCode )
							zNode.checked = true;
					}

               		zNodeList.push(zNode);
           		}

            	// zTree 세팅
        		var treeObj = $.fn.zTree.init($("#treeList"), setting, zNodeList);
        		// zTree 모두 펼치기
        		treeObj.expandAll(true);
            }
        });
	}

	// zTree Node click
	function nodeClick(event, treeId, treeNode) {
		var tree = $.fn.zTree.getZTreeObj(treeId);
		if  ( treeNode.checked )
			tree.checkNode(treeNode, false, true);
		else
			tree.checkNode(treeNode, true, true);
	}

	// 등록
	function fn_save() {
		var checkNodes = $.fn.zTree.getZTreeObj("treeList").getCheckedNodes(true);
		if  ( checkNodes.length == 0 )  {
			alert("선택된 메뉴가 없습니다. 메뉴를 선택해주세요.");
			return false;
		}

		if  ( confirm("저장 하시겠습니까?") )  {
			// 선택된 메뉴 ID 세팅
			var checkMenuInfo = new Array();
			for  ( var i in checkNodes )  {
				checkMenuInfo.push(checkNodes[i].id);
			}

			$.ajax({
				url : "/${hmSiteCode}<c:url value='/system/authorMenu/saveAjax.do'/>",
				data : {
					authorCode : $("#authorCode").val()
					, menuList : checkMenuInfo
				},
				success : function(data) {
					if  ( data.result )  {
						alert(data.message);
	                }  else  {
	                    alert(data.message);
	                }
				}
			});
		}
	}
</script>

	<div class="cont_area">
		<div class="tab_content" style="overflow: hidden;">
			<div class="left_div div_scroll" style="width: 40%">
				<div class="div_sub_area">
					<h3 class="subTitle01">권한 정보</h3>

					<div class="">
						<table class="table_t01 data_table">
							<colgroup>
								<col width="140px"/>
								<col />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">
										<span class="spanLabel">권한</span>
									</th>
									<td>
										<select class="f_size02 w_full" name="authorCode" id="authorCode" title="권한">
											<c:forEach items="${authorCodeList}" var="codeData" varStatus="status">
												<c:if test="${codeData.cmmnCode ne '5'}">
													<option value="${codeData.cmmnCode}">${codeData.cmmnCodeNm}</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="right_div" style="width: calc(60% - 24px);">
				<div class="div_sub_area">
					<h3 class="subTitle01">메뉴 정보</h3>

					<div class="treeArea div_scroll">
						<div id="treeList" class="ztree"></div>
					</div>
				</div>
			</div>
		</div>

		<div class="btn_area">
			<button type="button" class="ubtn normal primary hover ml-2" onclick="fn_save();" style="float:right;"><i class="icon icon_done"></i>저장</button>
		</div>
	</div>


