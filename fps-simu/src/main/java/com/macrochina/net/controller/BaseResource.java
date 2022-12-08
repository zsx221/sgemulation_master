package com.macrochina.net.controller;

import com.alibaba.fastjson.JSONObject;
import com.macrochina.net.entity.Res;
import com.macrochina.net.vo.UserVo;
import com.macrochina.net.utils.CommonUtils;
import com.macrochina.net.utils.MapContants;
import com.macrochina.net.utils.StringConstant;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @Desc 登录信息
 */
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseResource {

	private final static Logger log = LoggerFactory.getLogger(BaseResource.class);
	
	@GetMapping("/session")
    public Res session(HttpServletRequest request) {
		UserVo uv = (UserVo) request.getSession().getAttribute(StringConstant.USER_DATA_SESSION_KEY);
		if(null != uv){
			return Res.Success(uv);
		}
		return Res.Failure(MapContants.Timeout, MapContants.getMessage(MapContants.Timeout));
    }
	
	protected void userLogin(HttpServletRequest request, UserVo uv) {
		request.getSession().setMaxInactiveInterval(36000);
		request.getSession().setAttribute(StringConstant.USER_DATA_SESSION_KEY, uv);
	}
	
	protected void userLogout(HttpServletRequest request) {
		request.getSession().removeAttribute(StringConstant.USER_DATA_SESSION_KEY);
	}

	/**
	 * 获取jsonValue值
	 */
	public JSONObject getJsonValue(HttpServletRequest request) {
		JSONObject jsonValue = null;
		try {
			String str = CommonUtils.stringUncode(request.getParameter("jsonValue"));
			log.info("str="+str);
			log.info(StringEscapeUtils.unescapeHtml4(str));
			jsonValue = JSONObject.parseObject(StringEscapeUtils.unescapeHtml4(str));
		} catch (Exception e) {
			log.error("数据格式错误，请检查：" + e.getMessage(), e);
		}
		return jsonValue;
	}
}