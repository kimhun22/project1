package core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.math.JVMRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.co.heartmedia.vo.TnUserTbVO;




/**
 * 공통 메소드를 정의하는 클래스이다.
 *
 */
@Service("commonUtils")
public class CommonUtils {

	private String TAG = this.getClass().getName();

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public static JVMRandom random = new JVMRandom();

//	@Resource(name = "saeolGpki")
//	private SaeolGpki saeolGpki;
//
//	@Resource(name="saeolDao")
//	private SaeolDao saeolDao;

	/**
	 * <pre>
	 * 1. 개요 : 패스워드 암호화
	 * 2. 처리내용 : 패스워드 암호화
	 * 3. 설명 : 한국정보보호진흥원 및 국제사회에서 MD5, SHA1 해시 알고리즘이 깨졌다고 보고 있고
	 * 대안으로 SHA256, SHA512 알고리즘으로 변경하도록 권고하고 있다고 한다.
	 * SHA256 64자리의 16진수 문자열이 출력되며, SHA512는 128자리의 16진수 문자열이 출력된다.
	 * 4. 사용법 : encryption으로 true를 리턴받고, 하위 getter를 통해 암호화된 패스워드를 리턴받는다.
	 * </pre>
	 *
	 * @Method Name : encryption
	 * @date : 2016. 5. 17.
	 * @author : 신호석
	 * @history :
	 *          ----------------------------------------------------------------
	 *          ------- 변경일 작성자 변경내용 ----------- -------------------
	 *          --------------------------------------- 2016. 5. 17. 신호석 최초 작성
	 *          ----------------------------------------------------------------
	 *          -------
	 *
	 * @param userPassword
	 * @return 암호화 된 사용자 패스워드 boolean / 암호화 방식 : SHA-256
	 *
	 */
	public String encryption(String userPassword) {

		MessageDigest md;
		String tempPassword = "";

		try {
			// MessageDigest.getInstance : Java에서 지원하는 암호화 알고리즘
			// SHA-1, SHA-256, SHA-384, SHA-512를 지원한다.
			// 암호화 방식를 변경하려면 getInstance인자값의 "SHA-512" 부분만 해당값으로 변경하면 된다.
			// ex) MessageDigest.getInstance("SHA-256") >>
			// MessageDigest.getInstance("SHA-256")
			md = MessageDigest.getInstance("SHA-256");
			// update : 데이터를 hash함, String을 byte[]로 변환시 윈도우에서 구동하는 프로그램과
			// UNIX에서 구동하는 프로그램이 동시에 서로 암호화를 할 경우 한글 등을 처리할 때 문제가
			// 발생될 수 있으므로 인코딩 지정
			md.update(userPassword.getBytes());
			// digest : 바이트배열로 hash를 반환, 적은 데이터일 경우 digest에 직접입력가능
			StringBuffer sb = new StringBuffer();
			byte[] mb = md.digest();
			for (int i = 0; i < mb.length; i++) {
				sb.append(Integer.toString((mb[i] & 0xff) + 0x100, 16).substring(1));
			}

			tempPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			tempPassword = null;
			return tempPassword;
		}
		return tempPassword;
	}

	/**
	 * 임의의 4자리의 숫자를 반환한다.<br>
	 *
	 * @return
	 */
	public String getTempPassword() {
		logger.info("getTempPassword", TAG);

		String uuid = String.valueOf(random.nextInt(999999999));

		return uuid.substring(0, 4);
	}

	/**
	 * 밀리세컨드 + UUID 의 조합으로 26자리의 sequence key 를 만들어서 반환한다.
	 *
	 * 넘겨받은 4자리의 prefix 를 합쳐서 31자리의 sequence key 를 만들어서 반환한다.
	 *
	 * @return
	 */
	public String getSequenceKey(String prefix) {
		logger.info("> getSequenceKey", TAG);
		logger.debug("> prefix : {}", prefix, TAG);

		String result;

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		result = simpleDateFormat.format(date) + String.valueOf(random.nextInt(999999999));
		result = prefix + "-" + result;
		logger.debug("> sequence key : {}", result, TAG);

		return result;
	}

	/**
	 * 넘겨받은 4자리의 prefix 를 합쳐서 20자리의 sequence key 를 만들어서 반환한다.
	 *
	 * @return
	 */
	public String getSequenceKey20Length(String prefix) {
		logger.info("> getSequenceKey20Length", TAG);
		logger.debug("> prefix : {}", prefix, TAG);

		String result;

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");

		result = simpleDateFormat.format(date) + String.valueOf(random.nextInt(999999999)).substring(0, 5);
		result = prefix + "-" + result;
		logger.debug("> sequence key : {}", result, TAG);

		return result;
	}

