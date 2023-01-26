package core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 날짜처리 관련 Utils (org.apache.commons.lang.time.DateUtils 상속함)
 *
 * <pre>
 * [ 개정이력(Modification Information) ]
 * --------------  --------  ----------------------
 * 2014. 9. 23.    ysKo      신규생성
 * --------------  --------  ----------------------
 * </pre>
 *
 * @version 1.0
 * @Copyright : Heart Media CO.LTD.
 * @Project : hm-core-egov30
 * @Name : DateUtils
 * @Desc :
 *
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static Logger log = LoggerFactory.getLogger(DateUtils.class);

	private static final String BASE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String BASE_FULL_FORMAT = "yyyyMMddHHmmss";
	public static final String FULL_DATE_FORMAT = "yyyyMMddHHmmss";
	public static final String PRINT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String MIN_DATE_FORMAT = "HH:mm";

	/**
	 * get Current DateTime with BASE_DATE_FORMAT
	 *
	 * @return the String
	 */
	public static String getDate() {
		return getDate(DateUtils.BASE_DATE_FORMAT);
	}

	/**
	 * <pre>
	 * get Current DateTime with specific Date Format
	 * </pre>
	 *
	 * @param dateFormat
	 * @return
	 */
	public static String getDate(String dateFormat) {
		return DateUtils.convertDateFormat(new Date(), new SimpleDateFormat(dateFormat));
	}

	/**
	 * <pre>
	 * nextDays일 이후의 일자정보를 얻는다.
	 * </pre>
	 *
	 * @param nextDays
	 * @param dateFormat
	 * @return
	 */
	public static String getNextDate(int nextDays, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, nextDays);
		return DateUtils.convertDateFormat(cal.getTime(), new SimpleDateFormat(dateFormat));
	}

	/**
	 * <pre>
	 * srcDateFormat 형으로 지정된 일자 정보를 trgDateFormat형태로 변경
	 * </pre>
	 *
	 * @param sourceString
	 * @param srcDateFormat
	 * @param trgDateFormat
	 * @return the String
	 */
	public static String convertDateFormat(String sourceString, String srcDateFormat, String trgDateFormat) {
		if (sourceString == null || isEmpty(sourceString) || sourceString.startsWith("0000")
				|| sourceString.startsWith("00:00"))
			return "";

		Date sourceDate = null;
		try {
			sourceDate = new SimpleDateFormat(srcDateFormat).parse(sourceString);
		} catch (ParseException e) {
			return "";
		}
		return DateUtils.convertDateFormat(sourceDate, new SimpleDateFormat(trgDateFormat));
	}

	/**
	 * <pre>
	 * 일자 정보(yyyyMMddHHmmss)를 지정된 포멧으로 변경
	 * </pre>
	 *
	 * @param sourceString
	 * @param dateFormat
	 * @return the String
	 */
	public static String convertDateFormat(String sourceString, String dateFormat) {
		Date sourceDate = null;

		try {
			sourceDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(sourceString);
		} catch (ParseException e) {
		}
		return DateUtils.convertDateFormat(sourceDate, new SimpleDateFormat(dateFormat));
	}

	/**
	 * <pre>
	 * 일자 정보(yyyyMMddHHmmss)를 지정된 포멧으로 변경
	 * </pre>
	 *
	 * @param sourceDate
	 * @param dateFormat
	 * @return the String
	 */
	public static String convertDateFormat(Date sourceDate, String dateFormat) {
		return DateUtils.convertDateFormat(sourceDate, new SimpleDateFormat(dateFormat));
	}

	/**
	 * <pre>
	 * get format converted Date string with specific Date Format
	 * </pre>
	 *
	 * @param sourceDate
	 * @param dateForm
	 * @return the String
	 */
	public static String convertDateFormat(Date sourceDate, SimpleDateFormat dateForm) {
		return dateForm.format(sourceDate);
	}

	public static String dateDiffMinStr(String fromStr, String toStr) {
		StringBuffer resultBiff = new StringBuffer();
		Date from = DateUtils.convertDate(fromStr, null);
		Date to = DateUtils.convertDate(toStr, null);

		long time = (to.getTime() - from.getTime()) / 1000;
		long hour = time / 3600;
		long min = time % 3600 / 60;
		long sec = time % 3600 % 60;

		if (hour > 0) {
			resultBiff.append(hour + "시간 ");

		}
		if (min > 0) {
			resultBiff.append(min + "분 ");

		}

		return resultBiff.toString();
	}

	public static long dateDiffDays(String fromStr, String toStr) {
		StringBuffer resultBiff = new StringBuffer();
		Date from = DateUtils.convertDate(fromStr, null);
		Date to = DateUtils.convertDate(toStr, null);

		long time = (to.getTime() - from.getTime()) / 1000;

		long day = time / 60 / 60 / 24;

		return day;
	}

	public static long dateDiffMin(String fromStr, String toStr) {
		StringBuffer resultBiff = new StringBuffer();
		Date from = DateUtils.convertDate(fromStr, null);
		Date to = DateUtils.convertDate(toStr, null);

		long time = (to.getTime() - from.getTime()) / 1000;

		long min = time / 60;

		return min;
	}

	public static Date convertDate(String sourceString, String dateFormat) {
		Date sourceDate = null;

		try {
			sourceDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(sourceString);
		} catch (ParseException e) {
		}
		return sourceDate;
	}

	public static String dateDiff(Integer min) {
		StringBuffer resultBiff = new StringBuffer();

		long hour = min % 60;

		long min_etc = min % 60;

		if (hour > 0) {
			resultBiff.append(hour + "시간 ");
		}
		if (min > 0) {
			resultBiff.append(min_etc + "분 ");

		}

		return resultBiff.toString();
	}

	public static boolean validateWeek(String sourceString) {
		sourceString = isEmptyDefault(sourceString, "1,2,3,4,5,6,7,8");
		boolean rtn = false;
		String[] strList = sourceString.split(",");
		ArrayList<String> array = new ArrayList<String>();
		Collections.addAll(array, strList);
		String week = DateUtils.getDate("u");
		for (String str : array) {
			if (str.equals(week)) {
				rtn = true;
				break;
			}
		}

		return rtn;

	}

	public static Date addMonths(Date date, int numMonths) {
		Date rtnDate = new Date();
		rtnDate = date;
		rtnDate.setMonth((date.getMonth() - 1 + numMonths) % 12 + 1);
		return rtnDate;
	}

	public static Date addDays(Date date, int days) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		Date otherDay = calendar.getTime();

		return otherDay;

	}

	/**
	 * <pre>
	 * nextDays일 이후의 일자정보를 얻는다.
	 * </pre>
	 *
	 * @param nextDays
	 * @param dateFormat
	 * @return
	 */
	public static String getNextDate(int nextDays, String startDate, String dateFormat) {

		Date currentDate = DateUtils.convertDate(startDate, "yyyyMMddHHmmss");
		currentDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DATE, nextDays);
		return DateUtils.convertDateFormat(cal.getTime(), new SimpleDateFormat(dateFormat));

	}

	/**
	 * <pre>
	 * nextDays일 이후의 일자정보를 얻는다.
	 * </pre>
	 *
	 * @param nextDays
	 * @param dateFormat
	 * @return
	 */
	public static String getNextMonth(int nextDays, String startDate, String dateFormat) {

		Date currentDate = DateUtils.convertDate(startDate, "yyyyMMddHHmmss");
		currentDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MONTH, nextDays);
		cal.add(Calendar.DATE, -1);
		return DateUtils.convertDateFormat(cal.getTime(), new SimpleDateFormat(dateFormat));

	}

	public static String getNextMonthLastDate(String sourceString, String dateFormat) {
		Date date = DateUtils.convertDate(sourceString, DateUtils.FULL_DATE_FORMAT);
		System.out.println(DateUtils.convertDateFormat(date, "yyyyMMdd"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 2);
		cal.add(Calendar.DATE, -1);

		return DateUtils.convertDateFormat(cal.getTime(), new SimpleDateFormat(dateFormat));
	}

	public static String addMonthsStr(String sourceString, String trgDateFormat, int numMonths) {
		Date sourceDate = null;
		try {
			sourceDate = new SimpleDateFormat(trgDateFormat).parse(sourceString);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}

		sourceDate.setMonth((sourceDate.getMonth() - 1 + numMonths) % 12 + 1);
		return DateUtils.convertDateFormat(sourceDate, new SimpleDateFormat(trgDateFormat));
	}

	public static boolean isEmpty(String input) {
		return (input == null || input.trim().equals(""));
	}

	public static String isEmptyDefault(String input, String defaultVal) {
		if ( input == null || input.trim().equals("") ) {
			input = defaultVal;
		}
		return input;
	}

	public static Date getDateStartTime(Date date){
		if(date != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.AM_PM, 0);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			return cal.getTime();
		}
		return null;
	}
}
