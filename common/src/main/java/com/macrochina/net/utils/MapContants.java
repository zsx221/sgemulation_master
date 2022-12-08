package com.macrochina.net.utils;

import java.util.HashMap;
import java.util.Map;

public class MapContants {

	// Success 成功
	public final static int Success    = 0;
	// Failure 失败
	public final static int Failure    = 1;
	// Forbidden 禁止访问
	public final static int Forbidden  = 3;
	
	public final static int Timeout    = 9;
    
    /** 正则表达式：验证手机号 */ 
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0-1]|3|[5-8]))|(18[0-9]))\\d{8}$";
    
    /**
	 * 处理码Map
	 */
    public static final Map<Integer, String> MessageCodeMap = new HashMap<Integer, String>();
    
    static {
    	MessageCodeMap.put(Success, "成功");
    	MessageCodeMap.put(Failure, "失败");
    	MessageCodeMap.put(Forbidden, "禁止访问");
    	MessageCodeMap.put(Timeout, "未登录或会话已超时");
    }
    
    public static final String getMessage(Integer code){
    	return MessageCodeMap.get(code);
    }
    
}
