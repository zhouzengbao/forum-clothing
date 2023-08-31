package com.forum.clothing.util;

import com.forum.clothing.enums.WechatDomain;

public class WpApiUrl {

	// 微信域名
	private static WechatDomain domain = WechatDomain.COMMON_S;
	private static WechatDomain https_domain = WechatDomain.COMMON_S;
	private static WechatDomain pay_domain = WechatDomain.PAY;

	/**
	 * 重要接口
	 */
	public static enum Core {
		//@formatter:off
		Access_Token("/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}"), 
		/**
		 * CODE换取网页授权accessToken，包含openid
		 */
		CODE_TOKEN("/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code "), 
		WechatServerIP("/cgi-bin/getcallbackip?access_token={0}"),
		//@formatter:on


			;

		private String v;

		private Core(String v) {
			this.v = v;
		}

		public String url() {
			return https_domain + this.v;
		}
	}

	/**
	 * 未分类接口
	 */
	public static enum Common {
		//@formatter:off
		/**
		 * 自动回复规则
		 */
		Autoreply_Info("/cgi-bin/get_current_autoreply_info?access_token={0}"),
		
		/**
		 * 长链接转短链接接口
		 */
		Short_Url("/cgi-bin/shorturl?access_token={0}")
		;
		//@formatter:on

		private String v;

