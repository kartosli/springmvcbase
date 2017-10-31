package com.n5x.common.weixinutils;

public class ConfigUtil {
	/**
	 * 服务号相关信息
	 */
	 public final static String APPID = "wxdc553303c1b6aa37";//服务号的应用号
	 public final static String APP_SECRECT = "41ec0dd6d73d175565ae088b70f0dfd5";//服务号的应用密码
	 public final static String TOKEN = "weixinCourse";//服务号的配置token
	 public final static String MCH_ID = "1351243201";//商户号
	 public final static String API_KEY = "yzt11111222223333344444555556666";//API密钥
	 public final static String SIGN_TYPE = "MD5";//签名加密方式
	 public final static String CERT_PATH = "D:/apiclient_cert.p12";//微信支付证书存放路径地址
	 private final static String root = "http://qkt.test.qk100.com";//"http://219.128.49.146:7580";
	//微信支付统一接口的回调action
	 public final static String NOTIFY_URL = root+"/payment/weixin_callback.do";
	//微信支付成功支付后跳转的地址
	 public final static String SUCCESS_URL = root+"/payment/weixin_success.do";
	 //oauth2授权时回调action
	 public final static String REDIRECT_URI = root+"/weixinTest.jsp";
	/**
	 * 微信基础接口地址
	 */	
	 //获取token接口(GET)
	 public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	 //oauth2授权接口(GET)
	 public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	 //获取用户信息
	 public final static String USER_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	 //刷新access_token接口（GET）
	 public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	// 菜单创建接口（POST）
	 public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询（GET）
	 public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（GET）
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/**
	 * 微信支付接口地址
	 */
	//微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	//退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	//对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	//接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
}