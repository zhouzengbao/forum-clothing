package com.forum.clothing.enums;
/**
 * @author zb
 */
public enum WechatDomain {

	/**
	 * 支付域名
	 */
	PAY("https://api.mch.weixin.qq.com"),

	/**
	 * 使用该域名将访问官方指定就近的接入点
	 */
	COMMON("http://api.weixin.qq.com"),

	/**
	 * 使用该域名将访问上海的接入点
	 */
	SH("http://sh.api.weixin.qq.com"),

	/**
	 * 使用该域名将访问深圳的接入点
	 */
	SZ("http://sz.api.weixin.qq.com"),

	/**
	 * 使用该域名将访问香港的接入点
	 */
	HK("http://hk.api.weixin.qq.com"),

	/**
	 * 使用该域名将访问官方指定就近的接入点 https
	 */
	COMMON_S("https://api.weixin.qq.com"),

	/**
	 * 使用该域名将访问上海的接入点 https
	 */
	SH_S("https://sh.api.weixin.qq.com"),

	/**
	 * 使用该域名将访问深圳的接入点 https
	 */
	SZ_S("https://sz.api.weixin.qq.com"),

	/**
	 * 使用该域名将访问香港的接入点 https
	 */
	HK_S("https://hk.api.weixin.qq.com");

	private String v;

	private WechatDomain(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return this.v;
	}

}
