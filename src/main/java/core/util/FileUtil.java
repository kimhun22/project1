package core.util;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.exception.HMException;

public class FileUtil {

	/**
	 * 인코딩 파일명 세팅
	 *
	 * @param request
	 * @param fileName
	 * @return
	 */
	public String encodingFileName(HttpServletRequest request, String fileName) {

		// 브라우저 정보
		String browserInfo = request.getHeader("User-Agent");

		// 파일명 인코딩
		String encodingFileName = "";
		try {
			// IE ( MSIE : 10버전 이하, Trident : 11버전 이상 )
			if  ( browserInfo.indexOf("MSIE") > -1 || browserInfo.indexOf("Trident") > -1 )  {
				encodingFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			}
			// 크롬
			else if  ( browserInfo.indexOf("Chrome") > -1 )  {
				StringBuffer sb = new StringBuffer();
				for  ( int i = 0 ; i < fileName.length() ; i++ )  {
					char c = fileName.charAt( i );
					if  ( c > '~' )  {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					}
					else  {
						sb.append(c);
					}
				}
				encodingFileName = sb.toString();
			}
			// 나머지 다른 브라우저
			else  {
				encodingFileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			}
		} catch (Exception e) {
			throw new HMException("파일명 인코딩 중 오류가 발생했습니다.("+ e.getMessage() +")");
		}

		return encodingFileName;

	}

	/**
	 * 파일 다운로드 세팅
	 *
	 * @param response
	 * @param request
	 * @param fileName
	 */
	public void downloadInfoSet(HttpServletResponse response, String fileName) {

		response.setHeader("Content-type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "private");
		response.setHeader("Expires", "0");

	}

	public String filePathBlackList(String pathValue) {

		if (pathValue == null || pathValue.trim().equals("")) {
			return "";
		}

		pathValue = pathValue.replaceAll("\\.\\./", ""); // ../
		pathValue = pathValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return pathValue;

	}

}
