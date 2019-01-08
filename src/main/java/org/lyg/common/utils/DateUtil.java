package org.lyg.common.utils;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class DateUtil{
	public static final String LONG_ALL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String LONG_DATE_PATTERN = "yyyy-MM-dd HH:mm";
	public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String SHORT_HH_MM_PATTERN = "HH:mm";
	public static final String SHORT_DATE_REG = "\\d{4}-\\d{1,2}-\\d{1,2}";
	public static final String LONG_DATE_REG = "[\\d]{4}[-][\\d]{2}[-][\\d]{2} [\\d]{1,2}:[\\d]{1,2}";
	public static final String LONG_DATE_REG_HAVE_SS = "[\\d]{4}[-][\\d]{2}[-][\\d]{2} [\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}";
	public static final String LONG_DATE_REG_HAVE_MS = "[\\d]{4}[-][\\d]{2}[-][\\d]{2} [\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}.[\\d]{1,2}";
	public static final int SUNDAY = Calendar.SUNDAY;
	public static final int MONDAY = Calendar.MONDAY;
	public static final int TUESDAY = Calendar.TUESDAY;
	public static final int WEDNESDAY = Calendar.WEDNESDAY;
	public static final int THURSDAY = Calendar.THURSDAY;
	public static final int FRIDAY = Calendar.FRIDAY;
	public static final int SATURDAY = Calendar.SATURDAY;
	private static final String[] DAY_OF_WEEK = {""};

	/**
	 * @return Date today
	 */
	public static Date getNow() {
		Date now = new Date();
		return now;
	}

	/**
	 * yyy-mm-dd
	 * @return String
	 */
	public static String getShortNow(){
		String sNow;
		Date now = new Date();
		sNow = dateFormater(now,SHORT_DATE_PATTERN);
		return sNow;
	}

	/**
	 * yyy-mm-dd hh:mm
	 * @return String
	 */
	public static String getLongNow(){
		String sNow;
		Date now = new Date();
		sNow = dateFormater(now,LONG_DATE_PATTERN);
		return sNow;
	}

	/**
	 * yyy-mm-dd
	 * @return String
	 */
	public static String getHMTime(){
		String sNow;
		Date now = new Date();
		sNow = dateFormater(now,SHORT_HH_MM_PATTERN);
		return sNow;
	}

	/**
	 * @param date
	 * @param sPattern
	 * @return String
	 */
	public static String dateFormater(Date date,String sPattern) {
		String sDate;
		if(null == date){
			sDate = "";
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat(sPattern);
			sDate = sdf.format(date);
		}
		return sDate;
	}

	/**
	 * @param date
	 * @return String
	 */
	public static String shortDateFormater(Date date) {
		String sDate;
		if(null == date){
			sDate = "";
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat(SHORT_DATE_PATTERN);
			sDate = sdf.format(date);
		}
		return sDate;
	}

	/**
	 * @param date
	 * @return date
	 */
	public static Date shortDate(Date d) {
		Date date;
		if(null == d){
			date = new Date();
		}else{
			String str = shortDateFormater(d);
			date = parseDate(str);
		}
		return date;
	}

	/**
	 * @param date
	 * @return String
	 */
	public static String longDateFormater(Date date) {
		String sDate;
		if(null == date){
			sDate = "";
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_PATTERN);
			sDate = sdf.format(date);
		}
		return sDate;
	}

	/**
	 * @param date yyyy-mm-dd HH:MI:SS
	 * @return Date
	 */
	public static Date getLongDate(String sDate){
		Date date = null;
		if(sDate.matches(LONG_DATE_REG_HAVE_SS)){
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(sDate.split("-")[0]) );
			cal.set(Calendar.MONTH, Integer.parseInt(sDate.split("-")[1])-1 );
			cal.set(Calendar.DATE, Integer.parseInt(
					sDate.substring(sDate.lastIndexOf("-")+1,sDate.lastIndexOf(" "))));
			cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(
					sDate.substring(sDate.lastIndexOf(" ")+1,sDate.indexOf(":"))));
			cal.set(Calendar.MINUTE, Integer.parseInt(sDate.split(":")[1]) );
			cal.set(Calendar.SECOND, Integer.parseInt(sDate.split(":")[2]) );
			date = cal.getTime(); 
		} 
		else if(sDate.matches(LONG_DATE_REG_HAVE_MS)){
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(sDate.split("-")[0]) );
			cal.set(Calendar.MONTH, Integer.parseInt(sDate.split("-")[1])-1 );
			cal.set(Calendar.DATE, Integer.parseInt(
					sDate.substring(sDate.lastIndexOf("-")+1,sDate.lastIndexOf(" "))));
			cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(
					sDate.substring(sDate.lastIndexOf(" ")+1,sDate.indexOf(":"))));
			cal.set(Calendar.MINUTE, Integer.parseInt(sDate.split(":")[1]) );
			cal.set(Calendar.SECOND, Integer.parseInt(
					sDate.substring(sDate.lastIndexOf(":")+1,sDate.lastIndexOf("."))));
			date = cal.getTime(); 
		}
		return date;   	
	}

	/**
	 * yyy-mm-dd
	 * @param sDate yyyy-mm-dd
	 * @return Date
	 */
	public static Date parseDate(String sDate){
		if( !sDate.matches(SHORT_DATE_REG) ){
			return null;  
		} 
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(sDate.split("-")[0]) );
		cal.set(Calendar.MONTH, Integer.parseInt(sDate.split("-")[1])-1 );
		cal.set(Calendar.DATE, Integer.parseInt(sDate.split("-")[2]) );
		Date date = cal.getTime(); 
		return date;
	}

	/**
	 * yyy-mm-dd mm:ss
	 * @param sDate yyyy-mm-dd mm:ss
	 * @return Date
	 */
	public static Date parseLongDate(String sDate){
		if( !sDate.matches(LONG_DATE_REG) ){
			return null;  
		} 
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(sDate.split("-")[0]) );
		cal.set(Calendar.MONTH, Integer.parseInt(sDate.split("-")[1])-1 );
		cal.set(Calendar.DATE, Integer.parseInt(
				sDate.substring(sDate.lastIndexOf("-")+1,sDate.lastIndexOf(" "))));
		cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(
				sDate.substring(sDate.lastIndexOf(" ")+1,sDate.lastIndexOf(":"))));
		cal.set(Calendar.MINUTE, Integer.parseInt(sDate.split(":")[1]) );
		Date date = cal.getTime(); 
		return date;    	
	}

	/**
	 * yyy-mm-dd
	 * @param date 
	 * @return String
	 */
	public static String getShortDate(Date date){
		String sShortDate;
		if(null == date){
			sShortDate = "";
		}else{
			sShortDate = dateFormater(date,SHORT_DATE_PATTERN);
		}
		return sShortDate;
	}

	/**
	 * h:mm
	 * @param date
	 * @return String
	 */
	public static String getShortDateHHMM(Date date){
		String sShortDate;
		if(null == date){
			sShortDate = "";
		}else{
			sShortDate = dateFormater(date,SHORT_HH_MM_PATTERN);
		}
		return sShortDate;
	}
	/**
	 * @param date 
	 * @return long
	 * */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		long millins = c.getTimeInMillis();
		return millins;
	}
	/**
	 * @return Date
	 */
	public static Date getYesterday(){
		Date now = new Date();
		Date yesterday = addDate(now,-1);
		return yesterday;    	
	}
	/**
	 * @param date yyyy-mm-dd
	 * @return Date
	 */
	public static Date getYesterday(String date){
		Date currentDate = parseDate(date);
		Date yesterday = addDate(currentDate,-1);
		return yesterday;     	
	}
	/**
	 * @param date yyyy-mm-dd
	 * @return Date
	 */
	public static Date getYesterday(Date date){
		String sShortDate = "";
		if(null == date){
			sShortDate = "";
		}else{
			sShortDate = dateFormater(date,SHORT_DATE_PATTERN);
		}
		if(null != date){
			sShortDate = dateFormater(date,SHORT_DATE_PATTERN);
		}
		Date currentDate = parseDate(sShortDate);
		Date yesterday = addDate(currentDate,-1);
		return yesterday;     	
	}
	
	/**
	 * @param date
	 * @param day
	 * @return Date
	 */
	public static Date addDate(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long)days) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * @param date
	 * @param day
	 * @return Date
	 */
	public static Date addDate(Date date,double days){
		Calendar c = Calendar.getInstance();
		long lDays = (long)(days * 24 * 3600 * 1000);
		c.setTimeInMillis(getMillis(date) + lDays);
		return c.getTime();
	}

	/**
	 * @param dateFrom 
	 * @param dateTo
	 * @return int   
	 * */
	public static int diffDate(Date dateFrom,Date dateTo){
		int iDiff = (int)((getMillis(dateTo) - getMillis(dateFrom)) / (24 * 3600 * 1000));
		return iDiff;
	}
	
	/**  
	 * @param dateFrom 
	 * @param dateTo
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getDateList(String dateFrom,String dateTo){
		List lstDate =  new ArrayList();
		int iDiff = diffDate(parseDate(dateFrom),parseDate(dateTo));
		Date newDate = new Date();
		for(int i =0;i<=iDiff;i++){
			newDate = addDate(parseDate(dateFrom),i);
			lstDate.add(shortDateFormater(newDate));
		}
		return lstDate;
	}
	
	/**
	 * @return Date
	 */
	public static Date getTomorrow(){
		Date now = new Date();
		Date tomorrow = addDate(now,1);
		return tomorrow;       	
	}
	
	/**
	 * @param date yyyy-mm-dd
	 * @return Date
	 */
	public static Date getTomorrow(String date){
		Date currentDate = parseDate(date);
		Date tomorrow = addDate(currentDate,1);
		return tomorrow;      	
	}
	
	/**
	 * @param date yyyy-mm-dd mm:ss
	 * @return Date
	 */
	public static Date getLongTomorrow(String date){
		Date currentDate = parseLongDate(date);
		Date tomorrow = addDate(currentDate,1);
		return tomorrow;      	
	}
	
	/**
	 * @return String
	 */
	public static String getYear(){
		Date now = new Date();
		String year = getYear(now); 
		return year;
	}
	
	/**
	 * @param date
	 * @return String
	 */
	public static String getYear(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		return year;
	}
	
	/**
	 * @return String
	 */
	public static String getMonth(){
		Date now = new Date();
		String month = getMonth(now); 
		return month;
	}
	
	/**
	 * @param date
	 * @return String
	 */
	public static String getMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String month = sdf.format(date);
		return month;
	}
	
	/**
	 * @return String
	 */
	public static String getDay(){
		Date now = new Date();
		String day = getDay(now); 
		return day;
	}
	
	/**
	 * @param date
	 * @return String
	 */
	public static String getDay(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String day = sdf.format(date);
		return day;
	}
	
	/**
	 * @return String
	 * yyyy-mm
	 */
	public static String getYearMonth(){
		String year = getYear();
		String month = getMonth();
		String sYearMonth = year + "-" + month;
		return sYearMonth;
	}
	
	/**
	 * @param date 
	 * @return String yyyy-mm
	 */
	public static String getYearMonth(Date date){
		String sYearMonth = dateFormater(date,"yyyy-MM");
		return sYearMonth;
	}
	
	/**
	 * @return String 
	 * yyyy-mm
	 */
	public static String getNextYearAndMonth() {
		String year = getYear();
		String month = getMonth();
		String date = year + "-" + month;
		String nextDate=null;
		if("12".equals(date.substring(5, 7))){
			nextDate = String.valueOf(Integer.parseInt(date.substring(0, 4))+1)+"-01";
		}
		else{
			if((Integer.parseInt(date.substring(5, 6))+1)>=10){
				nextDate = date.substring(0, 4)+"-"+String.valueOf(Integer.parseInt(date.substring(5, 7))+1);
			}else
			{nextDate = date.substring(0, 4)+"-0"+String.valueOf(Integer.parseInt(date.substring(5, 7))+1);}
		}
		return nextDate;
	}
	
	/**
	 * @return String 
	 * yyyy-mm
	 */
	public static String getNextYearAndMonth(String date) {
		String nextDate=null;
		if("12".equals(date.substring(5, 7))){
			nextDate = String.valueOf(Integer.parseInt(date.substring(0, 4))+1)+"-01";
		}
		else{
			if((Integer.parseInt(date.substring(5, 6))+1)>=10){
				nextDate = date.substring(0, 4)+"-"+String.valueOf(Integer.parseInt(date.substring(5, 7))+1);
			}else
			{nextDate = date.substring(0, 4)+"-0"+String.valueOf(Integer.parseInt(date.substring(5, 7))+1);}
		}
		return nextDate;
	}
	
	/*
	 * yyy-mm-dd
	 * @return String 
	 * yyyy-mm-dd
	 */
	public static String getMonthBegin(){
		Date now = getNow();
		String sMonthBegin = getMonthBegin(now);
		return sMonthBegin;
	}
	
	/**
	 * @param date 
	 * @return String
	 */
	public static String getMonthBegin(Date date){
		String sYearMonth = dateFormater(date,"yyyy-MM");
		String sMonthBegin = sYearMonth + "-01";
		return sMonthBegin;
	}

	/**
	 * @return String
	 */
	public static String getMonthEnd(){
		Date now = getNow();
		String sMonthEnd = getMonthEnd(now);
		return sMonthEnd;
	}	

	/**
	 * @param date
	 * @return String
	 */
	public static String getMonthEnd(Date date){
		Date monthBegin = parseDate(getMonthBegin(date));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(monthBegin);
		calendar.add(Calendar.MONTH,1);
		calendar.add(Calendar.DAY_OF_YEAR,-1);
		String sMonthEnd = getShortDate(calendar.getTime());
		return sMonthEnd;
	}

	/**
	 * @param date
	 * @param day
	 * @see SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
	 * @return String
	 */
	public static String getWeekDate(Date date,int day){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK,day);
		Date sunday = c.getTime();
		String sWeekDay = dateFormater(sunday,SHORT_DATE_PATTERN);
		return sWeekDay;
	}
	
	/**
	 * @param date
	 * @return String
	 */
	public static String getDayOfWeek(Date date){
		String sDayOfWeek;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		sDayOfWeek = DAY_OF_WEEK[day-1];
		return sDayOfWeek;
	}

	/**
	 * @param date
	 * @return String
	 */
	public static int getDayNumberOfWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return day;
	}	

	/**
	 * @param year
	 * @param month
	 * @return int 
	 */
	public static int getDays(int year,int month) {
		int days=0;
		switch(month){
		case 1:days=31; break;
		case 2:days=(((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? 29 : 28); break;
		case 3:days=31; break;
		case 4:days=30; break;
		case 5:days=31; break;
		case 6:days=30; break;
		case 7:days=31; break;
		case 8:days=31; break;
		case 9:days=30; break;
		case 10:days=31; break;
		case 11:days=30; break;
		case 12:days=31; break;
		}
		return days;
	}
	
	/**
	 * @param date1
	 * @param date2
	 * @return int
	 */
	public static int dateSimpleCompare(Date date1,Date date2){
		int iResult = 0;
		int iDiff = diffDate(date1,date2);
		if(iDiff>0){
			iResult = 1;
		}
		else if(iDiff<0){
			iResult = -1;
		}
		else{
			iResult = 0;
		}
		return iResult;
	}
	
	/**
	 * @param date1
	 * @param date2
	 * @return int
	 */    
	public static int dateSimpleCompare(String sDate1,String sDate2) {
		Date date1 = parseDate(sDate1);
		Date date2 = parseDate(sDate2);
		int iResult = dateSimpleCompare(date1, date2);
		return iResult;
	} 
	
	/**
	 * @param date1
	 * @param date2
	 * @return int
	 */ 
	public static int dateComplexCompare(Date date1,Date date2) {
		int iResult = 0;
		boolean bBefore = date1.before(date2);
		boolean bAfter = date1.after(date2);
		if(bBefore){
			iResult = 1;
		}
		else if(bAfter){
			iResult = -1;
		}
		else{
			iResult = 0;
		}
		return iResult;
	}
	
	/**
	 * @param date1
	 * @param date2 
	 * @return int
	 */ 
	public static int dateComplexCompare(String sDate1,String sDate2) {
		Date date1 = parseDate(sDate1);
		Date date2 = parseDate(sDate2);
		int iResult = dateComplexCompare(date1, date2);
		return iResult;
	}
	
	/**
	 * @param sDate* @param sPattern
	 * @return true--?false
	 * */
	public static boolean isDate(
			String sDate,
			String sPattern) {
		boolean bIsDate = false;

		try {
			if ((sDate != null) && (sDate.length() > 0)) {
				SimpleDateFormat fmt = new SimpleDateFormat(sPattern);
				Date dtCheck = fmt.parse(sDate);

				String sCheck = fmt.format(dtCheck);

				if (sDate.equals(sCheck)) {
					bIsDate = true;
				}
			}
		} catch (ParseException e) {
			bIsDate = false;
		}

		return bIsDate;
	}
	
	/**
	 * @param sDate  * @return true---?false---
	 *  */
	public static boolean isDate(String sDate) {
		boolean bIsDate = false;

		if (isDate(sDate, "yyyy/MM/dd")
				|| isDate(sDate, "yyyy/M/dd")
				|| isDate(sDate, "yyyy/M/d")
				|| isDate(sDate, "yyyy/MM/d")
				|| isDate(sDate, "yyyy-MM-dd")
				|| isDate(sDate, "yyyy-M-dd")
				|| isDate(sDate, "yyyy-M-d")
				|| isDate(sDate, "yyyy-MM-d")) {
			bIsDate = true;
		}
		return bIsDate;
	} 

	/**
	 * @return String
	 */
	public static String getNowDateTimeString() {
		Date currentTime = getNow();
		return dateFormater(currentTime, "yyyyMMddHHmmss");
	}  

	/**
	 * @return String
	 */
	public static String getNowDateTimeAllString() {
		Date currentTime = getNow();
		return dateFormater(currentTime, LONG_ALL_DATE_PATTERN);
	}

	/**
	 * @param date
	 * @return String
	 */
	public static Date getNextMonth(Date date, int i){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,i);
		return calendar.getTime();
	}

	/*
	 * String(yyyy-MM-dd HH:mm:ss)->?     * @param time
	 * @throws NoSuchAlgorithmException,UnsupportedEncodingException
	 * @author james.wu
	 */
	public  static long timeFormat(String time)throws Exception{
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date = format.parse(time);
		return date.getTime()/1000;
	}
	
	/**
	 * @param time
	 * @throws NoSuchAlgorithmException,UnsupportedEncodingException
	 * @author james.wu
	 */
	public  static Long getNowLong()throws Exception{
		String time = DateUtil.dateFormater(DateUtil.getNow(), "yyyy-MM-dd HH:mm:ss");
		return DateUtil.timeFormat(time);
	}
}
