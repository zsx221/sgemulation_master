package com.macrochina.net.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

    /**
     * 格式化日期
     * @param date
     * @param exp  "yyyy-MM-dd hh:mm:ss"...
     * @return
     */
    public static String dateFormat(Date date, String exp) {
        String rtn = "";
        SimpleDateFormat sdf = new SimpleDateFormat(exp);
        rtn = sdf.format(date);
        return rtn;
    }

    public static Date parseDate(String date, String exp) {
        Date rtn = null;
        SimpleDateFormat sdf = new SimpleDateFormat(exp);
        try {
            rtn = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
