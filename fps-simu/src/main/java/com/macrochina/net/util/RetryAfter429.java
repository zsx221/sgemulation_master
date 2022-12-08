package com.macrochina.net.util;

import com.macrochina.net.entity.BizRuleSet;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RetryAfter429 {
    public static boolean retryAfter(HttpServletResponse response, BizRuleSet bizRuleSet){
        boolean flag = true;
        if(bizRuleSet != null){
            String httpResp = bizRuleSet.getHttpResp();
            Integer retryAfter = bizRuleSet.getRetryAfter();
            String format =bizRuleSet.getFormat();
            if("429".equals(httpResp)){
                response.setStatus(429);
                if("Seconds".equals(format)){
                    response.setHeader("Retry-After",retryAfter.toString());
                }
                if("Data".equals(format)){
                    DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String gmt = dateFormat.format(new Date().getTime()+ (retryAfter!=null?retryAfter:0 )* 1000) ;
                    response.setHeader("Retry-After",gmt);
                }
                flag = false;

            }
        }
        return flag;
    }
}
