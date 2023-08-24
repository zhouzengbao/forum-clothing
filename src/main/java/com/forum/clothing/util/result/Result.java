package com.forum.clothing.util.result;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499433944948002095L;

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 返回数据
	 */
	private T data;

	/**
	 * 结果说明
	 */
	private String msg;

	public static int SUCCESS = 0;
	public static int FAILURE = 1;

	public Map<String, Object> map() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		map.put("msg", msg);
		return map;
	}

	public boolean success() {
		return this.code == SUCCESS;
	}
	public String json() {
		return JSON.toJSONString(this);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", data=" + data +
				", msg='" + msg + '\'' +
				'}';
	}
}
