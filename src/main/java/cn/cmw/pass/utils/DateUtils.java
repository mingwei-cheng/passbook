/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.cmw.pass.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.dateTime.DateUtils类
 * @author jeeplus
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
		"yyyyMMdd", "MM-dd"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getHour(Date date) {
		return formatDate(date, "HH");
	}
	
	/**
	 * 得到当前是本年的第几周
	 */
	public static Integer getWeekOfYear(){
		Calendar c=Calendar.getInstance();
        return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parseStrToDate(Object str, String pa) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), pa);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String formatStrDateToStr(Object str, String patten){
		if (str != null){
			Date date = parseDate(str);
			return formatDate(date, patten);
		}else{
			return "";
		}
		
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 判断time时间字符串是否比当前时间晚
	 * @param time
	 * @return
	 */
	public static boolean IsLaterToNowTime(String time){
         return getDateTime().compareTo(time) >=0;
    }
	
	public static String ArabicToChinese(Integer day){
		String dayofweek = "";
		switch (day) {
		case 1:
			dayofweek = "周日";
			break;
		case 2:
			dayofweek = "周一";
			break;
		case 3:
			dayofweek = "周二";
			break;
		case 4:
			dayofweek = "周三";
			break;
		case 5:
			dayofweek = "周四";
			break;
		case 6:
			dayofweek = "周五";
			break;
		case 7:
			dayofweek = "周六";
			break;
		default:
			dayofweek ="日期不对";
			break;
		}
		
		return dayofweek;
	}
    
    public static String getNumBeforeDate(int num){
    	Date today = new Date();
    	Date yesterday = new Date(today.getTime() - num*24*60*60*1000L);//86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
    	
        return sdf.format(yesterday); 
    }
    
    public static String getNumBeforeMonth(int num){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
    	Calendar c = Calendar.getInstance();
    	
    	c.setTime(new Date());
        c.add(Calendar.MONTH, -num);
        Date m = c.getTime();
        String mon = sdf.format(m);
    	
        return mon; 
    }
    
    public static String getNumBeforeStrDate(String date, int num){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
    	Date today = null;
    	Date yesterday = null;
		try {
			today = sdf.parse(date);
			yesterday = new Date(today.getTime() - num*24*60*60*1000L);//86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	
        return sdf.format(yesterday); 
    }
    
    public static String getNumBeforeStrMonth(String date, int num){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
    	Calendar c = Calendar.getInstance();
    	
    	try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        c.add(Calendar.MONTH, -num);
        Date m = c.getTime();
        String mon = sdf.format(m);
    	
        return mon; 
    }
   
    /**
     * 日期格式化
     * @param date      需要格式化的日期
     * @param pattern   时间格式，如：yyyy-MM-dd HH:mm:ss
     * @return          返回格式化后的时间字符串
     */
    public static String format(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

	/** 
     * 两个时间相差距离多少天多少小时多少分
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return String 返回值为：xx天xx小时xx分xx秒 
     */  
    public static String getDistanceTime(String str1, String str2) {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
            if (day == 0 && hour == 0 && min == 0){
            	return "<1分";
            }else if (day == 0 && hour == 0){
            	return min + "分";
            }else if(day == 0){
            	return hour + "小时" + min + "分";  
            }
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return day + "天" + hour + "小时" + min + "分";  
    }  
    
    /** 
     * 两个时间相差距离多少天多少小时多少分
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return String 返回值为：xx:xx:xx 
     */  
    public static String getDistanceTimeNum(String str1, String str2) {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
//            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000));  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        String h, m, s;
        if (hour < 10){
        	h = "0"+hour;
        }else{
        	h = ""+hour;
        }
        if (min < 10){
        	m = "0"+min;
        }else{
        	m = ""+min;
        }
        if (sec < 10){
        	s = "0"+sec;
        }else{
        	s = ""+sec;
        }
        return h+":"+m+":"+s;  
    }  
    
    
    /** 
     * 秒转换成多少小时多少分钟多少秒
     * @param diff 秒数  min_scale--最小单位（ 0：秒 ；1：分； 其他：小时）
     * @return String 返回值为：xx:xx:xx 
     */  
    public static String getDistanceTimeNumBySecond(int diff, int min_scale) {  
    	long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        
//        day = diff / (24 * 60 * 60 );  
        hour = (diff / (60 * 60));  
        min = ((diff / (60)) - day * 24 * 60 - hour * 60);  
        sec = (diff-day*24*60*60-hour*60*60-min*60);  
        String h, m, s;
        if (hour < 10){
        	h = "0"+hour;
        }else{
        	h = ""+hour;
        }
        if (min < 10){
        	m = "0"+min;
        }else{
        	m = ""+min;
        }
        if (sec < 10){
        	s = "0"+sec;
        }else{
        	s = ""+sec;
        }
        
        if (min_scale == 0){
        	return h+":"+m+":"+s;
        }else if(min_scale == 1){
        	return h+":"+m;
        }else{
        	return h;
        }
          
    }  
    //按照格式转换为日期  并根据两个日期得到中间的天
    public static List<String> getBetweenDay(String sDate, String eDate, String pattern){
    	List<String> ret = new ArrayList<String>();
    	
    	Calendar dayc1 = new GregorianCalendar();
	    Calendar dayc2 = new GregorianCalendar();
	    DateFormat df = new SimpleDateFormat(pattern);
	    Date daystart;
		try {
			daystart = df.parse(sDate);
			Date dayend = df.parse(eDate);
		    dayc1.setTime(daystart);  //设置calendar的日期
		    dayc2.setTime(dayend);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 
		for (; dayc1.compareTo(dayc2) <= 0;) {   //dayc1在dayc2之前就循环
		    ret.add(fillZero((dayc1.get(Calendar.MONTH)+1))+"-"+fillZero(dayc1.get(Calendar.DATE)));
		    
		    dayc1.add(Calendar.DAY_OF_YEAR, 1);  //加1天
		 }
		
		return ret;
    }
    private static String fillZero(int month) {
    	if (month >=0 && month <=9){
    		return "0" + month;
    	}else{
    		return month + "";
    	}
	}
    //按照yyyy-MM格式转换为日期 根据两个日期得到月份
    public static List<String> getBetweenMonth(String sDate, String eDate, String pattern){
    	List<String> ret = new ArrayList<String>();
    	
    	Calendar dayc1 = new GregorianCalendar();
	    Calendar dayc2 = new GregorianCalendar();
	    DateFormat df = new SimpleDateFormat(pattern);
	    Date daystart;
		try {
			daystart = df.parse(sDate);
			Date dayend = df.parse(eDate);
		    dayc1.setTime(daystart);  //设置calendar的日期
		    dayc2.setTime(dayend);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 
		for (; dayc1.compareTo(dayc2) <= 0;) {   //dayc1在dayc2之前就循环
		    ret.add(dayc1.get(Calendar.YEAR)+""+fillZero((dayc1.get(Calendar.MONTH)+1)));
		    
		    dayc1.add(Calendar.MONTH, 1);  //加1月
		 }
		
		return ret;
    }

    // 根据两个周得到月份 周格式：201701
    public static List<String> getBetweenWeek(String sDate, String eDate){
    	List<String> ret = new ArrayList<String>();
    	
    	int sYear = Integer.parseInt(sDate)/100;
		int eYear = Integer.parseInt(eDate)/100;
		if (sYear != eYear){
			for (int i = Integer.parseInt(sDate); i <= Integer.parseInt(sYear+"53"); i++) {
				ret.add(i + "");
			}
			for (int i = Integer.parseInt(eYear+"01"); i <= Integer.parseInt(eDate); i++) {
				ret.add(i + "");
			}
		}else{
			for (int i = Integer.parseInt(sDate); i <= Integer.parseInt(eDate); i++) {
				ret.add(i + "");
			}
		}
		
		return ret;
    }
    /**
	 * 根据年周数和周开始日期，获取这周的开始日期和结束日期
	 * @param yearweek	eg:201701
	 * @param startDay	eg:3(周二)（1-7 周日到周六）
	 * @return		eg:[20170101,20170102]
	 * @throws ParseException 
	 */
	public static Integer[] getSEDateByWeekAndItStart(int yearweek, int startDay){
		int startDate = 0, endDate = 0;
		
		//1.得到判断日期
		int nowYear = yearweek/100;
		int lastYear = nowYear - 1;
		String dateBase = "";
		if (dayForWeek(lastYear + "-12-31") == startDay){ 
			dateBase = lastYear + "1231";
		}else if(dayForWeek(lastYear + "-12-30") == startDay){
			dateBase = lastYear + "1230";
		}else if(dayForWeek(lastYear + "-12-29") == startDay){
			dateBase = lastYear + "1229";
		}else if(dayForWeek(lastYear + "-12-28") == startDay){
			dateBase = lastYear + "1228";
		}else if(dayForWeek(lastYear + "-12-27") == startDay){
			dateBase = lastYear + "1227";
		}else if(dayForWeek(lastYear + "-12-26") == startDay){
			dateBase = lastYear + "1226";
		}else if(dayForWeek(lastYear + "-12-25") == startDay){
			dateBase = lastYear + "1225";
		}
		
		try {
			//2.判断是否跨年
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(dateBase));
			c.add(Calendar.DATE, 6);
			
			int isReduce = 0;
			if (c.getTime().getTime() >= format.parse(nowYear+"0101").getTime()){
				isReduce = 1;
			}
			
			//3.获取开始日期和结束日期
			int nowWeek = yearweek % 100;
			if (nowWeek == 1){
				startDate = Integer.parseInt(nowYear+"0101");
				if (isReduce == 1){
					endDate = Integer.parseInt(formatDate(c.getTime(), "yyyyMMdd"));
				}else{
					endDate = Integer.parseInt(nowYear+"0107");
				}
			}else{
				c.add(Calendar.DATE, 1+(nowWeek-1-isReduce)*7);
				startDate = Integer.parseInt(formatDate(c.getTime(), "yyyyMMdd"));
				c.add(Calendar.DATE, 6);
				endDate = Integer.parseInt(formatDate(c.getTime(), "yyyyMMdd"));
				
				if (endDate >= Integer.parseInt(nowYear+1+"0101")){
					endDate = Integer.parseInt(nowYear+"1231");
				}
			}
	 		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer[] dates=new Integer[2];
		dates[0] = startDate;
		dates[1] = endDate;
 		return dates;
		
	}
 /** 
    * 判断当前日期是星期几(1-7:周日-周六)
    *
    * @param pTime 修要判断的时间
    * @return dayForWeek 判断结果
    */  
	public static int dayForWeek(String pTime){  
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c = Calendar.getInstance();  
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		int dayForWeek = 0;  
		dayForWeek = c.get(Calendar.DAY_OF_WEEK);  
		return dayForWeek;  
	}
	  /** 
     * 得到上一个周期的开始时间和结束时间
     * @param str1 时间参数 1 格式：19900101
     * @param str2 时间参数 2 格式：20090101
     * @return String 
     */  
    public static int[] getDistanceDay(String str1, String str2) {  
        DateFormat df = new SimpleDateFormat("yyyyMMdd");  
        Date one;  
        Date two;  
        int day = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = (int) (diff / (24 * 60 * 60 * 1000));  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        int[] dates = new int[2];
        dates[0] = Integer.parseInt(getNumBeforeStrDate(str1, day+1));
        dates[1] = Integer.parseInt(getNumBeforeStrDate(str2, day+1));
        
        return dates;  
    }  
    /** 
     *  
     * @param minDate 最小时间  2015-01 
     * @return 日期集合 格式为 年
     * @throws Exception 
     */  
    public static List<String> getYearBetween(String minDate){  
        ArrayList<String> result = new ArrayList<String>();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");//格式化为年月  
  
        Calendar min = Calendar.getInstance();  
        Calendar max = Calendar.getInstance();  
  
        try {
			min.setTime(sdf.parse(minDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        min.set(min.get(Calendar.YEAR), 1, 1);  
  
        max.setTime(new Date());  
        max.set(max.get(Calendar.YEAR), 2, 1);  
  
        Calendar curr = min;  
        while (curr.before(max)) {  
	         result.add(sdf.format(curr.getTime()));  
	         curr.add(Calendar.YEAR, 1);  
        }  
  
        return result;  
    }   
    /** 
     * 根据日期获得星期 
     * @param strDate
     * @return 
     */ 
	public static String getWeekOfDate(String strDate, Object... pattern) {
		Date date = null;
		if (pattern != null && pattern.length > 0){
			date = parseStrToDate(strDate, pattern[0].toString());
		}else{
			date = parseDate(strDate);
		}
		
		String[] weekDaysName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" }; 
		//	  String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
		return weekDaysName[intWeek]; 
	} 
	/**
     * pattern yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String showDate(Date date,String pattern){
        String  dateStr=format(date,pattern);
        String year = dateStr.substring(0,4);
        Long yearNum =Long.parseLong(year);
        int month = Integer.parseInt(dateStr.substring(5,7));  
        int day = Integer.parseInt(dateStr.substring(8,10));  
        String hour = dateStr.substring(11,13);  
        String minute = dateStr.substring(14,16);

        long addtime =date.getTime();
        long today = System.currentTimeMillis();//当前时间的毫秒数
        Date now = new Date(today);
        String  nowStr=format(now,pattern);
        int  nowDay = Integer.parseInt(nowStr.substring(8,10));
        String result="";
           long l=today-addtime;//当前时间与给定时间差的毫秒数
           long days=l/(24*60*60*1000);//这个时间相差的天数整数，大于1天为前天的时间了，小于24小时则为昨天和今天的时间
           long hours=(l/(60*60*1000)-days*24);//这个时间相差的减去天数的小时数
           long min=((l/(60*1000))-days*24*60-hours*60);//
           if(days > 0){
                   if(days>0 && days<2){
                       result ="前天 "+hour+":"+minute;                           
                   } else {
                       result = yearNum%100+"-"+month+"-"+day+" "+hour+":"+minute;
                   }
           }else if(hours > 0 ) {
                    if(day!=nowDay){
                        result = "昨天 "+hour+":"+minute;
                    }else{
                        result=hour+":"+minute;    
                    }
           } else if(min > 0){
        	   result=hour+":"+minute;
           }else {
               result=hour+":"+minute;
           }
        return result;
    }

	public static Integer getEndWeekOfYear(Integer year){
		String today = year+"-12-31";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		Calendar calendar = null;
		int month = 0;
		int week = 0;
		try {
			date = format.parse(today);
			calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.SUNDAY);
			calendar.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//每周从周一开始  
			calendar.setTime(date);
			month = calendar.get(Calendar.MONTH);//获得当前月
			week = calendar.get(Calendar.WEEK_OF_YEAR);//获得周数
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (month+1 == 12 && week == 1){
			week = 53;
		}
		return week;

	}
	/**
	 * 得到上周的年周
	 */
	public static Integer getLastWeekYearOfYear(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Integer week = c.get(Calendar.WEEK_OF_YEAR);
		Integer year = c.get(Calendar.YEAR);
		if (week >= 0 && week <=9){
			return Integer.parseInt(year+"0"+week);
		}
        return Integer.parseInt(year+""+week);
	}
	/**
	 * 得到当前的年周
	 */
	public static Integer getNowWeekYearOfYear(){
		Calendar c=Calendar.getInstance();
		Integer week = c.get(Calendar.WEEK_OF_YEAR);
		Integer year = c.get(Calendar.YEAR);
		if (week >= 0 && week <=9){
			return Integer.parseInt(year+"0"+week);
		}
        return Integer.parseInt(year+""+week);
	}

	public static List<String> getRecent12Month(Integer n){
		List<String> last12Months = new ArrayList<String>();  
        
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+2); //要先+1,才能把本月的算进去</span>  
        for(int i=0; i<n; i++){  
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //逐次往前推1个月  
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            if (month == 0){
            	year--;
            	month = 12;
            }
            last12Months.add(year+ "" + fillZero(month));  
        }  
          
        return last12Months;  
	}
		
	/**
	 * 得到两个日期相差的秒数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int calDiffTime(Date startDate, Date endDate){
		if (startDate != null&&endDate != null){
			long a = endDate.getTime();
		    long b = startDate.getTime();
		    int c = (int)((a - b) / 1000);
		    if (c < 0){
		    	c = 0;
		    }
			return c;
		}else{
			return 0;
		}
	}
	
	/**
	 * 将string类型的日期，转成GMT标准的Date类型日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date formatStrToGMTDate(String date, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
		Date ret = null;
		try {
			ret = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
