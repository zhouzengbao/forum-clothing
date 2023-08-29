package com.forum.clothing.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forum.clothing.dto.WeChatDto;

import java.util.Optional;

public class ResultHandler {

	private JSONObject json;

	private Integer errcode;
	private String errmsg;

	private boolean isSuccess;

	public static ResultHandler create(String json) {
		ResultHandler handler = new ResultHandler();
		handler.init(JSON.parseObject(json));
		return handler;
	}

	private ResultHandler() {
	}

	public WeChatDto getDto(){
		return WeChatDto.builder()
				.country(Optional.ofNullable(get("country")).map(String::valueOf).orElse(""))
				.gender(Optional.ofNullable(get("gender")).map(String::valueOf).orElse(""))
				.avatarUrl(Optional.ofNullable(get("avatarUrl")).map(String::valueOf).orElse(""))
				.nickName(Optional.ofNullable(get("nickName")).map(String::valueOf).orElse(""))
				.language(Optional.ofNullable(get("language")).map(String::valueOf).orElse(""))
				.province(Optional.ofNullable(get("province")).map(String::valueOf).orElse(""))
				.city(Optional.ofNullable(get("city")).map(String::valueOf).orElse(""))
				.build();
	}
	public Object get(String key) {
		return json.get(key);
	}

	public JSONObject getJSONObject(String key) {
		return json.getJSONObject(key);
	}

	public JSONArray getJSONArray(String key) {
		return json.getJSONArray(key);
	}

	public String getString(String key) {
		return json.getString(key);
	}

	public Integer getInteger(String key) {
		return json.getInteger(key);
	}

	private void init(JSONObject json) {
		this.json = json;
		Integer errcode = json.getInteger("errcode");
		if (errcode == null || errcode == 0) {
			isSuccess = true;
		} else {
			this.errcode = errcode;
			this.errmsg = json.getString("errmsg");
			isSuccess = false;
			throw new RuntimeException("微信请求失败：" + this.errmsg);
		}
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
