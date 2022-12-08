package com.macrochina.net.util;


import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class XmlIsoDateUtil {

    private static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Author fangyu
     * @Description //输出格式为 2022-01-24T11:11:02Z   ISONormalisedDateTime
     * @Date 2022/1/24 11:11
     * @Param [c]
     * @return javax.xml.datatype.XMLGregorianCalendar
     **/
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Calendar  c) {  
        XMLGregorianCalendar gc = null;  
        try {  
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(
            		c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
            		c.get(Calendar.SECOND), DatatypeConstants.FIELD_UNDEFINED, 0);  
        } catch (Exception e) {  
  
             e.printStackTrace();  
        }  
        return gc;  
    }

    /**
     * @Author fangyu
     * @Description //输出格式为2022-01-24T11:09:36
     * @Date 2022/1/24 11:10
     * @Param [c]
     * @return javax.xml.datatype.XMLGregorianCalendar
     **/
    public static XMLGregorianCalendar convertToXMLGregorianCalendarSecond(Calendar  c) {
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                    c.get(Calendar.SECOND), DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return gc;
    }

    /**
     * @Author fangyu
     * @Description //输出格式为2022-01-24T11:14:23.614  ISODateTime
     * @Date 2022/1/24 11:14
     * @Param [c]
     * @return javax.xml.datatype.XMLGregorianCalendar
     **/
    public static XMLGregorianCalendar convertToXMLGregorianCalendarMillsec(Calendar  c) {
        XMLGregorianCalendar gc = null;  
        try {  
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(
            		c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
            		c.get(Calendar.SECOND), c.get(Calendar.MILLISECOND), DatatypeConstants.FIELD_UNDEFINED);  
        } catch (Exception e) {  
  
             e.printStackTrace();  
        }  
        return gc;  
    }

   /**
    * @Author fangyu
    * @Description 输出格式为2022-01-24   ISODate
    * @Date 2022/1/24 11:03
    * @Param
    * @return
    **/
	public static XMLGregorianCalendar convertToXMLGregorianCalendarDate(Calendar  c) {  
        XMLGregorianCalendar gc = null;  
        try {  
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
            		c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);  
        } catch (Exception e) {  
  
             e.printStackTrace();  
        }  
        return gc;  
    }  
     public static Date convertToDate(XMLGregorianCalendar cal) throws Exception{  
         GregorianCalendar ca = cal.toGregorianCalendar();  
         return ca.getTime();  
     }

    /**
     * String转化为XMLGregorianCalendar
     * @param time
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(String time) {
        GregorianCalendar cal = new GregorianCalendar();
        XMLGregorianCalendar gc = null;
        try {
            Date date = YYYYMMDD.parse(time);
            cal.setTime(date);
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }

    /**
     * 输出格式 2010-07-18
     */
    public static XMLGregorianCalendar convertToXMLCalendar(String time) {
        GregorianCalendar cal = new GregorianCalendar();
        XMLGregorianCalendar gc = null;
        try {
            Date date = YYYYMMDD.parse(time);
            cal.setTime(date);
            gc = convertToXMLGregorianCalendarDate(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }

    public static XMLGregorianCalendar convertDate(String dateTime) {
        DateFormat df2 = null;
        Date date1 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(dateTime);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertToXMLGregorianCalendar(df2.format(date1));
    }

    public static Date str2Date(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str); // Thu Jan 18 00:00:00 CST 2007
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date转化为XMLGregorianCalendar
     * @param date
     * @return
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            gc.setTimezone(DatatypeConstants.FIELD_UNDEFINED); //去除时区
            gc.setTime(0, 0, 0); //去除时间后面的间隔
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }


    public static String convertToStr(XMLGregorianCalendar xml) {
        String gc = null;
        try {
            Calendar calendar = xml.toGregorianCalendar();
            YYYYMMDD.setTimeZone(calendar.getTimeZone());
            gc = YYYYMMDD.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }

    public static String convertToStrFort(XMLGregorianCalendar xml) {
        String gc = null;
        try {
            Calendar calendar = xml.toGregorianCalendar();
            YYYYMMDDHHMMSS.setTimeZone(calendar.getTimeZone());
            gc = YYYYMMDDHHMMSS.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }
}
