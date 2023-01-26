<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/_inc/_default.jsp" %>

<!doctype html>
<html lang="ko" class="ifrm-layout-html" style="padding: 24px;">
<head>
<meta name="viewport" content="width=1200">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>스마트인허가</title>

<!-- css -->
<link rel="stylesheet" href="/${hmSiteCode}/css/bootstrap-grid.min.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/reset.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/common.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/main.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/ui-extend.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/style2.css"/>
<link rel="stylesheet" href="/${hmSiteCode}/css/jquery.datetimepicker.min.css" />
<link rel="stylesheet" href="/${hmSiteCode}/js/swiper/swiper.min.css" />
<link rel="stylesheet" href="/${hmSiteCode}/css/pretendard.css" />

<!-- js -->
<script type="text/javascript" src="/${hmSiteCode}/js/lib/jquery-3.6.1.min.js"></script>
<script src="/${hmSiteCode}/js/lib/ui/1.13.2/jquery-ui.min.js"></script>
<script src="/${hmSiteCode}/js/swiper/swiper.min.js"></script>
<script src="/${hmSiteCode}/js/datetimepicker/jquery.datetimepicker.full.min.js"></script>
<script src="/${hmSiteCode}/js/script.js"></script>
<script src="/smart/js/commonDate.js"></script>
<script src="/smart/js/common.js"></script>
<script src="/${hmSiteCode}/js/lib/fileDownload/jquery.fileDownload.js"></script>
<script src="/${hmSiteCode}/js/lib/cookie/jquery.cookie.js"></script>
<script src="/${hmSiteCode}/js/lib/form/jquery.form.js"></script>
<script src="/${hmSiteCode}/js/lib/picker/jquery.mtz.monthpicker.js"></script>
<script src="/${hmSiteCode}/js/lib/blockUI/jquery.blockUI.js"></script>
<script src="/${hmSiteCode}/js/hm/i18n_ko.js"></script>
<script src="/${hmSiteCode}/js/hm/hm-common-0.1.js"></script>
<script src="/${hmSiteCode}/js/hm/hm-common-file-0.1.js"></script>


<%@include file="/WEB-INF/jsp/common/_layout/cn/head.jsp" %>
</head>
<body>
	<t:insertAttribute name="content" ignore="true"/>
<div id="overlay"><img src="/${hmSiteCode}/image/ajax-loader.gif" id="img-load"/></div>

		<%@include file="/WEB-INF/jsp/common/_inc/_footer.jsp" %>

</body>
</html>