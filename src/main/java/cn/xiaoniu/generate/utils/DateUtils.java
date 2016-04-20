package cn.xiaoniu.generate.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @描述：日期工具类 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 *
 * @author 何源
 * @时间  2015年8月20日下午1:57:08
 *
 */
public final class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	public static String FORMAT_SHORT = "yyyy-MM-dd";
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	/**
	 * 获得默认的 date pattern 　　
	 */
	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	/**
	 * 根据预设格式返回当前日期 　　
	 * 
	 * @return 　　
	 */
	public static String getNow() {
		return format(new Date());
	}

	/**
	 * 根据用户格式返回当前日期 　
	 * 
	 * @param format
	 *            　　
	 * @return 　　
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}

	/**
	 * 使用预设格式格式化日期 　　
	 * 
	 * @param date
	 *            　　
	 * @return 　　
	 */
	public static String format(Date date) {
		if(date==null){
			return "";
		}
		return format(date, getDatePattern());
	}

	/**
	 * 使用用户格式格式化日期 　
	 * 
	 * @param date
	 *            日期 　　
	 * @param pattern
	 *            日期格式 　　
	 * @return 　　
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param pattern yyyy-MM-dd or yyyy-MM or yyyy
	 * @return
	 */
	public static List<String> getBetweeenTime(Date startDate,Date endDate,String pattern) {
		int type = 0;
		if(pattern.contains("dd")){
			 type = Calendar.DATE;
		}else if(pattern.contains("MM")){
			 type = Calendar.MONTH;
		}else if(pattern.contains("yyyy")){
			 type = Calendar.YEAR;
		}
		String strStartDate = format(startDate,pattern);
		String strEndDate = format(endDate,pattern);
		Date theEndDate = parse(strEndDate,pattern);
		
		Date currDate = parse(strStartDate,pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		List<String> ret = new ArrayList<String>();
		while(!cal.getTime().after(theEndDate)){
			ret.add(format(cal.getTime(),pattern));
			cal.add(type,1);
		}
		return ret;
	}
	

	/**
	 * 使用预设格式提取字符串日期 　　
	 * 
	 * @param strDate
	 *            日期字符串 　　
	 * @return 　　
	 */
	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	/**
	 * 使用用户格式提取字符串日期 　　
	 *
	 * @param strDate
	 *            日期字符串 　　
	 * @param pattern
	 *            日期格式 　　
	 * @return 　　
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 使用用户格式提取字符串日期 　　
	 *
	 * @param date
	 *            日期 　　
	 * @param pattern
	 *            日期格式 　　
	 * @return 　　
	 * 
	 * @throws ParseException
	 */
	public static Date parse(Date date, String pattern) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(df.format(date));
	}

	/**
	 * 在日期上增加数个整月 　　
	 * 
	 * @param date
	 *            日期 　　
	 * @param n
	 *            要增加的月数 　　
	 * @return 　　
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上减去天数 　　
	 * 
	 * @param date
	 *            日期 　　
	 * @param n
	 *            要减去的天数 　　
	 * @return 　　
	 */
	public static Date subDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加天数 　　
	 *
	 * @param date
	 *            日期 　　
	 * @param n
	 *            要增加的天数 　　
	 * @return 　　
	 */
	public static Date addDay(Date date, int n) {
		if(date == null || n == 0){
			return date;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}
	
	/**
	 * 在日期上增加天数 　　
	 *
	 * @param date
	 *            日期 　　
	 * @param n
	 *            要增加的天数 　　
	 * @return 　　
	 */
	public static Date addHour(Date date, int n) {
		if(date == null || n == 0){
			return date;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, n);
		return cal.getTime();
	}

	/**
	 * 获取时间戳 　　
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取日期年份 　　
	 *
	 * @param date
	 *            日期 　　
	 * @return 　　
	 */
	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}

	/**
	 * 按默认格式的字符串距离今天的天数 　　
	 * 
	 * @param date
	 *            日期字符串 　　
	 * @return 　　
	 */
	public static int countDays(String date) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}

	/**
	 * 　　 按用户格式字符串距离今天的天数 　　
	 * 
	 * @param date
	 *            日期字符串 　　
	 * @param format
	 *            日期格式 　 　
	 * @return 　　
	 */
	public static int countDays(String date, String format) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, format));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}

	/**
	 * 
	 * countDays(计算连个时间点相隔多少天)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * 
	 *         author：zhou'sheng
	 */
	public static int countDays(Date date1, Date date2) {

		Date dateA, dateB, temp;

		try {
			dateA = parse(date1, "yyyy-MM-dd");
			dateB = parse(date2, "yyyy-MM-dd");
		} catch (ParseException e) {
			return 0;
		}

		if (dateA.getTime() == dateB.getTime()) {
			return 0;
		}

		if (dateA.getTime() < dateB.getTime()) {
			temp = dateA;
			dateA = dateB;
			dateB = temp;
		}

		Calendar cA = Calendar.getInstance();
		Calendar cB = Calendar.getInstance();
		cA.setTime(dateA);
		cB.setTime(dateB);

		long t1 = dateA.getTime();
		long t2 = dateB.getTime();

		return (int) (t1 / 1000 - t2 / 1000) / 3600 / 24;
	}

	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(Date date1, Date date2, int stype) {
		int n = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}
	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;

		
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			logger.error("时间比较错误：",e3);
		}

		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}

		n = n - 1;

		if (stype == 2) {
			n = (int) n / 365;
		}

		return n;
	}

	/**
	 * 
	 * compareDate(比较时间大小)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * 
	 */
	public static int compareDate(Date date1, Date date2) {
		if(date1 == null || null == date2){
			return 0;
		}
		try {
			if (date1.getTime() > date2.getTime()) {
				return 1;
			} else if (date1.getTime() < date2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 得到当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(date);
	}
	

	

	public static int getMonths(String date1, String date2) {
		int year, month;
		Date dateA, dateB, temp;

		dateA = parse(date1, "yyyy-MM-dd");
		dateB = parse(date2, "yyyy-MM-dd");

		if (dateA.getTime() == dateB.getTime()) {
			return 0;
		}

		if (dateA.getTime() < dateB.getTime()) {
			temp = dateA;
			dateA = dateB;
			dateB = temp;
		}

		Calendar cA = Calendar.getInstance();
		Calendar cB = Calendar.getInstance();
		cA.setTime(dateA);
		cB.setTime(dateB);

		year = cA.get(Calendar.YEAR) - cB.get(Calendar.YEAR);// 年
		month = cA.get(Calendar.MONTH) - cB.get(Calendar.MONTH);// 月

		if (year > 0) {
			if (month > 0) {
				if (cB.get(Calendar.DATE) > cA.get(Calendar.DATE)) {
					month--;
				} else if (cB.get(Calendar.DATE) == cA.get(Calendar.DATE)) {

				} else {

				}
			} else if (month == 0) {
				if (cB.get(Calendar.DATE) > cA.get(Calendar.DATE)) {
					month = -1;
				} else if (cB.get(Calendar.DATE) == cA.get(Calendar.DATE)) {

				} else {

				}
			} else {
				if (cA.get(Calendar.DATE) > cB.get(Calendar.DATE)) {

				} else if (cA.get(Calendar.DATE) == cB.get(Calendar.DATE)) {

				} else {
					month--;
				}
			}
		} else {
			if (month > 0) {
				if (cB.get(Calendar.DATE) > cA.get(Calendar.DATE)) {
					month--;
				} else if (cB.get(Calendar.DATE) == cA.get(Calendar.DATE)) {

				} else {

				}
			}
		}

		return year * 12 + month;
	}

}