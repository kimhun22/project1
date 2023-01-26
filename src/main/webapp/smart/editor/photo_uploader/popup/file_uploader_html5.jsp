<%@page import="egovframework.cmmn.service.EgovProperties"%>
<%@page import="java.io.*"%>
<%@page import="java.util.UUID"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%
	String filename = request.getHeader("file-name");
	String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
	filename_ext = filename_ext.toLowerCase();

	//이미지 검증 배열변수
	String[] allow_file = { "jpg", "png", "bmp", "gif" };

	//돌리면서 확장자가 이미지인지 
	int cnt = 0;
	for (int i = 0; i < allow_file.length; i++) {
		if (filename_ext.equals(allow_file[i])) {
			cnt++;
		}
	}

	//이미지가 아님
	if (cnt == 0) {
		out.println("NOTALLOW_" + filename);
	} else {  
		//파일 기본경로
		String filePath = EgovProperties.getProperty("Globals.fileStorePath")+ File.separator + "editor";
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new java.util.Date());
		String realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
		InputStream is = request.getInputStream();
		OutputStream os = new FileOutputStream(new File(filePath, realFileNm));
		int numRead;
		byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
		while ((numRead = is.read(b, 0, b.length)) != -1) {
			os.write(b, 0, numRead);
		}
		if (is != null) {
			is.close();
		}
		os.flush();
		os.close();

		//정보 출력
		String sFileInfo = "&bNewLine=true";
		sFileInfo += "&sFileName=" + filename;
		String editorURL = EgovProperties.getProperty("Globals.uploadImageURL") + "/editor/";
		sFileInfo += "&sFileURL=" + editorURL + realFileNm;
		out.println(sFileInfo);
	}
%>