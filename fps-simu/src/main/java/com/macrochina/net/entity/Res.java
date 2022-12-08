package com.macrochina.net.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Res {
	// Success 成功
	public final static int Success = 200;
	// Failure 失败
	public final static int Failure = 500;
	// Forbidden 禁止访问
	public final static int Forbidden = 403;

	private int code;
	private String msg;
	private int errorcode;
	private Object data;
	private Long timestamp;

	public static Res Success() {
		return Success(null);
	}

	public static Res Success(Object data) {
		Res res = new Res();
		res.code = Success;
		res.msg = "OK";
		res.errorcode = 1;
		res.timestamp = System.currentTimeMillis();
		res.data = data;
		return res;
	}
	public static Res Success(Object data, String msg) {
		Res res = new Res();
		res.code = Success;
		res.msg = msg;
		res.errorcode = 1;
		res.timestamp = System.currentTimeMillis();
		res.data = data;
		return res;
	}

	public static Res Failure(int errorcode, String msg) {
		Res res = new Res();
		res.code = Failure;
		res.msg = msg;
		res.errorcode = errorcode;
		res.timestamp = System.currentTimeMillis();
		return res;
	}

	public static Res Forbidden(String msg) {
		Res res = new Res();
		res.code = Forbidden;
		res.msg = msg;
		res.errorcode = 401;
		res.timestamp = System.currentTimeMillis();
		return res;
	}

	public static Res wauPageInfo(Integer count, Integer curPage, Integer pageSize, List<?> contentList) {
		// 页数信息
		JSONObject pagination = new JSONObject();
		pagination.put("current_page", curPage == null ? 1 : curPage);
		pagination.put("page_size", pageSize == null ? 10 : pageSize);
		pagination.put("total", count == null ? 0 : count);

		// 数据信息
		JSONObject data = new JSONObject();
		// 记录
		data.put("list",(contentList == null || contentList.isEmpty()) ? new JSONArray() : JSONArray
						.parseArray(JSON.toJSONString(contentList)));
		data.put("pagination", pagination);
		return Success(data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
