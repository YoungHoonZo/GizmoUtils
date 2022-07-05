package com.innosoft.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class InnoDateUtil {

    public static final int TYPE_DOT = 1;
    public static final int TYPE_SLASH = 2;
    public static final int TYPE_DASH = 3;

    /**
     * 특정 날짜를 받아서 Date객체로 리턴한다.
     * @param sDate 소스날짜.
     * @param sFormat 소스날짜의 포맷
     * @return
     */
    public static Date toDateFromStringDate(String sDate, String sFormat){
        SimpleDateFormat fromFormat = new SimpleDateFormat(sFormat);
        Date date = new Date();
        try {
            date = fromFormat.parse(sDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 인자로 받은 date객체에서 특정구분(년, 월, 일)의 i의 값만큼
     * 더하거나 뺀다.
     * 예) date : 20170110, div : D, i : 5
     * 결과 : 20170105
     * @param date 대상 날짜의 date객체
     * @param div 계산할 구분 (Y:년도, M:월, D:일)
     * @param i 더하거나 뺄 숫자
     * @return
     */
    public static Date doDateCalc(Date date, String div, int i){
        Calendar cal = new GregorianCalendar(Locale.KOREA);
        cal.setTime(date);
        if(div.equalsIgnoreCase("Y")){
            cal.add(Calendar.YEAR, i);
        }
        else if(div.equalsIgnoreCase("M")){
            cal.add(Calendar.MONTH, i);
        }
        else if(div.equalsIgnoreCase("D")){
            cal.add(Calendar.DAY_OF_YEAR, i);
        }

        return cal.getTime();

    }

    /**
     * 인자로 받은 date객체에서 특정구분(년, 월, 일)의 i의 값만큼
     * 더하거나 뺀다.
     * 예) date : 20170110, div : D, i : 5
     * 결과 : 20170105
     * @param date 대상 날짜의 date객체
     * @param fromFormat 대상 날짜(date)의 날짜 포맷
     * @param div 계산할 구분 (Y:년도, M:월, D:일)
     * @param i 더하거나 뺄 숫자
     * @Example doDateCalc("2019-11-19", "yyyy-MM-dd", "D", 1)
     * @return Date
     */
    public static Date doDateCalc(String date, String fromFormat, String div, int i) throws ParseException{
        Calendar cal = new GregorianCalendar(Locale.KOREA);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dt = format.parse(date);

        cal.setTime(dt);
        if(div.equalsIgnoreCase("Y")){
            cal.add(Calendar.YEAR, i);
        }
        else if(div.equalsIgnoreCase("M")){
            cal.add(Calendar.MONTH, i);
        }
        else if(div.equalsIgnoreCase("D")){
            cal.add(Calendar.DAY_OF_YEAR, i);
        }

        return cal.getTime();

    }

    /**
     * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
     * @param date
     * @param dateType
     * @return
     * @throws Exception
     */
    public static String getWeekDay(String date, String dateType) throws Exception {

        String day = "" ;

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
        Date nDate = dateFormat.parse(date) ;

        Calendar cal = Calendar.getInstance() ;
        cal.setTime(nDate);

        int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;

        switch(dayNum){
            case 1:
                day = "일";
                break ;
            case 2:
                day = "월";
                break ;
            case 3:
                day = "화";
                break ;
            case 4:
                day = "수";
                break ;
            case 5:
                day = "목";
                break ;
            case 6:
                day = "금";
                break ;
            case 7:
                day = "토";
                break ;

        }

        return day ;
    }

    /**
    	 * 오늘날짜를 리턴한다.
    	 * @return SimpleDateFormat
    	 */
    	public static SimpleDateFormat getTimeStampFormat(){
    		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMddHHmmss"); // 날짜 시간으로 파일명 지정
    //		timeStamp.format(new Date());
    		return timeStamp;
    	}

    /**
    	 * 오늘 날짜를 String으로 리턴한다.
    	 * @param formatStr : yyyyMMddHHmmss 식의 포맷을 받는다.
    	 * @return String
    	 */
    	public static String getTimeStampFormat(String formatStr){
    		SimpleDateFormat timeStamp = new SimpleDateFormat(formatStr); // 날짜 시간으로 파일명 지정
    //		timeStamp.format(new Date());
    		return timeStamp.format(new Date()).toString();
    	}

    public static String getDataFormatChange(String date, String chgFormat){
    	String chgDate = "";

    	try{
    		SimpleDateFormat newDate = new SimpleDateFormat(chgFormat);
    		chgDate = newDate.format(date);

    	}catch(Exception pe){
    		pe.printStackTrace();
    	}

    	return chgDate;
    }

    /**
     * <pre>
     * 문자열 형태의 날짜를 원하는 형태로 변환합니다.
     *
     * 예시)
     * "yyyy.MM.dd G 'at' HH:mm:ss z"       2001.07.04 AD at 12:08:56 PDT
     * "EEE, MMM d, ''yy"   Wed, Jul 4, '01
     * "h:mm a"     12:08 PM
     * "hh 'o''clock' a, zzzz"      12 o'clock PM, Pacific Daylight Time
     * "K:mm a, z"  0:08 PM, PDT
     * "yyyyy.MMMMM.dd GGG hh:mm aaa"       02001.July.04 AD 12:08 PM
     * "EEE, d MMM yyyy HH:mm:ss Z" Wed, 4 Jul 2001 12:08:56 -0700
     * "yyMMddHHmmssZ"      010704120856-0700
     * "yyyy-MM-dd'T'HH:mm:ss.SSSZ" 2001-07-04T12:08:56.235-0700
     * </pre>
     *
     * @param date 변환할 날짜
     * @param fromFormatString date 파라미터의 포맷
     * @param toFormatString 변환될 포맷
     * @return 변환된 날짜 문자열
     */
    public static String formattedDate(String date, String fromFormatString, String toFormatString)	throws ParseException {
    	SimpleDateFormat fromFormat = new SimpleDateFormat(fromFormatString);
    	SimpleDateFormat toFormat = new SimpleDateFormat(toFormatString);
    	Date fromDate = null;

    	fromDate = fromFormat.parse(date);
//            try{
//            }
//            catch(ParseException e){
//            	fromDate = new Date();
//            }

        return toFormat.format(fromDate);
    }

    /**
     * <pre>
     * 날짜를 원하는 형태의 문자열로 반환합니다.
     * </pre>
     *
     * @param date 변환할 Date 인스턴스
     * @param format 변환할 포맷
     * @return 변환된 날짜 문자열
     */
    public static String formattedDate(Date date, String format){
            SimpleDateFormat toFormat = new SimpleDateFormat(format);
            return toFormat.format(date);
    }

    /**
     * 날짜 형식으로 변경하여 리턴한다.
     * @param date 날짜문자열
     * @return 날짜 형식 (yyyy년mm월dd일)
     */
    public static String formatDate(String date) {
    	return formatDate(date, 0);
    }

    /**
     * 주어진 포멧유형에 따라 날짜 형식으로 변경하여 리턴한다.
     * @param date 날짜문자열
     * @param type (TYPE_DOT: '.', TYPE_SLASH: '/', 아니면 한글형식)
     * @return 날짜형식 (yyyy.mm.dd, yyyy/mm/dd, yyyy년mm월dd일)
     */
    public static String formatDate(String date, int type) {
    	if (null == date) return "";
    	StringBuilder sb = new StringBuilder();
    	if (date.length() == 6) {
    		switch (type) {
    		case TYPE_DOT:
        		sb.append(StringUtils.substring(date, 0, 4)).append(".").append(StringUtils.substring(date, 4));
    			break;
    		case TYPE_SLASH:
        		sb.append(StringUtils.substring(date, 0, 4)).append("/").append(StringUtils.substring(date, 4));
    			break;
    		case TYPE_DASH:
        		sb.append(StringUtils.substring(date, 0, 4)).append("-").append(StringUtils.substring(date, 4));
    			break;
    		default:
        		sb.append(StringUtils.substring(date, 0, 4)).append("년").append(StringUtils.substring(date, 4)).append("월");
    		}
    	}
    	else if (date.length() == 8) {
    		switch (type) {
    		case TYPE_DOT:
        		sb.append(StringUtils.substring(date, 0, 4)).append(".").append(StringUtils.substring(date, 4, 6)).append(".").append(StringUtils.substring(date, 6));
    			break;
    		case TYPE_SLASH:
        		sb.append(StringUtils.substring(date, 0, 4)).append("/").append(StringUtils.substring(date, 4, 6)).append("/").append(StringUtils.substring(date, 6));
    			break;
    		case TYPE_DASH:
    			sb.append(StringUtils.substring(date, 0, 4)).append("-").append(StringUtils.substring(date, 4, 6)).append("-").append(StringUtils.substring(date, 6));
    			break;
    		default:
        		sb.append(StringUtils.substring(date, 0, 4)).append("년").append(StringUtils.substring(date, 4, 6)).append("월").append(StringUtils.substring(date, 6)).append("일");
    		}
    	}
    	else {
    		return date;
    	}
    	return sb.toString();
    }

    /**
     * 현재월을 기준으로 이전 3개월에 해당하는 월을 리턴한다.
     * <pre>
     * 현재월이 3월일경우 -1은 2월 -2는 1월 -3은 12월이 되어야 하고,
     * 현재월이 1월일경우 -1은 12월 -2는 11월 -3은 10월이 되어야 하므로, 이를 계산하여
     * 리턴하여 준다.
     * 최대 이전 3개월까지만 가능하다.
     * ex> getPrevMonth(3, 3) return 12
     * </pre>
     *
     * @param cMonth 현재월
     * @param prevMCnt 이전 몇월
     * @return cMonth - prevMCnt 월
     */
    public static int getPrevMonth(int cMonth, int prevMCnt){

        int[] mArry = {1,2,3,4,5,6,7,8,9,10,11,12};

        int sMonth = 0;

        if( (cMonth-prevMCnt) == 0  ){
            sMonth = 12;
        }
        else if( (cMonth-prevMCnt) == -1  ){
            sMonth = 11;
        }
        else if( (cMonth-prevMCnt) == -2  ){
            sMonth = 10;
        }
        else{
            sMonth = cMonth-prevMCnt;
        }

        return sMonth;
    }

    /**
     * Date 객체로 받은 fromDate, toDate 두 날짜 사이의 몇일이 차이나는지 계산을 해서 return 한다.
     * @param fromDate 시작일
     * @param toDate 종료일
     * @return 두 날짜 사이 차이나는 일수.
     */
    public static int doCompareDate(Date fromDate, Date toDate) {

    	long dates = toDate.getTime() - fromDate.getTime();

    	int compareDate = Integer.parseInt( String.valueOf(dates/(24*60*60*1000)) );

    	return compareDate;

    }

    /**
     * 문자로 받은 fromDate, toDate 두 날짜 사이의 몇일이 차이나는지 계산을 해서 return 한다.
     * Example)
     * doCompareDate("2021-01-01", "yyyy-MM-dd", "2021-02-17", "yyyy-MM-dd")
     * doCompareDate("2021-01-01", "yyyy-MM-dd", "20210217", "yyyyMMdd")
     * doCompareDate("20210101", "yyyyMMdd", "2021/02/17", "yyyy/MM/dd")
     * doCompareDate("202101", "yyyyMM", "2021/02", "yyyy/MM")
     * @param fromDateStr 시작일의 문자열
     * @param fromDateFromat 시작일 문자열의 날짜 포맷(ex: yyyy-MM-dd, yyyyMMdd, yy/MM/dd)
     * @param toDateStr 종료일의 문자열
     * @param toDateFormat 종료일 문자열의 날짜 포맷(ex: yyyy-MM-dd, yyyyMMdd, yy/MM/dd)
     * @return
     */
    public static int doCompareDate(String fromDateStr, String fromDateFromat, String toDateStr, String toDateFormat) throws ParseException {

    	Date fromDate = toDateFromStringDate(fromDateStr, fromDateFromat);
    	Date toDate = toDateFromStringDate(toDateStr, toDateFormat);

    	long dates = toDate.getTime() - fromDate.getTime();

    	int compareDate = Integer.parseInt( String.valueOf(dates/(24*60*60*1000)) );

    	return compareDate;

    }
}