		private Common(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 菜单
	 * 
	 */
	public static enum Menu {
		//@formatter:off
		Create("/cgi-bin/menu/create?access_token={0}"), 
		Get("/cgi-bin/menu/get?access_token={0}"),
		Delete("/cgi-bin/menu/delete?access_token={0}"),
		AddConditional("/cgi-bin/menu/addconditional?access_token={0}");
		//@formatter:on

		private String v;

		private Menu(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 消息
	 * 
	 */
	public static enum Message {
		//@formatter:off
		/**
		 * 客服消息
		 */
		Custom_Service("/cgi-bin/message/custom/send?access_token={0}"), 
		Template("/cgi-bin/message/template/send?access_token={0}");
		//@formatter:on

		private String v;

		private Message(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 客服账号
	 * 
	 */
	public static enum KFACCOUNT {
		//@formatter:off
		Add("/customservice/kfaccount/add?access_token={0}"), 
		Update("/customservice/kfaccount/update?access_token={0}"),
		Delete("/customservice/kfaccount/del?access_token={0}"),
		/**
		 * 设置头像
		 */
		HeadImage("/customservice/kfaccount/uploadheadimg?access_token={0}&kf_account={1}"),
		
		List("/cgi-bin/customservice/getkflist?access_token={0}"),
		
		/**
		 * 客服输入状态
		 */
		Typing("/cgi-bin/message/custom/typing?access_token={0}")
		;
		//@formatter:on

		private String v;

		private KFACCOUNT(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 模板
	 * 
	 */
	public static enum Template {
		//@formatter:off
		/**
		 * 设置行业
		 */
		Set_Industry("/cgi-bin/template/api_set_industry?access_token={0}"), 
		/**
		 * 获取设置的行业信息
		 */
		Get_Industry("/cgi-bin/template/get_industry?access_token={0}"),
		GetId("/cgi-bin/template/api_add_template?access_token={0}"),
		List("/cgi-bin/template/get_all_private_template?access_token={0}"),
		Delete("/cgi-bin/template/del_private_template?access_token={0}");
		//@formatter:on

		private String v;

		private Template(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 网页
	 * 
	 */
	public static enum Web {
		//@formatter:off
		Web_Access_Token("/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code "), 
		Refresh_Web_Access_Token("/refresh_token?appid={0}&grant_type=refresh_token&refresh_token={1}"),
		UserInfo("/sns/userinfo?access_token={0}&openid={1}&lang={2}"),

		/**
		 * {
		 * "openid":"xxxxxx",
		 * "session_key":"xxxxx",
		 * "unionid":"xxxxx",
		 * "errcode":0,
		 * "errmsg":"xxxxx"
		 * }
		 */
		LOGIN("/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code"),
		
		
		/**
		 * 检验授权凭证（access_token）是否有效
		 */
		Auth("/sns/auth?access_token={0}&openid={1}"),
		
		/**
		 * 获取JS_TICKET的接口
		 */
		Ticket("/cgi-bin/ticket/getticket?access_token={0}&type=jsapi")
		;
		//@formatter:on

		private String v;

		private Web(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 临时素材管理
	 * 
	 */
	public static enum Media {
		//@formatter:off
		Upload("/cgi-bin/media/upload?access_token={0}&type={1}"), 
		Get("/cgi-bin/media/get?access_token={0}&media_id={1}"),
		GetHDVoice("/cgi-bin/media/get/jssdk?access_token={0}&media_id={1}");
		//@formatter:on

		private String v;

		private Media(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 永久素材
	 * 
	 */
	public static enum Material {
		//@formatter:off
		Add_News("/cgi-bin/material/add_news?access_token={0}"), 
		
		/**
		 * 上传图文消息内的图片获取URL 
		 */
		Upload_Image("/cgi-bin/media/uploadimg?access_token={0}"),
		
		Add_Other("/cgi-bin/material/add_material?access_token={0}&type={1}"),
		
		Get("/cgi-bin/material/get_material?access_token={0}"),
		
		Del("/cgi-bin/material/del_material?access_token={0}"),
		
		Update_News("/cgi-bin/material/update_news?access_token={0}"),
		
		Count("/cgi-bin/material/get_materialcount?access_token={0}"),
		
		List("/cgi-bin/material/batchget_material?access_token={0}")
		;
		//@formatter:on

		private String v;

		private Material(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 用户标签管理
	 * 
	 */
	public static enum Tag {
		//@formatter:off
		Create("/cgi-bin/tags/create?access_token={0}"), 
		Get("/cgi-bin/tags/get?access_token={0}"),
		Update("/cgi-bin/tags/update?access_token={0}"),
		Del("/cgi-bin/tags/delete?access_token={0}"),
		FANS("/cgi-bin/user/tag/get?access_token={0}"),
		
		Tag_User("/cgi-bin/tags/members/batchtagging?access_token={0}"),
		Batch_Untag("/cgi-bin/tags/members/batchuntagging?access_token={0}"),
		User_Tag("/cgi-bin/tags/getidlist?access_token={0}")
		;
		//@formatter:on

		private String v;

		private Tag(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 用户
	 * 
	 */
	public static enum User {
		//@formatter:off
		/**
		 * 备注名
		 */
		Remark("/cgi-bin/user/info/updateremark?access_token={0}"), 
		Info("/cgi-bin/user/info?access_token={0}&openid={1}&lang={2}"),
		Batch_Info("/cgi-bin/user/info/batchget?access_token={0}"),
		
		List("/cgi-bin/user/get?access_token={0}&next_openid={1}")
		;
		//@formatter:on

		private String v;

		private User(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	/**
	 * 黑名单
	 * 
	 */
	public static enum BlackList {
		//@formatter:off
		List("/cgi-bin/tags/members/getblacklist?access_token={0}"), 
		Add("/cgi-bin/tags/members/batchblacklist?access_token={0}"),
		Remove("/cgi-bin/tags/members/batchunblacklist?access_token={0}"),
		
		;
		//@formatter:on

		private String v;

		private BlackList(String v) {
			this.v = v;
		}

		public String url() {
			return domain + this.v;
		}
	}

	public static enum Qrcode {
		//@formatter:off
		Ticket("/cgi-bin/qrcode/create?access_token={0}", true), 
		Generate("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}", false);
		//@formatter:on

		private String v;
		private boolean needDomain;

		private Qrcode(String v, boolean needDomain) {
			this.v = v;
			this.needDomain = needDomain;
		}

		public String url() {
			if (needDomain) {
				return domain + this.v;
			} else {
				return this.v;
			}
		}
	}

	/**
	 * 用户数据统计
	 * 
	 */
	public static enum DatacubeUser {
		//@formatter:off
		/**
		 * 获取用户增减数据
		 */
		Summary("/datacube/getusersummary?access_token={0}"), 
		
		/**
		 * 获取累计用户数据
		 */
		Cumulate("/cgi-bin/getcallbackip?access_token={0}");
		
		//@formatter:on

		private String v;

		private DatacubeUser(String v) {
			this.v = v;
		}

		public String url() {
			return https_domain + this.v;
		}
	}

	/**
	 * 图文数据统计
	 * 
	 */
	public static enum DataCubeNews {
		//@formatter:off
		
		/**
		 * 获取图文群发每日数据
		 */
		Summary("/datacube/getarticlesummary?access_token={0}"),
		
		/**
		 * 获取图文群发总数据
		 */
		Total("/datacube/getarticletotal?access_token={0]"),
		
		/**
		 * 获取图文统计数据
		 */
		Read("/datacube/getuserread?access_token={0}"),
		
		/**
		 * 获取图文统计分时数据
		 */
		Read_Hour("/datacube/getuserreadhour?access_token={0}"),
		
		/**
		 * 获取图文分享转发数据
		 */
		Share("/datacube/getusershare?access_token={0}"),
		
		/**
		 * 获取图文分享转发分时数据
		 */
		Share_Hour("/datacube/getusersharehour?access_token={0}");
		
		//@formatter:on

		private String v;

		private DataCubeNews(String v) {
			this.v = v;
		}

		public String url() {
			return https_domain + this.v;
		}
	}

	/**
	 * 消息数据统计
	 * 
	 */
	public static enum DatacubeMessage {
		//@formatter:off
		/**
		 * 获取消息发送概况数据
		 */
		Upstream_Msg("/datacube/getupstreammsg?access_token={0}"), 
		
		/**
		 * 获取消息发送分时数据
		 */
		Upstream_Msg_Hour("/datacube/getupstreammsg?access_token={0}"),
		
		/**
		 * 获取消息发送概况数据
		 */
		Upstream_Msg_Week("/datacube/getupstreammsgweek?access_token={0}"), 
		
		/**
		 * 获取消息发送概况数据
		 */
		Upstream_Msg_Month("/datacube/getupstreammsgmonth?access_token={0}"), 
		
		/**
		 * 获取消息发送分布数据
		 */
		Upstream_Msg_Dist("/datacube/getupstreammsgdist?access_token={0}"), 
		
		/**
		 * 获取消息发送分布数据周数据
		 */
		Upstream_Msg_Dist_Week("/datacube/getupstreammsgdistweek?access_token={0}"), 
		
		/**
		 * 获取消息发送分布数据周数据
		 */
		Upstream_Msg_Dist_Month("/datacube/getupstreammsgdistmonth?access_token={0}"), 
		;
		
		//@formatter:on

		private String v;

		private DatacubeMessage(String v) {
			this.v = v;
		}

		public String url() {
			return https_domain + this.v;
		}
	}

	/**
	 * 接口数据统计
	 * 
	 */
	public static enum DataCubeInterface {
		//@formatter:off
		/**
		 * 获取接口分析数据
		 */
		Summary("/datacube/getinterfacesummary?access_token={0}"),
		
		/**
		 * 获取接口分析分时数据
		 */
		Summary_Hour("/datacube/getinterfacesummaryhour?access_token={0}");
		//@formatter:on

		private String v;

		private DataCubeInterface(String v) {
			this.v = v;
		}

		public String url() {
			return https_domain + this.v;
		}
	}

	/**
	 * 支付接口
	 * 
	 */
	public static enum Pay {
		//@formatter:off
		/**
		 * 统一下单
		 */
		Order("/pay/unifiedorder"), 
		
		/**
		 * 查询订单
		 */
		Find("/pay/orderquery"),
		
		/**
		 * 关闭订单
		 */
		Close("/pay/closeorder"),
		
		/**
		 * 申请退款
		 */
		Refund("/secapi/pay/refund"),
		
		/**
		 * 查询退款
		 */
		FindRefund("/pay/refundquery"),
		
		/**
		 * 下载对账单
		 */
		DownloadBill("/pay/downloadbill"),
		
		/**
		 * 交易保障
		 */
		Report("/payitil/report"),

		/**
		 * 拉取订单评价数据
		 */
		BillComment("/billcommentsp/batchquerycomment")
		;
		//@formatter:on

		private String v;

		private Pay(String v) {
			this.v = v;
		}

		public String url() {
			return pay_domain + this.v;
		}
	}

}