	/**
	 * 현재 시간을 'yyyy-MM-dd HH:mm:ss' 포멧으로 반환한다.
	 *
	 * @return
	 */
	public String getCurrentDate() {
		logger.info("> getCurrentDate", TAG);

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return simpleDateFormat.format(date);
	}

	/**
	 * 현재 날짜를 'yyyyMMdd' 의 포멧으로 반환한다.<br>
	 *
	 * @return
	 */
	public String getShortCurrentDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		return simpleDateFormat.format(date);
	}

	/**
	 * 현재 시간을 밀리세컨드 포멧으로 반환 한다.
	 *
	 * @return
	 */
	public String getCurrentDateMillisecond() {
		long time = System.currentTimeMillis();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return simpleDateFormat.format(new Date(time));
	}

	/**
	 * 현재날짜를 14자리의 'yyyyMMddHHmmss' 의 포멧으로 반환한다.<br>
	 *
	 * @return
	 */
	public String getCurrentDateSecond() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		return simpleDateFormat.format(new Date());
	}

	/**
	 * SMS 발송용 현재날짜를 14자리의 'yyyyMMddHHmmss' 의 포멧으로 반환한다.<br>
	 *
	 * @return
	 */
	public String getCurrentDateForSms() {
		long time = System.currentTimeMillis();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		return simpleDateFormat.format(new Date(time));
	}

	/**
	 * 두 시간차이의 경과를 초 단위의 string 포멧으로 반환 한다.
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public String getProgressDatetime(String start, String end) {

		long diffSec = 0;

		try {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date from = transFormat.parse(start);
			Date to = transFormat.parse(end);

			long diff = to.getTime() - from.getTime();
			diffSec = diff / 1000;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return diffSec + "";
	}

	/**
	 * EAIS 검색용 현재날짜를 10자리의 'yyyyMMddHH' 의 포멧으로 가져온다.<br>
	 * 현재시간으로부터 10분 전의 시간을 가져온다.
	 *
	 * @return
	 */
	public String getCurrentDateTimeForEais() {
		long time = System.currentTimeMillis();

		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyyMMddHH"); // 시간단위.
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm"); // 분단위.

		Date currentDatetime = new Date();
		Date beforeOneHour = new Date(time);
		// beforeOneHour.setTime(currentDatetime.getTime() - ((long) 1000 * 60 *
		// 60)); // 1시간전.
		beforeOneHour.setTime(currentDatetime.getTime() - ((long) 1000 * 60 * 10)); // 10분전.

		// return simpleDateFormat.format(beforeOneHour); // 시간 단위.
		return simpleDateFormat.format(beforeOneHour).substring(0, 11); // 분 단위.
	}

	/**
	 * GET 방식으로 XML 포멧의 데이터를 가져온다.
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getXmlByGet(String url) throws Exception {
		logger.debug("> getXmlByGet", TAG);

		String result = "";

		try {
			URL postUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("Content-Type",
			// "application/json");
			OutputStream os = connection.getOutputStream();
			os.write("".getBytes());
			os.flush();

			logger.debug("response code : {}", connection.getResponseCode(), TAG);

			if (connection != null) {
				connection.setConnectTimeout(3000);

				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

					String line = br.readLine();
					logger.debug("> line : {}", line, TAG);

					result = line;
				}
			}
		} catch (Exception e) {
			logger.error("error : {}", e.toString(), TAG);
		}

		return result;
	}

	/**
	 * GET 방식으로 Json 포멧의 데이터를 가져온다.
	 *
	 * @return
	 * @throws Exception
	 */
	public String getJsonByGet(String url) throws Exception {
		logger.debug("> getJsonByGet", TAG);

        StringBuilder result = new StringBuilder();

        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-type", "application/json");
            OutputStream os = connection.getOutputStream();
            os.write("".getBytes());
            os.flush();

            logger.debug("response code : {}", connection.getResponseCode(), TAG);

            if (connection != null) {
                connection.setConnectTimeout(3000);

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));

                    if (br != null) {
                        int cp;
                        while ((cp = br.read()) != -1) {
                            result.append((char) cp);
                        }
                        br.close();
                    }

                    logger.debug("> line : {}", result.toString(), TAG);

                }
            }
        } catch (Exception e) {
            logger.error("error : {}", e.toString(), TAG);
        }

        return result.toString();
    }

	/**
	 * POST 방식으로 XML 포멧의 데이터를 가져온다.
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getXmlByPost(String url) throws Exception {
		logger.debug("> getXmlByPost", TAG);

		String result = "";

		try {

		} catch (Exception e) {
			logger.error("> error : {}", e.toString(), TAG);
		}

		return result;
	}

	/**
	 * POST 방식으로 Json 포멧의 데이터를 가져온다.
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
//	public String getJsonByPost(String url) throws Exception {
//		logger.debug("> getJsonByPost", TAG);
//
//		String result = "";
//
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("id", "사용자아이디");
//		jsonObject.put("pw", "비밀번호");
//		jsonObject.put("pnu", "주소코드");
//
//		String jsonString = jsonObject.toString();
//
//		try {
//			URL postUrl = new URL(url);
//			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
//			connection.setDoOutput(true);
//			connection.setInstanceFollowRedirects(false);
//			connection.setRequestMethod("POST");
//			// connection.setRequestProperty("Content-Type",
//			// "application/json");
//			OutputStream os = connection.getOutputStream();
//			os.write(jsonString.getBytes());
//			os.flush();
//
//			logger.debug("response code : {}", connection.getResponseCode(), TAG);
//
//			if (connection != null) {
//				connection.setConnectTimeout(3000);
//
//				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//					String line = br.readLine();
//					logger.debug("> line : {}", line, TAG);
//
//					JSONObject resultJsonObject = new JSONObject(line);
//
//					logger.debug("> result : {}", resultJsonObject.getString("result"), TAG);
//					logger.debug("> custNo : {}", resultJsonObject.getString("custNo"), TAG);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("> error : {}", e.toString(), TAG);
//		}
//
//		return result;
//	}

	/**
	 * 세올행정시스템 웹서비스 요청에 사용할 MSGKEY 를 가져온다.<br>
	 *
	 * 형식 : yyyyMMddHHmmssSSS + 랜덤8자리(총25자리).<br>
	 *
	 * @return
	 */
	public String getSaeolMsgKey() {
		// logger.info("> getSaeolMsgKey", TAG);

		String result = "";

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		result = simpleDateFormat.format(date) + String.valueOf(random.nextInt(99999999));

		// logger.debug("> MSGKEY        : {}", result, TAG);
		// logger.debug("> MSGKEY length : {}", result.length(), TAG);

		return result;
	}

	/**
	 * 세올행정시스템의 웹서비스를 통해서 response 를 가져온다.<br>
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getResponseFromSaeol(String request) throws Exception {
		 logger.debug("> getResponseFromSaeol", TAG.getClass());

		String serverMode = PropertiesUtil.getProperty("Globals.RunMode");
		if (!("PROD").equals(serverMode)) {
			String TestStringData =  getSaeolTestData();

			return TestStringData;
		}

		StringBuffer response = null ;
		String line = "";

		HttpURLConnection connection = null;

		try {
			// 세올행정 URL ( globals.properties 에  운영모드에 맞추어서 수정 필요)
			URL url = new URL(PropertiesUtil.getProperty("Globals.saeol.webservice.url"));
			logger.debug("Saeol URL : " + PropertiesUtil.getProperty("Globals.saeol.webservice.url"));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			logger.debug("Saeol URL Call");
			OutputStream os = connection.getOutputStream();
			os.write(request.getBytes("UTF-8"));
			os.flush();
			os.close();

			InputStreamReader inputStreamReader = null;

			BufferedReader br = null;

			if (connection != null) {
				connection.setConnectTimeout(3000);

				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

					inputStreamReader = new InputStreamReader(connection.getInputStream());
					br = new BufferedReader(inputStreamReader);
//					line = br.readLine();
					response = new StringBuffer();
				      String responsetemp;

				      while((responsetemp = br.readLine())!=null) {
				    	  response.append(responsetemp);
				      }
				      br.close();
				      line  = response.toString();
				      logger.debug("> Saeol URL Call Respose : " + response.toString());

				} else {
					logger.error("> http fail : {}", HttpURLConnection.HTTP_INTERNAL_ERROR);

					inputStreamReader = new InputStreamReader(connection.getErrorStream());
					br = new BufferedReader(inputStreamReader);

					while ((line = br.readLine()) != null) {
						System.out.println("> failure line : " + line);
					}

					logger.error("> error : {}", br.readLine());
				}
			}
		} catch (Exception e) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(out);
			e.printStackTrace(printStream);

			logger.debug("> error : {}", out.toString(), TAG);
		} finally {
			connection.disconnect();
		}

		return line;
	}


	private String getSaeolTestData() {
		String SaeolData = "";

		try {
			SaeolData = "<message>"+
					"<body>"+
					"<res_cnt>142</res_cnt>"+
					"<list>"+
					"<mw_stat_info><![CDATA[결과처리]]></mw_stat_info>"+
					"<cgg_mw_afr_cl_no><![CDATA[1500000005301]]></cgg_mw_afr_cl_no>"+
					"<mw_take_no><![CDATA[201739902990022368]]></mw_take_no>"+
					"<mw_afr_shtnm><![CDATA[개발행위허가(토지형질변경, 토석채취, 공작물설치, 토지분할, 물건적치)]]></mw_afr_shtnm>"+
					"<deal_plan_ymd><![CDATA[20170619]]></deal_plan_ymd>"+
					"<mw_aplct_nm><![CDATA[김휘창]]></mw_aplct_nm>"+
					"<dpp_id><![CDATA[D1347629]]></dpp_id>"+
					"<reg_dt><![CDATA[20170529105506]]></reg_dt>"+
					"<main_deal_dep_code><![CDATA[39903200000]]></main_deal_dep_code>"+
					"<main_deal_dep_nm><![CDATA[화도읍 도시건축과]]></main_deal_dep_nm></list>"+
					"<list>"+
					"<mw_stat_info><![CDATA[결과처리]]></mw_stat_info>"+
					"<cgg_mw_afr_cl_no><![CDATA[1500000005301]]></cgg_mw_afr_cl_no>"+
					"<mw_take_no><![CDATA[201739902980020541]]></mw_take_no>"+
					"<mw_afr_shtnm><![CDATA[개발행위허가(토지형질변경, 토석채취, 공작물설치, 토지분할, 물건적치)]]></mw_afr_shtnm>"+
					"<deal_plan_ymd><![CDATA[20170703]]></deal_plan_ymd>"+
					"<mw_aplct_nm><![CDATA[정인숙]]></mw_aplct_nm>"+
					"<dpp_id><![CDATA[KJS0829]]></dpp_id>"+
					"<reg_dt><![CDATA[20170529143942]]></reg_dt>"+
					"<main_deal_dep_code><![CDATA[39903160000]]></main_deal_dep_code>"+
					"<main_deal_dep_nm><![CDATA[진접읍 도시건축과]]></main_deal_dep_nm></list>"+
					"<res_code>Y</res_code>"+
					"<err_msg><![CDATA[]]></err_msg></body></message>	";
		}catch (Exception e) {
			SaeolData = "";
		}

		return SaeolData;
	}

	/**
	 * XML 태그값의 데이터를 가져온다.<br>
	 * 데이터가 없을 경우 공백을 설정한다.<br>
	 *
	 * @param sTag
	 * @param itemElement
	 * @return
	 */
	public String getTagValue(String sTag, Element itemElement) {
		String result = "";

		try {
			NodeList linkNodeList = itemElement.getElementsByTagName(sTag);
			Element linkElement = (Element) linkNodeList.item(0);
			NodeList childLinkNodeList = linkElement.getChildNodes();
			result = ((Node) childLinkNodeList.item(0)).getNodeValue();
			// logger.debug("NodeName: " + sTag + " > NodeValue: " + result ,
			// TAG);
		} catch (Exception e) {
			// logger.error("> error : {}", e, TAG);
			result = " ";
		}

		return result;
	}

	/**
	 * 현재 로그인 한 사용자의 아이디를 반환한다.
	 *
	 * @return
	 * @throws Exception
	 */
	public String getLoginUserId() throws Exception {

		TnUserTbVO tempVO = null;

		try {
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			HttpSession session = req.getSession();

			tempVO = (TnUserTbVO) session.getAttribute(GlobalEnum.LOGIN_SESSION_KEY.toString());
		} catch (Exception e) {
			tempVO = new TnUserTbVO();
			tempVO.setLoginId(GlobalEnum.NOT_AVAILABLE.toString());

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(out);
			e.printStackTrace(printStream);

			logger.debug("> error : {}", out.toString(), TAG);
		}

		return tempVO.getLoginId();
	}

	public static String xmlDocumentToString(Document doc) {
		try {
			StringWriter sw = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			return sw.toString();
		} catch (Exception ex) {
			throw new RuntimeException("Error converting to String", ex);
		}
	}

	public static boolean isIntegerParsable(String input) {
		boolean parsable = true;
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			parsable = false;
		}
		return parsable;
	}

	//구분된 문자열에서 원하는 위치의 문자를 가져온다.
	public String getsubStr(String str, String regex, int idx){

		String text_arr[] = str.split(regex);
		String rtn_text = null;
		int len = text_arr.length;

		for(int i=0; i<len;i++){
			if(idx == i){
				rtn_text = text_arr[i];
				break;
			}
		}
		return rtn_text;

	}
}
