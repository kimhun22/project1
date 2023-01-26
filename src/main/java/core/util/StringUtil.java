package core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.exception.HMException;

public class StringUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

	public static final String EMPTY = "";
	public static final String PUNCT = "\\p{Punct}";	// !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ 문자열
	public static final String PUNCTA = "*!#$%&'\\+,/:;<=>?[]^`~\"";  // @_-.()를 제외한 문자열
    public static final String PUNCTB = "$%&'\\+,/:;<=>?^`\"";        // @_-.()[]#!~ 를 제외한 문자열
    public static final String PUNCTC = "$%'\\+,:;<>^`\"";            // @_-.()[]#!~?=&/ 를 제외한 문자열

    /**
     * <p>Padding을 할 수 있는 최대 수치</p>
     */
    // private static final int PAD_LIMIT = 8192;
    /**
     * <p>An array of <code>String</code>s used for padding.</p>
     * <p>Used for efficient space padding. The length of each String expands as needed.</p>
     */
    /*
    private static final String[] PADDING = new String[Character.MAX_VALUE];

    static {
        // space padding is most common, start with 64 chars
        PADDING[32] = "                                                                ";
    }
     */

    /**
	 * null 처리 함수
	 * @param obj
	 * @param defaultVal
	 * @return
	 */
	public static String nvl(Object obj, String defaultVal) {
		return (obj == null || "".equals(obj)) ? defaultVal : obj.toString();
	}


	/**
	 * 널인 문자열을 스페이스("")로 치환한다 <br>
	 * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	 *
	 * @param String
	 * @return
	 */
	public static String null2space(String s) {

		if (s == null || s.length() == 0)
			return "";
		else
			return s.trim();
	}

	/**
	 * 숫자 check <br>
	 * klia_root/common_side/src/klia/common/util/StringUtil.java <br>
	 * @param String
	 * @return boolean
	 */
	public static boolean isNumber(String target){

		String numbers= "1234567890";
		boolean flag = true;

		for(int i = 0; i < target.length();i++){
			if(numbers.indexOf(target.substring(i,i+1)) < 0){
				flag = false;
			}
		}

		return flag;
	}

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else
                returnVal = source;
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else
                result = source;
        }
        return result;
    }

    /**
     *  String.replaceAll("\\p{Punct}","")
     *  !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ 문자열을 "" 로 대체
     * @param strs  the array to remove characters from, may be null
     * @return string
     */
	public static String replaceAllByPunct(String strs) {
		if ("".equals(nvl(strs, ""))) {
			return strs;
		}

		return strs.replaceAll(PUNCT, "");
	}

	/**
     *  removeChar(strs, PUNCTA);
     *   @-_(){}를 제외한 문자열을 ""로 대체
     * @param strs  the array to remove characters from, may be null
     * @return string
     */
	public static String replaceAllByPunctA(String strs) {
		if ("".equals(nvl(strs, ""))) {
			return strs;
		}

		String temp = removeChar(strs, PUNCTA);

		return temp;
	}

    /**
     *  removeChar(strs, PUNCTB);
     *   @_-.()[]#!~{} 를 제외한 문자열을 ""로 대체
     * @param strs  the array to remove characters from, may be null
     * @return string
     */
    public static String replaceAllByPunctB(String strs) {
        if (strs == null || strs.length() == 0 ){
            return strs;
        }
        return removeChar(strs, PUNCTB);
    }

    /**
     *  removeChar(strs, PUNCTC);
     *   @_-.()[]#!~?=&/{} 를 제외한 문자열을 ""로 대체
     * @param strs  the array to remove characters from, may be null
     * @return string
     */
    public static String replaceAllByPunctC(String strs) {
        if (strs == null || strs.length() == 0 ){
            return strs;
        }
        return removeChar(strs, PUNCTC);
    }

	/**
	 * 원본문자열(str)에서 캐릭터(ch)를 찾아 제거한다 <BR>
	 *
	 * @param str
	 *            입력문자열
	 * @param 제거할
	 *            문자
	 * @return 변환된 문자열을 리턴한다
	 */
	public static String removeChar(String str, char ch) {
		return removeChar(str, String.valueOf(ch));
	}

	/**
	 * 원본문자열(str)에서 문자열(ch)을 찾아 제거한다 <BR>
	 *
	 * @param str
	 *            입력문자열
	 * @param 제거할
	 *            문자열
	 * @return 변환된 문자열을 리턴한다
	 */
	public static String removeChar(String str, String ch) {
		StringBuffer buff = new StringBuffer();
		if( str != null ){
			StringTokenizer token = new StringTokenizer(str, ch);
			while (token.hasMoreTokens()) {
				buff.append(token.nextToken());
			}
		}
		return buff.toString();
	}

	/**
	 * 문자열을 정수로 변환한다.
	 * 입력문자열이 NULL 일 경우에는 0, 그외는 변환된 정수를 리턴한다.
	 * @param String
	 * @return int
	 */

	public static int str2int(String str) {
		if (str == null)
			return 0;
		else
			return Integer.valueOf(str).intValue();
	}

    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * <p>문자열 내부의 콤마 character(,)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str 입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }

    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }

    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr = source;

        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(object).append(nextStr);
            return rtnStr.toString();
        } else {
            return source;
        }
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     *
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr = source;

        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (srcStr.indexOf(chA) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(chA));
                nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
                srcStr = rtnStr.append(preStr).append(object).append(nextStr).toString();
            }
        }

        return srcStr;
    }

    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.</p>
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  검색 문자열
     * @param searchStr  검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }

    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>defaultStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo", "bar")= "foo"
     * StringUtil.decode("", null, "foo", "bar") = "bar"
     * StringUtil.decode(null, "", "foo", "bar") = "bar"
     * StringUtil.decode("하이", "하이", null, "bar") = null
     * StringUtil.decode("하이", "하이  ", "foo", null) = null
     * StringUtil.decode("하이", "하이", "foo", "bar") = "foo"
     * StringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @param defaultStr sourceStr와 compareStr의 값이 다를 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 defaultStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
        if (sourceStr == null && compareStr == null) {
            return returnStr;
        }

        if (sourceStr == null && compareStr != null) {
            return defaultStr;
        }

        if (sourceStr.trim().equals(compareStr)) {
            return returnStr;
        }

        return defaultStr;
    }

    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>sourceStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo") = "foo"
     * StringUtil.decode("", null, "foo") = ""
     * StringUtil.decode(null, "", "foo") = null
     * StringUtil.decode("하이", "하이", "foo") = "foo"
     * StringUtil.decode("하이", "하이 ", "foo") = "하이"
     * StringUtil.decode("하이", "바이", "foo") = "하이"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 sourceStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr) {
        return decode(sourceStr, compareStr, returnStr, sourceStr);
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object) {
        String string = "";

        if (object != null) {
            string = object.toString().trim();
        }

        return string;
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(Object src) {
        //if (src != null && src.getClass().getName().equals("java.math.BigDecimal")) {
        if (null != src && src instanceof BigDecimal) {
            return ((BigDecimal) src).toString();
        }

        if (src == null || src.equals("null")) {
            return "";
        } else {
            return ((String) src).trim();
        }
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(String src) {

        if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
            return "";
        } else {
            return src.trim();
        }
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(Object src) {

        if (src == null || src.equals("null")) {
            return 0;
        } else {
            return Integer.parseInt(((String) src).trim());
        }
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(String src) {

        if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
            return 0;
        } else {
            return Integer.parseInt(src.trim());
        }
    }

    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된
     * 모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  공백문자가 제거도어야 할 문자열
     * @return the 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }

        return new String(chs, 0, count);
    }

    /**
     * Html 코드가 들어간 문서를 표시할때 태그에 손상없이 보이기 위한 메서드
     *
     * @param strString
     * @return HTML 태그를 치환한 문자열
     */
    public static String checkHtmlView(String strString) {
        String strNew = "";

        StringBuffer strTxt = new StringBuffer("");

        char chrBuff;
        int len = strString.length();

        for (int i = 0; i < len; i++) {
            chrBuff = (char) strString.charAt(i);

            switch (chrBuff) {
                case '<':
                    strTxt.append("&lt;");
                    break;
                case '>':
                    strTxt.append("&gt;");
                    break;
                case '"':
                    strTxt.append("&quot;");
                    break;
                case 10:
                    strTxt.append("<br>");
                    break;
                case ' ':
                    strTxt.append("&nbsp;");
                    break;
                //case '&' :
                //strTxt.append("&amp;");
                //break;
                default:
                    strTxt.append(chrBuff);
                    break;
            }
        }

        strNew = strTxt.toString();

        return strNew;
    }

    /**
     * <p>{@link String#toLowerCase()}를 이용하여 소문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 소문자로 변환되어야 할 문자열
     * @return 소문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    /**
     * <p>{@link String#toUpperCase()}를 이용하여 대문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 대문자로 변환되어야 할 문자열
     * @return 대문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }

        return str.substring(start);
    }

    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }

        return str.substring(0, end);
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
        if (isEmpty(str)) {
            return str;
        }

        String srcStr = str;
        srcStr = stripStart(srcStr, stripChars);

        return stripEnd(srcStr, stripChars);
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @param arraylength 배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }

        return returnVal;
    }

    /**
     * 랜덤 문자 생성(A ~ Z)
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {

    	Random random = new Random();
    	random.setSeed(new Date().getTime());

    	String randomStr = "";
    	for  ( int i = 0 ; i < length ; i++ )  {
    		randomStr += String.valueOf( (char) ((int) (random.nextInt(26)) + 65) );
    	}

    	return randomStr;

    }

    /**
     * 랜덤 문자 생성(0 ~ 9)
     * @param length
     * @return
     */
    public static String getRandomInt(int length) {

    	Random random = new Random();
    	random.setSeed(new Date().getTime());

    	String randomStr = "";
    	for  ( int i = 0 ; i < length ; i++ )  {
    		randomStr += String.valueOf( random.nextInt(10) );
    	}

    	return randomStr;

    }

    /**
     * 랜덤 문자 생성(A ~ Z, 0 ~ 9)
     * @param length
     * @return
     */
    public static String getRandomStrAndInt(int length) {

    	Random random = new Random();
    	random.setSeed(new Date().getTime());

    	String randomStr = "";
    	for  ( int i = 0 ; i < length ; i++ )  {
    		if  ( random.nextBoolean() )  {
    			randomStr += String.valueOf( (char) ((int) (random.nextInt(26)) + 65) );
    		}
    		else  {
    			randomStr += String.valueOf( random.nextInt(10) );
    		}
    	}

    	return randomStr;

    }

    /**
     * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을
     * 복원하는 기능을 제공함 String temp = new String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601"); => UTF-8 에서
     * EUC-KR
     *
     * @param srcString
     *            - 문자열
     * @param srcCharsetNm
     *            - 원래 CharsetNm
     * @param charsetNm
     *            - CharsetNm
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
    public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm) {

        String rtnStr = null;

        if (srcString == null)
            return null;

        try {
            rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
        } catch (UnsupportedEncodingException e) {
            rtnStr = null;
        }

        return rtnStr;
    }

    /**
         * 특수문자를 웹 브라우저에서 정상적으로 보이기 위해 특수문자를 처리('<' -> & lT)하는 기능이다
         * @param   srcString       - '<'
         * @return  변환문자열('<' -> "&lt"
         * @exception MyException
         * @see
         */
    public static String getSpclStrCnvr(String srcString) {

        String rtnStr = null;

        try {
            StringBuffer strTxt = new StringBuffer("");

            char chrBuff;
            int len = srcString.length();

            for (int i = 0; i < len; i++) {
                chrBuff = (char) srcString.charAt(i);

                switch (chrBuff) {
                    case '<':
                        strTxt.append("&lt;");
                        break;
                    case '>':
                        strTxt.append("&gt;");
                        break;
                    case '&':
                        strTxt.append("&amp;");
                        break;
                    default:
                        strTxt.append(chrBuff);
                        break;
                }
            }

            rtnStr = strTxt.toString();

        } catch (Exception e) {
            LOGGER.debug("{}", e);
        }

        return rtnStr;
    }

    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {

        String rtnStr = null;

        // 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
        String pattern = "yyyyMMddhhmmssSSS";

        SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        rtnStr = sdfCurrent.format(ts.getTime());

        return rtnStr;
    }

    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String strToDateFmt(String yyyymmdd, String str) {

        String rtnStr = null;

        if( yyyymmdd.length() >= 8 ){
        	rtnStr = yyyymmdd.substring(0, 4) + str + yyyymmdd.substring(4, 6) + str + yyyymmdd.substring(6, 8);
        }

        return rtnStr;
    }

    /**
     * String 형식 날짜 yyyy-MM-dd hh:mm:ss 로 변환
     *
     * @param dateTime
     * @param str
     * @return
     */
    public static String strToDateTimeFmt(String dateTime, String str) {

        String rtnStr = null;

        if( dateTime.length() >= 14 ){
        	rtnStr = dateTime.substring(0, 4) + str
        				+ dateTime.substring(4, 6) + str
        				+ dateTime.substring(6, 8) + " "
        				+ dateTime.substring(8, 10) + ":"
        				+ dateTime.substring(10, 12) + ":"
        				+ dateTime.substring(12, 14);
        }

        return rtnStr;
    }

    /**
     * html의 특수문자를 표현하기 위해
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String getHtmlStrCnvr(String srcString) {

        String tmpString = srcString;

        tmpString = tmpString.replaceAll("&lt;", "<");
        tmpString = tmpString.replaceAll("&gt;", ">");
        tmpString = tmpString.replaceAll("&amp;", "&");
        tmpString = tmpString.replaceAll("&nbsp;", " ");
        tmpString = tmpString.replaceAll("&apos;", "\'");
        tmpString = tmpString.replaceAll("&quot;", "\"");

        return tmpString;

    }

    public static boolean isEmpty(Object object)
    {
      if (object == null) return true;

      if ((object instanceof String))
        return "".equals(object.toString().trim());
      if ((object instanceof List))
        return ((List)object).isEmpty();
      if ((object instanceof Map))
        return ((Map)object).isEmpty();
      if ((object instanceof Object[])) {
        return Array.getLength(object) == 0;
      }
      return object == null;
    }

    public static boolean isNotEmpty(Object object) {
      return !isEmpty(object);
    }

    public static String xss(String src)
    {
      if (isEmpty(src)) return "";

      StringBuffer buf = new StringBuffer();
      char[] c = src.toCharArray();
      int len = c.length;
      for (int i = 0; i < len; i++)
      {
        if (c[i] == '<') buf.append("&lt;");
        else if (c[i] == '>') buf.append("&gt;"); else
          buf.append(c[i]);
      }
      return buf.toString();
    }

    public static String unxss(String src) {
    	String rtnVal = "";
    	if (isEmpty(src)) return "";

    	rtnVal = src.replaceAll("&lt;", "<");
    	rtnVal = src.replaceAll("&gt;", ">");

	    return rtnVal;
    }

    public static String sqlInjection(String src){
    	if (isEmpty(src)) return "";

    	StringBuilder buffer = new StringBuilder();
    	char[] c = src.toCharArray();
    	int len = c.length;

    	for (int i = 0; i < len; i++) {
    		if ((c[i] == '\'') || (c[i] == '"') || (c[i] == '\\') || (c[i] == '/') ||
    			(c[i] == ';') || (c[i] == ':') || (c[i] == '-') || (c[i] == '+') || (c[i] == '%')) {
    			continue;
    		}
    		buffer.append(c[i]);
    	}

    	return buffer.toString();
    }

    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param src 원본 문자열
     * @param delimiter 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String src, String delimiter)
    {
      if (isEmpty(src)) return new String[0];

      int maxparts = src.length() / delimiter.length() + 2;
      int[] positions = new int[maxparts];
      int dellen = delimiter.length();

      int i = 0;
      int j = 0;
      int count = 0;
      positions[0] = (-dellen);
      while ((i = src.indexOf(delimiter, j)) != -1)
      {
        count++;
        positions[count] = i;
        j = i + dellen;
      }
      count++;
      positions[count] = src.length();

      String[] result = new String[count];

      for (i = 0; i < count; i++) {
        result[i] = trim(src.substring(positions[i] + dellen, positions[(i + 1)]));
      }
      return result;
    }

    public static final String encode(String src, String soruceEncoding, String targetEncoding)
    {
      if (isEmpty(src)) return "";

      String ss = null;
      try
      {
        ss = new String(src.getBytes(soruceEncoding), targetEncoding);
      }
      catch (UnsupportedEncodingException uee) {
        return src;
      }
      catch (NullPointerException nee) {
        return src;
      }
      return ss;
    }

    public static String trim(String src) {
      if (isEmpty(src)) return "";
      return src.trim();
    }

    public static String removeDot(String dateStr){
        if(StringUtils.isNotEmpty(dateStr) && dateStr !=null
                && dateStr.indexOf(".") > 0){
            return dateStr.replaceAll("\\.", "");
        }
        return dateStr;
    }


	/**
	 * 전문통신을 위한 Byte 패딩을 한다.
	 *
	 * @param data
	 * @return
	 * @throws IOException
	 */
    public static String fillEmpty(String str, String attr, int len, String encode) throws IOException {
		String value = str;
		//value = cutIfMaxLengthExceed(len, value);

		int valueLen = 0;
		if(encode.equals("")){
			valueLen = value.getBytes().length;
		}
		else {
			valueLen = value.getBytes(encode).length;
		}

		int totalLen = len;
		while(valueLen < totalLen) {
			value += " ";
			valueLen++;
		}
		return value;

	}

	public static String cutIfMaxLengthExceed(int max, String value) {
		return cutIfMaxLengthExceed(max, value, false);
	}

	public static String cutIfMaxLengthExceed(int max, String value, boolean reverse) {
		if(value.getBytes().length > max) {
			String newVal = null;
			if(reverse) {
				newVal = StringUtils.right(value, value.length() -1);
			}else {
				newVal = StringUtils.left(value, value.length() -1);
			}
			return cutIfMaxLengthExceed(max, newVal, reverse);
		}else {
			return value;
		}
	}

	public static byte[] copyString2ByteArray(String src, byte[] dest, int destStart, int copyLen) {
		byte[] bt = dest;
		String tgtStr = src;

		if (tgtStr == null || tgtStr == null) {
			tgtStr = "";
		} else if (tgtStr.getBytes().length > copyLen) {
				System.arraycopy(tgtStr.getBytes(), 0, bt, destStart, copyLen);
		} else {
				System.arraycopy(tgtStr.getBytes(), 0, bt, destStart, tgtStr.getBytes().length);
		}

		return bt;

	}

	public static String getStringFromBytes(byte[] textB, int start, int size){
		String rtnVal = "";
		byte[] valB = new byte[size];

		try{
			System.arraycopy(textB, start, valB, 0, size);
			rtnVal = new String(valB, "KSC5601").trim();
		} catch(Exception e){
			throw new HMException(e.getMessage());
		}

		return rtnVal;
	}

	/**
	 * 정수를 문자열로 변환한다 <br>
	 * 변환된 문자열을 리턴한다.
	 * @param int
	 * @return String
	 */
	public static String int2Str(int i) {
		return String.valueOf(i);
	}

	/**
	 * 주어진 길이보다 길이가 작은 문자열을 앞에 0을 붙여 패딩한다 <BR>
	 * 앞에 '0'으로 패딩된 문자열을 리턴한다. 단, 주어진 길이보다 크거나 같으면 원본문자열을 그대로 리턴한다
	 * @param String
	 * @param int
	 * @return String
	 */
	public static String paddingZero(String str, int len) {
		int strLen = str.length();
		int cab = 0;
		String tmp = "";
		if (strLen >= len)
			return str;
		else
			cab = len - strLen;

		for (int ii = 0; ii < cab; ii++) {
			tmp = "0" + tmp;
		}

		return tmp + str;
	}

	// YY MM
	public static String getDateYYMM(String date, String chrcrt) {
		String dateStr = date.substring(2, 4) + chrcrt
						+ date.substring(4, 6);
		return dateStr;
	}

	// YY년 MM월
	public static String getKorDateYYMM(String date) {
		String dateStr = date.substring(2, 4) + "년 "
						+ date.substring(4, 6) +"월";
		return dateStr;
	}

	// YY MM DD
	public static String getDateYYMMDD(String date, String chrcrt) {
		String dateStr = date.substring(2, 4) + chrcrt
						+ date.substring(4, 6) + chrcrt
						+ date.substring(6, 8);
		return dateStr;
	}

	// YY년 MM월 DD일
	public static String getKorDateYYMMDD(String date) {
		String dateStr = date.substring(2, 4) + "년 "
						+ date.substring(4, 6) + "월 "
						+ date.substring(6, 8) + "일";
		return dateStr;
	}

	// YY MM DD HH:MI:SS
	public static String getDateTimeYYMMDD(String date, String chrcrt) {
		String dateStr = date.substring(2, 4) + chrcrt
						+ date.substring(4, 6) + chrcrt
						+ date.substring(6, 8) + " "
						+ date.substring(8, 10) + ":"
						+ date.substring(10, 12) + ":"
						+ date.substring(12, 14);
		return dateStr;
	}

	// YYYY MM
	public static String getDateYYYYMM(String date, String chrcrt) {
		String dateStr = date.substring(0, 4) + chrcrt
						+ date.substring(4, 6);
		return dateStr;
	}

	// YYYY년 MM월
	public static String getKorDateYYYYMM(String date) {
		String dateStr = date.substring(0, 4) + "년 "
						+ date.substring(4, 6) +"월";
		return dateStr;
	}

	// YYYY MM DD
	public static String getDateYYYYMMDD(String date, String chrcrt) {
		String dateStr = date.substring(0, 4) + chrcrt
						+ date.substring(4, 6) + chrcrt
						+ date.substring(6, 8);
		return dateStr;
	}

	// YYYY년 MM월 DD일
	public static String getKorDateYYYYMMDD(String date) {
		String dateStr = date.substring(0, 4) + "년 "
						+ date.substring(4, 6) + "월 "
						+ date.substring(6, 8) + "일";
		return dateStr;
	}

	// YYYY MM DD HH:MI:SS
	public static String getDateTimeYYYYMMDD(String date, String chrcrt) {
		String dateStr = date.substring(0, 4) + chrcrt
						+ date.substring(4, 6) + chrcrt
						+ date.substring(6, 8) + " "
						+ date.substring(8, 10) + ":"
						+ date.substring(10, 12) + ":"
						+ date.substring(12, 14);
		return dateStr;
	}

	// 정수 천단위 콤바
	public static String getCommaFormat(Integer val) {
		String returnVal = "";
		if( val != null ) {
			DecimalFormat formatter = new DecimalFormat("###,##0");
			returnVal = formatter.format( val );
		}
		return returnVal;
	}

	// 실수형 포맷(소수점 불필요 0 제거)
	public static String doubleFormat(Double val) {
		String returnVal = "";
		if  ( val != null )  {
			DecimalFormat formatter = new DecimalFormat("0.####");
			returnVal = formatter.format(val);
		}
		return returnVal;
	}

	// array contains
	public static boolean arrayContains(String[] array, String str) {

		boolean result = false;
		if  ( array != null )  {
			for  ( String arrayStr : array )  {
				if  ( arrayStr.equals(str) )  {
					result = true;
					break;
				}
			}
		}
		return result;

	}

	public static boolean byteCheck(String str, int maxByte) {

        int strLength = 0;
        char[] strChar = str.toCharArray();
		for  ( int i = 0 ; i < strChar.length ; i++)  {
			if  ( strChar[i] < 128 )  {
				strLength++;
			}  else  {
				strLength += 2;
			}
		}

        if  ( strLength > maxByte )  {
            return false;
        }
        else  {
            return true;
        }

	}

	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = null;
		ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getNullToSpace(String param) {
		return (param == null) ? "" : param.trim();
	}

	public static String isEmptyDefault(String input, String defaultVal) {
		if ( input == null || input.trim().equals("") ) {
			input = defaultVal;
		}
		return input;
	}

	public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }

	// 문자에서 숫자만 추출
    public static String removeKor(String str) {
		String rtn = str.replaceAll("[^0-9?!\\.!\\_^a-zA-Z]", "");
		return rtn;
	}

    // 문자 우측 변환
    public static String subStringRightPad(String str, int changeLength, String changeStr) {

    	int totalLength = str.length();

    	String resultStr = str.substring(0, totalLength-changeLength);
    	resultStr = StringUtils.rightPad(resultStr, totalLength, changeStr);

    	return resultStr;

    }

}
